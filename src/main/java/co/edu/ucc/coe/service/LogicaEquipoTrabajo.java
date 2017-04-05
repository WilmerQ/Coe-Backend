/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Coordenadas;
import co.edu.ucc.coe.model.Dispositivo;
import co.edu.ucc.coe.model.EquipoTrabajo;
import co.edu.ucc.coe.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public List<EquipoTrabajo> equiposxUsuario(String NombreUsuario) {
        return em.createQuery("SELECT e from EquipoTrabajo e WHERE e.usuarioCreacion = :t").setParameter("t", NombreUsuario).getResultList();
    }

    public EquipoTrabajo getEquipoTrabajoxNombre(String NombreEquipo) {
        try {
            return (EquipoTrabajo) em.createQuery("Select o from EquipoTrabajo o where o.nombreEquipo= :v").setParameter("v", NombreEquipo).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Coordenadas> getUsuarios(EquipoTrabajo temp) {
        try {
            List<Usuario> usuarios = (List<Usuario>) em.createQuery("SELECT u FROM Usuario u where u.equipoTrabajo.id= :i").setParameter("i", temp.getId()).getResultList();
            List<Dispositivo> dispositivos;
            dispositivos = new ArrayList<>();
            for (Usuario u : usuarios) {
                System.out.println("---u " + u.getNombreUsuario());
                List<Dispositivo> dispositivos1 = (List<Dispositivo>) em.createQuery("SELECT d from Dispositivo d where d.usuario.id=:u").setParameter("u", u.getId()).getResultList();
                for (Dispositivo d : dispositivos1) {
                    System.out.println("------d " + d.getAndroidID());
                    dispositivos.add(d);
                }
            }
            List<Coordenadas> coordenadases;
            coordenadases = new ArrayList<>();
            for (Dispositivo d : dispositivos) {
                List<Coordenadas> cs = (List<Coordenadas>) em.createQuery("SELECT l from Coordenadas l WHERE l.dispositivo.id=:k").setParameter("k", d.getId()).getResultList();
                System.out.println("size " + cs.size());
                if (cs.size() > 0) {
                    System.out.println("---d " + cs.get(cs.size() - 1).getLatitud());
                    coordenadases.add(cs.get(cs.size() - 1));
                }
            }
            return coordenadases;
        } catch (Exception e) {
            return null;
        }
    }
}
