package com.treat;

import com.alibaba.fastjson.JSON;
import com.treat.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAccount("123456789");
        userDTO.setPassword("6666");

        // 对象转成json串
        String json = JSON.toJSONString(userDTO);

        stringRedisTemplate.opsForValue().set("user:10", json);

        // json转为javaBean
        UserDTO userDTO1 = JSON.parseObject(stringRedisTemplate.opsForValue().get("user:10"), UserDTO.class);

        System.out.println(userDTO1.toString());

    }
}
