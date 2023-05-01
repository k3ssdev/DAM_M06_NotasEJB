package clasesPOJO;

import clasesPOJO.Notas;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-01T21:05:21")
@StaticMetamodel(Alumnos.class)
public class Alumnos_ { 

    public static volatile SingularAttribute<Alumnos, Integer> idAlumno;
    public static volatile SingularAttribute<Alumnos, String> password;
    public static volatile CollectionAttribute<Alumnos, Notas> notasCollection;
    public static volatile SingularAttribute<Alumnos, String> nomUser;
    public static volatile SingularAttribute<Alumnos, String> nombre;

}