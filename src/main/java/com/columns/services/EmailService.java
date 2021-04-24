package com.columns.services;

import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.columns.models.Params;

@Repository
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;

	// @Override
	public boolean sendEmail(Params params)  {
		String email = params.getEmail();
		String name = params.getName();
		String message = params.getMessage();
		String messageToSend = "Nombre del usuario: " + name + " - Mail del usuario: " + email + " - Mensaje: " + message;
		return sendEmailTool(messageToSend, "soyjuanmedina@gmail.com", "Mensaje desde Columns");
	}
	

	private boolean sendEmailTool(String textMessage, String email,String subject) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		
		try {
			helper.setTo(email);
			helper.setText(textMessage, true);
			helper.setSubject(subject);
			sender.send(message);
			send = true;
			System.out.println("Mail enviado!");
		} catch (MessagingException e) {
			System.out.println("Hubo un error al enviar el mail");
		}
		return send;
	}	
}