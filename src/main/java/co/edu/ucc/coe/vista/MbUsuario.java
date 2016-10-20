/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaLoguin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author wilme
 */
@Named(value = "mbUsuario")
@SessionScoped
public class MbUsuario implements Serializable {

    @EJB
    private CommonsBean commonsBean;
    
    private Usuario usuario;
    
    
    public MbUsuario() {
    }
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    
    
    
    
}
