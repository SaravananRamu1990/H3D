package org.atmecs.h3d.locators;

import org.openqa.selenium.By;

public class UsersPage {
	public static By addButton=By.xpath("//h3[normalize-space()='Users']//following::div//button[normalize-space()='Add']");
    public static By addUserEmail=By.xpath("//input[@id='formEmail']");
    public static By addUserPassword=By.xpath("//input[@id='formPassword']");
    public static By addUserName=By.xpath("//input[@id='formName']");
    public static By selectTenants=By.xpath("");
}
