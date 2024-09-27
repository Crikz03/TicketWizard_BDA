/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package utilidades;

/**
 * Enum que representa los diferentes tipos de transacciones que pueden realizarse
 * en el sistema.
 * 
 * Los tipos de transacciones disponibles son:
 * - compra: Representa una transacción de compra de boletos.
 * - saldo: Representa una transacción relacionada con la adición de saldo a la cuenta del usuario.
 * - venta: Representa una transacción de venta de boletos.
 * - comision: Representa una transacción de comisión asociada a las ventas.
 * 
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public enum TipoTransaccion {
    compra, saldo, venta, comision;
}

