/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.base.Md5;
import co.edu.ucc.coe.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilme
 */
@Stateless
public class LogicaLoguin {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public Usuario login(String nombreUsuario, String passwordPlano) {
        try {
            return (Usuario) em.createQuery("Select u from Usuario u where u.nombreUsuario = :n and u.contrasena = :p")
                    .setParameter("n", nombreUsuario)
                    .setParameter("p", passwordPlano).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
