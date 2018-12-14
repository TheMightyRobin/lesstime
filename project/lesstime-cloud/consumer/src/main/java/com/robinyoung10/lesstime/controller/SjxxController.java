package com.robinyoung10.lesstime.controller;

import com.robinyoung10.lesstime.model.Sjxx;
import com.robinyoung10.lesstime.service.LesstimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-12-12
 */
@RestController
public class SjxxController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LesstimeService lesstimeService;

    /**
     * 商家注册
     * @param sjxx
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestBody Sjxx sjxx) {
        logger.info("===consummer===>/register===>sjxx = {}", sjxx);
        return lesstimeService.register(sjxx);
    }

    /**
     * 商家登录
     * @param sjxx
     * @return
     */
    @RequestMapping("/login")
    public Sjxx login(@RequestBody Sjxx sjxx) {
        logger.info("===consummer===>/login===>sjxx = {}", sjxx);
        return lesstimeService.login(sjxx);
    }

    /**
     * 更改信息
     * @param sjxx
     * @return
     */
    @RequestMapping("/setting")
    public Sjxx setting(@RequestBody Sjxx sjxx) {
        logger.info("===consummer===>/setting===>sjxx = {}", sjxx);
        return lesstimeService.setting(sjxx);
    }
}
