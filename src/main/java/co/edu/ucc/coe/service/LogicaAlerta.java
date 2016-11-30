/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
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
    public static final String GCM_API_KEY = "AIzaSyB6LjtQdIdeA8l0pExtdvg5zJEKBddnJC0";
    //mensaje a mandar.
    public static final String MESSAGE_VALUE = "Esto es una alerta!";
    public static final String MESSAGE_KEY = "message";

    public void EnviarAlerta(Object a, String nombreProyecto) throws IOException {
        System.out.println("Enviar Alerta");
        Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
        String mensaje = g.toJson(a);
        System.out.println("Json " + mensaje);
        List<String> ds1 = getDispositivos(nombreProyecto);
        if (!ds1.isEmpty()) {
            ArrayList<String> devicesList = new ArrayList<>();
            for (String s1 : ds1) {
                System.out.println(s1);
                devicesList.add(s1);
            }
            Sender sender = new Sender(GCM_API_KEY);
            Message message = new Message.Builder().timeToLive(30).delayWhileIdle(true).addData(MESSAGE_KEY, mensaje).build();
            MulticastResult result = sender.send(message, devicesList, 1);
            sender.send(message, devicesList, 1);
            System.out.println("Resultado: " + result.toString());

        }
    }

    private List<String> getDispositivos(String NombreProyecto) {
        return em.createQuery("SELECT d.tokenGoogle FROM Dispositivo d where d.usuario.equipoTrabajo.nombreEquipo= :n").setParameter("n", NombreProyecto).getResultList();
    }
}
