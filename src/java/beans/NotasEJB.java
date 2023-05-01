/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package beans;

import clasesPOJO.Alumnos;
import clasesPOJO.Notas;
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

        public Alumnos findAlumnoByName (String nombre) {
        Query q = emf.createEntityManager().createNamedQuery("Alumnos.findByNomUser");
        q.setParameter("nomUser", nombre);
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        Alumnos a = (Alumnos) iter.next();
        // Si no hay alumno con ese nombre, devuelve  string no encontrado
        return a;

    }

    public Profesores findProfesorByName (String nombre) {
        Query q = emf.createEntityManager().createNamedQuery("Profesores.findByNomUser");
        q.setParameter("nomUser", nombre);
        List<Profesores> result = q.getResultList();
        Iterator iter = result.iterator();
        Profesores a = (Profesores) iter.next();
        //si no hay profesor con ese nombre, devuelve null
        return a;
    }
        
    public boolean existeAlumno(Alumnos a) {
        Query q = emf.createEntityManager().createNamedQuery("Alumnos.findByNomUser");
        q.setParameter("nomUser", a.getNomUser());
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        if (iter.hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existeProfesor(Profesores a) {
        Query q = emf.createEntityManager().createNamedQuery("Profesores.findByNomUser");
        q.setParameter("nomUser", a.getNomUser());
        List<Profesores> result = q.getResultList();
        Iterator iter = result.iterator();
        if (iter.hasNext()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean existeMatricula(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        //buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a.getNomUser());
        em.close();
        return encontrada != null;
    }

    public List<Notas> findNotasByAlumno(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        //buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a.getIdAlumno());
        //recogemos las notas del alumno
        List<Notas> notas = (List<Notas>) encontrada.getNotasCollection();
        em.close();
        return notas;
    }

    public void insertarAlumno(Alumnos a) {
    }
    
  
}
