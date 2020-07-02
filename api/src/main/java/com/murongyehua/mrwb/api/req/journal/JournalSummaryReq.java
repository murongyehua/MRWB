package com.murongyehua.mrwb.api.req.journal;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@Getter
@Setter
public class JournalSummaryReq {

    private String dealUser;

    private String dealDate;

    private String attach;

    private String tagId;

    private String title;

    private List<String> fieldContents;

    private String id;

    private List<String> ids;

    private String state;
}
