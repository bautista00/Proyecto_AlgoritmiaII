package logica;

import java.util.Stack;

public class Camion {

    // Pila que representa los paquetes cargados en el camión.
    // LIFO: el último paquete cargado es el primero en descargarse.
    private Stack<Paquete<String>> pilaDePaquetes = new Stack<>();

    // Carga un paquete en la parte superior de la pila
    public void cargarPaqueteAlCamion(Paquete<String> paquete) {
        pilaDePaquetes.push(paquete);
    }

    // Descarga el último paquete cargado
    public Paquete<String> descargarPaqueteDelCamion() {
        if (pilaDePaquetes.isEmpty()) {
            return null;
        }
        return pilaDePaquetes.pop();
    }

    // Elimina la última carga realizada en caso de error
    public Paquete<String> deshacerUltimaCargaDelCamion() {
        if (pilaDePaquetes.isEmpty()) {
            return null;
        }
        return pilaDePaquetes.pop();
    }

    // Muestra los paquetes dentro del camión
    public void mostrarPaquetesDelCamion() {
        if (pilaDePaquetes.isEmpty()) {
            System.out.println("El camión está vacío.");
        } else {
            for (Paquete<String> paquete : pilaDePaquetes) {
                System.out.println(paquete);
            }
        }
    }
}