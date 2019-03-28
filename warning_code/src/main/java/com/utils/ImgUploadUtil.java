package com.utils;

import java.util.Random;

public class ImgUploadUtil {

	/**
	 * 将图片上传到服务器
	 *
	 * @param imgFile
	 * @param type
	 * @return
	 */
	// public static String uploadImg(MultipartFile imgFile, String type) throws
	// IOException ,ImgException{
	// if (imgFile.getSize() > 1024 * 1024) {
	// throw new ImgException("上传图片大小不能超过1M！");
	// }
	// SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
	// String dirPath = ImgUploadUtil.IMG_DIR + type + "/" + formater.format(new
	// Date());
	//
	// File dir = new File(dirPath);
	// if (!dir.exists()) {
	// dir.mkdirs();
	// }
	// String fileName = ImgUploadUtil.getName(imgFile.getOriginalFilename());
	// if (!fileName.endsWith("png") && !fileName.endsWith("jpg") &&
	// !fileName.endsWith("gif")
	// && !fileName.endsWith("jpeg") && !fileName.endsWith("bmp")) {
	//
	//
	// }
	// //原图,用于点击图片后查看原图
	// File fileOnServer = new File(dirPath + "/" + fileName);
	// imgFile.transferTo(fileOnServer);
	// return dirPath + "/" + fileName;
	// }

	/**
	 * 依据原始文件名生成新文件名
	 *
	 * @return
	 */
	public static String getName(String fileName) {
		Random random = new Random();
		return random.nextInt(10000) + System.currentTimeMillis()
				+ ImgUploadUtil.getFileExt(fileName).toLowerCase();
	}

	/**
	 * 获取文件扩展名
	 *
	 * @return string
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
