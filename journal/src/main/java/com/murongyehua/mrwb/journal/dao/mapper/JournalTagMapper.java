package com.murongyehua.mrwb.journal.dao.mapper;

import com.murongyehua.mrwb.journal.dao.po.JournalTagPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JournalTagMapper {
    int deleteByPrimaryKey(String id);

    int insert(JournalTagPO record);

    int insertSelective(JournalTagPO record);

    JournalTagPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JournalTagPO record);

    int updateByPrimaryKey(JournalTagPO record);

    List<JournalTagPO> selectByProjectId(@Param("projectId") String projectId);
}