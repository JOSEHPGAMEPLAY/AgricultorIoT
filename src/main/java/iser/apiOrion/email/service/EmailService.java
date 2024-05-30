package iser.apiOrion.email.service;

import java.io.File;
import java.io.IOException;

public interface EmailService {

    void sendMail(String[] to, String subject, String text);

    void sendMail(String[] to, String subject, String text, File file) throws IOException;

}
