/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

/**
 *
 * @author pauli
 */
public class Asiento {

    private int idAsiento;
    private int asiento;
    private String fila;
    private int idUsuario;
    private int idEvento;

    // Constructor vacío
    public Asiento() {
    }

    // Constructor con parámetros
    public Asiento(int idAsiento, int asiento, String fila, int idUsuario, int idEvento) {
        this.idAsiento = idAsiento;
        this.asiento = asiento;
        this.fila = fila;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    // Constructor sin idAsiento (para inserciones)
    public Asiento(int asiento, String fila, int idUsuario, int idEvento) {
        this.asiento = asiento;
        this.fila = fila;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    // Getters y Setters
    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asiento{");
        sb.append("idAsiento=").append(idAsiento);
        sb.append(", asiento=").append(asiento);
        sb.append(", fila='").append(fila).append('\'');
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", idEvento=").append(idEvento);
        sb.append('}');
        return sb.toString();
    }
}
