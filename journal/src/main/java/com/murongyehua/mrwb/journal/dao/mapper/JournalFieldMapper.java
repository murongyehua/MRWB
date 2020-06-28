package com.murongyehua.mrwb.journal.dao.mapper;

import com.murongyehua.mrwb.journal.dao.po.JournalFieldPO;

public interface JournalFieldMapper {
    int deleteByPrimaryKey(String id);

    int insert(JournalFieldPO record);

    int insertSelective(JournalFieldPO record);

    JournalFieldPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JournalFieldPO record);

    int updateByPrimaryKey(JournalFieldPO record);
}