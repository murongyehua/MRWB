package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseUserInfoPO;

public interface BaseUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUserInfoPO record);

    int insertSelective(BaseUserInfoPO record);

    BaseUserInfoPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseUserInfoPO record);

    int updateByPrimaryKey(BaseUserInfoPO record);
}