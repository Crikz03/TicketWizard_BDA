/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.sql.Date;

/**
 *
 * @author pauli
 */
public class Evento {

    private int idEvento;
    private String nombre;
    private Date fecha;
    private String localidad;
    private int capacidad;
    private String venue;

    // Constructor vacío
    public Evento() {
    }

    // Constructor con parámetros
    public Evento(int idEvento, String nombre, Date fecha, String localidad, int capacidad, String venue) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.venue = venue;
    }

    public Evento(String nombre, Date fecha, String localidad, int capacidad, String venue) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
        this.capacidad = capacidad;
        this.venue = venue;
    }

    // Getters y Setters
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

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
        sb.append('}');
        return sb.toString();
    }

}
