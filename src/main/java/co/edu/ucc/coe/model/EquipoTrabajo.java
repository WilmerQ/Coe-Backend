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
 * Entidad utilizada para almacenar los grupos de trabajo
 * Grupo Trabajo: es la union de una cantidad de usuarios los cuales se encuentran dirigidos por una persona
 * la cual puede conocer la ubicacion de su personal a cargo a traves de la aplicacion movil.
 * @author wilme
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class EquipoTrabajo extends CamposComunesdeEntidad implements Serializable {

    /**
     * Variables String que almacena el nombre del equipo.
     */
    private String nombreEquipo;
    /**
     * Objeto Usuario el cual sera designado como jefe de equipo
     */
    @ManyToOne
    private Usuario jefeEquipo;

    /**
     * getNombreEquipo
     * @return
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * setNombreEquipo
     * @param nombreEquipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    /**
     * getJefeEquipo
     * @return
     */
    public Usuario getJefeEquipo() {
        return jefeEquipo;
    }

    /**
     * setJefeEquipo
     * @param jefeEquipo
     */
    public void setJefeEquipo(Usuario jefeEquipo) {
        this.jefeEquipo = jefeEquipo;
    }

}
