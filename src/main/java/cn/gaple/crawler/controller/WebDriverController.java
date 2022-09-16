package cn.gaple.crawler.controller;

import cn.gaple.crawler.util.SeleniumCrawlerUtils;
import cn.gaple.crawler.util.SeleniumDriverUtils;
import cn.hutool.core.lang.Dict;
import cn.maple.core.framework.controller.GXBaseController;
import cn.maple.core.framework.util.GXResultUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class WebDriverController implements GXBaseController {
    @GetMapping("/pp")
    public GXResultUtils<Dict> pp() throws IOException, InterruptedException, DocumentException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\webdriver\\chromedriver.exe");
        String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        //String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";

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
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        driver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        WebElement detailinfo = driver.findElement(By.id("app"));
        //webDriverWait.until(ExpectedConditions.visibilityOf(detailinfo));
        System.out.println(driver.getPageSource());

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(driver);

        ImageIO.write(screenshot.getImage(), "png", new File("C:\\Users\\mapleaf\\万方.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", baos);
        byte[] bytes = baos.toByteArray();


        // 1.新建document对象
        Document document = new Document();

        document.setPageSize(new Rectangle(screenshot.getImage().getWidth() + 60.0f, screenshot.getImage().getHeight() + 100.0f));

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\mapleaf\\万方.pdf"));

        // 3.打开文档
        document.open();
        Image instance = Image.getInstance(bytes);

//设置图片位置的x轴和y周
        //instance.setAbsolutePosition(0, 0);
        //设置图片的宽度和高度
        instance.scaleAbsolute((float) (screenshot.getImage().getWidth()), (float) (screenshot.getImage().getHeight()));
        // 4.添加一个内容段落
        document.add(instance);
        // 5.关闭文档
        document.close();

        //关闭书写器
        writer.close();

     /*   Pdf pdf = new Pdf(Base64.encodeBase64String(ImageTool.toByteArray(screenshot)));
        Path printPage = Paths.get("C:\\Users\\mapleaf\\万方.pdf");
        Files.write(printPage, pdf.getContent().getBytes());*/

       /* Path printPage = Paths.get("C:\\Users\\mapleaf\\万方.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));*/


         /*Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeCdpCommand(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\mapleaf\\万方.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }

    @GetMapping("/pp1")
    public GXResultUtils<Dict> pp1() throws IOException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\webdriver\\chromedriver.exe");
        //String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";

        ChromeOptions chromeOptions = new ChromeOptions();
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
        //chromeOptions.setHeadless(true);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        driver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        WebElement detailinfo = driver.findElement(By.className("qkxq-content"));
        //webDriverWait.until(ExpectedConditions.visibilityOf(detailinfo));
        System.out.println(driver.getPageSource());

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(driver);

        ImageIO.write(screenshot.getImage(), "png", new File("C:\\Users\\mapleaf\\维普.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", baos);
        byte[] bytes = baos.toByteArray();

       /* WebElement detailinfo = driver.findElement(By.ByCssSelector.className("qkxq-content"));
        System.out.println(driver.getPageSource());
*/

        Path printPage = Paths.get("C:\\Users\\mapleaf\\维普.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));

        /*
         Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeCdpCommand(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\aaaa.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }


    @GetMapping("/pp2")
    public GXResultUtils<Dict> pp2() throws IOException {
        String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("acceptSslCerts", false);
        desiredCapabilities.setCapability("takesScreenshot", true);
        desiredCapabilities.setCapability("cssSelectorsEnabled", true);
        desiredCapabilities.setBrowserName(Browser.CHROME.browserName());
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "E:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        PhantomJSDriver phantomJSDriver = new PhantomJSDriver(desiredCapabilities);
        phantomJSDriver.manage().window().setSize(new Dimension(1960, 1080));
        phantomJSDriver.get(url);
        String pageSource = phantomJSDriver.getPageSource();
        System.out.println(pageSource);
        phantomJSDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }

    @GetMapping("/pp3")
    public GXResultUtils<Dict> pp3() throws IOException {
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.gecko.driver", "C:\\tools\\webdriver\\geckodriver.exe");
        //String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        //String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";
        //String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";
        //String url = "http://www.cqvip.com/QK/80222X/202224/epub1000003449271.html";
        String url = "http://www.cqvip.com/QK/95519X/20214/7104462291.html";
        FirefoxOptions options = new FirefoxOptions();
        //firefoxOptions.addArguments("--screenshot");
        options.addArguments("--headless");
        options.addArguments("--incognito");
        //options.addArguments("--no-sandbox", "--disable-gpu");
        //firefoxOptions.addArguments("blink-settings=imagesEnabled=false", "--disable-gpu");
        //firefoxOptions.addArguments("--windows-size=1920,1080");
        //firefoxOptions.setHeadless(true);
        // options.addArguments("--width=2560", "--height=1440");
        //禁止GPU渲染
        options.addArguments("--disable-gpu");
        //忽略错误   不能添加  添加之后报错
        //options.addArguments("--ignore-certificate-errors");
        //禁止浏览器被自动化的提示
        //options.addArguments("--disable-infobars");
        //找了十几天的一句代码，反爬关键：window.navigator.webdrive值=false*********************
        options.setCapability("dom.webdriver.enabled", false);
        //禁止加载图片
        //options.SetPreference("permissions.default.image", 2);
        //不显示浏览器界面
        //options.AddArgument("--headless");
        //安全模式启动
        //options.AddArgument("-safe-mode");
        //禁止js
        //options.SetPreference("javascript.enabled", false);
        //本地代理
        //options.AddArgument("--proxy--server=127.0.0.1:8080");
        //禁止加载css样式
        //options.SetPreference("permissions.default.stylesheet", 2);
        //设置隐身模式
        //options.AddArgument("--private");
        //禁用缓存
        options.setCapability("network.http.use-cache", true);
        options.setCapability("browser.cache.memory.enable", true);
        options.setCapability("browser.cache.disk.enable", true);
        //options.setCapability("browser.sessionhistory.max_total_viewers", 3);
        options.setCapability("network.dns.disableIPv6", true);
        //options.setCapability("Content.notify.interval", 750000);
        //options.setCapability("content.notify.backoffcount", 3);
        options.setCapability("network.http.pipelining", true);
        options.setCapability("network.http.proxy.pipelining", true);
        //options.setCapability("network.http.pipelining.maxrequests", 32);
        options.setCapability("acceptInsecureCerts", false);
        FirefoxDriver driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        driver.get(url);
        //WebElement detailinfo = driver.findElement(By.ByCssSelector.className("qkxq-content"));
        WebElement detailinfo = driver.findElement(By.ByCssSelector.className("detailinfo"));

        System.out.println(driver.getPageSource());

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(driver);

        ImageIO.write(screenshot.getImage(), "png", new File("C:\\Users\\mapleaf\\维普.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", baos);
        byte[] bytes = baos.toByteArray();

        driver.manage().timeouts().implicitlyWait(Duration.of(30, TimeUnit.SECONDS.toChronoUnit()));
        Path printPage = Paths.get("C:\\Users\\mapleaf\\维普.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));


        /* Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeScript(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\维普.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }

    @GetMapping("/pp4")
    public GXResultUtils<Dict> pp4() throws IOException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\webdriver\\chromedriver.exe");
        String url = "https://navi.cnki.net/knavi/journals/YXJI/detail?uniplatform=NZKPT";
        //String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--screenshot");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--allowed-ips");
        //chromeOptions.addArguments("blink-settings=imagesEnabled=false", "--disable-gpu");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--windows-size=1920,1080");
        chromeOptions.setHeadless(true);
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.get(url);
        System.out.println(driver.getPageSource());


        Path printPage = Paths.get("C:\\Users\\mapleaf\\知网.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));

        /*
         Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeCdpCommand(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\aaaa.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }


    @GetMapping("/pp5")
    public GXResultUtils<Dict> pp5() throws IOException {
        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.gecko.driver", "C:\\tools\\webdriver\\geckodriver.exe");
        //String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        //String url = "http://www.cqvip.com/qikan/Detail.aspx?gch=80222X&years=2022&num=24";
        String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        FirefoxOptions options = new FirefoxOptions();
        //firefoxOptions.addArguments("--screenshot");
        //options.addArguments("--headless");
        options.addArguments("--incognito");
        //options.addArguments("--no-sandbox", "--disable-gpu");
        //firefoxOptions.addArguments("blink-settings=imagesEnabled=false", "--disable-gpu");
        //firefoxOptions.addArguments("--windows-size=1920,1080");
        //firefoxOptions.setHeadless(true);
        //options.addArguments("--width=2560", "--height=1440");
        //禁止GPU渲染
        options.addArguments("--disable-gpu");
        //忽略错误
        options.addArguments("ignore-certificate-errors");
        //禁止浏览器被自动化的提示
        options.addArguments("--disable-infobars");
        //找了十几天的一句代码，反爬关键：window.navigator.webdrive值=false*********************
        options.setCapability("dom.webdriver.enabled", false);
        //禁止加载图片
        //options.SetPreference("permissions.default.image", 2);
        //不显示浏览器界面
        //options.AddArgument("--headless");
        //安全模式启动
        //options.AddArgument("-safe-mode");
        //禁止js
        //options.SetPreference("javascript.enabled", false);
        //本地代理
        //options.AddArgument("--proxy--server=127.0.0.1:8080");
        //禁止加载css样式
        //options.SetPreference("permissions.default.stylesheet", 2);
        //设置隐身模式
        //options.AddArgument("--private");
        //禁用缓存
        options.setCapability("network.http.use-cache", false);
        options.setCapability("browser.cache.memory.enable", false);
        options.setCapability("browser.cache.disk.enable", false);
        options.setCapability("browser.sessionhistory.max_total_viewers", 3);
        options.setCapability("network.dns.disableIPv6", true);
        //options.setCapability("Content.notify.interval", 750000);
        //options.setCapability("content.notify.backoffcount", 3);
        options.setCapability("network.http.pipelining", true);
        options.setCapability("network.http.proxy.pipelining", true);
        options.setCapability("network.http.pipelining.maxrequests", 32);
        FirefoxDriver driver = new FirefoxDriver(options);
        //driver.manage().window().setSize(new Dimension(1920 , 1080));
        driver.manage().timeouts().implicitlyWait(Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        driver.get(url);
        WebElement detailinfo = driver.findElement(By.ByCssSelector.className("detailInfo"));
        System.out.println(driver.getPageSource());

        driver.manage().timeouts().implicitlyWait(Duration.of(30, TimeUnit.SECONDS.toChronoUnit()));
        Path printPage = Paths.get("C:\\Users\\mapleaf\\万方.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));


        /* Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeScript(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\维普.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }

    @GetMapping("/pp6")
    public GXResultUtils<Dict> pp6() throws IOException {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\webdriver\\chromedriver.exe");
        //String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        String url = "http://www.qikan.com.cn/newarticleinfo/dzwz202233260.html";

        ChromeOptions chromeOptions = new ChromeOptions();
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
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        driver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(120, TimeUnit.SECONDS.toChronoUnit()));
        WebElement detailinfo = driver.findElement(By.className("bg-gray"));
        //webDriverWait.until(ExpectedConditions.visibilityOf(detailinfo));
        System.out.println(driver.getPageSource());

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(driver);

        ImageIO.write(screenshot.getImage(), "png", new File("C:\\Users\\mapleaf\\龙源.png"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", baos);
        byte[] bytes = baos.toByteArray();

       /* WebElement detailinfo = driver.findElement(By.ByCssSelector.className("qkxq-content"));
        System.out.println(driver.getPageSource());
*/

        Path printPage = Paths.get("C:\\Users\\mapleaf\\龙源.pdf");
        Pdf print = driver.print(new PrintOptions());

        Files.write(printPage, OutputType.BYTES.convertFromBase64Png(print.getContent()));

        /*
         Map<String, Object> params = new HashMap<>();
        String command = "Page.printToPDF";
        Map<String, Object> output = driver.executeCdpCommand(command, params);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("e:\\aaaa.pdf");
            byte[] byteArray = java.util.Base64.getDecoder().decode((String) output.get("data"));
            fileOutputStream.write(byteArray);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        driver.quit();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }


    @GetMapping("/pp7")
    public GXResultUtils<Dict> pp7() {
        String driverPath = "E:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe";
        //String url = "https://d.wanfangdata.com.cn/periodical/pp202222001";
        String url = "https://mp.weixin.qq.com/s/4qMNsnb_UbaVfx5bo_HrLQ";
        PhantomJSDriver phantomJSDriver = SeleniumDriverUtils.phantomJSDriver(driverPath);
        String js_link_dialog = SeleniumCrawlerUtils.getPDFByPhantomJSDriver(phantomJSDriver, url, By.className("profile_arrow_wrp"), "e:\\kkk.pdf", 120);
        System.out.println(js_link_dialog);
        phantomJSDriver.close();
        return GXResultUtils.ok(Dict.create().set("username", "PPP"));
    }
}
