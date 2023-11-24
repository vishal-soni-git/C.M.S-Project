import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */

public class Mail{
    public static void sendEmail(String message, String subject, String to, String from) {
        String host = "smtp.gmail.com";

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", true);

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cinestar569@gmail.com", "zxnr jvrn whpe hsuc ");
            }
        };

        // get the seesion object
        // Session session = Session.getInstance(prop, new Authenticator() {
        // protected PasswordAuthentication getPasswordAuthentication() {
        // return new PasswordAuthentication("vso55089@gmail.com", "soni2580");
        // }
        // });

        Session session = Session.getInstance(prop, auth);
        // compose the message

        session.setDebug(true);

        MimeMessage mime = new MimeMessage(session);

        try {
            mime.setFrom(from);
            mime.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // adding subject to message
            mime.setSubject(subject);

            // adding text to message
            mime.setText(message);

            // send the message using transport class

            Transport.send(mime);

            System.out.println("sent...");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
