/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.accesoDinamico.Roll;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB encargado de almacenar los metodos refenrentes al Crud da la entidad Roll
 * @author Wilmer Quintero
 * @see Stateless
 * @see LocalBean
 */
@Stateless
@LocalBean
public class LogicaRoll {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    /**
     * funcion que recibe un Objeto Roll para almacenarlo o actualizarlo
     * @param f
     * @return
     */
    public boolean guardar(Roll f) {
        try {
            if (f.getId() == null) {
                System.out.println("guardar");
                em.persist(f);
            } else {
                System.out.println("actualizar");
                em.merge(f);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * funcion encargada de obtener todos los roll almacenados 
     * @return
     */
    public List<Roll> getRolles() {
        return em.createQuery("SELECT r FROM Roll r").getResultList();
    }
}
