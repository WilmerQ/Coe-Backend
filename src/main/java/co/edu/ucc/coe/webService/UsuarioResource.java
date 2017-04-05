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
 * REST Web Service para gestion de usuario desde el aplicativo movil
 *
 * @author wilme
 * @see Stateless
 * @see Path
 */
@Path("usuario")
@Stateless
public class UsuarioResource {

    @Context
    private UriInfo context;

    @EJB
    private LogicaLoguin ll;

    /**
     * Constructor de clase
     */
    public UsuarioResource() {
    }

    /**
     * Metodo Get encargado de recibir la informacion de logueo para un usuario
     * desde el aplativo movil el cual envia el nombre de usuario, y la
     * contraseña encriptida en md5 y se le responde con el objeto usuario con
     * la informacion o con el informe de error.
     *
     * @param usuario
     * @return
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
                //Gson g = new GsonBuilder().setExclusionStrategies(new GsonExcludeListStrategy()).setPrettyPrinting().create();
                Gson g = new GsonBuilder().setPrettyPrinting().create();
                u.setUsuarioCreacion(null);
                return new ResponseMessenger().getResponseOk(g.toJson(u));
            } else {
                return new ResponseMessenger().getResponseError("Usuario y/o Contraseña incorrecto");
            }
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
    }
}
