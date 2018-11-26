package com.performer.player.video.entity;

public class callBackBodyData {
	/**
	 * oss存储空间
	 */
	public String bucket;
	/**
	 * 存储文件类型
	 */
	public String mimeType;
	/**
	 * 文件大小
	 */
	public String size;
	/**
	 * 存放路径 url
	 */
	public String object;
	/**
	 * 单个文件的md5码（分段上传的文件每段的md5都不同）
	 */
	public String etag;
	/**
	 * 图片长 上传是图片的情况下
	 */
	public String imageHeight;
	/**
	 * 图片宽
	 */
	public String imageWidth;
	/**
	 * 图片类型
	 */
	public String imageType;
	/**
	 * 自定义参数 用户id
	 */
	public String user_id;
	public String getBucket() {
		return bucket;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getEtag() {
		return etag;
	}
	public void setEtag(String etag) {
		this.etag = etag;
	}
	public String getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}
	public String getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
