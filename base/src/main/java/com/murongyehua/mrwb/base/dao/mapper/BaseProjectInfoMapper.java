package com.murongyehua.mrwb.base.dao.mapper;

import com.murongyehua.mrwb.base.dao.po.BaseProjectInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseProjectInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseProjectInfoPO record);

    int insertSelective(BaseProjectInfoPO record);

    BaseProjectInfoPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseProjectInfoPO record);

    int updateByPrimaryKey(BaseProjectInfoPO record);

    List<BaseProjectInfoPO> selectBySelective(BaseProjectInfoPO projectInfo);

    List<BaseProjectInfoPO> selectByIds(@Param("ids") List<String> ids);
}