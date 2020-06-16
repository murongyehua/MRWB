package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * base_project_info
 * @author 
 */
public class BaseProjectInfoPO implements Serializable {
    private String id;

    private String name;

    private String projectState;

    private String createUser;

    private Date createTime;

    private Integer version;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}