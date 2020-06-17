package com.murongyehua.mrwb.api.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@Setter
@Getter
public class UserEditReq {
    private String id;

    private String oldPassword;

    private String password;

    private String rePassword;

    private String nickname;
}
