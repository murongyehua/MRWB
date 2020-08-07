package com.murongyehua.mrwb.base.service.modal.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.api.req.ModalAddReq;
import com.murongyehua.mrwb.api.req.ModalInProjectReq;
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
import com.murongyehua.mrwb.commom.user.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public ResultContext addModal(ModalAddReq req){
        if (isModalExist(req.getModalName())) {
            return ResultContext.businessFail("模块已存在");
        }
        BaseModalInfoPO modalInfoPO = new BaseModalInfoPO();
        modalInfoPO.setName(req.getModalName());
        modalInfoPO.setId(IdUtil.simpleUUID());
        modalInfoPO.setVersion(1);
        modalInfoPO.setCreateUser(UserContext.getUserId());
        modalInfoPO.setModalState(ENCommonState.ACTIVE.getValue());
        modalInfoPO.setCreateTime(new Date());
        int count = modalInfoMapper.insert(modalInfoPO);
        if (count != 1) {
            return ResultContext.businessFail("新增失败");
        }
        return ResultContext.success("新增成功");
    }


    @Override
    public ResultContext getModalsByPorject(ModalInProjectReq req) {
        BaseModalInProjectPO modalInProject = new BaseModalInProjectPO();
        modalInProject.setProjectId(req.getProjectId());
        List<BaseModalInProjectPO> modalInProjects = modalInProjectMapper.selectBySelective(modalInProject);
        if (CollectionUtil.isEmpty(modalInProjects)) {
            return ResultContext.businessFail("此项目还未开通模块");
        }
        List<String> modalIds = modalInProjects.stream().map(BaseModalInProjectPO::getModalId).collect(Collectors.toList());
        List<BaseModalInfoPO> modalInfos = modalInfoMapper.selectByIds(modalIds);
        BaseUserRightPO param = new BaseUserRightPO();
        param.setProjectId(req.getProjectId());
        param.setUserId(req.getUserId());
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

    private boolean isModalExist(String name) {
        BaseModalInfoPO baseModalInfoPO = new BaseModalInfoPO();
        baseModalInfoPO.setName(name);
        List<BaseModalInfoPO> list = modalInfoMapper.selectBySelective(baseModalInfoPO);
        if (CollectionUtil.isNotEmpty(list)) {
            return true;
        }
        return false;
    }
}
