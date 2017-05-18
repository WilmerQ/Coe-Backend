/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.alerta.TipoSensor;
import co.edu.ucc.coe.model.alerta.proyectoSensado;
import co.edu.ucc.coe.service.CommonsBean;
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
@ManagedBean(name = "MbNivelesProyectoXSenor")
public class MbNivelesProyectoXSenor {

    private Boolean proyectoSelecionado;
    private Boolean TipoSensorSelecionado;
    private Boolean resetListTipoSensor;
    private List<SelectItem> ListaProyecto;
    private List<SelectItem> ListaTiposSensoresProyecto;
    private List<SelectItem> ListaSensores;
    private Long idProyecto;
    private Long idTipoSensor;
    @EJB
    private CommonsBean cb;

    public MbNivelesProyectoXSenor() {
    }

    @PostConstruct
    public void init() {
        resetListTipoSensor = Boolean.FALSE;
        proyectoSelecionado = Boolean.FALSE;
        TipoSensorSelecionado = Boolean.FALSE;
        ListaProyecto = new LinkedList<>();
        for (proyectoSensado e : (List<proyectoSensado>) cb.getAll(proyectoSensado.class)) {
            ListaProyecto.add(new SelectItem(e.getId(), e.getNombre()));
        }
        ListaTiposSensoresProyecto = new LinkedList<>();
        ListaSensores = new LinkedList<>();
    }

    public List<SelectItem> getListaProyecto() {
        return ListaProyecto;
    }

    public void setListaProyecto(List<SelectItem> ListaProyecto) {
        this.ListaProyecto = ListaProyecto;
    }

    public Long getIdProyecto() {
        System.out.println("getIdProyecto");
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        System.out.println("setIdProyecto" + idProyecto);
        if ((idProyecto != null) && (idTipoSensor != null)) {
            System.out.println("setIdProyecto entro");
            resetListTipoSensor = Boolean.TRUE;
        }
        proyectoSelecionado = Boolean.TRUE;
        ListaTiposSensoresProyecto.clear();
        this.idProyecto = idProyecto;
    }

    public Boolean getProyectoSelecionado() {
        return proyectoSelecionado;
    }

    public void setProyectoSelecionado(Boolean proyectoSelecionado) {
        this.proyectoSelecionado = proyectoSelecionado;
    }

    public Boolean getTipoSensorSelecionado() {
        return TipoSensorSelecionado;
    }

    public Long getIdTipoSensor() {
        System.out.println("----getIdTipoSensor- antes" + idTipoSensor);
        if (resetListTipoSensor) {
            System.out.println("getIdTipoSensor entro resetListTipoSensor");
            idTipoSensor = null;
            resetListTipoSensor = Boolean.FALSE;
        }
        System.out.println("----getIdTipoSensor - despues " + idTipoSensor);
        return idTipoSensor;
    }

    public void setIdTipoSensor(Long idTipoSensor) {
        System.out.println("setIdTipoSensor");
        this.idTipoSensor = idTipoSensor;
    }

    public void setTipoSensorSelecionado(Boolean TipoSensorSelecionado) {
        this.TipoSensorSelecionado = TipoSensorSelecionado;
    }

    public List<SelectItem> getListaSensores() {
        return ListaSensores;
    }

    public void setListaSensores(List<SelectItem> ListaSensores) {
        this.ListaSensores = ListaSensores;
    }

    public List<SelectItem> getListaTiposSensoresProyecto() {
        if (idProyecto != null) {
            System.out.println("getListaTiposSensoresProyecto");
            for (TipoSensor ts : (List<TipoSensor>) cb.getAll(TipoSensor.class)) {
                ListaTiposSensoresProyecto.add(new SelectItem(ts.getId(), ts.getNombre()));
            }
        }
        return ListaTiposSensoresProyecto;
    }

    public void setListaTiposSensoresProyecto(List<SelectItem> ListaTiposSensoresProyecto) {
        this.ListaTiposSensoresProyecto = ListaTiposSensoresProyecto;
    }

}
