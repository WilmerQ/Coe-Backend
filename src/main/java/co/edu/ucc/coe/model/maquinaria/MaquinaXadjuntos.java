/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;

import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import co.edu.ucc.coe.model.adjunto;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author wilme
 */
@Entity
public class MaquinaXadjuntos extends CamposComunesdeEntidad implements Serializable {

    @ManyToOne
    private adjunto adjunto;
    @ManyToOne
    private Maquina maquina;

    public adjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(adjunto adjunto) {
        this.adjunto = adjunto;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

}
