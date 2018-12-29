package com.taotao.controller;

import com.taotao.common.bean.PictureResult;
import com.taotao.common.utils.JsonUtil;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;


    @RequestMapping("/rest/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile) {
        PictureResult result = pictureService.uploadPic(uploadFile);
        //需要把java对象手工转化为json数据
        String json = JsonUtil.objectToJson(result);
        return json;
    }
}
