package logica;



import java.util.LinkedList;
import java.util.Queue;

public class CentroDistribucion {
    // Usamos dos colas para separar la prioridad
    private Queue<Paquete<String>> colaPrioridad = new LinkedList<>();
    private Queue<Paquete<String>> colaEstandar = new LinkedList<>();

    public void recibirPaquete(Paquete<String> p) {
        // Regla: Urgente o peso > 50kg va a prioridad
        if (p.isUrgente() || p.getPeso() > 50.0) {
            colaPrioridad.add(p);
        } else {
            colaEstandar.add(p);
        }
    }

    public Paquete<String> despacharSiguiente() {
        if (!colaPrioridad.isEmpty()) {
            return colaPrioridad.poll(); // O(1)
        }
        return colaEstandar.poll(); // O(1)
    }

    public boolean estaVacio() {
        return colaPrioridad.isEmpty() && colaEstandar.isEmpty();
    }
}
