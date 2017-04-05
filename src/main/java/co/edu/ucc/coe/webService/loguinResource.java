/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.clases.CredencialesLoguin;
import co.edu.ucc.coe.clases.EquipoTrabajo;
import co.edu.ucc.coe.clases.usuarioAndroid;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.LogicaLoguin;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
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
@Path("loguin")
@Stateless
public class loguinResource {

    @Context
    private UriInfo context;

    @EJB
    private LogicaLoguin ll;

    /**
     * Creates a new instance of loguinResource
     */
    public loguinResource() {
    }

    /**
     * Retrieves representation of an instance of
     * co.edu.ucc.coe.webService.loguinResource
     *
     * @param usuario
     * @return an instance of java.lang.String
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{usuario}")
    public Response exampleResponse(@PathParam("usuario") String usuario) {
        try {
            Gson gson = new Gson();
            CredencialesLoguin cl = gson.fromJson(usuario, CredencialesLoguin.class);
            Usuario u = ll.login(cl.getNombre(), cl.getContrasena());
            if (u != null) {
                Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
                EquipoTrabajo equipoTrabajo = new EquipoTrabajo();
                equipoTrabajo.setId(u.getEquipoTrabajo().getId());
                equipoTrabajo.setNombreEquipo(u.getEquipoTrabajo().getNombreEquipo());

                usuarioAndroid android = new usuarioAndroid();
                android.setContrasena(u.getContrasena());
                android.setEmail(u.getEmail());
                android.setId(u.getId());
                android.setNombreUsuario(u.getNombreUsuario());
                android.setTelefono(u.getTelefono());
                android.setEquipoTrabajo(equipoTrabajo);
                
                return new ResponseMessenger().getResponseOk(g.toJson(android));
            } else {
                return new ResponseMessenger().getResponseError("Usuario y/o Contraseña incorrecto");
            }
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
    }
}
