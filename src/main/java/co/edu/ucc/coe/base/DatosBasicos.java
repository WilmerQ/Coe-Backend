/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.base;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Windows 8.1 Clase encargada de obtener los datos de conexion en
 * momentos de ejecucion del proyecto
 */
public class DatosBasicos {

    /**
     * variables utilizado para obtener la ip
     */
    public static String ip = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getServerName();
    /**
     * variable utilizada para obtener el link actual del proyecto
     */
    public static String path = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
    /**
     *Variable encargada de obtener el puerto del servidor donde se ejecuta el proyecto
     */
    public static Integer port = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getLocalPort();
}
