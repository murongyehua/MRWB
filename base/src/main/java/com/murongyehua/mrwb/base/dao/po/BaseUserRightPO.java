package com.murongyehua.mrwb.base.dao.po;

import java.io.Serializable;
import java.util.Date;

/**
 * base_user_right
 * @author 
 */
public class BaseUserRightPO implements Serializable {
    private String id;

    private String projectId;

    private String modalId;

    private String userId;

    private String createUser;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getModalId() {
        return modalId;
    }

    public void setModalId(String modalId) {
        this.modalId = modalId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}