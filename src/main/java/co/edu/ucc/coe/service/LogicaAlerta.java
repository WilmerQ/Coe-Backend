/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.model.Dispositivo;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    //public static final String GCM_API_KEY = "AIzaSyANLRpamMIbOZ_nOmU8JnroWtBH8_bdtBw";
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
            Message message = new Message.Builder().timeToLive(30).delayWhileIdle(true).addData(MESSAGE_KEY, mensaje).build();
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
}
