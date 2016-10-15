/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.clases.CredencialesLoguin;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaLoguin;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author wilme
 */
@Path("usuario")
@Stateless
public class UsuarioResource {

    @Context
    private UriInfo context;

    @EJB
    private LogicaLoguin ll;

    @EJB
    private CommonsBean cb;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String getUsuarioLogin(@PathParam("id") String id) {
       
        Gson gson = new Gson();
        CredencialesLoguin cl = gson.fromJson(id, CredencialesLoguin.class);
        Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
        System.out.println(id);
        System.out.println(cl.getNombre() + " " + cl.getContrasena());
        Usuario usuario = ll.login(cl.getNombre(), cl.getContrasena());
        co.edu.ucc.coe.clases.Usuario u = new co.edu.ucc.coe.clases.Usuario();
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setInformeDeError(1);
            String repuesta = g.toJson(usuario);
            System.out.println(repuesta);
            return repuesta;
        } else {
            u.setId(usuario.getId());
            u.setNombreUsuario(usuario.getNombreUsuario());
            u.setInformeDeError(2);
            u.setEmail(usuario.getEmail());
            u.setTelefono(usuario.getTelefono());
            u.setContrasena(usuario.getContrasena());
            String repuesta = g.toJson(u);
            System.out.println(repuesta);
            return repuesta;
        }
    }

    /**
     * PUT method for updating or creating an instance of UsuarioResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
