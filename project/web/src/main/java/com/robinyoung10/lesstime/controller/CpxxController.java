package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.service.ICpxxService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-11-08
 */
@Controller
public class CpxxController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICpxxService cpxxService;
	
	/**
	 * 菜品类别列表
	 * @param cpxx
	 * @return
	 */
	@RequestMapping("/food/catagory/list")
	@ResponseBody
	public List<Cpxx> foodCatagoryList(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
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
	@RequestMapping("/food/catagory/add")
	@ResponseBody
	public Cpxx foodCatagoryAdd(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
		//查询拥有多少个菜品类别
		QueryWrapper<Cpxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjbh", cpxx.getSjbh());
		queryWrapper.eq("lx", 1);
		int num = cpxxService.count(queryWrapper) + 1;
		//生成菜品编号
		String no = "cp" + cpxx.getSjbh() + "1" + num;
		
		UpdateWrapper<Cpxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("sjbh", cpxx.getSjbh());
		cpxx.setCpbh(no);
		cpxx.setLx(1);
		boolean flag = cpxxService.update(cpxx, updateWrapper);
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
	@RequestMapping("/food/catagory/update")
	@ResponseBody
	public String foodCatagoryUpdate(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
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
	@RequestMapping("/food/catagory/delete")
	@ResponseBody
	public String foodCatagoryDelete(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
		boolean flag = cpxxService.removeById(cpxx);
		if(flag) {
			return "删除成功";
		} else {
			return "删除失败";
		}
	}
	
	/**
	 * 菜品列表
	 * @param cpxx
	 * @return
	 */
	@RequestMapping("/food/subfood/list")
	@ResponseBody
	public List<Cpxx> foodSubfoodList(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
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
	@RequestMapping("/food/subfood/add")
	@ResponseBody
	public Cpxx foodSubfoodAdd(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
		//查询拥有多少个菜品
		QueryWrapper<Cpxx> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjbh", cpxx.getSjbh());
		queryWrapper.eq("lx", 2);
		int num = cpxxService.count(queryWrapper) + 1;
		//生成菜品编号
		String no = "cp" + cpxx.getSjbh() + "2" + num;
		
		UpdateWrapper<Cpxx> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("sjbh", cpxx.getSjbh());
		cpxx.setCpbh(no);
		cpxx.setLx(2);
		boolean flag = cpxxService.update(cpxx, updateWrapper);
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
	@RequestMapping("/food/subfood/update")
	@ResponseBody
	public String foodSubfoodUpdate(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
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
	@RequestMapping("/food/subfood/delete")
	@ResponseBody
	public String foodSubfoodDelete(@RequestBody Cpxx cpxx) {
		logger.info("cpxx = {}", cpxx);
		boolean flag = cpxxService.removeById(cpxx);
		if(flag) {
			return "删除成功";
		} else {
			return "删除失败";
		}
	}
}

