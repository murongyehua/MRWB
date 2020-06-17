package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseUserInfoPO;

import java.util.List;

public interface BaseUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUserInfoPO record);

    int insertSelective(BaseUserInfoPO record);

    BaseUserInfoPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseUserInfoPO record);

    int updateByPrimaryKey(BaseUserInfoPO record);

    List<BaseUserInfoPO> selectBySelective(BaseUserInfoPO userInfo);
}