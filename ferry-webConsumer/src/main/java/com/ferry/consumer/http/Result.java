package com.ferry.consumer.http;

import org.apache.http.HttpStatus;

/**
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public class Result {

	private int code = 200;
	private String msg;
	private Object data;

	public static Result error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}

	public static Result error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}

	public static Result error(int code, String msg) {
		Result r = new Result();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static Result ok(String msg) {
		Result r = new Result();
		r.setMsg(msg);
		return r;
	}
	
	public static Result ok(Object data) {
		Result r = new Result();
		r.setData(data);
		return r;
	}
	
	public static Result ok() {
		return new Result();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
