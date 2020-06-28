package com.murongyehua.mrwb.api.param;

import com.murongyehua.mrwb.commom.BaseQueryParams;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@Getter
@Setter
public class MenuParam extends BaseQueryParams {
    private static final long serialVersionUID = 6271958858829663722L;

    private String title;
}
