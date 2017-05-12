/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.alerta;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author wilme
 */
@Entity
public class HistoricoReporte extends CamposComunesdeEntidad implements Serializable {

    private String idManual;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date horaServidor;
    @Lob
    private byte[] contenidoPdf;

    public String getIdManual() {
        return idManual;
    }

    public void setIdManual(String idManual) {
        this.idManual = idManual;
    }

    public Date getHoraServidor() {
        return horaServidor;
    }

    public void setHoraServidor(Date horaServidor) {
        this.horaServidor = horaServidor;
    }

    public byte[] getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(byte[] contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
    }
}
