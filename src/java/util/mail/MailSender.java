package util.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase que se encarga de enviar correos 
 * @author alberto
 */
public  class MailSender {

    private final  String puerto;
    private final  String host;
    private final String remitente;
    private final String password;
    private Properties propiedades;
    private Session sesion;
    
    /**
     * Constructor
     */
    public MailSender() {
        Properties map = getMailProperties();
        this.puerto = map.getProperty("correoPuerto");
        this.host = map.getProperty("correoHost");
        this.remitente = map.getProperty("correoUsuario");
        this.password = map.getProperty("correoPassword");
        propiedades = new Properties();
        setProperties();
        sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

    }
    
//
//    private String getHtml(List<String> cuerpo) {
//        String cabecera = "<table style=\"border-color:#006\" width=\"600\" border=\"0\">"
//                + "<tr style=\"background-color:\">"
//                + "<th scope=\"col\"><font face=\"Chiller\"><img src=\"http://www.mejorpersona.com.mx/LogoMejorPersona.png\" width=\"150\" height=\"150\" /></font></th>"
//                + "</tr>"
//                + "<tr>"
//                + "<td>";
//        
//        String fin = "</td>"
//                + "</tr>"
//                + "<tr>"
//                + "<td><a href=\"http://www.mejorpersona.com.mx\">www.mejorpersona.com.mx</a></td>"
//                + "</tr>"
//                + "</table>";
//
//        String centro = "";
//        for (String s : cuerpo) {
//            centro += "<p style=\"color:#003\"><font>" + s + "</font></p>";
//        }
//
//        return cabecera + centro + fin;
//    }

    public MailSender(String host, final String remitente, final String password, final String puerto) {
        this.puerto = puerto;
        this.host = host;
        this.remitente = remitente;
        this.password = password;
        propiedades = new Properties();
        setProperties();
        sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });
    }

    public boolean sendMail(String[] destiny, String subject, String htmlMessage) {
        try {
            MimeMessage message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(remitente));
            addDestiny(message, destiny);
            message.setSubject(subject);
            message.setContent(htmlMessage, "text/html");
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private void addDestiny(MimeMessage message, String[] destiny) {
        for (String s : destiny) {
            try {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(s));
            } catch (MessagingException ex) {
                Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void setProperties() {

        propiedades.put("mail.smtp.host", host);
        propiedades.put("mail.smtp.user", remitente);
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.password", password);
        propiedades.setProperty("mail.smtp.port", puerto);
    }

    private Properties getMailProperties() {
           Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("mail.properties"));
        } catch (IOException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }
}
