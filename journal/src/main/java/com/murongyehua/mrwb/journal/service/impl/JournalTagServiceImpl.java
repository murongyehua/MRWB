package com.murongyehua.mrwb.journal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENMsgCode;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.journal.dao.mapper.JournalTagMapper;
import com.murongyehua.mrwb.journal.dao.po.JournalTagPO;
import com.murongyehua.mrwb.journal.service.JournalTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Service
public class JournalTagServiceImpl implements JournalTagService {

    @Autowired
    private JournalTagMapper journalTagMapper;

    @Override
    public ResultContext addTag(String name) {
        int count = journalTagMapper.countByProjectIdAndName(UserContext.getProjectId(), name);
        if (count != 0) {
            return ResultContext.businessFail("分类已存在");
        }
        JournalTagPO journalTagPO = new JournalTagPO();
        journalTagPO.setTagname(name);
        journalTagPO.setId(IdUtil.simpleUUID());
        journalTagPO.setProjectId(UserContext.getProjectId());
        journalTagPO.setCreateUser(UserContext.getUserId());
        journalTagPO.setCreateTime(new Date());
        int successCount = journalTagMapper.insert(journalTagPO);
        if (successCount != 1) {
            return ResultContext.businessFail("操作失败");
        }
        return ResultContext.success("操作成功");
    }

    @Override
    public PageView queryTag() {
        PageView pageView = new PageView();
        List<JournalTagPO> journalTagPOS = journalTagMapper.selectByProjectId(UserContext.getProjectId());
        if (CollectionUtil.isEmpty(journalTagPOS)) {
            return pageView;
        }
        pageView.setTotal((long) journalTagPOS.size());
        pageView.setRows(journalTagPOS);
        pageView.setCode(ENMsgCode.SUCCESS.getValue());
        return pageView;
    }
}
