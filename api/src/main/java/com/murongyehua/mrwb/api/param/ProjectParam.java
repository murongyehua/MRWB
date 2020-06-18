package com.murongyehua.mrwb.api.param;

import com.murongyehua.mrwb.commom.BaseQueryParams;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/18
 */
@Getter
@Setter
public class ProjectParam extends BaseQueryParams {

    private static final long serialVersionUID = 8952318271029934817L;

    private String name;

}
