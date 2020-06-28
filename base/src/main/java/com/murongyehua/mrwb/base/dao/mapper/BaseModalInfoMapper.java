package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseModalInfoPO;

import java.util.List;

public interface BaseModalInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseModalInfoPO record);

    int insertSelective(BaseModalInfoPO record);

    BaseModalInfoPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseModalInfoPO record);

    int updateByPrimaryKey(BaseModalInfoPO record);

    List<BaseModalInfoPO> selectBySelective(BaseModalInfoPO record);
}