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
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaLoguin;
import co.edu.ucc.coe.service.LogicaVista;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;

/**
 * JSF Managed Beam encargado de la pantalla de Loguin que se encuentra en el
 * index.xhtml
 *
 * @author wilme
 * @see SessionScoped
 */
@SessionScoped
@ManagedBean(name = "mbLoguin")
public class MbLoguin implements Serializable {

    private Usuario usuario;
    private Boolean autenticado;
    private Boolean isusuario;
    private Boolean isadmin;

    //Loguin
    private String nombreDeUsuaio;
    private String password;

    //actualizar datos
    private Boolean actualizarImagenPerfil;
    private Boolean actualzarPass;
    private String passActual;
    private String passNueva;
    private String passConfirmar;

    @EJB
    private LogicaLoguin logicaLoguin;
    @EJB
    private LogicaVista lv;
    @EJB
    private CommonsBean cb;

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
        actualizarImagenPerfil = Boolean.FALSE;
        actualzarPass = Boolean.FALSE;
        lv.ingresarVistas();
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

    public String accionActualizar() throws Exception {
        if (verificarFormulario()) {
            Boolean actualizar = Boolean.FALSE;
            Usuario usuario1 = (Usuario) cb.getById(Usuario.class, usuario.getId());
            if (usuario1 != null) {
                System.out.println("email usuario1 " + usuario1.getEmail());
                System.out.println("email usuario " + usuario.getEmail());
                System.out.println("telefono usuario1 " + usuario1.getTelefono());
                System.out.println("telefono usuario " + usuario.getTelefono());
                if ((!(usuario1.getEmail().equals(usuario.getEmail())))
                        || (!(usuario1.getTelefono().equals(usuario.getTelefono())))) {
                    actualizar = Boolean.TRUE;
                }
                try {
                    if (usuario1.getImagenperfil().length != usuario.getImagenperfil().length) {
                        actualizar = Boolean.TRUE;
                    }
                } catch (Exception e) {
                    actualizar = Boolean.TRUE;
                }

                System.out.println("actualizar " + actualizar);
                if (actualzarPass) {
                    if (usuario1.getContrasena().equals(Md5.getEncoddedString(passActual))) {
                        if (passNueva.equals(passConfirmar)) {
                            usuario.setContrasena(Md5.getEncoddedString(passConfirmar));
                            actualizar = Boolean.TRUE;
                        } else {
                            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Revisar contraseñas nuevas no coinciden");
                        }
                    } else {
                        mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Contraseña actual no coincide con su contraseña");
                    }
                }
            }
            if (actualizar) {
                if (cb.guardar(usuario)) {
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha Actualizado");
                    accionLogout();
                    //SessionOperations.setSessionValue("USUARIO", usuario);
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "Ha fallado al Guardar");
                }
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "No se detectaron cambios");
            }
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

    public Boolean verificarFormulario() throws Exception {
        Boolean resultado = Boolean.TRUE;
        if (usuario.getEmail().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Correo Electronico");
        } else if (!(validateEmail(usuario.getEmail()))) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Formato de Correo Electronico no admitido");
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

        if (actualzarPass) {
            if (passActual.trim().length() == 0) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Contraseña actual");
            } else {
                String[] campos = passActual.split(" ");
                if (campos.length > 1) {
                    resultado = Boolean.FALSE;
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo Contraseña actual no permite espacio");
                }
            }
            if (passNueva.trim().length() == 0) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue la nueva Contraseña");
            } else {
                String[] campos = passNueva.split(" ");
                if (campos.length > 1) {
                    resultado = Boolean.FALSE;
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo nueva Contraseña no permite espacio");
                }
            }

            if (passConfirmar.trim().length() == 0) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Confirmar Contraseña");
            } else {
                String[] campos = passConfirmar.split(" ");
                if (campos.length > 1) {
                    resultado = Boolean.FALSE;
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo Confirmar Contraseña no permite espacio");
                }
            }
        }
        return resultado;
    }

    public boolean validateEmail(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
            actualizarImagenPerfil = Boolean.FALSE;
        } catch (IOException ex) {
            Logger.getLogger(MbUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public Boolean getActualzarPass() {
        return actualzarPass;
    }

    public void setActualzarPass(Boolean actualzarPass) {
        if (actualzarPass == Boolean.FALSE) {
            passActual = "";
            passNueva = "";
            passConfirmar = "";
        }
        this.actualzarPass = actualzarPass;
    }

    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public String getPassNueva() {
        return passNueva;
    }

    public void setPassNueva(String passNueva) {
        this.passNueva = passNueva;
    }

    public String getPassConfirmar() {
        return passConfirmar;
    }

    public void setPassConfirmar(String passConfirmar) {
        this.passConfirmar = passConfirmar;
    }

    public Boolean getActualizarImagenPerfil() {
        return actualizarImagenPerfil;
    }

    public void setActualizarImagenPerfil(Boolean actualizarImagenPerfil) {
        this.actualizarImagenPerfil = actualizarImagenPerfil;
    }

}
