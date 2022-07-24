package com.utp.registro_de_productos.provider;

import io.vavr.control.Either;
import jakarta.mail.Message;
import java.util.Properties;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailProvider {

    static final String FROM = "u18104155@utp.edu.pe";
    static final String FROMNAME = "La Vaca";
    static final String SMTP_USERNAME = "apikey";
    static final String SMTP_PASSWORD = "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";
    static final String HOST = "smtp.sendgrid.net";
    static final int PORT = 587;

    static final String SUBJECT = "Notificación de Productos - La Vaca";
    static final String BODY = String.join(
            System.getProperty("line.separator"),
            "<h1>OCI Email Delivery test</h1>",
            "<p>This email was sent with OCI Email Delivery using the ",
            "<a href='Javamail'>https://github.com/javaee/javamail'>Javamail Package</a>",
            " for <a href='Javahttps://www.java.com'>Java</a>."
    );

    public static Either<String, String> sendEmail(String recipientEmail, String body, String subject) throws Exception {
        System.out.println(recipientEmail);
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth.login.disable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        Session session = Session.getDefaultInstance(props);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        msg.setSubject(subject);
        msg.setContent(body, "text/html");

        Transport transport = session.getTransport();
        
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            return Either.right("Email enviado correctamente");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage() );
            return Either.right("Ocurrió un error: " + ex.getMessage());
        } finally {
            transport.close();
        }
    }

    public static void main(String[] args) throws Exception {
        sendEmail("israelgs83@gmail.com", BODY, SUBJECT);
    }

}
