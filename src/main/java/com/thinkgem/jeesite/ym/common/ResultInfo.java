package com.thinkgem.jeesite.ym.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class ResultInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5109035306487591781L;

	private int code;

	private String message;

	private Object data;

	public ResultInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ResultInfo get() {
		return new ResultInfo(200, "OK");
	}

	public String toString() {
		return JSON.toJSONString(this);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultInfo setData(Object data) {
		this.data = data;
		return this;
	}

	public Object getData() {
		return data;
	}

}
