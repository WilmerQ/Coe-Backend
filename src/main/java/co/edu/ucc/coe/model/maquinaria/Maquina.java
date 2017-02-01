/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author wilme
 */
@Entity
public class Maquina extends CamposComunesdeEntidad implements Serializable {

    @Transient
    private String idManual;
    private String numeroChasis;
    private String numeroPlaca;
    private String modelo;
    private String estadoUnidadInial;
    private String estadoUnidadActual;
    private Boolean mantenimientoProgramado;
    private String lapsoMantenimiento;
    private String estadoActual;
    @ManyToOne
    private TipoMaquinaria tipoMaquinaria;

    public String getIdManual() {
        return idManual;
    }

    public void setIdManual(String idManual) {
        this.idManual = idManual;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstadoUnidadInial() {
        return estadoUnidadInial;
    }

    public void setEstadoUnidadInial(String estadoUnidadInial) {
        this.estadoUnidadInial = estadoUnidadInial;
    }

    public String getEstadoUnidadActual() {
        return estadoUnidadActual;
    }

    public void setEstadoUnidadActual(String estadoUnidadActual) {
        this.estadoUnidadActual = estadoUnidadActual;
    }

    public Boolean getMantenimientoProgramado() {
        return mantenimientoProgramado;
    }

    public void setMantenimientoProgramado(Boolean mantenimientoProgramado) {
        this.mantenimientoProgramado = mantenimientoProgramado;
    }

    public String getLapsoMantenimiento() {
        return lapsoMantenimiento;
    }

    public void setLapsoMantenimiento(String lapsoMantenimiento) {
        this.lapsoMantenimiento = lapsoMantenimiento;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public TipoMaquinaria getTipoMaquinaria() {
        return tipoMaquinaria;
    }

    public void setTipoMaquinaria(TipoMaquinaria tipoMaquinaria) {
        this.tipoMaquinaria = tipoMaquinaria;
    }

}
