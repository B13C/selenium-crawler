package cn.gaple.crawler.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Collections;

/**
 * 参考  http://www.51testing.com/html/53/15344953-7792295.html
 */
public class SeleniumDriverUtils {
    private SeleniumDriverUtils() {
    }

    /**
     * 获取chromeDriver对象
     *
     * @param chromedriverPath chromeDriver可执行文件路径
     * @return ChromeDriver
     */
    public static ChromeDriver chromeWebDriver(String chromedriverPath) {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        // 启动进入隐身模式
        chromeOptions.addArguments("--incognito");
        // 隐藏滚动条, 应对一些特殊页面
        chromeOptions.addArguments("--hide-scrollbars");
        // 以最高权限运行
        chromeOptions.addArguments("--no-sandbox");
        //  不加载图片, 提升速度
        // chromeOptions.addArguments("blink-settings=imagesEnabled=false");
        // 禁用JavaScript
        chromeOptions.addArguments("--disable-javascript");
        // 禁用图像
        chromeOptions.addArguments("--disable-images");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        // 设置开发者模式启动，该模式下webdriver属性为正常值
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(chromeOptions);
    }

    /**
     * 获取firefoxDriver对象
     * firefoxDriver的驱动名字叫做  geckodriver
     *
     * @param firefoxDriverPath firefoxDriver的可执行文件路径
     * @return FirefoxDriver
     */
    public static FirefoxDriver firefoxDriver(String firefoxDriverPath) {
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // 设置无头浏览  不显示浏览器界面
        firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--incognito");
        // 禁止GPU渲染
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("--no-sandbox");
        // 禁止浏览器被自动化的提示
        firefoxOptions.addArguments("--disable-infobars");
        //反爬关键：window.navigator.webdriver值=false
        firefoxOptions.setCapability("dom.webdriver.enabled", false);
        // 忽略错误
        firefoxOptions.addArguments("--ignore-certificate-errors");
        // 设置隐身模式  可以加快速度 但是 无样式数据
        // firefoxOptions.addArguments("--private");
        // 安全模式启动
        firefoxOptions.addArguments("-safe-mode");
        // 禁止加载图片
        firefoxOptions.setCapability("permissions.default.image", 2);
        // 禁止js
        firefoxOptions.setCapability("javascript.enabled", false);
        // 禁止加载css样式
        firefoxOptions.setCapability("permissions.default.stylesheet", 2);
        //禁用缓存
        firefoxOptions.setCapability("network.http.use-cache", true);
        firefoxOptions.setCapability("browser.cache.memory.enable", true);
        firefoxOptions.setCapability("browser.cache.disk.enable", true);
        firefoxOptions.setCapability("network.http.pipelining", true);
        firefoxOptions.setCapability("network.http.proxy.pipelining", true);
        firefoxOptions.setCapability("acceptInsecureCerts", false);
        return new FirefoxDriver(firefoxOptions);
    }
}
