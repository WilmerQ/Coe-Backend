/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author wilme
 * @see CamposComunesdeEntidad
 * @see Serializable
 */
@Entity
public class Usuario extends CamposComunesdeEntidad implements Serializable {

    /**
     * Variable String para almacenar el nombre del usuario con el cual se
     * registra
     */
    private String nombreUsuario;
    /**
     * Variable String para almacenar la contraseña, dicha contaseña se recibe
     * encriptada con md5
     */
    private String contrasena;
    /**
     * Variable String para almacenar el correo electrinico del usuario
     */
    private String email;
    /**
     * Variable String para almacenar el telefono ingresado por el usaurio para
     * su registro
     */
    private String telefono;
    /**
     * Variable Integer utilizada para comunicar un error al momento de utilizar
     * el Web service
     */
    @Transient
    private Integer informeDeError;
    /**
     * Objeto Roll el cual se designado para el usuario, dicho objeto se debe
     * asignar por el usuario Administrador luego de haberse registrado
     */
    @ManyToOne
    private Roll rollUsuario;

    @ManyToOne
    private EquipoTrabajo equipoTrabajo;

    /**
     * getNombreUsuario
     *
     * @return
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * setNombreUsuario
     *
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * getContrasena
     *
     * @return
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * setContrasena
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * getEmail
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * setEmail
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getTelefono
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * setTelefono
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * getInformeDeError
     *
     * @return
     */
    public Integer getInformeDeError() {
        return informeDeError;
    }

    /**
     * setInformeDeError
     *
     * @param informeDeError
     */
    public void setInformeDeError(Integer informeDeError) {
        this.informeDeError = informeDeError;
    }

    /**
     * getRollUsuario
     *
     * @return
     */
    public Roll getRollUsuario() {
        return rollUsuario;
    }

    /**
     * setRollUsuario
     *
     * @param rollUsuario
     */
    public void setRollUsuario(Roll rollUsuario) {
        this.rollUsuario = rollUsuario;
    }

    public EquipoTrabajo getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(EquipoTrabajo equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

}
