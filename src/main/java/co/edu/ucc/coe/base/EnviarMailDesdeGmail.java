/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author wilme
 */
public class EnviarMailDesdeGmail {

    public static void main(String[] args) {
        System.out.println("inicio Envio email");
        EnviarMailDesdeGmail e = new EnviarMailDesdeGmail();
        List<String> destino = new LinkedList<>();
        destino.add("wilmer.quintero@outlook.com");
        List<String> destinocc = new LinkedList<>();
        List<String> destinobcc = new LinkedList<>();
        //destinobcc.add("danilo.juvinao@campusucc.edu.co");
        String texto = "<h2>Prueba de regional1</h2><br/>"
                + "bla bla bla<br/><hr/>Prueba en clase";
        e.enviarCorreoelectronico(destino, 
                destinocc, destinobcc, 
                "Asunto de prueba", texto);
    }

    public void enviarCorreoelectronico(
            List<String> destinatarioTo, 
            List<String> destinatarioCC, 
            List<String> destinatarioBCC, 
            String asunto, String cuerpoMensaje) {
        try {
            Properties props = new Properties();
            String host = "smtp.gmail.com";
            String username = "soporte.sipnat@gmail.com";
            String password = "sipnat.2016";
            props.put("mail.smtps.auth", "true");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            // Quien envia el correo
            message.setFrom(new InternetAddress("soporte.sipnat@gmail.com"));

            for (int i = 0; i < destinatarioTo.size(); i++) {
                if (destinatarioTo.get(i) != null) {
                    message.addRecipient(Message.RecipientType.TO, 
                            new InternetAddress(
                            destinatarioTo.get(i).toString()));
                }
            }
            
            for (int i = 0; i < destinatarioCC.size(); i++) {
                if (destinatarioCC.get(i) != null) {
                    message.addRecipient(Message.RecipientType.CC, 
                            new InternetAddress(
                            destinatarioCC.get(i).toString()));
                }
            }
            for (int i = 0; i < destinatarioBCC.size(); i++) {
                if (destinatarioBCC.get(i) != null) {
                    message.addRecipient(Message.RecipientType.BCC, 
                            new InternetAddress(
                            destinatarioBCC.get(i).toString()));
                }
            }
            message.setSubject(asunto);
            message.setContent(cuerpoMensaje.toString(), "text/html");
            Transport t = session.getTransport("smtps");
            t.connect(host, username, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
