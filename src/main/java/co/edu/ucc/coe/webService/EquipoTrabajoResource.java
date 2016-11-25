/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.clases.Dispositivo;
import co.edu.ucc.coe.clases.SolicitarEquipo;
import co.edu.ucc.coe.model.EquipoTrabajo;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author wilme
 */
@Path("EquipoTrabajo")
@Stateless
public class EquipoTrabajoResource {

    @Context
    private UriInfo context;
    @EJB
    private CommonsBean cb;

    /**
     * Creates a new instance of EquipoTrabajoResource
     */
    public EquipoTrabajoResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response TodosEquipos() {
        try {
            List<EquipoTrabajo> equipoTrabajos = cb.getAll(EquipoTrabajo.class);
            List<co.edu.ucc.coe.clases.EquipoTrabajo> equipoTrabajos1 = new ArrayList<>();
            for (EquipoTrabajo et : equipoTrabajos) {
                co.edu.ucc.coe.clases.EquipoTrabajo et1 = new co.edu.ucc.coe.clases.EquipoTrabajo();
                et1.setNombreEquipo(et.getNombreEquipo());
                et1.setId(et.getId());
                co.edu.ucc.coe.clases.Usuario u = new co.edu.ucc.coe.clases.Usuario();
                u.setNombreUsuario(et.getJefeEquipo().getNombreUsuario());
                et1.setJefeEquipo(u);
                equipoTrabajos1.add(et1);
            }
            Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
            return new ResponseMessenger().getResponseOk(g.toJson(equipoTrabajos1));
        } catch (Exception e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
    }

}
