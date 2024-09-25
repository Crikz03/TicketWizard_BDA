/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Boleto;

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

    boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws PersistenciaException;

    void asignarBoletosDAO(List<Boleto> boletosSeleccionados) throws PersistenciaException;

    List<Boleto> consultarPorEvento(int idEvento) throws PersistenciaException;

    List<Boleto> consultarAsignados() throws PersistenciaException;



}
