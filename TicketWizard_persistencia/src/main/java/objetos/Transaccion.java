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
public class Transaccion {
    private int idTransaccion;
    private String numTransaccion;
    private double monto;
    private Date fecha;
    private int idUsuario;
    private int idBoleto;

    // Constructor vacío
    public Transaccion() {
    }

    // Constructor con parámetros
    public Transaccion(int idTransaccion, String numTransaccion, double monto, Date fecha, int idUsuario, int idBoleto) {
        this.idTransaccion = idTransaccion;
        this.numTransaccion = numTransaccion;
        this.monto = monto;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idBoleto = idBoleto;
    }

    // Getters y Setters
    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getNumTransaccion() {
        return numTransaccion;
    }

    public void setNumTransaccion(String numTransaccion) {
        this.numTransaccion = numTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }
}
