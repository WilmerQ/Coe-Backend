/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.clases.VistaActiva;
import co.edu.ucc.coe.model.Roll;
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
        GuardarVista("mapa", "gestionequipo.xhtml");
        GuardarVista("ejemplo", "gestionequipo.xhtml");
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

    public Boolean getvistas(Long idRoll, String nombre) {
        List<VistaActiva> vistaActivas = new ArrayList<>();
        List<VistasXRoll> vistasXRolls = em.createQuery("SELECT r FROM VistasXRoll r WHERE r.roll.id= :i AND r.vista.nombre= :n").setParameter("i", idRoll).setParameter("n", nombre).getResultList();

        for (int i = 0; i < vistasXRolls.size(); i++) {
            VistaActiva vistaActiva = new VistaActiva();
            vistaActiva.setActiva(Boolean.TRUE);
            vistaActiva.setNombre(vistasXRolls.get(i).getVista().getNombre());
            vistaActivas.add(vistaActiva);
            System.out.println("-------------------------------------");
            System.out.println("elemento:" + i);
            System.out.println("nombre:" + vistaActiva.getNombre());
            System.out.println("boolean:" + vistaActiva.getActiva());
        }

        if (!vistasXRolls.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void guardarVistas(List<VistaActiva> vistaActivas, Long idRoll) {
        Roll r = (Roll) em.find(Roll.class, idRoll);
        System.out.println("rol " + (Roll) em.find(Roll.class, idRoll));
        for (VistaActiva va : vistaActivas) {
            Vista v = (Vista) em.createQuery("SELECT v FROM Vista v WHERE v.nombre= :i").setParameter("i", va.getNombre()).getSingleResult();
            if (va.getActiva()) {
                if ((Long) em.createQuery("SELECT COUNT(vr) FROM VistasXRoll vr WHERE vr.roll.id= :i AND vr.vista= :v").setParameter("i", idRoll).setParameter("v", v).getSingleResult() == 0) {
                    System.out.println("vista v: " + v.getNombre());
                    VistasXRoll vistasXRoll = new VistasXRoll();
                    vistasXRoll.setRoll(r);
                    vistasXRoll.setVista(v);
                    em.persist(vistasXRoll);
                }
            } else {
                if ((Long) em.createQuery("SELECT COUNT(vr) FROM VistasXRoll vr WHERE vr.roll.id= :i AND vr.vista= :v").setParameter("i", idRoll).setParameter("v", v).getSingleResult() > 0) {
                    System.out.println("Existe la vista a eliminar");
                    em.createQuery("DELETE FROM VistasXRoll vr WHERE vr.roll.id= :i AND vr.vista= :v").setParameter("i", idRoll).setParameter("v", v).executeUpdate();
                }
            }
        }
    }
}
