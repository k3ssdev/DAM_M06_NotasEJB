/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package beans;

import clasesPOJO.Alumnos;
import clasesPOJO.Historial;
import clasesPOJO.Modulos;
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
        EntityManager em = emf.createEntityManager();
        TypedQuery<Alumnos> q = em.createNamedQuery("Alumnos.findAll", Alumnos.class);
        List<Alumnos> result = q.getResultList();

        return result;
    }

    public List findAllProfesores() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Profesores> q = em.createNamedQuery("Profesores.findAll", Profesores.class);
        List<Profesores> result = q.getResultList();

        return result;
    }

    public Alumnos findAlumnoByName(String nombre) {
        Query q = emf.createEntityManager().createNamedQuery("Alumnos.findByNomUser");
        q.setParameter("nomUser", nombre);
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        Alumnos a = (Alumnos) iter.next();
        // Si no hay alumno con ese nombre, devuelve string no encontrado
        return a;

    }

    public Profesores findProfesorByName(String nombre) {
        Query q = emf.createEntityManager().createNamedQuery("Profesores.findByNomUser");
        q.setParameter("nomUser", nombre);
        List<Profesores> result = q.getResultList();
        Iterator iter = result.iterator();
        Profesores a = (Profesores) iter.next();
        // si no hay profesor con ese nombre, devuelve null
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

    public boolean loginAlumno(Alumnos a) {
        Query q = emf.createEntityManager().createNamedQuery("Alumnos.findByNomUser");
        q.setParameter("nomUser", a.getNomUser());
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        if (iter.hasNext()) {
            Alumnos alumno = (Alumnos) iter.next();
            if (alumno.getPassword().equals(a.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public boolean loginProfesor(Profesores p) {
        Query q = emf.createEntityManager().createNamedQuery("Profesores.findByNomUser");
        q.setParameter("nomUser", p.getNomUser());
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        if (iter.hasNext()) {
            Profesores profesor = (Profesores) iter.next();
            if (profesor.getPassword().equals(p.getPassword())) {
                return true;
            }
        }

        return false;
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
        // buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a.getNomUser());

        return encontrada != null;
    }

    public List<Notas> findNotasByAlumno(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        // buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a.getIdAlumno());
        // recogemos las notas del alumno
        List<Notas> notas = (List<Notas>) encontrada.getNotasCollection();

        return notas;
    }

    public List<Notas> findNotasByAlumnoId(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        Alumnos alumno = em.find(Alumnos.class, a.getIdAlumno());
        Query query = em.createQuery("SELECT n FROM Notas n WHERE n.idAlumno = :alumno");
        query.setParameter("alumno", alumno);
        List<Notas> notas = query.getResultList();

        return notas;
    }

    public boolean insertarAlumno(Alumnos a) {
        // Se crea el entity manager
        EntityManager em = emf.createEntityManager();
        // Se busca el ultimo id de la tabla alumnos
        TypedQuery<Alumnos> query = em.createQuery("SELECT a FROM Alumnos a ORDER BY a.idAlumno DESC", Alumnos.class);
        query.setMaxResults(1);
        List<Alumnos> result = query.getResultList();
        Iterator iter = result.iterator();
        Alumnos ultimo = (Alumnos) iter.next();
        // Se le asigna el id al alumno, sumando 1 al ultimo id
        int id = ultimo.getIdAlumno() + 1;
        a.setIdAlumno(id);
        // Se guarda el alumno si no existe
        if (!existeAlumno(a)) {
            em.persist(a);

            return true;
        }

        return false;
    }

    public Boolean modificarAlumno(Alumnos a) {
        EntityManager em = emf.createEntityManager();
        // buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a.getIdAlumno());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se modifica
        encontrada.setNomUser(a.getNomUser());
        encontrada.setPassword(a.getPassword());
        encontrada.setNombre(a.getNombre());

        em.merge(encontrada);

        return true;

    }

    public Boolean borrarAlumno(Alumnos a1) {
        EntityManager em = emf.createEntityManager();
        // buscamos el alumno por nombre
        Alumnos encontrada = em.find(Alumnos.class, a1.getIdAlumno());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se borra
        em.remove(encontrada);

        return true;
    }

    public List<Notas> findAllNotas() {
        EntityManager em = emf.createEntityManager();
        List<Notas> notas = em.createNamedQuery("Notas.findAll").getResultList();

        return notas;

    }

    public List<Modulos> findAllAsignaturas() {
        EntityManager em = emf.createEntityManager();
        List<Modulos> modulos = em.createNamedQuery("Modulos.findAll").getResultList();

        return modulos;
    }

    private boolean existeNota(Notas n) {
        EntityManager em = emf.createEntityManager();
        // buscamos el alumno por nombre
        Notas encontrada = em.find(Notas.class, n.getIdNotas());

        return encontrada != null;

    }

    public Boolean insertarNota(Notas n1) {

        EntityManager em = emf.createEntityManager();
        // Se busca el ultimo id de la tabla notas
        TypedQuery<Notas> query = em.createQuery("SELECT n FROM Notas n ORDER BY n.idNotas DESC", Notas.class);
        query.setMaxResults(1);
        List<Notas> result = query.getResultList();
        Iterator iter = result.iterator();
        Notas ultimo = (Notas) iter.next();
        // Se le asigna el id a la nota, sumando 1 al ultimo id
        int id = ultimo.getIdNotas() + 1;
        n1.setIdNotas(id);
        // Se guarda la nota si no existe
        if (!existeNota(n1)) {
            em.persist(n1);

            return true;
        }
        return false;
    }

    public Boolean modificarNota(Notas n1) {
        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Notas encontrada = em.find(Notas.class, n1.getIdNotas());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se modifica
        encontrada.setNotas(n1.getNotas());
        em.merge(encontrada);

        return true;
    }

    public Boolean borrarNota(Notas n1) {

        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Notas encontrada = em.find(Notas.class, n1.getIdNotas());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se borra
        em.remove(encontrada);

        return true;
    }

    public List<Modulos> findAllModulos() {
        EntityManager em = emf.createEntityManager();
        List<Modulos> modulos = em.createNamedQuery("Modulos.findAll").getResultList();

        return modulos;
    }

    public Boolean InsertarModulo(Modulos m) {
        EntityManager em = emf.createEntityManager();
        // Se guarda el modulo
        if (!existeModulo(m)) {
            em.persist(m);

            return true;
        }

        return false;
    }

    public boolean existeModulo(Modulos m) {
        Query q = emf.createEntityManager().createNamedQuery("Modulos.findByNombre");
        q.setParameter("nombre", m.getNombre());
        List<Alumnos> result = q.getResultList();
        Iterator iter = result.iterator();
        if (iter.hasNext()) {
            emf.createEntityManager().close();
            return true;

        } else {
            emf.createEntityManager().close();
            return false;
        }
    }

    public Boolean ModificarModulo(Modulos m1) {
        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Modulos encontrada = em.find(Modulos.class, m1.getId());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se modifica
        encontrada.setNombre(m1.getNombre());
        em.merge(encontrada);

        return true;
    }

    public Boolean borrarModulo(Modulos m1) {
        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Modulos encontrada = em.find(Modulos.class, m1.getId());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se borra
        em.remove(encontrada);

        return true;
    }

    public Boolean InsertarProfesor(Profesores p) {
        EntityManager em = emf.createEntityManager();
        // Se guarda el profesor
        if (!existeProfesor(p)) {
            em.persist(p);
            return true;
        }
        return false;
    }

    public Boolean borrarProfesor(Profesores p1) {
        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Profesores encontrada = em.find(Profesores.class, p1.getId());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se borra
        em.remove(encontrada);
        return true;
    }

    public Boolean ModificarProfesor(Profesores p1) {
        EntityManager em = emf.createEntityManager();
        // buscamos la nota por id
        Profesores encontrada = em.find(Profesores.class, p1.getId());
        // si no existe, devuelve false
        if (encontrada == null) {
            return false;
        }
        // si existe, se modifica
        encontrada.setNomUser(p1.getNomUser());
        encontrada.setPassword(p1.getPassword());
        encontrada.setNombre(p1.getNombre());
        em.merge(encontrada);
        return true;
    }

    public List<Historial> findAllHistorialEventos() {
        EntityManager em = emf.createEntityManager();
        List<Historial> historial = em.createNamedQuery("Historial.findAll").getResultList();

        return historial;
    }

    // Insertar un evento en el historial

    public Boolean InsertarEvento(Historial h) {
        EntityManager em = emf.createEntityManager();
        // Se guarda el evento
        em.persist(h);
        return true;

    }

    public List<Historial> findHistorialByTipo(Historial h1) {
        EntityManager em = emf.createEntityManager();
        String tipo = h1.getTipo();
        // buscamos el evento por tipo
        TypedQuery<Historial> query = em.createQuery("SELECT h FROM Historial h WHERE h.tipo = :tipo", Historial.class);
        query.setParameter("tipo", tipo);
        List<Historial> result = query.getResultList();

        // si no existe, devuelve false
        if (result == null) {
            return null;
        }
        // si existe, se devuelve
        return result;
    }

    public List<Notas> findAlumnosByModulo(Modulos m1) {

        EntityManager em = emf.createEntityManager();
        String modulo = m1.getNombre();
        // buscamos el evento por tipo
        TypedQuery<Notas> query = em.createQuery("SELECT n FROM Notas n WHERE n.modulo = :modulo", Notas.class);
        query.setParameter("modulo", modulo);
        List<Notas> result = query.getResultList();

        // si no existe, devuelve false
        if (result == null) {
            return null;
        }
        // si existe, se devuelve
        return result;
    }
}
