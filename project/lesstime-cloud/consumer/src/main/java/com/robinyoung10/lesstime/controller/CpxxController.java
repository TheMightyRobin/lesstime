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

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/food")
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
    public List<Cpxx> foodCatagoryList(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/list===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryList(cpxx);
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
    public String foodCatagoryUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/update===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryUpdate(cpxx);
    }

    /**
     * 菜品类别删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/catagory/delete")
    public String foodCatagoryDelete(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/catagory/delete===>czxx = {}", cpxx);
        return lesstimeService.foodCatagoryDelete(cpxx);
    }

    /**
     * 菜品列表
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/list")
    public List<Cpxx> foodSubfoodList(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food//subfood/list===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodList(cpxx);
    }

    /**
     * 菜品增加
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/add")
    public Cpxx foodSubfoodAdd(Cpxx cpxx, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        logger.info("===consummer===>/food/subfood/add===>czxx = {}", cpxx);
        logger.info("===consummer===>/food/subfood/add===>file = {}", file);
        return lesstimeService.foodSubfoodAdd(cpxx, file, request);
    }

    /**
     * 菜品更改
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/update")
    public String foodSubfoodUpdate(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/subfood/update===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodUpdate(cpxx);
    }

    /**
     * 菜品删除
     * @param cpxx
     * @return
     */
    @RequestMapping("/subfood/delete")
    public String foodSubfoodDelete(@RequestBody Cpxx cpxx) {
        logger.info("===consummer===>/food/subfood/delete===>czxx = {}", cpxx);
        return lesstimeService.foodSubfoodDelete(cpxx);
    }
}
