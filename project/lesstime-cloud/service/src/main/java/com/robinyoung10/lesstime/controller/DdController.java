package com.robinyoung10.lesstime.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.model.Dd;
import com.robinyoung10.lesstime.service.ICpxxService;
import com.robinyoung10.lesstime.service.IDdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-12-12
 */
@RestController
@RequestMapping("/order")
public class DdController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDdService ddService;

    @Autowired
    private ICpxxService cpxxService;

    @RequestMapping("/add")
    public boolean orderAdd(@RequestBody Dd dd, @RequestParam List<String> cpbhList) {
        logger.info("===service===>/order/add===>dd = {}", dd);
        logger.info("===service===>/order/add===>cpbhList = {}", cpbhList);
        //获取当前日期时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = df.format(new Date());
        //拼接订单编号
        String ddbh = "dd-" + now + "-" + dd.getCpbh();
        //设置到dd对象
        dd.setDdbh(ddbh);
        //设置订单状态
        dd.setDdzt(1);
        //设置订单时间
        dd.setDdsj(new Date());
        //保存数据到数据库
        boolean flag = ddService.save(dd);
        if(flag) {
            //查询菜品编号列表里面的每一个菜品信息
            for(int i = 0; i < cpbhList.size(); i++) {
                //获取菜品信息
                QueryWrapper<Cpxx> cpxxQueryWrapper = new QueryWrapper<>();
                cpxxQueryWrapper.eq("cpbh", cpbhList.get(i));
                Cpxx cpxx = cpxxService.getOne(cpxxQueryWrapper);
                //设置子订单信息
                Dd ddEntity = new Dd();
                ddEntity.setSjbh(dd.getSjbh());
                ddEntity.setDdbh(dd.getDdbh());
                ddEntity.setCzbh(dd.getCzbh());
                ddEntity.setCpbh(cpxx.getCpbh());
                ddEntity.setCpmc(cpxx.getMc());
                ddEntity.setCpjg(cpxx.getJg());
                String ddzbh = "ddz-" + ddbh + "-" + i;
                ddEntity.setDdzbh(ddzbh);
                //插入数据库
                boolean flag2 = ddService.save(ddEntity);
                return flag2;
            }
        }
        return flag;
    }

}

