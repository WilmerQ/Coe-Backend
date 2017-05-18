/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.alerta;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import co.edu.ucc.coe.model.maquinaria.TipoMaquinaria;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author wilme
 */
@Entity
public class Sensor extends CamposComunesdeEntidad implements Serializable {

    private Long idNativo;
    private String latitud;
    private String longitud;
    private String nombreTipoSensor;
    private String descripcion;
    private String estadoActual;
    @ManyToOne
    private TipoSensor idTipoSensor;

    public Long getIdNativo() {
        return idNativo;
    }

    public void setIdNativo(Long idNativo) {
        this.idNativo = idNativo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getNombreTipoSensor() {
        return nombreTipoSensor;
    }

    public void setNombreTipoSensor(String nombreTipoSensor) {
        this.nombreTipoSensor = nombreTipoSensor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public TipoSensor getIdTipoSensor() {
        return idTipoSensor;
    }

    public void setIdTipoSensor(TipoSensor idTipoSensor) {
        this.idTipoSensor = idTipoSensor;
    }

}
