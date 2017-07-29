package quartz;

import org.slf4j.Logger;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import static org.slf4j.LoggerFactory.getLogger;

public class Mailer {

    private static final Logger LOGGER = getLogger(Mailer.class);

    public static final String HOST = "smtp.gmail.com";
    public static final int PORT = 587;
    public static final String FROM = "testerpeanut@gmail.com";
    public static final String PASSWORD = "testerpeanut12";

    public void sendMail(String to, String subject, String text) {

        // Setup mail server
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        // Get the default Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(text, "text/html; charset=utf-8");

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);
            message.setContent(mp);
            Transport.send(message);

            System.out.println("Sent message successfully....");
            LOGGER.debug("Mail " + subject + " sent to to:" + to + " from " + FROM);

        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
