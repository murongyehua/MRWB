package com.murongyehua.mrwb.commom;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseQueryParams implements Serializable {

    private static final long serialVersionUID = 2227634848787001565L;
    private static final String SORT_BY_FIELD_SUFFIX_TO_CUT = "translate";

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数量
     */
    private Integer pageSize = 20;

    /**
     * 表格排序字段名
     */
    private String sortName;

    /**
     * 表格排序类型 desc,asc
     */
    private String sortType;

    public String getOrderBy() {
        String sortBy = null;
        if (StrUtil.isNotBlank(sortName) && StrUtil.isNotBlank(sortType)) {
            /*// 截取尾缀为Translate的字符串
            if(sortName.toLowerCase().endsWith(SORT_BY_FIELD_SUFFIX_TO_CUT)){
                sortName = sortName.substring(0,sortName.length()-SORT_BY_FIELD_SUFFIX_TO_CUT.length());
            }
            //先把驼峰转换成数据库下划线格式
            sortBy = StrUtil.toUnderlineCase(sortName) + " " + sortType;*/
            sortBy = sortName + " " + sortType;
        }
        return sortBy;
    }

    public String getDtoOrderBy() {
        String sortBy = null;
        if (StrUtil.isNotBlank(sortName) && StrUtil.isNotBlank(sortType)) {
            sortBy = sortName + " " + sortType;
        }
        return sortBy;
    }
}
