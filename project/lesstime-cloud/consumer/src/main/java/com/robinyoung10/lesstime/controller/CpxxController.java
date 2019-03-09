package com.robinyoung10.lesstime.controller;

import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.service.LesstimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/handle/food")
public class CpxxController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LesstimeService lesstimeService;

    /**
     * 菜品类别列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/list")
    public Map foodCatagoryList(Cpxx cpxx, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("===consummer===>/food/catagory/list===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryList(cpxx, page, limit);
    }

    /**
     * 菜品类别增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/add")
    public Cpxx foodCatagoryAdd(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/add===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryAdd(cpxx);
    }

    /**
     * 菜品类别更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/update")
    public boolean foodCatagoryUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/update===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryUpdate(cpxx);
    }

    /**
     * 菜品类别删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/delete")
    public Map foodCatagoryDelete(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/delete===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryDelete(cpxx);
    }

    /**
     * 菜品列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/list")
    public Map foodSubfoodList(Cpxx cpxx, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("===consummer===>/food//subfood/list===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodList(cpxx, page, limit);
    }

    /**
     * 菜品增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/add")
    public boolean foodSubfoodAdd(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/subfood/add===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodAdd(cpxx);
    }

    /**
     * 菜品图片上传
     * @param file
     * @param sjbh
     * @return
     */
    @RequestMapping("/subfood/file")
    public Map foodSubfoodFile(@RequestParam("file") MultipartFile file, @RequestParam("sjbh") String sjbh) {
        logger.info("===consummer===>/food/subfood/file===>file = {}", file);
        logger.info("===consummer===>/food/subfood/file===>sjbh = {}", sjbh);
        String tp = lesstimeService.foodSubfoodAddFile(file, sjbh);
        Map map = new HashMap();
        map.put("tp", tp);
        return map;
    }

    /**
     * 菜品更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/update")
    public boolean foodSubfoodUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/subfood/update===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodUpdate(cpxx);
    }

    /**
     * 菜品删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/delete")
    public boolean foodSubfoodDelete(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/subfood/delete===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodDelete(cpxx);
    }
}
