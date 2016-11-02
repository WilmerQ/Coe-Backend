/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.Roll;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaVista;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wilmer
 */
@ViewScoped
@ManagedBean(name = "MbRoll")
public class MbRoll implements Serializable {

    Roll roll;
    private List<Roll> roles;

    @EJB
    private CommonsBean cb;
    
    @EJB
    private LogicaVista lv;

    /**
     * Creates a new instance of MbRoll
     */
    public MbRoll() {
    }

    @PostConstruct
    public void init() {
        roll = new Roll();
        roles = new ArrayList<>();
        roles = cb.getAll(Roll.class);
        lv.ingresarVistas();
    }

    public void accionGuarda() {
        if (cb.guardar(roll)) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha Guardado el Roll");
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "Ha fallado al Guardar el Roll");
        }
    }

    public void cargaRoll(Roll row) {
        this.roll = row;
    }

    public Roll getRoll() {
        return roll;
    }

    public void setRoll(Roll roll) {
        this.roll = roll;
    }

    public List<Roll> getRoles() {
        return roles;
    }

    public void setRoles(List<Roll> roles) {
        this.roles = roles;
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

}
