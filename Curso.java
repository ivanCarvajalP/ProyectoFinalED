package Clases;

import Estructuras.Pila;
import java.text.DecimalFormat;

public class Curso {

    private int id;
    private String nombre;
    private Pila estudiantes;
    private double promedio;

    DecimalFormat f = new DecimalFormat("0.##");

    public Curso(int id, String nombre, Pila estudiantes, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = estudiantes;
        this.promedio = promedio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pila getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Pila estudiantes) {
        this.estudiantes = estudiantes;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {

        return "ID: " + id + " // Nombre: " + nombre + " // Promedio: " + f.format(promedio);
    }

}
