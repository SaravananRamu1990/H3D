package org.atmecs.h3d.testscripts;

import java.util.Map;

import org.apache.log4j.Logger;
import org.atmecs.h3d.actions.LoginPageActions;
import org.atmecs.h3d.constants.Constants;
import org.atmecs.h3d.locators.LoginPageLocators;
import org.atmecs.h3d.reusables.HelperClass;
import org.atmecs.h3d.reusables.ReadExcel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage extends HelperClass {
	LoginPageActions loginPage = new LoginPageActions();
	ReadExcel read = new ReadExcel();
	static Logger logger = Logger.getLogger(LoginPage.class);
	@Test(priority=1,description="This method is used to validate login functionality with invalid credentials")
	public void invalidCredentialsWithValidUsernameAndInvalidPassword() {
	    try {
	        Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, "Login", "H3D_01");
	        String username = testDataMap.get("Username");
	        String password = testDataMap.get("Password");
	        logger.info("Attempting login with valid Username: " + username + " and invalid Password: " + password);
	        loginPage.invalidMessage(username, password);
	        String expectedErrorMessage=testDataMap.get("Error Message");
	        String actualErrorMessage=getObjectText(findWebElement(LoginPageLocators.warningmessage));
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,"Warning message doesn't match the expected value");
	        logger.info("Login was unsuccessful for the Username:"+ username +",Password:"+password);

	        // Add assertions to verify that the login failed as expected
	    } catch (Exception exception) {
	        logger.error("An error occurred during the login attempt: " + exception.getMessage());
	    }
	}

	@Test(priority=2,description="This method is used to validate login functionality with invalid credentials")
	public void invalidCredentialsWithInValidUsernameandValidPassword() {
	    try {
	        Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, "Login", "H3D_02");
	        String username = testDataMap.get("Username");
	        String password = testDataMap.get("Password");
	        logger.info("Attempting login with Invalid credentials.  Username: " + username + " and invalid Password: " + password);
	        loginPage.invalidMessage(username, password);
	        String expectedErrorMessage=testDataMap.get("Error Message");
	        String actualErrorMessage=getObjectText(findWebElement(LoginPageLocators.warningmessage));
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage,"Warning message doesn't match the expected value");
	        logger.info("Login was unsuccessful for the Username:"+username+",Password:"+password);
	    } catch (Exception exception) {
	        logger.error("An error occurred during the login attempt: " + exception.getMessage());
	    }
	}


	@Test(priority=3,description = "This method is used to validate login functionality with valid credentials")
	public void validCredentials() {
	    try {
	        Map<String, String> testDataMap = ReadExcel.getTestDataInMap(Constants.excelFilePath, "Login", "H3D_03");
	        String username = testDataMap.get("Username");
	        String password = testDataMap.get("Password");

	        logger.info("Attempting login with valid credentials. Username: " + username + ", Password: " + password);
	        loginPage.validLogin(username, password);
	        logger.info("Login was successful for Username: " + username + ", Password: " + password);
	    } catch (Exception exception) {
	        logger.error("An error occurred during the login attempt: " + exception.getMessage());
	    }
	}


}
