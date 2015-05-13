/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class SendEmail
{

	public void setEmail(String receiver)
	{
		String recipient = receiver;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(
								"himanshu_verma_29@yahoo.com", "YAm@7861");
					}
				});
		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("himanshu_verma_29@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			message.setSubject("JS Error Report");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart.setText("Hi All, \n");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir") +"/src/test/resources/"
					+ "HTMLReport.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("JSErrorReport.html");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			String mess = "";
			try
			{
				mess = FileUtils.readFileToString(new File(filename));
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			messageBodyPart1.setContent(mess, "text/html; charset=utf-8");
			messageBodyPart.setText("Regards, \n");
			messageBodyPart.setText("Himanshu Verma. \n");
			multipart.addBodyPart(messageBodyPart1);

			Transport.send(message);

			System.out.println("Done");

		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static void serverLogSendMail(String receiver, String fileName)
	{
		String recipient = receiver;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(
								"himanshu_verma_29@yahoo.com", "qatesting");
					}
				});
		try
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("automation.set@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(recipient));
			message.setSubject("Mobile Tomcat Server Log:UIAutomationWap");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPart1 = new MimeBodyPart();

			messageBodyPart.setText("Hi All, \n\n Please find attached Mobile tomcat server log. ");
			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir") +"/tomcatlogs/"
					+ fileName;
		
			//4) create new MimeBodyPart object and set DataHandler object to this object      
		    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
		   
		    DataSource source = new FileDataSource(filename);  
		    messageBodyPart2.setDataHandler(new DataHandler(source));  
		    messageBodyPart2.setFileName(fileName);  
			
		  //5) create Multipart object and add MimeBodyPart objects to this object      
		    multipart.addBodyPart(messageBodyPart2);  
		    //6) set the multiplart object to the message object  
		    message.setContent(multipart );  
			Transport.send(message);
			System.out.println("Done");

		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}
}
