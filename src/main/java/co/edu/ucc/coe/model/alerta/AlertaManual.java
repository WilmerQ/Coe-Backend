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
public class AlertaManual extends CamposComunesdeEntidad implements Serializable {

    private String idManual;
    private String nombrePersona;
    private String tipoEvento;
    private String celular;
    private String correo;
    private Double latitud;
    private Double longitud;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date horaServidor;
    private String horaDispositivo;
    @Lob
    private byte[] foto;
    @Lob
    private byte[] video;
    @Lob
    private byte[] audio;

    public String getIdManual() {
        return idManual;
    }

    public void setIdManual(String idManual) {
        this.idManual = idManual;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }
}
