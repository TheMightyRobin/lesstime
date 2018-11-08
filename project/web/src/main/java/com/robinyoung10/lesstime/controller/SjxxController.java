package com.robinyoung10.lesstime.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robinyoung10.lesstime.model.Sjxx;
import com.robinyoung10.lesstime.service.ISjxxService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  商家信息前端控制器
 * </p>
 *
 * @author RobinYoung10
 * @since 2018-11-08
 */
@Controller
public class SjxxController {
	@Autowired
	private ISjxxService sjxxService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String Test(@RequestParam("zh") String zh, @RequestParam("mm") String mm) {
		Sjxx sjxx = new Sjxx();
		sjxx.setSjbh("test");
		sjxx.setZh(zh);
		sjxx.setMm(mm);
		boolean message = sjxxService.save(sjxx);
		if(message) {
			return "success";
		} else {
			return "fail";
		}
	}
}

