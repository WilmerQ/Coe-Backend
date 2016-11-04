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
 *
 * @author wilme
 */
@Stateless
@LocalBean
public class LogicaMenu {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public List<Vista> getVistasMenuUsuario(Usuario u) {
        try {
            List<Vista> lv = new ArrayList<>();
            List<VistasXRoll> vistasXRolls = em.createQuery("SELECT r FROM VistasXRoll r WHERE r.roll.id= :i").setParameter("i", u.getRollUsuario().getId()).getResultList();
            System.out.println("LogicaMenu: getVistasMenuUsuario----" + vistasXRolls.size());
            for (VistasXRoll vxr : vistasXRolls){
                lv.addAll((List<Vista>)em.createQuery("SELECT r FROM Vista r WHERE r = :i").setParameter("i", vxr.getVista()).getResultList());
            }
            return lv;
        } catch (Exception e) {
            return null;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
