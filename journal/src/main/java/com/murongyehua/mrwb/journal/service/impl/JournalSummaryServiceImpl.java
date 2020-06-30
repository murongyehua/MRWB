package com.murongyehua.mrwb.journal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryAddReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.commom.enums.ENCommonState;
import com.murongyehua.mrwb.commom.enums.ENJournalState;
import com.murongyehua.mrwb.commom.exception.MRBaseException;
import com.murongyehua.mrwb.commom.user.UserContext;
import com.murongyehua.mrwb.journal.dao.mapper.JournalContentMapper;
import com.murongyehua.mrwb.journal.dao.mapper.JournalFieldMapper;
import com.murongyehua.mrwb.journal.dao.mapper.JournalSummaryMapper;
import com.murongyehua.mrwb.journal.dao.po.JournalContentPO;
import com.murongyehua.mrwb.journal.dao.po.JournalFieldPO;
import com.murongyehua.mrwb.journal.dao.po.JournalSummaryPO;
import com.murongyehua.mrwb.journal.service.JournalSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Service
public class JournalSummaryServiceImpl implements JournalSummaryService {

    @Autowired
    private JournalSummaryMapper summaryMapper;

    @Autowired
    private JournalFieldMapper fieldMapper;

    @Autowired
    private JournalContentMapper contentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultContext addSummary(JournalSummaryAddReq addReq) {
        // 判重
        JournalSummaryPO tempPo = new JournalSummaryPO();
        tempPo.setTitle(addReq.getTitle());
        List<JournalSummaryPO> tempList = summaryMapper.selectBySelective(tempPo);
        if (CollectionUtil.isNotEmpty(tempList)) {
            throw new MRBaseException("标题重复");
        }
        // 添加汇总表
        JournalSummaryPO journalSummaryPO = this.req2JournalSummaryPo(addReq);
        int summaryCount = summaryMapper.insert(journalSummaryPO);
        if (summaryCount != 1) {
            throw new MRBaseException("新增失败");
        }
        // 添加字段内容表
            // 获取项目字段
            List<JournalFieldPO> fieldPOS = fieldMapper.selectByProject(UserContext.getProjectId());
            if (CollectionUtil.isEmpty(fieldPOS)) {
                throw new MRBaseException("记录字段未配置");
            }
            List<JournalContentPO> contentPOS = new ArrayList<>();
            for (JournalFieldPO fieldPO : fieldPOS) {
                contentPOS.add(this.getJournalContent(fieldPO, addReq.getFieldContents(), journalSummaryPO.getId()));
            }
            int contentCount = contentMapper.insertBatch(contentPOS);
            if (contentCount != contentPOS.size()) {
                throw new MRBaseException("字段内容保存异常");
            }
        return ResultContext.success("操作成功");
    }

    @Override
    public PageView queryList(JournalSummaryParam param) {
        return null;
    }

    private JournalSummaryPO req2JournalSummaryPo(JournalSummaryAddReq req) {
        JournalSummaryPO journalSummaryPO = new JournalSummaryPO();
        BeanUtil.copyProperties(req, journalSummaryPO);
        journalSummaryPO.setId(IdUtil.simpleUUID());
        journalSummaryPO.setCreateUser(UserContext.getUserId());
        journalSummaryPO.setCreateTime(new Date());
        journalSummaryPO.setLastModifyTime(new Date());
        journalSummaryPO.setProjectId(UserContext.getProjectId());
        journalSummaryPO.setState(ENJournalState.ACTIVE.getValue());
        return journalSummaryPO;
    }

    private JournalContentPO getJournalContent(JournalFieldPO fieldPO, List<String> contents, String summaryId) {
            JournalContentPO contentPO = new JournalContentPO();
            contentPO.setId(IdUtil.simpleUUID());
            contentPO.setSummaryId(summaryId);
            contentPO.setFieldId(fieldPO.getId());
            contentPO.setContent(contents.get(Integer.parseInt(fieldPO.getSortnum())));
            return contentPO;
    }
}
