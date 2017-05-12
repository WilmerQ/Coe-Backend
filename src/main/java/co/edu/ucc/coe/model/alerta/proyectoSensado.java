/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.alerta;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author wilme
 */
@Entity
public class proyectoSensado extends CamposComunesdeEntidad implements Serializable {

    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
