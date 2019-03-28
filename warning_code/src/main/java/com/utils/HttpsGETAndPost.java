package com.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.*;

import org.apache.commons.lang.StringUtils;

public class HttpsGETAndPost {
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	private static final String DEFAULT_CHARSET = "utf-8";

	public static String doGet(String url, String charset,
			int connectTimeout, int readTimeout) throws IOException{
		String ctype = "application/json;charset=" + charset;
		byte[] content = {};
		return doGet(url, ctype, content, connectTimeout, readTimeout);

	}

	public static InputStream doGetForStream(String url, String params,
			String charset, int connectTimeout, int readTimeout)
			throws Exception {
		byte[] content = {};
		return doGetForStream(url,  content, connectTimeout, readTimeout);

	}

	private static InputStream doGetForStream(String url, 
			byte[] content, int connectTimeout, int readTimeout)
			throws Exception {
		HttpsURLConnection conn = null;
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		BufferedInputStream bis = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		// String rsp = null;
		try {
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0],
						new TrustManager[] { new DefaultTrustManager() },
						new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = (HttpsURLConnection) new URL(url).openConnection();
				conn.setRequestMethod(METHOD_GET);
				conn.setDoInput(true);
				conn.setDoOutput(true);
				
				conn.setUseCaches(false);

				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (Exception e) {
				// log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw e;
			}
			try {
				
				bis = new BufferedInputStream(conn.getInputStream());
				while ((size = bis.read(buf)) != -1) {
					bos.write(buf, 0, size);
				}
				ByteArrayInputStream in = new ByteArrayInputStream(bos.toByteArray());
				return in;
			} catch (IOException e) {
				// log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw e;
			}

		} finally {
			
			if (conn != null) {
				conn.disconnect();
			}
			if(bis!=null){
				
				bis.close();
			}
			if(bos!=null){
				
				bos.close();
			}
		}
	}

	public static String doGet(String url, String ctype, byte[] content,
			int connectTimeout, int readTimeout) throws IOException {
		HttpsURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0],
						new TrustManager[] { new DefaultTrustManager() },
						new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = getConnection(new URL(url), METHOD_GET, ctype);
				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (IOException e) {
				// log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw e;
			} catch (NoSuchAlgorithmException e) {
			} catch (KeyManagementException e) {
			}
			try {
				out = conn.getOutputStream();
				out.write(content);
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				// log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw e;
			}

		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	public static String doPost(String url, String params, String charset,
			int connectTimeout, int readTimeout) throws Exception {
		String ctype = "application/json;charset=" + charset;
		byte[] content = {};
		if (params != null) {
			content = params.getBytes(charset);
		}

		return doPost(url, ctype, content, connectTimeout, readTimeout);
	}

	public static String doPost(String url, String ctype, byte[] content,
			int connectTimeout, int readTimeout) throws Exception {
		HttpsURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(new KeyManager[0],
						new TrustManager[] { new DefaultTrustManager() },
						new SecureRandom());
				SSLContext.setDefault(ctx);

				conn = getConnection(new URL(url), METHOD_POST, ctype);
				conn.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
				conn.setConnectTimeout(connectTimeout);
				conn.setReadTimeout(readTimeout);
			} catch (Exception e) {
				// log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
				throw e;
			}
			try {
				out = conn.getOutputStream();
				out.write(content);
				rsp = getResponseAsString(conn);
			} catch (IOException e) {
				// log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
				throw e;
			}

		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	private static class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

	}

	private static HttpsURLConnection getConnection(URL url, String method,
			String ctype) throws IOException {
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
		conn.setRequestProperty("User-Agent", "stargate");
		conn.setRequestProperty("Content-Type", ctype);
		return conn;
	}

	protected static String getResponseAsString(HttpURLConnection conn)
			throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (StringUtils.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":"
						+ conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}

	private static String getStreamAsString(InputStream stream, String charset)
			throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;

		if (!StringUtils.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtils.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

	public static String getJsonString(String urlPath) throws Exception {
		URL url = new URL(urlPath);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		// 对应的字符编码转换
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String str = null;
		StringBuffer sb = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			sb.append(str);
		}
		reader.close();
		connection.disconnect();
		return sb.toString();
	}

	
	/**
	 * 流周转
	 * 
	 * @param ins
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public static ByteArrayInputStream stream2BetyStream(InputStream ins) {
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		bis = new BufferedInputStream(ins);
		ByteArrayInputStream in = null;
		try {
			while ((size = bis.read(buf)) != -1) {
				bos.write(buf, 0, size);
			}
			in = new ByteArrayInputStream(bos.toByteArray());
		} catch (IOException e) {
		}finally{
			try {
				if(in!=null){
					in.close();
				}				
				if(bis!=null){
					bis.close();
				}
				if(bos!=null){
					
					bos.close();
				}
			} catch (IOException e) {
			}
		}
		return in;

	}
	
	
	public static void main(String[] args) throws Exception {
		HttpsGETAndPost
				.doGetForStream(
						"https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFY7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL19FT0hodGpsVkNvdGRWQ2tfbS0yAAIEUhF%2FVwMEAAAAAA%3D%3D",
						null, "utf-8", 6000, 6000);

	}

}
