package com.mouse.common.api;

import com.alibaba.fastjson.JSONObject;
import com.mouse.common.http.HttpResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/1
 */
@Controller
@RequestMapping("")
public class TestController {

    private static final Logger log  = LoggerFactory.getLogger(TestController.class);

    static {
        log.info("@@@@@@@@@@@@@@@@@@@@ = {}.", TestController.class);
    }

    @RequestMapping(value = "/{v1}/{v2}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject test(@PathVariable String v1, @PathVariable String v2) {
        return HttpResponseBuilder.success("Hello, world");
    }

}
