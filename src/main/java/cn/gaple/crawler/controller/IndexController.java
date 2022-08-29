package cn.gaple.crawler.controller;

import cn.gaple.crawler.protocol.req.CrawlerReqProtocol;
import cn.gaple.crawler.protocol.res.CrawlerResProtocol;
import cn.gaple.crawler.service.CrawlerService;
import cn.hutool.core.lang.Dict;
import cn.maple.core.framework.annotation.GXRequestBody;
import cn.maple.core.framework.controller.GXBaseController;
import cn.maple.core.framework.util.GXResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class IndexController implements GXBaseController {
    @Resource
    private CrawlerService crawlerService;

    @PostMapping("/")
    public GXResultUtils<CrawlerResProtocol> index(@GXRequestBody CrawlerReqProtocol reqProtocol) {
        Dict data = Dict.create().set("content", "测试内容");
        CrawlerResProtocol crawlerResProtocol = crawlerService.obtainContent();
        return GXResultUtils.ok(crawlerResProtocol);
    }
}
