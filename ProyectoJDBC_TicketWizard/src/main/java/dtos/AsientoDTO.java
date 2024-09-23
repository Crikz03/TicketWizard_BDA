/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author pauli
 */
public class AsientoDTO {

    private int idAsiento; 
    private String fila; 
    private int asiento; 
    private int idEvento; 
    private int idUsuario; 
    private boolean selected;
    
    public AsientoDTO() {
    }

    public AsientoDTO(String fila, int numero, int idEvento, int idUsuario, boolean selected) {
        this.fila = fila;
        this.asiento = numero;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.selected = selected;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int numero) {
        this.asiento = numero;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

