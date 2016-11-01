/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilme
 */
@Stateless
public class LogicaUsuarios {
    
    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;
    
    public List<Usuario> getUsuariosConRoll() {
        List<Usuario> temp = em.createQuery("SELECT r FROM Usuario r where r.rollUsuario!=null").getResultList();
        System.out.println("tamaño list usuarios" + temp.size());
        return temp;
    }
    
    public List<Usuario> getUsuariosSinRoll() {
        List<Usuario> temp = em.createQuery("SELECT r FROM Usuario r where r.rollUsuario=null").getResultList();
        System.out.println("tamaño list usuarios" + temp.size());
        return temp;
    }
   
}
