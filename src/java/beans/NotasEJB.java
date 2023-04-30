/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package beans;

import clasesPOJO.Alumnos;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class NotasEJB {

    @PersistenceUnit
    EntityManagerFactory em;

    public List findAllAlumnos() {
        return em.createEntityManager().createNamedQuery("Alumnos.findAll").getResultList();
    }

    public List findAllProfesores() {
        return em.createEntityManager().createNamedQuery("Profesores.findAll").getResultList();
    }

    // validar usuario, recibe usuario, password y rol. Devuelve true si existe usando las named queries
    //@NamedQuery(name = "Alumnos.findByNomUser", query = "SELECT a FROM Alumnos a WHERE a.nomUser = :nomUser"),
    //@NamedQuery(name = "Alumnos.findByPassword", query = "SELECT a FROM Alumnos a WHERE a.password = :password")
    public boolean validarUsuario(String usuario, String password, String rol) {
        if (rol.equals("alumno")) {
            em.createEntityManager();
            TypedQuery<Alumnos> q = ((EntityManager) em).createNamedQuery("Alumnos.findByNomUser", Alumnos.class);
            q.setParameter("nomUser", usuario);
            q.setParameter("password", password);
            List<Alumnos> l = q.getResultList();
            Iterator<Alumnos> it = l.iterator();
            while (it.hasNext()) {
                Alumnos a = it.next();
                if (a.getNomUser().equals(usuario) && a.getPassword().equals(password)) {
                    return true;
                }
            }
        } else if (rol.equals("profesor")) {
            em.createEntityManager();
            TypedQuery<Alumnos> q = ((EntityManager) em).createNamedQuery("Alumnos.findByUsuario", Alumnos.class);
            q.setParameter("usuario", usuario);
            q.setParameter("password", password);
            List<Alumnos> l = q.getResultList();
            Iterator<Alumnos> it = l.iterator();
            while (it.hasNext()) {
                Alumnos a = it.next();
                if (a.getNomUser().equals(usuario) && a.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    // devuelve el id del alumno

}

