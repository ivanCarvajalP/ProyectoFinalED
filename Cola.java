package Estructuras;

import Clases.Centro_Educativo;
import Clases.Curso;
import Clases.Estudiante;

public class Cola {

    private Nodo primero;
    private Nodo ultimo;
    private int tamanio;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Cola{" + "primero=" + primero + ", ultimo=" + ultimo + ", tamanio=" + tamanio + '}';
    }

    public boolean Vacia() {
        return primero == null && ultimo == null && tamanio == 0;
    }

    public void Encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);

        if (Vacia()) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        tamanio++;
    }

    public void Desacolar() {
        if (!Vacia()) {
            if (primero == ultimo) {
                primero = ultimo = null;
            } else {
                primero = primero.getSiguiente();
            }
            tamanio--;
        }
    }

    public String Mostrar_Cola() {
        String mostrar = "Vacia";
        if (!Vacia()) {
            mostrar = "";
            Nodo aux = primero;
            while (aux != null) {
                mostrar += aux.getDato() + "\n";
                aux = aux.getSiguiente();
            }
        }
        return mostrar;
    }

    public Curso SelecCursoItem(int ite) {
        Curso curso = null;
        if (!Vacia()) {
            Nodo aux = primero;
            for (int i = 0; i < ite; i++) {
                aux = aux.getSiguiente();
            }
            curso = (Curso) (aux.getDato());
        }
        return curso;
    }

    public double promedioCurso() {
        double promedio = 0.00;
        if (Vacia()) {
            return promedio;
        }
        if (!Vacia()) {
            Nodo aux = primero;
            while (aux != null) {
                promedio += ((Curso) (aux.getDato())).getPromedio();

                aux = aux.getSiguiente();
            }

        }

        return promedio / this.tamanio;
    }

    public void EliminarCurso(int ite) {
        if (!Vacia()) {
            if (ite == 0) { // Si se quiere eliminar el primer curso
                Desacolar();
            } else if (ite < tamanio) { // Si la posición está dentro de la cola
                Nodo aux = primero;
                // Avanzar hasta el curso anterior al que se quiere eliminar
                for (int i = 0; i < ite - 1; i++) {
                    aux = aux.getSiguiente();
                }
                // Si el curso a eliminar es el último
                if (aux.getSiguiente() == ultimo) {
                    ultimo = aux;
                }
                // Eliminar el curso de la cola
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                tamanio--;
            }
        }
    }

    public boolean VerificaNombreRepetido(String nombre) {

        if (!Vacia()) {
            Nodo aux = primero;

            while (aux != null) {

                if (((Curso) aux.getDato()).getNombre().equalsIgnoreCase(nombre)) {
                    return true;
                }
                aux = aux.getSiguiente();
            }

        }

        return false;
    }

}
