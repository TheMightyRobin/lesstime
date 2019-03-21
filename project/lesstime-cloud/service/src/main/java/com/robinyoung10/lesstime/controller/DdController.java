package com.robinyoung10.lesstime.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import java.util.HashMap;
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
@RequestMapping("/order")
public class DdController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDdService ddService;

    @Autowired
    private ICpxxService cpxxService;

    /**
     * 用户点菜页面点击选好了按钮提交数据后，新增订单
     * @param dd
     * @param cpbhList
     * @return
     */
    @RequestMapping("/add")
    public Map orderAdd(@RequestBody Dd dd, @RequestParam List<String> cpbhList) {
        logger.info("===service===>/order/add===>dd = {}", dd);
        logger.info("===service===>/order/add===>cpbhList = {}", cpbhList);
        //map用于返回数据
        Map map = new HashMap();
        //获取当前日期时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = df.format(new Date());
        //拼接订单编号
        String ddbh = "dd-" + now + "-" + dd.getCzbh();
        //设置到dd对象
        dd.setDdbh(ddbh);
        //设置订单状态
        dd.setDdzt(1);
        //设置订单时间
        dd.setDdsj(new Date());
        //保存数据到数据库
        boolean flag = ddService.save(dd);
        if(flag) {
            //菜品总价
            Double totalPrice = 0.00;
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
                ddEntity.setDdzt(2);
                String ddzbh = "ddz-" + ddbh + "-" + i;
                ddEntity.setDdzbh(ddzbh);
                //插入数据库
                boolean flag2 = ddService.save(ddEntity);
                //菜品总价增加
                totalPrice += cpxx.getJg();
                //如果有一条数据插入失败，则删除所有已经插入的数据，返回false
                if(!flag2) {
                    QueryWrapper<Dd> ddQueryWrapper = new QueryWrapper<>();
                    ddQueryWrapper.eq("ddbh", dd.getDdbh());
                    ddService.remove(ddQueryWrapper);
                    map.put("message", "false");
                    return map;
                }
            }
            //把菜品总价更新到订单
            dd.setCpjg(totalPrice);
            UpdateWrapper<Dd> ddUpdateWrapper = new UpdateWrapper<>();
            ddUpdateWrapper.eq("ddbh", dd.getDdbh()).eq("ddzt", 1);
            ddService.update(dd, ddUpdateWrapper);
        }
        map.put("message", "true");
        map.put("ddbh", dd.getDdbh());
        return map;
    }

    /**
     * 用户获取订单列表
     * @param dd
     * @return
     */
    @RequestMapping("/user/list")
    public Map orderUserList(@RequestBody Dd dd) {
        logger.info("===service===>/order/user/list===>dd = {}", dd);
        //新建map用来返回数据
        Map map = new HashMap();
        //查询总订单
        QueryWrapper<Dd> ddQueryWrapper = new QueryWrapper<>();
        ddQueryWrapper.eq("ddbh", dd.getDdbh()).eq("ddzt", 1);
        Dd ddEntity = ddService.getOne(ddQueryWrapper);
        //将订单总价格放进map
        map.put("totalSpend", ddEntity.getCpjg());
        //查询子订单list
        QueryWrapper<Dd> zddQueryWrapper = new QueryWrapper<>();
        zddQueryWrapper.eq("ddbh", dd.getDdbh()).eq("ddzt", 2);
        List<Dd> zddList = ddService.list(zddQueryWrapper);
        //将菜品总数放进map
        map.put("count", zddList.size());
        //将菜品list放进map
        map.put("cpList", zddList);
        return map;
    }

    @RequestMapping("/company/index")
    public Map orderCompanyIndex(@RequestBody Dd dd) {
        logger.info("===service===>/order/company/list===>dd = {}", dd);
        //新建map用来返回数据
        Map map = new HashMap();
        //查询子订单列表
        QueryWrapper<Dd> zddQueryWrapper = new QueryWrapper<>();
        zddQueryWrapper.eq("czbh", dd.getCzbh()).eq("ddzt", dd.getDdzt());
        List<Dd> zddList = ddService.list(zddQueryWrapper);
        //将菜品list放进map
        map.put("cpList", zddList);
        return map;
    }

}

