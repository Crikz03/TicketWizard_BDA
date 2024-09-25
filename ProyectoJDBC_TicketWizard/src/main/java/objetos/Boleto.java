/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import utilidades.EstadoAdquisicion;

/**
 * Clase que representa un Boleto de un evento.
 * Un boleto tiene un identificador, número de serie, fila, asiento, precio,
 * estado de adquisición, y está asociado a un usuario y a un evento específico.
 * 
 * Esta clase se puede usar para gestionar la compra y asignación de boletos para usuarios en eventos.
 * 
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class Boleto {

    private int idBoleto; //identificador unico del boleto
    private String numSerie; //número de serie del boleto
    private String fila; //fila del asiento correspondiente al boleto
    private String asiento; //numero del asiento asignado al boleto
    private double precio; //precio del boleto
    private EstadoAdquisicion estadoAdquisicion; //estado de adquisicion del boleto(si es reventa o adquirido directo de boletera)
    private int idUsuario; //id del usuario asignado a ese boleto
    private int idEvento; //id del evento al que pertence ese boleto
    

    // Constructor vacío
    public Boleto() {
    }

    /**
     * Constructor con todos los parámetros del boleto, incluyendo el identificador.
     * Se utiliza cuando se tiene toda la información del boleto, por ejemplo,
     * al consultar datos ya almacenados en una base de datos.
     *
     * @param idBoleto Identificador único del boleto.
     * @param numSerie Número de serie del boleto.
     * @param fila Fila donde se encuentra el asiento del boleto.
     * @param asiento Número del asiento del boleto.
     * @param precio Precio del boleto.
     * @param estadoAdquisicion Estado actual del boleto (disponible, reservado, vendido).
     * @param idUsuario Identificador del usuario que adquirió el boleto.
     * @param idEvento Identificador del evento asociado al boleto.
     */
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
    /**
     * Constructor sin el identificador del boleto.
     * Este constructor es útil cuando se crea un nuevo boleto y el identificador es generado automáticamente.
     *
     * @param numSerie Número de serie del boleto.
     * @param fila Fila donde se encuentra el asiento del boleto.
     * @param asiento Número del asiento del boleto.
     * @param precio Precio del boleto.
     * @param estadoAdquisicion Estado actual del boleto (disponible, reservado, vendido).
     * @param idUsuario Identificador del usuario que adquirió el boleto.
     * @param idEvento Identificador del evento asociado al boleto.
     */
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
    
    /**
     * Obtiene el identificador único del boleto.
     * 
     * @return El identificador del boleto.
     */
    public int getIdBoleto() {
        return idBoleto;
    }
    /**
     * Establece el identificador único del boleto.
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
     * Obtiene la fila del asiento asignado al boleto.
     * 
     * @return La fila del asiento.
     */
    public String getFila() {
        return fila;
    }
    /**
     * Establece la fila del asiento asignado al boleto.
     * 
     * @param fila La fila a asignar.
     */
    public void setFila(String fila) {
        this.fila = fila;
    }
    /**
     * Obtiene el número del asiento asignado al boleto.
     * 
     * @return El número del asiento.
     */
    public String getAsiento() {
        return asiento;
    }
    /**
     * Establece el número del asiento asignado al boleto.
     * 
     * @param asiento El asiento a asignar.
     */
    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    /**
     * Obtiene el precio del boleto.
     * 
     * @return El precio del boleto.
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * Establece el precio del boleto.
     * 
     * @param precio El precio a asignar.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
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
     * Obtiene el identificador del usuario que compró o reservó el boleto.
     * 
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
     * Establece el identificador del usuario que compró o reservó el boleto.
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
     * Genera una representación en forma de cadena del objeto Boleto.
     * 
     * @return Una cadena que contiene la información del boleto.
     */
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
