package com.controller.warning;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.utils.*;
import com.utils.web.RetCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Api("文件上传下载")
@RequestMapping("/upload")
@CrossOrigin
@RestController
public class UploadController {

    public final static Logger _logger = LoggerFactory.getLogger(UploadController.class);


    /**
     * 文件上传
     *
     * @param mu
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upFile", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public RetCode<Map<String, Object>> httpclentPost(@RequestParam("file") MultipartFile mu,
                                                      HttpServletRequest request) {

        Map<String, Object> resuMap = new HashMap<>();
        try {
            String fileSuffixName = mu.getOriginalFilename();
            String[] resu = OssClientUtils.uploadFile2OSS(mu.getInputStream(), fileSuffixName);

            resuMap.put("filePath", resu[1]);
            resuMap.put("fileKey", resu[0]);
            resuMap.put("fileName", fileSuffixName);
        } catch (IOException e) {
            _logger.error("上传失败>>" + e);
            return RetCode.serverError();
        }
        return RetCode.success(resuMap);

    }


    /**
     * 从阿里云下载文件 （以附件形式下载）
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        OSSClient ossClient = null;
        try {
            String fileid = request.getParameter("fileKey").toString();//从前台获取当前下载文件的id值（每个上传到阿里云的文件都会有一个独一无二的id值）
            String filename = request.getParameter("fileName").toString();//从前台获取要下载文件的文件名
            int i = filename.lastIndexOf("\\");
            filename = filename.substring(i + 1);
            String aliyunId = OSSClientConstants.ACCESS_KEY_ID;
            String aliyunSecret = OSSClientConstants.ACCESS_KEY_SECRET;
            String ossEndpoint = OSSClientConstants.ENDPOINT;
            ossClient = new OSSClient(ossEndpoint, aliyunId, aliyunSecret);
            //获取fileid对应的阿里云上的文件对象
            OSSObject ossObject = ossClient.getObject(OSSClientConstants.BACKET_NAME, fileid);//bucketName需要自己设置

            // 读去Object内容  返回
            in = new BufferedInputStream(ossObject.getObjectContent());

            out = new BufferedOutputStream(response.getOutputStream());
            //通知浏览器以附件形式下载
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

            byte[] car = new byte[1024];
            int line = 0;
            while ((line = in.read(car)) != -1) {
                out.write(car, 0, line);
            }
        } catch (Exception e) {
            _logger.error("异常="+e);
        }finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
            ossClient.shutdown();
        }
    }

}
