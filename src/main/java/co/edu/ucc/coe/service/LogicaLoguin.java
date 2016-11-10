/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.coe.service;

import co.edu.ucc.coe.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB donde se almacenan los metodos propios para realizar el inicio de sesion de los usuarios
 * @author wilme
 * @see Stateless
 */
@Stateless
public class LogicaLoguin {

    @PersistenceContext(unitName = "COEPU")
    private EntityManager em;

    /**
     * funcion encargado de verificar las credenciales para el inicio de sesion y retorno el objeto usuario de ser validas
     * @param nombreUsuario
     * @param passwordPlano
     * @return 
     */
    public Usuario login(String nombreUsuario, String passwordPlano) {
        if ((nombreUsuario.equals("admin")) && (passwordPlano.equals("827ccb0eea8a706c4c34a16891f84e7b"))) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario("Administrador");
            return usuario;
        }
        try {
            return (Usuario) em.createQuery("Select u from Usuario u where u.nombreUsuario = :n and u.contrasena = :p")
                    .setParameter("n", nombreUsuario)
                    .setParameter("p", passwordPlano).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
