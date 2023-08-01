package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;

import java.io.IOException;

public interface IUserService extends IService<User> {
    Result login(UserDTO userDTO);

    Result register(User user) throws IOException;

    Result forget(ChangeDTO changeDTO);

    Result change(UserDTO userDTO);

    Result changeSec(ChangeDTO changeDTO);

    Result personInfo(String userAccount);
}
