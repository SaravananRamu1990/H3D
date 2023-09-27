package org.atmecs.h3d.reusables;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.atmecs.h3d.constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelperClass {

	protected static WebDriver driver;
	public static Robot virtualKey;

	static Logger logger = Logger.getLogger(HelperClass.class);

	/**
	 * This method initialize the browser
	 * @throws InterruptedException 
	 */
//	@BeforeTest
//	@Parameters("browser")
//	public void selectBrowser( String browser)
	
	@Test
	@BeforeTest
	@Parameters("browser")
	public void selectBrowser(String browser) throws InterruptedException, AWTException {
	    PropertyConfigurator.configure("D:\\H3DUITESTING\\H3D\\log4j.properties");

		if (readConfig("ChromeBrowser").equals(browser)) {
		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    logger.info("Thread ID of Chrome Browser: " + Thread.currentThread().getId());
		    driver.manage().window().maximize();
		    getUrl();
		    
		    // Log an info message for selecting Chrome browser
		    logger.info("Selected Chrome Browser");
		} else if (readConfig("EdgeBrowser").equals(browser)) {
		    WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
		    logger.info("Thread ID of Edge Browser: " + Thread.currentThread().getId());
		    driver.manage().window().maximize();
		    getUrl();
		    
		    // Log an info message for selecting Edge browser
		    logger.info("Selected Edge Browser");
		} else if (readConfig("FirefoxBrowser").equals(browser)) {
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		    logger.info("Thread ID of Firefox Browser: " + Thread.currentThread().getId());
		    driver.manage().window().maximize();
		    getUrl();
		    
		    // Log an info message for selecting Firefox browser
		    logger.info("Selected Firefox Browser");
		} else {
		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		    System.out.println("Thread ID of Chrome Browser: " + Thread.currentThread().getId());
		    driver.manage().window().maximize();
		    getUrl();
		    
		    // Log an info message for selecting the default Chrome browser
		    logger.info("Selected Default Chrome Browser");
		}
	}
	
	public boolean displayed(By locator) {
	    try {
	        WebElement element = findWebElement(locator);
	        return element.isDisplayed();
	    } catch (NoSuchElementException noSuchElementException) {
	        // Log an error message for NoSuchElementException
	        logger.error("Element not found while checking if it is displayed.", noSuchElementException);
	        return false;
	    } catch (StaleElementReferenceException staleElementReferenceException) {
	        // Log an error message for StaleElementReferenceException
	        logger.error("Stale Element Reference Exception occurred while checking if the element is displayed.", staleElementReferenceException);
	        return false;
	    } catch (Exception exception) {
	        // Log a generic error message for any other exceptions that may occur
	        logger.error("An error occurred while checking if the element is displayed.", exception);
	        return false;
	    }
	}

	/**
	 * This keyword navigate to the url to be automated
	 */
//	public void getUrl() {
//	    try {
//	        driver.get(readConfig("url"));
//	        Thread.sleep(3000);
//	    } catch (InterruptedException interruptedException) {
//	        // Log an error message for InterruptedException
//	        logger.error("An InterruptedException occurred while getting the URL.", interruptedException);
//	        // Restore the interrupted status
//	        Thread.currentThread().interrupt();
//	    } catch (Exception exception) {
//	        // Log a generic error message for any other exceptions that may occur
//	        logger.error("An error occurred while getting the URL.", exception);
//	    }
//	}
	public void getUrl() {
	    try {
	        String url = readConfig("url");
	        driver.get(url);
	    } catch (WebDriverException e) {
	        logger.error("An error occurred while navigating to the URL.", e);
	    }
	}


	/**
	 * This keyword helps to find a web element
	 * 
	 * @param locator: is passed as a parameter to find the web element
	 * @return : It returns a web element
	 */
	public WebElement findWebElement(By locator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(locator);
	        logger.info("Element found with locator: " + locator.toString());
	    } catch (Exception exception) {
	        logger.error("Element not found with locator: " + locator.toString());
	    }
	    return webElement;
	}


	/**
	 * This method returns a webelement by relative locator below
	 * 
	 * @param actualElementLocator    : This locator is the actual webelement need
	 *                                to find
	 * @param referenceElementLocator : This locator is the reference for the
	 *                                element to be found
	 * @return
	 */
	public WebElement belowRelativeLocator(By actualElementLocator, By referenceElementLocator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(with(actualElementLocator).below(referenceElementLocator));
	        logger.info("Element found below reference element using locator: " + actualElementLocator.toString());
	    } catch (Exception exception) {
	        logger.error("Element not found below reference element using locator: " + actualElementLocator.toString());
	    }
	    return webElement;
	}




	/**
	 * This method returns a webelement by relative locator below
	 * 
	 * @param actualElementLocator    : This locator is the actual webelement need
	 *                                to find
	 * @param ReferenceElementLocator : This locator is the reference for the
	 *                                element to be found
	 * @return
	 */
	public WebElement aboveRelativeLocator(By actualElementLocator, By referenceElementLocator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(with(actualElementLocator).above(referenceElementLocator));
	        logger.info("Element found above reference element using locator: " + actualElementLocator.toString());
	    } catch (Exception exception) {
	        logger.error("Element not found above reference element using locator: " + actualElementLocator.toString());
	    }
	    return webElement;
	}

	/**
	 * This method returns a webelement by relative locator below
	 * 
	 * @param actualElementLocator    : This locator is the actual webelement need
	 *                                to find
	 * @param ReferenceElementLocator : This locator is the reference for the
	 *                                element to be found
	 * @return
	 */
	public WebElement leftRelativeLocator(By actualElementLocator, By referenceElementLocator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(with(actualElementLocator).toLeftOf(referenceElementLocator));
	        // Log an info message indicating that the element was successfully found
	        logger.info("Element found to the left of the reference element using locator: " + actualElementLocator.toString());
	    } catch (Exception exception) {
	        // Log an error message indicating that the element was not found
	        logger.error("Element not found to the left of the reference element using locator: " + actualElementLocator.toString());
	        // You can rethrow the exception or take other actions as needed
	    }
	    return webElement;
	}
		/**
	 * This method returns a webelement by relative locator below
	 * 
	 * @param actualElementLocator    : This locator is the actual webelement need
	 *                                to find
	 * @param ReferenceElementLocator : This locator is the reference for the
	 *                                element to be found
	 * @return
	 */
	public WebElement rightRelativeLocator(By actualElementLocator, By referenceElementLocator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(with(actualElementLocator).toRightOf(referenceElementLocator));
	        // Log an info message indicating that the element was successfully found
	        logger.info("Element found to the right of the reference element using locator: " + actualElementLocator.toString());
	    } catch (Exception exception) {
	        // Log an error message indicating that the element was not found
	        logger.error("Element not found to the right of the reference element using locator: " + actualElementLocator.toString());
	        // You can rethrow the exception or take other actions as needed
	    }
	    return webElement;
	}

	/**
	 * This method returns a webelement by relative locator below
	 * 
	 * @param actualElementLocator    : This locator is the actual webelement need
	 *                                to find
	 * @param ReferenceElementLocator : This locator is the reference for the
	 *                                element to be found
	 * @return
	 */
	public WebElement nearRelativeLocator(By actualElementLocator, By referenceElementLocator) {
	    WebElement webElement = null;
	    try {
	        webElement = driver.findElement(with(actualElementLocator).near(referenceElementLocator));
	        
	        // Log an info message indicating that the element was successfully found
	        logger.info("Element found near the reference element using locator: " + actualElementLocator.toString());
	    } catch (Exception exception) {
	        // Log an error message indicating that the element was not found
	        logger.error("Element not found near the reference element using locator: " + actualElementLocator.toString());
	        // You can rethrow the exception or take other actions as needed
	    }
	    return webElement;
	}

	/**
	 * This keyword helps to find a web elements
	 * 
	 * @param locator: is passed as a parameter to find the web element
	 * @return : It returns a list of web element
	 */
	public List<WebElement> findWebElements(By locator, WebDriver driver) {
	    try {
	        List<WebElement> elements = driver.findElements(locator);
	        
	        // Log an info message indicating the successful retrieval of elements
	        logger.info("Found " + elements.size() + " elements with locator: " + locator.toString());
	        
	        return elements;
	    } catch (Exception exception) {
	        // Log an error message for the exception
	        logger.error("An error occurred while finding elements with locator: " + locator.toString(), exception);
	        
	        // You can rethrow the exception or take other actions as needed
	        return new ArrayList<>();
	    }
	}

	/**
	 * This keyword helps reading a config file
	 * 
	 * @param key: helps in accessing the value of a given key
	 * @return
	 */
	public String readConfig(String key) {
	    String value = null;
	    try {
	        Properties config = new Properties();
	        config.load(new FileReader(Constants.configPath));
	        value = config.getProperty(key);

	        // Log an info message indicating successful configuration value retrieval
	        logger.info("Retrieved configuration value for key: " + key + ", Value: " + value);
	    } catch (FileNotFoundException fileNotFoundException) {
	        // Log an error message for the file not found exception
	        logger.error("File not found at location: " + Constants.configPath, fileNotFoundException);
	    } catch (IOException ioException) {
	        // Log an error message for input mismatch or other IO exception
	        logger.error("Error while reading configuration file: " + Constants.configPath, ioException);
	    }
	    return value;
	}

	/**
	 * This keyword allow to click, clear and write a text in the text field
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @param data:    This parameter is writeen in the text box
	 */

	public void clearAndWriteText(WebElement element, String data) {
	    try {
	        element.click();
	        element.clear();
	        element.sendKeys(data);

	        // Log an info message indicating successful text entry
	        logger.info("Entered text '" + data + "' in the field.");
	    } catch (Exception exception) {
	        // Log an error message for any exceptions that occur during text entry
	        logger.error("An error occurred while entering text '" + data + "' in the field.", exception);
	    }
	}


	/**
	 * This keyword allow to click, clear and write a text in the text field
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @param data:    This parameter is writeen in the text box
	 */
	public void writeText(WebElement element, String data) {
	    try {
	        element.sendKeys(data);

	        // Log an info message indicating successful text entry
	        logger.info("Entered text '" + data + "' in the field.");
	    } catch (Exception exception) {
	        // Log an error message for any exceptions that occur during text entry
	        logger.error("An error occurred while entering text '" + data + "' in the field.", exception);
	    }
	}
	/**
	 * This keyword allow to click the button
	 * 
	 * @param element: It is passed to locate the exact web element
	 */
	public void clickButton(WebElement element) {
	    try {
	        element.click();

	        // Log an info message indicating successful button click
	        logger.info("Clicked on the element.");
	    } catch (Exception exception) {
	        // Log an error message for any exceptions that occur during the click
	        logger.error("An error occurred while clicking the element.", exception);
	    }
	}

	/**
	 * This keyword allow to click the Link
	 * 
	 * @param data: The data is passed to locate the exact web element
	 */
	public void clickLink(WebDriver driver, String data) {
	    try {
	        driver.findElement(By.linkText(data)).click();

	        // Log an info message indicating successful link click
	        logger.info("Clicked on the link with text: " + data);
	    } catch (Exception exception) {
	        // Log an error message for any exceptions that occur during the click
	        logger.error("An error occurred while clicking the link with text: " + data, exception);
	    }
	}
	/**
	 * This keyword clears the value of specified Edit box.
	 *
	 * @param element: It is passed to locate the exact web element
	 */
	public void clearEditField(WebElement element) {
	    try {
	        element.clear();

	        // Log an info message indicating successful clearing of the text box
	        logger.info("Cleared the text box.");
	    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
	        // Log an error message for a "NoSuchElementException" if the element is not found
	        logger.error("The element was not found for clearing the text box.", noSuchElementException);
	    } catch (org.openqa.selenium.InvalidElementStateException invalidElementStateException) {
	        // Log an error message for an "InvalidElementStateException" if the element cannot be interacted with
	        logger.error("Cannot clear the text box due to an invalid element state.", invalidElementStateException);
	    } catch (Exception exception) {
	        // Log a generic error message for any other exceptions that occur during clearing
	        logger.error("An error occurred while clearing the text box.", exception);
	    }
	}

	/**
	 * This keyword is used to get the title of the page
	 * 
	 * @return
	 */
	public String getPageTitle() {
	    try {
	        return driver.getTitle();
	    } catch (Exception exception) {
	        // Log an error message for any exceptions that occur while fetching the title
	        logger.error("An error occurred while fetching the page title.", exception);
	        return null;
	    }
	}


	/**
	 * This keyword helps is used to get the text of a specfied locator
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @return
	 */
	public String getObjectText(WebElement element) {
	    try {
	        return element.getText();
	    } catch (NoSuchElementException e) {
	        logger.error("The WebElement was not found while trying to get the text.", e);
	        return null;
	    } catch (StaleElementReferenceException e) {
	        logger.error("The WebElement is no longer attached to the DOM while trying to get the text.", e);
	        return null;
	    } catch (Exception e) {
	        logger.error("An error occurred while getting the text from the WebElement.", e);
	        return null;
	    }
	}


	/**
	 * This method is used to get Internal text
	 *
	 * @param element: It is passed to locate the exact web element
	 * @return
	 */
	public String getObjectValue(WebElement element) {
	    try {
	        return element.getAttribute("value");
	    } catch (org.openqa.selenium.NoSuchElementException noSuchElementException) {
	        // Log an error message for a "NoSuchElementException" if the element is not found
	        logger.error("The element was not found while trying to get its value.", noSuchElementException);
	    } catch (org.openqa.selenium.StaleElementReferenceException staleElementReferenceException) {
	        // Log an error message for a "StaleElementReferenceException" if the element is no longer attached to the DOM
	        logger.error("The element is stale while trying to get its value.", staleElementReferenceException);
	    } catch (org.openqa.selenium.WebDriverException webDriverException) {
	        // Log a generic error message for any other WebDriverException
	        logger.error("An error occurred while trying to get the element's value.", webDriverException);
	    }
	    
	    // Return null if any exception occurred
	    return null;
	}

	/**
	 * This keyword compares the actual string with the expected string
	 * 
	 * @param actual    : Actual text is passed as parameter
	 * @param expected: Expected text is passed as parameter
	 * @return: It returns a boolean value
	 */
	public boolean compareString(String actual, String expected) {
	    if (actual.equalsIgnoreCase(expected)) {
	        // Log an info message indicating that the strings match
	        logger.info(actual + " matches " + expected);
	        return true;
	    } else {
	        // Log an error message indicating that the strings do not match
	        logger.error(actual + " does not match " + expected);
	        return false;
	    }
	}


	/**
	 * This keyword verifies whether the actual string contains the expected string
	 * 
	 * @param actual    : Actual text is passed as parameter
	 * @param expected: Expected text is passed as parameter
	 * @return: It returns a boolean value
	 */
	public boolean containsString(String actual, String expected) {
	    if (actual.contains(expected)) {
	        // Log an info message indicating that the actual string contains the expected string
	        logger.info(actual + " contains " + expected);
	        return true;
	    } else {
	        // Log an error message indicating that the actual string does not contain the expected string
	        logger.error(actual + " does not contain " + expected);
	        return false;
	    }
	}


	/**
	 * This keyword waits for a page to load
	 */
	public void waitForPageLoad() {
	    try {
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.waitDuration));
	    } catch (TimeoutException timeoutException) {
	        // Log an error message for the page load timeout exception
	        logger.error("Page load timed out after " + Constants.waitDuration + " seconds.", timeoutException);
	    }
	}

	/**
	 * This keyword waits for the presence of a element in the page
	 * 
	 * @param locator: It is passed to locate the exact web element
	 */
	
	public void waitForElementPresence(By locator) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.waitDuration));
	        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	        // Log an info message indicating successful element presence
	        logger.info("Element located by: " + locator.toString() + " is present.");
	    } catch (TimeoutException timeoutException) {
	        // Log an error message for the timeout exception
	        logger.error("Timeout waiting for element presence located by: " + locator.toString(), timeoutException);
	    }
	}


	/**
	 * This keyword waits for the visibilty of a element in the page
	 * 
	 * @param locator: It is passed to locate the exact web element
	 */
	public void waitForElementVisibility(By locator) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.waitDuration));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	        // Log an info message indicating successful element visibility
	        logger.info("Element located by: " + locator.toString() + " is visible.");
	    } catch (TimeoutException timeoutException) {
	        // Log an error message for the timeout exception
	        logger.error("Timeout waiting for visibility of element located by: " + locator.toString(), timeoutException);
	    }
	}
	/**
	 * This keyword waits for the specified web element to be clickable
	 * 
	 * @param locator: It is passed to locate the exact web element
	 */
	public void waitForClickable(By locator) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.waitDuration));
	        wait.until(ExpectedConditions.elementToBeClickable(locator));
	        
	        // Log an info message indicating successful element clickability
	        logger.info("Element located by: " + locator.toString() + " is clickable.");
	    } catch (TimeoutException timeoutException) {
	        // Log an error message for the timeout exception
	        logger.error("Timeout waiting for element to be clickable located by: " + locator.toString(), timeoutException);
	    }
	}

	/**
	 * This keyword is used to wait for a specified period without any condition
	 */
	public void timeoutWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	/**
	 * This method is used to wait
	 */
	public void waitCondition() {
		try {
			TimeUnit.MILLISECONDS.sleep(4000);
		} catch (InterruptedException interruptedException) {
		}
	}
	
	/**
	 * This method is used to wait
	 */
	public void waittoDelete() {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException interruptedException) {
		}
	}

	/**
	 * This mehod is used to wait for particular time period ignoring exception
	 */
	public void waitAndIgnoreCondition() {
		new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Constants.waitDuration))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
	}

	/**
	 * This keyword helps moving a cursor to the specified webelement
	 * 
	 * @param element: It is passed to locate the exact web element
	 */
	public void mouseHover(WebElement element) {
	    try {
	        new Actions(driver).moveToElement(element).build().perform();
	    } catch (MoveTargetOutOfBoundsException e) {
	        logger.error("Mouse hover failed because the target element is out of bounds.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while performing mouse hover.", e);
	    }
	}


	/**
	 * This keyword helps moving a cursor to the specified webelement and click on
	 * it
	 * 
	 * @param element: It is passed to locate the exact web element
	 */
	public void mouseHoverAndClick(WebElement element) {
	    try {
	        new Actions(driver)
	            .moveToElement(element)
	            .click()
	            .build()
	            .perform();
	    } catch (ElementNotInteractableException e) {
	        logger.error("Mouse hover and click failed because the element is not interactable.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while performing mouse hover and click.", e);
	    }
	}


	/**
	 * This keyword helps to right click on a specified webelement it
	 * 
	 * @param element: It is passed to locate the exact web element
	 */
	public void rightClick(WebElement element) {
	    try {
	        new Actions(driver)
	            .contextClick(element)
	            .build()
	            .perform();
	    } catch (ElementNotInteractableException e) {
	        logger.error("Right click failed because the element is not interactable.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while performing right click.", e);
	    }
	}


	/**
	 * This keyword helps to select a element in the dropdown by the specified index
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @param index:   It is used to enter the specified index for the element to be
	 *                 chosen
	 */
	public void selectByIndex(WebElement element, int index) {
	    try {
	        mouseHoverAndClick(element);
	        new Select(element).selectByIndex(index);
	    } catch (ElementNotInteractableException e) {
	        logger.error("Select from the dropdown failed because the element is not interactable.", e);
	    } catch (NoSuchElementException e) {
	        logger.error("Select from the dropdown failed because the element was not found.", e);
	    } catch (StaleElementReferenceException e) {
	        logger.error("Select from the dropdown failed due to a stale element reference.", e);
	    } catch (IndexOutOfBoundsException e) {
	        logger.error("Select from the dropdown failed because the index is out of bounds.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while selecting by index from the dropdown.", e);
	    }
	}

	/**
	 * This keyword helps to select a element in the dropdown by the specified value
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @param value:   It is used to enter the specified value for the element to be
	 *                 chosen
	 */
	public void selectByValue(WebElement element, String value) {
	    try {
	        mouseHoverAndClick(element);
	        new Select(element).selectByValue(value);
	    } catch (ElementNotInteractableException e) {
	        logger.error("Select from the dropdown failed because the element is not interactable.", e);
	    } catch (NoSuchElementException e) {
	        logger.error("Select from the dropdown failed because the element was not found.", e);
	    } catch (StaleElementReferenceException e) {
	        logger.error("Select from the dropdown failed due to a stale element reference.", e);
	    } catch (UnexpectedTagNameException e) {
	        logger.error("Select from the dropdown failed because the element is not a select input.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while selecting by value from the dropdown.", e);
	    }
	}


	/**
	 * This keyword helps to select a element in the dropdown by the specified text
	 * 
	 * @param element: It is passed to locate the exact web element
	 * @param text:    It is used to enter the specified text for the element to be
	 *                 chosen
	 */
	public void selectByVisibleText(WebElement element, String text) {
	    try {
	        mouseHoverAndClick(element);
	        new Select(element).selectByVisibleText(text);
	    } catch (ElementNotInteractableException e) {
	        logger.error("Select from the dropdown failed because the element is not interactable.", e);
	    } catch (NoSuchElementException e) {
	        logger.error("Select from the dropdown failed because the element was not found.", e);
	    } catch (StaleElementReferenceException e) {
	        logger.error("Select from the dropdown failed due to a stale element reference.", e);
	    } catch (UnexpectedTagNameException e) {
	        logger.error("Select from the dropdown failed because the element is not a select input.", e);
	    } catch (Exception e) {
	        logger.error("An error occurred while selecting by visible text from the dropdown.", e);
	    }
	}

	/**
	 * This keyword helps in pressing down key
	 */
	public void pressDownKey() {
	    try {
	        new Robot().keyPress(KeyEvent.VK_DOWN);
	    } catch (AWTException exception) {
	        logger.error("An AWTException occurred while pressing the down key.", exception);
	    }
	}

	
	/**
	 * This keyword helps in pressing down key
	 */
	public void pressBackSpace() {
	    try {
	        new Robot().keyPress(KeyEvent.VK_BACK_SPACE);
	    } catch (AWTException exception) {
	        logger.error("An AWTException occurred while pressing the Backspace key.", exception);
	    }
	}


	/**
	 * This keyword helps in pressing down key
	 */
	public void pressKey(int keyCode) {
	    try {
	        new Robot().keyPress(keyCode);
	    } catch (AWTException exception) {
	        logger.error("An AWTException occurred while pressing the key with code " + keyCode + ".", exception);
	    }
	}


	/**
	 * This keyword helps in pressing Enter key
	 */
	public void pressEnter() {
	    try {
	        new Robot().keyPress(KeyEvent.VK_ENTER);
	    } catch (AWTException exception) {
	        logger.error("An AWTException occurred while pressing the Enter key.", exception);
	    }
	}


	/**
	 * This keyword helps in pressing Escape key
	 */
	public void pressEsc() {
	    try {
	        new Robot().keyPress(KeyEvent.VK_ESCAPE);
	    } catch (AWTException e) {
	        logger.error("An AWTException occurred while pressing the Escape key.", e);
	    }
	}


	/**
	 * This keyword refreshes the current browser session.
	 *
	 * @return
	 */
	public boolean refreshBrowser() {
	    try {
	        driver.navigate().refresh();
	        return true;
	    } catch (WebDriverException exception) {
	        logger.error("An error occurred while refreshing the page.", exception);
	        return false;
	    }
	}


	/**
	 * This keyword is used to upload a file in the webpage
	 * 
	 * @param filePath: This is used to locate the path of the file to be uploaded
	 */
	public void uploadFile(String filePath) {
	    try {
	        Robot robot = new Robot();
	        StringSelection selection = new StringSelection(filePath);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	        
	        // Log an info message indicating successful file upload
	        logger.info("File is uploaded");
	    } catch (AWTException awtException) {
	        // Log an error message for the AWTException
	        logger.error("An AWTException occurred while uploading the file.", awtException);
	    }
	}

	/**
	 * Captures and saves a screenshot of the current state of the web page.
	 */

	public void screenshot() {
	    try {
	        String fileName = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss'.png'").format(new Date());
	        TakesScreenshot screenshot = (TakesScreenshot) driver;
	        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
	        File destination = new File(
	                Constants.userDirectory + File.separator + Constants.screenshotFolder + File.separator + fileName);

	        FileUtils.copyFile(sourceFile, destination);

	        // Log an info message indicating successful screenshot capture
	        logger.info("Screenshot captured and saved as: " + destination.getAbsolutePath());
	    } catch (IOException ioException) {
	        // Log an error message for the IOException
	        logger.error("An IOException occurred while capturing or saving the screenshot.", ioException);
	    } catch (Exception exception) {
	        // Log a generic error message for any other exceptions that may occur
	        logger.error("An error occurred while capturing or saving the screenshot.", exception);
	    }
	}

	/**
	 * This method is used to read data from excel file
	 * 
	 * @param sheetName: This parameter is used to select the particular sheet from
	 *                   the excel sheet
	 * @return
	 */
	public static List<Map<String, String>> getExcelData(String sheetName) {
		List<Map<String, String>> testDataAllRows = null;
		Map<String, String> testData = null;
		String filePath = Constants.userDirectory + File.separator + Constants.excelFilePath;
		try {
			FileInputStream fielInput = new FileInputStream(filePath);
			@SuppressWarnings("resource")
			Workbook workbook = new XSSFWorkbook(fielInput);
			Sheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
			List<String> headers = new ArrayList<String>();
			for (int index = 0; index < columnCount; index++) {
				Row titleRow = sheet.getRow(0);
				Cell titleCell = titleRow.getCell(index);
				String headerData = titleCell.getStringCellValue();
				headers.add(headerData);
			}
			testDataAllRows = new ArrayList<Map<String, String>>();

			for (int row = 1; row < rowCount; row++) {
				Row rowData = sheet.getRow(row);
				testData = new LinkedHashMap<String, String>();
				for (int column = 0; column < columnCount; column++) {
					Cell cell = rowData.getCell(column);
					String data = cell.getStringCellValue();
					testData.put(headers.get(column), data);
				}
				testDataAllRows.add(testData);
			}

		} catch (FileNotFoundException fileNotFoundException) {
	        logger.error("File not found Exception occurred while reading the Excel file.", fileNotFoundException);
		} catch (IOException ioException) {
	        logger.error("IO exception occurred while reading the Excel file.", ioException);
		} catch (Exception exception) {
	        logger.error("An error occurred while reading the Excel file.", exception);
		}
		return testDataAllRows;
	}

	/**
	 * This method is used to quit the browser
	 */
	@Test
	@AfterTest
	public void closeBrowser() {
	    try {
	        driver.quit();
	    } catch (WebDriverException e) {
	        logger.error("An error occurred while closing the browser.", e);
	    }
	}

	public static void main(String[] args) {
		List<Map<String, String>> excelData = getExcelData("Admin");
		System.out.println(excelData.size());
	}
}

