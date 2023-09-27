package org.atmecs.h3d.testscripts;

import org.atmecs.h3d.reusables.HelperClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

	public class Data {
				public static void main(String[] args) throws InterruptedException {
	        WebDriverManager.chromedriver().setup();

	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");

	        WebDriver driver = new ChromeDriver(options);

	        driver.get("http://engage.atmecs.online/");
	        Thread.sleep(5000);

			driver.findElement(By.xpath("//input[@id='formEmail']")).sendKeys("harish@atmecs.com");
			driver.findElement(By.xpath("//input[@id='formPassword']")).sendKeys("admin");
			driver.findElement(By.xpath("//button[text()='Submit']")).click();
            Thread.sleep(5000);		
			driver.findElement(By.xpath("//a[@title='Device Types']")).click();
            Thread.sleep(5000);
	        

	        String targetData = "HYBRID VTOL DRONE"; // Make sure this matches the data in the table

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
	                System.out.println("Data Not found");
	                break;
	            }

	            WebElement nextPageButton = driver.findElement(By.xpath("//span[contains(text(),'›')]"));
	            if (nextPageButton.isEnabled()) {
	                // Perform click using Actions class
	                actions.moveToElement(nextPageButton).click().perform();
	                Thread.sleep(5000); // Give more time for the new page to load
	            } else {
	                break;
	            }
	            
	            // Reset dataFound for the next page
	            dataFound = false;
	        }

	        if (!dataFound) {
	            System.out.println("Data not found in table.");
	        }

	        driver.quit();
	    }
	}
