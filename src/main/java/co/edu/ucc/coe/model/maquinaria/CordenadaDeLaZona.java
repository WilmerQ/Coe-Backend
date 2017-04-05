/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.primefaces.model.map.Marker;

/**
 *
 * @author wilme
 */
@Entity
public class CordenadaDeLaZona extends CamposComunesdeEntidad implements Serializable {

    private String latitud;
    private String longitud;
    private Integer orden;
    @Transient
    private Marker marker;
    private String idManualActividadVehiculo;

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

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public String getIdManualActividadVehiculo() {
        return idManualActividadVehiculo;
    }

    public void setIdManualActividadVehiculo(String idManualActividadVehiculo) {
        this.idManualActividadVehiculo = idManualActividadVehiculo;
    }

}
