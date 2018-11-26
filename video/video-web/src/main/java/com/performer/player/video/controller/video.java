package com.performer.player.video.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.PolicyConditions;
import com.google.gson.Gson;
import com.performer.player.video.entity.callBackBodyData;
import com.performer.player.video.entity.getOssSignResponseBodyData;
import com.performer.player.video.util.uploadMethod;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.Date;

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
//  	private String callbackBody = "{\"bucket\":${bucket},\"mimeType\":${mimeType},\"size\":${size},\"object\":${object},\"etag\":${etag},\"imageHeight\":${imageInfo.height},\"imageWidth\":${imageInfo.width},\"imageType\":${imageInfo.format},\"user_id\":123456}";
  	
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
//			Map<String, String> callbackMap = new LinkedHashMap<String, String>();
			Long userId = (long) 1234567890;
			JSONObject jasonCallback = new JSONObject();
			jasonCallback.put("callbackUrl", callbackUrl);
			jasonCallback.put("callbackBody",
					"filename=${object}&size=${size}&mimeType=${mimeType}&imageHeight=${imageInfo.height}&imageWidth=${imageInfo.width}&user_id="+userId+"");
			jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
			
//			callbackMap.put("callbackUrl", callbackUrl);
//			callbackMap.put("callbackBody", callbackBody);
//			callbackMap.put("callbackBodyType", "application/json");
//			JSONObject callbackJson = JSONObject.fromObject(callbackMap);
			byte[] callbackData = jasonCallback.toString().getBytes("utf-8");
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
		String ossCallbackBody = uploadMethod.GetPostBody(request.getInputStream(),
				Integer.parseInt(request.getHeader("content-length")));
		boolean ret = uploadMethod.VerifyOSSCallbackRequest(request, ossCallbackBody);
		System.out.println("verify result : " + ret);
		LOG.info("OSS Callback Body:" + ossCallbackBody);
		Gson gson = new Gson();
		callBackBodyData resData = gson.fromJson(ossCallbackBody, callBackBodyData.class);
		Long id = resData.getUser_id();
		LOG.info(""+id);
		
		if (ret) {
			uploadMethod.response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
		} else {
			uploadMethod.response(request, response, "{\"Status\":\"verdify not ok\"}", HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
