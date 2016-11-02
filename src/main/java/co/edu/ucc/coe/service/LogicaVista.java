/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.clases.VistaActiva;
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
public class LogicaVista {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public void ingresarVistas() {
        GuardarVista("index", "index.xhtml");
        GuardarVista("equipo", "gestionequipo.xhtml");
    }

    public boolean GuardarVista(String nombreVista, String rutaVista) {
        Vista v = new Vista();
        v.setNombre(nombreVista);
        v.setRuta(rutaVista);
        try {
            Long temp = (Long) em.createQuery("SELECT COUNT(v) FROM Vista v WHERE v.nombre= :t").setParameter("t", v.getNombre()).getSingleResult();
            System.out.println("vista temp = " + temp);
            if (temp == 0) {
                em.persist(v);
                System.out.println("Guardando vistas ");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vista> getVistas1() {
        return em.createQuery("SELECT r FROM Vista r").getResultList();
    }

    public List<VistaActiva> getvistas(Long idRoll) {
        List<VistaActiva> vistaActivas = new ArrayList<>();
        List<VistasXRoll> vistasXRolls = em.createQuery("SELECT r FROM VistasXRoll r WHERE r.roll.id= :i").setParameter("i", idRoll).getResultList();

        for (int i = 0; i < vistasXRolls.size(); i++) {
            VistaActiva vistaActiva = new VistaActiva();
            vistaActiva.setActiva(Boolean.TRUE);
            vistaActiva.setNombre(vistasXRolls.get(i).getVista().getNombre());
            vistaActivas.add(vistaActiva);
        }
        return vistaActivas;
    }
}
