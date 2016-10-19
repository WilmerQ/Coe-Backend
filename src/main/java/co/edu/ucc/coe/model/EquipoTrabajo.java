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
public class EquipoTrabajo extends CamposComunesdeEntidad implements Serializable {

    private String nombreEquipo;
    @ManyToOne
    private Usuario jefeEquipo;

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public Usuario getJefeEquipo() {
        return jefeEquipo;
    }

    public void setJefeEquipo(Usuario jefeEquipo) {
        this.jefeEquipo = jefeEquipo;
    }

}
