/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

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
 *
 * @author wilme
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

    @PostConstruct
    public void init() {
        listaVistas = new ArrayList<>();
    }
    
    

}
