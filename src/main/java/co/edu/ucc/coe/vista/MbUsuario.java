/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.Md5;
import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;

import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 * JSF Managed Beam encargado de la pantalla de index.xhtml en la seccion de
 * resgistro de usuario donde se validara los campos ingresador y se procedera a
 * realizar el guardado del usuario
 *
 * @author wilme
 * @see Named
 * @see SessionScoped
 */
@SessionScoped
@ManagedBean(name = "MbUsuario")
public class MbUsuario implements Serializable {

    @EJB
    private CommonsBean commonsBean;

    private Usuario usuario;
    private String contrasena1;
    private String contrasena2;

    /**
     * Constructor de la clase
     */
    public MbUsuario() {
    }

    /**
     * Metodo Init el cual se ejecuta inmediatamente despues de la creacion del
     * Managed Beam en este se inicializa los objetos y variables para este Mb
     *
     * @see PostConstruct
     */
    @PostConstruct
    public void init() {
        usuario = new Usuario();
        contrasena1 = "";
        contrasena2 = "";
    }

    /**
     * Funcion utilizada para verificar campo por campo a diligenciar por el
     * usaurio al momento de restigrase en caso de que el campo se encuentre con
     * informacion invalidad se le mostrara al usuario un mensaje
     *
     * @return @throws Exception
     */
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

    /**
     * Metodo utilizado como accion encargado de recoger los datos ingresados al
     * formulario y ejecutar la accion de guardar del ejb commonsBean como
     * resultado mostrara un mensaje tipo Growl mostrando el resultado
     *
     * @throws Exception
     */
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

    public void handleFileUpload(FileUploadEvent event) {
        UUID id = UUID.randomUUID();
        try {
            System.out.println("aqui estoy");
            InputStream fi;
            fi = event.getFile().getInputstream();
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            fi.read(buffer);
            usuario.setImagenperfil(buffer);
            SessionOperations.setSessionValue(id.toString().toUpperCase(), buffer);
        } catch (IOException ex) {
            Logger.getLogger(MbUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo encargado de rediccionar como parametro recibe un String que
     * contiene el link ejemplo /usuario/index.xht
     *
     * @param url
     */
    private void redirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MbUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo utilizado para ejecutar la presentacion de un mensaje emergente
     * tipo Growl en pantalla
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
     * getUsuario
     *
     * @return
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * setUsuario
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * getContrasena1
     *
     * @return
     */
    public String getContrasena1() {
        return contrasena1;
    }

    /**
     * setContrasena1
     *
     * @param contrasena1
     */
    public void setContrasena1(String contrasena1) {
        this.contrasena1 = contrasena1;
    }

    /**
     * getContrasena2
     *
     * @return
     */
    public String getContrasena2() {
        return contrasena2;
    }

    /**
     * setContrasena2
     *
     * @param contrasena2
     */
    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
    }

}
