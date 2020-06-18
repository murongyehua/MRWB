package com.murongyehua.mrwb.commom;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PageView<T> extends ResultContext<T> implements Serializable {

    private static final long serialVersionUID = -4596656718420140378L;

    /**
     * 列表内容
     */
    private List<T> rows;

    /**
     * 总条数
     */
    private Long total;
}
