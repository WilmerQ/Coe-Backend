/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbNivelesProyectoXSenor")
public class MbNivelesProyectoXSenor {

    /**
     * Creates a new instance of MbNivelesProyectoXSenor
     */
    public MbNivelesProyectoXSenor() {
    }
    
    
    @PostConstruct
    public void init() {
        //usuarioNotificar = new UsuarioNotificar();
        //usuarioNotificars = new ArrayList<>();
        //usuarioNotificars = cb.getAll(UsuarioNotificar.class);
    }
    
}
