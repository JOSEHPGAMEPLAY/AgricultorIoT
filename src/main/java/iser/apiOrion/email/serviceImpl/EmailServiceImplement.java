package iser.apiOrion.email.serviceImpl;

import iser.apiOrion.email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImplement implements EmailService {

    /**
     * mailSender :: gmail email server configuration
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * logger :: log for EmailServiceImplement
     */
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    /**
     * sendMail :: send email
     * @param to :: email recipient
     * @param subject :: email subject
     * @param text :: email text
     */
    @Override
    public void sendMail(String[] to, String subject, String text) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper email = new MimeMessageHelper(message, true);

            email.setTo(to);
            email.setSubject(subject);
            email.setText(text, true);

            mailSender.send(message);
        }catch (Exception e){
            logger.error("ERROR_MESSAGE: " + e.getMessage());
        }
    }

    /**
     * sendMail :: send email with attachment
     * @param to :: email recipient
     * @param subject :: email subject
     * @param text :: email text
     * @param file :: file to attach
     */
    @Override
    public void sendMail(String[] to, String subject, String text, File file){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper email = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());

            email.setTo(to);
            email.setSubject(subject);
            email.setText(text, true);
            email.addAttachment(file.getName(), file);

            mailSender.send(message);

        } catch (MessagingException e) {
            logger.error("ERROR_MESSAGE: " + e.getMessage());
        }
    }


}
