/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import utilidades.EstadoAdquisicion;

/**
 *
 * @author Chris
 */
public class BoletoDTO {

    private int idBoleto;
    private String numSerie;
    private String fila;
    private String asiento;
    private double precio;
    private EstadoAdquisicion estadoAdquisicion;
    private int idUsuario;
    private int idEvento;
    private boolean selected;
    public BoletoDTO() {
    }

    public BoletoDTO(String numSerie, String fila, String asiento, double precio, EstadoAdquisicion estadoAdquisicion, int idUsuario, int idEvento,boolean selected) {
        this.numSerie = numSerie;
        this.fila = fila;
        this.asiento = asiento;
        this.precio = precio;
        this.estadoAdquisicion = estadoAdquisicion;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.selected=selected;
    }
    public BoletoDTO(String fila, String asiento, int idEvento, int idUsuario, boolean selected) {
        this.fila = fila;
        this.asiento = asiento;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.selected = selected;
    }
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
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
