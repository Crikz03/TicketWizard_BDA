/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Usuario;

/**
 * Interfaz para la gestión de usuarios en el nivel de persistencia. Define los
 * métodos necesarios para agregar, actualizar y consultar usuarios en la base
 * de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IUsuarioDAO {

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     *
     * @param usuario el objeto Usuario que contiene los datos actualizados del
     * usuario
     * @return true si el usuario se actualizó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean actualizar(Usuario usuario) throws PersistenciaException;

    /**
     * Agrega un nuevo usuario a la base de datos.
     *
     * @param usuario el objeto Usuario que representa al usuario a agregar
     * @return true si el usuario se agregó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean agregar(Usuario usuario) throws PersistenciaException;

    /**
     * Consulta todos los usuarios en la base de datos.
     *
     * @return una lista de objetos Usuario que representan todos los usuarios
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    List<Usuario> consultar() throws PersistenciaException;

    /**
     * Consulta un usuario por su dirección de correo electrónico en la base de
     * datos.
     *
     * @param correo la dirección de correo electrónico del usuario a consultar
     * @return el objeto Usuario que representa al usuario, o null si no existe
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    Usuario consultarPorCorreo(String correo) throws PersistenciaException;

    /**
     * Consulta un usuario por su identificador en la base de datos.
     *
     * @param id el identificador del usuario a consultar
     * @return el objeto Usuario que representa al usuario, o null si no existe
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    Usuario consultar(int id) throws PersistenciaException;

    /**
     * Verifica si un correo electrónico ya existe en la base de datos.
     *
     * @param correo la dirección de correo electrónico a verificar
     * @return true si el correo existe, false en caso contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean existeCorreo(String correo) throws PersistenciaException;

    /**
     * Obtiene el saldo actual de un usuario desde la base de datos.
     *
     * @param idUsuario el identificador del usuario cuyo saldo se va a obtener
     * @return el saldo actual del usuario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    double obtenerSaldoActual(int idUsuario) throws PersistenciaException;

    /**
     * Verifica si un usuario puede agregar una cantidad de saldo específica.
     *
     * @param usuario el objeto Usuario que representa al usuario
     * @param montoAgregar la cantidad de saldo a agregar
     * @return true si el usuario puede agregar el saldo, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean puedeAgregarSaldo(Usuario usuario, double montoAgregar) throws PersistenciaException;

}
