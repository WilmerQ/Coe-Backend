/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author wilme
 */
@Entity
public class TipoMaquinaria extends CamposComunesdeEntidad implements Serializable {

    private String nombremaquina;
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String categoriaVehiculo;
    private String unidadCarga;
    private String capacidadCarga;
    private String unidadMedicion;

    public String getNombremaquina() {
        return nombremaquina;
    }

    public void setNombremaquina(String nombremaquina) {
        this.nombremaquina = nombremaquina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoriaVehiculo() {
        return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(String categoriaVehiculo) {
        this.categoriaVehiculo = categoriaVehiculo;
    }

    public String getUnidadCarga() {
        return unidadCarga;
    }

    public void setUnidadCarga(String unidadCarga) {
        this.unidadCarga = unidadCarga;
    }

    public String getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(String capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public String getUnidadMedicion() {
        return unidadMedicion;
    }

    public void setUnidadMedicion(String unidadMedicion) {
        this.unidadMedicion = unidadMedicion;
    }

}
