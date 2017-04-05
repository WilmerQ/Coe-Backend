/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.servlet;


import co.edu.ucc.coe.model.adjunto;
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
@WebServlet(urlPatterns = {"/imagenadjuntoxid"})
public class ImagenAdjunto extends HttpServlet {

    @EJB
    private CommonsBean cb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idDeImagen = req.getParameter("id");
        System.out.println("+++++++++" + idDeImagen);
        try {

            adjunto u = (adjunto) cb.getByOneFieldWithOneResult(adjunto.class, "idmanual", idDeImagen);
            System.out.println("id " + idDeImagen + " objeto " + u.getNombreArchivo());
            if (u == null) {
                
            } else {
                byte[] imagen = u.getContenido();
                resp.setContentType("image");
                resp.setContentLength(imagen.length);
                resp.getOutputStream().write(imagen);
            }
        } catch (Exception e) {
            //byte[] imagen = (byte[]) req.getSession().getAttribute(idDeImagen);
            //resp.setContentType("image");
            //resp.setContentLength(imagen.length);
            //resp.getOutputStream().write(imagen);
        }

        // super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
