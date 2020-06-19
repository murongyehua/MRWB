package com.murongyehua.mrwb.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liul
 * @version 1.0 2020/6/19
 */
@Getter
@Setter
public class UserInfoDTO {

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
}
