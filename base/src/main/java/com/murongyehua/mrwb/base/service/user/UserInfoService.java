package com.murongyehua.mrwb.base.service.user;

import com.murongyehua.mrwb.api.req.UserAddReq;
import com.murongyehua.mrwb.api.req.UserEditReq;
import com.murongyehua.mrwb.api.req.UserLoginReq;
import com.murongyehua.mrwb.commom.ResultContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
public interface UserInfoService {

    ResultContext addUser(UserAddReq req);

    ResultContext editUser(UserEditReq req);

    ResultContext userLogin(UserLoginReq req, HttpServletRequest request);

}
