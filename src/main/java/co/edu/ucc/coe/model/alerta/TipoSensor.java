/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.alerta;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author wilme
 */
@Entity
public class TipoSensor extends CamposComunesdeEntidad implements Serializable {

    private Long idNativo;
    private String nombre;
    private String unidadDeMedida;

    public Long getIdNativo() {
        return idNativo;
    }

    public void setIdNativo(Long idNativo) {
        this.idNativo = idNativo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

}
