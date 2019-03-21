package com.robinyoung10.lesstime.controller;

import com.robinyoung10.lesstime.model.Dd;
import com.robinyoung10.lesstime.service.LesstimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-12-12
 */
@RestController
@RequestMapping("/handle/order")
public class DdController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LesstimeService lesstimeService;

    @RequestMapping("/add")
    public Map orderAdd(Dd dd, @RequestParam("cpbhList[]") List<String> cpbhList) {
        logger.info("===consummer===>/order/add===>dd = {}", dd);
        logger.info("===consummer===>/order/add===>cpbhList = {}", cpbhList);
        return lesstimeService.orderAdd(dd, cpbhList);
    }

    @RequestMapping("/user/list")
    public Map orderUserList(@RequestBody Dd dd) {
        logger.info("===consummer===>/order/user/list===>dd = {}", dd);
        return lesstimeService.orderUserList(dd);
    }

    @RequestMapping("/company/index")
    public Map orderCompanyIndex(@RequestBody Dd dd) {
        logger.info("===consummer===>/order/company/index===>dd = {}", dd);
        return lesstimeService.orderCompanyIndex(dd);
    }
}
