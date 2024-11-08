package Estructuras;

import Clases.Centro_Educativo;

public class Lista_Doble {

    private Nodo primero;
    private Nodo ultimo;
    private int tamanio;

    public Lista_Doble() {
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

    public boolean Vacia() {
        return primero == null && ultimo == null;
    }

    public void Insertar_Primero(Object dato) {
        if (Vacia()) {
            Nodo nuevo = new Nodo(dato);
            primero = ultimo = nuevo;
        } else {
            Nodo nuevo = new Nodo(dato, primero, null);
            primero.setAnterior(nuevo);
            primero = nuevo;
        }
        tamanio++;
    }

    public void Insertar_Ultimo(Object dato) {
        if (Vacia()) {
            Nodo nuevo = new Nodo(dato);
            primero = ultimo = nuevo;
        } else {
            Nodo nuevo = new Nodo(dato, null, ultimo);
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        tamanio++;
    }

    public void Eliminar_Primero() {
        if (!Vacia()) {
            if (primero == ultimo) {
                primero = ultimo = null;
            } else {
                primero = primero.getSiguiente();
                primero.setAnterior(null);
            }
            tamanio--;
        }
    }

    public void Eliminar_Ultimo() {
        if (!Vacia()) {
            if (primero == ultimo) {
                primero = ultimo = null;
            } else {
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(null);
            }
            tamanio--;
        }
    }

    public String MostrarPU() {
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

//    public String MostrarUP() {
//        String mostrar = "";
//        if (!Vacia()) {
//            Nodo aux = ultimo;
//            while (aux != null) {
//                mostrar += aux.getDato() + "\n";
//                aux = aux.getAnterior();
//            }
//        }
//        return mostrar;
//    }
    public Centro_Educativo SelecCentroEduItem(int ite) {
        Centro_Educativo SelecCentroEduItem = null;
        if (!Vacia()) {
            Nodo aux = primero;
            for (int i = 0; i < ite; i++) {
                aux = aux.getSiguiente();
            }
            SelecCentroEduItem = (Centro_Educativo) (aux.getDato());
        }
        return SelecCentroEduItem;
    }

    public void EliminarCD(int ite) {
        if (!Vacia()) {
            if (ite == 0) { // Si se quiere eliminar el primer nodo
                Eliminar_Primero();
            } else if (ite == tamanio - 1) { // Si se quiere eliminar el último nodo
                Eliminar_Ultimo();
            } else if (ite < tamanio) { // Si la posición está dentro de la lista
                Nodo aux = primero;
                // Avanzar hasta el nodo en la posición ite
                for (int i = 0; i < ite; i++) {
                    aux = aux.getSiguiente();
                }
                // Ajustar los enlaces para eliminar el nodo
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                tamanio--;
            }
        }
    }

    public boolean VerificaNombreRepetido(String nombre) {

        if (!Vacia()) {
            Nodo aux = primero;

            while (aux != null) {

                if (((Centro_Educativo) aux.getDato()).getNombre().equalsIgnoreCase(nombre)) {
                    return true;
                }
                aux = aux.getSiguiente();
            }

        }

        return false;
    }

}
