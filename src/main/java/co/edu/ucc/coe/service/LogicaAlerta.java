/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.clases.AlertaManualGcm;
import co.edu.ucc.coe.clases.GcmObjeto;
import co.edu.ucc.coe.model.Dispositivo;
import co.edu.ucc.coe.model.EquipoTrabajo;
import co.edu.ucc.coe.model.Peticion;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.model.alerta.AlertaManual;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alvaro Padilla
 *
 */
@Stateless
@LocalBean
public class LogicaAlerta {

    @EJB
    private CommonsBean cb;

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    //Key de la app movil.
    public static final String GCM_API_KEY = "AIzaSyCXnoCoOYk7uxUJCETprOtbCtd8AmEX7Wk";
    //mensaje a mandar.
    public static final String MESSAGE_VALUE = "Esto es una alerta!";
    public static final String MESSAGE_KEY = "message";

    public void EnviarAlerta(Object a, String nombreProyecto, String id) throws IOException {
        System.out.println("Enviar Alerta");
        Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
        String mensaje = g.toJson(a);
        System.out.println("Json " + mensaje);
        List<String> ds1 = getDispositivos(nombreProyecto, id);
        if (!ds1.isEmpty()) {
            ArrayList<String> devicesList = new ArrayList<>();
            for (String s1 : ds1) {
                System.out.println("EnviarAlerta: s1 " + s1);
                devicesList.add(s1);
            }
            Sender sender = new Sender(GCM_API_KEY);
            System.out.println("EnviarAlerta: sender" + sender.toString());
            Message message = new Message.Builder().delayWhileIdle(true).addData(MESSAGE_KEY, mensaje).build();
            MulticastResult result = sender.send(message, devicesList, 1);
            System.out.println("Resultado: " + result.toString());
            sender.send(message, devicesList, 1);
        }
    }

    private List<String> getDispositivos(String NombreProyecto, String IdGenera) {
        try {
            Dispositivo d = (Dispositivo) em.createQuery("SELECT d FROM Dispositivo d WHERE d.androidID= :id").setParameter("id", IdGenera).getSingleResult();
            List<String> temp = em.createQuery("SELECT d.tokenGoogle FROM Dispositivo d where d.usuario.equipoTrabajo.nombreEquipo= :n")
                    .setParameter("n", NombreProyecto).getResultList();
            List<String> temp2 = new ArrayList();
            for (String s : temp) {
                if (!s.equals(d.getTokenGoogle())) {
                    temp2.add(s);
                    System.out.println("getDispositivos: s " + s);
                }
            }
            return temp2;
        } catch (Exception e) {
            System.out.println("getDispositivos" + e.getMessage());
            return null;
        }

    }

    public void EnviarRespuesADispositivoSolicitante(Object a, Peticion p, Dispositivo d) throws IOException {
        System.out.println("Enviar EnviarRespuesADispositivoSolicitante");
        Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
        String mensaje = g.toJson(a);
        System.out.println("Json " + mensaje);
        String ds1 = d.getTokenGoogle();
        if (!ds1.isEmpty()) {
            ArrayList<String> devicesList = new ArrayList<>();
            System.out.println("EnviarRespuesADispositivoSolicitante: s1 " + ds1);
            devicesList.add(ds1);
            Sender sender = new Sender(GCM_API_KEY);
            System.out.println("EnviarRespuesADispositivoSolicitante: sender" + sender.toString());
            Message message = new Message.Builder().delayWhileIdle(true).addData(MESSAGE_KEY, mensaje).build();
            MulticastResult result = sender.send(message, devicesList, 1);
            System.out.println("Resultado: " + result.toString());
            sender.send(message, devicesList, 1);
        }
    }

    private AlertaManual getAlertaManual(AlertaManual am) {
        try {
            AlertaManual am1 = (AlertaManual) em.createQuery("SELECT al FROM AlertaManual al WHERE al.nombrePersona :n AND al.latitud= :lat AND al.longitud=:lon")
                    .setParameter("n", am.getNombrePersona()).setParameter("lat", am.getLatitud()).setParameter("lon", am.getLongitud()).getSingleResult();
            return am1;
        } catch (Exception e) {
            return null;
        }
    }

    public void InformarAlertaManual(co.edu.ucc.coe.model.alerta.AlertaManual alertaManual) throws IOException, Exception {

        Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
        List<EquipoTrabajo> temp = (List<EquipoTrabajo>) cb.getAll(EquipoTrabajo.class);
        List<Usuario> usuarios = new ArrayList<>();
        for (EquipoTrabajo et : temp) {
            usuarios.add(et.getJefeEquipo());
        }
        Set<Usuario> usuarios1 = new LinkedHashSet<Usuario>(usuarios);
        usuarios.clear();
        usuarios.addAll(usuarios1);

        System.out.println("usuarios:tamaño final " + usuarios.size());
        List<Dispositivo> ds = new ArrayList<>();
        List<Dispositivo> dispositivos = cb.getAll(Dispositivo.class);
        System.out.println("dispositivos size: " + dispositivos.size());
        for (Dispositivo d : dispositivos) {
            for (Usuario u : usuarios) {
                if (d.getUsuario().getNombreUsuario().equals(u.getNombreUsuario())) {
                    ds.add(d);
                }
            }
        }
        System.out.println("ds: tamano " + ds.size());
        AlertaManual am = getAlertaManual(alertaManual);
//*************************************************
        AlertaManualGcm manualGcm = new AlertaManualGcm();
        if (am != null) {
            manualGcm.setNombrePersona(am.getNombrePersona());
            manualGcm.setCelular(am.getCelular());
            manualGcm.setCorreo(am.getCorreo());
            manualGcm.setTipoEvento(am.getTipoEvento());
            manualGcm.setHoraDispositivo(am.getHoraDispositivo());
            manualGcm.setHoraServidor(am.getHoraServidor());
            manualGcm.setId(am.getId());
            manualGcm.setLatitud(am.getLatitud());
            manualGcm.setLongitud(am.getLongitud());           
//************************************************        
            GcmObjeto gcmObjeto = new GcmObjeto();
            gcmObjeto.setTipo("alertaManual");
            gcmObjeto.setAlertaManualGcm(manualGcm);
            String mensaje = g.toJson(gcmObjeto);
            System.out.println("Json " + mensaje);
            List<String> ds1 = new ArrayList<>();
            for (Dispositivo d : ds) {
                ds1.add(d.getTokenGoogle());
            }
            if (!ds1.isEmpty()) {
                ArrayList<String> devicesList = new ArrayList<>();
                for (String s1 : ds1) {
                    System.out.println("EnviarAlerta: s1 " + s1);
                    devicesList.add(s1);
                }
                Sender sender = new Sender(GCM_API_KEY);
                System.out.println("EnviarAlerta: sender" + sender.toString());
                Message message = new Message.Builder().delayWhileIdle(true).addData(MESSAGE_KEY, mensaje).build();
                MulticastResult result = sender.send(message, devicesList, 1);
                System.out.println("Resultado: " + result.toString());
                sender.send(message, devicesList, 1);
            }
        }
    }
}
