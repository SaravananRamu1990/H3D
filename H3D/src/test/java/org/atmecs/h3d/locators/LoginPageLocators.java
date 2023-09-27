package org.atmecs.h3d.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
	public static By email= By.xpath("//input[@id='formEmail']");
	public static By password=By.xpath("//input[@id='formPassword']");
	public static By submitButton=By.xpath("//button[text()='Submit']");
	public static By warningmessage=By.xpath("//span[@class='error-text']");
    public static By homepage=By.xpath("//h3[text()='Admin Dashboard']");
    public static By viewPassword=By.xpath("//*[name()='path' and contains(@d,'M12 4.5C7 ')]");
    public static By logoutButton=By.xpath("//div[@class='toolbar-icon']//*[name()='svg']");

}
