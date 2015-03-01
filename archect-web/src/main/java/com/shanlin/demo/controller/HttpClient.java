/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: HttpClientRequet.java
 * create time: 2014年11月23日 下午4:02:16
 * version: v1.0
 */
package com.shanlin.demo.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.Cancellable;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author shanlin
 *
 */
public class HttpClient {
	public void post(String uri){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(uri);
		
		post.setCancellable(new Cancellable() {
			public boolean cancel() {
				return false;
			}
		});
		
//		HttpEntity entity = 
//		post.setEntity(entity);;
	}
}
