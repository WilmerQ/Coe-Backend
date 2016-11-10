/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * entidad encargada de realizar la realizar la relacion entre el Usuario intigrante del equipo
 * y un equipo de trabajo
 * @author wilme
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class IntegranteXEquipoTrabajo extends CamposComunesdeEntidad implements Serializable {

    /**
     * Objeto usuario utilizado para agregar un integrante
     */
    @ManyToOne
    private Usuario integrante;
    /**
     * Objeto Equipo de trabajo el cual es utilizado para asignar el equipo de trabajo.
     */
    @ManyToOne
    private EquipoTrabajo equipoTrabajo;

    /**
     * getIntegrante
     * @return
     */
    public Usuario getIntegrante() {
        return integrante;
    }

    /**
     * getIntegrante
     * @param integrante
     */
    public void getIntegrante(Usuario integrante) {
        this.integrante = integrante;
    }

    /**
     * getEquipoTrabajo
     * @return
     */
    public EquipoTrabajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    /**
     * setEquipoTrabajo
     * @param equipoTrabajo
     */
    public void setEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

}
