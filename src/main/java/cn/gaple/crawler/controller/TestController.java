package cn.gaple.crawler.controller;

import cn.gaple.crawler.util.SeleniumCrawlerUtils;
import cn.gaple.crawler.util.SeleniumDriverUtils;
import cn.hutool.core.lang.Dict;
import cn.maple.core.framework.controller.GXBaseController;
import cn.maple.core.framework.util.GXResultUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController implements GXBaseController {
    /**
     * 拉取维普的详细数据
     *
     * @return
     */
    @GetMapping("screenshot-firefox")
    public GXResultUtils<Dict> testScreenshotByFirefox() {
        String driverPath = "C:\\tools\\webdriver\\geckodriver.exe";
        FirefoxDriver firefoxDriver = SeleniumDriverUtils.firefoxDriver(driverPath);
        String url = "http://www.cqvip.com/QK/80222X/202224/epub1000003449271.html";
        SeleniumCrawlerUtils.getScreenshotByFirefoxDriver(firefoxDriver, url, By.className("detailinfo"), "C:\\Users\\mapleaf\\维普1.png", 240);
        firefoxDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取维普的详细数据
     *
     * @return
     */
    @GetMapping("pdf-firefox")
    public GXResultUtils<Dict> testPDFByFirefox() {
        String driverPath = "C:\\tools\\webdriver\\geckodriver.exe";
        FirefoxDriver firefoxDriver = SeleniumDriverUtils.firefoxDriver(driverPath);
        String url = "http://www.cqvip.com/QK/80222X/202224/epub1000003449271.html";
        SeleniumCrawlerUtils.getPDFByFirefoxDriver(firefoxDriver, url, By.className("detailinfo"), "C:\\Users\\mapleaf\\维普1.pdf", 240);
        firefoxDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("screenshot-chrome")
    public GXResultUtils<Dict> testScreenshotByChrome() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        SeleniumCrawlerUtils.getScreenshotByChromeDriver(chromeDriver, url, By.className("detailInfo"), "C:\\Users\\mapleaf\\万方.png", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("pdf-chrome")
    public GXResultUtils<Dict> testPDFByChrome() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        SeleniumCrawlerUtils.getPDFByChromeDriver(chromeDriver, url, By.className("detailInfo"), "C:\\Users\\mapleaf\\万方.pdf", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("screenshot-zhiwang")
    public GXResultUtils<Dict> testScreenshotZhiwang() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "https://navi.cnki.net/knavi/journals/YXJI/detail?uniplatform=NZKPT";
        SeleniumCrawlerUtils.getScreenshotByChromeDriver(chromeDriver, url, By.className("infobox"), "C:\\Users\\mapleaf\\知网.png", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("pdf-zhiwang")
    public GXResultUtils<Dict> testPDFZhiwang() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "https://navi.cnki.net/knavi/journals/YXJI/detail?uniplatform=NZKPT";
        SeleniumCrawlerUtils.getPDFByChromeDriver(chromeDriver, url, By.className("infobox"), "C:\\Users\\mapleaf\\知网.pdf", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("screenshot-ly")
    public GXResultUtils<Dict> testScreenshotLY() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "http://www.qikan.com.cn/newarticleinfo/dzwz202233260.html";
        SeleniumCrawlerUtils.getScreenshotByChromeDriver(chromeDriver, url, By.className("textWrap"), "C:\\Users\\mapleaf\\龙源.png", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    /**
     * 拉取万方的详细数据
     *
     * @return
     */
    @GetMapping("pdf-ly")
    public GXResultUtils<Dict> testPDFLY() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "http://www.qikan.com.cn/newarticleinfo/dzwz202233260.html";
        SeleniumCrawlerUtils.getPDFByChromeDriver(chromeDriver, url, By.className("textWrap"), "C:\\Users\\mapleaf\\龙源.pdf", 240);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "塵渊"));
    }

    @GetMapping("html-firefox")
    public GXResultUtils<Dict> getHtmlByFirefox() {
        String driverPath = "C:\\tools\\webdriver\\geckodriver.exe";
        FirefoxDriver firefoxDriver = SeleniumDriverUtils.firefoxDriver(driverPath);
        String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=95519X&years=2021&num=4";
        String htmlByFirefoxWebDriver = SeleniumCrawlerUtils.getHtmlByFirefoxWebDriver(firefoxDriver, url, By.className("qkxq-content"), 120);
        firefoxDriver.close();
        return GXResultUtils.ok(Dict.create().set("html", htmlByFirefoxWebDriver));
    }

    @GetMapping("html-chrome")
    public GXResultUtils<Dict> getHtmlByChrome() {
        String driverPath = "C:\\tools\\webdriver\\chromedriver.exe";
        ChromeDriver chromeDriver = SeleniumDriverUtils.chromeWebDriver(driverPath);
        String url = "https://s.wanfangdata.com.cn/periodical?q=CPU";
        String htmlByFirefoxWebDriver = SeleniumCrawlerUtils.getHtmlByChromeWebDriver(chromeDriver, url, By.className("top-control-bar"), 120);
        chromeDriver.close();
        return GXResultUtils.ok(Dict.create().set("html", htmlByFirefoxWebDriver));
    }
}
