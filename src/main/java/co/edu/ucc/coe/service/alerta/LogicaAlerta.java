/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service.alerta;

import co.edu.ucc.coe.model.alerta.HistoricoEstadoAlerta;
import co.edu.ucc.coe.model.alerta.proyectoSensado;
import java.io.Serializable;
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
public class LogicaAlerta implements Serializable {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    public HistoricoEstadoAlerta estadoActual() {
        try {
            List<HistoricoEstadoAlerta> alertas = em.createQuery("SELECT e FROM HistoricoEstadoAlerta e ORDER BY e.horaServidor DESC").getResultList();
            if (alertas.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + alertas.size());
                System.out.println("list " + alertas.get(0).getNivelAlerta());
                return alertas.get(0);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public proyectoSensado BuscarProyecto(String nombre) {
        try {
            proyectoSensado sensado = (proyectoSensado) em.createQuery("SELECT p FROM proyectoSensado p WHERE p.nombre=:s").setParameter("s", nombre).getSingleResult();
            return sensado;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean proyectosExitentes() {
        try {
            List<proyectoSensado> proyectos = (List<proyectoSensado>) em.createQuery("SELECT p FROM proyectoSensado p").getResultList();
            if (proyectos.isEmpty()) {
                return Boolean.FALSE;
            } else {
                System.out.println("proyectosExitentes " + proyectos.size());
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /* public HistoricoEstadoAlerta estadoAnterior() {
        try {
            List<HistoricoEstadoAlerta> alertas = em.createQuery("SELECT e FROM HistoricoEstadoAlerta e ORDER BY e.horaServidor DESC").getResultList();
            if (alertas.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + alertas.size());
                if (alertas.size() > 1) {
                    System.out.println("list " + alertas.get(0).getNivelAlerta());
                    return alertas.get(1);
                }else{
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
