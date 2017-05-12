/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.alerta.UsuarioNotificar;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbNotificaciones")
public class MbNotificaciones implements Serializable {

    @EJB
    private CommonsBean cb;
    private UsuarioNotificar usuarioNotificar;
    private List<UsuarioNotificar> usuarioNotificars;

    public MbNotificaciones() {
    }

    @PostConstruct
    public void init() {
        usuarioNotificar = new UsuarioNotificar();
        usuarioNotificars = new ArrayList<>();
        usuarioNotificars = cb.getAll(UsuarioNotificar.class);
    }

    public void accionGuarda() throws Exception {
        if (verificarFormulario()) {
            if (cb.guardar(usuarioNotificar)) {
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha Guardado");
                init();
            } else {
                mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "Ha fallado al Guardar");
            }
        }
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    public Boolean verificarFormulario() throws Exception {
        Boolean resultado = Boolean.TRUE;

        if (usuarioNotificar.getNombres().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue nombres");
        } else {
            UsuarioNotificar u = (UsuarioNotificar) cb.getByOneFieldWithOneResult(UsuarioNotificar.class, "nombres", usuarioNotificar.getNombres());
            if (u != null) {
                if (!usuarioNotificar.getId().equals(u.getId())) {
                    resultado = Boolean.FALSE;
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Nombre de usuario ya existe");
                }
            }
        }

        if (usuarioNotificar.getEntidad().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Entidad");
        }

        if (usuarioNotificar.getCargo().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Cargo");
        }

        if (usuarioNotificar.getEmail().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Correo Electronico");
        } else if (!(validateEmail(usuarioNotificar.getEmail()))) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Formato de Correo Electronico no admitido");
        }

        /*if (usuario.getEmail().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Email");
        } else {
            String[] campos = usuario.getEmail().split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo email no permite espacio");
            }
        }*/
        if (usuarioNotificar.getTelefono().trim().length() == 0) {
            resultado = Boolean.FALSE;
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Agregue Telefono");
        } else {
            String[] campos = usuarioNotificar.getTelefono().split(" ");
            if (campos.length > 1) {
                resultado = Boolean.FALSE;
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "El campo telefono no permite espacio");
            }
        }
        return resultado;
    }

    public void cargarUsuarioNotificar(UsuarioNotificar row) {
        this.usuarioNotificar = row;
    }

    public boolean validateEmail(String email) {
        String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public CommonsBean getCb() {
        return cb;
    }

    public void setCb(CommonsBean cb) {
        this.cb = cb;
    }

    public UsuarioNotificar getUsuarioNotificar() {
        return usuarioNotificar;
    }

    public void setUsuarioNotificar(UsuarioNotificar usuarioNotificar) {
        this.usuarioNotificar = usuarioNotificar;
    }

    public List<UsuarioNotificar> getUsuarioNotificars() {
        return usuarioNotificars;
    }

    public void setUsuarioNotificars(List<UsuarioNotificar> usuarioNotificars) {
        this.usuarioNotificars = usuarioNotificars;
    }

}
