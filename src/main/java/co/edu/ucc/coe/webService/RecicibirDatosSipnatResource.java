/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.model.alerta.HistoricoEstadoAlerta;
import co.edu.ucc.coe.model.alerta.proyectoSensado;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.alerta.LogicaAlerta;
import co.edu.ucc.coe.sipnat.clases.Dato;
import co.edu.ucc.coe.sipnat.clases.TipoSensor;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * REST Web Service
 *
 * @author wilme
 */
@Path("RecicibirDatosSipnat")
//@Stateful
//@LocalBean
@Stateless
public class RecicibirDatosSipnatResource {

    @Context
    private UriInfo context;
    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaAlerta logicaAlerta;

    /**
     * Creates a new instance of RecicibirDatosSipnatResource
     */
    public RecicibirDatosSipnatResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.ucc.coe.webService.RecicibirDatosSipnatResource
     *
     * @param datos
     * @param tipos
     * @return an instance of java.lang.String
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{datos}/{tipos}")
    public Response RecibirDatos(@PathParam("datos") String datos, @PathParam("tipos") String tipos) {
        try {
            Gson gson = new Gson();
            Dato temp = new Dato();
            List<Dato> datosRecibidos = new ArrayList<>();
            Type listType = new TypeToken<ArrayList<Dato>>() {
            }.getType();
            datosRecibidos = new Gson().fromJson(datos, listType);
            List<TipoSensor> tiposSensor = new ArrayList<>();
            Type listType2 = new TypeToken<ArrayList<TipoSensor>>() {
            }.getType();
            tiposSensor = new Gson().fromJson(tipos, listType2);
            System.out.println("tiposensor " + tiposSensor.size());
            if (tiposSensor.size() > 0) {
                for (TipoSensor ts : tiposSensor) {
                    if (logicaAlerta.tiposSensorExitentes()) {
                        System.out.println("tipos de sensor" + ts.getNombre() + " -- " + ts.getUnidadDeMedida());
                        List<co.edu.ucc.coe.model.alerta.TipoSensor> list = cb.getByOneField(co.edu.ucc.coe.model.alerta.TipoSensor.class, "idNativo", ts.getId());
                        if (list.size() > 0) {
                            System.out.println("ya existe el tipo de sensor");
                        } else {
                            co.edu.ucc.coe.model.alerta.TipoSensor ts1 = new co.edu.ucc.coe.model.alerta.TipoSensor();
                            ts1.setIdNativo(ts.getId());
                            ts1.setNombre(ts.getNombre());
                            ts1.setUnidadDeMedida(ts.getUnidadDeMedida());
                            cb.guardar(ts1);
                        }
                    } else {
                        System.out.println("no existe ningun tipo de sensor - se guardara el primero");
                        co.edu.ucc.coe.model.alerta.TipoSensor ts1 = new co.edu.ucc.coe.model.alerta.TipoSensor();
                        ts1.setIdNativo(ts.getId());
                        ts1.setNombre(ts.getNombre());
                        ts1.setUnidadDeMedida(ts.getUnidadDeMedida());
                        cb.guardar(ts1);
                    }
                }
            }
            List<String> estadosTemp = new ArrayList<>();
            for (Dato d : datosRecibidos) {
                System.out.println("-" + d.getSensor().getProyectoPadre().getNombre());
                if (logicaAlerta.proyectosExitentes()) {
                    System.out.println("existe");
                    if (logicaAlerta.BuscarProyecto(d.getSensor().getProyectoPadre().getNombre()) == null) {
                        System.out.println(" BuscarProyecto entro");
                        proyectoSensado sensado = new proyectoSensado();
                        sensado.setNombre(d.getSensor().getProyectoPadre().getNombre());
                        sensado.setDescripcion(d.getSensor().getProyectoPadre().getDescripcion());
                        cb.guardar(sensado);
                    }
                } else {
                    System.out.println("no existe");
                    proyectoSensado sensado = new proyectoSensado();
                    sensado.setNombre(d.getSensor().getProyectoPadre().getNombre());
                    sensado.setDescripcion(d.getSensor().getProyectoPadre().getDescripcion());
                    cb.guardar(sensado);
                }
                //------------------------------------------------------
                System.out.println("dato analizado: " + d.getDato());
                if ((Double.parseDouble(d.getDato()) > 0) && (Double.parseDouble(d.getDato()) < 200)) {
                    System.out.println("intervalo 0 a 200");
                    estadosTemp.add("normal");
                    temp = d;
                } else if ((Double.parseDouble(d.getDato()) >= 200) && (Double.parseDouble(d.getDato()) < 300)) {
                    System.out.println("intervalo 200 a 300");
                    estadosTemp.add("Alerta Amarrilla");
                    temp = d;
                } else if ((Double.parseDouble(d.getDato()) >= 300) && (Double.parseDouble(d.getDato()) < 400)) {
                    System.out.println("intervalo 300 a 400");
                    estadosTemp.add("Alerta naranja");
                    temp = d;
                } else if (Double.parseDouble(d.getDato()) >= 400) {
                    System.out.println("intervalo > 400");
                    estadosTemp.add("Alerta Roja");
                    temp = d;
                }
            }
            try {
                System.out.println("logicaAlerta.estadoActual() != null  entro");
                if (logicaAlerta.estadoActual().getNivelAlerta().equals(estadosTemp.get(estadosTemp.size() - 1))) {
                    return new ResponseMessenger().getResponseOk(gson.toJson("Se mantiene el estado"));
                } else {
                    HistoricoEstadoAlerta hea = new HistoricoEstadoAlerta();
                    if (!(estadosTemp.isEmpty())) {
                        if (estadosTemp.size() > 1) {
                            hea.setNivelAlerta(estadosTemp.get(estadosTemp.size() - 1));
                        } else if (estadosTemp.size() == 1) {
                            hea.setNivelAlerta(estadosTemp.get(estadosTemp.size()));
                        }
                    } else {
                        return new ResponseMessenger().getResponseError("Problema interno");
                    }
                    hea.setHoraServidor(new Date());
                    hea.setSensor(temp.getSensor().getNombreTipoSensor());
                    cb.guardar(hea);
                    return new ResponseMessenger().getResponseOk(gson.toJson(estadosTemp.get(estadosTemp.size() - 1)));
                }
            } catch (Exception e) {
                System.out.println(" catch ");
                try {
                    System.out.println(" catch -- try");
                    if (!(estadosTemp.isEmpty())) {
                        HistoricoEstadoAlerta hea = new HistoricoEstadoAlerta();
                        if (estadosTemp.size() >= 1) {
                            hea.setNivelAlerta(estadosTemp.get(estadosTemp.size() - 1));
                        } else if (estadosTemp.size() == 1) {
                            hea.setNivelAlerta(estadosTemp.get(estadosTemp.size()));
                        }
                        hea.setHoraServidor(new Date());
                        hea.setSensor(temp.getSensor().getNombreTipoSensor());
                        cb.guardar(hea);
                        return new ResponseMessenger().getResponseOk(gson.toJson(hea.getNivelAlerta()));
                    } else {
                        return new ResponseMessenger().getResponseOk(gson.toJson("sin datos"));
                    }
                } catch (Exception o) {
                    System.out.println(" catch -- catch");
                    return new ResponseMessenger().getResponseOk(gson.toJson("sin datos"));
                }
            }
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno");
        }
    }
}
