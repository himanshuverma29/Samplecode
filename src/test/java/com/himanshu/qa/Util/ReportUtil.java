/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.himanshu.qa.Util.FileUtil;

public class ReportUtil {
	
	public static void editTestngReport(String testngReportName) throws IOException
	{
		String testReportPath	=	System.getProperty("user.dir")+FileUtil.separator+FileUtil.getConstantValue("TestReport")+FileUtil.separator+FileUtil.getConstantValue("SuiteName")+FileUtil.separator+testngReportName+".html";
		int count=0;
		File input = new File(testReportPath);
		Document doc = Jsoup.parse(input, "UTF-8");
		Elements link = doc.select("link");
		
		for (Element linksTag : link) {
			
				linksTag.remove();
			
		}
		
		Elements table = doc.select("table");
		for (Element tableTag : table) {
			
			if (count==1){
				
				Elements failedRemove = doc.select("td:eq(3)");
				failedRemove.remove();
			}
			
			if (count ==2){
				Elements passedRemove = doc.select("td:eq(3)");
				passedRemove.remove();
			}
			
			if (count ==3){
				Elements skippedRemove = doc.select("td:eq(3)");
				skippedRemove.remove();
			}
			
			
			if (tableTag.attr("class").equals("invocation-failed") || tableTag.attr("class").equals("test-failed")){
				tableTag.attr("bgcolor", "#C14E4E");
			}else if (tableTag.attr("class").equals("invocation-percent") || tableTag.attr("class").equals("test-percent")) {
				tableTag.attr("bgcolor", "#4D944D");
			}else if (tableTag.attr("class").equals("invocation-passed") || tableTag.attr("class").equals("test-passed")){
				tableTag.attr("bgcolor", "#66CC66");
			}else if (tableTag.attr("class").equals("invocation-skipped") || tableTag.attr("class").equals("test-skipped")){
				tableTag.attr("bgcolor", "#E6E682");
			}
			count++;
		}
		
		String html = doc.html();
		Writer writer = new PrintWriter(testReportPath, "UTF-8");
		writer.write(html);
		writer.close();
		}

	public static File extractResultTable(String reportHeading) throws IOException
	{
		
		File file =new File(System.getProperty("user.dir")+FileUtil.separator+FileUtil.getConstantValue("TempFile")+".html");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String testngReport	=	System.getProperty("user.dir")+FileUtil.separator+FileUtil.getConstantValue("TestReport")+FileUtil.separator+FileUtil.getConstantValue("SuiteName")+FileUtil.separator+reportHeading+".html";
		File input = new File(testngReport);
		Document doc = Jsoup.parse(input, "UTF-8");
		Element table = doc.select("table").first();
		Element title = doc.select("h2").first();
		String html = title.outerHtml() + table.outerHtml();
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter buf = new BufferedWriter(writer);
		buf.write(html);
		buf.close();
		return file;
	}
}
