package co.edu.ucc.coe.clases;

/**
 * Created by wilme on 13/10/2016.
 *
 * @author Wilmer Quintero Clase encargada de manejar el objeto del json
 * recibido a traves del Web Service Rest de nombre UsuaroResource
 */
public class CredencialesLoguin {

    /**
     * variable String utilizada para el nombre
     */
    private String nombre;
    /**
     * Variable String Utilizada para almacenar la contraseña
     */
    private String contrasena;

    /**
     * getContrasena
     * @return
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * setContrasena
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

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

}
