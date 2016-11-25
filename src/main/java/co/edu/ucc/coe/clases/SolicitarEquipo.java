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
public class SolicitarEquipo implements Serializable {
    
    private EquipoTrabajo equipoTrabajo;
    private Usuario usuarioSolicita;

    public EquipoTrabajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public Usuario getUsuarioSolicita() {
        return usuarioSolicita;
    }

    public void setUsuarioSolicita(Usuario usuarioSolicita) {
        this.usuarioSolicita = usuarioSolicita;
    }
}
