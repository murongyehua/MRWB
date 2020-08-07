package com.murongyehua.mrwb.api.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author limq
 * @version 1.0 2020/8/3
 */
@Getter
@Setter
public class MenuAddReq {
    private String title;

    private String icon;

    private String routeIndex;

}