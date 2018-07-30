package com.pinyougou.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Result;

import utils.FastDFSClient;

@RestController
public class UploadController {

	@Value("${IMAGE_SERVER_URL}")
	private String imageServerUrl;
	
	
	@RequestMapping("/upload")
	public Result uploadFile(MultipartFile file) {
		
		try {
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
			String originalFilename = file.getOriginalFilename();
//			originalFilename:T1.jpg
//			jpg  png
//			扩展名截取
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			String uploadFile = fastDFSClient.uploadFile(file.getBytes(), extName);
//			uploadFile：group1/M00/00/00/wKgZhVsCRciAfmNFAACuI4TeyLI361.jpg
			return new Result(true, imageServerUrl+uploadFile);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "上传失败");
		}
		
	}
}
