/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.clases;

import java.io.Serializable;

/**
 * Clase Utilizada para el manejo de objeto Vista Activa para cada usuario
 *
 * @author wilme
 */
public class VistaActiva implements Serializable {

    /**
     * Variable Boolean utilizada para definir si la vista esta activa para
     * dicha operacion
     */
    private Boolean activa;
    /**
     * Variables String encargada de almacenar el nombre de la vista.
     */
    private String nombre;

    /**
     * getActiva
     * @return
     */
    public Boolean getActiva() {
        return activa;
    }

    /**
     * setActiva
     * @param activa
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    /**
     * getNombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
