package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;

public interface IUserService extends IService<User> {
    Boolean login(UserDTO userDTO);

    Result register(User user);

    Result forget(ChangeDTO changeDTO);

    Result change(UserDTO userDTO);

    Result changeSec(ChangeDTO changeDTO);

    Result personInfo(String userAccount);
}
