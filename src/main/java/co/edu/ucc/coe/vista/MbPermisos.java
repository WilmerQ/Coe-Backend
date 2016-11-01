/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.Roll;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaUsuarios;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author wilme
 */
@ManagedBean(name = "mbPermisos")
@ViewScoped
public class MbPermisos {

    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaUsuarios lu;

    private Usuario u;
    private List<SelectItem> ListaUsuarioSelect;
    private List<SelectItem> ListaRollSelect;
    private Long idRoll;
    private Long idUsuario;
    private List<Usuario> usuarioXRoll;

    /**
     * Creates a new instance of MbPermisos
     */
    public MbPermisos() {
    }

    @PostConstruct
    public void init() {
        usuarioXRoll = new ArrayList<>();
        usuarioXRoll = lu.getUsuariosConRoll();
        ListaUsuarioSelect = new LinkedList<>();
        for (Usuario usu : (List<Usuario>) lu.getUsuariosSinRoll()) {
            ListaUsuarioSelect.add(new SelectItem(usu.getId(), usu.getNombreUsuario()));
        }
        ListaRollSelect = new LinkedList<>();
        for (Roll r : (List<Roll>) cb.getAll(Roll.class)) {
            ListaRollSelect.add(new SelectItem(r.getId(), r.getNombre()));
        }
    }

    public void accionGuarda() {
        if ((idUsuario != null) && (idRoll != null)) {
            u = (Usuario) cb.getById(Usuario.class, idUsuario);
            u.setRollUsuario((Roll) cb.getById(Roll.class, idRoll));
            cb.guardar(u);
            idRoll = null;
            idUsuario = null;
            init();
        }
    }

    public void Cargar(Usuario usu) {
        this.idUsuario = usu.getId();
        this.idRoll = usu.getRollUsuario().getId();
        ListaUsuarioSelect.clear();
        ListaUsuarioSelect.add(new SelectItem(usu.getId(), usu.getNombreUsuario()));
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public List<SelectItem> getListaUsuarioSelect() {
        return ListaUsuarioSelect;
    }

    public void setListaUsuarioSelect(List<SelectItem> ListaUsuarioSelect) {
        this.ListaUsuarioSelect = ListaUsuarioSelect;
    }

    public Long getIdRoll() {
        return idRoll;
    }

    public void setIdRoll(Long idRoll) {
        this.idRoll = idRoll;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<SelectItem> getListaRollSelect() {
        return ListaRollSelect;
    }

    public void setListaRollSelect(List<SelectItem> ListaRollSelect) {
        this.ListaRollSelect = ListaRollSelect;
    }

    public List<Usuario> getUsuarioXRoll() {
        return usuarioXRoll;
    }

    public void setUsuarioXRoll(List<Usuario> usuarioXRoll) {
        this.usuarioXRoll = usuarioXRoll;
    }

}
