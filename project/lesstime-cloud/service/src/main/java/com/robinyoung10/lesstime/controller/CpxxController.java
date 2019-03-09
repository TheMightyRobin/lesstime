package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.service.ICpxxService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-12-12
 */
@RestController
@RequestMapping("/food")
public class CpxxController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICpxxService cpxxService;

    /**
     * 菜品类别列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/list")
    public Map foodCatagoryList(@RequestBody Cpxx cpxx, @RequestParam int page, @RequestParam int limit) {
        logger.info("===service===>/food/catagory/list===>cpxx = {}", cpxx);
        //根据sjbh和lx为1查询菜品信息列表
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", cpxx.getSjbh()).eq("lx", 1);
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        //查询到的总数，返回数据要用到
        int count = cpxxList.size();
        //list截取分页的索引
        int fromIndex = (page-1)*limit;
        int toIndex = page * limit;
        //索引最大值不能超过list总个数，否则会出现越界
        if(toIndex > count) {
            toIndex = count;
        }
        //截取分页数据
        cpxxList = cpxxList.subList(fromIndex, toIndex);

        //使用map来返回数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", cpxxList);
        return map;
    }

    /**
     * 菜品类别增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/add")
    public Cpxx foodCatagoryAdd(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/add===>cpxx = {}", cpxx);
        //查询拥有多少个菜品类别
        QueryWrapper<Cpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sjbh", cpxx.getSjbh());
        queryWrapper.eq("lx", 1);
        int num = cpxxService.count(queryWrapper) + 1;
        //生成菜品编号
        String no = "cp-" + cpxx.getSjbh() + "-1-" + num;

        //验证是否存在编号
        while(true) {
            QueryWrapper<Cpxx> existQueryWrapper = new QueryWrapper<>();
            existQueryWrapper.eq("cpbh", no);
            List<Cpxx> cpxxList = cpxxService.list(existQueryWrapper);
            if(cpxxList.size() > 0) {
                num += 1;
                no = "cp-" + cpxx.getSjbh() + "-1-" + num;
            } else {
                break;
            }
        }

        cpxx.setCpbh(no);
        cpxx.setLx(1);
        boolean flag = cpxxService.save(cpxx);
        if(flag) {
            return cpxx;
        } else {
            return new Cpxx();
        }
    }

    /**
     * 菜品类别更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/update")
    public boolean foodCatagoryUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/update===>cpxx = {}", cpxx);
        boolean flag = cpxxService.updateById(cpxx);
        return flag;
    }

    /**
     * 菜品类别删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/delete")
    public Map foodCatagoryDelete(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/delete===>cpxx = {}", cpxx);
        //查询菜品类别的隶属菜品列表
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("ls", cpxx.getCpbh());
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        //用于返回数据的map
        Map map = new HashMap();
        if(cpxxList.size() > 0) {
            map.put("message", "菜品类别有隶属菜品，无法删除");
            return map;
        } else {
            boolean flag = cpxxService.removeById(cpxx);
            if(flag) {
                map.put("message", "删除成功");
                return map;
            } else {
                map.put("message", "删除失败");
                return map;
            }
        }
    }

    /**
     * 菜品列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/list")
    public Map foodSubfoodList(@RequestBody Cpxx cpxx, @RequestParam int page, @RequestParam int limit) {
        logger.info("===service===>/food/subfood/list===>cpxx = {}", cpxx);
        //查询菜品列表
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", cpxx.getSjbh()).eq("lx", 2);
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        //把菜品隶属从编号转为名称
        for(Cpxx item : cpxxList) {
            QueryWrapper<Cpxx> catagoryWrapper = new QueryWrapper<>();
            catagoryWrapper.eq("cpbh", item.getLs());
            Cpxx catagory = cpxxService.getOne(catagoryWrapper);
            item.setLs(catagory.getMc());
        }
        //查询到的总数，返回数据要用到
        int count = cpxxList.size();
        //list截取分页的索引
        int fromIndex = (page-1)*limit;
        int toIndex = page * limit;
        //索引最大值不能超过list总个数，否则会出现越界
        if(toIndex > count) {
            toIndex = count;
        }
        //截取分页数据
        cpxxList = cpxxList.subList(fromIndex, toIndex);

        //使用map来返回数据
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", count);
        map.put("data", cpxxList);
        return map;
    }

    /**
     * 菜品增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/add")
    public boolean foodSubfoodAdd(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/add===>cpxx = {}", cpxx);
        //查询拥有多少个菜品
        QueryWrapper<Cpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sjbh", cpxx.getSjbh());
        queryWrapper.eq("lx", 2);
        int num = cpxxService.count(queryWrapper) + 1;
        //生成菜品编号
        String no = "cp-" + cpxx.getSjbh() + "-2-" + num;

        //验证是否存在编号
        while(true) {
            QueryWrapper<Cpxx> existQueryWrapper = new QueryWrapper<>();
            existQueryWrapper.eq("cpbh", no);
            List<Cpxx> cpxxList = cpxxService.list(existQueryWrapper);
            if(cpxxList.size() > 0) {
                num += 1;
                no = "cp" + cpxx.getSjbh() + "2" + num;
            } else {
                break;
            }
        }

        cpxx.setCpbh(no);
        cpxx.setLx(2);
        boolean flag = cpxxService.save(cpxx);
        return flag;
    }

    /**
     * 菜品图片文件写入
     * @param file
     * @param sjbh
     * @return
     */
    @RequestMapping("/subfood/add/file")
    public String foodSubfoodAddFile(@RequestParam("file") MultipartFile file, @RequestParam("sjbh") String sjbh) throws Exception {
//        String path = "D:\\graduation-project\\workspace\\lesstime-cloud";
//        path += "\\ROOT\\images\\" + sjbh + "\\";
        String path = "/home/tomcat/apache-tomcat-8.5.34/webapps";
        path += "/ROOT/images/" + sjbh + "/";
        //获取文件名
        String filename = file.getOriginalFilename();
        //获取时间字符
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newNo = df.format(new Date());
        //将文件名设置为时间+文件名
        filename = newNo + filename;
        //新建文件对象
        File filepath = new File(path, filename);
        //如果路径不存在,就创建一个
        if(!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdir();
        }
        file.transferTo(new File(path + filename));
        logger.info(path + filename);
        return "http://114.115.168.26:8080/images/" + sjbh + "/" + filename;
    }

    /**
     * 菜品更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/update")
    public boolean foodSubfoodUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/update===>cpxx = {}", cpxx);
        boolean flag = cpxxService.updateById(cpxx);
        return flag;
    }

    /**
     * 菜品删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/delete")
    public boolean foodSubfoodDelete(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/delete===>cpxx = {}", cpxx);
        boolean flag = cpxxService.removeById(cpxx);
        return flag;
    }
}

