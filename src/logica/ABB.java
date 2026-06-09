package logica;

import java.time.LocalDateTime;

public class ABB {
    Deposito raiz;

    public ABB() {
        this.raiz = null;
    }

    public void insertar(int id) {
        raiz = insertarRecursivo(raiz, id);
    }
    // Complejidad temporal: O(log n) caso promedio, O(n) peor caso
    // Caso promedio: árbol balanceado, cada paso descarta la mitad de los nodos
    // Peor caso: árbol desbalanceado (inserción en orden), se convierte en lista
    // Complejidad espacial: O(log n) por la pila de recursión

    private Deposito insertarRecursivo(Deposito nodo, int id) {
        if (nodo == null) {
            return new Deposito(id);
        }
        if (id < nodo.id) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, id);
        } else if (id > nodo.id) {
            nodo.derecho = insertarRecursivo(nodo.derecho, id);
        }
        return nodo;
    }

    public void auditarDepositos() {
        auditarDepositosRecursivo(this.raiz);
    }

    private void auditarDepositosRecursivo(Deposito nodo) {
        if(nodo == null) return;
     auditarDepositosRecursivo(nodo.izquierdo);
        auditarDepositosRecursivo(nodo.derecho);
        if (nodo.fechaUltimaAuditoria.isBefore(LocalDateTime.now().minusDays(30))) {
         nodo.visitado = true;
            nodo.fechaUltimaAuditoria = LocalDateTime.now();
     }
    }
    // Complejidad temporal: O(n)
    // Visita todos los nodos del árbol exactamente una vez en post-orden
    // Complejidad espacial: O(n) por la pila de recursión en el peor caso

    public void imprimirNivel(int nivel) {
        imprimirNivelRecursivo(this.raiz, nivel, 0);
    }

    private void imprimirNivelRecursivo(Deposito nodo, int nivel, int nivelActual) {
        if (nodo == null) return;
        if (nivelActual == nivel) {
            System.out.println("Depósito ID: " + nodo.id + " | Visitado: " + nodo.visitado +
                    " | Última Auditoría: " + nodo.fechaUltimaAuditoria);
        }
        imprimirNivelRecursivo(nodo.izquierdo, nivel, nivelActual + 1);
        imprimirNivelRecursivo(nodo.derecho, nivel, nivelActual + 1);
    }
    // Complejidad temporal: O(n)
    // En el peor caso recorre todos los nodos para encontrar los del nivel N
    // Complejidad espacial: O(n) por la pila de recursión

    public void buscar(int id) {
        Deposito resultado = buscarRecursivo(this.raiz, id);
        if (resultado != null) {
            System.out.println("Depósito encontrado: ID " + resultado.id + " | Visitado: " + resultado.visitado +
                    " | Última Auditoría: " + resultado.fechaUltimaAuditoria);
        } else {
            System.out.println("Depósito con ID " + id + " no encontrado.");
        }
    }
    // Complejidad temporal: O(log n) caso promedio, O(n) peor caso
    // Caso promedio: árbol balanceado, descarta la mitad en cada comparación
    // Peor caso: árbol desbalanceado, recorre todos los nodos
    // Complejidad espacial: O(log n) por la pila de recursión

    private Deposito buscarRecursivo(Deposito nodo, int id) {
        if (nodo == null || nodo.id == id) {
            return nodo;
        }
        if (id < nodo.id) {
            return buscarRecursivo(nodo.izquierdo, id);
        } else {
            return buscarRecursivo(nodo.derecho, id);
        }
    }
}
