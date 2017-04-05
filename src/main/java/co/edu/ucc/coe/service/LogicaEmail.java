/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.base.EnviarMailDesdeGmail;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilme
 */
@Stateless
@LocalBean
public class LogicaEmail {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public void EnviarEmail(String Asunto) {
        System.out.println("inicio Envio email");
        //EnviarMailDesdeGmail e = new EnviarMailDesdeGmail();
        List<String> destino = new LinkedList<>();
        destino.add("wilmer.quintero@outlook.com");
        destino.add("wilmer.quintero@campusucc.edu.co");
        destino.add("cuases@hotmail.com");
        List<String> destinocc = new LinkedList<>();
        List<String> destinobcc = new LinkedList<>();
        //String texto = "<div style=\"text-align: center;\">&nbsp; &nbsp; &nbsp;&nbsp;<img src=\"http://www.colciencias.gov.co/sites/default/files/logo_0.png\">&nbsp; &nbsp;<img src=\"http://clinicaucc.com.co/images/ucc.png\" style=\"font-size: 10pt;\"></div><div style=\"text-align: left;\"><br></div><div style=\"text-align: left;\"><span style=\"font-family: &quot;Times New Roman&quot;; font-size: medium;\">Santa Marta D. T. C. H., Marzo 14 de 2017.</span></div><div style=\"text-align: center; font-size: 10pt;\"><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Señores</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">CONSEJO MUNICIPAL DE GESTIÓN DEL RIESGO</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Santa Marta</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Cordial saludo,</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">El Sistema Nacional de Gestión del Riesgo de Desastres de Colombia se ha ido posicionando a nivel nacional e internacional como un sistema sólido y ajustado a las necesidades del territorio, con lo cual, tanto las administraciones municipales como las comunidades han participado de manera activa en el proceso de implementación de la Ley 1523 de 2012, desde la comprensión de sus procesos y la importancia de asumir la corresponsabilidad como un aporte al desarrollo local.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Dicho postulado, está soportado no sólo en la normatividad nacional, sino también en los acuerdos internacionales, dentro del cual se resalta el Marco de Acción de Sendai, el cual, a través de sus cuatro prioridades de acción, nos invita a articular los procesos de la gestión del riesgo desde la comprensión de sus riesgos, pero también de las políticas locales y las prácticas en cada uno de ellos, de manera que se fortalezca la acción en cada uno de los niveles de gobierno y se empodere a la ciudadanía y sus organizaciones, para mejorar no sólo las acciones de respuesta, sino también las de recuperación, rehabilitación y reconstrucción.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">En este sentido, cobra vital importancia el trabajo conjunto que se ha desarrollado frente a los Sistemas de Alerta Temprana – SAT - en el país, reconociendo la importancia del relacionamiento técnico y el comunitario, de manera que pueda realizarse el monitoreo del evento y se activen los canales de comunicación establecidos; por lo tanto, el presente documento se convierte en una herramienta fundamental en las acciones a implementar a nivel territorial frente al diseño y la puesta en marcha de SAT, evidenciando la necesidad de enlazar los procesos de la gestión del riesgo, en función de contribuir en la consecución de una Colombia menos vulnerable, con comunidades más resilientes.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Los SAT permiten “facultar a las personas y comunidades que enfrentan un amenaza para que actúen con suficiente tiempo y de manera adecuada para reducir la posibilidad de que se produzcan lesiones personales, pérdidas de vidas humanas y daños a los bienes y el medio ambiente”.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">La alerta temprana les da respaldo técnico a las comunidades o individuos para actuar con tiempo suficiente y de una manera apropiada para reducir la posibilidad de daño personal, pérdida de vidas, daños a la propiedad y al ambiente ante una amenaza o evento adverso que puede desencadenar situaciones potencialmente peligrosas.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Para lo cual, desde los lineamientos internacionales, la UNISDR (NOAA) propone cuatro componentes fundamentales:</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">1. Detección y pronóstico de amenazas</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">2. Evaluación de los riesgos e integración de la información</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">3. Divulgación oportuna, confiable y comprensible</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">4. Planificación, preparación y capacitación para la respuesta en todo nivel (institucional y comunitario)</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">Por lo anterior, nos permitimos remitir el reporte suministrado por nuestro Sistema de Alerta Temprana en el día de hoy a las 7:40 am.</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\"></p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">SISTEMA ALERTA TEMPRANA</p><p style=\"font-family: &quot;Times New Roman&quot;; font-size: medium; text-align: start;\">UNIVERSIDAD COOPERATIVA DE COLOMBIA && COLCIENCIAS</p></div>";
        String texto = "<div style=\"text-align: center;\"><img src=\"http://www.colciencias.gov.co/sites/default/files/logo_0.png\" style=\"font-size: 10pt;\"><span style=\"font-size: 10pt;\">&nbsp;&nbsp;</span><img src=\"http://clinicaucc.com.co/images/ucc.png\" style=\"font-size: 10pt;\"></div><hr id=\"null\"><p class=\"MsoNormal\" style=\"margin-bottom:0cm;margin-bottom:.0001pt;text-align:\n"
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
        enviarCorreoelectronico(destino, destinocc, destinobcc, Asunto, texto);
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
