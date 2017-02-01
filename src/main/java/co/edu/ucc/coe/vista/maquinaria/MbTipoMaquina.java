/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista.maquinaria;

import co.edu.ucc.coe.model.maquinaria.TipoMaquinaria;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.vista.MbUsuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbTipoMaquina")
public class MbTipoMaquina implements Serializable {

    @EJB
    private CommonsBean cb;
    private TipoMaquinaria tipomaquina;
    private List<SelectItem> listaTipoConteo;
    private List<SelectItem> Categorias;
    private List<SelectItem> tipoCarga;

    //-------------------------------------
    /**
     * Creates a new instance of MbMaquinaria
     */
    public MbTipoMaquina() {

    }

    @PostConstruct
    public void init() {
        tipomaquina = new TipoMaquinaria();
        listaTipoConteo = new LinkedList<>();
        Categorias = new LinkedList<>();
        tipoCarga = new LinkedList<>();
        inicializarlistas();

    }

    public void inicializarlistas() {
        listaTipoConteo.add(new SelectItem("Horas"));
        listaTipoConteo.add(new SelectItem("Kilometros"));

        Categorias.add(new SelectItem("Vehiculo de carga"));
        Categorias.add(new SelectItem("Vehiculo de rescate"));
        Categorias.add(new SelectItem("Vehiculo de apoyo"));
        Categorias.add(new SelectItem("Trasporte de personal"));
        Categorias.add(new SelectItem("vehiculo de emergencias"));

        tipoCarga.add(new SelectItem("Trasporte de carga"));
        tipoCarga.add(new SelectItem("personal"));
        tipoCarga.add(new SelectItem("otros"));
    }

    public String accionGuardar() {
        System.out.println("accionGuardar");
        try {
            if (cb.guardar(tipomaquina)) {
                mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha guardado");
                init();
                String contextPath = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
                redirect(contextPath + "/usuario/index.xhtml");
            }
        } catch (Exception e) {
        }

        return null;
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

    /**
     * Metodo encargado de rediccionar como parametro recibe un String que
     * contiene el link ejemplo /usuario/index.xht
     *
     * @param url
     */
    private void redirect(String url) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MbUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoMaquinaria getTipomaquina() {
        return tipomaquina;
    }

    public void setTipomaquina(TipoMaquinaria tipomaquina) {
        this.tipomaquina = tipomaquina;
    }

    public List<SelectItem> getListaTipoConteo() {
        return listaTipoConteo;
    }

    public void setListaTipoConteo(List<SelectItem> listaTipoConteo) {
        this.listaTipoConteo = listaTipoConteo;
    }

    public List<SelectItem> getCategorias() {
        return Categorias;
    }

    public void setCategorias(List<SelectItem> Categorias) {
        this.Categorias = Categorias;
    }

    public List<SelectItem> getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(List<SelectItem> tipoCarga) {
        this.tipoCarga = tipoCarga;
    }

}
