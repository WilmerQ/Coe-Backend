/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Peticion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilme
 */
@Stateless
public class LogicaPeticion {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public Peticion getPeticion(String Id, String nombre) {
        try {
            List<Peticion> temp = (List<Peticion>) em.createQuery("SELECT p FROM Peticion p where p.IDdispositivoRealizador= :id AND p.NombreUsuario= :nombre").setParameter("id", Id).setParameter("nombre", nombre).getResultList();
            
            return temp.get(temp.size()-1);
        } catch (Exception e) {
            return null;
        }
    }

}
