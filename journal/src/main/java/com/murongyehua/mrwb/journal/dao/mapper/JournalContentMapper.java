package com.murongyehua.mrwb.journal.dao.mapper;

import com.murongyehua.mrwb.journal.dao.po.JournalContentPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JournalContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(JournalContentPO record);

    int insertSelective(JournalContentPO record);

    JournalContentPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JournalContentPO record);

    int updateByPrimaryKey(JournalContentPO record);

    int insertBatch(@Param("list") List<JournalContentPO> contentPOS);
}