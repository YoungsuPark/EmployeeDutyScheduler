package kr.co.ahope.common.utils;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import kr.co.ahope.employee.controller.EmplController;

public class MailUtil {

	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);
	
	@Autowired
	private JavaMailSender mailSender;

	private String from ;
	private String to;
	private String subject;
	private String content;

	public void mailSender() {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(from);
			messageHelper.setTo(to);
			messageHelper.setSubject(subject); 
			messageHelper.setText(content);
			
			mailSender.send(message);
			
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.debug("email Exception : " + e1);
		}
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setContent(String content) {
		this.content = content;
	}
}