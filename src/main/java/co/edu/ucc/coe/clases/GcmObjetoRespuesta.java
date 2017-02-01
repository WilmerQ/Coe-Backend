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
public class GcmObjetoRespuesta implements Serializable {

    private String NombreUsuarioResponde;
    private String NombreGrupoTrabajo;
    private double latitud;
    private double Longitud;
    private Long IdPeticion;

    public String getNombreUsuarioResponde() {
        return NombreUsuarioResponde;
    }

    public void setNombreUsuarioResponde(String NombreUsuarioResponde) {
        this.NombreUsuarioResponde = NombreUsuarioResponde;
    }

    public String getNombreGrupoTrabajo() {
        return NombreGrupoTrabajo;
    }

    public void setNombreGrupoTrabajo(String NombreGrupoTrabajo) {
        this.NombreGrupoTrabajo = NombreGrupoTrabajo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double Longitud) {
        this.Longitud = Longitud;
    }

    public Long getIdPeticion() {
        return IdPeticion;
    }

    public void setIdPeticion(Long IdPeticion) {
        this.IdPeticion = IdPeticion;
    }

}
