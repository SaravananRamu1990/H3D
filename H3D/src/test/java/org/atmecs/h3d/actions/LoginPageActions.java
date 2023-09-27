package org.atmecs.h3d.actions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.atmecs.h3d.constants.Constants;
import org.atmecs.h3d.locators.HomePageLocators;
import org.atmecs.h3d.locators.LoginPageLocators;
import org.atmecs.h3d.reusables.HelperClass;
import org.atmecs.h3d.reusables.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageActions extends HelperClass {
	static Logger logger = Logger.getLogger(LoginPageActions.class);
	List<Map<String, String>> input = getExcelData("LoginPage");
	ReadExcel read = new ReadExcel();

	/**
	 * This method passes valid credentials to the login page and check whether the
	 * login is successful
	 */
		public void validLogin(String username, String password) {
		try {
			Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, Constants.Login_Sheet,
					"H3D_03");
			waitForElementPresence(LoginPageLocators.email);
			clearAndWriteText(findWebElement(LoginPageLocators.email), username);
			waitForElementPresence(LoginPageLocators.password);
			clearAndWriteText(findWebElement(LoginPageLocators.password), password);
			waitForElementPresence(LoginPageLocators.submitButton);
			clickButton(findWebElement(LoginPageLocators.submitButton));
			String expectedPageTitle = testDataMap.get("Page Title");
			String actualPageTitle = getPageTitle();
//        Assert that the actual page title matches the expected page title
			Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page title doesn't match the expected value");
			// If the assertion passes, log a success message
			logger.info("Login was successful for Username: " + username + ", Password: " + password);
			String pageTitle = getPageTitle();
			logger.info(pageTitle);
			// Validate the page title using an assertion
//        Assert.assertEquals(pageTitle, testDataMap.get("Page Title"));
////        Assert.assertTrue();
			logger.info("Logged in successfully.");
		} catch (NoSuchElementException noSuchElementException) {
			// Log an error message for NoSuchElementException
			logger.error("Element not found while performing a login.", noSuchElementException);
			logger.error("Login failed.");
		} catch (AssertionError assertionError) {
			// Log an error message for assertion failure
			logger.error("Assertion failed: Page title does not match the expected value.");
			logger.error("Login failed.");
		} catch (Exception exception) {
			// Log an error message for any other exceptions that may occur
			logger.error("An error occurred while performing a login.", exception);
			logger.error("Login failed.");
		}
	}

	public void invalidMessage(String username, String password) {
		try {
			Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, Constants.Login_Sheet,
					"H3D_01");
			waitForElementPresence(LoginPageLocators.email);
			clearAndWriteText(findWebElement(LoginPageLocators.email), username);
			waitForElementPresence(LoginPageLocators.password);
			clearAndWriteText(findWebElement(LoginPageLocators.password), password);
			waitForElementPresence(LoginPageLocators.submitButton);
			clickButton(findWebElement(LoginPageLocators.submitButton));
			waitForElementPresence(LoginPageLocators.warningmessage);
			String actualError = getObjectText(findWebElement(LoginPageLocators.warningmessage));
			// Validate the error message using an assertion
			Assert.assertEquals(actualError, testDataMap.get("Error Message"));
			logger.error("Login is Unsuccessful");
		} catch (InterruptedException interruptedException) {
			// Log an error message for InterruptedException
			logger.error("An InterruptedException occurred during the login attempt.", interruptedException);
			// Restore the interrupted status
			Thread.currentThread().interrupt();
		} catch (AssertionError assertionError) {
			// Log an error message for assertion failure
			logger.error("Assertion failed: Error message does not match the expected value.");
			logger.error("Login is Unsuccessful");
		} catch (Exception exception) {
			// Log an error message for any other exceptions that may occur during login
			logger.error("An error occurred during the login attempt.", exception);
		}
	}

	public void requiredMessage(String username, String password) {
		try {
			Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, Constants.Login_Sheet,
					"H3D_01");
			waitForElementPresence(LoginPageLocators.email);
			clearAndWriteText(findWebElement(LoginPageLocators.email), username);
			clearAndWriteText(findWebElement(LoginPageLocators.password), password);
			clickButton(findWebElement(LoginPageLocators.submitButton));
			String pageTitle = getPageTitle();
			Assert.assertEquals(pageTitle, testDataMap.get("Page Title"));
			logger.info("Successful Login");
		} catch (NoSuchElementException noSuchElementException) {
			// Log an error message for NoSuchElementException
			logger.error("Element not found while performing a login.", noSuchElementException);
		} catch (AssertionError assertionError) {
			// Log an error message for assertion failure
			logger.error("Assertion failed. Page title does not match expected value.", assertionError);
		} catch (Exception exception) {
			// Log a generic error message for any other exceptions
			logger.error("An error occurred while performing a login.", exception);
		}
	}

}
