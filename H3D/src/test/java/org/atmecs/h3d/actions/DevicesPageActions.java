package org.atmecs.h3d.actions;

import org.apache.log4j.Logger;
import org.atmecs.h3d.locators.DeviceTypesLocators;
import org.atmecs.h3d.locators.HomePageLocators;
import org.atmecs.h3d.reusables.HelperClass;
import org.openqa.selenium.WebElement;

public class DevicesPageActions extends HelperClass {
	public static Logger logger= Logger.getLogger(DevicesPageActions.class);

	public void clickDevicesTab() {
	    try {
	        waitForElementPresence(HomePageLocators.deviceTypesMenu);
	        WebElement deviceTypesMenu = findWebElement(DeviceTypesLocators.DeviceTypesMenu);
	        if (deviceTypesMenu != null) {
	            deviceTypesMenu.click();
	            logger.info("Devices Tab is clicked");
	        } else {
	            logger.error("Devices Tab element not found");
	        }
	    } catch (Exception e) {
	        logger.error("Clicking Devices Tab is not performed: " + e.getMessage());
	    }
	}
	public boolean isHomePageDisplayed() {
	    try {
	        WebElement homePageTextElement = findWebElement(HomePageLocators.homePagetext);
	        if (homePageTextElement != null && homePageTextElement.isDisplayed()) {
	            logger.info("Home page is displayed");
	            return true;
	        } else {
	            logger.info("Home page is not displayed");
	            return false;
	        }
	    } catch (Exception e) {
	        logger.error("An error occurred while checking if the home page is displayed: " + e.getMessage());
	        return false;
	    }
	}

}
