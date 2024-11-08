package Clases;

import Estructuras.Cola;
import java.text.DecimalFormat;

public class Centro_Educativo {

    private int id;
    private String nombre;
    private Cola cursos;
    private double promedio;
    DecimalFormat f = new DecimalFormat("0.##");

    public Centro_Educativo(int id, String nombre, Cola cursos, double promedio) {
        this.id = id;
        this.nombre = nombre;
        this.cursos = cursos;
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

    public Cola getCursos() {
        return cursos;
    }

    public void setCursos(Cola cursos) {
        this.cursos = cursos;
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
