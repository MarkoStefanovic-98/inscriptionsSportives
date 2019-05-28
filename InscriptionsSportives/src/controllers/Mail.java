package controllers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mail {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

//    private static final String host = "smtp.gmail.com";
//    private static final String port = "465";
//    private static final String login = "bvasseur77@gmail.com";
//    private static final String password = "kseqqkrtbynjbaak";

    private Properties properties;
    private Session session;

    private static Mail INSTANCE = null; // Utiliser avec le singletion

    private Mail() {}

    public static Mail getInstance()  {
        if (INSTANCE == null) {
            INSTANCE = new Mail();
        }
        return INSTANCE;
    }

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX . matcher(emailStr);
        return matcher.find();
    }

    public void initConnection(){
    	
    	ResourceBundle config = ResourceBundle.getBundle("config.config");
    	
        properties = System.getProperties();
        properties.put("mail.smtp.host", config.getString("mail.host"));
        properties.put("mail.smtp.socketFactory.port", config.getString("mail.port"));
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", config.getString("mail.port"));

        setSession(Session.getDefaultInstance(properties,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(config.getString("mail.login"), config.getString("mail.password"));
                }
            })
        );
    }

    public static void sendMail(String recipient, String subject, String text) {
        Mail mail = getInstance();
        if ( validateEmail(recipient) ) {
            mail.initConnection();

            try {
            	ResourceBundle config = ResourceBundle.getBundle("config.config");
            	
                Message message = new MimeMessage(mail.session);
                message.setFrom(new InternetAddress(config.getString("mail.sender")));
                message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(recipient) );
                message.setSubject(subject);
                message.setText(text);

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void setSession(Session session) {
        this.session = session;
    }
}
