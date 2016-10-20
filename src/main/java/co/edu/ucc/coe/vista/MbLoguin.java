/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.Md5;
import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.LogicaLoguin;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilme
 */
@Named(value = "mbLoguin")
@SessionScoped
public class MbLoguin implements Serializable {

    private Usuario usuario;
    private Boolean autenticado;
    private Boolean isusuario;
    private Boolean isadmin;

    //Loguin
    private String nombreDeUsuaio;
    private String password;

    @EJB
    private LogicaLoguin logicaLoguin;

    //Registro
    public MbLoguin() {
    }

    @PostConstruct
    public void init() {
        usuario = (Usuario) SessionOperations.getSessionValue("USUARIO");
        if (usuario == null) {
            usuario = new Usuario();
            autenticado = Boolean.FALSE;
            isusuario = Boolean.FALSE;
            SessionOperations.setSessionValue("USER", Boolean.FALSE);
        } else {
            autenticado = Boolean.TRUE;
            isusuario = Boolean.TRUE;
        }
    }

    public String accionLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        autenticado = false;
        isusuario = false;
        isadmin = false;
        Usuario u = logicaLoguin.login(nombreDeUsuaio, Md5.getEncoddedString(password));
        SessionOperations.setSessionValue("USER", Boolean.FALSE);
        if (u != null) {
            usuario = u;
            autenticado = true;
            isusuario = true;
            SessionOperations.setSessionValue("USER", Boolean.TRUE);
            SessionOperations.setSessionValue("USUARIO", usuario);
            //mostrarMensaje(FacesMessage.SEVERITY_INFO, u.getNombreUsuario(), "Bienvenido");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, u.getNombreUsuario(), "Bienvenido"));
            redirect("usuario/index.xhtml");
        } else {
            
        }
        return null;
    }

    private void redirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MbLoguin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    public String getNombreDeUsuaio() {
        return nombreDeUsuaio;
    }

    public void setNombreDeUsuaio(String nombreDeUsuaio) {
        this.nombreDeUsuaio = nombreDeUsuaio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
