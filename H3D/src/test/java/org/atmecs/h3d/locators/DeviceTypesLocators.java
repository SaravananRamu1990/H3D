package org.atmecs.h3d.locators;

import org.openqa.selenium.By;

public class DeviceTypesLocators {
	public static By DeviceTypesMenu= By.xpath("//a[@title='Device Types']");
	public static By addDeviceTypes=By.xpath("//h3[normalize-space()='Device Types']//following::div//button[normalize-space()='Add']");
    public static By deviceTypeName=By.xpath("//input[@id='formDeviceTypeName']");
    public static By deviceTypeCode=By.xpath("//input[@id='formDeviceTypeCode']");
    public static By deviceTypeSubmit=By.xpath("//div[text()='Add Device Type']//following::button[text()='Submit']");
    public static By deviceTypeCancel=By.xpath("//div[text()='Add Device Type']//following::button[text()='Cancel']");
    public static By deviceTypeClose=By.xpath("//div[text()='Add Device']//following::button[@aria-label='Close']");
    public static By deviceTypeSearch=By.xpath("//h3[normalize-space()='Device Types']//following::input[@id='filterInput']");
    public static By deviceTypeText=By.xpath("//h3[normalize-space()='Device Types']");
    
    
    // need to work on
    public static By tablepage=By.xpath("//li[contains(@class,'page-item')]//child::*[@class=\"page-link\"]");
    
    








}
