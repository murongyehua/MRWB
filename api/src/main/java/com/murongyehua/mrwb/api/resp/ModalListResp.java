package com.murongyehua.mrwb.api.resp;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@Getter
@Setter
public class ModalListResp {

    private String id;

    private String name;

    private String modalState;

    private String createUser;

    private Date createTime;

    private Integer version;

    private int index;

    private String modalStateText;

    private String createTimeText;
}
