/**
 * 
 */
package com.shanlin.demo.crawler;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author shanlin
 *
 */
public class MyCrawler extends WebCrawler {
	  
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico"  
                    + "|png|tiff?|mid|mp2|mp3|mp4"  
                    + "|wav|avi|mov|mpeg|ram|m4v|pdf"  
                    + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
    
    private final static String URL_PREFIX = "*";
    private final static String URL_PREFIX_LIST = "*";
    
	@Override
	public boolean shouldVisit(Page page, WebURL url) {
		String href = url.getURL().toLowerCase();
		if (FILTERS.matcher(href).matches() || href.startsWith(URL_PREFIX_LIST)
				|| !href.startsWith(URL_PREFIX)) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void visit(Page page) {
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			
			System.out.println("url:"+page.getWebURL().getURL()+"; title:"+htmlParseData.getTitle());
		}
	}
}
