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
    public ResultContext addUserRight(UserRightReq req) {
        // 项目是否存在
        BaseProjectInfoPO projectInfo = projectInfoMapper.selectByPrimaryKey(req.getProjectId());
        if (projectInfo == null) {
            return ResultContext.businessFail("项目不存在");
        }
        // 模块是否存在
        BaseModalInfoPO modalInfo = modalInfoMapper.selectByPrimaryKey(req.getModalId());
        if (modalInfo == null) {
            return ResultContext.businessFail("项目不存在");
        }
        // 项目是否已开通模块
        BaseModalInProjectPO modalInProjectParam = new BaseModalInProjectPO();
        modalInProjectParam.setModalId(req.getModalId());
        modalInProjectParam.setProjectId(req.getProjectId());
        List<BaseModalInProjectPO> modalInProjects = modalInProjectMapper.selectBySelective(modalInProjectParam);
        if (CollectionUtil.isEmpty(modalInProjects)) {
            return ResultContext.businessFail("该项目未开通此模块");
        }
        // 用户是否存在
        BaseUserInfoPO userInfo = userInfoMapper.selectByPrimaryKey(req.getUserId());
        if (userInfo == null) {
            return ResultContext.businessFail("用户不存在");
        }
        // 记录是否已存在
        BaseUserRightPO userRightParam = new BaseUserRightPO();
        userRightParam.setModalId(req.getModalId());
        userRightParam.setProjectId(req.getProjectId());
        userRightParam.setUserId(req.getUserId());
        List<BaseUserRightPO> userRights = userRightMapper.selectBySelective(userRightParam);
        if (CollectionUtil.isNotEmpty(userRights)) {
            return ResultContext.businessFail("权限已分配，请勿重复操作");
        }
        userRightParam.setCreateTime(new Date());
        userRightParam.setCreateUser(UserContext.getUserId());
        userRightParam.setId(IdUtil.simpleUUID());
        int count = userRightMapper.insert(userRightParam);
        if (count != 1) {
            return ResultContext.businessFail("操作失败");
        }
        return ResultContext.success("操作成功");
    }

}
