package com.murongyehua.mrwb.base.service.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.dto.UserInfoDTO;
import com.murongyehua.mrwb.api.param.UserListParam;
import com.murongyehua.mrwb.api.req.UserAddReq;
import com.murongyehua.mrwb.api.req.UserEditReq;
import com.murongyehua.mrwb.api.req.UserLoginReq;
import com.murongyehua.mrwb.api.resp.UserListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserInfoMapper;
import com.murongyehua.mrwb.base.dao.po.BaseUserInfoPO;
import com.murongyehua.mrwb.base.service.user.UserInfoService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.commom.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        if (isUserExist(userInfoPO)) {
            return ResultContext.businessFail("用户已存在");
        }
        userInfoPO.setId(IdUtil.simpleUUID());
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setCreateUser(UserContext.getUserId());
        userInfoPO.setUserState(ENCommonState.ACTIVE.getValue());
        userInfoPO.setPassword(DigestUtil.md5Hex(userInfoPO.getPassword()));
        userInfoPO.setVersion(1);
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
        user.setLastLoginTime(new Date());
        userInfoMapper.updateByPrimaryKey(user);
        ResultContext resultContext = ResultContext.success("登录成功");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        BeanUtil.copyProperties(user, userInfoDTO);
        userInfoDTO.setLastLoginTimeText(DateUtil.format(user.getLastLoginTime(), DatePattern.NORM_DATETIME_PATTERN));
        resultContext.setData(userInfoDTO);
        return resultContext;
    }

    @Override
    public PageView queryUserList(UserListParam param) {
        BaseUserInfoPO userInfoPO = new BaseUserInfoPO();
        BeanUtil.copyProperties(param, userInfoPO);
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        List<BaseUserInfoPO> list = userInfoMapper.selectBySelective(userInfoPO);
        PageInfo pageInfo = new PageInfo(this.translate(list));
        PageView pageView = new PageView();
        BeanUtil.copyProperties(ResultContext.success("操作成功"), pageView);
        pageView.setRows(pageInfo.getList());
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    private List<UserListResp> translate(List<BaseUserInfoPO> list) {
        List<UserListResp> result = new ArrayList<>();
        int num = 1;
        for (BaseUserInfoPO userInfoPO : list) {
            UserListResp userListResp = new UserListResp();
            BeanUtil.copyProperties(userInfoPO, userListResp);
            userListResp.setUserStateText(ENCommonState.getLabelByValue(userInfoPO.getUserState()));
            userListResp.setUserTypeText(ENUserType.getLabelByValue(userInfoPO.getUserType()));
            userListResp.setIndex(num);
            result.add(userListResp);
            num++;
        }
        return result;
    }

    private boolean isUserExist(BaseUserInfoPO userInfoPO) {
        BaseUserInfoPO checkParam = new BaseUserInfoPO();
        checkParam.setUsername(userInfoPO.getUsername());
        List<BaseUserInfoPO> sameUsernameList = userInfoMapper.selectBySelective(checkParam);
        if (CollectionUtil.isNotEmpty(sameUsernameList)) {
            return true;
        }
        checkParam.setUsername(null);
        checkParam.setNickname(userInfoPO.getNickname());
        List<BaseUserInfoPO> sameNicknameList = userInfoMapper.selectBySelective(checkParam);
        if (CollectionUtil.isNotEmpty(sameNicknameList)) {
            return true;
        }
        return false;
    }
}
