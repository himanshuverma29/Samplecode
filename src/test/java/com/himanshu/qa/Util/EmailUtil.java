/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;

import com.himanshu.qa.Util.FileUtil;

public class EmailUtil {
	
	public static void sendEmail(List<String> reportList,File tmpFile,String receiver, final String sender, final String password)
	   {
		   String recipient	=	receiver;
		   Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender,password);
					}
				});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(sender));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(recipient));
				message.setSubject(FileUtil.getConstantValue("EmailReportSubject"));
				
				// creates message part
		        MimeBodyPart messageBodyPart = new MimeBodyPart();
		        messageBodyPart.setContent(FileUtil.getConstantValue("EmailReportBody"), "text/html");
		 
		        // creates multi-part
		        Multipart multipart = new MimeMultipart();
		        multipart.addBodyPart(messageBodyPart);
		 
		        // adds attachments
		        	
		        //String browserArr[]	=	browser.split(",");
		            for (int i=0;i<reportList.size();i++) {
		                MimeBodyPart attachPart = new MimeBodyPart();
		                //String testngFile	=	reportHeading+browserArr[i];
				         String filename	=	System.getProperty("user.dir")+FileUtil.separator+FileUtil.getConstantValue("TestReport")+FileUtil.separator+FileUtil.getConstantValue("SuiteName")+FileUtil.separator+reportList.get(i)+".html";

		                try {
		                    attachPart.attachFile(filename);
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		 
		                multipart.addBodyPart(attachPart);
		            }
		        message.setContent(multipart);
		        String mess = "";
		         try
				{
					mess = FileUtils.readFileToString(tmpFile);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
				BodyPart messageBodyPart1 = new MimeBodyPart();
		         messageBodyPart1.setContent(mess,"text/html; charset=utf-8");
		         multipart.addBodyPart(messageBodyPart1);
		        Transport.send(message);
		        
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	   }
}
