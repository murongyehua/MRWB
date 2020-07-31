package com.murongyehua.mrwb.base.service.userright.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.murongyehua.mrwb.api.req.UserRightReq;
import com.murongyehua.mrwb.base.dao.mapper.*;
import com.murongyehua.mrwb.base.dao.po.*;
import com.murongyehua.mrwb.base.service.userright.UserRightService;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/24
 */
@Service
public class UserRightServiceImpl implements UserRightService {

    @Autowired
    private BaseUserRightMapper userRightMapper;

    @Autowired
    private BaseProjectInfoMapper projectInfoMapper;

    @Autowired
    private BaseUserInfoMapper userInfoMapper;

    @Autowired
    private BaseModalInfoMapper modalInfoMapper;

    @Autowired
    private BaseModalInProjectMapper modalInProjectMapper;

    @Override
    public ResultContext getProjects() {
        List<String> projectIds = userRightMapper.distinctProjectId(UserContext.getUserId());
        ResultContext resultContext = ResultContext.success("操作成功");
        if (ENUserType.MANAGER.getValue().equals(UserContext.getUserInfo().getUserType())) {
            return resultContext;
        }
        if (CollectionUtil.isEmpty(projectIds)) {
            return ResultContext.businessFail("无项目权限，请联系管理员");
        }
        List<BaseProjectInfoPO> projects = projectInfoMapper.selectByIds(projectIds);
        resultContext.setData(projects);
        return resultContext;
    }

    @Override
    public ResultContext getUsers() {
        ResultContext resultContext = ResultContext.success("操作成功");
        List<String> userIds = userRightMapper.distinctUserId(UserContext.getProjectId());
        if (CollectionUtil.isEmpty(userIds)) {
            return ResultContext.businessFail("无可选用户");
        }
        List<BaseUserInfoPO> list = userInfoMapper.selectByIds(userIds);
        resultContext.setData(list);
        return resultContext;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultContext addUserRight(UserRightReq req) {
        // 项目是否存在
        BaseProjectInfoPO projectInfo = projectInfoMapper.selectByPrimaryKey(req.getProjectId());
        if (projectInfo == null) {
            return ResultContext.businessFail("项目不存在");
        }
        // 模块是否存在
        if (CollectionUtil.isNotEmpty(req.getModalIds())) {
            List<BaseModalInfoPO> modalInfos = modalInfoMapper.selectByIds(req.getModalIds());
            if (CollectionUtil.isEmpty(modalInfos) || modalInfos.size() != req.getModalIds().size()) {
                return ResultContext.businessFail("存在模块不存在");
            }
        }
        // 用户是否存在
        BaseUserInfoPO userInfo = userInfoMapper.selectByPrimaryKey(req.getUserId());
        if (userInfo == null) {
            return ResultContext.businessFail("用户不存在");
        }
        BaseUserRightPO userRight = new BaseUserRightPO();
        userRight.setProjectId(req.getProjectId());
        userRight.setUserId(req.getUserId());
        // 先删除
        userRightMapper.deleteByProjectIdAndUserId(userRight);
        // 若没有模块，可能是取消权限，就不用继续保存了，直接返操作成功
        if (CollectionUtil.isEmpty(req.getModalIds())) {
            return ResultContext.success("操作成功");
        }
        req.getModalIds().forEach(x -> {
            userRight.setModalId(x);
            userRight.setCreateTime(new Date());
            userRight.setCreateUser(UserContext.getUserId());
            userRight.setId(IdUtil.simpleUUID());
            userRightMapper.insert(userRight);
        });
        return ResultContext.success("操作成功");
    }

}
