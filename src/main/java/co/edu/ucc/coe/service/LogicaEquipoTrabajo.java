/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.EquipoTrabajo;
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
public class LogicaEquipoTrabajo {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;
    
    public List<EquipoTrabajo> equiposxUsuario(String NombreUsuario){
        return em.createQuery("SELECT e from EquipoTrabajo e WHERE e.usuarioCreacion = :t").setParameter("t", NombreUsuario).getResultList();
        
    }
}
