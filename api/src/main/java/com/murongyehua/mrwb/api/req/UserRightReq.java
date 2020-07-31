package com.murongyehua.mrwb.api.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/7/31
 */
@Getter
@Setter
public class UserRightReq {

    private String userId;

    private String projectId;

    private String modalId;

}
