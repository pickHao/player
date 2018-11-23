package com.performer.player.video.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.PolicyConditions;
import com.performer.player.video.entity.getOssSignResponseBodyData;

import java.util.Date;

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
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);
			
			da.setAccessid(accessId);
			da.setPolicy(encodedPolicy);
			da.setSignature(postSignature);
			da.setDir(dir);
			da.setHost(host);
			da.setExpire(String.valueOf(expireEndTime / 1000));
			
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		} finally{
			//关闭
			client.shutdown();
		}
        return da;
    }
}
