package com.murongyehua.mrwb.journal.service;

import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;

import java.util.List;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
public interface JournalSummaryService {

    ResultContext addSummary(JournalSummaryReq addReq, boolean isCheckDup);

    PageView queryList(JournalSummaryParam param);

    ResultContext getById(String summaryId);

    ResultContext editSummary(JournalSummaryReq editReq);

    PageView queryHistory(String summaryId);

    ResultContext delete(JournalSummaryReq deleteReq);

    String export(JournalSummaryParam param);

}
