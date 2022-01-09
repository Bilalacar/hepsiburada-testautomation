package com.hepsiburada.basepages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hepsiburada.data.DataFinder;
import com.hepsiburada.data.GetData;
import com.hepsiburada.data.GetData.*;
import com.hepsiburada.utility.*;

public class SeleniumAbstractPage extends AbstractBase {

	public WebDriver driver;
	protected WebDriverWait wait, waitZero, waitLoader;

	public SeleniumAbstractPage(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT));
		this.waitZero = new WebDriverWait(driver, Duration.ofSeconds(0));
		this.waitLoader = new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));
	}

	/**
	 * navigate to url
	 */
	public void navigateTo(Url url) {

		try {
			driver.get(DataFinder.getUrl(url));
			driver.manage()
			      .timeouts()
			      .pageLoadTimeout(Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));
			LogPASS("Web sayfası açıldı: " + DataFinder.getUrl(url));
		} catch (Exception e) {
			log.error("Error while getting app url : " + e);
			LogFAIL("Error while getting app url : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method to find element by cssSelector
	 * 
	 * @param by
	 * @param index
	 * @return A WebElement, or an empty if nothing matches @
	 */
	public WebElement findElement(By by, int... index) {
		// driver.manage().timeouts().implicitlyWait(GetData.DEFAULT_WAIT,

		// TimeUnit.SECONDS);

		WebElement element = null;
		untilElementAppear(by);
		try {
			if (index.length == 0)
				element = driver.findElement(by);
			else
				element = driver.findElements(by)
				                .get(index[0]);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].focus();", element);
			// wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			log.error("Error while clicking webelement : " + e);
			LogFAIL("Error while clicking webelement : " + e);

			throw new RuntimeException(e);
		}
		return element;
	}

	public WebElement waitForElementClickable(WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElement(By by, int... index) {

		waitLoaderBox();
		findElement(by, index);
	}

	public WebElement waitForElement(WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT)).until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElement(WebElement element, int seconds) {

		return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitLoaderBox() {

		waitLoaderBox(GetData.DEFAULT_WAIT_LOADERBOX);// 90
	}

	public void waitLoaderBox(int time) {

		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(0));
		if (driver.findElements(By.xpath("//div[starts-with(@class,'loader')]"))
		          .size() != 0) {
			driver.manage()
			      .timeouts()
			      .implicitlyWait(Duration.ofSeconds(time));
			driver.findElement(By.xpath("//div[@class='loader' and @style='display: none;']"));
			driver.findElement(By.xpath("//div[@class='loader-box' and @style='display: none;']"));
		}
		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));
	}

	/**
	 * Use this method to find elements by cssSelector
	 * 
	 * @param by
	 * @return A list of WebElements, or an empty if nothing matches
	 */
	protected List<WebElement> findElements(By by) {

		List<WebElement> webElements = null;
		untilElementAppear(by);
		try {
			webElements = driver.findElements(by);
		} catch (Exception e) {
			log.error("Error while listing webelements by css selector : " + e);
			LogFAIL("Error while listing webelements by css selector : " + e);
			log.info("Error while listing webelements by css selector : " + e);

			throw new RuntimeException(e);
		}
		return webElements;
	}

	/**
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index @
	 */
	public void click(By by, int... index) {

		WebElement element;
		try {
			element = findElement(by, index);
			String elemText = element.getText();
			element.click();
			LogPASS("Click Button : " + elemText);
		} catch (Exception e) {
			log.error("Error while clicking webelement : " + e);
			LogFAIL("Error while clicking webelement : " + e);
			log.info("Error while clicking webelement : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index @
	 */
	public void click(WebElement element) {

		String elemText = "";
		try {
			elemText = element.getText();
			element.click();
			LogPASS("Click Button : " + elemText);
		} catch (WebDriverException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
			element.click();
			LogPASS("Click Button : " + elemText);
		} catch (Exception e) {
			log.error("Error while clicking webelement : " + e);
			LogFAIL("Error while clicking webelement : " + e);
			log.info("Error while clicking webelement : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index @
	 */
	public void click(By by, boolean clickable) {

		try {
			if (!clickable)
				click(by);
			else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
				String elemText = elem.getText();
				elem.click();
				LogPASS("Click Button : " + elemText);
			}
		} catch (Exception e) {
			log.error("Error while clicking webelement : " + e);
			LogFAIL("Error while clicking webelement : " + e);
			log.info("Error while clicking webelement : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	public void sendKeys(By by, String text, boolean pressEnter, int... index) {

		WebElement element = null;
		String elemText = null;
		try {
			element = findElement(by, index);
			if (element.isEnabled()) {
				elemText = element.getText();
				element.clear();
				element.sendKeys(text);
				if (pressEnter) {
					waitLoaderBox();
					element.sendKeys(Keys.ENTER);
				}
			}
			LogPASS("Value : " + text + " - SendKeys : " + elemText);
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	public void sendKeys(WebElement element, String text, boolean pressEnter) {

		String elemText = null;
		try {
			if (element.isEnabled()) {
				elemText = element.getText();
				//				element.clear();
				element.sendKeys(text);
				if (pressEnter) {
					waitLoaderBox();
					element.sendKeys(Keys.ENTER);
				}
			}
			LogPASS("Value : " + text + " - SendKeys : " + elemText);
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	protected void sendKeys(By by, Keys key, int... index) {

		WebElement element = null;
		String elemText = null;
		try {
			element = findElement(by, index);
			if (element.isEnabled()) {
				elemText = element.getText();
				element.sendKeys(key);
			}
			LogPASS("Value : " + key.toString() + " - SendKeys : " + elemText);
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);

			throw new RuntimeException(e);
		}
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text @
	 */
	public void sendKeys(By by, String text, int... index) {

		sendKeys(by, text, false, index);
	}

	/**
	 * Use this method to simulate typing into an element if it is enable. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text @
	 */
	public void sendKeys(WebElement element, String text) {

		sendKeys(element, text, false);
	}

	public void selectCombobox(By by, String value) {

		WebElement element = findElement(by);
		String elemText = null;
		try {
			if (element.isEnabled()) {
				elemText = element.getText();
				Select selectBox = new Select(driver.findElement(by));
				selectBox.selectByValue(value);
			}
			LogPASS("Value : " + value + " - SelectComboBox : " + elemText);
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);

			throw new RuntimeException(e);
		}
	}

	public void selectCombobox(WebElement element, String value) {

		String elemText = null;
		try {
			if (element.isEnabled()) {
				elemText = element.getText();
				Select selectBox = new Select(element);
				selectBox.selectByValue(value);
			}
			LogPASS("Value : " + value + " - SelectComboBox : " + elemText);
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);

			throw new RuntimeException(e);
		}
	}

	public void moveToElement(By by) {

		try {
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(by);
			action.moveToElement(we)
			      .build()
			      .perform();
		} catch (Exception e) {
			log.error("Error while filling field : " + e);
			LogFAIL("Error while filling field : " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the visible (i.e. not hidden by CSS) innerText of this element.
	 * 
	 * @param by
	 * @param index
	 * @return The innerText of this element. @
	 */
	public String getTextOfElement(By by, int... index) {

		String text = null;
		untilElementAppear(by);

		try {
			if (index.length == 0)

				text = driver.findElement(by)
				             .getText();
			else
				text = driver.findElements(by)
				             .get(index[0])
				             .getText();
		} catch (Exception e) {
			log.error("Error while getting text of element : " + e);
			LogFAIL("Error while getting text of element : " + e);

			throw new RuntimeException(e);
		}
		return text;
	}

	@SuppressWarnings("finally")
	public String getTextOfElement(WebElement elem) {

		String text = null;
		try {
			text = elem.getText();
		} finally {
			return text;
		}
	}

	/**
	 * Wait until element appears
	 * 
	 * @param by
	 * @param index
	 */
	protected void untilElementAppear(By by) {

		try {
			// waitLoaderBox(90);// , 40
			// Thread.sleep(1000);
			// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			log.error("Error while waiting until element appears : " + e);
			LogFAIL("Error while waiting until element appears : " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Wait until element disappears
	 * 
	 * @param by //
	 */
	protected void untilElementDisappear(By by) {

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			log.error("Error while waiting until element disappears : " + e);
			LogFAIL("Error while waiting until element disappears : " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Return true if element exist, false otherwise.
	 * 
	 * @param by
	 * @param index
	 * @return True if element exists, false otherwise.
	 */
	public boolean isElementExist(List<WebElement> elem) {

		return isElementExist(elem, 15);
	}

	public boolean isElementExist(List<WebElement> elem, int timeSeconds) {

		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(timeSeconds));
		boolean isExist = !elem.isEmpty();
		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));

		return isExist;
	}

	public boolean isElementExist(By by) {

		return isElementExist(by, 15);
	}

	public boolean isElementExist(By by, int timeSeconds) {

		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(timeSeconds));
		boolean isExist = driver.findElements(by)
		                        .size() > 0;
		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));

		return isExist;
	}

	public boolean isElementExist(WebElement element) {

		return isElementExist(getByOfElement(element), 15);
	}

	public boolean isElementExist(WebElement element, int timeSeconds) {

		return isElementExist(getByOfElement(element), timeSeconds);
	}

	public String getProperty(By by, String expectedPropertyName, int... index) {

		WebElement elem;

		if (index.length == 0)
			elem = driver.findElement(by);
		else
			elem = driver.findElements(by)
			             .get(index[0]);

		return elem.getAttribute(expectedPropertyName);
	}

	public String getProperty(WebElement elem, String expectedPropertyName) {

		return elem.getAttribute(expectedPropertyName);
	}

	public void setValue(WebElement element, String value) {

		((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + value + "';", element);
	}

	public By getByOfElement(WebElement element) {

		try {
			driver.manage()
			      .timeouts()
			      .implicitlyWait(0, TimeUnit.SECONDS);
			String byString = element.toString();
			if (byString.contains(" -> "))
				byString = byString.split(" -> ")[1];
			else
				byString = byString.split("By.")[1];
			int byStringLenght = byString.length();
			String[] data = byString.substring(0, byStringLenght - 1)
			                        .split(": ");
			String locator = data[0];
			String term = "";
			for (int i = 1; i < data.length; i++)
				term += data[i] + ": ";
			term = term.substring(0, term.length() - 2);

			switch (locator) {
				case "xpath":
					return By.xpath(term);
				case "css selector":
					return By.cssSelector(term);
				case "id":
					return By.id(term);
				case "tag name":
					return By.tagName(term);
				case "name":
					return By.name(term);
				case "link text":
					return By.linkText(term);
				case "class name":
					return By.className(term);
			}
			throw new RuntimeException();
		} catch (Exception e) {
			log.error("getByOfElement hata aldı. Hata: " + e.getMessage());
			return null;
		} finally {
			driver.manage()
			      .timeouts()
			      .implicitlyWait(GetData.DEFAULT_WAIT, TimeUnit.SECONDS);
		}
	}
}
