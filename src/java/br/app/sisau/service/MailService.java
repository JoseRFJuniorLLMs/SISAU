package br.app.sisau.service;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Herick
 */
public class MailService {

    private static MailService instance = new MailService();
    private Logger logger = Logger.getLogger(this.getClass());
    private String username = "sisau2012@gmail.com";
    private String password = "debian23";
    private String host = "smtp.gmail.com";
    /*
     * Metodo construtor privado, para evitar multipla instanciacao
     */

    private MailService() {
    }

    public static MailService getInstance() {
        return instance;
    }

    /**
     * Send mails to
     *
     * @param subject
     * @param sendTo
     * @param message
     */
    public void sendMail(final String subject, final String sendTo, final String message) {
        Thread sendMail = new Thread() {

            @Override
            public void run() {
                Properties p = new Properties();
                p.put("mail.smtp.host", host);
                p.put("mail.smtp.starttls.enable", "true");
                p.put("mail.smtp.auth", "true");

                Authenticator auth = new SMTPAuthenticator();

                Session session = Session.getInstance(p, auth);
                MimeMessage msg = new MimeMessage(session);

                try {
                    // "de" e "para"!!
                    msg.setFrom(new InternetAddress(username));
                    msg.setRecipients(Message.RecipientType.TO, sendTo);

                    msg.setSentDate(new Date());
                    msg.setSubject(subject);
                    msg.setText(message);
                    //Para envio de HTML
                    //msg.setContent(message, "text/html");

                    // enviando mensagem (tentando)
                    Transport.send(msg);
                    getLogger().debug("Email Enviado com Sucesso!!!");
                } catch (AddressException e) {
                    // nunca deixe catches vazios!
                    getLogger().debug("Address Exception - " + e.getMessage());
                } catch (MessagingException e) {
                    // nunca deixe catches vazios!
                    getLogger().debug("Messaging Exception - " + e.getMessage());
                }
            }
        };
        sendMail.start();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    private class SMTPAuthenticator extends Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }
}
