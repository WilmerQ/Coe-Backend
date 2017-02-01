/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.model.maquinaria;


import co.edu.ucc.coe.base.CamposComunesdeEntidad;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author wilme
 */
@Entity
public class ActividadMaquina extends CamposComunesdeEntidad implements Serializable {

    private String AccionxRealizar;
    private String tiempoDedicado;
    @ManyToOne
    private TipoMaquinaria maquinaria;
    private String proyectoAsociado;

    public String getAccionxRealizar() {
        return AccionxRealizar;
    }

    public void setAccionxRealizar(String AccionxRealizar) {
        this.AccionxRealizar = AccionxRealizar;
    }

    public String getTiempoDedicado() {
        return tiempoDedicado;
    }

    public void setTiempoDedicado(String tiempoDedicado) {
        this.tiempoDedicado = tiempoDedicado;
    }

    public TipoMaquinaria getMaquinaria() {
        return maquinaria;
    }

    public void setMaquinaria(TipoMaquinaria maquinaria) {
        this.maquinaria = maquinaria;
    }

    public String getProyectoAsociado() {
        return proyectoAsociado;
    }

    public void setProyectoAsociado(String proyectoAsociado) {
        this.proyectoAsociado = proyectoAsociado;
    }

}
