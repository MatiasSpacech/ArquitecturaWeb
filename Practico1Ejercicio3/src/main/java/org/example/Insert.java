package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.example.Dao.Direccion;
import org.example.Dao.Persona;

public class Insert {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp1ej5Persistencia");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Direccion direccion = new Direccion("Tandil", "Calle Falsa 123");
        em.persist(direccion);
        Persona persona1 = new Persona(3, "Juan Perez", 30, direccion);
        em.persist(persona1);
        Persona persona2 = new Persona(4, "Maria Gomez", 25, direccion);
        em.persist(persona2);
        em.getTransaction().commit();
        em.close();        
        emf.close();
    }

}
