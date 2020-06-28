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
public class ModalParam extends BaseQueryParams {

    private static final long serialVersionUID = 7538763997357403937L;

    private String name;

    private String status;

}
