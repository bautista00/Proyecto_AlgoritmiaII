package logica;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MenuPrincipal {

    private Scanner scanner = new Scanner(System.in);
    private CentroDistribucion centro = new CentroDistribucion();
    private Camion camion = new Camion();

    private Set<String> idsUsados = new HashSet<>();

    public void iniciarMenu() {
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("\n--- LOGI-UADE 2026: GESTIÓN LOGÍSTICA ---");
            System.out.println("1. Cargar inventario desde JSON");
            System.out.println("2. Cargar paquete manualmente");
            System.out.println("3. Enviar paquete del Centro al Camión");
            System.out.println("4. Ver estado del Camión");
            System.out.println("5. Deshacer última carga del Camión");
            System.out.println("6. Descargar Camión");
            System.out.println("7. Salir");
            System.out.print("Seleccione: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: cargarDesdeJson(); break;
                case 2: cargarManual(); break;
                case 3: despacharHaciaCamion(); break;
                case 4: camion.mostrarPaquetesDelCamion(); break;
                case 5: deshacerCarga(); break;
                case 6: descargar(); break;
                case 7: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    private void cargarDesdeJson() {
        try (FileReader reader = new FileReader("src/logica/inventario.json")) {
            Gson gson = new Gson();
            Paquete[] lista = gson.fromJson(reader, Paquete[].class);
            if (lista != null) {
                for (Paquete p : lista) {
                    String idString = String.valueOf(p.getId());

                    if (!idsUsados.contains(idString)) {
                        idsUsados.add(idString);
                        Paquete<String> nuevo = new Paquete<>(idString, p.getPeso(), p.getDestino(), p.isUrgente(), (String)p.getContenido());
                        centro.recibirPaquete(nuevo);
                    }
                }
                System.out.println("Inventario cargado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar JSON: " + e.getMessage());
        }
    }

    private void cargarManual() {
        String id;
        // Validamos que el Id no exista
        while (true) {
            System.out.print("ID: ");
            id = scanner.nextLine();
            if (idsUsados.contains(id)) {
                System.out.println("Ese ID ya existe. Por favor, ingrese uno nuevo.");
            } else {
                idsUsados.add(id);
                break;
            }
        }

        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        System.out.print("¿Es Urgente? (Si/No): ");
        boolean urgente = scanner.nextLine().equalsIgnoreCase("si");

        System.out.print("Contenido: ");
        String cont = scanner.nextLine();

        centro.recibirPaquete(new Paquete<>(id, peso, destino, urgente, cont));
        System.out.println("Paquete ingresado correctamente al Centro.");
    }

    private void despacharHaciaCamion() {
        Paquete<String> p = centro.despacharSiguiente();
        if (p != null) {
            camion.cargarPaqueteAlCamion(p);
            System.out.println("Paquete enviado al camión: " + p.getId());
        } else {
            System.out.println("No hay paquetes en el centro.");
        }
    }

    private void deshacerCarga() {
        Paquete<String> p = camion.deshacerUltimaCargaDelCamion();
        if (p != null) {
            System.out.println("Carga deshecha: " + p.getId());
        } else {
            System.out.println("Camión vacío.");
        }
    }

    private void descargar() {
        Paquete<String> p = camion.descargarPaqueteDelCamion();
        if (p != null) {
            System.out.println("Descargando: " + p);
        } else {
            System.out.println("Camión vacío.");
        }
    }
}