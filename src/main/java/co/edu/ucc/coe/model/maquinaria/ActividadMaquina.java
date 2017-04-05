/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author wilme
 */
@Entity
public class ActividadMaquina extends CamposComunesdeEntidad implements Serializable {

    private String idmanual;
    private String AccionxRealizar;
    private String tiempoDedicado;
    @ManyToOne
    private Maquina maquina;
    private String proyectoAsociado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDateFinal;

    public String getAccionxRealizar() {
        return AccionxRealizar;
    }

    public void setAccionxRealizar(String AccionxRealizar) {
        this.AccionxRealizar = AccionxRealizar;
    }

    public String getTiempoDedicado() {
        return tiempoDedicado;
    }

    public void setTiempoDedicado(String tiempoDedicado) {
        this.tiempoDedicado = tiempoDedicado;
    }

    public String getProyectoAsociado() {
        return proyectoAsociado;
    }

    public void setProyectoAsociado(String proyectoAsociado) {
        this.proyectoAsociado = proyectoAsociado;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaDateFinal() {
        return fechaDateFinal;
    }

    public void setFechaDateFinal(Date fechaDateFinal) {
        this.fechaDateFinal = fechaDateFinal;
    }

    public String getIdmanual() {
        return idmanual;
    }

    public void setIdmanual(String idmanual) {
        this.idmanual = idmanual;
    }

}
