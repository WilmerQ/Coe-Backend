/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;



/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbVerAlertas")
public class MbVerAlertas {

    private List<SelectItem> alertasExistentes;
    private String alerta;
    /**
     * Creates a new instance of MbVerAlertas
     */
    public MbVerAlertas() {
    }
    
    @PostConstruct
    public void init(){
        alertasExistentes = new LinkedList<>();
        alertasExistentes.add(new SelectItem("ultima hora"));
        alertasExistentes.add(new SelectItem("ultima 6 horas"));
        alertasExistentes.add(new SelectItem("ultimas 24 hora"));
        alertasExistentes.add(new SelectItem("ultima semana"));
    }

    public List<SelectItem> getAlertasExistentes() {
        return alertasExistentes;
    }

    public void setAlertasExistentes(List<SelectItem> alertasExistentes) {
        this.alertasExistentes = alertasExistentes;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }
    
    
}
