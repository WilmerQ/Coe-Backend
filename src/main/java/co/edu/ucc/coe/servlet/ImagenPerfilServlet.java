/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.servlet;


import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import java.awt.image.BufferedImage;
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
@WebServlet(urlPatterns = {"/imagenperfilservlet"})
public class ImagenPerfilServlet extends HttpServlet {

    @EJB
    private CommonsBean cb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String idDeImagen = req.getParameter("id");
        System.out.println("+++++++++"+idDeImagen);
        try {
            Long l = new Long(idDeImagen);
            Usuario u = (Usuario) cb.getById(Usuario.class, l);
            System.out.println("id "+ l + " nombre usuario: " + u.getNombreUsuario());
            if (u == null) {
                byte[] imagen = (byte[]) req.getSession().getAttribute(idDeImagen);
                resp.setContentType("image");
                resp.setContentLength(imagen.length);
                resp.getOutputStream().write(imagen);
            } else {
                byte[] imagen = u.getImagenperfil();
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
