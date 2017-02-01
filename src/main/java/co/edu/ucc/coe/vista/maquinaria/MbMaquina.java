/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista.maquinaria;

import co.edu.ucc.coe.model.maquinaria.Maquina;
import co.edu.ucc.coe.model.maquinaria.TipoMaquinaria;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbMaquina")
public class MbMaquina implements Serializable {

    @EJB
    private CommonsBean cb;
    private Maquina maquina;
    private List<SelectItem> listTipoVehiculos;
    private List<SelectItem> listModelos;
    private List<SelectItem> listEstados;
    private Long idMaquina;
    private int slider;
//------------------------------------------------------
    private List<Maquina> maquinas;

    /**
     * Creates a new instance of MbMaquina
     */
    public MbMaquina() {
    }

    @PostConstruct
    public void init() {
        maquina = new Maquina();
        listTipoVehiculos = new LinkedList<>();
        for (TipoMaquinaria tm : (List<TipoMaquinaria>) cb.getAll(TipoMaquinaria.class)) {
            listTipoVehiculos.add(new SelectItem(tm.getId(), tm.getNombremaquina()));
        }
        listModelos = new LinkedList<>();
        for (int i = 1950; i <= 2017; i++) {
            listModelos.add(new SelectItem("" + i));
        }
        listEstados = new LinkedList<>();
        listEstados.add(new SelectItem("Activa"));
        listEstados.add(new SelectItem("Reparacion"));
        listEstados.add(new SelectItem("Fuera de servicio"));
        slider = 0;
        idMaquina = null;
        maquinas = new ArrayList<>();
        maquinas = cb.getAll(Maquina.class);
    }

    public String accionGuardar() {
        System.out.println("id maquina " + slider);
        if (slider > 0) {
            maquina.setMantenimientoProgramado(Boolean.TRUE);
        } else {
            maquina.setMantenimientoProgramado(Boolean.FALSE);
        }
        maquina.setLapsoMantenimiento("" + slider);
        try {
            TipoMaquinaria tm = (TipoMaquinaria) cb.getById(TipoMaquinaria.class, idMaquina);
            maquina.setTipoMaquinaria(tm);
            if (cb.guardar(maquina)) {
                System.out.println("accion Guardar");
                init();
            }
            return null;
        } catch (Exception e) {

        }
        return null;
    }

    public void Cargar(Maquina m) {
        this.maquina = m;
        this.slider = Integer.parseInt(m.getLapsoMantenimiento());
        this.idMaquina = m.getTipoMaquinaria().getId();
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public List<SelectItem> getListTipoVehiculos() {
        return listTipoVehiculos;
    }

    public void setListTipoVehiculos(List<SelectItem> listTipoVehiculos) {
        this.listTipoVehiculos = listTipoVehiculos;
    }

    public Long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public List<SelectItem> getListModelos() {
        return listModelos;
    }

    public void setListModelos(List<SelectItem> listModelos) {
        this.listModelos = listModelos;
    }

    public List<SelectItem> getListEstados() {
        return listEstados;
    }

    public void setListEstados(List<SelectItem> listEstados) {
        this.listEstados = listEstados;
    }

    public int getSlider() {
        return slider;
    }

    public void setSlider(int slider) {
        this.slider = slider;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }
}
