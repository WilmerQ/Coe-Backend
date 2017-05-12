/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.EquipoTrabajo;

import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaEquipoTrabajo;
import co.edu.ucc.coe.service.LogicaUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbEquipoTrabajo")
public class MbEquipoTrabajo implements Serializable {

    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaEquipoTrabajo let;
    @EJB
    private LogicaUsuarios lu;
    private EquipoTrabajo equipoTrabajo;
    private Usuario u;
    private List<EquipoTrabajo> equipoTrabajos;
    private List<EquipoTrabajo> equipoTrabajosXUsuario;
    private List<SelectItem> ListaUsuarioSelect;
    private List<SelectItem> ListaEquiposSelect;
    private Long idEquipo;
    private Long idUsuario;
    private List<Usuario> usuarioXEquipo;

    /**
     * Creates a new instance of MbEquipoTrabajo
     */
    public MbEquipoTrabajo() {
    }

    @PostConstruct
    public void init() {
        u = new Usuario();
        u = (Usuario) SessionOperations.getSessionValue("USUARIO");
        usuarioXEquipo = new ArrayList<>();
        usuarioXEquipo = lu.getUsuariosConEquipoTrabajo(u.getNombreUsuario());
        equipoTrabajo = new EquipoTrabajo();

        equipoTrabajos = new ArrayList<>();
        equipoTrabajosXUsuario = new ArrayList<>();
        equipoTrabajos = cb.getAll(EquipoTrabajo.class);
        equipoTrabajosXUsuario = let.equiposxUsuario(u.getNombreUsuario());

        //----------------------------------------------
        ListaUsuarioSelect = new LinkedList<>();
        for (Usuario usu : (List<Usuario>) lu.getUsuariosSinEquipoTrabajo()) {
            if (!(u.getNombreUsuario().equals(usu.getNombreUsuario()))) {
                ListaUsuarioSelect.add(new SelectItem(usu.getId(), usu.getNombreUsuario()));
            }
        }
        ListaEquiposSelect = new LinkedList<>();
        for (EquipoTrabajo e : (List<EquipoTrabajo>) cb.getAll(EquipoTrabajo.class)) {
            if (e.getJefeEquipo().getNombreUsuario().equals(u.getNombreUsuario())) {
                ListaEquiposSelect.add(new SelectItem(e.getId(), e.getNombreEquipo()));
            }
        }

    }

    public void accionGuarda() {
        equipoTrabajo.setJefeEquipo(u);
        if (cb.guardar(equipoTrabajo)) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha Guardado");
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "Ha fallado al Guardar");
        }
    }

    public void accionGuardaUsualxEquipo() {
        if ((idUsuario != null) && (idEquipo != null)) {
            u = (Usuario) cb.getById(Usuario.class, idUsuario);
            u.setEquipoTrabajo((EquipoTrabajo) cb.getById(EquipoTrabajo.class, idEquipo));
            cb.guardar(u);
            idEquipo = null;
            idUsuario = null;
            init();
        }
    }

    public void Cargar(Usuario usu) {
        this.idUsuario = usu.getId();
        this.idEquipo = usu.getEquipoTrabajo().getId();
        ListaUsuarioSelect.clear();
        ListaUsuarioSelect.add(new SelectItem(usu.getId(), usu.getNombreUsuario()));
    }

    public void cargarEquipo(EquipoTrabajo row) {
        this.equipoTrabajo = row;
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    public EquipoTrabajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public List<EquipoTrabajo> getEquipoTrabajos() {
        return equipoTrabajos;
    }

    public void setEquipoTrabajos(List<EquipoTrabajo> equipoTrabajos) {
        this.equipoTrabajos = equipoTrabajos;
    }

    public List<EquipoTrabajo> getEquipoTrabajosXUsuario() {
        return equipoTrabajosXUsuario;
    }

    public void setEquipoTrabajosXUsuario(List<EquipoTrabajo> equipoTrabajosXUsuario) {
        this.equipoTrabajosXUsuario = equipoTrabajosXUsuario;
    }

    public List<SelectItem> getListaUsuarioSelect() {
        return ListaUsuarioSelect;
    }

    public void setListaUsuarioSelect(List<SelectItem> ListaUsuarioSelect) {
        this.ListaUsuarioSelect = ListaUsuarioSelect;
    }

    public List<SelectItem> getListaEquiposSelect() {
        return ListaEquiposSelect;
    }

    public void setListaEquiposSelect(List<SelectItem> ListaEquiposSelect) {
        this.ListaEquiposSelect = ListaEquiposSelect;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Usuario> getUsuarioXEquipo() {
        return usuarioXEquipo;
    }

    public void setUsuarioXEquipo(List<Usuario> usuarioXEquipo) {
        this.usuarioXEquipo = usuarioXEquipo;
    }

}
