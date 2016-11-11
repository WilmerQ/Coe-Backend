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
 * JSF Managed Beam encargado de la pantalla de gestionrol.xhtml lo cual realiza
 * la accion guardar o actualizar objetos roll
 *
 * @author Wilmer
 * @see ViewScoped
 * @see ManagedBean
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
     * Constructor de la clase
     */
    public MbRoll() {
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
        roll = new Roll();
        roles = new ArrayList<>();
        roles = cb.getAll(Roll.class);
        lv.ingresarVistas();
    }

    /**
     * metodo utilizado de accion la cual obtiene el objeto Roll del managed
     * Beam y ejecuta la funcion de guardar de EJB CommonsBean como resultado
     * retorna un mensaje emergente notificando el resultado.
     */
    public void accionGuarda() {
        if (cb.guardar(roll)) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha Guardado el Roll");
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error", "Ha fallado al Guardar el Roll");
        }
    }

    /**
     * cargaRoll
     *
     * @param row
     */
    public void cargaRoll(Roll row) {
        this.roll = row;
    }

    /**
     * getRoll
     *
     * @return
     */
    public Roll getRoll() {
        return roll;
    }

    /**
     * setRoll
     *
     * @param roll
     */
    public void setRoll(Roll roll) {
        this.roll = roll;
    }

    /**
     * getRoles
     *
     * @return
     */
    public List<Roll> getRoles() {
        return roles;
    }

    /**
     * setRoles
     *
     * @param roles
     */
    public void setRoles(List<Roll> roles) {
        this.roles = roles;
    }

    /**
     * Metodo utilizado para ejecutar la presentacion de un mensaje emergente
     * tipo Growl en pantalla
     *
     * @param icono
     * @param titulo
     * @param mensaje
     */
    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

}
