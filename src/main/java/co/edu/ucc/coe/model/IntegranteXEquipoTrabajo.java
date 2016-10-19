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
 *
 * @author wilme
 */
@Entity
public class IntegranteXEquipoTrabajo extends CamposComunesdeEntidad implements Serializable {

    @ManyToOne
    private Usuario integrante;
    @ManyToOne
    private EquipoTrabajo equipoTrabajo;

    public Usuario getIntegrante() {
        return integrante;
    }

    public void setIntegrante(Usuario integrante) {
        this.integrante = integrante;
    }

    public EquipoTrabajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

}
