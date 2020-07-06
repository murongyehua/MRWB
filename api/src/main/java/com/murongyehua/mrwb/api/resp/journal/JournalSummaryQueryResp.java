package com.murongyehua.mrwb.api.resp.journal;

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
public class JournalSummaryQueryResp {

    private String id;

    private String projectId;

    private String dealUser;

    private String dealDate;

    private String state;

    private String attach;

    private Date lastModifyTime;

    private String createUser;

    private Date createTime;

    private String historyForId;

    private String tagId;

    private String title;

    private String createUserText;

    private String dealUserText;

    private String tagText;

    private String createTimeText;

    private String lastModifyTimeText;

    private String lastModifyUserText;

    private List<JournalFieldContentResp> contents;

}
