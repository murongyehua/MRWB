package com.murongyehua.mrwb.base.service.modal.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.murongyehua.mrwb.api.param.ModalParam;
import com.murongyehua.mrwb.api.resp.ModalListResp;
import com.murongyehua.mrwb.api.resp.ProjectListResp;
import com.murongyehua.mrwb.base.dao.mapper.BaseModalInfoMapper;
import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;
import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;
import com.murongyehua.mrwb.base.service.modal.ModalService;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@Service
public class ModalServiceImpl implements ModalService {

    @Autowired
    private BaseModalInfoMapper modalInfoMapper;

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
