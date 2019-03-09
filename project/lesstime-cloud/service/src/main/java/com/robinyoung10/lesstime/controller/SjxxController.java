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
import org.springframework.web.bind.annotation.ResponseBody;
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
    public boolean register(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/register===>sjxx = {}", sjxx);
        //生成商家编号"sj"+日期的组合
        String sjbh = "sj";
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String now = df.format(new Date());
        sjbh = sjbh.concat(now);
        //查询数据库中存在的相似编号的数量（要插入的编号+1）
        QueryWrapper<Sjxx> wrapper = new QueryWrapper<>();
        wrapper.likeRight("sjbh", sjbh);
        int num = sjxxService.count(wrapper) + 1;
        //拼接类似001、011、111的字符串到尾部
        if(num /10 == 0) {
            sjbh = sjbh.concat("00" + num);
        } else if(num / 10 >= 1 && num / 10 < 10) {
            sjbh = sjbh.concat("0" + num);
        } else {
            sjbh = sjbh.concat("" + num);
        }
        //设置编号并插入数据库
        sjxx.setSjbh(sjbh);
        boolean message = sjxxService.save(sjxx);
        return message;
    }

    /**
     * 商家登录
     * @param sjxx
     * @return
     */
    @RequestMapping("/login")
    public Sjxx login(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/login===>sjxx = {}", sjxx);
        //根据账号密码查询商家数据
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
    public boolean setting(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/setting===>sjxx = {}", sjxx);
        QueryWrapper<Sjxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", sjxx.getSjbh());
        Sjxx sjxxEntity = sjxxService.getOne(wrapper);
        sjxx.setZh(sjxxEntity.getZh());
        sjxx.setMm(sjxxEntity.getMm());
        boolean flag = sjxxService.updateById(sjxx);
        return flag;
    }

    /**
     * 用户访问欢迎页获取商家名称电话等信息
     * @param sjxx
     * @return
     */
    @RequestMapping("/company/info")
    public Sjxx info(@RequestBody Sjxx sjxx) {
        logger.info("===service===>/info===>sjxx = {}", sjxx);
        QueryWrapper<Sjxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sjbh", sjxx.getSjbh());
        Sjxx sjxxEntity = sjxxService.getOne(queryWrapper);
        sjxxEntity.setZh("");
        sjxxEntity.setMm("");
        return sjxxEntity;
    }
}

