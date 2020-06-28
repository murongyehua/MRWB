package com.murongyehua.mrwb.controller.journal;

import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.journal.service.JournalFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/28
 */
@RestController
@RequestMapping("/journal/field")
public class JournalFieldController {

    @Autowired
    private JournalFieldService fieldService;

    @PostMapping("/getFields.do")
    public ResultContext getFields() {
        return fieldService.getFieldByProjectId();
    }

}
