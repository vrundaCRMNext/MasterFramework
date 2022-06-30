package CommonUtility;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG extends SetUp implements AutoConst {
	public static File flOutput;
	static ExtentReports extent ;
	static Logger log = LoggerFactory.getLogger(ExtentReporterNG.class);

	public static ExtentReports getReportObject()
	{
		
		
		//String reportPath = System.getProperty("user.dir")+"\\Reports\\KMB_LeadCreationReport_"+folderDate;
		String currentDir = System.getProperty("user.dir")+"\\Results";
		String outPutFolder = currentDir +"\\Output_"+folderDate;
			
		flOutput = new File(outPutFolder);
		if(!flOutput.exists()) {
			if(flOutput.mkdir()) {
				System.out.println("Extent report Directory is created!");
				log.debug("Extent report Directory is created!");
				}
			else {
                System.out.println("Failed to create extent report directory!");
				log.debug("Failed to create extent report directory!");
				}
			}
			
		//String reportPath = System.getProperty("user.dir")+"\\TestReport"+folderDate+".html";
		
		String reportPath = flOutput+"\\TestReport_"+folderDate+".html";
				
		ExtentSparkReporter reporter =new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Project Name","Kotak ");
		extent.setSystemInfo("Browser","Chrome");
		
		
		return extent;
	}
}
