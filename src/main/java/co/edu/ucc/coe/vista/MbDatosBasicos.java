/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.vista;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Windows 8.1
 */
@ViewScoped
@ManagedBean(name = "MbDatosBasicos")
public class MbDatosBasicos implements Serializable {

    private String ip;
    private Integer port;
    private String contextPath;
    private String nombreServlet;
    private String urlServlet;

    public MbDatosBasicos() {
    }

    @PostConstruct
    public void init() {
        ip = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
        contextPath = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
        port = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerPort();
        nombreServlet = "/imagenServlet";
      
        //System.out.println(urlServlet);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getNombreServlet() {
        return nombreServlet;
    }

    public void setNombreServlet(String nombreServlet) {
        this.nombreServlet = nombreServlet;
    }

    public String getUrlServlet() {
        return urlServlet;
    }

    public void setUrlServlet(String urlServlet) {
        this.urlServlet = urlServlet;
    }

}
