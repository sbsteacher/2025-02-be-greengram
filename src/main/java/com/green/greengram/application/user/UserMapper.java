package com.green.greengram.application.user;

import com.green.greengram.application.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int signUp(UserSignUpReq req);
    UserGetOneRes findByUid(String uid);
    UserGetOneRes findById(long id);
    UserProfileGetRes findProfileUser (UserProfileGetReq req);
    int updUser(UserUpdDto dto);
}
