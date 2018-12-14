package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.robinyoung10.lesstime.model.Sjxx;
import com.robinyoung10.lesstime.service.ISjxxService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ISjxxService sjxxService;

    /**
     * 商家注册
     * @param sjxx
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/register===>sjxx = {}", sjxx);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String newNo = df.format(new Date());
        QueryWrapper<Sjxx> wrapper = new QueryWrapper<>();
        wrapper.likeRight("sjbh", newNo);
        int num = sjxxService.count(wrapper) + 1;
        switch(num / 10) {
            case 0:
                newNo = newNo + "00" + num;
                break;
            case 1:
                newNo = newNo + "0" + num;
                break;
            case 2:
                newNo = newNo + "" + num;
                break;
        }
        sjxx.setSjbh(newNo);
        boolean message = sjxxService.save(sjxx);
        if (message) {
            return "注册成功,请登录";
        } else {
            return "注册失败,请重试";
        }
    }

    /**
     * 商家登录
     * @param sjxx
     * @return
     */
    @RequestMapping("/login")
    public Sjxx login(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/login===>sjxx = {}", sjxx);
        QueryWrapper<Sjxx> wrapper = new QueryWrapper<>();
        wrapper.eq("zh", sjxx.getZh()).eq("mm", sjxx.getMm());
        Sjxx sjxxEntity = sjxxService.getOne(wrapper);
        return sjxxEntity;
    }

    /**
     * 更改信息
     * @param sjxx
     * @return
     */
    @RequestMapping("/setting")
    public Sjxx setting(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/setting===>sjxx = {}", sjxx);
        QueryWrapper<Sjxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", sjxx.getSjbh());
        Sjxx sjxxEntity = sjxxService.getOne(wrapper);
        sjxx.setZh(sjxxEntity.getZh());
        sjxx.setMm(sjxxEntity.getMm());
        boolean message = sjxxService.updateById(sjxx);
        if(message) {
            return sjxx;
        } else {
            return new Sjxx();
        }
    }
}

