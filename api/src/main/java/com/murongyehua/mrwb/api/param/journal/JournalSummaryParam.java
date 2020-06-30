package com.murongyehua.mrwb.api.param.journal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Getter
@Setter
public class JournalSummaryParam {

    private String dealDateStart;

    private String dealDateEnd;

    private String dealUser;

    private String tag;

    private String titleLike;

}
