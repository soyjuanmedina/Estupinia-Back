package com.estupinia.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;

import com.estupinia.models.User;
import com.estupinia.tools.SiteConstants;

@Repository
public class EmailService {

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender sender;

	public void sendActivation(InputStream input, User user) throws MailException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("baseURL", SiteConstants.MAIL.BASE_URL);
		map.put("registrationURL", SiteConstants.MAIL.REGISTRATION_URL + user.getUuid());
		String content = fillMailContentData(input, map);
		String subject = "Confirma tu mail para acceder a Coolumns";
		sendEmailTool(content, user.getEmail(), subject);
	}

	public boolean sendEmailTool(String textMessage, String email, String subject) {
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

	protected String fillMailContentData(InputStream input, Map<String, String> map) {
		try {
			StringBuilder content = new StringBuilder();
			String theString = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			while ((theString = reader.readLine()) != null) {
				content.append(theString);
			}
			input.close();
			Pattern fileTagPattern = Pattern.compile("\\{\\{(.*?)\\}\\}", Pattern.DOTALL | Pattern.MULTILINE);
			Matcher matcher = fileTagPattern.matcher(content);
			StringBuffer sb = new StringBuffer(content.length());
			while (matcher.find()) {
				String found = matcher.group(1);
				matcher.appendReplacement(sb, map.get(found));
			}
			matcher.appendTail(sb);
			return sb.toString();
		} catch (IOException ex) {
			log.error("fillMailContentData", ex);
		}
		return null;
	}
}