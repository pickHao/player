package com.performer.player.video.entity;

public class callBackBodyData {
	/**
	 * oss�洢�ռ�
	 */
	public String bucket;
	/**
	 * �洢�ļ�����
	 */
	public String mimeType;
	/**
	 * �ļ���С
	 */
	public String size;
	/**
	 * ���·�� url
	 */
	public String object;
	/**
	 * �����ļ���md5�루�ֶ��ϴ����ļ�ÿ�ε�md5����ͬ��
	 */
	public String etag;
	/**
	 * ͼƬ�� �ϴ���ͼƬ�������
	 */
	public String imageHeight;
	/**
	 * ͼƬ��
	 */
	public String imageWidth;
	/**
	 * ͼƬ����
	 */
	public String imageType;
	/**
	 * �Զ������ �û�id
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
