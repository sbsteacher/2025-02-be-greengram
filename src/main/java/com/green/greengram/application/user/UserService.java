package com.green.greengram.application.user;

import com.green.greengram.application.user.model.UserGetOneRes;
import com.green.greengram.application.user.model.UserSignInReq;
import com.green.greengram.application.user.model.UserSignInRes;
import com.green.greengram.application.user.model.UserSignUpReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public int signUp(UserSignUpReq req, MultipartFile pic) {
        String hashedPw = passwordEncoder.encode( req.getUpw() );
        log.info("hashedPw: {}", hashedPw);
        req.setUpw(hashedPw);
        return userMapper.signUp(req);
    }

    public UserSignInRes signIn(UserSignInReq req) {
        UserGetOneRes res = userMapper.findByUid( req.getUid() );
        log.info("res: {}", res);
        if(!passwordEncoder.matches(req.getUpw(), res.getUpw())) {
            return null;
        }
        //로그인 성공!! 예전에는 AT, RT을 FE전달  >>> 보안 쿠키 이용
//        JwtUser jwtUser = new JwtUser(res.getId());
//        String accessToken = jwtTokenProvider.generateAccessToken(jwtUser);
//        String refreshToken = jwtTokenProvider.generateRefreshToken(jwtUser);

        return UserSignInRes.builder()
                            .signedUserId( res.getId() )
                            .nm( res.getNm() )
                            .build();
    }
}
