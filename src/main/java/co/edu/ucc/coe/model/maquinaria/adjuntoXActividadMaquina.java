/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author wilme
 */
@Entity
public class adjuntoXActividadMaquina extends CamposComunesdeEntidad implements Serializable {

    private String idManualActividadMaquina;
    private String idManualAdjunto;

    public String getIdManualActividadMaquina() {
        return idManualActividadMaquina;
    }

    public void setIdManualActividadMaquina(String idManualActividadMaquina) {
        this.idManualActividadMaquina = idManualActividadMaquina;
    }

    public String getIdManualAdjunto() {
        return idManualAdjunto;
    }

    public void setIdManualAdjunto(String idManualAdjunto) {
        this.idManualAdjunto = idManualAdjunto;
    }

    

}
