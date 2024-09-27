/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.BoletoDTO;
import excepciones.NegocioException;
import utilidades.EstadoAdquisicion;
import utilidades.TipoTransaccion;
import java.util.Date;
import java.util.List;

/**
 * Interfaz para la gestión de boletos en el sistema. Define los métodos
 * necesarios para realizar operaciones de negocio sobre los boletos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IBoletoBO {

    /**
     * Agrega un nuevo boleto al sistema.
     *
     * @param boleto el objeto BoletoDTO que representa el boleto a agregar
     * @return true si el boleto fue agregado exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean agregar(BoletoDTO boleto) throws NegocioException;

    /**
     * Elimina un boleto existente del sistema.
     *
     * @param id el identificador del boleto a eliminar
     * @return true si el boleto fue eliminado exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean eliminar(int id) throws NegocioException;

    /**
     * Actualiza la información de un boleto existente en el sistema.
     *
     * @param boleto el objeto BoletoDTO que contiene los nuevos datos del
     * boleto
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean actualizar(BoletoDTO boleto) throws NegocioException;

    /**
     * Consulta un boleto específico por su identificador.
     *
     * @param id el identificador del boleto a consultar
     * @return el objeto BoletoDTO correspondiente al id proporcionado
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    BoletoDTO consultar(int id) throws NegocioException;

    /**
     * Consulta todos los boletos disponibles en el sistema.
     *
     * @return una lista de objetos BoletoDTO que representan los boletos
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    List<BoletoDTO> consultar() throws NegocioException;

    /**
     * Compra un boleto específico en el sistema.
     *
     * @param idBoleto el identificador del boleto a comprar
     * @param precio el precio del boleto
     * @param estadoAdquisicion el estado de adquisición del boleto
     * @param tipoTransaccion el tipo de transacción de la compra
     * @param idUsuario el identificador del usuario que realiza la compra
     * @param idUsuarioAnteriorDueño el identificador del usuario anterior dueño
     * del boleto
     * @return true si la compra fue exitosa, false en caso contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario, int idUsuarioAnteriorDueño) throws NegocioException;

    /**
     * Crea múltiples boletos para un evento.
     *
     * @param numeroFilas el número de filas de asientos
     * @param numeroAsientosPorFila el número de asientos por fila
     * @param idEvento el identificador del evento
     * @param precio el precio de los boletos
     * @return true si los boletos fueron creados exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws NegocioException;

    /**
     * Asigna boletos seleccionados a usuarios.
     *
     * @param boletosSeleccionados la lista de boletos a asignar
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    void asignarBoletos(List<BoletoDTO> boletosSeleccionados) throws NegocioException;

    /**
     * Consulta boletos por evento.
     *
     * @param idEvento el identificador del evento
     * @return una lista de boletos asociados al evento
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    List<BoletoDTO> consultarPorEvento(int idEvento) throws NegocioException;

    /**
     * Consulta boletos asignados en el sistema.
     *
     * @return una lista de boletos asignados
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    List<BoletoDTO> consultarAsignados() throws NegocioException;

    /**
     * Consulta boletos asociados a un usuario específico.
     *
     * @param idUsuario el identificador del usuario
     * @return una lista de boletos del usuario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    List<BoletoDTO> consultarIdUsuario(int idUsuario) throws NegocioException;

    /**
     * Aparta un boleto para un usuario específico.
     *
     * @param idBoleto el identificador del boleto a apartar
     * @param idUsuario el identificador del usuario que aparta el boleto
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    void apartarBoleto(int idBoleto, int idUsuario) throws NegocioException;

    /**
     * Libera un boleto previamente apartado.
     *
     * @param idBoleto el identificador del boleto a liberar
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    void liberarBoleto(int idBoleto) throws NegocioException;

    /**
     * Obtiene el precio original de un boleto basado en su número de serie.
     *
     * @param numSerie el número de serie del boleto
     * @return el precio original del boleto
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    double obtenerPrecioOriginal(String numSerie) throws NegocioException;

    /**
     * Revende un boleto en el sistema.
     *
     * @param idBoleto el identificador del boleto a revender
     * @param precioReventa el precio al cual se revende el boleto
     * @param fechaLimite la fecha límite para la reventa
     * @param idUsuario el identificador del usuario que revende el boleto
     * @return true si la reventa fue exitosa, false en caso contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean revenderBoleto(int idBoleto, double precioReventa, Date fechaLimite, int idUsuario) throws NegocioException;

    /**
     * Consulta los boletos en venta de un usuario específico.
     *
     * @param idUsuario el identificador del usuario
     * @return una lista de boletos en venta
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    List<BoletoDTO> consultarBoletosEnVenta(int idUsuario) throws NegocioException;

    /**
     * Actualiza un boleto para reventa.
     *
     * @param idBoleto el identificador del boleto a actualizar
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean actualizarBoletoParaReventa(int idBoleto) throws NegocioException;
}
