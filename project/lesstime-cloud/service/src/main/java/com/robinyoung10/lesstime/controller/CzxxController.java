package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.robinyoung10.lesstime.model.Czxx;
import com.robinyoung10.lesstime.service.ICzxxService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
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
        //查询商家餐桌数+1
        QueryWrapper<Czxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", czxx.getSjbh());
        int num = czxxService.count(wrapper) + 1;
        //拼接餐桌编号
        String czbh = "cz-" + czxx.getSjbh() + "-" + num;
        //查看数据库是否有相同编号
        while(true) {
            QueryWrapper<Czxx> existQueryWrapper = new QueryWrapper<>();
            existQueryWrapper.eq("czbh", czbh);
            int count = czxxService.count(existQueryWrapper);
            if(count > 0) {
                num++;
                czbh = "cz-" + czxx.getSjbh() + "-" + num;
            } else {
                break;
            }
        }
        //拼接餐桌二维码链接
        //String url = "http://localhost:9000/user/welcome";
        String url = "http://114.115.168.26:9000/user/welcome";
        //插入数据库
        czxx.setCzbh(czbh);
        czxx.setCzewm(url + "?czbh=" + czbh + "&sjbh=" + czxx.getSjbh());
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
    public Map listTable(@RequestBody Czxx czxx, @RequestParam int page, @RequestParam int limit) {
        logger.info("===service===>/table/list===>czxx = {}", czxx);
        logger.info("===service===>/table/list===>page = {}", page);
        logger.info("===service===>/table/list===>limit = {}", limit);
        QueryWrapper<Czxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", czxx.getSjbh());
        List<Czxx> czxxList = czxxService.list(wrapper);
        //查询到的总数，返回数据要用到
        int count = czxxList.size();
        //list截取分页的索引
        int fromIndex = (page-1)*limit;
        int toIndex = page * limit;
        //索引最大值不能超过list总个数，否则会出现越界
        if(toIndex > count) {
            toIndex = count;
        }
        //截取分页数据
        czxxList = czxxList.subList(fromIndex, toIndex);

        //使用map来返回数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", czxxList);
        return map;
    }

    /**
     * 删除餐桌
     * @param czxx
     * @return
     */
    @RequestMapping("/table/delete")
    public boolean deleteTable(@RequestBody Czxx czxx) {
        logger.info("===service===>/table/delete===>czxx = {}", czxx);
        UpdateWrapper<Czxx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sjbh", czxx.getSjbh()).eq("czbh", czxx.getCzbh());
        boolean flag = czxxService.remove(updateWrapper);
        return flag;
    }

    @RequestMapping("/table/seat")
    public boolean tableSeat(@RequestBody Czxx czxx) {
        logger.info("===service===>/table/seat===>czxx = {}", czxx);
        QueryWrapper<Czxx> czxxQueryWrapper = new QueryWrapper<>();
        czxxQueryWrapper.eq("czbh", czxx.getCzbh());
        Czxx czxxEntity = czxxService.getOne(czxxQueryWrapper);
        boolean flag = false;
        if(czxxEntity.getCzzt() == 0) {
            czxx.setCzzt(1);
            czxxService.updateById(czxx);
            flag = true;
            return flag;
        } else {
            return flag;
        }
    }

}

