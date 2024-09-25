/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Transaccion;
import objetos.Usuario;

/**
 * Interfaz para la gestión de transacciones en el nivel de persistencia. Define
 * los métodos necesarios para agregar, actualizar, eliminar y consultar
 * transacciones en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface ITransaccionDAO {

    /**
     * Actualiza una transacción existente en la base de datos.
     *
     * @param transaccion el objeto Transaccion que contiene los datos
     * actualizados
     * @return true si la transacción se actualizó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    boolean actualizar(Transaccion transaccion) throws PersistenciaException;

    /**
     * Agrega una nueva transacción a la base de datos.
     *
     * @param transaccion el objeto Transaccion que representa la transacción a
     * agregar
     * @return true si la transacción se agregó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    boolean agregar(Transaccion transaccion) throws PersistenciaException;

    /**
     * Consulta todas las transacciones en la base de datos.
     *
     * @return una lista de objetos Transaccion que representan todas las
     * transacciones
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    List<Transaccion> consultar() throws PersistenciaException;

    /**
     * Consulta una transacción por su número de transacción.
     *
     * @param num_transaccion el identificador de la transacción a consultar
     * @return el objeto Transaccion que representa la transacción, o null si no
     * existe
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    Transaccion consultar(int num_transaccion) throws PersistenciaException;

    /**
     * Consulta las transacciones de un usuario específico por su ID.
     *
     * @param idUsuario el identificador del usuario cuyas transacciones se van
     * a consultar
     * @return una lista de objetos Transaccion que representan las
     * transacciones del usuario
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    List<Transaccion> consultarIdUsuario(int idUsuario) throws PersistenciaException;

    /**
     * Elimina una transacción de la base de datos por su número de transacción.
     *
     * @param num_transaccion el identificador de la transacción a eliminar
     * @return true si la transacción se eliminó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    boolean eliminar(int num_transaccion) throws PersistenciaException;

    /**
     * Agrega saldo a un usuario específico en la base de datos.
     *
     * @param usuario el objeto Usuario que representa al usuario
     * @param monto la cantidad de saldo a agregar
     * @return true si se agregó el saldo exitosamente, false en caso contrario
     * @throws PersistenciaException si ocurre un error en la persistencia de
     * datos
     */
    boolean agregarSaldo(Usuario usuario, double monto) throws PersistenciaException;

}
