/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * Clase que representa un asiento en un evento. Un asiento está asociado a un
 * evento específico y puede ser seleccionado por un usuario para la compra.
 * Esta clase almacena la información relacionada con el asiento, incluyendo su
 * ubicación y estado de selección.
 *
 * Esta clase es útil para gestionar la selección de asientos en el proceso de
 * compra de boletos para eventos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class AsientoDTO {

    private int idAsiento; // Identificador del asiento
    private String fila; // Fila en la que se encuentra el asiento
    private int asiento; // Número del asiento
    private int idEvento; // Identificador del evento al que pertenece el asiento
    private int idUsuario; // Identificador del usuario que ha seleccionado el asiento
    private boolean selected; // Estado de selección del asiento

    /**
     * Constructor vacío para crear un objeto AsientoDTO sin parámetros
     * iniciales. Se utiliza cuando los datos del asiento aún no están
     * disponibles.
     */
    public AsientoDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto AsientoDTO. Se utiliza
     * cuando se tiene la información del asiento, el evento y el usuario que lo
     * seleccionó.
     *
     * @param fila Fila en la que se encuentra el asiento.
     * @param numero Número del asiento.
     * @param idEvento Identificador del evento al que pertenece el asiento.
     * @param idUsuario Identificador del usuario que ha seleccionado el
     * asiento.
     * @param selected Estado de selección del asiento.
     */
    public AsientoDTO(String fila, int numero, int idEvento, int idUsuario, boolean selected) {
        this.fila = fila;
        this.asiento = numero;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.selected = selected;
    }

    /**
     * Obtiene el identificador del asiento.
     *
     * @return El identificador del asiento.
     */
    public int getIdAsiento() {
        return idAsiento;
    }

    /**
     * Establece el identificador del asiento.
     *
     * @param idAsiento El identificador del asiento a asignar.
     */
    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    /**
     * Obtiene la fila en la que se encuentra el asiento.
     *
     * @return La fila del asiento.
     */
    public String getFila() {
        return fila;
    }

    /**
     * Establece la fila en la que se encuentra el asiento.
     *
     * @param fila La fila a asignar al asiento.
     */
    public void setFila(String fila) {
        this.fila = fila;
    }

    /**
     * Obtiene el número del asiento.
     *
     * @return El número del asiento.
     */
    public int getAsiento() {
        return asiento;
    }

    /**
     * Establece el número del asiento.
     *
     * @param numero El número del asiento a asignar.
     */
    public void setAsiento(int numero) {
        this.asiento = numero;
    }

    /**
     * Obtiene el identificador del evento al que pertenece el asiento.
     *
     * @return El identificador del evento.
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento al que pertenece el asiento.
     *
     * @param idEvento El identificador del evento a asignar.
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el identificador del usuario que ha seleccionado el asiento.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que ha seleccionado el asiento.
     *
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el estado de selección del asiento.
     *
     * @return true si el asiento está seleccionado, false en caso contrario.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Establece el estado de selección del asiento.
     *
     * @param selected El estado de selección a asignar.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
