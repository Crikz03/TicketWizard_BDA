/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Date;

/**
 * Clase que representa un evento dentro del sistema de gestión de boletos. Un
 * evento incluye información sobre su nombre, fecha, localidad, capacidad y
 * otros detalles relevantes. Esta clase es utilizada para almacenar y gestionar
 * la información asociada a los eventos en el sistema.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class EventoDTO {

    private int idEvento; // Identificador del evento
    private String nombre; // Nombre del evento
    private Date fecha; // Fecha en que se llevará a cabo el evento
    private String localidad; // Localidad donde se realiza el evento
    private int capacidad; // Capacidad máxima de asistentes al evento
    private String venue; // Lugar específico del evento
    private String descripcion; // Descripción del evento

    /**
     * Constructor vacío para crear un objeto EventoDTO sin parámetros
     * iniciales. Se utiliza cuando los datos del evento aún no están
     * disponibles.
     */
    public EventoDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto EventoDTO. Se utiliza
     * cuando se tiene la información completa del evento.
     *
     * @param nombre Nombre del evento.
     * @param fecha Fecha en que se llevará a cabo el evento.
     * @param localidad Localidad donde se realiza el evento.
     * @param capacidad Capacidad máxima de asistentes al evento.
     * @param venue Lugar específico del evento.
     * @param descripcion Descripción del evento.
     */
    public EventoDTO(String nombre, Date fecha, String localidad, int capacidad, String venue, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.venue = venue;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador del evento.
     *
     * @return El identificador del evento.
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * Establece el identificador del evento.
     *
     * @param idEvento El identificador del evento a asignar.
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return El nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @param nombre El nombre del evento a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha en que se llevará a cabo el evento.
     *
     * @return La fecha del evento.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en que se llevará a cabo el evento.
     *
     * @param fecha La fecha del evento a asignar.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la localidad donde se realiza el evento.
     *
     * @return La localidad del evento.
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Establece la localidad donde se realiza el evento.
     *
     * @param localidad La localidad del evento a asignar.
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Obtiene la capacidad máxima de asistentes al evento.
     *
     * @return La capacidad del evento.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima de asistentes al evento.
     *
     * @param capacidad La capacidad del evento a asignar.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el lugar específico del evento.
     *
     * @return El venue del evento.
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Establece el lugar específico del evento.
     *
     * @param venue El venue del evento a asignar.
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Obtiene la descripción del evento.
     *
     * @return La descripción del evento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del evento.
     *
     * @param descripcion La descripción del evento a asignar.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
