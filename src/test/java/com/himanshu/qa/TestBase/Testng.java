/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.TestBase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.himanshu.qa.Util.EmailUtil;
import com.himanshu.qa.Util.ExcelUtil;
import com.himanshu.qa.Util.FileUtil;
import com.himanshu.qa.Util.ReportUtil;

/*This class is used to create testNG dynamically.
 No need to modify it.*/

public final class Testng {
	public static void testNgXmlCreation(String environment, String tests,
			String browser, String groups) throws Exception {
		String testPackage = FileUtil.getConstantValue("TestPackage");
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName(FileUtil.getConstantValue("SuiteName"));
		String path = null;
		List<String> packageClasses;
		// Create an instance of XmlTest and assign a name for it.

		String[] testArray = tests.split(",");
		String browserArray[] = browser.split(",");
		XmlTest myTest = null;
		List<XmlClass> myClasses = null;
		List<XmlPackage> myPackages = null;
		List<XmlTest> myTests = new ArrayList<XmlTest>();

		String reportHeading = "";
		for (int i = 0; i < testArray.length; i++)
			reportHeading = reportHeading + testArray[i] + "-";

		for (int browserIndex = 0; browserIndex < browserArray.length; browserIndex++) {
			myTest = new XmlTest(mySuite);
			myTest.setName(reportHeading + browserArray[browserIndex]);// myTest.setName(browserArray[browserIndex]);
			myTest.addParameter("browser", browserArray[browserIndex]);
			myTest.addParameter("environment", environment);

			// Create a list which contain the classes that you want to run.
			myClasses = new ArrayList<XmlClass>();
			myPackages = new ArrayList<XmlPackage>();
			path = System.getProperty("user.dir") + FileUtil.separator
					+ "UIAutomationWap" + FileUtil.separator
					+ FileUtil.getConstantValue("TestMapping");
			File f = new File(path);
			if (f.exists()) {
				packageClasses = ExcelUtil.getPackagesAndClasses(path,
						testArray);
			} else {

				packageClasses = ExcelUtil.getPackagesAndClasses(
						System.getProperty("user.dir") + FileUtil.separator
								+ FileUtil.getConstantValue("TestMapping"),
						testArray);
			}
			for (int index = 0; index < packageClasses.size(); index++) {
				String strArray[] = packageClasses.get(index).split(",");
				for (int classIndex = 0; classIndex < strArray.length; classIndex++) {
					if (!strArray[classIndex].contains("."))
						myPackages.add(new XmlPackage(testPackage
								+ strArray[classIndex]));
					else
						myClasses.add(new XmlClass(testPackage
								+ strArray[classIndex]));
				}
			}
			myTest.setPackages(myPackages);
			myClasses.add(new XmlClass("com.snapdeal.qa.Util.WebServerLog"));
			myTest.setXmlClasses(myClasses);
			if (!groups.equals("")) {
				String myGroups[] = groups.split(",");
				for (int index = 0; index < myGroups.length; index++)
					myTest.addIncludedGroup(myGroups[index]);
			}
			myTests.add(myTest);
		}
		mySuite.setTests(myTests);
		mySuite.setParallel("tests");
		mySuite.setThreadCount(browserArray.length);
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		myTestNG.setXmlSuites(mySuites);
		System.out.println("Testng created successfully !!!");
		myTestNG.run();
		System.out.println("Tests executed !!!");
	//	System.exit(1);
		/*File tempFile = null;
		List<String> reportList = new ArrayList<String>();
		for (int i = 0; i < browserArray.length; i++) {
			String testngReportHeading = reportHeading + browserArray[i];
			reportList.add(testngReportHeading);
			ReportUtil.editTestngReport(testngReportHeading);
			tempFile = ReportUtil.extractResultTable(testngReportHeading);
		}*/
		/*EmailUtil.sendEmail(reportList, tempFile,
				FileUtil.getConstantValue("EmailReciever"),
				FileUtil.getConstantValue("EmailSender"),
				FileUtil.getConstantValue("EmailPassword"));
		System.out.println("Email sent");*/
		// tempFile.delete();
		System.out.println(Thread.activeCount());
	    System.out.println(Thread.currentThread());
	 

	}

	public static void main(String args[]) throws Exception {
		if (args.length < 3)
			Assert.fail("Please give Environment/Test/Browser to execute.");
		else if (args.length == 3) {
			testNgXmlCreation(args[0], args[1], args[2], "");
			System.out.println("will exit from here");
		} else if (args.length == 4) {
			if (args[3].equals("yes"))
				retryFailedTestCases();
			else
				testNgXmlCreation(args[0], args[1], args[2], args[3]);
		} else if (args.length == 5)
			retryFailedTestCases();

	}

	private static void retryFailedTestCases() {
		String arg[] = { FileUtil.getConstantValue("FailedTestCasesXml")
				+ FileUtil.separator + "testng-failed.xml" };
		System.out.println("failedtestcases :: " + arg[0]);
		File f = new File(arg[0]);
		System.out.println("File exits ....." + f.exists());
		System.out.println("Running retry ");
		TestNG.main(arg);
	}
}