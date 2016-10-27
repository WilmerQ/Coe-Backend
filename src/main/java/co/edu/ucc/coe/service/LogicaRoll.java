/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Roll;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alvaro Padilla
 */
@Stateless
@LocalBean
public class LogicaRoll {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

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

    public List<Roll> getRolles() {
        return em.createQuery("SELECT r FROM Roll r").getResultList();
    }
}
