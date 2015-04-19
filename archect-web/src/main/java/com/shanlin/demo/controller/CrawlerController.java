/**
 * 
 */
package com.shanlin.demo.controller;

import com.shanlin.demo.crawler.ImageCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author shanlin
 *
 */
public class CrawlerController {
//	public static void main(String[] args) throws Exception {
//        String crawlStorageFolder = "f:\\crawler\\root";
//        int numberOfCrawlers = 3;
//
//        CrawlConfig config = new CrawlConfig();
//        config.setCrawlStorageFolder(crawlStorageFolder);
//
//        PageFetcher pageFetcher = new PageFetcher(config);
//        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
//        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
//        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
//
//        controller.addSeed("http://www.czyunliang.com");
//
//        controller.start(MyCrawler.class, numberOfCrawlers);   
//	}
	
	/**
	 * 图片爬虫
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
        final String crawlStorageFolder = "f:\\crawler\\root";
        int numberOfCrawlers = 3;
        final String[] crawlDomains = new String[]{"XXX"};
        
        
        create(ImageCrawler.class,crawlStorageFolder, numberOfCrawlers, crawlDomains, new CrawlerCallback() {
			
			public void exec() {
				ImageCrawler.configure(crawlDomains, crawlStorageFolder);
			}
		});
	}
	
	private static void create( Class<? extends WebCrawler> clazz, String crawlStorageFolder, int numberOfCrawlers, String[] crawlDomains, CrawlerCallback crawlerCallback) throws Exception{
		CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setIncludeBinaryContentInCrawling(true);
        
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		for (String domain : crawlDomains) {
			controller.addSeed(domain);
		}
		
		crawlerCallback.exec();
        
        controller.start(clazz, numberOfCrawlers);   
	}
	
	private interface CrawlerCallback{
		void exec();
	}
}
