/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import utilidades.TipoTransaccion;

/**
 * La clase Transaccion representa una transacción que involucra un monto, una
 * comisión y se asocia a un tipo de transacción. También incluye un tiempo
 * límite y la fecha en que se realiza, así como un usuario responsable.
 * 
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class Transaccion {

    private int numTransaccion; // Identificador único de la transacción
    private double monto; // Monto total de la transacción
    private double comision; // Comisión aplicada a la transacción
    private Time tiempoLimite; // Tiempo límite para realizar la transacción
    private TipoTransaccion tipo; // Tipo de transacción 
    private Timestamp  fecha; // Fecha en que se realiza la transacción
    private int idUsuario; // Identificador del usuario que realiza la transacción

    // Constructor vacío
    public Transaccion() {
    }

    /**
     * Constructor que inicializa un objeto Transaccion con todos sus atributos.
     * 
     * @param numTransaccion Identificador único de la transacción.
     * @param monto Monto total de la transacción.
     * @param comision Comisión aplicada a la transacción.
     * @param tiempoLimite Tiempo límite para completar la transacción.
     * @param tipo Tipo de transacción (Ej. Compra, Venta, etc.).
     * @param fecha Fecha en que se realiza la transacción.
     * @param idUsuario Identificador del usuario que realiza la transacción.
     */
    public Transaccion(int numTransaccion, double monto, double comision, Time tiempoLimite, TipoTransaccion tipo, Timestamp fecha, int idUsuario) {
        this.numTransaccion = numTransaccion;
        this.monto = monto;
        this.comision = comision;
        this.tiempoLimite = tiempoLimite;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }
    /**
     * Constructor que inicializa un objeto Transaccion sin el identificador de la transacción.
     * 
     * @param monto Monto total de la transacción.
     * @param comision Comisión aplicada a la transacción.
     * @param tiempoLimite Tiempo límite para completar la transacción.
     * @param tipo Tipo de transacción (Ej. Compra, Venta, etc.).
     * @param fecha Fecha en que se realiza la transacción.
     * @param idUsuario Identificador del usuario que realiza la transacción.
     */
    public Transaccion(double monto, double comision, Time tiempoLimite, TipoTransaccion tipo, Timestamp fecha, int idUsuario) {
        this.monto = monto;
        this.comision = comision;
        this.tiempoLimite = tiempoLimite;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    // Getters y Setters
    /**
     * Obtiene el número de la transacción.
     * 
     * @return numTransaccion El identificador de la transacción.
     */
    public int getNumTransaccion() {
        return numTransaccion;
    }
    /**
     * Asigna el número de la transacción.
     * 
     * @param numTransaccion El nuevo identificador de la transacción.
     */
    public void setNumTransaccion(int numTransaccion) {
        this.numTransaccion = numTransaccion;
    }
    /**
     * Obtiene el monto total de la transacción.
     * 
     * @return monto El monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }
    /**
     * Asigna el monto total de la transacción.
     * 
     * @param monto El nuevo monto de la transacción.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }
    /**
     * Obtiene la comisión aplicada a la transacción.
     * 
     * @return comision La comisión de la transacción.
     */
    public double getComision() {
        return comision;
    }
    /**
     * Asigna la comisión a la transacción.
     * 
     * @param comision El nuevo valor de la comisión.
     */
    public void setComision(double comision) {
        this.comision = comision;
    }
    /**
     * Obtiene el tiempo límite para completar la transacción.
     * 
     * @return tiempoLimite El tiempo límite.
     */
    public Time getTiempoLimite() {
        return tiempoLimite;
    }
    /**
     * Asigna el tiempo límite para completar la transacción.
     * 
     * @param tiempoLimite El nuevo tiempo límite.
     */
    public void setTiempoLimite(Time tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }
    /**
     * Obtiene el tipo de transacción.
     * 
     * @return tipo El tipo de transacción (Ej. Compra, Venta).
     */
    public TipoTransaccion getTipo() {
        return tipo;
    }
    /**
     * Asigna el tipo de transacción.
     * 
     * @param tipo El nuevo tipo de transacción.
     */
    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }
    /**
     * Obtiene la fecha en que se realiza la transacción.
     * 
     * @return fecha La fecha de la transacción.
     */
    public Timestamp getFecha() {
        return fecha;
    }
    /**
     * Asigna la fecha de la transacción.
     * 
     * @param fecha La nueva fecha de la transacción.
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    /**
     * Obtiene el identificador del usuario que realiza la transacción.
     * 
     * @return idUsuario El ID del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
     * Asigna el identificador del usuario que realiza la transacción.
     * 
     * @param idUsuario El nuevo ID del usuario.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
     * Genera una representación en cadena del objeto Transaccion.
     * 
     * @return Una cadena con todos los atributos de la transacción.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaccion{");
        sb.append("numTransaccion=").append(numTransaccion);
        sb.append(", monto=").append(monto);
        sb.append(", comision=").append(comision);
        sb.append(", tiempoLimite=").append(tiempoLimite);
        sb.append(", tipo=").append(tipo);
        sb.append(", fecha=").append(fecha);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append('}');
        return sb.toString();
    }

}
