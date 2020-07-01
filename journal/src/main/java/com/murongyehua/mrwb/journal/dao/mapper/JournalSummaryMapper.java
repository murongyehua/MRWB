package com.murongyehua.mrwb.journal.dao.mapper;

import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.journal.dao.po.JournalSummaryPO;

import java.util.List;

public interface JournalSummaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(JournalSummaryPO record);

    int insertSelective(JournalSummaryPO record);

    JournalSummaryPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JournalSummaryPO record);

    int updateByPrimaryKey(JournalSummaryPO record);

    List<JournalSummaryPO> selectBySelective(JournalSummaryPO summaryPO);

    List<JournalSummaryPO> query(JournalSummaryParam param);
}