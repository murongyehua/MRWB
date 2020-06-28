package com.murongyehua.mrwb.journal.dao.mapper;

import com.murongyehua.mrwb.journal.dao.po.JournalContentPO;

public interface JournalContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(JournalContentPO record);

    int insertSelective(JournalContentPO record);

    JournalContentPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JournalContentPO record);

    int updateByPrimaryKey(JournalContentPO record);
}