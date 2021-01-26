package com.murongyehua.mrwb.controller.journal;

import cn.hutool.core.io.FileUtil;
import com.murongyehua.mrwb.api.param.journal.JournalSummaryParam;
import com.murongyehua.mrwb.api.req.journal.JournalSummaryReq;
import com.murongyehua.mrwb.commom.PageView;
import com.murongyehua.mrwb.commom.ResultContext;
import com.murongyehua.mrwb.journal.service.JournalSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
    public ResultContext add(@RequestBody JournalSummaryReq addReq) {
        return summaryService.addSummary(addReq, true);
    }

    @PostMapping("/query.do")
    public PageView query(JournalSummaryParam param) {
        return summaryService.queryList(param);
    }

    @PostMapping("/getById.do")
    public ResultContext getById(String summaryId) {
        return summaryService.getById(summaryId);
    }

    @PostMapping("/edit.do")
    public ResultContext edit(@RequestBody JournalSummaryReq editReq) {
        return summaryService.editSummary(editReq);
    }

    @PostMapping("/queryHistory.do")
    public PageView queryHistory(String summaryId) {
        return summaryService.queryHistory(summaryId);
    }

    @PostMapping("/delete.do")
    public ResultContext delete(@RequestBody JournalSummaryReq deleteReq) {
        return summaryService.delete(deleteReq);
    }

    @PostMapping("/export.pub")
    public void export(JournalSummaryParam param, HttpServletResponse response) {
        String filePath = summaryService.export(param);
        try {
            byte[] buffer = FileUtil.readBytes(filePath);
            // 设置response的Header
            response.setContentType("application/x-download");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment");
            //设置HttpOnly属性
            response.addHeader("Set-Cookie", "uid=112; Path=/; HttpOnly");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
            FileUtil.del(filePath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
