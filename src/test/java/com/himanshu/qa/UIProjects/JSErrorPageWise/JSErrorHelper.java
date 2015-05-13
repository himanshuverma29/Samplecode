/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.UIProjects.JSErrorPageWise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;


public class JSErrorHelper {
		public static void generateBodyFileForHTMLReport(WebDriver driver, String page, Map<String, String> map, String operation)throws IOException{
			File file =new File(System.getProperty("user.dir")+"/src/test/resources/"+"HTMLReportBody.html");
			 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileBody = new FileWriter(file.getName(),true);
			BufferedWriter bw = new BufferedWriter(fileBody);
			
			if(map.size()>0){
			for (Map.Entry<String, String> entry : map.entrySet()) {
				bw.write("<tr>"
						+"<td align=\"center\" width=\"15%\">"+ page +"</td>"
						+"<td align=\"center\" width=\"30%\">"+ entry.getKey().toString() +"</td>"
						+"<td align=\"center\" width=\"45%\"><a href=\""+ entry.getValue().toString() +"\">"+ entry.getValue().toString() +"</a></td>"
						+"<td align=\"center\" width=\"10%\"><img id=\""+operation+"\" src=\""+System.getProperty("user.dir")+"/src/test/resources/"+operation+".jpg\""+" "+"class=\"magnify\"  width=\"40\" height=\"20\"></td>"  /*\"onclick=\"changeImage(this)\"*/
						+"</tr>");
				System.out.println("entry.getValue().toString() = "+entry.getValue().toString());
				}
			bw.write("<br>");
			bw.close();
			fileBody.close();
			}
		}
		
		
		public static void generateHTMLReport() throws IOException
		{
			FileWriter f = new FileWriter(System.getProperty("user.dir")+"/src/test/resources/"+"HTMLReport.html", false);
			BufferedWriter bw = new BufferedWriter(f);

			bw.write("<html>");
			bw.write("<head>"
                        +"<meta charset=\"utf-8\">"
                        +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
                        +"<link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\">"
                        );
			bw.write("<script type=\"text/javascript\" src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js\"></script>"
					+"<script type=\"text/javascript\" src=\"jquery.magnifier.js\">");
			bw.write("</script>");
			bw.write("<style>"
						+"table {" 
								+"display: table;"
								+"border-collapse: separate;"
								+"border-spacing: 2px;"
								+"text-overflow: ellipsis;"
						+"}"
						+"body {"
								+"background-color: linen;"
						+"}"		
					+"</style>");
			bw.write("</head>");
			bw.write("<body>");
			bw.write("<div id=\"chart_div\" align=\"center\"></div>");
			bw.write("<h2 style=\"color:black\" align=\"center\"><b>************** JS Error Report **************</b></h2>");
			bw.write("<h2 style=\"color:green\" align=\"center\"> (Page-Wise) </h2>");
			bw.write("<TABLE BORDER='5' CELLPADDING=0 CELLSPACING=0 align=\"center\" bordercolor=\"green\" style=\"width:100%\">");
			
			FileReader fileBodyReader = new FileReader("HTMLReportBody.html");
			BufferedReader br = new BufferedReader(fileBodyReader);
			String line;
			bw.write("<tr>"
					+"<td align=\"center\" width=\"15%\"><h4 style=\"color:blue\" align=\"center\"><b> Page </b></h4></td>"
					+"<td align=\"center\" width=\"30%\"><h4 style=\"color:blue\" align=\"center\"><b> Operation </b></h4></td>"
					+"<td align=\"center\" width=\"45%\"><h4 style=\"color:blue\" align=\"center\"><b> JSError </b></h4></td>"
					+"<td align=\"center\" width=\"10%\"><h4 style=\"color:blue\" align=\"center\"><b> ScreenShot </b></h4></td>"
					+"</tr>");
			
			while ((line = br.readLine()) != null) {
				 bw.write(line);
			}
			
			br.close();
			bw.write("<br>");
			bw.write("</TABLE>");
			
			bw.write("</body>");
			bw.write("</html>");
			bw.close();
			f.close();
		}
}
