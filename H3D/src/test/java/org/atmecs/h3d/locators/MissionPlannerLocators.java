package org.atmecs.h3d.locators;

import org.openqa.selenium.By;

public class MissionPlannerLocators {
	public static By tenantDropDown= By.xpath("//h4[normalize-space()='Devices Lists :']//following::select//following::option");
    public static By saveFileButton= By.xpath("//h4[normalize-space()='Devices Lists :']//following::button[normalize-space()='Save File']");
    public static By loadFileButton= By.xpath("//h4[normalize-space()='Devices Lists :']//following::button[normalize-space()='Load file']");
    public static By missionPlannerLink=By.linkText("");


}