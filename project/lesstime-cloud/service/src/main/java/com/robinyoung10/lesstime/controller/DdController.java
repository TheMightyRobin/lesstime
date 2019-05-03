package com.robinyoung10.lesstime.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.model.Dd;
import com.robinyoung10.lesstime.service.ICpxxService;
import com.robinyoung10.lesstime.service.IDdService;
import com.robinyoung10.lesstime.util.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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

    private static Tool tool = new Tool();

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
        logger.info("===service===>/order/company/index===>dd = {}", dd);
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

    /**
     * 商家获取订单列表
     * @param dd
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/company/list")
    public Map orderCompanyList(@RequestBody Dd dd, @RequestParam int page, @RequestParam int limit) {
        logger.info("===service===>/order/company/list===>dd = {}", dd);
        logger.info("===service===>/table/list===>page = {}", page);
        logger.info("===service===>/table/list===>limit = {}", limit);
        QueryWrapper<Dd> ddQueryWrapper = new QueryWrapper<>();
        ddQueryWrapper.eq("sjbh", dd.getSjbh()).eq("ddzt", 0).isNull("ddzbh");
        List<Dd> ddList = ddService.list(ddQueryWrapper);
        int count = ddList.size();
        //list截取分页的索引
        int fromIndex = (page-1)*limit;
        int toIndex = page * limit;
        //索引最大值不能超过list总个数，否则会出现越界
        if(toIndex > count) {
            toIndex = count;
        }
        //截取分页数据
        ddList = ddList.subList(fromIndex, toIndex);

        //使用map来返回数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", ddList);
        return map;
    }

    /**
     * 商家订单列表的菜品详情
     * @param dd
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/company/list/detail")
    public Map orderCompanyListDetail(@RequestBody Dd dd, @RequestParam int page, @RequestParam int limit) {
        logger.info("===service===>/order/company/list===>dd = {}", dd);
        logger.info("===service===>/table/list===>page = {}", page);
        logger.info("===service===>/table/list===>limit = {}", limit);
        QueryWrapper<Dd> ddQueryWrapper = new QueryWrapper<>();
        ddQueryWrapper.eq("ddbh", dd.getDdbh()).eq("ddzt", 0).isNotNull("ddzbh");
        List<Dd> ddList = ddService.list(ddQueryWrapper);
        int count = ddList.size();
        //list截取分页的索引
        int fromIndex = (page-1)*limit;
        int toIndex = page * limit;
        //索引最大值不能超过list总个数，否则会出现越界
        if(toIndex > count) {
            toIndex = count;
        }
        //截取分页数据
        ddList = ddList.subList(fromIndex, toIndex);

        //使用map来返回数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", ddList);
        return map;
    }

    /**
     * 查询七天订单数据
     * @param dd
     * @return
     */
    @RequestMapping("/line")
    public Map orderLine(@RequestBody Dd dd) {
        logger.info("===service===>/line===>dd = {}", dd);
        //map对象用来储存返回数据
        Map map = new HashMap();
        //新建sysjList和counts，用于保存时间和访问数
        List<String> ddList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();
        for(int i = 6; i >= 0; i--) {
            //获取i天前日期对象
            Date date = tool.getDateBefore(new Date(), i);
            //溯源时间转字符串
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String ddString = df.format(date);
            //查询i天前溯源数量
            QueryWrapper<Dd> ddQueryWrapper = new QueryWrapper<>();
            ddQueryWrapper.eq("ddsj", ddString).eq("sjbh", dd.getSjbh());
            int count = ddService.count(ddQueryWrapper);
            //将结果插入list
            ddList.add(ddString);
            countList.add(count);
        }
        map.put("ddList", ddList);
        map.put("countList", countList);
        return map;
    }

    /**
     * 结账
     * @param dd
     * @return
     */
    @RequestMapping("/check")
    public boolean orderCheck(@RequestBody Dd dd) {
        logger.info("===service===>/check===>dd = {}", dd);
        dd.setDdzt(0);
        UpdateWrapper<Dd> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("czbh", dd.getCzbh()).ne("ddzt", 0);
        boolean flag = ddService.update(dd, updateWrapper);
        return flag;
    }
}

