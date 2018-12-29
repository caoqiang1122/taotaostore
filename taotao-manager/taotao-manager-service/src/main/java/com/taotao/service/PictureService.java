package com.taotao.service;

import com.taotao.common.bean.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    PictureResult uploadPic(MultipartFile multipartFile);
}
