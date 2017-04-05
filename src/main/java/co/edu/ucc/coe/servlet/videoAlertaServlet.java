/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.servlet;

import co.edu.ucc.coe.model.alerta.AlertaManual;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilme
 */
@WebServlet(urlPatterns = {"/videoalertaservlet"})
public class videoAlertaServlet extends HttpServlet {

    @EJB
    private CommonsBean cb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idDeImagen = req.getParameter("id");
        System.out.println("+++++++++" + idDeImagen);
        try {
            Long l = new Long(idDeImagen);
            AlertaManual am = (AlertaManual) cb.getById(AlertaManual.class, l);
            System.out.println("id " + l + " nombre usuario que reporto " + am.getNombrePersona());
            System.out.println("id " + l + " tamaño foto " + am.getVideo().length);
            if (am != null) {
                byte[] video = am.getVideo();
                resp.setContentType("video");
                resp.setContentLength(video.length);
                resp.getOutputStream().write(video);
            }
        } catch (IOException | NumberFormatException e) {

        }
        //super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
