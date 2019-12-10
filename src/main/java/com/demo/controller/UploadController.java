package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

@RestController
public class UploadController {
	
	private static String IMG_BASE64="";
	
	/**
	 * jquery图片上传-非跨域
	 */
	@RequestMapping(value = "/jquery/upload", method = RequestMethod.POST)
	public String jquery(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> rsMap = new HashMap<>();
		MultipartFile multipartFile=((MultipartHttpServletRequest) request).getFile("imgUpload");
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));// 文件后缀
		// key 以文件以时间命名，根据上传文件名取后缀
		String objectkey = "upload/" + System.currentTimeMillis() + suffix;
		File file = new File("src/main/webapp/"+objectkey);
		try {
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
		} catch (IOException e) {
			rsMap.put("code", "failed");
			System.out.println("接收图片出错");
			return JSON.toJSONString(rsMap);
		}
		//获取base64图片编码
		IMG_BASE64 = ImgBase64.getImgStr(file);
		System.out.println(IMG_BASE64);
		//组装上传结果
		rsMap.put("code", "success");
		rsMap.put("imgPath", objectkey);
		return JSON.toJSONString(rsMap);
	}
	/**
	 * jquery图片下载
	 */
	@RequestMapping(value = "/jquery/download", method = RequestMethod.GET)
	public void downloadImg(HttpServletResponse response) {
		try {
			ImgBase64.generateImage(IMG_BASE64, response.getOutputStream());
			System.out.println("下载base64图片");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * jquery图片上传-跨域-XMLHttpRequest提交
	 */
	@CrossOrigin
	@RequestMapping(value = "/jquery/cross/xmlupload", method = RequestMethod.POST)
	public String jqueryCross(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> rsMap = new HashMap<>();
		MultipartFile multipartFile=((MultipartHttpServletRequest) request).getFile("imgUpload");
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));// 文件后缀
		// key 以文件以时间命名，根据上传文件名取后缀
		String objectkey = "upload/" + System.currentTimeMillis() + suffix;
		File file = new File("src/main/webapp/"+objectkey);
		try {
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
		} catch (IOException e) {
			rsMap.put("code", "failed");
			System.out.println("接收图片出错");
			return JSON.toJSONString(rsMap);
		}
		//组装上传结果
		rsMap.put("code", "success");
		rsMap.put("imgPath", objectkey);
		return JSON.toJSONString(rsMap);
	}
	/**
	 * jquery图片上传-跨域-iframe提交
	 */
	@RequestMapping(value = "/jquery/cross/iframeupload", method = RequestMethod.POST)
	public String jqueryCrossIframe(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> rsMap = new HashMap<>();
		MultipartFile multipartFile=((MultipartHttpServletRequest) request).getFile("imgUpload");
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."));// 文件后缀
		// key 以文件以时间命名，根据上传文件名取后缀
		String objectkey = "upload/" + System.currentTimeMillis() + suffix;
		File file = new File("src/main/webapp/"+objectkey);
		try {
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
		} catch (IOException e) {
			rsMap.put("code", "failed");
			System.out.println("接收图片出错");
			return JSON.toJSONString(rsMap);
		}
		//组装上传结果
		rsMap.put("code", "success");
		rsMap.put("imgPath", objectkey);
		String redirect = request.getParameter("redirect");
		try {
			response.sendRedirect(redirect + JSON.toJSONString(rsMap));
		} catch (IOException e) {
			System.out.println("response跳转设置出错：" + e.getMessage());
		}
		return null;
	}
}
