/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.sql.Time;
import java.sql.Timestamp;
import utilidades.TipoTransaccion;

/**
 * Clase que representa una transacción dentro del sistema de gestión de
 * boletos. Esta clase contiene información sobre el monto de la transacción, la
 * comisión, el tipo de transacción, la fecha y el usuario asociado. Se utiliza
 * para registrar y gestionar transacciones, como la compra de boletos y la
 * adición de saldo.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class TransaccionDTO {

    private int numTransaccion; // Número de la transacción
    private double monto; // Monto de la transacción
    private double comision; // Comisión asociada a la transacción
    private Time tiempoLimite; // Tiempo límite para realizar la transacción
    private TipoTransaccion tipo; // Tipo de transacción (compra, saldo, etc.)
    private Timestamp fecha; // Fecha y hora de la transacción
    private int idUsuario; // Identificador del usuario que realiza la transacción

    /**
     * Constructor vacío para crear un objeto TransaccionDTO sin parámetros
     * iniciales. Se utiliza cuando los datos de la transacción aún no están
     * disponibles.
     */
    public TransaccionDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto TransaccionDTO. Se
     * utiliza cuando se tiene la información completa de la transacción.
     *
     * @param monto Monto de la transacción.
     * @param comision Comisión asociada a la transacción.
     * @param tiempoLimite Tiempo límite para realizar la transacción.
     * @param tipo Tipo de transacción.
     * @param fecha Fecha y hora de la transacción.
     * @param idUsuario Identificador del usuario que realiza la transacción.
     */
    public TransaccionDTO(double monto, double comision, Time tiempoLimite, TipoTransaccion tipo, Timestamp fecha, int idUsuario) {
        this.monto = monto;
        this.comision = comision;
        this.tiempoLimite = tiempoLimite;
        this.tipo = tipo;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el número de la transacción.
     *
     * @return El número de la transacción.
     */
    public int getNumTransaccion() {
        return numTransaccion;
    }

    /**
     * Establece el número de la transacción.
     *
     * @param numTransaccion El número de la transacción a asignar.
     */
    public void setNumTransaccion(int numTransaccion) {
        this.numTransaccion = numTransaccion;
    }

    /**
     * Obtiene el monto de la transacción.
     *
     * @return El monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción.
     *
     * @param monto El monto de la transacción a asignar.
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Obtiene la comisión asociada a la transacción.
     *
     * @return La comisión de la transacción.
     */
    public double getComision() {
        return comision;
    }

    /**
     * Establece la comisión asociada a la transacción.
     *
     * @param comision La comisión de la transacción a asignar.
     */
    public void setComision(double comision) {
        this.comision = comision;
    }

    /**
     * Obtiene el tiempo límite para realizar la transacción.
     *
     * @return El tiempo límite de la transacción.
     */
    public Time getTiempoLimite() {
        return tiempoLimite;
    }

    /**
     * Establece el tiempo límite para realizar la transacción.
     *
     * @param tiempoLimite El tiempo límite de la transacción a asignar.
     */
    public void setTiempoLimite(Time tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    /**
     * Obtiene el tipo de transacción.
     *
     * @return El tipo de transacción.
     */
    public TipoTransaccion getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de transacción.
     *
     * @param tipo El tipo de transacción a asignar.
     */
    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fecha y hora de la transacción.
     *
     * @return La fecha y hora de la transacción.
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha y hora de la transacción.
     *
     * @param fecha La fecha y hora de la transacción a asignar.
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el identificador del usuario que realiza la transacción.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que realiza la transacción.
     *
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
