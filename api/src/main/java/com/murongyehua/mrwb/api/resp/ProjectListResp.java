package com.murongyehua.mrwb.api.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liul
 * @version 1.0 2020/6/23
 */
@Getter
@Setter
public class ProjectListResp {
    private String id;

    private String name;

    private String projectState;

    private String createUser;

    private Date createTime;

    private Integer version;

    private String createTimeText;

    private String projectStateText;

    private int index;
}
