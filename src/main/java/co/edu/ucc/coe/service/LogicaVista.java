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
 * EJB encargado de realizar todas las operaciones que giran entorno a la gestion de vistas
 * @author wilme
 * @see LocalBean
 * @see LocalBean
 * 
 */
@Stateless
@LocalBean
public class LogicaVista {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    /**
     * metodo que permite adicionar las nuevas vistas segun se vallan creando los .Xhtml
     */
    public void ingresarVistas() {
        GuardarVista("index", "index.xhtml", "");
        GuardarVista("Mi Equipo", "gestionequipo.xhtml", "");
        GuardarVista("ejemplo", "gestionequipo.xhtml", "");
    }

    /**
     * funcion encargada de guardar las nuevas vistas
     * @param nombreVista
     * @param rutaVista
     * @param icono
     * @return 
     */
    public boolean GuardarVista(String nombreVista, String rutaVista, String icono) {
        Vista v = new Vista();
        v.setNombre(nombreVista);
        v.setRuta(rutaVista);
        v.setIcono(icono);
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

    /**
     * Funcion encargada de obtener todas las vista creadas
     * @return 
     */
    public List<Vista> getVistas1() {
        return em.createQuery("SELECT r FROM Vista r").getResultList();
    }

    /**
     * Funcion Encargada de Obtener la VistaxRoll  Cruzando la consulta por idRoll y por el nombre de la vista
     * lo que arroja un Boolean 
     * @param idRoll
     * @param nombre
     * @return 
     */
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

    /**
     * metodo encargado de Gestionar las Vistas por Roll
     * @param vistaActivas
     * @param idRoll 
     */
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
