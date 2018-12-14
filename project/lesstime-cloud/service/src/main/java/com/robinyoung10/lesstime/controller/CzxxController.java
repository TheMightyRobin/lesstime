package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.robinyoung10.lesstime.model.Czxx;
import com.robinyoung10.lesstime.service.ICzxxService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class CzxxController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICzxxService czxxService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 增加餐桌
     * @param czxx
     * @return
     */
    @RequestMapping("/table/add")
    public Czxx addTable(@RequestBody Czxx czxx) {
        logger.info("===service===>/table/add===>czxx = {}", czxx);
        QueryWrapper<Czxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", czxx.getSjbh());
        int num = czxxService.count(wrapper) + 1;
        String url = request.getScheme() +"://" + request.getServerName() + ":" +request.getServerPort() + request.getServletPath();
        czxx.setCzbh(num);
        czxx.setCzewm(url + "?czbh=" + num + "&sjbh=" + czxx.getSjbh());
        czxx.setCzzt(0);
        boolean flag = czxxService.save(czxx);
        if(flag) {
            return czxx;
        } else {
            return new Czxx();
        }
    }

    /**
     * 餐桌列表
     * @param czxx
     * @return
     */
    @RequestMapping("/table/list")
    public List<Czxx> listTable(@RequestBody Czxx czxx) {
        logger.info("===service===>/table/list===>czxx = {}", czxx);
        QueryWrapper<Czxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", czxx.getSjbh());
        List<Czxx> list = czxxService.list(wrapper);
        return list;
    }

    /**
     * 删除餐桌
     * @param czxx
     * @return
     */
    @RequestMapping("/table/delete")
    public String deleteTable(@RequestBody Czxx czxx) {
        logger.info("===service===>/table/delete===>czxx = {}", czxx);
        QueryWrapper<Czxx> entityWrapper = new QueryWrapper<>();
        entityWrapper.eq("sjbh", czxx.getSjbh());
        int num = czxxService.count(entityWrapper);
        UpdateWrapper<Czxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sjbh", czxx.getSjbh()).eq("czbh", num);
        boolean message = czxxService.remove(updateWrapper);
        if(message) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}

