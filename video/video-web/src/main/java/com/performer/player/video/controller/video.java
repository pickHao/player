package com.performer.player.video.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.PolicyConditions;
import com.performer.player.video.entity.callBackBodyData;
import com.performer.player.video.entity.getOssSignResponseBodyData;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
  	private String callbackBody = "{\"bucket\":${bucket},\"mimeType\":${mimeType},\"size\":${size},\"object\":${object},\"etag\":${etag},\"imageInfo.height\":${imageInfo.height},\"imageInfo.width\":${imageInfo.width},\"imageInfo.format\":${imageInfo.format},\"my_var\":${x:my_var}}";
  	
  	@RequestMapping(value = "/getOssSign",method = RequestMethod.GET)
    public getOssSignResponseBodyData getOssSign(){
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
  	public void AliupLoad(HttpServletRequest request, HttpServletResponse response){
		LOG.info("阿里上传回调开始");
		try {
			String ossCallbackBody = GetPostBody(request.getInputStream(), Integer.parseInt(request.getHeader("content-length")));
			LOG.info("oss is:"+ossCallbackBody);
//			boolean ret = VerifyOSSCallbackRequest(request, ossCallbackBody);
//			callBackBodyData advertiseData = null;
//			try {
//				advertiseData = JsonUtils.fromJson(ossCallbackBody, new TypeReference<callBackBodyData>() {});
//			} catch (JsonParseException e) {
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			LOG.info("verify result:" + ret);
//			LOG.info("OSS Callback content:" + ossCallbackBody);
//			LOG.info("etag is " + advertiseData.getEtag());
//			if (ret)
//			{
//				aliUpLoadCalllBackService.saveUploadFileInfo(ossCallbackBody);
//				response(request, response, "{\"myetag\":\""+advertiseData.getEtag()+"\"}", HttpServletResponse.SC_OK,ossCallbackBody);
////				response(request, response, "{\"myetag\":\"aaaa\"}", HttpServletResponse.SC_OK,ossCallbackBody);
//			}
//			else
//			{
//				response(request, response, "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST,ossCallbackBody);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("阿里上传回调结束");
	}
//  	protected boolean VerifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) throws NumberFormatException, IOException
//	{
//		boolean ret = false;	
//		String autorizationInput = new String(request.getHeader("Authorization"));
//		String pubKeyInput = request.getHeader("x-oss-pub-key-url");//公钥
//		byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
//		byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
//		String pubKeyAddr = new String(pubKey);
//		if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/") && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/"))
//		{
//			System.out.println("pub key addr must be oss addrss");
//			return false;
//		}
//		String retString = executeGet(pubKeyAddr);
//		retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
//		retString = retString.replace("-----END PUBLIC KEY-----", "");
//		String queryString = request.getQueryString();
//		String uri = request.getRequestURI();
//		String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
//		String authStr = decodeUri;
//		if (queryString != null && !queryString.equals("")) {
//			authStr += "?" + queryString;
//		}
//		authStr += "\n" + ossCallbackBody;
//		ret = doCheck(authStr, authorization, retString);
//		return ret;
//	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String ossCallbackBody = GetPostBody(request.getInputStream(),
//				Integer.parseInt(request.getHeader("content-length")));
//		boolean ret = VerifyOSSCallbackRequest(request, ossCallbackBody);
//		System.out.println("verify result : " + ret);
//		// System.out.println("OSS Callback Body:" + ossCallbackBody);
//		if (ret) {
//			response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
//		} else {
//			response(request, response, "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
//		}
//	}
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
	private void response(HttpServletRequest request, HttpServletResponse response, String results, int status,String ossCallbackBody) throws IOException {
		String callbackFunName = request.getParameter("callback");
		LOG.info("callbackFunName is:"+callbackFunName);
		response.addHeader("Content-Length", String.valueOf(results.length()));
		response.addHeader("OSS-CallbackBody",ossCallbackBody);
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(status);
		response.flushBuffer();
	}
}
