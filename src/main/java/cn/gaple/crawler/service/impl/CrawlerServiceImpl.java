package cn.gaple.crawler.service.impl;

import cn.gaple.crawler.protocol.res.CrawlerResProtocol;
import cn.gaple.crawler.service.CrawlerService;
import cn.hutool.http.HttpUtil;
import org.springframework.stereotype.Service;

@Service
public class CrawlerServiceImpl implements CrawlerService {
    @Override
    public CrawlerResProtocol obtainContent() {
        String s = HttpUtil.get("http://www.cqvip.com/QK/80222X/202224/epub1000003449282.html");
        System.out.println(s);
        CrawlerResProtocol crawlerResProtocol = new CrawlerResProtocol();
        crawlerResProtocol.setContent(s);
        return crawlerResProtocol;
    }
}
