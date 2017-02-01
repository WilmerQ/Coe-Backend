/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 *
 * @author wilme
 */
@Entity
public class adjunto extends CamposComunesdeEntidad implements Serializable {

    private String idmanual;
    @Lob
    private byte[] contenido;
    private String nombreArchivo;

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public String getIdmanual() {
        return idmanual;
    }

    public void setIdmanual(String idmanual) {
        this.idmanual = idmanual;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}
