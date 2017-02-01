/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.accesoDinamico.Roll;
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
 * JSF Managed Beam encargado de la pantalla gesstionpermisos.xhtml aqui se
 * asigna un roll a un usuario que no lo posea
 *
 * @author wilme
 * @see ManagedBean
 * @see ViewScoped
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
     * Constructor de la clase
     */
    public MbPermisos() {
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

    /**
     * Metodo utilizado como accion para guardar el cambio realizado se obtiene
     * un id de usuario no null y id roll no null y se realiza la asignascion y
     * luego dicha actualizacion de la entidad.
     *
     */
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

    /**
     * Metodo utilizado para cargar al managed Beam un Objeto tipo usuario
     * presentado desde un data table en el Xhtml, recibe como parametro un row
     * de la datable que es un objeto Usuario
     *
     * @param usu
     */
    public void Cargar(Usuario usu) {
        this.idUsuario = usu.getId();
        this.idRoll = usu.getRollUsuario().getId();
        ListaUsuarioSelect.clear();
        ListaUsuarioSelect.add(new SelectItem(usu.getId(), usu.getNombreUsuario()));
    }

    /**
     * getU
     *
     * @return
     */
    public Usuario getU() {
        return u;
    }

    /**
     * setU
     *
     * @param u
     */
    public void setU(Usuario u) {
        this.u = u;
    }

    /**
     * getListaUsuarioSelect
     *
     * @return
     */
    public List<SelectItem> getListaUsuarioSelect() {
        return ListaUsuarioSelect;
    }

    /**
     * setListaUsuarioSelect
     *
     * @param ListaUsuarioSelect
     */
    public void setListaUsuarioSelect(List<SelectItem> ListaUsuarioSelect) {
        this.ListaUsuarioSelect = ListaUsuarioSelect;
    }

    /**
     * getIdRoll
     *
     * @return
     */
    public Long getIdRoll() {
        return idRoll;
    }

    /**
     * setIdRoll
     *
     * @param idRoll
     */
    public void setIdRoll(Long idRoll) {
        this.idRoll = idRoll;
    }

    /**
     * getIdUsuario
     *
     * @return
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * setIdUsuario
     *
     * @param idUsuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * getListaRollSelect
     *
     * @return
     */
    public List<SelectItem> getListaRollSelect() {
        return ListaRollSelect;
    }

    /**
     * setListaRollSelect
     *
     * @param ListaRollSelect
     */
    public void setListaRollSelect(List<SelectItem> ListaRollSelect) {
        this.ListaRollSelect = ListaRollSelect;
    }

    /**
     * getUsuarioXRoll
     *
     * @return
     */
    public List<Usuario> getUsuarioXRoll() {
        return usuarioXRoll;
    }

    /**
     * setUsuarioXRoll
     *
     * @param usuarioXRoll
     */
    public void setUsuarioXRoll(List<Usuario> usuarioXRoll) {
        this.usuarioXRoll = usuarioXRoll;
    }

}
