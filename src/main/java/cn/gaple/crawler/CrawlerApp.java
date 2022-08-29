package cn.gaple.crawler;

import cn.maple.core.framework.annotation.GXEnableLeafFramework;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@GXEnableLeafFramework
public class CrawlerApp {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApp.class, args);
        System.err.println("测试项目启动成功(ノ￣▽￣)");
    }
}
