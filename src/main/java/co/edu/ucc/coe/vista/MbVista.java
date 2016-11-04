/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.clases.VistaActiva;
import co.edu.ucc.coe.model.Roll;
import co.edu.ucc.coe.model.Vista;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaVista;
import java.io.Serializable;
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
@ViewScoped
@ManagedBean(name = "MbVista")
public class MbVista implements Serializable {

    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaVista lv;
    private Boolean prueba;
    private Long idRoll;
    private List<SelectItem> ListaRollSelect;
    private List<Vista> listaVistas;
    private List<VistaActiva> vistaActivas;

    public MbVista() {
    }

    @PostConstruct
    public void init() {
        ListaRollSelect = new LinkedList<>();
        for (Roll r : (List<Roll>) cb.getAll(Roll.class)) {
            ListaRollSelect.add(new SelectItem(r.getId(), r.getNombre()));
        }
        listaVistas = new ArrayList<>();
        listaVistas = lv.getVistas1();
        vistaActivas = new ArrayList<>();
        idRoll = null;
    }

    public void cargarvistas() {
        System.out.println("cargarvistas");
        List<VistaActiva> activasTemp = new ArrayList<>();
        for (Vista v : listaVistas) {
            VistaActiva vistaActiva = new VistaActiva();
            vistaActiva.setNombre(v.getNombre());
            activasTemp.add(vistaActiva);
        }
        List<VistaActiva> activasTemp1 = new ArrayList<>();
        for (VistaActiva va : activasTemp) {
            if (lv.getvistas(idRoll, va.getNombre())) {
                va.setActiva(Boolean.TRUE);
                activasTemp1.add(va);
            } else {
                va.setActiva(Boolean.FALSE);
                activasTemp1.add(va);
            }
        }
        this.vistaActivas = activasTemp1;
        System.out.println("tamaño de lista: " + vistaActivas.size());
        for (VistaActiva va : vistaActivas) {
            System.out.println("Describiendo la lista: " + va.getNombre());
            System.out.println("Describiendo la lista: " + va.getActiva());
        }
    }

    public void accionGuardar() {
        System.out.println("tamaño de lista AccionGuardar: " + vistaActivas.size());
        for (VistaActiva va : vistaActivas) {
            System.out.println("Describiendo la lista AccionGuardar: " + va.getNombre());
            System.out.println("Describiendo la lista AccionGuardar: " + va.getActiva());
        }
        lv.guardarVistas(vistaActivas, idRoll);
        init();
    }

    public Boolean getPrueba() {
        return prueba;
    }

    public void setPrueba(Boolean prueba) {
        this.prueba = prueba;
    }

    public Long getIdRoll() {
        return idRoll;
    }

    public void setIdRoll(Long idRoll) {
        this.idRoll = idRoll;
    }

    public List<SelectItem> getListaRollSelect() {
        return ListaRollSelect;
    }

    public void setListaRollSelect(List<SelectItem> ListaRollSelect) {
        this.ListaRollSelect = ListaRollSelect;
    }

    public List<Vista> getListaVistas() {
        return listaVistas;
    }

    public void setListaVistas(List<Vista> listaVistas) {
        this.listaVistas = listaVistas;
    }

    public List<VistaActiva> getVistaActivas() {
        return vistaActivas;
    }

    public void setVistaActivas(List<VistaActiva> vistaActivas) {
        this.vistaActivas = vistaActivas;
    }

}
