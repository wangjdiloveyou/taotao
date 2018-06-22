package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadPic(MultipartFile uploadFile, HttpServletRequest request) {

       try {
           //根据相对路径获取绝对路径，图片上传后位于元数据中 这里取路径要注意
           String realUploadPath = request.getSession().getServletContext().getRealPath("/WEB-INF/")+"/images";
           //如果目录不存在则创建目录
           File uploadFile1=new File(realUploadPath+"/rawImages");
           if(!uploadFile1.exists()){
               uploadFile1.mkdirs();
           }
           //创建输入流
           InputStream inputStream=uploadFile.getInputStream();
           //生成输出地址URL
           String outputPath=realUploadPath+"/rawImages/"+uploadFile.getOriginalFilename();
           //创建输出流
           OutputStream outputStream=new FileOutputStream(outputPath);
           //设置缓冲区
           byte[] buffer=new byte[1024];

           //输入流读入缓冲区，输出流从缓冲区写出
           while((inputStream.read(buffer))>0)
           {
               outputStream.write(buffer);
           }
           outputStream.close();
           Map result = new HashMap();
           result.put("error", 0);
           result.put("url", IMAGE_SERVER_URL+"images/rawImages/"+uploadFile.getOriginalFilename());
           return JsonUtils.objectToJson(result);
       }
       catch (Exception e) {
           e.printStackTrace();
           Map result = new HashMap();
           result.put("error", 1);
           result.put("message", "图片上传失败");
           return JsonUtils.objectToJson(result);
       }

    }
}
