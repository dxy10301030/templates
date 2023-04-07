package com.wisoft.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author
 * @createTime 2022/3/9 12:44
 * @description
 **/
@Controller
public class TestController {

    @RequestMapping("/page")
    public String gotoInfo(){
        return "1233";
    }
}
