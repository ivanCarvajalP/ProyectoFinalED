package Estructuras;

import Clases.Centro_Educativo;
import Clases.Estudiante;

public class Pila {

    private Nodo cima;
    private int tamanio;

    public Pila() {
        this.cima = null;
        this.tamanio = 0;
    }

    public Nodo getCima() {
        return cima;
    }

    public void setCima(Nodo cima) {
        this.cima = cima;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Pila{" + "cima=" + cima + ", tamanio=" + tamanio + '}';
    }

    public boolean Vacia() {
        return cima == null && tamanio == 0;
    }

    public void Apilar(Object dato) {
        Nodo nuevo = new Nodo(dato, cima);
        cima = nuevo;
        tamanio++;
    }

    public void Desapilar() {
        if (!Vacia()) {
            cima = cima.getSiguiente();
            tamanio--;
        }
    }

    public String Mostrar_Pila() {
        String mostrar = "Vacia";
        if (!Vacia()) {
            mostrar = "";
            Nodo aux = cima;
            while (aux != null) {
                mostrar += aux.getDato() + "\n";
                aux = aux.getSiguiente();
            }
        }
        return mostrar;
    }

    Pila Invertir() {
        Pila aux = new Pila();
        while (!Vacia()) {
            aux.Apilar(cima.getDato());
            Desapilar();
        }
        return aux;
    }

    void Eliminar(Object nombre) {
        Pila aux = Invertir();
        while (!aux.Vacia()) {
            if (!aux.getCima().getDato().equals(nombre)) {
                Apilar(aux.getCima().getDato());
            }
            aux.Desapilar();
        }
    }

    public double promedioEstu() {
        double nota = 0.00;
        if (Vacia()) {
            return nota;
        }
        if (!Vacia()) {
            Nodo aux = cima;
            while (aux != null) {
                nota += ((Estudiante) (aux.getDato())).getPromedio();

                aux = aux.getSiguiente();
            }

        }

        return nota / this.tamanio;
    }

    public String[] BuscarMejorPromedio() {
        String elMejorEstudiante[] = new String[2];
        String estudiante = null;
        double mejorNota = 0;

        if (Vacia()) {
            elMejorEstudiante[0] = "SIN ESTUDIANTE";
            elMejorEstudiante[1] = "0";
        }

        if (!Vacia()) {
            Nodo aux = cima;

            while (aux != null) {
                if (((Estudiante) (aux.getDato())).getPromedio() > mejorNota) {
                    mejorNota = ((Estudiante) (aux.getDato())).getPromedio();
                    estudiante = ((Estudiante) (aux.getDato())).getNombre();
                }

                aux = aux.getSiguiente();
            }

            elMejorEstudiante[0] = estudiante;
            elMejorEstudiante[1] = "" + mejorNota;

        }

        return elMejorEstudiante;

    }

    public Estudiante SelecEstudiantexItem(int ite) {
        Estudiante estudiante = null;
        if (!Vacia()) {
            Nodo aux = cima;

            for (int i = 0; i < ite; i++) {
                aux = aux.getSiguiente();
            }
            estudiante = (Estudiante) (aux.getDato());
        }
        return estudiante;
    }

    public void EliminarEstudiantexItem(int ite) {

        if (!Vacia()) {
            Nodo aux = cima;

            for (int i = 0; i < ite; i++) {
                aux = aux.getSiguiente();
            }
            Eliminar(aux.getDato());
        }

    }

    public boolean VerificaNombreRepetido(String nombre) {

        if (!Vacia()) {
            Nodo aux = cima;

            while (aux != null) {

                if (((Estudiante) aux.getDato()).getNombre().equalsIgnoreCase(nombre)) {
                    return true;
                }
                aux = aux.getSiguiente();
            }

        }

        return false;
    }

}
