package com.performer.player.video.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.PolicyConditions;
import com.google.gson.Gson;
import com.performer.player.video.entity.callBackBodyData;
import com.performer.player.video.entity.getOssSignResponseBodyData;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class video {

	private static final Logger LOG = LoggerFactory.getLogger(video.class);
    //OSS 对外服务的访问域名
  	@Value("${oss.endpoint}")
  	private String endpoint;
  	//秘钥加密中用于标识用户
  	@Value("${oss.access.id}")
  	private String accessId;
  	//访问秘钥
  	@Value("${oss.access.key}")
  	private String accessKey;
  	//存储空间
  	@Value("${oss.bucket}")
  	private String bucket;
  	//存储的文件夹
  	@Value("${oss.dir}")
  	private String dir;
  	//Policy的失效时间
  	@Value("${oss.expire.time}")
  	private long expireTime;
  	//上传文件最大限制
  	@Value("${oss.policy.max.size}")
  	private long policyMaxSize;
  	//回调url
  	@Value("${oss.callback.url}")
  	private String callbackUrl;
  	//发起回调时body的值 支持oss系统变量、自定义变量和常量
  	private String callbackBody = "{\"mimeType\":${mimeType},\"size\":${size},\"object\":${object},\"etag\":${etag}}";
  	
  	@RequestMapping(value = "/getOssSign",method = RequestMethod.GET)
    public getOssSignResponseBodyData getOssSign(HttpServletRequest request, HttpServletResponse response){
    	getOssSignResponseBodyData da = new getOssSignResponseBodyData();
    	String host = "https://" + bucket + "." + endpoint;
		OSSClient client = new OSSClient(endpoint, accessId, accessKey);
		try {
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			//设置policy的失效时间
			Date expiration = new Date(expireEndTime);
			PolicyConditions policyConds = new PolicyConditions();
			//上传文件的大小限制
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, policyMaxSize);

			String postPolicy = client.generatePostPolicy(expiration, policyConds);
			Map<String, String> callbackMap = new LinkedHashMap<String, String>();
//			Long userId = (long) 1234567890;
//			JSONObject jasonCallback = new JSONObject();
//			jasonCallback.put("callbackUrl", callbackUrl);
//			jasonCallback.put("callbackBody",
//					"filename=${object}&size=${size}&mimeType=${mimeType}&imageHeight=${imageInfo.height}&imageWidth=${imageInfo.width}&user_id="+userId+"");
//			jasonCallback.put("callbackBodyType", "application/json");
			
			callbackMap.put("callbackUrl", callbackUrl);
			callbackMap.put("callbackBody", callbackBody);
			callbackMap.put("callbackBodyType", "application/json");
			JSONObject callbackJson = JSONObject.fromObject(callbackMap);
			byte[] callbackData = callbackJson.toString().getBytes("utf-8");
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);
			
			da.setAccessid(accessId);
			da.setPolicy(encodedPolicy);
			da.setSignature(postSignature);
			da.setDir(dir);
			da.setHost(host);
			da.setExpire(String.valueOf(expireEndTime / 1000));
			da.setCallback(BinaryUtil.toBase64String(callbackData));
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		} finally{
			//关闭
			client.shutdown();
		}
        return da;
    }
  	
  	@RequestMapping(value = "/callback",method = RequestMethod.POST)
  	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ossCallbackBody = GetPostBody(request.getInputStream(),
				Integer.parseInt(request.getHeader("content-length")));
		boolean ret = VerifyOSSCallbackRequest(request, ossCallbackBody);
		System.out.println("verify result : " + ret);
		LOG.info("OSS Callback Body:" + ossCallbackBody);
//		Gson gson = new Gson();
//		callBackBodyData resData = gson.fromJson(ossCallbackBody, callBackBodyData.class);
//		Long id = resData.getUser_id();
//		LOG.info(""+id);
		
		if (ret) {
			response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
		} else {
			response(request, response, "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
		}
	}
  	
  	/**
	 * 获取public key
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("finally")
	public String executeGet(String url) {
		BufferedReader in = null;
		String content = null;
		try {
			// 定义HttpClient
			HttpClient client = HttpClientBuilder.create().build();
			// 实例化HTTP方法
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}

	/**
	 * 获取Post消息体
	 * 
	 * @param is
	 * @param contentLen
	 * @return
	 */
	public String GetPostBody(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen - readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}
				return new String(message);
			} catch (IOException e) {
			}
		}
		return "";
	}

	/**
	 * 验证上传回调的Request
	 * 
	 * @param request
	 * @param ossCallbackBody
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody)
			throws NumberFormatException, IOException {
		boolean ret = false;
		String autorizationInput = new String(request.getHeader("Authorization"));
		String pubKeyInput = request.getHeader("x-oss-pub-key-url");
		byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
		byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
		String pubKeyAddr = new String(pubKey);
		if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/")
				&& !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
			System.out.println("pub key addr must be oss addrss");
			return false;
		}
		String retString = executeGet(pubKeyAddr);
		retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
		retString = retString.replace("-----END PUBLIC KEY-----", "");
		LOG.info("public key is"+retString);
		String queryString = request.getQueryString();
		String uri = request.getRequestURI();
		String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
		String authStr = decodeUri;
		if (queryString != null && !queryString.equals("")) {
			authStr += "?" + queryString;
		}
		authStr += "\n" + ossCallbackBody;
		LOG.info("content is:"+authStr);
		LOG.info("sign is:"+authorization);
		ret = doCheck(authStr, authorization, retString);
		return ret;
	}

	/**
	 * 验证RSA
	 * 
	 * @param content
	 * @param sign
	 * @param publicKey
	 * @return
	 */
	public boolean doCheck(String content, byte[] sign, String publicKey) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
			signature.initVerify(pubKey);
			signature.update(content.getBytes());
			boolean bverify = signature.verify(sign);
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 服务器响应结果
	 * 
	 * @param request
	 * @param response
	 * @param results
	 * @param status
	 * @throws IOException
	 */
	public void response(HttpServletRequest request, HttpServletResponse response, String results, int status)
			throws IOException {
		String callbackFunName = request.getParameter("callback");
		response.addHeader("Content-Length", String.valueOf(results.length()));
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(status);
		response.flushBuffer();
	}
}
