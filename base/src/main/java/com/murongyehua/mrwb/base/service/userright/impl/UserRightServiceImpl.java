package com.murongyehua.mrwb.base.service.userright.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.murongyehua.mrwb.base.dao.mapper.BaseProjectInfoMapper;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserRightMapper;
import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;
import com.murongyehua.mrwb.base.service.userright.UserRightService;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENUserType;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
