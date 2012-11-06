package org.gvlabs.logger.impl;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import org.gvlabs.logger.Logger;
import org.gvlabs.logger.engine.LoggerLevel;

public class MailLoggerImpl extends Logger {
	
	private String prefix;
	
	public MailLoggerImpl(LoggerLevel maxLogLevel, String prefix) {
		super(maxLogLevel, prefix);
		this.prefix = prefix;
	}



	@Override
	public void log(LoggerLevel level, String msg, Throwable e) {
//		try {
//			send("hostname", 25, "joe@smith.com", "sue@smith.com",
//					"re: dinner", "How about at 7?");
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

	}

//	public static void send(String smtpHost, int smtpPort, String from,
//			String to, String subject, String content) throws AddressException,
//			MessagingException {
//		// Create a mail session
//		java.util.Properties props = new java.util.Properties();
//		props.put("mail.smtp.host", smtpHost);
//		props.put("mail.smtp.port", "" + smtpPort);
//		Session session = Session.getDefaultInstance(props, null);
//
//		// Construct the message
//		Message msg = new MimeMessage(session);
//		msg.setFrom(new InternetAddress(from));
//		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//		msg.setSubject(subject);
//		msg.setText(content);
//
//		// Send the message
//		Transport.send(msg);
//	}
}
