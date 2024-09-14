/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import utilidades.EstadoAdquisicion;

/**
 *
 * @author pauli
 */
public class Boleto {

    private int idBoleto;
    private String numSerie;
    private String fila;
    private String asiento;
    private double precio;
    private EstadoAdquisicion estadoAdquisicion;
    private int idUsuario;
    private int idEvento;

    // Constructor vacío
    public Boleto() {
    }

    // Constructor con parámetros
    public Boleto(int idBoleto, String numSerie, String fila, String asiento, double precio, EstadoAdquisicion estadoAdquisicion, int idUsuario, int idEvento) {
        this.idBoleto = idBoleto;
        this.numSerie = numSerie;
        this.fila = fila;
        this.asiento = asiento;
        this.precio = precio;
        this.estadoAdquisicion = estadoAdquisicion;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    public Boleto(String numSerie, String fila, String asiento, double precio, EstadoAdquisicion estadoAdquisicion, int idUsuario, int idEvento) {
        this.numSerie = numSerie;
        this.fila = fila;
        this.asiento = asiento;
        this.precio = precio;
        this.estadoAdquisicion = estadoAdquisicion;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
    }

    // Getters y Setters
    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoAdquisicion getEstadoAdquisicion() {
        return estadoAdquisicion;
    }

    public void setEstadoAdquisicion(EstadoAdquisicion estadoAdquisicion) {
        this.estadoAdquisicion = estadoAdquisicion;
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
        sb.append("Boleto{");
        sb.append("idBoleto=").append(idBoleto);
        sb.append(", numSerie=").append(numSerie);
        sb.append(", fila=").append(fila);
        sb.append(", asiento=").append(asiento);
        sb.append(", precio=").append(precio);
        sb.append(", estadoAdquisicion=").append(estadoAdquisicion);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", idEvento=").append(idEvento);
        sb.append('}');
        return sb.toString();
    }
    
    
}
