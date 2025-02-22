package com.treat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;
import com.treat.mapper.UserMapper;
import com.treat.service.IUserService;
import com.treat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.treat.utils.RedisContants.LOGIN_TOKEN_KEY;
import static com.treat.utils.RedisContants.LOGIN_TOKEN_TTL;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userDTO.getAccount())
                .eq("user_pwd", userDTO.getPassword());

        //如果未注册，即用户不存在
        if (userService.getOne(wrapper) == null) {
            return Result.fail();
        }

        // TODO 密码设置为空
        userDTO.setPassword("");
        //生成一个token
        String jwtToken = JwtUtil.getJwtToken(userDTO);
        Map<String, String> map = new HashMap<>();
        map.put(JwtUtil.token, jwtToken);

        // 存在，将用户存入到redis中
        String tokenKey = LOGIN_TOKEN_KEY + jwtToken;
        stringRedisTemplate.opsForHash().putAll(tokenKey, BeanUtil.beanToMap(userDTO));
        //设置有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_TOKEN_TTL, TimeUnit.MINUTES);
        return Result.ok(map);
    }

    @Override
    public Result register(User user) throws IOException {
        // 如果用户名重复
        if (isExists(user)) {
            return Result.fail("用户名重复，请更换用户名");
        }
        user.setUserAccount(generateUniqueUserAccount(user));
        userService.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(user.getUserAccount());

        Path sourceDir = Paths.get("D:\\Workspaces\\Project\\treattest\\dataset");
        Path targetDir = Paths.get("D:\\Workspaces\\Project\\treattest\\treatdata\\" + userDTO.getAccount() + "\\outfile\\dataset");

        // 拷贝文件夹及其内容
        Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDirPath = targetDir.resolve(sourceDir.relativize(dir));
                Files.createDirectories(targetDirPath);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetFilePath = targetDir.resolve(sourceDir.relativize(file));
                Files.copy(file, targetFilePath, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });

        // 生成一个token
        String jwtToken = JwtUtil.getJwtToken(userDTO);

        // 存在，将用户存入到redis中
        String tokenKey = LOGIN_TOKEN_KEY + jwtToken;
        stringRedisTemplate.opsForHash().putAll(tokenKey, BeanUtil.beanToMap(userDTO));
        //设置有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_TOKEN_TTL, TimeUnit.MINUTES);

        return Result.ok(JwtUtil.getJwtToken(userDTO));
    }

    @Override
    public Result forget(ChangeDTO changeDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("user_account", changeDTO.getUserAccount());
        if (userService.getOne(wrapper) == null) {
            return Result.fail("此账号不存在");
        }

        wrapper.eq("user_phone", changeDTO.getUserPhone());
        if (userService.getOne(wrapper) == null) {
            return Result.fail("手机号填写错误，请重试");
        }

        wrapper.eq("user_email", changeDTO.getUserEmail());
        if (userService.getOne(wrapper) == null) {
            return Result.fail("邮箱填写错误，请重试");
        }

        wrapper.eq("user_secproblem", changeDTO.getUserSecproblem());
        if (userService.getOne(wrapper) == null) {
            return Result.fail("密保填写错误，请重试");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(changeDTO.getUserAccount());

        return Result.ok(JwtUtil.getJwtToken(userDTO));
    }

    @Override
    public Result change(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userDTO.getAccount());
        User user = userService.getOne(wrapper);
        if (user == null) {
            return Result.fail("该用户不存在，请重试！");
        }

        user.setUserPwd(userDTO.getPassword());

        userService.saveOrUpdate(user, wrapper);
        return Result.ok();
    }

    @Override
    public Result changeSec(ChangeDTO changeDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq("user_account", changeDTO.getUserAccount());
        User user = userService.getOne(wrapper);
        if (user == null) {
            return Result.fail("此账号不存在");
        }

        if (!user.getUserPhone().equals(changeDTO.getUserPhone())) {
            return Result.fail("手机号填写错误，请重试");
        }

        if (!user.getUserEmail().equals(changeDTO.getUserEmail())) {
            return Result.fail("邮箱填写错误，请重试");
        }

        if (user.getUserSecproblem() == null || user.getUserSecproblem().equals("")) {
            user.setUserSecproblem(changeDTO.getUserSecproblem());
            userService.saveOrUpdate(user);
            return Result.ok("密保设置成功！");
        }else {
            user.setUserSecproblem(changeDTO.getUserSecproblem());
            userService.saveOrUpdate(user);
            return Result.ok("密保修改成功！");
        }
    }

    @Override
    public Result personInfo(String userAccount) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userAccount);

        return Result.ok(userService.getOne(wrapper));
    }

    public String generateUniqueUserAccount(User user) {
        boolean isUnique = false;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", user.getUserAccount());

        while (!isUnique) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            char firstChar = uuid.charAt(0);

            // 如果生成的 UUID 不满足首字符为 1 或 2，重新生成
            if (firstChar != '1' && firstChar != '2') {
                continue;
            }

            String userAccount = uuid.substring(0, 10);

            // 判断是否全部为数字
            if (userAccount.matches("\\d+")) {
                // 判断是否存在相同账号
                User existingUser = userService.getOne(wrapper);
                // 不存在，则创建账号
                if (existingUser == null) {
                    isUnique = true;
                    return userAccount;
                }
            }
        }
        return null;
    }


    public boolean isExists(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", user.getUserName());

        if (userService.getOne(wrapper) != null) {
            return true;
        }else {
            return false;
        }
    }
}
