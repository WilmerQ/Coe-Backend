/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.clases;

import java.io.Serializable;

/**
 *
 * @author wilme
 */
public class EquipoTrabajo implements Serializable {

    private Long id;
    private String nombreEquipo;
    private Usuario jefeEquipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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