package com.bxp.web.springweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bbp(xiuping bao) on 2018/5/27.
 * @Date: 2018/5/27 19:11
 */
@RestController
public class TrainController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrainController.class);

//    @RequestMapping("/buy/check")
    public String check(@RequestBody String obj){
        LOGGER.info("jerry fat:"+obj.toString());
        return "";
    }
}
