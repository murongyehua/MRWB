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
public class UserListResp {

    private String id;

    private String username;

    private String password;

    private String nickname;

    private String userType;

    private String userState;

    private Date createTime;

    private String createUser;

    private Date lastLoginTime;

    private String lastLoginTimeText;

    private Integer version;

    private String userTypeText;

    private String userStateText;

    private int index;

}
