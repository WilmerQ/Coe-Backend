/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.DatosBasicos;
import co.edu.ucc.coe.base.Md5;
import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.LogicaLoguin;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;

/**
 * JSF Managed Beam encargado de la pantalla de Loguin que se encuentra en el
 * index.xhtml 
 *
 * @author wilme
 * @see SessionScoped
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
    /**
     * Constructor de la clase
     */
    public MbLoguin() {
    }

    /**
     * Metodo Init el cual se ejecuta inmediatamente despues de la creacion del
     * Managed Beam en este se inicializa los objetos y variables para el
     * Managed Beam
     *
     * @see PostConstruct
     */
    @PostConstruct
    public void init() {
        usuario = (Usuario) SessionOperations.getSessionValue("USUARIO");
        if (usuario == null) {
            usuario = new Usuario();
            autenticado = Boolean.FALSE;
            isusuario = Boolean.FALSE;
            isadmin = Boolean.FALSE;
            SessionOperations.setSessionValue("USER", Boolean.FALSE);
        } else {
            autenticado = Boolean.TRUE;
            isusuario = Boolean.TRUE;
        }
    }

    /**
     * Funcion tipo accion las cual es llamada desde un event del un html
     * obtiene los datos de nombre y contraseña y ejecuta la operacion del ejb
     * login y retorna el resultado.
     *
     * @return
     */
    public String accionLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        autenticado = false;
        isusuario = false;
        isadmin = false;
        Usuario u = logicaLoguin.login(nombreDeUsuaio, Md5.getEncoddedString(password));
        SessionOperations.setSessionValue("USER", Boolean.FALSE);
        if (u != null) {
            String url;
            usuario = u;
            autenticado = true;
            if (u.getNombreUsuario().equals("Administrador")) {
                isadmin = true;
                SessionOperations.setSessionValue("ADMIN", Boolean.TRUE);
                SessionOperations.setSessionValue("USER", Boolean.FALSE);
                System.out.println("logueo admin");
                url = "admin/index.xhtml";
            } else {
                isusuario = true;
                SessionOperations.setSessionValue("USER", Boolean.TRUE);
                SessionOperations.setSessionValue("ADMIN", Boolean.FALSE);
                System.out.println("logueo user");
                url = "usuario/index.xhtml";
            }
            SessionOperations.setSessionValue("USUARIO", usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, u.getNombreUsuario(), "Bienvenido"));
            redirect(url);
        } else {

        }
        return null;
    }

    /**
     * Funcion tipo accion las cual es llamada desde un event del un html en
     * esta acion se realiza el proceso de cierre se sesion lo que se realiza es
     * cambiar el estado de las variable de sesion que almacena el usuario
     * actual.
     *
     * @return
     */
    public String accionLogout() {
        init();
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        try {
            SessionOperations.setSessionValue("USER", Boolean.FALSE);
            SessionOperations.setSessionValue("ADMIN", Boolean.FALSE);
            context.getExternalContext().invalidateSession();
        } catch (Exception e) {

        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salida", "Se ha cerrado la sesion correctamente"));
        redirect(DatosBasicos.path);
        return null;
    }

    /**
     * Metodo encargado de rediccionar como parametro recibe un String que
     * contiene el link ejemplo /usuario/index.xhtml
     *
     * @param url
     */
    private void redirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MbLoguin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo utilizado para ejecutar la presentacion de un mensaje emergente
     * tipo Growl en pantall
     *
     * @param icono
     * @param titulo
     * @param mensaje
     */
    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    /**
     * getNombreDeUsuaio
     *
     * @return
     */
    public String getNombreDeUsuaio() {
        return nombreDeUsuaio;
    }

    /**
     * setNombreDeUsuaio
     *
     * @param nombreDeUsuaio
     */
    public void setNombreDeUsuaio(String nombreDeUsuaio) {
        this.nombreDeUsuaio = nombreDeUsuaio;
    }

    /**
     * getPassword
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * getUsuario
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
