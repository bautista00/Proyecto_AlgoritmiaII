package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {

    // Scanner para leer datos ingresados por consola
    private Scanner scanner = new Scanner(System.in);

    // Diccionario de paquetes: la clave es el ID y el valor es el paquete completo
    private Map<String, Paquete<String>> mapaDePaquetes = new HashMap<>();

    // Camión que internamente maneja los paquetes con una pila
    private Camion camion = new Camion();

    public void iniciarMenu() {
        int opcionSeleccionada = 0;

        while (opcionSeleccionada != 7) {
            mostrarOpcionesDelMenu();

            opcionSeleccionada = scanner.nextInt();
            scanner.nextLine(); // Limpia el salto de línea pendiente

            switch (opcionSeleccionada) {
                case 1:
                    cargarPaqueteManual();
                    break;
                case 2:
                    mostrarPaquetesCargados();
                    break;
                case 3:
                    cargarPaqueteAlCamion();
                    break;
                case 4:
                    mostrarPaquetesDelCamion();
                    break;
                case 5:
                    descargarPaqueteDelCamion();
                    break;
                case 6:
                    deshacerUltimaCargaDelCamion();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Muestra las opciones disponibles para el usuario
    private void mostrarOpcionesDelMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Cargar paquete manualmente");
        System.out.println("2. Ver paquetes cargados");
        System.out.println("3. Cargar paquete al camión");
        System.out.println("4. Ver paquetes del camión");
        System.out.println("5. Descargar paquete del camión");
        System.out.println("6. Deshacer última carga del camión");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Crea un paquete nuevo y lo guarda en el mapa usando su ID como clave
    private void cargarPaqueteManual() {
        System.out.print("Ingrese ID del paquete: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese peso del paquete: ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Ingrese destino del paquete: ");
        String destino = scanner.nextLine();

        System.out.print("Ingrese contenido del paquete: ");
        String contenido = scanner.nextLine();

        Paquete<String> paqueteNuevo = new Paquete<>(id, peso, destino, contenido);

        mapaDePaquetes.put(id, paqueteNuevo);

        System.out.println("Paquete cargado correctamente.");
    }

    // Muestra todos los paquetes guardados en el mapa
    private void mostrarPaquetesCargados() {
        if (mapaDePaquetes.isEmpty()) {
            System.out.println("No hay paquetes cargados.");
        } else {
            for (Paquete<String> paquete : mapaDePaquetes.values()) {
                System.out.println(paquete);
            }
        }
    }

    // Busca un paquete por ID y lo carga al camión
    private void cargarPaqueteAlCamion() {
        System.out.print("Ingrese el ID del paquete a cargar al camión: ");
        String idBuscado = scanner.nextLine();

        Paquete<String> paqueteEncontrado = mapaDePaquetes.get(idBuscado);

        if (paqueteEncontrado == null) {
            System.out.println("No existe un paquete con ese ID.");
        } else {
            camion.cargarPaqueteAlCamion(paqueteEncontrado);
            System.out.println("Paquete cargado al camión correctamente.");
        }
    }

    // Muestra los paquetes que están actualmente dentro del camión
    private void mostrarPaquetesDelCamion() {
        camion.mostrarPaquetesDelCamion();
    }

    // Descarga el último paquete cargado al camión
    private void descargarPaqueteDelCamion() {
        Paquete<String> paqueteDescargado = camion.descargarPaqueteDelCamion();

        if (paqueteDescargado == null) {
            System.out.println("El camión está vacío.");
        } else {
            System.out.println("Paquete descargado:");
            System.out.println(paqueteDescargado);
        }
    }

    // Deshace la última carga realizada en el camión
    private void deshacerUltimaCargaDelCamion() {
        Paquete<String> paqueteEliminado = camion.deshacerUltimaCargaDelCamion();

        if (paqueteEliminado == null) {
            System.out.println("No hay cargas para deshacer.");
        } else {
            System.out.println("Se deshizo la carga del paquete:");
            System.out.println(paqueteEliminado);
        }
    }
}