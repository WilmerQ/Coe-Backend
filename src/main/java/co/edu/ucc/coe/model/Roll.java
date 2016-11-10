/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;


/**
 * Entidad encargada del manejo del rol para cada usuario 
 * un rol debe ser asignado a los nuevos usuarios por el administrador
 * @author Wilmer Quintero
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class Roll extends CamposComunesdeEntidad implements Serializable {

    private String nombre;

    /**
     * getNombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
