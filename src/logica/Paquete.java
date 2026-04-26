package logica;

public class Paquete<T> {

    // Atributos del paquete
    private String id;
    private double peso;
    private String destino;
    private T contenido;

    // Constructor
    public Paquete(String id, double peso, String destino, T contenido) {
        this.id = id;
        this.peso = peso;
        this.destino = destino;
        this.contenido = contenido;
    }

    // Getters (para acceder a los datos)
    public String getId() {
        return id;
    }

    public double getPeso() {
        return peso;
    }

    public String getDestino() {
        return destino;
    }

    public T getContenido() {
        return contenido;
    }

    // Para mostrar el paquete por consola
    @Override
    public String toString() {
        return "Paquete{" +
                "id='" + id + '\'' +
                ", peso=" + peso +
                ", destino='" + destino + '\'' +
                ", contenido=" + contenido +
                '}';
    }
}