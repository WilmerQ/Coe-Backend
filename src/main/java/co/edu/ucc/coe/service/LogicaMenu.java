/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.model.Vista;
import co.edu.ucc.coe.model.VistasXRoll;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB encargado de la realizacion de los metodos necesarios para alimentar el
 * menu lateral para cada usuario especifico
 *
 * @author wilme
 * @see Stateless
 * @see LocalBean
 */
@Stateless
@LocalBean
public class LogicaMenu {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    /**
     * funcion que recibe un Objeto usuario y reliza la consulta de que vistas se encuentran activas para dicho usuario
     * @param u
     * @return
     */
    public List<Vista> getVistasMenuUsuario(Usuario u) {
        try {
            List<Vista> lv = new ArrayList<>();
            List<VistasXRoll> vistasXRolls = em.createQuery("SELECT r FROM VistasXRoll r WHERE r.roll.id= :i").setParameter("i", u.getRollUsuario().getId()).getResultList();
            System.out.println("LogicaMenu: getVistasMenuUsuario----" + vistasXRolls.size());
            for (VistasXRoll vxr : vistasXRolls) {
                lv.addAll((List<Vista>) em.createQuery("SELECT r FROM Vista r WHERE r = :i").setParameter("i", vxr.getVista()).getResultList());
            }
            return lv;
        } catch (Exception e) {
            return null;
        }
    }
}
