package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseModalInProjectPO;

public interface BaseModalInProjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseModalInProjectPO record);

    int insertSelective(BaseModalInProjectPO record);

    BaseModalInProjectPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseModalInProjectPO record);

    int updateByPrimaryKey(BaseModalInProjectPO record);
}