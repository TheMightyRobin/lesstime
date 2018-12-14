package com.robinyoung10.lesstime.controller;

import com.robinyoung10.lesstime.model.Czxx;
import com.robinyoung10.lesstime.service.LesstimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CzxxController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LesstimeService lesstimeService;

    /**
     * 增加餐桌
     * @param czxx
     * @return
     */
    @RequestMapping("/table/add")
    public Czxx addTable(@RequestBody Czxx czxx) {
        logger.info("===consummer===>/table/add===>czxx = {}", czxx);
        return lesstimeService.addTable(czxx);
    }

    /**
     * 餐桌列表
     * @param czxx
     * @return
     */
    @RequestMapping("/table/list")
    public List<Czxx> listTable(@RequestBody Czxx czxx) {
        logger.info("===consummer===>/table/list===>czxx = {}", czxx);
        return lesstimeService.listTable(czxx);
    }

    /**
     * 删除餐桌
     * @param czxx
     * @return
     */
    @RequestMapping("/table/delete")
    public String deleteTable(@RequestBody Czxx czxx) {
        logger.info("===consummer===>/table/delete===>czxx = {}", czxx);
        return lesstimeService.deleteTable(czxx);
    }

}
