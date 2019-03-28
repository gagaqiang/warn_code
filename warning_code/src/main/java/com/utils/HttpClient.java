package com.utils;

import org.springframework.http.MediaType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    /**
     * post请求
     * @param url
     * @param content params
     * @param contentType MediaType.APPLICATION_JSON
     * @param method post
     * @param charset utf-8
     * @return
     */
    public static String post(String url, String content, String contentType, String method, String charset) {
        HttpURLConnection httpConn = null;
        BufferedReader responseReader = null;
        try {
            // 建立连接
            httpConn = (HttpURLConnection) new URL(url).openConnection();
            //设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(false);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法

            /*Accept代表发送端（客户端）希望接受的数据类型*/
            // 设置请求属性
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            byte[] paramsBytes = content.getBytes(charset);
            httpConn.setRequestProperty("Content-length", String.valueOf(paramsBytes.length));

            /* Content-Type代表发送端（客户端|服务器）发送的实体数据的数据类型*/
            httpConn.setRequestProperty("Content-Type", contentType);
//            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", charset);
            httpConn.setInstanceFollowRedirects(false);

            httpConn.setConnectTimeout(1000 * 60 * 10);
            httpConn.setReadTimeout(1000 * 60 * 10);

            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(paramsBytes);
            outputStream.close();

            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) { // 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
                return sb.toString();
            } else {
                return "HTTP-CONN-ERROR: 请求失败，响应状态编号：" + responseCode;
            }
        } catch (Exception e) {
            return "HTTP-CONN-ERROR: " + e.getMessage();
        } finally {
            try {
                if (responseReader != null) {
                    responseReader.close();
                }
                if (httpConn != null) {
                    /*关闭流*/
                    InputStream in = httpConn.getInputStream();
                    if (in != null) {
                        in.close();
                    }
                    httpConn.disconnect();
                }
            } catch (IOException e) {
            }
        }
    }

}
