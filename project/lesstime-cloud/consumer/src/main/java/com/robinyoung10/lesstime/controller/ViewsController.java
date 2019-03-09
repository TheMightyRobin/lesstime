package com.robinyoung10.lesstime.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class ViewsController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/home")
    public String home(Model model) {
        logger.info("====访问页面/home====");
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        logger.info("====访问页面/login====");
        return "company/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        logger.info("====访问页面/register====");
        return "company/register";
    }

    @RequestMapping("/company/index")
    public String companyIndex(Model model) {
        logger.info("====访问页面/company/index====");
        return "company/index";
    }

    @RequestMapping("/company/setting")
    public String companySetting(Model model) {
        logger.info("====访问页面/company/setting====");
        return "company/setting";
    }

    @RequestMapping("/catagory/add")
    public String catagoryAdd(Model model) {
        logger.info("====访问页面/catagory/add====");
        return "catagory/add";
    }

    @RequestMapping("/catagory/list")
    public String catagoryList(Model model) {
        logger.info("====访问页面/catagory/list====");
        return "catagory/list";
    }

    @RequestMapping("/catagory/modify")
    public String catagoryModify(Model model) {
        logger.info("====访问页面/catagory/modify====");
        return "catagory/modify";
    }

    @RequestMapping("/food/add")
    public String foodAdd(Model model) {
        logger.info("====访问页面/food/add====");
        return "food/add";
    }

    @RequestMapping("/food/list")
    public String foodList(Model model) {
        logger.info("====访问页面/food/list====");
        return "food/list";
    }

    @RequestMapping("/food/modify")
    public String foodModify(Model model) {
        logger.info("====访问页面/food/modify====");
        return "food/modify";
    }

    @RequestMapping("/table/list")
    public String tableList(Model model) {
        logger.info("====访问页面/table/list====");
        return "table/list";
    }

    @RequestMapping("/table/info")
    public String tableInfo(Model model) {
        logger.info("====访问页面/table/info====");
        return "table/info";
    }

    @RequestMapping("/user/welcome")
    public String userWelcome(Model model) {
        logger.info("====访问页面/user/welcome====");
        return "user/welcome";
    }

    @RequestMapping("/user/choose")
    public String userChoose(Model model) {
        logger.info("====访问页面/user/choose====");
        return "user/choose";
    }

    @RequestMapping("/user/order")
    public String userOrder(Model model) {
        logger.info("====访问页面/user/order====");
        return "user/order";
    }
}
