/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.Md5;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaLoguin;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wilme
 */
@Named(value = "mbUsuario")
@SessionScoped
public class MbUsuario implements Serializable {

    @EJB
    private CommonsBean commonsBean;

    private Usuario usuario;
    private String contrasena1;
    private String contrasena2;

    public MbUsuario() {
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        contrasena1 = "";
        contrasena2 = "";
    }

    public Boolean verificarFormulario() throws Exception {
        Boolean resultado = Boolean.TRUE;

        if (usuario.getNombreUsuario().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue nombre de usuario");
        } else {
            Usuario u = (Usuario) commonsBean.getByOneFieldWithOneResult(Usuario.class, "nombreUsuario", usuario.getNombreUsuario());
            if (u != null) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Nombre de usuario ya existe");
            }
        }

        if (contrasena1.trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue clave");
        } else {
            String[] campos = contrasena1.split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo clave  no permite espacio");
            }
        }

        if (contrasena2.trim().length() == 1) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue clave de confirmacion");
        } else {
            String[] campos = contrasena2.split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo clave de confirmacion no permite espacio");
            }
        }

        if (!contrasena1.equals(contrasena2)) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "claves no coinciden");
        }

        if (usuario.getEmail().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Email");
        } else {
            String[] campos = usuario.getEmail().split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo email no permite espacio");
            }
        }

        if (usuario.getTelefono().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Telefono");
        } else {
            String[] campos = usuario.getTelefono().split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo telefono no permite espacio");
            }
        }
        return resultado;
    }

    public void accionGuarda() throws Exception {
        if (verificarFormulario()) {
            usuario.setContrasena(Md5.getEncoddedString(contrasena1));
            if (commonsBean.guardar(usuario)) {
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha guardado");
                init();
                String contextPath = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
                redirect(contextPath + "/index.xhtml");
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "No se ha guardado");
            }
        }
    }

    private void redirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MbUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContrasena1() {
        return contrasena1;
    }

    public void setContrasena1(String contrasena1) {
        this.contrasena1 = contrasena1;
    }

    public String getContrasena2() {
        return contrasena2;
    }

    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
    }

}
