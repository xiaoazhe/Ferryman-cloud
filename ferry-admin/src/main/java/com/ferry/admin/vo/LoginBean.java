package com.ferry.admin.vo;

/**
 * 登录接口封装
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public class LoginBean {

	private String account;
	private String password;
	private String captcha;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
}
