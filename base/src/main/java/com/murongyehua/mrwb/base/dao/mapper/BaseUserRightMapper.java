package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseUserRightPO;

public interface BaseUserRightMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUserRightPO record);

    int insertSelective(BaseUserRightPO record);

    BaseUserRightPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseUserRightPO record);

    int updateByPrimaryKey(BaseUserRightPO record);
}