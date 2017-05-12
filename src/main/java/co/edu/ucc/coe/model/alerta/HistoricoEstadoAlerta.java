/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.alerta;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author wilme
 */
@Entity
public class HistoricoEstadoAlerta extends CamposComunesdeEntidad implements Serializable {

    private String nivelAlerta;
    private String Sensor;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaServidor;

    public String getNivelAlerta() {
        return nivelAlerta;
    }

    public void setNivelAlerta(String nivelAlerta) {
        this.nivelAlerta = nivelAlerta;
    }

    public String getSensor() {
        return Sensor;
    }

    public void setSensor(String Sensor) {
        this.Sensor = Sensor;
    }

    public Date getHoraServidor() {
        return horaServidor;
    }

    public void setHoraServidor(Date horaServidor) {
        this.horaServidor = horaServidor;
    }
}
