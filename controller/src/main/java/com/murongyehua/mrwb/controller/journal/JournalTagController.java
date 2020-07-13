package com.murongyehua.mrwb.controller.journal;

import com.murongyehua.mrwb.api.req.journal.JournalTagReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.journal.service.JournalTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/30
 */
@RestController
@RequestMapping("/journal/tag")
public class JournalTagController {

    @Autowired
    private JournalTagService journalTagService;

    @PostMapping("/add.do")
    public ResultContext addTag(String name) {
        return journalTagService.addTag(name);
    }

    @PostMapping("/query.do")
    public PageView queryList() {
        return journalTagService.queryTag();
    }

    @PostMapping("/edit.do")
    public ResultContext editTag(JournalTagReq req){
        return journalTagService.editTag(req);
    }

}
