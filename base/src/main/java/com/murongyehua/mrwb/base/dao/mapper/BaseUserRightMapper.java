package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseUserRightPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseUserRightMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUserRightPO record);

    int insertSelective(BaseUserRightPO record);

    BaseUserRightPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseUserRightPO record);

    int updateByPrimaryKey(BaseUserRightPO record);

    List<String> distinctProjectId(@Param("userId") String userId);

    List<String> distinctUserId(@Param("projectId") String projectId);

    List<BaseUserRightPO> selectBySelective(BaseUserRightPO record);
}