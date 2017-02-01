/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.clases;

import co.edu.ucc.coe.model.Peticion;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author wilme
 */
public class GcmObjeto implements Serializable {

    String tipo;
    Peticion peticion;
    GcmObjetoRespuesta respuesta;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Peticion getPeticion() {
        return peticion;
    }

    public void setPeticion(Peticion peticion) {
        this.peticion = peticion;
    }

    public GcmObjetoRespuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(GcmObjetoRespuesta respuesta) {
        this.respuesta = respuesta;
    }

    
}
