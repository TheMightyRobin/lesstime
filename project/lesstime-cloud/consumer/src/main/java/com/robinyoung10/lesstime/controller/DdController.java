package com.robinyoung10.lesstime.controller;

import com.robinyoung10.lesstime.model.Dd;
import com.robinyoung10.lesstime.service.LesstimeService;
import com.robinyoung10.lesstime.service.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/company/list")
    public Map orderCompanyList(Dd dd, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("===consummer===>/order/company/list===>dd = {}", dd);
        return lesstimeService.orderCompanyList(dd, page, limit);
    }

    @RequestMapping("/company/list/detail")
    public Map orderCompanyListDetail(Dd dd, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("===consummer===>/order/company/list/detail===>dd = {}", dd);
        return lesstimeService.orderCompanyListDetail(dd, page, limit);
    }

    @RequestMapping("/line")
    public Map orderLine(@RequestBody Dd dd) {
        logger.info("===consummer===>/order/line===>dd = {}", dd);
        return lesstimeService.orderLine(dd);
    }

    @RequestMapping("/check")
    public boolean orderCheck(@RequestBody Dd dd) {
        logger.info("===consummer===>/order/check===>dd = {}", dd);
        return lesstimeService.orderCheck(dd);
    }

    @RequestMapping("/user/send/{sjbh}")
    public boolean sendToCompany(String message, @PathVariable String sjbh) {
        logger.info("===consummer===>/order/user/send/{}===>message = {}", sjbh, message);
        try {
            WebSocketServer.sendInfo(message, sjbh);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
