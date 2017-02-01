/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.webService;

import co.edu.ucc.coe.base.GsonExcludeListStrategy;
import co.edu.ucc.coe.clases.Coordenadas;
import co.edu.ucc.coe.clases.GcmObjeto;
import co.edu.ucc.coe.clases.GcmObjetoRespuesta;
import co.edu.ucc.coe.model.Dispositivo;
import co.edu.ucc.coe.model.EquipoTrabajo;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.model.Peticion;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaAlerta;
import co.edu.ucc.coe.service.LogicaDispositivo;
import co.edu.ucc.coe.service.LogicaEquipoTrabajo;
import co.edu.ucc.coe.service.LogicaPeticion;
import co.edu.ucc.coe.webService.base.ResponseMessenger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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

    @EJB
    private LogicaAlerta la;
    @EJB
    private LogicaEquipoTrabajo let;
    @EJB
    private LogicaDispositivo ld;
    @EJB
    private LogicaPeticion lp;

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

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{nombreEquipoTrabajo}/{usuario}")
    public Response BuscandoALaGente(@PathParam("nombreEquipoTrabajo") String nombreEquipoTrabajo,
            @PathParam("usuario") String usuario) {
        try {
            Usuario u = (Usuario) cb.getById(Usuario.class, Long.parseLong(usuario));
            Dispositivo d = ld.getDispositivo((Usuario) cb.getById(Usuario.class, Long.parseLong(usuario)));
            Gson gson = new Gson();
            String s = gson.fromJson(nombreEquipoTrabajo, String.class);
            EquipoTrabajo et = let.getEquipoTrabajoxNombre(s);
            Peticion p = new Peticion();
            System.out.println(" nombre usuario " + u.getNombreUsuario());
            p.setNombreUsuario(u.getNombreUsuario());
            p.setIDdispositivoRealizador(d.getAndroidID());
            cb.guardar(p);

            GcmObjeto gcmObjeto = new GcmObjeto();
            gcmObjeto.setTipo("CompartirUbicacion");
            System.out.println("BuscandoALaGente: setpeticion " + lp.getPeticion(d.getAndroidID(), u.getNombreUsuario()));
            gcmObjeto.setPeticion(lp.getPeticion(d.getAndroidID(), u.getNombreUsuario()));
            la.EnviarAlerta(gcmObjeto, s, d.getAndroidID());
            return new ResponseMessenger().getResponseOk("ok: " + lp.getPeticion(d.getAndroidID(), u.getNombreUsuario()).getId());
        } catch (JsonSyntaxException | IOException | NumberFormatException e) {
            return new ResponseMessenger().getResponseError("Problema interno del servidor");
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{respuesta}")
    public Response RespuestaDelEquipo(@PathParam(value = "respuesta") String respuesta, @QueryParam(value = "tipo") String tipo) throws IOException {
        System.out.println("tipo: " + tipo);
        try {
            Coordenadas c = new Coordenadas();
            Gson gson = new Gson();
            Dispositivo d = new Dispositivo();
            Peticion p = new Peticion();
            co.edu.ucc.coe.model.Coordenadas c1 = new co.edu.ucc.coe.model.Coordenadas();
            c = gson.fromJson(respuesta, Coordenadas.class);
            p = (Peticion) cb.getById(Peticion.class, c.getIdPeticion());
            d = ld.getDispositivo(c.getIdDispostitivo(), c.getAndroidID());
            c1.setDispositivo(d);
            c1.setPeticion(p);
            c1.setLatitud(c.getLatitud());
            c1.setLongitud(c.getLongitud());
            cb.guardar(c1);
            
            GcmObjetoRespuesta gcmObjetoRespuesta =  new GcmObjetoRespuesta();
            gcmObjetoRespuesta.setIdPeticion(p.getId());
            gcmObjetoRespuesta.setNombreGrupoTrabajo(d.getUsuario().getEquipoTrabajo().getNombreEquipo());
            gcmObjetoRespuesta.setNombreUsuarioResponde(d.getUsuario().getNombreUsuario());
            gcmObjetoRespuesta.setLatitud(c.getLatitud());
            gcmObjetoRespuesta.setLongitud(c.getLongitud());
            GcmObjeto gcmObjeto = new GcmObjeto();
            gcmObjeto.setTipo("RespuestaPeticion");
            gcmObjeto.setRespuesta(gcmObjetoRespuesta);
            la.EnviarRespuesADispositivoSolicitante(gcmObjeto,p,ld.getDispositivo(p.getIDdispositivoRealizador()));
            return new ResponseMessenger().getResponseOk("Ubicacion Trasmitida");
        } catch (JsonSyntaxException e) {
            return new ResponseMessenger().getResponseError("Error Interno");
        }
    }
}
