package com.murongyehua.mrwb.journal.service;

import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryAddReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
public interface JournalSummaryService {

    ResultContext addSummary(JournalSummaryAddReq addReq);

    PageView queryList(JournalSummaryParam param);

}
