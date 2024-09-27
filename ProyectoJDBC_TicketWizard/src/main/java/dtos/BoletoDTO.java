/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import utilidades.EstadoAdquisicion;

/**
 * Clase que representa un boleto para un evento. Un boleto contiene información
 * sobre su estado de adquisición, ubicación y precio. Esta clase se utiliza
 * para gestionar los detalles relacionados con un boleto, ya sea para la
 * compra, reventa o apartado por un usuario.
 *
 * Esta clase es útil para el manejo de boletos en el sistema de venta de
 * entradas, incluyendo aspectos como la selección, reventa y estado del boleto.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class BoletoDTO {

    private int idBoleto; // Identificador del boleto
    private String numSerie; // Número de serie del boleto
    private String fila; // Fila donde se ubica el asiento
    private String asiento; // Número del asiento
    private double precioOriginal; // Precio original del boleto
    private double precioReventa; // Precio al que se revende el boleto
    private EstadoAdquisicion estadoAdquisicion; // Estado de adquisición del boleto
    private int idUsuario; // Identificador del usuario que posee el boleto
    private int idEvento; // Identificador del evento asociado al boleto
    private boolean selected; // Estado de selección del boleto
    private boolean apartado; // Indica si el boleto está apartado
    private boolean en_venta; // Indica si el boleto está en venta

    /**
     * Constructor vacío para crear un objeto BoletoDTO sin parámetros
     * iniciales. Se utiliza cuando los datos del boleto aún no están
     * disponibles.
     */
    public BoletoDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto BoletoDTO. Se utiliza
     * cuando se tiene la información del boleto, su precio y estado de
     * adquisición.
     *
     * @param numSerie Número de serie del boleto.
     * @param fila Fila donde se ubica el asiento.
     * @param asiento Número del asiento.
     * @param precioOriginal Precio original del boleto.
     * @param precioReventa Precio al que se revende el boleto.
     * @param estadoAdquisicion Estado de adquisición del boleto.
     * @param idUsuario Identificador del usuario que posee el boleto.
     * @param idEvento Identificador del evento asociado al boleto.
     * @param selected Estado de selección del boleto.
     * @param en_venta Indica si el boleto está en venta.
     * @param apartado Indica si el boleto está apartado.
     */
    public BoletoDTO(String numSerie, String fila, String asiento, double precioOriginal,
            double precioReventa, EstadoAdquisicion estadoAdquisicion,
            int idUsuario, int idEvento, boolean selected,
            boolean en_venta, boolean apartado) {
        this.numSerie = numSerie;
        this.fila = fila;
        this.asiento = asiento;
        this.precioOriginal = precioOriginal;
        this.precioReventa = precioReventa;
        this.estadoAdquisicion = estadoAdquisicion;
        this.idUsuario = idUsuario;
        this.idEvento = idEvento;
        this.selected = selected;
        this.apartado = apartado;
        this.en_venta = en_venta;
    }

    /**
     * Constructor con parámetros para crear un objeto BoletoDTO. Se utiliza
     * cuando se tiene la información básica del asiento y del evento asociado.
     *
     * @param fila Fila donde se ubica el asiento.
     * @param asiento Número del asiento.
     * @param idEvento Identificador del evento asociado al boleto.
     * @param idUsuario Identificador del usuario que posee el boleto.
     * @param selected Estado de selección del boleto.
     */
    public BoletoDTO(String fila, String asiento, int idEvento, int idUsuario, boolean selected) {
        this.fila = fila;
        this.asiento = asiento;
        this.idEvento = idEvento;
        this.idUsuario = idUsuario;
        this.selected = selected;
    }

    /**
     * Obtiene el estado de apartado del boleto.
     *
     * @return true si el boleto está apartado, false en caso contrario.
     */
    public boolean getApartado() {
        return apartado;
    }

    /**
     * Establece el estado de apartado del boleto.
     *
     * @param apartado El estado de apartado a asignar.
     */
    public void setApartado(boolean apartado) {
        this.apartado = apartado;
    }

    /**
     * Obtiene el estado de venta del boleto.
     *
     * @return true si el boleto está en venta, false en caso contrario.
     */
    public boolean getEn_venta() {
        return en_venta;
    }

    /**
     * Establece el estado de venta del boleto.
     *
     * @param en_venta El estado de venta a asignar.
     */
    public void setEn_venta(boolean en_venta) {
        this.en_venta = en_venta;
    }

    /**
     * Obtiene el identificador del boleto.
     *
     * @return El identificador del boleto.
     */
    public int getIdBoleto() {
        return idBoleto;
    }

    /**
     * Establece el identificador del boleto.
     *
     * @param idBoleto El identificador del boleto a asignar.
     */
    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    /**
     * Obtiene el número de serie del boleto.
     *
     * @return El número de serie del boleto.
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * Establece el número de serie del boleto.
     *
     * @param numSerie El número de serie a asignar.
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * Obtiene la fila donde se ubica el asiento.
     *
     * @return La fila del asiento.
     */
    public String getFila() {
        return fila;
    }

    /**
     * Establece la fila donde se ubica el asiento.
     *
     * @param fila La fila a asignar.
     */
    public void setFila(String fila) {
        this.fila = fila;
    }

    /**
     * Obtiene el número del asiento.
     *
     * @return El número del asiento.
     */
    public String getAsiento() {
        return asiento;
    }

    /**
     * Establece el número del asiento.
     *
     * @param asiento El número del asiento a asignar.
     */
    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    /**
     * Obtiene el precio original del boleto.
     *
     * @return El precio original del boleto.
     */
    public double getPrecioOriginal() {
        return precioOriginal;
    }

    /**
     * Establece el precio original del boleto.
     *
     * @param precioOriginal El precio original a asignar.
     */
    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    /**
     * Obtiene el precio de reventa del boleto.
     *
     * @return El precio de reventa del boleto.
     */
    public double getPrecioReventa() {
        return precioReventa;
    }

    /**
     * Establece el precio de reventa del boleto.
     *
     * @param precioReventa El precio de reventa a asignar.
     */
    public void setPrecioReventa(double precioReventa) {
        this.precioReventa = precioReventa;
    }

    /**
     * Obtiene el estado de adquisición del boleto.
     *
     * @return El estado de adquisición del boleto.
     */
    public EstadoAdquisicion getEstadoAdquisicion() {
        return estadoAdquisicion;
    }

    /**
     * Establece el estado de adquisición del boleto.
     *
     * @param estadoAdquisicion El estado de adquisición a asignar.
     */
    public void setEstadoAdquisicion(EstadoAdquisicion estadoAdquisicion) {
        this.estadoAdquisicion = estadoAdquisicion;
    }

    /**
     * Obtiene el identificador del usuario que posee el boleto.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que posee el boleto.
     *
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el identificador del evento asociado al boleto.
     *
     * @return El identificador del evento.
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento asociado al boleto.
     *
     * @param idEvento El identificador del evento a asignar.
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el estado de selección del boleto.
     *
     * @return true si el boleto está seleccionado, false en caso contrario.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Establece el estado de selección del boleto.
     *
     * @param selected El estado de selección a asignar.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
