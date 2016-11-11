/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.model.Vista;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaMenu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * JSF Managed Beam encargado la presentacion de los elementos del menu lateral
 *
 * @author wilme
 * @see ViewScoped
 * @see ManagedBean
 */
@ViewScoped
@ManagedBean(name = "MbMenu")
public class MbMenu {

    @EJB
    private LogicaMenu lm;
    @EJB
    private CommonsBean cb;

    private List<Vista> listaVistas;

    /**
     * Creates a new instance of MbMenu
     */
    public MbMenu() {
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
        listaVistas = new ArrayList<>();
        listaVistas = lm.getVistasMenuUsuario((Usuario) SessionOperations.getSessionValue("USUARIO"));
    }

    /**
     * getListaVistas
     *
     * @return
     */
    public List<Vista> getListaVistas() {
        return listaVistas;
    }

    /**
     * setListaVistas
     *
     * @param listaVistas
     */
    public void setListaVistas(List<Vista> listaVistas) {
        this.listaVistas = listaVistas;
    }

}
