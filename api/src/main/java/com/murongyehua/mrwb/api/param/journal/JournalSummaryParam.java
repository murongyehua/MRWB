package com.murongyehua.mrwb.api.param.journal;

import com.murongyehua.mrwb.commom.BaseQueryParams;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Getter
@Setter
public class JournalSummaryParam extends BaseQueryParams {

    private static final long serialVersionUID = 3199067683499505699L;

    private String projectId;

    private String dealDateStart;

    private String dealDateEnd;

    private String dealUser;

    private String tag;

    private String titleLike;

    private String state;

}
