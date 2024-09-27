/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.Date;
import java.util.List;
import objetos.Boleto;
import utilidades.EstadoAdquisicion;
import utilidades.TipoTransaccion;

/**
 * Interfaz para la gestión de boletos en la capa de persistencia. Define los
 * métodos necesarios para realizar operaciones de acceso a datos sobre los
 * boletos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IBoletoDAO {

    /**
     * Agrega un nuevo boleto al sistema de persistencia.
     *
     * @param boleto el objeto Boleto que representa el boleto a agregar
     * @return true si el boleto fue agregado exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean agregar(Boleto boleto) throws PersistenciaException;

    /**
     * Elimina un boleto existente del sistema de persistencia.
     *
     * @param id el identificador del boleto a eliminar
     * @return true si el boleto fue eliminado exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean eliminar(int id) throws PersistenciaException;

    /**
     * Actualiza la información de un boleto existente en el sistema de
     * persistencia.
     *
     * @param boleto el objeto Boleto que contiene los nuevos datos del boleto
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean actualizar(Boleto boleto) throws PersistenciaException;

    /**
     * Consulta un boleto específico por su identificador en el sistema de
     * persistencia.
     *
     * @param id el identificador del boleto a consultar
     * @return el objeto Boleto correspondiente al id proporcionado
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    Boleto consultar(int id) throws PersistenciaException;

    /**
     * Consulta todos los boletos disponibles en el sistema de persistencia.
     *
     * @return una lista de objetos Boleto que representan los boletos
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    List<Boleto> consultar() throws PersistenciaException;

    /**
     * Crea múltiples boletos para un evento específico.
     *
     * @param numeroFilas el número de filas de asientos a crear
     * @param numeroAsientosPorFila el número de asientos por fila
     * @param idEvento el identificador del evento al que se asignan los boletos
     * @param precio el precio de los boletos a crear
     * @return true si los boletos fueron creados exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws PersistenciaException;

    /**
     * Asigna una lista de boletos seleccionados en el sistema de persistencia.
     *
     * @param boletosSeleccionados la lista de boletos a asignar
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    void asignarBoletosDAO(List<Boleto> boletosSeleccionados) throws PersistenciaException;

    /**
     * Consulta todos los boletos asignados a un evento específico.
     *
     * @param idEvento el identificador del evento
     * @return una lista de objetos Boleto asignados al evento
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    List<Boleto> consultarPorEvento(int idEvento) throws PersistenciaException;

    /**
     * Consulta todos los boletos que están asignados en el sistema de
     * persistencia.
     *
     * @return una lista de objetos Boleto que están asignados
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    List<Boleto> consultarAsignados() throws PersistenciaException;

    /**
     * Consulta todos los boletos asignados a un usuario específico.
     *
     * @param idUsuario el identificador del usuario
     * @return una lista de objetos Boleto que pertenecen al usuario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    List<Boleto> consultarIdUsuario(int idUsuario) throws PersistenciaException;

    /**
     * Permite la compra de un boleto específico por un usuario.
     *
     * @param idBoleto el identificador del boleto a comprar
     * @param precio el precio del boleto
     * @param estadoAdquisicion el estado de adquisición del boleto
     * @param tipoTransaccion el tipo de transacción (compra o saldo)
     * @param idUsuario el identificador del usuario que compra el boleto
     * @param idUsuarioAnteriorDueño el identificador del usuario anterior dueño
     * del boleto
     * @return true si la compra fue exitosa, false en caso contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario, int idUsuarioAnteriorDueño) throws PersistenciaException;

    /**
     * Aparta un boleto para un usuario específico.
     *
     * @param idBoleto el identificador del boleto a apartar
     * @param idUsuario el identificador del usuario que aparta el boleto
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    void apartarBoleto(int idBoleto, int idUsuario) throws PersistenciaException;

    /**
     * Libera un boleto previamente apartado.
     *
     * @param idBoleto el identificador del boleto a liberar
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    void liberarBoleto(int idBoleto) throws PersistenciaException;

    /**
     * Obtiene el precio original de un boleto a partir de su número de serie.
     *
     * @param numSerie el número de serie del boleto
     * @return el precio original del boleto
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    double obtenerPrecioOriginal(String numSerie) throws PersistenciaException;

    /**
     * Permite la reventa de un boleto a un precio específico con una fecha
     * límite.
     *
     * @param idBoleto el identificador del boleto a revender
     * @param precioReventa el precio de reventa del boleto
     * @param fechaLimite la fecha límite para la reventa
     * @param idUsuario el identificador del usuario que revende el boleto
     * @return true si la reventa fue exitosa, false en caso contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean revenderBoleto(int idBoleto, double precioReventa, Date fechaLimite, int idUsuario) throws PersistenciaException;

    /**
     * Consulta todos los boletos en venta que pertenecen a un usuario
     * específico.
     *
     * @param idUsuario el identificador del usuario
     * @return una lista de objetos Boleto que están en venta
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    public List<Boleto> consultarBoletosEnVenta(int idUsuario) throws PersistenciaException;

    /**
     * Actualiza la información de un boleto específico para reventa.
     *
     * @param idBoleto el identificador del boleto a actualizar
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws PersistenciaException si ocurre un error durante la operación de
     * persistencia
     */
    boolean actualizarBoletoParaReventa(int idBoleto) throws PersistenciaException;

}
