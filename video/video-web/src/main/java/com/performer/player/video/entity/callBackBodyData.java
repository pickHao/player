package com.performer.player.video.entity;

public class callBackBodyData {
	/**
	 * 存储文件类型
	 */
	public String mimeType;
	/**
	 * 文件大小
	 */
	public Long size;
	/**
	 * 存放路径 url
	 */
	public String filename;
	/**
	 * 图片长 上传是图片的情况下
	 */
	public Integer imageHeight;
	/**
	 * 图片宽
	 */
	public Integer imageWidth;
	/**
	 * 自定义参数 用户id
	 */
	public Long user_id;
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getImageHeight() {
		return imageHeight;
	}
	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}
	public Integer getImageWidth() {
		return imageWidth;
	}
	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
