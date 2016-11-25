/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author wilme
 */
@Entity
public class Dispositivo extends CamposComunesdeEntidad implements Serializable {

    @Column(columnDefinition = "TEXT")
    private String tokenGoogle;
    @Column(columnDefinition = "TEXT")
    private String androidID;
    @ManyToOne
    private Usuario usuario;

    public String getTokenGoogle() {
        return tokenGoogle;
    }

    public void setTokenGoogle(String tokenGoogle) {
        this.tokenGoogle = tokenGoogle;
    }

    public String getAndroidID() {
        return androidID;
    }

    public void setAndroidID(String androidID) {
        this.androidID = androidID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
