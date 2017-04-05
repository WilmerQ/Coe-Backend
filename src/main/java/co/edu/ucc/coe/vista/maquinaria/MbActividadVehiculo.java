/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista.maquinaria;

import co.edu.ucc.coe.base.SessionOperations;
import co.edu.ucc.coe.model.Usuario;
import co.edu.ucc.coe.model.adjunto;
import co.edu.ucc.coe.model.maquinaria.ActividadMaquina;
import co.edu.ucc.coe.model.maquinaria.CordenadaDeLaZona;
import co.edu.ucc.coe.model.maquinaria.Maquina;
import co.edu.ucc.coe.model.maquinaria.adjuntoXActividadMaquina;
import co.edu.ucc.coe.service.CommonsBean;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbActividadVehiculo")
public class MbActividadVehiculo implements Serializable {

    @EJB
    private CommonsBean cb;

    private ActividadMaquina actividadMaquina;
    private List<SelectItem> Vehiculos;
    private Long idVehiculo;
    private Maquina vehiculo;
    private Boolean estadoVehiculo;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private List<String> nombresArchivos;
    private UUID idUUIDVehiculo;
    private List<String> uiddAajuntos;
    Boolean popupGrafica;
    private List<ActividadMaquina> actividadMaquinas;
    private String usuarioCreacion;
    private List<adjuntoXActividadMaquina> adjuntoxActividadMaquinas;
    private List<StreamedContent> filelist;
    private List<adjunto> adjuntoArchivos;
//---------------------------------
    private MapModel advancedModel;
    private List<CordenadaDeLaZona> listaCordenadaDeLaZona;
    private Boolean bloqueo;
    private Marker marker;
    private Boolean popupGrafica2;
    private Boolean popupGrafica3;

    /**
     * Creates a new instance of MbActividadVehiculo
     */
    public MbActividadVehiculo() {
    }

    @PostConstruct
    public void init() {
        actividadMaquina = new ActividadMaquina();
        Vehiculos = new LinkedList<>();
        for (Maquina m : (List<Maquina>) cb.getAll(Maquina.class)) {
            if (!m.getEstadoActual().equals("Fuera de servicio")) {
                Vehiculos.add(new SelectItem(m.getId(), m.getNumeroPlaca()));
            }
        }
        idVehiculo = null;
        estadoVehiculo = null;
        nombresArchivos = new ArrayList<>();
        uiddAajuntos = new ArrayList<>();
        idUUIDVehiculo = UUID.randomUUID();
        popupGrafica = Boolean.FALSE;
        actividadMaquinas = new ArrayList<>();
        actividadMaquinas = (List<ActividadMaquina>) cb.getAll(ActividadMaquina.class);

        Usuario u = (Usuario) SessionOperations.getSessionValue("USUARIO");
        usuarioCreacion = u.getNombreUsuario();

        advancedModel = new DefaultMapModel();
        listaCordenadaDeLaZona = new ArrayList<>();
        bloqueo = Boolean.FALSE;

        popupGrafica2 = Boolean.FALSE;
        popupGrafica3 = Boolean.FALSE;

        adjuntoArchivos = new ArrayList<>();
        filelist = new ArrayList<>();
    }

    public void limpiar() {
        advancedModel = new DefaultMapModel();
        listaCordenadaDeLaZona = new ArrayList<>();
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }

    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
        bloqueo = Boolean.FALSE;
        if (listaCordenadaDeLaZona.isEmpty()) {
            CordenadaDeLaZona cdlz = new CordenadaDeLaZona();
            cdlz.setMarker(marker);
            cdlz.setLatitud(marker.getLatlng().getLat() + "");
            cdlz.setLongitud(marker.getLatlng().getLng() + "");
            cdlz.setOrden(1);
            listaCordenadaDeLaZona.add(cdlz);
        } else {
            Boolean entro = Boolean.FALSE;
            for (CordenadaDeLaZona cordenadaDeLaZona : listaCordenadaDeLaZona) {
                if (cordenadaDeLaZona.getMarker().getId().equals(marker.getId())) {
                    entro = Boolean.TRUE;
                    cordenadaDeLaZona.setMarker(marker);
                    cordenadaDeLaZona.setLatitud(marker.getLatlng().getLat() + "");
                    cordenadaDeLaZona.setLongitud(marker.getLatlng().getLng() + "");
                    break;
                }
            }
            if (!entro) {
                CordenadaDeLaZona cdlz = new CordenadaDeLaZona();
                cdlz.setMarker(marker);
                cdlz.setLatitud(marker.getLatlng().getLat() + "");
                cdlz.setLongitud(marker.getLatlng().getLng() + "");
                cdlz.setOrden(listaCordenadaDeLaZona.size() + 1);
                listaCordenadaDeLaZona.add(cdlz);
            }
        }
    }

    public void dibujar() {
        if (listaCordenadaDeLaZona.size() > 1) {
            advancedModel = new DefaultMapModel();
            Polygon polygon = new Polygon();
            //Polygon
            for (CordenadaDeLaZona cdlz : listaCordenadaDeLaZona) {
                System.out.println(cdlz.getOrden());
                polygon.getPaths().add(cdlz.getMarker().getLatlng());
            }
            polygon.setStrokeColor("#FF0000");
            polygon.setFillColor("#FF0000");
            polygon.setStrokeOpacity(0.7);
            polygon.setFillOpacity(0.5);
            advancedModel.addOverlay(polygon);
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Tiene que agregar mas de un punto");
        }
    }

    public void agregarMarker() {
        bloqueo = Boolean.TRUE;
        if (!listaCordenadaDeLaZona.isEmpty()) {
            advancedModel = new DefaultMapModel();
            for (CordenadaDeLaZona cdlz : listaCordenadaDeLaZona) {
                advancedModel.addOverlay(cdlz.getMarker());
            }
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Informacion", "Mueva el punto en el mapa");
        }
        LatLng coord1 = new LatLng(11.247141, -74.205504);
        int numero = listaCordenadaDeLaZona.size() + 1;
        advancedModel.addOverlay(new Marker(coord1, numero + ""));
        for (Marker premarker : getAdvancedModel().getMarkers()) {
            premarker.setDraggable(true);
        }
    }

    public void mostrarMensaje(FacesMessage.Severity icono, String titulo, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(icono, titulo, mensaje));
    }

    public void cargaVehiculo() {
        if (idVehiculo != null) {
            this.vehiculo = (Maquina) cb.getById(Maquina.class, idVehiculo);
            if (vehiculo != null) {
                if (vehiculo.getEstadoActual().equals("Reparacion")) {
                    estadoVehiculo = Boolean.FALSE;
                } else {
                    estadoVehiculo = Boolean.TRUE;
                }
            }
        }
    }

    public void MostrarAdjuntosPopup(ActividadMaquina temp) {
        adjuntoxActividadMaquinas = new ArrayList<>();
        List<adjunto> list = new ArrayList<>();
        filelist = new ArrayList<>();
        adjuntoxActividadMaquinas = (List<adjuntoXActividadMaquina>) cb.getByOneField(adjuntoXActividadMaquina.class, "idManualActividadMaquina", temp.getIdmanual());
        System.out.println("list size: " + adjuntoxActividadMaquinas.size());
        popupGrafica3 = Boolean.TRUE;
        for (adjuntoXActividadMaquina xam : adjuntoxActividadMaquinas) {
            List<adjunto> ad = (List<adjunto>) cb.getByOneField(adjunto.class, "idmanual", xam.getIdManualAdjunto());
            for(adjunto aux : ad){
                list.add(aux);
            }
            System.out.println(" --- ");
        }
        for (adjunto adj : list) {
            StreamedContent file = new ByteArrayContent(adj.getContenido(), ".*", adj.getNombreArchivo());
            filelist.add(file);
        }
    }
    
    /*private void mostrarVentanaReporte() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        String js = "mostrarReporte('" + reportUrl + "');";
        //System.out.println(js);
        requestContext.execute(js);
    }*/

    public Boolean BuscarAdjuntos(ActividadMaquina temp) {
        try {
            adjuntoxActividadMaquinas = new ArrayList<>();
            adjuntoxActividadMaquinas = (List<adjuntoXActividadMaquina>) cb.getByOneField(adjuntoXActividadMaquina.class, "idManualActividadMaquina", temp.getIdmanual());
            if (adjuntoxActividadMaquinas.size() > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public String DeterminarEstado(ActividadMaquina temp) {
        Date hoy = new Date();
        if (temp.getFechaDateFinal().before(hoy)) {
            return "Fecha Finalizada";
        } else if (temp.getFechaInicio().after(hoy)) {
            return "Proximo a inicio";
        } else if ((temp.getFechaInicio().before(hoy)) && (temp.getFechaDateFinal().after(hoy))) {
            return "En Proceso";
        }
        return "";
    }

    public String accionguardar() throws Exception {
        System.out.println("entro");
        if (!listaCordenadaDeLaZona.isEmpty()) {
            for (CordenadaDeLaZona temp : listaCordenadaDeLaZona) {
                temp.setIdManualActividadVehiculo(idUUIDVehiculo.toString().toUpperCase());
                cb.guardar(temp);
            }
        }
        actividadMaquina.setMaquina((Maquina) cb.getById(Maquina.class, idVehiculo));
        actividadMaquina.setIdmanual(idUUIDVehiculo.toString().toUpperCase());
        actividadMaquina.setFechaInicio(fechaInicio);
        actividadMaquina.setFechaDateFinal(fechaFinalizacion);
        if (true) {
            System.out.println("Paso Verificacion");
            if (cb.guardar(actividadMaquina)) {
                for (String s : uiddAajuntos) {
                    adjuntoXActividadMaquina aux = new adjuntoXActividadMaquina();
                    aux.setIdManualActividadMaquina(idUUIDVehiculo.toString().toUpperCase());
                    aux.setIdManualAdjunto(s);
                    cb.guardar(aux);
                }
                //mostrarMensaje(FacesMessage.SEVERITY_INFO, "Exitoso", "Se ha guardado");
                init();
                //String contextPath = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
                //redirect(contextPath + "/usuario/index.xhtml");
            } else {
                //mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "No se ha guardado");
            }
        }
        return null;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        System.out.println("aqui estoy");
        InputStream fi;
        InputStream fi2;
        UUID id = UUID.randomUUID();
        try {
            fi = event.getFile().getInputstream();
            fi2 = event.getFile().getInputstream();
            byte[] buffer = new byte[(int) event.getFile().getSize()];
            System.out.println("-------" + event.getFile().getFileName());
            fi.read(buffer);
            adjunto temp = new adjunto();
            temp.setContenido(buffer);
            temp.setIdmanual(id.toString().toUpperCase());
            temp.setNombreArchivo(event.getFile().getFileName());
            cb.guardar(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            adjunto aux = (adjunto) cb.getByOneFieldWithOneResult(adjunto.class, "idmanual", id.toString().toUpperCase());
            System.out.println("aux " + aux.getId());
            nombresArchivos.add(aux.getNombreArchivo());
            uiddAajuntos.add(aux.getIdmanual());
        } catch (Exception ex) {
            Logger.getLogger(MbActividadVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String acciongrafica() {
        popupGrafica = Boolean.TRUE;
        return null;
    }

    public String acciongrafica2() {
        popupGrafica2 = Boolean.TRUE;
        return null;
    }

    public String acciongrafica3() {
        popupGrafica3 = Boolean.TRUE;
        return null;
    }

    public void handleDateSelect(SelectEvent event) {
        fechaInicio = (Date) event.getObject();
    }

    public ActividadMaquina getActividadMaquina() {
        return actividadMaquina;
    }

    public void setActividadMaquina(ActividadMaquina actividadMaquina) {
        this.actividadMaquina = actividadMaquina;
    }

    public List<SelectItem> getVehiculos() {
        return Vehiculos;
    }

    public void setVehiculos(List<SelectItem> Vehiculos) {
        this.Vehiculos = Vehiculos;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Maquina getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Maquina vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Boolean getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(Boolean estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public List<String> getNombresArchivos() {
        return nombresArchivos;
    }

    public void setNombresArchivos(List<String> nombresArchivos) {
        this.nombresArchivos = nombresArchivos;
    }

    public Boolean getPopupGrafica() {
        return popupGrafica;
    }

    public void setPopupGrafica(Boolean popupGrafica) {
        this.popupGrafica = popupGrafica;
    }

    public List<ActividadMaquina> getActividadMaquinas() {
        return actividadMaquinas;
    }

    public void setActividadMaquinas(List<ActividadMaquina> actividadMaquinas) {
        this.actividadMaquinas = actividadMaquinas;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public MapModel getAdvancedModel() {
        return advancedModel;
    }

    public void setAdvancedModel(MapModel advancedModel) {
        this.advancedModel = advancedModel;
    }

    public List<CordenadaDeLaZona> getListaCordenadaDeLaZona() {
        return listaCordenadaDeLaZona;
    }

    public void setListaCordenadaDeLaZona(List<CordenadaDeLaZona> listaCordenadaDeLaZona) {
        this.listaCordenadaDeLaZona = listaCordenadaDeLaZona;
    }

    public Boolean getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public Boolean getPopupGrafica2() {
        return popupGrafica2;
    }

    public void setPopupGrafica2(Boolean popupGrafica2) {
        this.popupGrafica2 = popupGrafica2;
    }

    public Boolean getPopupGrafica3() {
        return popupGrafica3;
    }

    public void setPopupGrafica3(Boolean popupGrafica3) {
        this.popupGrafica3 = popupGrafica3;
    }

    public List<StreamedContent> getFilelist() {
        return filelist;
    }

    public void setFilelist(List<StreamedContent> filelist) {
        this.filelist = filelist;
    }

}
