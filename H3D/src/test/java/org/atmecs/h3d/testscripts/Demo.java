package org.atmecs.h3d.testscripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
	public static void main(String[] args) throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);

		driver.get("http://engage.atmecs.online/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@id='formEmail']")).clear();
		driver.findElement(By.xpath("//input[@id='formEmail']")).sendKeys("harish@atmecs.com");
		driver.findElement(By.xpath("//input[@id='formPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='formPassword']")).sendKeys("admin");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@title='Device Types']")).click();
        

		// Enter device type name and device type code
		String deviceTypeName = "AlamicDrone";
		String deviceTypeCode = "DTCv123";
		Thread.sleep(3000);
        WebElement addDeviceTypeButton= driver.findElement(By.xpath("//h3[normalize-space()='Device Types']//following::div//button[normalize-space()='Add']"));
		addDeviceTypeButton.click();
		Thread.sleep(3000);

        WebElement deviceTypeNameField = driver.findElement(By.xpath("//input[@id='formDeviceTypeName']"));
		Thread.sleep(3000);

		WebElement deviceTypeCodeField = driver.findElement(By.xpath("//input[@id='formDeviceTypeCode']"));
		Thread.sleep(3000);

		WebElement addButton = driver.findElement(By.xpath("//div[text()='Add Device Type']//following::button[text()='Submit']"));
		Thread.sleep(3000);

		deviceTypeNameField.sendKeys(deviceTypeName);
		deviceTypeCodeField.sendKeys(deviceTypeCode);
		addButton.click();

		// Wait for the data to be added to the table
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[normalize-space()='Device Types']//following::div[@class='sc-bczSft cMMpgd rdt_Table']")));

		// Validate the entered data in the paginated table
		String targetData = deviceTypeName;
		boolean dataFound = false;

		Actions actions = new Actions(driver);

		while (true) {
			List<WebElement> rows = driver.findElements(By.xpath("//h3[normalize-space()='Device Types']//following::div[@class='sc-jqUWZQ jSILou rdt_TableRow']"));

			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.xpath(".//div[@class='sc-hKMtDE sc-eCYdKt sc-jSMdHm ekbsWQ xizbp ryWnn rdt_TableCell']"));
				for (WebElement cell : cells) {
					if (cell.getText().equals(targetData)) {
						System.out.println("Data found in table.");
						dataFound = true;
						break;
					                        }
				                         }
				if (dataFound) {
					break;
				}
			}

			if (!dataFound) {
				System.out.println("Data not found in table.");
			}

			WebElement nextPageButton = driver.findElement(By.xpath("//span[contains(text(),'›')]"));
			if (nextPageButton.isEnabled()) {
				// Perform click using Actions class
				actions.moveToElement(nextPageButton).click().perform();
				Thread.sleep(3000); // Give more time for the new page to load
			} else {
				break;
			}

			// Reset dataFound for the next page
			dataFound = false;

		}
		driver.quit();

	}
}
