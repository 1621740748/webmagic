package us.codecraft.webmagic.samples;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author code4crafter@gmail.com <br>
 * Date: 13-4-21
 * Time: 下午8:08
 */
public class XianzhiProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
//       List<String> requests = page.getHtml().links().regex("(http://www\\.diaoyuweng\\.com/home\\.php\\?mod=space&uid=88304&do=thread&view=me&type=thread&order=dateline&from=space&page=\\d+)").all();
//        page.addTargetRequests(requests);
//        requests = page.getHtml().links().regex("(http://www\\.diaoyuweng\\.com/thread-\\d+-1-1.html)").all();
//        page.addTargetRequests(requests);
//        if (page.getUrl().toString().contains("thread")){
//            page.putField("title", page.getHtml().xpath("//a[@id='thread_subject']"));
//            page.putField("content", page.getHtml().xpath("//div[@class='pcb']//tbody/tidyText()"));
//            page.putField("date",page.getHtml().regex("发表于 (\\d{4}-\\d+-\\d+ \\d+:\\d+:\\d+)"));
//            page.putField("id",new PlainText("1000"+page.getUrl().regex("http://www\\.diaoyuweng\\.com/thread-(\\d+)-1-1.html").toString()));
//        }
    	
    	List<String> requests = page.getHtml().links().regex("https://xz.aliyun.com/.*").all();
    	page.addTargetRequests(requests);
//        for(String link:requests) { 
//        	System.out.println(link); 
//        }
    	if(page.getUrl().toString().contains("/t/")) {
    		 page.putField("title", page.getHtml().xpath("//div[@class='main-topic']//span[@class='content-title']/tidyText()"));
    		 page.putField("content", page.getHtml().xpath("//div[@class='main-topic']//div[@id='topic_content']"));

    	}
	

    }

    @Override
    public Site getSite() {
        if (site==null){
            site= Site.me().setDomain("xz.aliyun.com").
                    setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31").setCharset("UTF-8").setSleepTime(500);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new XianzhiProcessor()).addUrl("https://xz.aliyun.com").run();
    }
}
