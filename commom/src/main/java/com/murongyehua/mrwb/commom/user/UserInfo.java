package com.murongyehua.mrwb.commom.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liul
 * @version 1.0 2020/6/16
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4953613226285918884L;

    private String username;
    private String nickname;
    private String userId;
    private String userType;

}
