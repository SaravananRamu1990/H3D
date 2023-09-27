package org.atmecs.h3d.constants;

import java.io.File;

public class Constants {
	public static String userDirectory = System.getProperty("user.dir");
	public static String configPath = "src" + File.separator + "main" + File.separator + "resource" + 
	File.separator + "org" + File.separator + "atmecs" + File.separator + "h3d" + File.separator + "data" + 
			File.separator + "config.properties";
	public static String excelFilePath = "src" + File.separator + "main" + File.separator + "resource" + 
			File.separator + "org" + File.separator + "atmecs" + File.separator + "h3d" + File.separator +
			"data"+ File.separator + "H3D_TestData.xlsx";
	public static int waitDuration=20;
	public static String screenshotFolder = "screenshot";
	public static String Login_Sheet = "Login";

}