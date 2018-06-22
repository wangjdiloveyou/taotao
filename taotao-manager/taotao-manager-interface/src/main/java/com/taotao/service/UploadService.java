package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

public interface UploadService {

   String uploadImage(CommonsMultipartFile file, String realUploadPath) throws IOException;


}
