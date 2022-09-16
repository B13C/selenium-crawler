package cn.gaple.crawler.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.maple.core.framework.exception.GXBusinessException;
import cn.maple.core.framework.util.GXResultUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SeleniumCrawlerUtils {
    private SeleniumCrawlerUtils() {

    }

    /**
     * 获取指定URL的html
     *
     * @param chromeDriver 驱动对象
     * @param url          待获取的URL
     * @param locator      期望继续执行的条件
     * @param timeOut      超时时间 单位 秒
     * @return HTML
     */
    public static String getHtmlByChromeWebDriver(ChromeDriver chromeDriver, String url, By locator, Integer timeOut) {
        chromeDriver.manage().window().setSize(new Dimension(1920, 1080));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        chromeDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = chromeDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        return chromeDriver.getPageSource();
    }

    /**
     * 获取指定URL的html
     *
     * @param firefoxDriver 驱动对象
     * @param url           待获取的URL
     * @param locator       期望继续执行的条件
     * @param timeOut       超时时间 单位 秒
     * @return HTML
     */
    public static String getHtmlByFirefoxWebDriver(FirefoxDriver firefoxDriver, String url, By locator, Integer timeOut) {
        firefoxDriver.manage().window().setSize(new Dimension(1920, 1080));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        firefoxDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(firefoxDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = firefoxDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        return firefoxDriver.getPageSource();
    }

    /**
     * 获取指定URL的html
     *
     * @param phantomJSDriver 驱动对象
     * @param url             待获取的URL
     * @param locator         期望继续执行的条件
     * @param timeOut         超时时间 单位 秒
     * @return HTML
     */
    public static String getHtmlByPhantomJSDriver(PhantomJSDriver phantomJSDriver, String url, By locator, Integer timeOut) {
        phantomJSDriver.manage().window().setSize(new Dimension(1920, 1080));
        phantomJSDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        phantomJSDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(phantomJSDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = phantomJSDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        return phantomJSDriver.getPageSource();
    }

    /**
     * 获取截屏数据
     *
     * @param chromeDriver       驱动对象
     * @param url                待截屏URL
     * @param locator            期望继续执行的条件
     * @param screenshotSavePath 截屏图片保存路径
     * @param timeOut            超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getScreenshotByChromeDriver(ChromeDriver chromeDriver, String url, By locator, String screenshotSavePath, Integer timeOut) {
        chromeDriver.manage().window().setSize(new Dimension(1920, 1080));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        chromeDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = chromeDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(chromeDriver);
        try {
            ImageIO.write(screenshot.getImage(), "png", new File(screenshotSavePath));
        } catch (IOException e) {
            throw new GXBusinessException("获取截屏数据失败", e);
        }
        return chromeDriver.getPageSource();
    }

    /**
     * 获取截屏数据
     *
     * @param phantomJSDriver    驱动对象
     * @param url                待截屏URL
     * @param locator            期望继续执行的条件
     * @param screenshotSavePath 截屏图片保存路径
     * @param timeOut            超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getScreenshotByPhantomJSDriver(PhantomJSDriver phantomJSDriver, String url, By locator, String screenshotSavePath, Integer timeOut) {
        phantomJSDriver.manage().window().setSize(new Dimension(1920, 1080));
        phantomJSDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        phantomJSDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(phantomJSDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = phantomJSDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(phantomJSDriver);
        try {
            ImageIO.write(screenshot.getImage(), "png", new File(screenshotSavePath));
        } catch (IOException e) {
            throw new GXBusinessException("获取截屏数据失败", e);
        }
        return phantomJSDriver.getPageSource();
    }

    /**
     * 获取截屏数据
     *
     * @param firefoxDriver      驱动对象
     * @param url                待截屏URL
     * @param locator            期望继续执行的条件
     * @param screenshotSavePath 截屏图片保存路径
     * @param timeOut            超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getScreenshotByFirefoxDriver(FirefoxDriver firefoxDriver, String url, By locator, String screenshotSavePath, Integer timeOut) {
        firefoxDriver.manage().window().setSize(new Dimension(1920, 1080));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        firefoxDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(firefoxDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = firefoxDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(firefoxDriver);
        try {
            ImageIO.write(screenshot.getImage(), "png", new File(screenshotSavePath));
        } catch (IOException e) {
            throw new GXBusinessException("获取截屏数据失败", e);
        }
        return firefoxDriver.getPageSource();
    }

    /**
     * 取PDF数据
     *
     * @param chromeDriver 驱动对象
     * @param url          待获取的PDF的URL
     * @param locator      期望继续执行的条件
     * @param pdfSavePath  截屏图片保存路径
     * @param timeOut      超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getPDFByChromeDriver(ChromeDriver chromeDriver, String url, By locator, String pdfSavePath, Integer timeOut) {
        chromeDriver.manage().window().setSize(new Dimension(1920, 1080));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        chromeDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = chromeDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(chromeDriver);
        try {
            screenshotConvertPDF(screenshot, pdfSavePath);
        } catch (DocumentException | IOException e) {
            throw new GXBusinessException("获取PDF数据失败", e);
        }
        return chromeDriver.getPageSource();
    }

    /**
     * 取PDF数据
     *
     * @param phantomJSDriver 驱动对象
     * @param url             待获取的PDF的URL
     * @param locator         期望继续执行的条件
     * @param pdfSavePath     截屏图片保存路径
     * @param timeOut         超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getPDFByPhantomJSDriver(PhantomJSDriver phantomJSDriver, String url, By locator, String pdfSavePath, Integer timeOut) {
        phantomJSDriver.manage().window().setSize(new Dimension(1920, 1080));
        phantomJSDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        phantomJSDriver.get(url);
           /* WebDriverWait webDriverWait = new WebDriverWait(phantomJSDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
            WebElement locatorElement = phantomJSDriver.findElement(locator);
            webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));*/
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(phantomJSDriver);
        try {
            screenshotConvertPDF(screenshot, pdfSavePath);
        } catch (DocumentException | IOException e) {
            throw new GXBusinessException("获取PDF数据失败", e);
        }
        return phantomJSDriver.getPageSource();
    }

    /**
     * 取PDF数据
     *
     * @param firefoxDriver 驱动对象
     * @param url           待获取的PDF的URL
     * @param locator       期望继续执行的条件
     * @param pdfSavePath   截屏图片保存路径
     * @param timeOut       超时时间 单位 秒
     * @return HTML 文件
     */
    public static String getPDFByFirefoxDriver(FirefoxDriver firefoxDriver, String url, By locator, String pdfSavePath, Integer timeOut) {
        firefoxDriver.manage().window().setSize(new Dimension(1920, 1080));
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        firefoxDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(firefoxDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = firefoxDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1200)).takeScreenshot(firefoxDriver);
        try {
            screenshotConvertPDF(screenshot, pdfSavePath);
        } catch (DocumentException | IOException e) {
            throw new GXBusinessException("获取PDF数据失败", e);
        }
        return firefoxDriver.getPageSource();
    }


    /**
     * 将截图转换为PDF
     *
     * @param screenshot 截图对象
     * @param savePath   PDF需要保存的位置
     */
    private static void screenshotConvertPDF(Screenshot screenshot, String savePath) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(screenshot.getImage(), "png", baos);
        byte[] bytes = baos.toByteArray();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savePath));
        document.setPageSize(new Rectangle(screenshot.getImage().getWidth() + 60.0f, screenshot.getImage().getHeight() + 100.0f));

        document.open();

        Image instance = Image.getInstance(bytes);

        instance.scaleAbsolute(screenshot.getImage().getWidth(), screenshot.getImage().getHeight());
        document.add(instance);
        document.close();
        writer.close();
    }

    /**
     * 获取指定URL的内容 并将其保存为MHTML文件
     *
     * @param chromeDriver  驱动对象
     * @param url           待获取内容的URL
     * @param locator       定位元素
     * @param mHtmlSavePath 保存的地址
     * @param timeOut       超时时间
     * @return 文件路径
     */
    public static String saveMHTMLByChrome(ChromeDriver chromeDriver, String url, By locator, String mHtmlSavePath, Integer timeOut) {
        chromeDriver.manage().window().setSize(new Dimension(1920, 1080));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        chromeDriver.get(url);
        WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.of(timeOut, TimeUnit.SECONDS.toChronoUnit()));
        WebElement locatorElement = chromeDriver.findElement(locator);
        webDriverWait.until(ExpectedConditions.visibilityOf(locatorElement));
        Map<String, Object> data = chromeDriver.executeCdpCommand("Page.captureSnapshot", Dict.create());
        FileUtil.writeString(data.get("data").toString(), mHtmlSavePath, StandardCharsets.UTF_8);
        return mHtmlSavePath;
    }
}
