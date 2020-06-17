package com.murongyehua.mrwb.api.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/17
 */
@Getter
@Setter
public class UserAddReq {

    private String username;

    private String password;

    private String nickname;

    private String userType;

}
