/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.TransaccionDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz para la gestión de transacciones en el nivel de negocio. Define los
 * métodos necesarios para agregar, actualizar, eliminar y consultar
 * transacciones en el sistema.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface ITransaccionBO {

    /**
     * Actualiza una transacción existente en el sistema.
     *
     * @param transaccion el objeto TransaccionDTO que contiene los datos
     * actualizados
     * @return true si la transacción se actualizó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean actualizar(TransaccionDTO transaccion) throws NegocioException;

    /**
     * Agrega una nueva transacción al sistema.
     *
     * @param transaccion el objeto TransaccionDTO que representa la transacción
     * a agregar
     * @return true si la transacción se agregó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean agregar(TransaccionDTO transaccion) throws NegocioException;

    /**
     * Consulta todas las transacciones en el sistema.
     *
     * @return una lista de objetos TransaccionDTO que representan todas las
     * transacciones
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    List<TransaccionDTO> consultar() throws NegocioException;

    /**
     * Consulta una transacción por su número de transacción.
     *
     * @param num_transaccion el identificador de la transacción a consultar
     * @return el objeto TransaccionDTO que representa la transacción, o null si
     * no existe
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    TransaccionDTO consultar(int num_transaccion) throws NegocioException;

    /**
     * Consulta las transacciones de un usuario específico por su ID.
     *
     * @param idUsuario el identificador del usuario cuyas transacciones se van
     * a consultar
     * @return una lista de objetos TransaccionDTO que representan las
     * transacciones del usuario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    List<TransaccionDTO> consultarIdUsuario(int idUsuario) throws NegocioException;

    /**
     * Elimina una transacción del sistema por su número de transacción.
     *
     * @param num_transaccion el identificador de la transacción a eliminar
     * @return true si la transacción se eliminó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean eliminar(int num_transaccion) throws NegocioException;

    /**
     * Agrega saldo a un usuario específico.
     *
     * @param usuario el objeto UsuarioDTO que representa al usuario
     * @param monto la cantidad de saldo a agregar
     * @return true si se agregó el saldo exitosamente, false en caso contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean agregarSaldo(UsuarioDTO usuario, double monto) throws NegocioException;
}
