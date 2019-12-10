package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class ImgBase64 {
	/**
	 * 将图片转换成Base64编码
	 * 
	 * @param imgFile 待处理图片
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理

		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}
	public static String getImgStr(File file) {
		// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(file);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr      图片数据
	 * @param imgFilePath 保存图片全路径地址
	 * @return
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) {
		//
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean generateImage(String imgStr,OutputStream out) {
		//
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpg图片
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void main(String[] args) {
		String inUrl= "D:\\dev\\eclipse-workspace\\springBoot-fileupload\\src\\main\\webapp\\upload\\1575948585556.jpg";// 待处理的图片
		String imgbase64 = getImgStr(inUrl);
//		String img_path="data:image/jpeg;base64,"+imgbase;
//		System.out.println(img_path);
		System.out.println(imgbase64.length());
		String outUrl= "D:\\test.jpg";// 新生成的图片
		generateImage(imgbase64, outUrl);
	}
}
