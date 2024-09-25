/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EventoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz para la gestión de eventos. Define los métodos para agregar,
 * actualizar, eliminar y consultar eventos dentro del sistema, utilizando
 * objetos de transferencia de datos (DTO).
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IEventoBO {

    /**
     * Agrega un nuevo evento al sistema.
     *
     * @param evento el objeto EventoDTO que representa el evento a agregar
     * @return true si el evento se agregó exitosamente, false en caso contrario
     * @throws NegocioException si ocurre un error de negocio al agregar el
     * evento
     */
    boolean agregar(EventoDTO evento) throws NegocioException;

    /**
     * Actualiza un evento existente en el sistema.
     *
     * @param evento el objeto EventoDTO que contiene los datos actualizados del
     * evento
     * @return true si el evento se actualizó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error de negocio al actualizar el
     * evento
     */
    boolean actualizar(EventoDTO evento) throws NegocioException;

    /**
     * Elimina un evento del sistema por su ID.
     *
     * @param id el identificador del evento a eliminar
     * @return true si el evento se eliminó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error de negocio al eliminar el
     * evento
     */
    boolean eliminar(int id) throws NegocioException;

    /**
     * Consulta todos los eventos en el sistema.
     *
     * @return una lista de objetos EventoDTO que representan todos los eventos
     * @throws NegocioException si ocurre un error de negocio al consultar los
     * eventos
     */
    List<EventoDTO> consultar() throws NegocioException;

    /**
     * Consulta un evento por su ID.
     *
     * @param id el identificador del evento a consultar
     * @return el objeto EventoDTO que representa el evento, o null si no existe
     * @throws NegocioException si ocurre un error de negocio al consultar el
     * evento
     */
    EventoDTO consultar(int id) throws NegocioException;

    /**
     * Verifica si un evento con el nombre dado existe en el sistema.
     *
     * @param nombre el nombre del evento a verificar
     * @return true si el evento existe, false en caso contrario
     * @throws NegocioException si ocurre un error de negocio al verificar la
     * existencia del evento
     */
    boolean existeEvento(String nombre) throws NegocioException;

    /**
     * Consulta un evento por su nombre.
     *
     * @param nombre el nombre del evento a consultar
     * @return el objeto EventoDTO que representa el evento, o null si no existe
     * @throws NegocioException si ocurre un error de negocio al consultar el
     * evento
     */
    EventoDTO consultarPorNombre(String nombre) throws NegocioException;

}
