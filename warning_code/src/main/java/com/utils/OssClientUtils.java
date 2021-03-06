package com.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class OssClientUtils {

    public final static Logger _logger = LoggerFactory.getLogger(OssClientUtils.class);

    //阿里云API的内或外网域名
    private static String ENDPOINT;
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    //阿里云API的bucket名称
    private static String BACKET_NAME;
    //阿里云API的文件夹名称
    private static String FOLDER;

    private static String FILE;
    private static String ENDPOINT_URL;

    //初始化属性
    static{
        ENDPOINT = OSSClientConstants.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConstants.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConstants.ACCESS_KEY_SECRET;
        BACKET_NAME = OSSClientConstants.BACKET_NAME;
        FOLDER = OSSClientConstants.FOLDER;
        FILE = OSSClientConstants.FILE;
        ENDPOINT_URL = OSSClientConstants.ENDPOINT_URL;
    }

    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static  OSSClient getOSSClient(){
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }


    /**
     * 创建存储空间
     * @param ossClient      OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public  static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames=bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            _logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     * @param ossClient  oss对象
     * @param bucketName  存储空间
     */
    public static  void deleteBucket(OSSClient ossClient, String bucketName){
        ossClient.deleteBucket(bucketName);
        _logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     * @param ossClient oss连接
     * @param bucketName 存储空间
     * @param folder   模拟文件夹名如"qj_nanjing/"
     * @return  文件夹名
     */
    public  static String createFolder(OSSClient ossClient,String bucketName,String folder){
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在，不存在则创建
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            _logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param ossClient  oss连接
     * @param bucketName  存储空间
     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
        _logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 上传图片至OSS
     * @param ossClient  oss连接
     * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
     * @param bucketName  存储空间
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @return String 返回的唯一MD5数字签名
     * */
    public static  String uploadObject2OSS(OSSClient ossClient, File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);

            //解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            _logger.error("上传阿里云OSS服务器异常." + e);
        }
        return resultStr;
    }


    /**
     * 流形式上传 本方法没有关闭 inputStream：
     *
     * @param inputStream
     * @return String[] :数组长度为2 String[0]： 生成的key String[1]：图片地址
     */
    public String[] uploadFilesOSS(InputStream inputStream, String fileName) {
        if (inputStream == null) {
            _logger.error("流形式上传的 uploadFileImg 方法 inputStream=", inputStream);
        }
        OSSClient ossClient = null;
        String newFile = "";
        if (StringUtils.isNotBlank(fileName)) {
            newFile = ImgUploadUtil.getName(fileName);
        }
        StringBuffer buff = new StringBuffer();
        buff.append(FOLDER).append(newFile);
        // 文件key 组装
        String buff2Str = buff.toString();

        /*
         * 组装 对应的 图片请求地址 和对应 的 key surl[0] = key surl[1] = 图片url
         */
        String[] surl = new String[2];
        try {
            ossClient = getOSSClient();

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            PutObjectResult putResult = ossClient.putObject(BACKET_NAME, buff2Str, inputStream, objectMetadata);
            /*
             * 1:节省网络开支上传后的图片通过oss 配置的图片域名加key配置2： newFile 产生新的 文件名和3：生成的图片目录
             */
            surl[0] = URLEncoder.encode(buff2Str, "UTF-8");
            StringBuffer url = new StringBuffer();
            url.append(ENDPOINT_URL).append("/").append(buff2Str);
            surl[1] = url.toString();
            _logger.info("-surl[0]-- "+ surl[0]);
            _logger.info("-surl[1]-- "+ surl[1]);
        } catch (Exception e) {
            _logger.error("流上传文件的出现异常", e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return surl;
    }


    /**
     * 网络上传图片
     *
     * @param url
     *            ：含有http格式
     * @return String[] :数组长度为2 String[0]： 生成的key String[1]：图片地址
     */
    public static String[] uploadUrlImg(String url, String oldFileName) {
        // 上传
        InputStream inputStream = null;
        // 返回值
        String[] rs = null;
        try {
            inputStream = new URL(url).openStream();
            InputStream ins =HttpsGETAndPost.stream2BetyStream(inputStream);
            rs = uploadFile2OSS(ins, oldFileName);
        } catch (MalformedURLException e) {
            _logger.error("传入的url格式错误", e.getMessage());
        } catch (IOException e) {
            _logger.error("url 网络io 异常", e.getMessage());
        }finally{
            if(inputStream!=null){

                try {
                    inputStream.close();
                } catch (IOException e) {
                    _logger.error("url 网络io 关闭 异常", e.getMessage());
                }
            }
        }
        return rs;
    }


    /**
     * 流形式上传 本方法没有关闭 inputStream：
     *
     * @param inputStream
     * @return String[] :数组长度为2 String[0]： 生成的key String[1]：图片地址
     */
    public static String[] uploadFile2OSS(InputStream inputStream, String fileName) {
        if (inputStream == null) {
            _logger.error("流形式上传的 uploadFileImg 方法 inputStream=", inputStream);
        }
        OSSClient ossClient = null;
        String newFile = "";
        if (StringUtils.isNotBlank(fileName)) {
            newFile = ImgUploadUtil.getName(fileName);
        }
        StringBuffer buff = new StringBuffer();
        buff.append(FOLDER).append(newFile);
        // 文件key 组装
        String buff2Str = buff.toString();

        /*
         * 组装 对应的 图片请求地址 和对应 的 key surl[0] = key surl[1] = 图片url
         */
        String[] surl = new String[2];
        try {
            ossClient = getOSSClient();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName
                    .substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            _logger.info("key==========="+buff2Str);
            PutObjectResult putResult = ossClient.putObject(BACKET_NAME, buff2Str, inputStream, objectMetadata);
            /*
             * 1:节省网络开支上传后的图片通过oss 配置的图片域名加key配置2： newFile 产生新的 文件名和3：生成的图片目录
             */
            surl[0] = URLEncoder.encode(buff2Str, "UTF-8");
            _logger.info("--------- "+ surl[0]);
            StringBuffer url = new StringBuffer();
            url.append(ENDPOINT_URL).append("/").append(buff2Str);
            surl[1] = url.toString();
            _logger.info("--------- "+ surl[1]);
            _logger.info("sssss==="+ putResult.getETag());
            _logger.info("id==="+ putResult.getRequestId());
        } catch (Exception e) {
            _logger.error("流上传文件的出现异常", e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return surl;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static  String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

    public static void main(String[] args){
        uploadUrlImg("http://img5.duitang.com/uploads/item/201205/16/20120516154405_BYNQH.thumb.700_0.jpeg", "003.jpg");
    }
}
