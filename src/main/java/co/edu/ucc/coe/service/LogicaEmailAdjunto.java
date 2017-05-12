/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import java.io.File;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author wilme
 */
@Stateless
public class LogicaEmailAdjunto {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void EnviarEmail() {
        // SMTP info
        System.out.println("inicio Envio email con adjunto");
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "soporte.sipnat@gmail.com";
        String password = "sipnat.2016";

        // message info
        String mailTo = "wilmer.quintero@outlook.com";
        String subject = "New email with attachments";
        String message = "<div style=\"text-align: center;\"><img src=\"http://www.colciencias.gov.co/sites/default/files/logo_0.png\" style=\"font-size: 10pt;\"><span style=\"font-size: 10pt;\">&nbsp;&nbsp;</span><img src=\"http://clinicaucc.com.co/images/ucc.png\" style=\"font-size: 10pt;\"></div><hr id=\"null\"><p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Santa Marta D. T. C.\n"
                + "H., Marzo 14 de 2017.<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Señores<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><b><span style=\"font-size:12.0pt\">CONSEJO MUNICIPAL DE GESTION DEL RIESGO <o:p></o:p></span></b></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Santa Marta<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" align=\"center\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;\n"
                + "text-align:center;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Cordial saludo,<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">El Sistema Nacional\n"
                + "de Gestión del Riesgo de Desastres de Colombia se ha ido posicionando a nivel\n"
                + "nacional e internacional como un sistema sólido y ajustado a las necesidades\n"
                + "del territorio, con lo cual, tanto las administraciones municipales como las\n"
                + "comunidades han participado de manera activa en el proceso de implementación de\n"
                + "la Ley 1523 de 2012, desde la comprensión de sus procesos y la importancia de\n"
                + "asumir la corresponsabilidad como un aporte al desarrollo local.<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Dicho postulado,\n"
                + "está soportado no sólo en la normatividad nacional, sino también en los\n"
                + "acuerdos internacionales, dentro del cual se resalta el Marco de Acción de Sendai,\n"
                + "el cual, a través de sus cuatro prioridades de acción, nos invita a articular\n"
                + "los procesos de la gestión del riesgo desde la comprensión de sus riesgos, pero\n"
                + "también de las políticas locales y las prácticas en cada uno de ellos, de\n"
                + "manera que se fortalezca la acción en cada uno de los niveles de gobierno y se\n"
                + "empodere a la ciudadanía y sus organizaciones, para mejorar no sólo las\n"
                + "acciones de respuesta, sino también las de recuperación, rehabilitación y\n"
                + "reconstrucción.<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">En este sentido,\n"
                + "cobra vital importancia el trabajo conjunto que se ha desarrollado frente a los\n"
                + "Sistemas de Alerta Temprana – SAT - en el país, reconociendo la importancia del\n"
                + "relacionamiento técnico y el comunitario, de manera que pueda realizarse el\n"
                + "monitoreo del evento y se activen los canales de comunicación establecidos; por\n"
                + "lo tanto, el presente documento se convierte en una herramienta fundamental en\n"
                + "las acciones a implementar a nivel territorial frente al diseño y la puesta en\n"
                + "marcha de SAT, evidenciando la necesidad de enlazar los procesos de la gestión\n"
                + "del riesgo, en función de contribuir en la consecución de una Colombia menos\n"
                + "vulnerable, con comunidades más resilientes.<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Los SAT permiten\n"
                + "“facultar a las personas y comunidades que enfrentan un amenaza&nbsp; para que actúen con suficiente tiempo y de\n"
                + "manera adecuada para reducir la posibilidad de que se produzcan lesiones\n"
                + "personales, pérdidas de vidas humanas y daños a los bienes y el medio\n"
                + "ambiente”.<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">La alerta temprana\n"
                + "les da respaldo técnico a las comunidades o individuos para actuar con tiempo\n"
                + "suficiente y de una manera apropiada para reducir la posibilidad de daño\n"
                + "personal, pérdida de vidas, daños a la propiedad&nbsp; y al ambiente ante una amenaza o evento\n"
                + "adverso que puede desencadenar situaciones potencialmente peligrosas.&nbsp; <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Para lo cual, desde\n"
                + "los lineamientos internacionales, la UNISDR (NOAA) propone cuatro componentes\n"
                + "fundamentales:<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">1. Detección y\n"
                + "pronóstico de amenazas <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">2. Evaluación de los\n"
                + "riesgos e integración de la información <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">3. Divulgación\n"
                + "oportuna, confiable y comprensible <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">4. Planificación,\n"
                + "preparación y capacitación para la respuesta en todo nivel&nbsp;&nbsp;&nbsp; (institucional y comunitario)<o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">Por lo anterior, nos\n"
                + "permitimos remitir el reporte suministrado por nuestro Sistema de Alerta\n"
                + "Temprana en el día de hoy a las 7:40 am. <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">&nbsp;</span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">SISTEMA ALERTA\n"
                + "TEMPRANA <o:p></o:p></span></p>\n"
                + "\n"
                + "<p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
                + "justify;line-height:normal\"><span style=\"font-size:12.0pt\">UNIVERSIDAD\n"
                + "COOPERATIVA DE COLOMBIA &amp;&amp; COLCIENCIAS<o:p></o:p></span></p><hr id=\"null\">";
        
// attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = "D:/raid.txt";
        System.out.println("------" + attachFiles[0]);
        //attachFiles[2] = "e:/Test/Video.mp4";

        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
                try {
                    File file = new File(filePath);
                    System.out.println("---------file: " + file.getName());
                    System.out.println("---------file: " + file.getAbsolutePath());
                    attachPart.attachFile(file);
                    System.out.println("----------------------------");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                multipart.addBodyPart(attachPart);
            }
        }
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
