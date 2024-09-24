package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter SpartReporter; // UI of Report
	public ExtentReports report;			 // Populate Common Info on Report
	public ExtentTest test; // Creating Case Entries & Update the status of Test Methods...
	String ReportName;

	public void OnStart(ITestContext context) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String CurrentTimeStamp=df.format(dt);	
//		String CurrentTimeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format.(new Date(0));
		
		SpartReporter = new ExtentSparkReporter("./reports//"+"ReportName");
		SpartReporter.config().setDocumentTitle("Opencart Automation Report");// title of report
		SpartReporter.config().setReportName("Opencart Functional Testing"); // name of report
		SpartReporter.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(SpartReporter);
		report.setSystemInfo("Application", "Opencart");
		report.setSystemInfo("Tester Name", "Shubham");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Report Priority", "Medium");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("Operating System", os);
		String browser=context.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser Name", browser);
		List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) 
		{
		report.setSystemInfo("Groups", includeGroups.toString());	
		}
	}

	public void OnTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());// Create new entry in Report...
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "TestCase is passed:" + result.getName());// Update Report...
	}

	public void OnTestFailure(ITestResult result) {
		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to dislpay groups in report...
		test.log(Status.FAIL, "TestCase is failed:" + result.getName());
		test.log(Status.INFO, "Failed because of:" + result.getThrowable().getMessage());
	try {	
		String imgPath=new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);	}
	catch(Exception e)
	{	e.printStackTrace();	}
	}

	public void OnTestSkipped(ITestResult result) {
		test = report.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "TestCase is Skipped:" + result.getName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void OnFinish(ITestContext result) throws IOException {
		report.flush();// Writes test information from the started reporters to their output view
		
		String PathOfExtentReport=".\\reports"+ReportName;
		File ExtentReport=new File(PathOfExtentReport);
		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
}
