package org.example.Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Persona {
    @Id
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "anios")
    private int edad;

    @ManyToOne
    private Direccion domicilio;

    public Persona() {
    }
    public Persona(int id, String nombre, int edad, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.domicilio = direccion;
    }
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Direccion getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(Direccion direccion) {
        this.domicilio = direccion;
    }
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", direccion=" + domicilio +
                '}';
    }

}
