/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.himanshu.qa.Util.FileUtil;
import com.himanshu.qa.TestBase.BaseTest;

public class BrowserUtil extends BaseTest {

	public static int checkResponseCode(String href) throws IOException {
		URL url = new URL(href);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.connect();
		int responseCode = http.getResponseCode();
		return responseCode;
	}

	public static void scrollDown(int x) {
		try {
			Robot robot = new Robot();
			robot.mouseWheel(x);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void scrollUp(int x) {
		try {
			Robot robot = new Robot();
			robot.mouseWheel(-x);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteIframeCookie() {
		Calendar expiryDate = Calendar.getInstance();
		expiryDate.add(Calendar.YEAR, 1);
		Date date = expiryDate.getTime();
		Cookie cookie = new Cookie("s", "2", ".snapdeal.com", "/", date, false);
		driver.manage().addCookie(cookie);
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void takeScreenshotByElement(WebElement el, String method)
			throws IOException {
		Date date = new Date();
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-mm-dd");
		System.out.println(dt1.format(date));
		System.out.println("current date :: " + date);
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = null;
		try {
			fullImg = ImageIO.read(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Point point = el.getLocation();
		int eleWidth = el.getSize().getWidth();
		int eleHeight = el.getSize().getHeight();
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(),
				point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", screenshot);
		FileUtils.copyFile(screenshot,
				new File(System.getProperty("user.dir") + FileUtil.separator
						+ FileUtil.getConstantValue("ScreenshotPath")
						+ FileUtil.separator + method + ".png"));
	}

	public static void takeScreenshot(String method) throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + FileUtil.separator
						+ FileUtil.getConstantValue("ScreenshotPath")
						+ FileUtil.separator + method + ".png"));
	}

	public static WebDriver invokeBrowser(String browser, String os) {

		String path = null;
		if (browser.equalsIgnoreCase("firefox")) {
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("firefox_profile");
			profile.setPreference("extensions.checkCompatibility", false);
			profile.setAcceptUntrustedCertificates(true);
			profile.setAssumeUntrustedCertificateIssuer(false);
			dc.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(profile);
			driver = new EventFiringWebDriver(driver);
			
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			path = System.getProperty("user.dir") + FileUtil.separator
					+ "UIAutomationWap" + FileUtil.separator
					+ FileUtil.getConstantValue("WindowsChromeDriver");
			if (os.equalsIgnoreCase("win")) {

				File f = new File(path);
				if (f.exists()) {
					System.setProperty("webdriver.chrome.driver", path);
				} else {

					System.setProperty(
							"webdriver.chrome.driver",
							System.getProperty("user.dir")
									+ FileUtil.separator
									+ FileUtil
											.getConstantValue("WindowsChromeDriver"));
				}
			}
			// download chromedriver for mac
			else if (os.equalsIgnoreCase("mac")) {

				/*File f = new File(path);
				if (f.exists()) {
					System.setProperty("webdriver.chrome.driver", path);
				} else {

					System.setProperty(
							"webdriver.chrome.driver",
							System.getProperty("user.dir")
									+ FileUtil.separator
									+ FileUtil
											.getConstantValue("WindowsChromeDriver"));
				}
*/
			}

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			LoggingPreferences loggingprefs = new LoggingPreferences();
			loggingprefs.enable(LogType.BROWSER, Level.ALL);
			capabilities.setCapability(CapabilityType.LOGGING_PREFS,
					loggingprefs);
			driver = new ChromeDriver(capabilities);

			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments(
			 * "--user-agent=Mozilla/5.0 (Linux; U; Android 4.0.3; ko-kr; LG-L160L Build/IML74K) AppleWebkit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
			 * ); driver = new ChromeDriver(options);
			 */
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + FileUtil.separator
							+ FileUtil.getConstantValue("IEDriver"));
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		driver.manage().window().maximize();
		return driver;

	}

}
