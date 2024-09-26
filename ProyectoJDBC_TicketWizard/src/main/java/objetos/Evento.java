/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.sql.Date;

/**
 * La clase Evento representa un evento que tiene lugar en un lugar específico y
 * en una fecha determinada. Cada evento tiene un nombre, una capacidad máxima
 * de asistentes y se lleva a cabo en un lugar específico.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class Evento {

    private int idEvento; // Identificador único del evento
    private String nombre; // Nombre del evento
    private Date fecha; // Fecha en la que se llevará a cabo el evento
    private String localidad; // Ciudad o lugar donde ocurre el evento
    private int capacidad; // Capacidad máxima del evento
    private String venue; // Nombre del lugar específico donde se realiza el evento
    private String descripcion; //Descripcion del evento

    // Constructor vacío
    public Evento() {
    }

    /**
     * Constructor que inicializa un objeto Evento con todos sus atributos.
     *
     * @param idEvento Identificador único del evento.
     * @param nombre Nombre del evento.
     * @param fecha Fecha en la que se llevará a cabo el evento.
     * @param localidad Lugar o ciudad donde se lleva a cabo el evento.
     * @param capacidad Capacidad máxima de personas que pueden asistir.
     * @param venue Lugar específico donde se realizará el evento.
     * @param descripcion Descripcion del evento.
     */
    public Evento(int idEvento, String nombre, Date fecha, String localidad, int capacidad, String venue, String descripcion) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.venue = venue;
        this.descripcion = descripcion;
    }

    /**
     * Constructor que inicializa un objeto Evento sin el idEvento.
     *
     * @param nombre Nombre del evento.
     * @param fecha Fecha en la que se llevará a cabo el evento.
     * @param localidad Lugar o ciudad donde se lleva a cabo el evento.
     * @param capacidad Capacidad máxima de personas que pueden asistir.
     * @param venue Lugar específico donde se realizará el evento.
     * @param descripcion Descripcion del evento.
     */
    public Evento(String nombre, Date fecha, String localidad, int capacidad, String venue, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.venue = venue;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único del evento.
     *
     * @return idEvento El ID del evento.
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * Asigna el identificador único al evento.
     *
     * @param idEvento El nuevo ID del evento.
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return nombre El nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre al evento.
     *
     * @param nombre El nuevo nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @return fecha La fecha en la que se llevará a cabo el evento.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Asigna la fecha al evento.
     *
     * @param fecha La nueva fecha del evento.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la localidad del evento.
     *
     * @return localidad La ciudad o lugar donde se lleva a cabo el evento.
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Asigna la localidad al evento.
     *
     * @param localidad El nuevo lugar donde se llevará a cabo el evento.
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Obtiene la capacidad máxima del evento.
     *
     * @return capacidad La capacidad máxima de personas que pueden asistir al
     * evento.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Asigna la capacidad máxima del evento.
     *
     * @param capacidad La nueva capacidad máxima de personas que pueden asistir
     * al evento.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el lugar específico donde se llevará a cabo el evento.
     *
     * @return venue El nombre del lugar específico.
     */
    public String getVenue() {
        return venue;
    }

    /**
     * Asigna el lugar específico donde se llevará a cabo el evento.
     *
     * @param venue El nuevo lugar específico donde se llevará a cabo el evento.
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Obtiene la descripcion del evento.
     *
     * @return descripcion del evento.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripcion del evento.
     *
     * @param descripcion La nueva descripcion que se le agregara al evento.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Genera una representación en cadena del objeto Evento.
     *
     * @return Una cadena con todos los atributos del evento.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento{");
        sb.append("idEvento=").append(idEvento);
        sb.append(", nombre=").append(nombre);
        sb.append(", fecha=").append(fecha);
        sb.append(", localidad=").append(localidad);
        sb.append(", capacidad=").append(capacidad);
        sb.append(", venue=").append(venue);
        sb.append(", descripcion=").append(descripcion);
        sb.append('}');
        return sb.toString();
    }
}
