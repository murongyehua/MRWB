package com.murongyehua.mrwb.controller.base.user;

import com.murongyehua.mrwb.api.param.UserListParam;
import com.murongyehua.mrwb.api.req.UserAddReq;
import com.murongyehua.mrwb.api.req.UserEditReq;
import com.murongyehua.mrwb.api.req.UserLoginReq;
import com.murongyehua.mrwb.base.service.user.UserInfoService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@RestController
@RequestMapping("/base/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/add.smg")
    public ResultContext addUser(UserAddReq req) {
        return userInfoService.addUser(req);
    }

    @PostMapping("/login.pub")
    public ResultContext userLogin(UserLoginReq req, HttpServletRequest request) {
        return userInfoService.userLogin(req, request);
    }

    @PostMapping("/query.smg")
    public PageView queryList(UserListParam param) {
        return userInfoService.queryUserList(param);
    }

    @PostMapping("/edit.do")
    public ResultContext editUser(UserEditReq req) {
        return userInfoService.editUser(req);
    }
}
