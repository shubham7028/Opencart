package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
//Common class which is required for every test cases,so we want to generate Logs for all test cases	
	public static WebDriver driver;//we refer same driver instance in ExtendReportManager
	public Logger logger;
	public Properties prop;
	
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"Browser","os"})
	public void setupTest(String br,String OS) 						throws IOException 
	{	
		FileReader file=new FileReader("./src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);	
		logger=LogManager.getLogger(this.logger);//log4j2
	//OS Setup:		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			if(OS.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
				
			}
			else if(OS.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(OS.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{	
				System.out.println("OS not matching..."); return;
			}
	//Browser Setup:
		switch(br.toLowerCase())
		{
			case "chrome": cap.setBrowserName("chrome");		break;
			case "edge":   cap.setBrowserName("MicrosoftEdge");	break;
			case "firefox":cap.setBrowserName("Firefox");		break;
			default:  System.out.println("Browser not found"); return;
		}	
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
//Local:-		
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver();break;
			case "edge":  driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default :System.out.println("Invalid Browser...");return;
			}
		}				
/*		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();	break;
		case "edge":  driver=new EdgeDriver();		break;
		case "firefox":driver=new FirefoxDriver();	break;
		default :System.out.println("Invalid Browser...");return;//It will not execute further statements...
		}	*/	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
//		driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("URL"));			//Reading URL from Properties file...
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"sanity","regression","master"})
	public void closeTest() 
	{
		driver.quit();
	}
	
//RandomData:-	
	public String randomstring() {
		String random = RandomStringUtils.randomAlphabetic(7);
		return random;
	}

	public String randomNunmber() {
		String numb = RandomStringUtils.randomNumeric(10);
		return numb;
	}

	public String randomPass() {
		String aplha = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);
		return (aplha + "$" + num);
	}	
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String path=".\\screenshots"+tname+"_"+timeStamp+".png";
		File target=new File(path);
		source.renameTo(target);
		return path;	
	}
}
