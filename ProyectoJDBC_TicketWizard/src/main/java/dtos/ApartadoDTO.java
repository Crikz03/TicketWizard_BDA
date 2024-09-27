/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 * Clase que representa un Apartado de un boleto por un usuario. Un apartado
 * implica que un usuario ha reservado temporalmente un boleto, sin haber
 * completado la compra. Esta clase almacena la relación entre un boleto y el
 * usuario que lo apartó.
 *
 * Esta clase es útil para gestionar la reserva temporal de boletos en el
 * proceso de compra.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class ApartadoDTO {

    private int idBoleto; // Identificador del boleto apartado
    private int idUsuario; // Identificador del usuario que apartó el boleto

    /**
     * Constructor vacío para crear un objeto ApartadoDTO sin parámetros
     * iniciales. Se utiliza cuando los datos del apartado aún no están
     * disponibles.
     */
    public ApartadoDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto ApartadoDTO. Se utiliza
     * cuando se tiene la información del boleto apartado y del usuario que lo
     * apartó.
     *
     * @param idBoleto Identificador del boleto apartado.
     * @param idUsuario Identificador del usuario que apartó el boleto.
     */
    public ApartadoDTO(int idBoleto, int idUsuario) {
        this.idBoleto = idBoleto;
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el identificador del boleto apartado.
     *
     * @return El identificador del boleto.
     */
    public int getIdBoleto() {
        return idBoleto;
    }

    /**
     * Establece el identificador del boleto apartado.
     *
     * @param idBoleto El identificador del boleto a asignar.
     */
    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    /**
     * Obtiene el identificador del usuario que apartó el boleto.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario que apartó el boleto.
     *
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
