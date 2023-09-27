package org.atmecs.h3d.locators;

import org.openqa.selenium.By;

public class DevicesPageLocators {
	
	public static By deviceTypesText=By.xpath("//h3[normalize-space()='Devices']");
	public static By deviceSearch=By.xpath("//h3[normalize-space()='Devices']//following::input[@id='filterInput']");
	public static By addDeviceButton=By.xpath("//h3[normalize-space()='Devices']//following::div//button[normalize-space()='Add']"); 
    public static By selectDeviceType=By.xpath("//h3[normalize-space()='Devices']//following::div//select[@id='formDeviceName']");
	public static By deviceName=By.xpath("//input[@id='formDeviceName']");
	public static By deviceBrand=By.xpath("//input[@id='formDeviceBrand']");
	public static By deviceModel=By.xpath("//input[@id='formDeviceModel']");
	public static By deviceStatus=By.xpath("//select[@id='formDeviceTypeCode']");
	public static By deviceSubmitButton=By.xpath("//div[text()='Add Device']//following::button[normalize-space()='Submit']");
	public static By deviceCancelButton=By.xpath("//div[text()='Add Device']//following::button[normalize-space()='Cancel']");
	
	
	
	
	
	
	
	
	
	
}
