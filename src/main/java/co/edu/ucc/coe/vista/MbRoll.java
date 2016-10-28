/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.Roll;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
    }

    public void accionGuarda() {
        cb.guardar(roll);
        init();
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

}
