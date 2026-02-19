package com.green.greengram.application.user;

import com.green.greengram.application.user.model.UserGetOneRes;
import com.green.greengram.application.user.model.UserSignUpReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int signUp(UserSignUpReq req);
    UserGetOneRes findByUid(String uid);
}
