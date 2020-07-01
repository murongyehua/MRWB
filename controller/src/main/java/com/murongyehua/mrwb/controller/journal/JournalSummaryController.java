package com.murongyehua.mrwb.controller.journal;

import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryAddReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.journal.service.JournalSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@RestController
@RequestMapping("/journal/summary")
public class JournalSummaryController {

    @Autowired
    private JournalSummaryService summaryService;

    @PostMapping("/add.do")
    public ResultContext add(@RequestBody JournalSummaryAddReq addReq) {
        return summaryService.addSummary(addReq);
    }

    @PostMapping("/query.do")
    public PageView query(JournalSummaryParam param) {
        return summaryService.queryList(param);
    }

}
