package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;
import com.treat.mapper.UserMapper;
import com.treat.service.IUserService;
import com.treat.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private IUserService userService;

    @Override
    public Boolean login(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_account", userDTO.getAccount()).eq("user_pwd", userDTO.getPassword());

        if (userService.getOne(wrapper) != null) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Result register(User user) {
        // 如果用户名重复
        if (isExists(user)) {
            return Result.fail("用户名重复，请更换用户名");
        }
        user.setUserAccount(generateUniqueUserAccount(user));
        userService.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setAccount(user.getUserAccount());
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
