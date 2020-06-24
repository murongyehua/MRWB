package com.murongyehua.mrwb.base.service.project.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.ProjectParam;
import com.murongyehua.mrwb.api.req.ProjectAddReq;
import com.murongyehua.mrwb.api.resp.ProjectListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseProjectInfoMapper;
import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;
import com.murongyehua.mrwb.base.service.project.ProjectInfoService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/18
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {

    @Autowired
    private BaseProjectInfoMapper projectInfoMapper;

    @Override
    public ResultContext addProject(ProjectAddReq req) {
        if (isProjectExist(req.getName())) {
            return ResultContext.businessFail("项目已存在");
        }
        BaseProjectInfoPO projectInfoPO = new BaseProjectInfoPO();
        projectInfoPO.setName(req.getName());
        projectInfoPO.setId(IdUtil.simpleUUID());
        projectInfoPO.setVersion(1);
        projectInfoPO.setCreateUser(UserContext.getUserId());
        projectInfoPO.setProjectState(ENCommonState.ACTIVE.getValue());
        projectInfoPO.setCreateTime(new Date());
        int count = projectInfoMapper.insert(projectInfoPO);
        if (count != 1) {
            return ResultContext.businessFail("新增失败");
        }
        return ResultContext.success("新增成功");
    }

    @Override
    public PageView queryProject(ProjectParam param) {
        BaseProjectInfoPO projectInfo = new BaseProjectInfoPO();
        BeanUtil.copyProperties(param, projectInfo);
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        PageInfo pageInfo = new PageInfo(this.translate(projectInfoMapper.selectBySelective(projectInfo)));
        PageView pageView = new PageView();
        BeanUtil.copyProperties(ResultContext.success("操作成功"), pageView);
        pageView.setRows(pageInfo.getList());
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    private List<ProjectListResp> translate(List<BaseProjectInfoPO> list) {
        List<ProjectListResp> result = new ArrayList<>();
        int num = 1;
        for (BaseProjectInfoPO projectInfoPO : list) {
            ProjectListResp projectListResp = new ProjectListResp();
            BeanUtil.copyProperties(projectInfoPO, projectListResp);
            projectListResp.setCreateTimeText(DateUtil.format(projectInfoPO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            projectListResp.setProjectStateText(ENCommonState.getLabelByValue(projectInfoPO.getProjectState()));
            projectListResp.setIndex(num);
            result.add(projectListResp);
            num++;
        }
        return result;
    }


    private boolean isProjectExist(String name) {
        BaseProjectInfoPO projectInfoPO = new BaseProjectInfoPO();
        projectInfoPO.setName(name);
        List<BaseProjectInfoPO> list = projectInfoMapper.selectBySelective(projectInfoPO);
        if (CollectionUtil.isNotEmpty(list)) {
            return true;
        }
        return false;
    }

}
