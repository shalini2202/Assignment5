package com.assignment5;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ShoppingXML {
	

    public  String url = "http://automationpractice.com/index.php/";
    public String driverPath = "C:\\Users\\a631020\\Downloads\\";
    public WebDriver driver ; 
    
    public String id = "shalinigupta.it44@gmail.com";
    public String pwd = "om**om";
    
    public String actual = null;
    public String expected = null;
    
    public WebElement element;
    
    
    
  //Launching Browser
    
    @Parameters({"Browser"})
    @BeforeTest
    public void setUp(String browser) {
    	System.out.println("select browser of your choice");
    	
    	if(browser.equalsIgnoreCase("chrome")) {
    		System.out.println("launching chrome browser"); 
    	   	 
            System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
    	}
    	
    	else if(browser.equalsIgnoreCase("firefox")) {
    		System.out.println("launching firefox browser"); 
   	   	 
            System.setProperty("webdriver.firefox.marionette", driverPath+"geckodriver-v0.19.1-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(url);
    	}
        
        else if(browser.equalsIgnoreCase("ie")){
        	System.out.println("launching internet explorer browser"); 
      	   	 
            System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer_x64_3.8.0\\IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();	
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);  
			capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);    
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,url);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);        	
		//	driver=new RemoteWebDriver(new URL(url),capabilities);
			driver=new InternetExplorerDriver(capabilities);
			driver.get(url); 
        }
    	
    	else {
    		System.out.println("browser not found");
    	}
  
    }

      

    //Login
    @Test(priority=0)
    public void login() {
   	 System.out.println("login");
   	 
   	 driver.findElement(By.xpath("//a[@class='login']")).click();
   
     driver.findElement(By.id("email")).sendKeys(id);
   	 driver.findElement(By.id("passwd")).sendKeys(pwd);
   	 
   	 driver.findElement(By.id("SubmitLogin")).click();
   	 
     actual = driver.findElement(By.xpath("//a[@class = 'account']/span")).getText();
   	 System.out.println(actual);
     expected = "shalini gupta";
   	 Assert.assertTrue(actual.toLowerCase().contains(expected));
   	 
   	 System.out.println("Login Verification Successful......"); 	
    	 
    }
    
    
    
    //Select Dresses
     @Test(priority=1)
     public void selectDress() throws InterruptedException {
   	     System.out.println("selecting dresses");
   	  
   	     element = driver.findElement(By.xpath("(//a[@class = 'sf-with-ul'])[4]"));
         Actions action1 = new Actions(driver);
         action1.moveToElement(element).build().perform();
         
         Thread.sleep(2000);
         
         driver.findElement(By.xpath(".//*[@id='block_top_menu']/ul/li[2]/ul/li[1]/a")).click();
        
         element = driver.findElement(By.xpath(".//*[@id='center_column']/ul/li/div/div[1]/div/a[1]/img"));
         Actions action2 = new Actions(driver);
         action2.moveToElement(element).build().perform();
         
         Thread.sleep(2000);
         
         driver.findElement(By.xpath("//a[@class='button lnk_view btn btn-default']/span")).click();
         
         driver.findElement(By.className("icon-plus")).click();
         
         Select s1=new Select(driver.findElement(By.id("group_1")));
   	     s1.selectByValue("3");
   	  
}
     
     
        
     //add to cart
     @Test(priority=2)
     public void addToCart() throws InterruptedException {
    	 System.out.println("adding to cart");
    	
         driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click();
         
         Thread.sleep(2000);
         actual = driver.findElement(By.xpath(".//*[@id='layer_cart_product_title']")).getText();
	     System.out.println(actual);
	     String expected ="Printed Dress";
		 Assert.assertTrue(actual.equalsIgnoreCase(expected));
	
         actual = driver.findElement(By.xpath(".//*[@id='layer_cart_product_attributes']")).getText();
		 System.out.println(actual);
	     expected ="orange, l";
		 Assert.assertTrue(actual.toLowerCase().contains(expected));
		 
		 actual = driver.findElement(By.xpath(".//*[@id='layer_cart_product_quantity']")).getText();
		 System.out.println(actual);
	     expected ="2";
		 Assert.assertTrue(actual.toLowerCase().contains(expected));
	
	    System.out.println("Dress Verification Successful......"); 	

        driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();
		 	 
     }
     
     
     
     //go to address
     @Test(priority=3)
     public void goToAddress() {
    	 System.out.println("going to address");
    	 
    	 driver.findElement(By.xpath(".//*[@id='center_column']/p[2]/a[1]/span")).click();
    	 
    	 actual = driver.findElement(By.xpath(".//*[@id='center_column']/h1")).getText();
    	 System.out.println(actual);
    	 expected = "addresses";
    	 Assert.assertTrue(actual.equalsIgnoreCase(expected));
    	 
    	 System.out.println("Address Tab Verification Successful......"); 	
    	 
     }
     
     
     
     //close browser
     @AfterTest
     public void closeBrowser() {
    	 System.out.println("closing browser");
    	 
    	 driver.close();
    	 
     }

}
