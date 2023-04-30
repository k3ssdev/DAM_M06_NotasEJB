/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package beans;

import clasesPOJO.Alumnos;
import clasesPOJO.Profesores;
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
    EntityManagerFactory emf;

    public List findAllAlumnos() {
        return emf.createEntityManager().createNamedQuery("Alumnos.findAll").getResultList();
    }

    public List findAllProfesores() {
        return emf.createEntityManager().createNamedQuery("Profesores.findAll").getResultList();
    }

    public boolean existeAlumno(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        Alumnos encontrado = em.find(Alumnos.class, a.getNomUser());
        em.close();
        return encontrado != null;
    }
}

