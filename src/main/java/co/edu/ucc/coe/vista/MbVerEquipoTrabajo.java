/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import co.edu.ucc.coe.model.Coordenadas;
import co.edu.ucc.coe.model.EquipoTrabajo;
import co.edu.ucc.coe.service.CommonsBean;
import co.edu.ucc.coe.service.LogicaEquipoTrabajo;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbVerEquipoTrabajo")
public class MbVerEquipoTrabajo {

    @EJB
    private CommonsBean cb;
    @EJB
    private LogicaEquipoTrabajo let;

    private EquipoTrabajo equipoTrabajo;
    private List<SelectItem> EquiposCreados;
    private Long idEquipo;
    private MapModel draggableModel;
    private Marker marker;
    private Coordenadas coorTemp;
    EquipoTrabajo et;

    /**
     * Creates a new instance of MbVerEquipoTrabajo
     */
    public MbVerEquipoTrabajo() {
    }

    @PostConstruct
    public void init() {
        equipoTrabajo = new EquipoTrabajo();
        EquiposCreados = new LinkedList<>();
        for (EquipoTrabajo r : (List<EquipoTrabajo>) cb.getAll(EquipoTrabajo.class)) {
            EquiposCreados.add(new SelectItem(r.getId(), r.getNombreEquipo()));
        }
    }

    public void cargaProyecto() {
        if (idEquipo != null) {
            System.out.println("idEquipo " + idEquipo);
            et = (EquipoTrabajo) cb.getById(EquipoTrabajo.class, idEquipo);
            for (Coordenadas c : let.getUsuarios(et)) {
                System.out.println("c " + c.getLatitud());
                System.out.println("c " + c.getLongitud());
                System.out.println("c " + c.getPeticion());

            }
//            Proyecto p = (Proyecto) cb.getById(Proyecto.class, idProyecto);
//            sensores = cb.getByOneField(ProyectoXSensor.class, "proyecto", p);
            draggableModel = new DefaultMapModel();
//            centroZona();
            for (Coordenadas c : let.getUsuarios(et)) {
                LatLng coord1 = new LatLng(c.getLatitud(), c.getLongitud());
                Marker m = new Marker(coord1, c.getDispositivo().getUsuario().getNombreUsuario());
                draggableModel.addOverlay(m);
                //draggableModel.addOverlay(new Marker(coord1, pxs.getSensor().getId() + "", this, "http://" + DatosBasicos.ip + ":" + DatosBasicos.port + "/" + DatosBasicos.path + "/imagenServlet?id=" + pxs.getSensor().getTipoSensor().getId()));
            }
//            List<ZonaXProyecto> zxps = cb.getByOneField(ZonaXProyecto.class, "proyecto", p);
//            for (ZonaXProyecto zxp : zxps) {
//                Polygon polygon = new Polygon();
//                List<CordenadaDeLaZona> cdlzs = cb.getByOneField(CordenadaDeLaZona.class, "zonaAfectada", zxp.getZonaAfectada());
//                for (CordenadaDeLaZona cdlz : cdlzs) {
//                    LatLng ll = new LatLng(new Double(cdlz.getLatitud()), new Double(cdlz.getLongitud()));
//                    polygon.getPaths().add(ll);
//                }
//                polygon.setStrokeColor("#FF0000");
//                polygon.setFillColor("#FF0000");
//                polygon.setStrokeOpacity(0.7);
//                polygon.setFillOpacity(0.5);
//                draggableModel.addOverlay(polygon);
//            }
        }
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        for (Coordenadas c : let.getUsuarios(et)) {
            Marker m = (Marker) event.getOverlay();
            if ((c.getLatitud() == m.getLatlng().getLat()) && (c.getLongitud() == m.getLatlng().getLng())) {
                this.coorTemp = c;
            }
        }
        marker = (Marker) event.getOverlay();

    }

    public List<SelectItem> getEquiposCreados() {
        return EquiposCreados;
    }

    public void setEquiposCreados(List<SelectItem> EquiposCreados) {
        this.EquiposCreados = EquiposCreados;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public MapModel getDraggableModel() {
        return draggableModel;
    }

    public void setDraggableModel(MapModel draggableModel) {
        this.draggableModel = draggableModel;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Coordenadas getCoorTemp() {
        return coorTemp;
    }

    public void setCoorTemp(Coordenadas coorTemp) {
        this.coorTemp = coorTemp;
    }

    
}
