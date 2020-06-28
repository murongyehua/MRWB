package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseMenuPO;
import com.murongyehua.mrwb.base.dao.po.BaseModalMenuPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseModalMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseModalMenuPO record);

    int insertSelective(BaseModalMenuPO record);

    BaseModalMenuPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseModalMenuPO record);

    int updateByPrimaryKey(BaseModalMenuPO record);

    List<BaseModalMenuPO> selectByModalIds(@Param("modalIds") List<String> modalIds);

}