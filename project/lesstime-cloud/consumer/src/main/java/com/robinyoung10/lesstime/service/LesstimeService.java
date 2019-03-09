package com.robinyoung10.lesstime.service;

import com.robinyoung10.lesstime.config.MultipartConfig;
import com.robinyoung10.lesstime.model.Cpxx;
import com.robinyoung10.lesstime.model.Czxx;
import com.robinyoung10.lesstime.model.Sjxx;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@FeignClient(name = "lesstime-service", configuration = MultipartConfig.class)
public interface LesstimeService {

    @RequestMapping("/register")
    String register(Sjxx sjxx);

    @RequestMapping("/login")
    Sjxx login(Sjxx sjxx);

    @RequestMapping("/setting")
    boolean setting(Sjxx sjxx);

    @RequestMapping("/company/info")
    Sjxx info(Sjxx sjxx);

    @RequestMapping("/table/add")
    Czxx addTable(Czxx czxx);

    @RequestMapping("/table/list")
    Map listTable(Czxx czxx, @RequestParam("page") int page, @RequestParam("limit") int limit);

    @RequestMapping("/table/delete")
    boolean deleteTable(Czxx czxx);

    @RequestMapping("/table/seat")
    boolean tableSeat(Czxx czxx);

    @RequestMapping("/food/catagory/list")
    Map foodCatagoryList(Cpxx cpxx, @RequestParam("page") int page, @RequestParam("limit") int limit);

    @RequestMapping("/food/catagory/add")
    Cpxx foodCatagoryAdd(Cpxx cpxx);

    @RequestMapping("/food/catagory/update")
    boolean foodCatagoryUpdate(Cpxx cpxx);

    @RequestMapping("/food/catagory/delete")
    Map foodCatagoryDelete(Cpxx cpxx);

    @RequestMapping("/food/subfood/list")
    Map foodSubfoodList(Cpxx cpxx, @RequestParam("page") int page, @RequestParam("limit") int limit);

    @RequestMapping("/food/subfood/add")
    boolean foodSubfoodAdd(Cpxx cpxx);

    @RequestMapping(value = "/food/subfood/add/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String foodSubfoodAddFile(@RequestPart("file") MultipartFile file, @RequestParam("sjbh") String sjbh);

    @RequestMapping("/food/subfood/update")
    boolean foodSubfoodUpdate(Cpxx cpxx);

    @RequestMapping("/food/subfood/delete")
    boolean foodSubfoodDelete(Cpxx cpxx);
}
