package lintcode.systemDesign;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*
 * error case:
 * "http://www.wikipedia.org/": ["http://www.wikipedia.org/help/"]
"http://www.wikipedia.org/help/": ["http://www.wikipedia.org/","http://www.wikipedia.org/about/"]
"http://www.wikipedia.org/about/": ["http://www.google.com/"]
Your output
["http://www.google.com/","http://www.wikipedia.org/","http://www.wikipedia.org/","http://www.wikipedia.org/about/","http://www.wikipedia.org/help/"]
Expected
["http://www.wikipedia.org/","http://www.wikipedia.org/about/","http://www.wikipedia.org/help/"]
 */
public class WebpageCrawler {
    // 1. Producer and Consumer model
    // 2. bfs但是为了更新，这个网络不止访问一遍
    // 3. multithreading需要考虑线程安全问题
    // 4. 这道题并没有要求consumer，而只是producer -- 没有这回事儿，是你自己理解不到位
	// queue用什么结构？java的并发容器有哪些？（http://www.360doc.com/content/10/1027/21/495229_64582795.shtml）
	
     private BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    //private Queue<String> queue = new LinkedList<String>();
    /**
     * 超时版
     * @param url a url of root page
     * @return all urls
     */
    public List<String> crawler1(String url) {
        List<String> list = new ArrayList<String>();
        list.add(url);
        queue.offer(url);
        while (!queue.isEmpty()) {
            //consumer
            String current = queue.poll();
            List<String> newlinks = HtmlHelper.parseUrls(current);
            // producer
            for (String link : newlinks) {
                // System.out.println(link);
                list.add(link);
                queue.offer(link);
            }
        }
        
        return list;
    }
    // How to design multi-threading version, what and where can I change?
    public List<String> crawler2(String url) {
    	// 一个task一个线程？？
        List<String> list = new ArrayList<String>();
        list.add(url);
        queue.offer(url);
        // The condition is not very good, it will fail when the producer is slow than consumer
        while (!queue.isEmpty()) {
        	Processor processor = new Processor(queue, list);
            new Thread(processor).start();
        }

        return list;
    }
    class Processor implements Runnable {
         private List<String> urls;
         private BlockingQueue<String> queue; 
         public Processor(BlockingQueue<String> queue, List<String> urls) {
        	 this.queue = queue;
             this.urls = urls;
         }
         public void run() {
        	 String current = queue.poll();
        	 System.out.println(Thread.currentThread().getName()+","+current);
             List<String> newUrls = HtmlHelper.parseUrls(current);
             if (newUrls == null) {
            	 return;
             }
             for (String url : newUrls) {
                 System.out.println(url);
                 urls.add(url);
                 queue.offer(url);
             }
         }
     }

	@SuppressWarnings("deprecation")
	public List<String> crawler(String url) {
		Work.setFirstElement(url);
    	int thread_pool_size = 7;
    	Work[] thread_pool = new Work[thread_pool_size];
    	for (int i = 0; i < thread_pool_size; i++) {
    		thread_pool[i] = new Work();
    		thread_pool[i].start();
    	}
    	
    	try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
    	
    	for (int i = 0; i < thread_pool_size; i++) {
    		thread_pool[i].stop();
    	}
    	return Work.getUrls();
    }
    public static void main(String[] args) {
    	System.out.println("##"+HtmlHelper.parseUrls("http://www.wikipedia.org/"));
    	WebpageCrawler pageCrawler = new WebpageCrawler();
//    	System.out.println(pageCrawler.crawler2("a"));
    	System.out.println(pageCrawler.crawler("http://www.wikipedia.org/"));
    }
}
class Work extends Thread {
	// 把线程的内部逻辑都封装在Work内部
    private static BlockingQueue<String> queue  = new LinkedBlockingQueue<String>();
    private static List<String> urls = new ArrayList<String>();
    private static HashSet<String> visited = new HashSet<String>();
	public static void setFirstElement(String url) {
		String domain = "";
		try {
			URL netUrl = new URL(url);
			domain = netUrl.getHost();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(domain.endsWith("wikipedia.org")) {
	    	urls.add(url);
	        queue.offer(url);
	        visited.add(url);
		}
	}
	public static List<String> getUrls() {
		return urls;
	}
	public void run() {
		while (true) {
			String current = "";
			try {
			    current = queue.poll();
			    System.out.println(Thread.currentThread().getName()+"-"+current);
    			List<String> newUrls = HtmlHelper.parseUrls(current);
                for (String url : newUrls) {
                    System.out.println(url);
                	if (visited.contains(url)) {
                		continue;
                	}
                    visited.add(url);
                    String domain = "";
            		try {
            			URL netUrl = new URL(url);
            			domain = netUrl.getHost();
            		} catch (MalformedURLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            		if(domain.endsWith("wikipedia.org")) {
            			urls.add(url);
            		}
                    queue.offer(url);
                }
			} catch (Exception e) {
//			    e.printStackTrace();
			}
		}
	}
}
class HtmlHelper {
	private static HashMap<String, List<String>> urlMap = new HashMap<String, List<String>>();
	public static void genMap() {
		String[] aList = {"http://www.wikipedia.org/helper", "http://www.ww.org", "http://www.wikipedia.org/main"};
		urlMap.put("http://www.wikipedia.org/", new ArrayList<String>(Arrays.asList(aList)));
		String[] bList = {"http://www.google.com","http://www.wikipedia.org/main"};
		urlMap.put("http://www.wikipedia.org/helper", new ArrayList<String>(Arrays.asList(bList)));
	}
	public static List<String> parseUrls(String url) {
		// Get all urls from a webpage of given url. 
		genMap();
		return urlMap.get(url);
	}
}