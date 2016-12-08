package com.spring.jdbc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller和Component实现的结果一样，只是Component会让HomeController无法确定是什么组件类型而已
 * Created by zhaoxl on 2016/11/25.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
