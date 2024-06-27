package iser.apiOrion.email.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Value("${email.mail:null}")
    String mail;

    @Value("${email.password:null}")
    String password;

    @Value("${email.host:null}")
    String host;

    @Value("${email.port:0}")
    int port;

    @Value("${email.protocol:null}")
    String protocol;

    @Value("${email.auth:false}")
    boolean auth;

    @Value("${email.starttls:false}")
    boolean starttls;

    @Value("${email.debug:false}")
    boolean debug;


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
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(mail);
        sender.setPassword(password);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);

        return sender;
    }

}
