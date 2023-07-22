package com.treat.utils;

import com.treat.dto.MyException;
import com.treat.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;
import java.util.Date;



public class JwtUtil {
    // token失效：24小时
    public static final String token = "token";
    public static final long EXPIPE = 1000 * 60 * 60 * 2;
    public static final String APP_SECRET = "modox@ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 根据传入的用户Id和类型生成token
     * @param user user对象
     * @return JWT规则生成的token
     */
    public static String getJwtToken(UserDTO user) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("treat_user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIPE))
                .claim("userAccount", user.getAccount())
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 验证token是否有效
     * @param jwtToken token字符串
     * @return 如果token有效返回true，否则false
     */
    public static boolean checkToken(String jwtToken) {
        try {
            if (StringUtils.isEmpty(jwtToken))
                return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取User信息
     * @param jwtToken token字符串
     * @return 解析token获得的user对象
     */
    public static UserDTO getUser(String jwtToken) {
        //验证token
        if (checkToken(jwtToken)) {
            Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken).getBody();
            UserDTO user = new UserDTO();
            user.setAccount(claims.get("userAccount").toString());
            return user;
        }else {
            throw new MyException("超时或不合法token");
        }
    }
}
