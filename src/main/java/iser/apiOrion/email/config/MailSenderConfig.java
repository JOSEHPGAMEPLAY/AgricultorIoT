package iser.apiOrion.email.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfig {
    /**
     * javaMailSender :: gmail email server configuration
     * <p>
     *     <strong>Note:</strong> for it to work correctly, access to insecure applications must be enabled
     *     <br/>
     *     <a href="https://myaccount.google.com/lesssecureapps">Enable access to less secure apps</a>
     *     <br/>
     *     <a href="https://accounts.google.com/DisplayUnlockCaptcha">Unlock captcha</a>
     *     <br/>
     *     <a href="https://accounts.google.com/b/0/DisplayUnlockCaptcha">Unlock captcha</a>
     *     <br/>
     *     <a href="https://accounts.google.com/UnlockCaptcha">Unlock captcha</a>
     *     <br/>
     * </p>
     * @return the java mail sender
     */
    @Bean(name = "mailSender")
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("envioc848@gmail.com");
        sender.setPassword("gtfoglkiuqyvguly");

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.debug", true);

        return sender;
    }

}
