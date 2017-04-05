/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.clases;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author wilme
 */
public class AlertaManualGcm implements Serializable {

    private Long id;
    private String nombrePersona;
    private String tipoEvento;
    private String celular;
    private String correo;
    private Double latitud;
    private Double longitud;
    private Date horaServidor;
    private String horaDispositivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Date getHoraServidor() {
        return horaServidor;
    }

    public void setHoraServidor(Date horaServidor) {
        this.horaServidor = horaServidor;
    }

    public String getHoraDispositivo() {
        return horaDispositivo;
    }

    public void setHoraDispositivo(String horaDispositivo) {
        this.horaDispositivo = horaDispositivo;
    }

}
