package com.murongyehua.mrwb.controller.base;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liul
 * @version 1.0 2020/6/16
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("/test.pub")
    public String test() {
        return "test success!";
    }
}
