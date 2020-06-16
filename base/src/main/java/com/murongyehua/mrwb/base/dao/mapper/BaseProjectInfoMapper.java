package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;

public interface BaseProjectInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseProjectInfoPO record);

    int insertSelective(BaseProjectInfoPO record);

    BaseProjectInfoPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseProjectInfoPO record);

    int updateByPrimaryKey(BaseProjectInfoPO record);
}