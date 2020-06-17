package com.murongyehua.mrwb.base.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.murongyehua.mrwb.api.req.UserAddReq;
import com.murongyehua.mrwb.api.req.UserEditReq;
import com.murongyehua.mrwb.api.req.UserLoginReq;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserInfoMapper;
import com.murongyehua.mrwb.base.dao.po.BaseUserInfoPO;
import com.murongyehua.mrwb.base.service.user.UserInfoService;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.commom.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private BaseUserInfoMapper userInfoMapper;

    @Override
    public ResultContext addUser(UserAddReq req) {
        BaseUserInfoPO userInfoPO = new BaseUserInfoPO();
        BeanUtil.copyProperties(req, userInfoPO);
        if (isUserExsit(userInfoPO)) {
            return ResultContext.businessFail("用户已存在");
        }
        userInfoPO.setId(IdUtil.simpleUUID());
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setCreateUser(UserContext.getUserId());
        userInfoPO.setUserState(ENCommonState.ACTIVE.getValue());
        userInfoPO.setPassword(DigestUtil.md5Hex(userInfoPO.getPassword()));
        int count = userInfoMapper.insertSelective(userInfoPO);
        if (count != 1) {
            return ResultContext.businessFail("新增失败");
        }
        return ResultContext.success("新增成功");
    }

    @Override
    public ResultContext editUser(UserEditReq req) {
        return null;
    }

    @Override
    public ResultContext userLogin(UserLoginReq req, HttpServletRequest request) {
        BaseUserInfoPO userInfoPO = new BaseUserInfoPO();
        userInfoPO.setUsername(req.getUsername());
        userInfoPO.setPassword(DigestUtil.md5Hex(req.getPassword()));
        List<BaseUserInfoPO> list = userInfoMapper.selectBySelective(userInfoPO);
        if (CollectionUtil.isEmpty(list)) {
            return ResultContext.businessFail("用户名或密码错误");
        }
        BaseUserInfoPO user = list.get(0);
        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(user, userInfo);
        userInfo.setUserId(user.getId());
        request.getSession().setAttribute(UserContext.USER_SESSION, userInfo);
        return ResultContext.success("登录成功");
    }

    private boolean isUserExsit(BaseUserInfoPO userInfoPO) {
        BaseUserInfoPO checkParam = new BaseUserInfoPO();
        checkParam.setUsername(userInfoPO.getUsername());
        List<BaseUserInfoPO> sameUsernameList = userInfoMapper.selectBySelective(userInfoPO);
        if (CollectionUtil.isNotEmpty(sameUsernameList)) {
            return true;
        }
        checkParam.setUsername(null);
        checkParam.setNickname(userInfoPO.getNickname());
        List<BaseUserInfoPO> sameNicknameList = userInfoMapper.selectBySelective(userInfoPO);
        if (CollectionUtil.isNotEmpty(sameNicknameList)) {
            return true;
        }
        return false;
    }
}
