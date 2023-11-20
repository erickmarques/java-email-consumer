package br.com.erickmarques.service;

import org.springframework.stereotype.Service;

import br.com.erickmarques.representation.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {

	@Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(Email email) throws MessagingException {
        
    	MimeMessage message = javaMailSender.createMimeMessage();
    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

    	mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(email.getBody());

        javaMailSender.send(message);
    }
}