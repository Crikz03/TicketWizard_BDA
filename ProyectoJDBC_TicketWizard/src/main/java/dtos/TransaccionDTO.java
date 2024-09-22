/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Date;
import java.sql.Time;
import utilidades.TipoTransaccion;

/**
 *
 * @author Chris
 */
public class TransaccionDTO {
    private int numTransaccion;
    private double monto;
    private double comision;
    private Time tiempoLimite;
    private TipoTransaccion tipo;
    private Date fecha;
    private int idUsuario;

    public TransaccionDTO() {
    }

    public TransaccionDTO(double monto, double comision, Time tiempoLimite, TipoTransaccion tipo, Date fecha, int idUsuario) {
        this.monto = monto;
        this.comision = comision;
        this.tiempoLimite = tiempoLimite;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public int getNumTransaccion() {
        return numTransaccion;
    }

    public void setNumTransaccion(int numTransaccion) {
        this.numTransaccion = numTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public Time getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(Time tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
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
    
    
}
