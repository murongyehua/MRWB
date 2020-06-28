package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseMenuPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseMenuPO record);

    int insertSelective(BaseMenuPO record);

    BaseMenuPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseMenuPO record);

    int updateByPrimaryKey(BaseMenuPO record);

    List<BaseMenuPO> selectBySelective(BaseMenuPO record);

    List<BaseMenuPO> selectByIds(@Param("ids") List<String> ids);
}