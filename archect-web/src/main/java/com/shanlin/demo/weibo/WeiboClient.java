/**
 * 
 */
package com.shanlin.demo.weibo;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;
import weibo4j.util.WeiboConfig;

/**
 * @author shanlin
 *
 */
public class WeiboClient {
	private static final String AppKey = "*";
	private static final String AppSecret = "****";
	private static final String redirectUrl = "https://api.weibo.com/oauth2/default.html";
	private static WeiboClient client = new WeiboClient();
	private Oauth oauth = new Oauth();
	private AccessToken token;
	
	private WeiboClient(){
	}
	
	public static WeiboClient getInstance(){
		return client;
	}
	
	public void Open() throws WeiboException {
		WeiboConfig.updateProperties("client_ID", AppKey);
		WeiboConfig.updateProperties("client_SERCRET", AppSecret);
		WeiboConfig.updateProperties("redirect_URI", redirectUrl);

		BareBonesBrowserLaunch.openURL(oauth.authorize("code", ""));
	}
	
	/**
	 * @return the oauth
	 */
	public Oauth getOauth() {
		return oauth;
	}

	public AccessToken getToken() {
		return token;
	}

	public void setToken(AccessToken token) {
		this.token = token;
	}
}
