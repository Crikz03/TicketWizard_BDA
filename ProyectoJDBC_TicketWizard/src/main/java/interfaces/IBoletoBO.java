/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.BoletoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import objetos.Boleto;
import utilidades.EstadoAdquisicion;
import utilidades.TipoTransaccion;

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
     * @param numSerie el número de serie del boleto
     * @param precio el precio del boleto
     * @param estadoAdquisicion el estado de adquisición del boleto
     * @param idUsuario el identificador del usuario que realiza la compra
     * @return true si la compra fue exitosa, false en caso contrario
     * @throws NegocioException si ocurre un error durante la operación de
     * negocio
     */
    boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario) throws NegocioException;

    boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws NegocioException;

    void asignarBoletos(List<BoletoDTO> boletosSeleccionados) throws NegocioException;

    List<BoletoDTO> consultarPorEvento(int idEvento) throws NegocioException;

    List<BoletoDTO> consultarAsignados() throws NegocioException;
}
