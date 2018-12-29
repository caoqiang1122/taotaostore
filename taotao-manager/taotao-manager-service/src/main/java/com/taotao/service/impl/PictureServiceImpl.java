package com.taotao.service.impl;

import com.taotao.common.bean.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String imageBaseUrl;
    @Override
    public PictureResult uploadPic(MultipartFile multipartFile) {
        PictureResult pictureResult = new PictureResult();
        //判断图片是否为空
        if(multipartFile.isEmpty()){
            pictureResult.setError(1);
            pictureResult.setMessage("图片为空");
            return  pictureResult;
        }
        //上传到图片服务器
        try {
            //获取图片的扩展名
            String orginalFileName = multipartFile.getOriginalFilename();
            //截取扩展名不需要"."
            String extName = orginalFileName.substring(orginalFileName.lastIndexOf(".")+1);
            //创建图片上传工具类对象
            FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
            String url = client.uploadFile(multipartFile.getBytes(), extName);
            url = imageBaseUrl+url;
            //将url响应给客户端
            pictureResult.setError(0);
            pictureResult.setUrl(url);
        } catch (Exception e){
            e.printStackTrace();
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败");
        }
        return pictureResult;
    }
}
