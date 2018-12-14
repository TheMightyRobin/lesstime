package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.service.ICpxxService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
    public List<Cpxx> foodCatagoryList(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/list===>cpxx = {}", cpxx);
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", cpxx.getSjbh()).eq("lx", 1);
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        return cpxxList;
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
        String no = "cp" + cpxx.getSjbh() + "1" + num;

        //验证是否存在编号
        while(true) {
            queryWrapper.eq("cpbh", no);
            List<Cpxx> cpxxList = cpxxService.list(queryWrapper);
            if(cpxxList.size() > 0) {
                num += 1;
                no = "cp" + cpxx.getSjbh() + "1" + num;
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
    public String foodCatagoryUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/update===>cpxx = {}", cpxx);
        boolean flag = cpxxService.updateById(cpxx);
        if(flag) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    /**
     * 菜品类别删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/delete")
    public String foodCatagoryDelete(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/catagory/delete===>cpxx = {}", cpxx);
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("ls", cpxx.getCpbh());
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        if(cpxxList.size() > 0) {
            return "菜品类别有隶属菜品，无法删除";
        } else {
            boolean flag = cpxxService.removeById(cpxx);
            if(flag) {
                return "删除成功";
            } else {
                return "删除失败";
            }
        }
    }

    /**
     * 菜品列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/list")
    public List<Cpxx> foodSubfoodList(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/list===>cpxx = {}", cpxx);
        QueryWrapper<Cpxx> wrapper = new QueryWrapper<>();
        wrapper.eq("sjbh", cpxx.getSjbh()).eq("ls", cpxx.getLs());
        List<Cpxx> cpxxList = cpxxService.list(wrapper);
        return cpxxList;
    }

    /**
     * 菜品增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/add")
    public Cpxx foodSubfoodAdd(Cpxx cpxx, @RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        logger.info("===service===>/food/subfood/add===>cpxx = {}", cpxx);
        logger.info("===service===>/food/subfood/add===>file = {}", file);
        //查询拥有多少个菜品
        QueryWrapper<Cpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sjbh", cpxx.getSjbh());
        queryWrapper.eq("lx", 2);
        int num = cpxxService.count(queryWrapper) + 1;
        //生成菜品编号
        String no = "cp" + cpxx.getSjbh() + "2" + num;

        //验证是否存在编号
        while(true) {
            queryWrapper.eq("cpbh", no);
            List<Cpxx> cpxxList = cpxxService.list(queryWrapper);
            if(cpxxList.size() > 0) {
                num += 1;
                no = "cp" + cpxx.getSjbh() + "2" + num;
            } else {
                break;
            }
        }

        //隶属菜品名称转菜品编号
        QueryWrapper<Cpxx> catagoryWrapper = new QueryWrapper<>();
        catagoryWrapper.eq("sjbh", cpxx.getSjbh()).eq("lx", 1).eq("mc", cpxx.getLs());
        Cpxx catagoryCpxx = cpxxService.getOne(catagoryWrapper);
        cpxx.setLs(catagoryCpxx.getCpbh());

        if(file != null) {
            //写入文件的路径,获取到的是部署到webapps的项目根目录+/images/
            String path = request.getServletContext().getRealPath("");
            int lastIndex = path.lastIndexOf("/", path.length()-2);
            path = path.substring(0, lastIndex);
            path += "/ROOT/images/" + cpxx.getSjbh() + "/";
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
            cpxx.setTp("http://114.115.168.26:8080/images/" + cpxx.getSjbh() + "/" + filename);
            logger.info("===service===>/food/subfood/add===>cpxx.getTp = {}", cpxx.getTp());
        }

        cpxx.setCpbh(no);
        cpxx.setLx(2);
        boolean flag = cpxxService.save(cpxx);
        if(flag) {
            return cpxx;
        } else {
            return new Cpxx();
        }
    }

    /**
     * 菜品更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/update")
    public String foodSubfoodUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/update===>cpxx = {}", cpxx);
        boolean flag = cpxxService.updateById(cpxx);
        if(flag) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    /**
     * 菜品删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/delete")
    public String foodSubfoodDelete(@RequestBody Cpxx cpxx) {
        logger.info("===service===>/food/subfood/delete===>cpxx = {}", cpxx);
        boolean flag = cpxxService.removeById(cpxx);
        if(flag) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}

