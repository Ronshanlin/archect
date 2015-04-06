package com.shanlin.demo.weibo;

import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

public class AuthorizeCallback {
	AuthorizeCbLinstener linstener = new AuthorizeCbLinstener();
	
	
	public void exec(String code, String state) throws WeiboException{
		// 获取token
		AccessToken accessToken = WeiboClient.getInstance().getOauth().getAccessTokenByCode(code);
		// set
		WeiboClient.getInstance().setToken(accessToken);
	}
}
