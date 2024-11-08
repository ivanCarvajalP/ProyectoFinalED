package Clases;

import java.text.DecimalFormat;

public class Estudiante {

    private int cod;
    private String nombre;
    private String genero;
    private int edad;
    private double promedio;

    DecimalFormat f = new DecimalFormat("0.##");

    public Estudiante(int cod, String nombre, String genero, int edad, double promedio) {
        this.cod = cod;
        this.nombre = nombre;
        this.genero = genero;
        this.edad = edad;
        this.promedio = promedio;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return "COD: " + cod + " // Nombre: " + nombre + " // Genero: " + genero + " // Edad: " + edad + " // Promedio: " + f.format(promedio);
    }

}
