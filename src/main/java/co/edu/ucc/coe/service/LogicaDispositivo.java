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

    /**
     *
     * @param idDsipositivo
     * @param idGoogle
     * @return
     */
    public Dispositivo getDispositivo(String idGoogle, String idDsipositivo) {
        System.out.println("id Dispositivo" + idDsipositivo);
        System.out.println("id google " + idGoogle);
        try {
            return (Dispositivo) em.createQuery("SELECT d FROM Dispositivo d WHERE d.androidID= :idDispositivo AND d.tokenGoogle= :idGoogle")
                    .setParameter("idDispositivo", idDsipositivo).setParameter("idGoogle", idGoogle).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Dispositivo getDispositivo(String idDispositivo) {
        try {
            return (Dispositivo) em.createQuery("SELECT d FROM Dispositivo d WHERE d.androidID= :idDsipositivo").setParameter("idDsipositivo", idDispositivo).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
