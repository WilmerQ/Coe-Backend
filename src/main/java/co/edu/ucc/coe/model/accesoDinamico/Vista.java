/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.accesoDinamico;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Entidad Vista utilizada para crear vistas que seran asignadas a los usuarios
 * @author wilme
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class Vista extends CamposComunesdeEntidad implements Serializable {

    /**
     * Variable tipo String que almacena el nombre de la vista
     */
    private String nombre;
    /**
     * Variable tipo String que almacena la ruta de esta vista
     */
    private String ruta;
    /**
     * Variable String que almacena el icono asignado a la vista
     */
    private String icono;

    /**
     * getNombre
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getRuta
     * @return
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * setRuta
     * @param ruta
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * getIcono
     * @return
     */
    public String getIcono() {
        return icono;
    }

    /**
     * setIcono
     * @param icono
     */
    public void setIcono(String icono) {
        this.icono = icono;
    }
    
}
