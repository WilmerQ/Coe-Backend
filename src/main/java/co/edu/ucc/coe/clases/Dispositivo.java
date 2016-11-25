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
public class Dispositivo implements Serializable {

    private String tokenGoogle;
    private String androidID;
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
