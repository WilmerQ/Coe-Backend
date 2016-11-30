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
 *
 * @author wilme
 */
@Entity
public class Peticion extends CamposComunesdeEntidad implements Serializable {

    String NombreUsuario;
    String IDdispositivoRealizador;

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getIDdispositivoRealizador() {
        return IDdispositivoRealizador;
    }

    public void setIDdispositivoRealizador(String IDdispositivoRealizador) {
        this.IDdispositivoRealizador = IDdispositivoRealizador;
    }
    
    
}
