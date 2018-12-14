package com.robinyoung10.lesstime.service;

import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.model.Czxx;
import com.robinyoung10.lesstime.model.Sjxx;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@FeignClient("lesstime-service")
public interface LesstimeService {

    @RequestMapping("/register")
    String register(Sjxx sjxx);

    @RequestMapping("/login")
    Sjxx login(Sjxx sjxx);

    @RequestMapping("/setting")
    Sjxx setting(Sjxx sjxx);

    @RequestMapping("/table/add")
    Czxx addTable(Czxx czxx);

    @RequestMapping("/table/list")
    List<Czxx> listTable(Czxx czxx);

    @RequestMapping("/table/delete")
    String deleteTable(Czxx czxx);

    @RequestMapping("/food/catagory/list")
    List<Cpxx> foodCatagoryList(Cpxx cpxx);

    @RequestMapping("/food/catagory/add")
    Cpxx foodCatagoryAdd(Cpxx cpxx);

    @RequestMapping("/food/catagory/update")
    String foodCatagoryUpdate(Cpxx cpxx);

    @RequestMapping("/food/catagory/delete")
    String foodCatagoryDelete(Cpxx cpxx);

    @RequestMapping("/food/subfood/list")
    List<Cpxx> foodSubfoodList(Cpxx cpxx);

    @RequestMapping("/food/subfood/add")
    Cpxx foodSubfoodAdd(Cpxx cpxx, @RequestParam("file") MultipartFile file, @RequestParam("request") HttpServletRequest request);

    @RequestMapping("/food/subfood/update")
    String foodSubfoodUpdate(Cpxx cpxx);

    @RequestMapping("/food/subfood/delete")
    String foodSubfoodDelete(Cpxx cpxx);
}
