package com.murongyehua.mrwb.base.service.modal.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.api.resp.ModalListResp;
import com.murongyehua.mrwb.api.resp.ProjectListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseModalInProjectMapper;
import com.murongyehua.mrwb.base.dao.mapper.BaseModalInfoMapper;
import com.murongyehua.mrwb.base.dao.mapper.BaseUserRightMapper;
import com.murongyehua.mrwb.base.dao.po.BaseModalInProjectPO;
import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;
import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;
import com.murongyehua.mrwb.base.dao.po.BaseUserRightPO;
import com.murongyehua.mrwb.base.service.dto.ModalInProjectResp;
import com.murongyehua.mrwb.base.service.modal.ModalService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@Service
public class ModalServiceImpl implements ModalService {

    @Autowired
    private BaseModalInfoMapper modalInfoMapper;

    @Autowired
    private BaseModalInProjectMapper modalInProjectMapper;

    @Autowired
    private BaseUserRightMapper userRightMapper;

    @Override
    public PageView queryList(ModalParam param) {
        BaseModalInfoPO modalInfoPO = new BaseModalInfoPO();
        BeanUtil.copyProperties(param, modalInfoPO);
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.getOrderBy());
        PageInfo pageInfo = new PageInfo(this.translate(modalInfoMapper.selectBySelective(modalInfoPO)));
        PageView pageView = new PageView();
        BeanUtil.copyProperties(ResultContext.success("操作成功"), pageView);
        pageView.setRows(pageInfo.getList());
        pageView.setTotal(pageInfo.getTotal());
        return pageView;
    }

    @Override
    public ResultContext getModalsByPorject(String projectId, String userId) {
        BaseModalInProjectPO modalInProject = new BaseModalInProjectPO();
        modalInProject.setProjectId(projectId);
        List<BaseModalInProjectPO> modalInProjects = modalInProjectMapper.selectBySelective(modalInProject);
        List<String> modalIds = modalInProjects.stream().map(BaseModalInProjectPO::getModalId).collect(Collectors.toList());
        List<BaseModalInfoPO> modalInfos = modalInfoMapper.selectByIds(modalIds);
        BaseUserRightPO param = new BaseUserRightPO();
        param.setProjectId(projectId);
        param.setUserId(userId);
        List<BaseUserRightPO> userRights = userRightMapper.selectBySelective(param);
        ModalInProjectResp modalInProjectResp = new ModalInProjectResp();
        modalInProjectResp.setModalInfos(modalInfos);
        if (CollectionUtil.isNotEmpty(userRights)) {
            modalInProjectResp.setDistributedModalIds(userRights.stream().map(BaseUserRightPO::getModalId).collect(Collectors.toList()));
        }else {
            modalInProjectResp.setDistributedModalIds(new ArrayList<>());
        }
        ResultContext resultContext = ResultContext.success("操作成功");
        resultContext.setData(modalInProjectResp);
        return resultContext;
    }

    private List<ModalListResp> translate(List<BaseModalInfoPO> list) {
        List<ModalListResp> result = new ArrayList<>();
        int num = 1;
        for (BaseModalInfoPO modalInfoPO : list) {
            ModalListResp modalListResp = new ModalListResp();
            BeanUtil.copyProperties(modalInfoPO, modalListResp);
            modalListResp.setCreateTimeText(DateUtil.format(modalInfoPO.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
            modalListResp.setModalStateText(ENCommonState.getLabelByValue(modalInfoPO.getModalState()));
            modalListResp.setIndex(num);
            result.add(modalListResp);
            num++;
        }
        return result;
    }
}
