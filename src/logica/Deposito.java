package logica;

import java.time.LocalDateTime;

public class Deposito {
    int id;
    boolean visitado;
    LocalDateTime fechaUltimaAuditoria;
    Deposito izquierdo;
    Deposito derecho;

    public Deposito(int id) {
        this.id = id;
        this.visitado = false;
        this.fechaUltimaAuditoria = LocalDateTime.now();
        this.izquierdo = null;
        this.derecho = null;
    }
}
