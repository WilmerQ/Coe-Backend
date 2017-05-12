/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.clases.AlertaManual;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaAlerta;
import co.edu.ucc.coe.service.LogicaEmailAdjunto;
import co.edu.ucc.coe.service.reporte.LogicaReporteAlerta;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.jersey.multipart.FormDataParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.castor.core.util.Base64Decoder;

/**
 * REST Web Service
 *
 * @author wilme
 */
@Path("auxiliar")
@Stateless
public class AuxiliarResource {

    @Context
    private UriInfo context;

    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaAlerta la;
    @EJB
    private LogicaEmailAdjunto le;
    @EJB
    private LogicaReporteAlerta logicaReporteAlerta;

    /**
     * Creates a new instance of AuxiliarResource
     */
    public AuxiliarResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.ucc.coe.webService.AuxiliarResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{rangoalerta}")
    public Response alertaXRangoDeTiempo(@PathParam("rangoalerta") String rangoalerta) {
        return null;
        /*try {
            Gson gson = new Gson();
            String s = gson.fromJson(rangoalerta, String.class);
            List<co.edu.ucc.coe.model.alerta.AlertaManual> alertas = cb.getAll(co.edu.ucc.coe.model.alerta.AlertaManual.class);
            System.out.println("rando recibido: " + s);
            List<AlertaManualGcm> alertaManualGcms = new ArrayList<>();
            if (s.equals("ultima hora")) {
                Date d = new Date(System.currentTimeMillis() - 3600 * 1000);
                System.out.println("d" + d.getTime() + " " + d.toString());
                for (co.edu.ucc.coe.model.alerta.AlertaManual am : alertas) {
                    System.out.println("hora servidor " + am.getHoraServidor());
                    if (am.getHoraServidor().after(d)) {
                        AlertaManualGcm amg = new AlertaManualGcm();
                        amg.setHoraDispositivo(am.getHoraDispositivo());
                        amg.setId(am.getId());
                        amg.setLatitud(am.getLatitud());
                        amg.setLongitud(am.getLongitud());
                        amg.setNivelAlerta(am.getNivelAlerta());
                        amg.setNombre(am.getNombre());
                        amg.setNota(am.getNota());
                        alertaManualGcms.add(amg);
                    }
                }
            } else if (s.equals("ultimas seis horas")) {
                Date d = new Date(System.currentTimeMillis() - 3600 * 1000 * 6);
                System.out.println("d" + d.getTime() + " " + d.toString());
                for (co.edu.ucc.coe.model.alerta.AlertaManual am : alertas) {
                    System.out.println("hora servidor " + am.getHoraServidor());
                    if (am.getHoraServidor().after(d)) {
                        AlertaManualGcm amg = new AlertaManualGcm();
                        amg.setHoraDispositivo(am.getHoraDispositivo());
                        amg.setId(am.getId());
                        amg.setLatitud(am.getLatitud());
                        amg.setLongitud(am.getLongitud());
                        amg.setNivelAlerta(am.getNivelAlerta());
                        amg.setNombre(am.getNombre());
                        amg.setNota(am.getNota());
                        alertaManualGcms.add(amg);
                    }
                }
            } else if (s.equals("ultimo dia")) {
                Date d = new Date(System.currentTimeMillis() - 3600 * 1000 * 24);
                System.out.println("d" + d.getTime() + " " + d.toString());
                for (co.edu.ucc.coe.model.alerta.AlertaManual am : alertas) {
                    System.out.println("hora servidor " + am.getHoraServidor());
                    if (am.getHoraServidor().after(d)) {
                        AlertaManualGcm amg = new AlertaManualGcm();
                        amg.setHoraDispositivo(am.getHoraDispositivo());
                        amg.setId(am.getId());
                        amg.setLatitud(am.getLatitud());
                        amg.setLongitud(am.getLongitud());
                        amg.setNivelAlerta(am.getNivelAlerta());
                        amg.setNombre(am.getNombre());
                        amg.setNota(am.getNota());
                        alertaManualGcms.add(amg);
                    }
                }
            } else if (s.equals("ultima semana")) {
                Date d = new Date(System.currentTimeMillis() - 3600 * 1000 * 168);
                System.out.println("d" + d.getTime() + " " + d.toString());
                for (co.edu.ucc.coe.model.alerta.AlertaManual am : alertas) {
                    System.out.println("hora servidor " + am.getHoraServidor());
                    if (am.getHoraServidor().after(d)) {
                        AlertaManualGcm amg = new AlertaManualGcm();
                        amg.setHoraDispositivo(am.getHoraDispositivo());
                        amg.setId(am.getId());
                        amg.setLatitud(am.getLatitud());
                        amg.setLongitud(am.getLongitud());
                        amg.setNivelAlerta(am.getNivelAlerta());
                        amg.setNombre(am.getNombre());
                        amg.setNota(am.getNota());
                        alertaManualGcms.add(amg);
                    }
                }
            } else if (s.equals("todas")) {
                for (co.edu.ucc.coe.model.alerta.AlertaManual am : alertas) {
                    AlertaManualGcm amg = new AlertaManualGcm();
                    amg.setHoraDispositivo(am.getHoraDispositivo());
                    amg.setId(am.getId());
                    amg.setLatitud(am.getLatitud());
                    amg.setLongitud(am.getLongitud());
                    amg.setNivelAlerta(am.getNivelAlerta());
                    amg.setNombre(am.getNombre());
                    amg.setNota(am.getNota());
                    alertaManualGcms.add(amg);
                }
            }
            return new ResponseMessenger().getResponseOk(gson.toJson(alertaManualGcms));
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
         */
    }

    /**
     * PUT method for updating or creating an instance of AuxiliarResource
     *
     * @param content representation for the resource
     * @return
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) throws Exception {
        try {
            Gson gson = new Gson();
            AlertaManual am = gson.fromJson(content, AlertaManual.class);
            try {
                System.out.println("tamaño getImagen " + am.getImagen().length());
            } catch (Exception e) {
            }
            try {
                System.out.println("tamaño getAudio " + am.getAudio().length);
            } catch (Exception e) {
            }
            try {
                System.out.println("tamaño getVideo " + am.getVideo().length);
            } catch (Exception e) {
            }

            final co.edu.ucc.coe.model.alerta.AlertaManual alertaManual = new co.edu.ucc.coe.model.alerta.AlertaManual();
            UUID idManual = UUID.randomUUID();
            alertaManual.setIdManual(idManual.toString().toUpperCase());
            alertaManual.setNombrePersona(am.getNombrePersona());
            alertaManual.setCorreo(am.getCorreo());
            alertaManual.setCelular(am.getCelular());
            alertaManual.setLatitud(am.getLatitud());
            alertaManual.setLongitud(am.getLongitud());
            alertaManual.setTipoEvento(am.getTipoEvento());
            java.util.Date d = new java.util.Date();
            alertaManual.setHoraServidor(d);
            alertaManual.setHoraDispositivo(am.getHora());

            if (am.getImagen() != null) {
                byte[] bs = Base64Decoder.decode(am.getImagen());
                System.out.println("bs" + bs.length);
                alertaManual.setFoto(bs);
            }

            try {
                if (am.getVideo().length > 0) {
                    alertaManual.setVideo(am.getVideo());
                }
            } catch (Exception e) {
            }

            try {
                if (am.getAudio().length > 0) {
                    alertaManual.setAudio(am.getAudio());
                }
            } catch (Exception e) {
            }

            if (cb.guardar(alertaManual)) {
                //la.InformarAlertaManual(alertaManual);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        le.EnviarEmail();
                    }
                }).start();

                return new ResponseMessenger().getResponseOk("ok");
            } else {
                return new ResponseMessenger().getResponseError("No se logro Guardar su alerta, intente nuevamente");
            }
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor, intente de nuevo");
        }
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response post(@FormDataParam("file") InputStream alerta) {
        AlertaManual am = null;
        try {
            Gson gson = new Gson();
            String s = IOUtils.toString(alerta);
            System.out.println("alerta: " + s.length());
            int inicio = s.indexOf("{");
            System.out.println("{" + inicio);
            int fin = s.lastIndexOf("}");
            System.out.println("}" + fin);
            String aux = s.substring(inicio, fin + 1);
            System.out.println("aux " + aux.length());
            am = gson.fromJson(aux, AlertaManual.class);
            System.out.println("coreo clase: " + am.getCorreo());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            try {
                System.out.println("tamaño getImagen " + am.getImagen().length());
            } catch (Exception e) {
            }
            try {
                System.out.println("tamaño getAudio " + am.getAudio().length);
            } catch (Exception e) {
            }
            try {
                System.out.println("tamaño getVideo " + am.getVideo().length);
            } catch (Exception e) {
            }

            final co.edu.ucc.coe.model.alerta.AlertaManual alertaManual = new co.edu.ucc.coe.model.alerta.AlertaManual();
            UUID idManual = UUID.randomUUID();
            alertaManual.setIdManual(idManual.toString().toUpperCase());
            alertaManual.setNombrePersona(am.getNombrePersona());
            alertaManual.setCorreo(am.getCorreo());
            alertaManual.setCelular(am.getCelular());
            alertaManual.setLatitud(am.getLatitud());
            alertaManual.setLongitud(am.getLongitud());
            alertaManual.setTipoEvento(am.getTipoEvento());
            java.util.Date d = new java.util.Date();
            alertaManual.setHoraServidor(d);
            alertaManual.setHoraDispositivo(am.getHora());

            if (am.getImagen() != null) {
                byte[] bs = Base64Decoder.decode(am.getImagen());
                System.out.println("bs" + bs.length);
                alertaManual.setFoto(bs);
            }

            try {
                if (am.getVideo().length > 0) {
                    alertaManual.setVideo(am.getVideo());
                }
            } catch (Exception e) {
            }

            try {
                if (am.getAudio().length > 0) {
                    alertaManual.setAudio(am.getAudio());
                }
            } catch (Exception e) {
            }

            if (cb.guardar(alertaManual)) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //la.InformarAlertaManual(alertaManual);
                        le.EnviarEmail();
                        logicaReporteAlerta.generarReporte();
                    }
                }).start();

                return new ResponseMessenger().getResponseOk("ok");
            } else {
                return new ResponseMessenger().getResponseError("No se logro Guardar su alerta, intente nuevamente");
            }
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor, intente de nuevo");
        }
    }
}
