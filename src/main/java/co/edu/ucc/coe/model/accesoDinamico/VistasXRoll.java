/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.accesoDinamico;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entidad encargada del manejo de la relacion entre vista y roll
 * @author wilme
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class VistasXRoll extends CamposComunesdeEntidad implements Serializable {

    /**
     * Obtejo tipo roll
     */
    @ManyToOne
    private Roll roll;
    /**
     * objeto tipo vista
     */
    @ManyToOne
    private Vista vista;

    /**
     * getRoll
     * @return
     */
    public Roll getRoll() {
        return roll;
    }

    /**
     * setRoll
     * @param roll
     */
    public void setRoll(Roll roll) {
        this.roll = roll;
    }

    /**
     * getVista
     * @return
     */
    public Vista getVista() {
        return vista;
    }

    /**
     * setVista
     * @param vista
     */
    public void setVista(Vista vista) {
        this.vista = vista;
    }

}
