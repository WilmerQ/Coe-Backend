/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.clases.Dispositivo;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author wilme
 */
@Path("dispositivo")
@Stateless
public class DispositivoResource {

    @Context
    private UriInfo context;
    @EJB
    private CommonsBean cb;

    /**
     * Creates a new instance of DispositivoResource
     */
    public DispositivoResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.ucc.coe.webService.DispositivoResource
     *
     * @param dispositivo
     * @return an instance of java.lang.String
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{dispositivo}")
    public Response exampleResponse(@PathParam("dispositivo") String dispositivo) {
        try {
            Gson gson = new Gson();
            Dispositivo d = gson.fromJson(dispositivo, Dispositivo.class);
            co.edu.ucc.coe.model.Dispositivo d1 = new co.edu.ucc.coe.model.Dispositivo();
            d1.setAndroidID(d.getAndroidID());
            d1.setTokenGoogle(d.getTokenGoogle());
            d1.setUsuario((Usuario) cb.getById(Usuario.class, d.getUsuario().getId()));
            System.out.println("dispositivos con mismo id: " + cb.getByOneField(co.edu.ucc.coe.model.Dispositivo.class, "androidID", d.getAndroidID()).size());
            int temp = cb.getByOneField(co.edu.ucc.coe.model.Dispositivo.class, "androidID", d.getAndroidID()).size();
            if (temp == 0) {
                if (cb.guardar(d1)) {
                    return new ResponseMessenger().getResponseOk("Guardo Dispositivo");
                } else {
                    return new ResponseMessenger().getResponseError("No se puedo Guardar El Dispositivo");
                }
            } else {
                return new ResponseMessenger().getResponseError("Dispositivo Ya existente");
            }
        } catch (Exception e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
    }

}
