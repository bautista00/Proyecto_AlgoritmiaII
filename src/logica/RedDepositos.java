package logica;

public class RedDepositos {
    private int[][] matrizAdyacencia;
    private int numDepositos;

    public RedDepositos(int numDepositos) {
        this.numDepositos = numDepositos;
        this.matrizAdyacencia = new int[numDepositos][numDepositos];
    }

    public void agregarRuta(int origen, int destino) {
        if (origen >= 0 && origen < numDepositos && destino >= 0 && destino < numDepositos) {
            matrizAdyacencia[origen][destino] = 1;
            matrizAdyacencia[destino][origen] = 1; // Asumiendo rutas bidireccionales
        }
    }
    // Complejidad temporal: O(1)
    // Solo accede a una celda de la matriz directamente por índice
    // Complejidad espacial: O(1)

    public int cantidadSaltos(int origen, int destino) {
        if (origen == destino) return 0;
        boolean[] visitado = new boolean[numDepositos];
        return bfs(origen, destino, visitado);
    }
    // Complejidad temporal: O(V + E)
    // V = cantidad de depósitos, E = cantidad de rutas
    // BFS visita cada nodo y cada arista exactamente una vez
    // Complejidad espacial: O(V) por el array de visitados y la cola

    private int bfs(int origen, int destino, boolean[] visitado) {
        java.util.Queue<Integer> cola = new java.util.LinkedList<>();
        int[] saltos = new int[numDepositos];
        cola.add(origen);
        visitado[origen] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            for (int i = 0; i < numDepositos; i++) {
                if (matrizAdyacencia[actual][i] == 1 && !visitado[i]) {
                    visitado[i] = true;
                    saltos[i] = saltos[actual] + 1;
                    if (i == destino) return saltos[i];
                    cola.add(i);
                }
            }
        }
        return -1; // No hay ruta
    }

    public void agregarRutaPonderada(int origen, int destino, int peso) {
        if (origen >= 0 && origen < numDepositos && destino >= 0 && destino < numDepositos) {
            matrizAdyacencia[origen][destino] = peso;
            matrizAdyacencia[destino][origen] = peso; // Asumiendo rutas bidireccionales
        }
    }

    public int dijkstra(int origen, int destino) {
        int[] distancias = new int[numDepositos];
        boolean[] visitado = new boolean[numDepositos];
        java.util.Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origen] = 0;

        for (int i = 0; i < numDepositos; i++) {
            int nodoMinimo = -1;
            for (int j = 0; j < numDepositos; j++) {
                if (!visitado[j] && (nodoMinimo == -1 || distancias[j] < distancias[nodoMinimo])) {
                    nodoMinimo = j;
                }
            }
            if (distancias[nodoMinimo] == Integer.MAX_VALUE) break;
            visitado[nodoMinimo] = true;

            for (int j = 0; j < numDepositos; j++) {
                if (matrizAdyacencia[nodoMinimo][j] > 0 && !visitado[j]) {
                    int nuevaDistancia = distancias[nodoMinimo] + matrizAdyacencia[nodoMinimo][j];
                    if (nuevaDistancia < distancias[j]) {
                        distancias[j] = nuevaDistancia;
                    }
                }
            }
        }
        return distancias[destino] == Integer.MAX_VALUE ? -1 : distancias[destino];
    }
    // Complejidad temporal: O(V²)
    // Dos bucles anidados sobre todos los vértices
    // Complejidad espacial: O(V) por los arrays de distancias y visitados
}
