/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service.reporte;

import co.edu.ucc.coe.model.alerta.HistoricoReporte;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author wilme
 */
@Stateless
@LocalBean
public class LogicaReporteAlerta {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    @EJB
    private CommonsBean cb;

    public void generarReporte() {
        Map mapPersonalizado = new HashMap();
        mapPersonalizado.put("nombrealerta", "alerta Roja desde EJB");
        URL url = getClass().getResource("/reporte/reportealerta.jasper");
        System.out.println("url: " + url.toString());
        InputStream is = null;
        try {
            is = url.openStream();
            JasperPrint jasperPrint = JasperFillManager.fillReport(is, mapPersonalizado);
            HistoricoReporte reporte = new HistoricoReporte();
            java.util.Date d = new java.util.Date();
            reporte.setHoraServidor(d);
            reporte.setContenidoPdf(JasperExportManager.exportReportToPdf(jasperPrint));
            if (cb.guardar(reporte)) {
                System.out.println("----" + reporte.getContenidoPdf().length);
            }
        } catch (JRException ex) {
            System.out.println("JRException");
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(LogicaReporteAlerta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(LogicaReporteAlerta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public HistoricoReporte buscarUltimoReporte() {
        HistoricoReporte hr = null;
        List<HistoricoReporte> list;
        try {
            list = (List<HistoricoReporte>) em.createQuery("SELECT hr FROM HistoricoReporte hr ORDER BY hr.horaServidor DESC").getResultList();
            hr = list.get(0);
        } catch (Exception e) {
        }
        return hr;
    }
}
