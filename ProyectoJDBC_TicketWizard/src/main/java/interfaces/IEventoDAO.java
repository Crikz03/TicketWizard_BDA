/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Evento;

/**
 * Interfaz para la gestión de eventos en el nivel de persistencia. Define los
 * métodos necesarios para agregar, actualizar, eliminar y consultar eventos en
 * la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IEventoDAO {

    /**
     * Actualiza un evento existente en la base de datos.
     *
     * @param evento el objeto Evento que contiene los datos actualizados
     * @return true si el evento se actualizó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean actualizar(Evento evento) throws PersistenciaException;

    /**
     * Agrega un nuevo evento a la base de datos.
     *
     * @param evento el objeto Evento que representa el evento a agregar
     * @return true si el evento se agregó exitosamente, false en caso contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean agregar(Evento evento) throws PersistenciaException;

    /**
     * Consulta todos los eventos en la base de datos.
     *
     * @return una lista de objetos Evento que representan todos los eventos
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    List<Evento> consultar() throws PersistenciaException;

    /**
     * Consulta un evento por su ID.
     *
     * @param id el identificador del evento a consultar
     * @return el objeto Evento que representa el evento, o null si no existe
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    Evento consultar(int id) throws PersistenciaException;

    /**
     * Elimina un evento de la base de datos por su ID.
     *
     * @param id el identificador del evento a eliminar
     * @return true si el evento se eliminó exitosamente, false en caso
     * contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean eliminar(int id) throws PersistenciaException;

    /**
     * Verifica si un evento con el nombre dado existe en la base de datos.
     *
     * @param nombre el nombre del evento a verificar
     * @return true si el evento existe, false en caso contrario
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    boolean existeEvento(String nombre) throws PersistenciaException;

    /**
     * Consulta un evento por su nombre.
     *
     * @param nombre el nombre del evento a consultar
     * @return el objeto Evento que representa el evento, o null si no existe
     * @throws PersistenciaException si ocurre un error al acceder a la base de
     * datos
     */
    Evento consultarPorNombre(String nombre) throws PersistenciaException;

}
