/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Dispositivo;
import co.edu.ucc.coe.model.Usuario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilme
 */
@Stateless
@LocalBean
public class LogicaDispositivo {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public Dispositivo getDispositivo(Usuario u) {
        try {
            return (Dispositivo) em.createQuery("SELECT d FROM Dispositivo d WHERE d.usuario.id= :u").setParameter("u", u.getId()).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
