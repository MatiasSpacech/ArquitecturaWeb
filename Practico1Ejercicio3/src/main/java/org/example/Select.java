package org.example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.example.Dao.Persona;

public class Select {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp1ej5Persistencia");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Persona persona1 = em.find(Persona.class, 1); // Buscar por ID
        System.out.println(persona1);

        // Buscar todas las personas
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        for (Persona persona : personas) {
            System.out.println(persona);
        }

         // Buscar personas por nombre
         List<Persona> personasPorNombre = em.createQuery("SELECT p FROM Persona p WHERE p.nombre = :nombre", Persona.class)
         .setParameter("nombre", "Juan Perez")
         .getResultList();
         personasPorNombre.forEach(System.out::println);

         // Buscar personas por edad mayor a un valor
         List<Persona> personasPorEdad = em.createQuery("SELECT p FROM Persona p WHERE p.edad > :edad", Persona.class)
         .setParameter("edad", 25)
         .getResultList();
         personasPorEdad.forEach(System.out::println);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
