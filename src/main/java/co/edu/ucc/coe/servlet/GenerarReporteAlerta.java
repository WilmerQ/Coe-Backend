/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.servlet;

import co.edu.ucc.coe.model.alerta.HistoricoReporte;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.reporte.LogicaReporteAlerta;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/GenerarReporteAlerta"})
public class GenerarReporteAlerta extends HttpServlet {

    @EJB
    private CommonsBean cb;

    @EJB
    private LogicaReporteAlerta lra;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        response.setContentType("application/pdf");
        HistoricoReporte reporte = (HistoricoReporte) lra.buscarUltimoReporte();
        System.out.println("id: " + reporte.getId());
        response.setContentLength(reporte.getContenidoPdf().length);
        response.getOutputStream().write(reporte.getContenidoPdf());
    }

}
