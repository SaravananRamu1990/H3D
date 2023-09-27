package org.atmecs.h3d.testscripts;

import org.testng.annotations.Test;

public class DevicePage extends LoginPage{
	LoginPage lp=new LoginPage();
@Test(priority=1,description = "This method is used to add device")
public void addDeviceType() {
	lp.validCredentials();
	
}
}
