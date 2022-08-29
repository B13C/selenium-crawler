package cn.gaple.crawler.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--screenshot");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--hide-scrollbars");
        chromeOptions.addArguments("--no-sandbox");
        //chromeOptions.addArguments("blink-settings=imagesEnabled=false");
        chromeOptions.addArguments(/*"--no-sandbox",*/ "--disable-dev-shm-usage"/*, "--disable-gpu", "--allowed-ips"*/);
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        //chromeOptions.addArguments("blink-settings=imagesEnabled=false", "--disable-gpu");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        //chromeOptions.setExperimentalOption("useAutomationExtension", false);
        //chromeOptions.addArguments("--windows-size=1920,1080");
        chromeOptions.setHeadless(true);
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
        // WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // 设置无头浏览  不显示浏览器界面
        // firefoxOptions.setHeadless(true);
        firefoxOptions.addArguments("--headless");
        firefoxOptions.addArguments("--incognito");
        // 禁止GPU渲染
        firefoxOptions.addArguments("--disable-gpu");
        //firefoxOptions.addArguments("--no-sandbox");
        // 禁止浏览器被自动化的提示
        //firefoxOptions.addArguments("--disable-infobars");
        //反爬关键：window.navigator.webdriver值=false
        firefoxOptions.setCapability("dom.webdriver.enabled", false);
        // 忽略错误   不能添加  添加之后报错
        // firefoxOptions.addArguments("--ignore-certificate-errors");
        // 禁止图片渲染
        // firefoxOptions.addArguments("blink-settings=imagesEnabled=false");
        // 禁止加载图片
        // firefoxOptions.SetPreference("permissions.default.image", 2);
        // 安全模式启动
        // firefoxOptions.AddArgument("-safe-mode");
        // 禁止js
        // firefoxOptions.SetPreference("javascript.enabled", false);
        // 本地代理
        // firefoxOptions.AddArgument("--proxy--server=127.0.0.1:8080");
        // 禁止加载css样式
        // firefoxOptions.SetPreference("permissions.default.stylesheet", 2);
        // 设置隐身模式
        // firefoxOptions.AddArgument("--private");
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
