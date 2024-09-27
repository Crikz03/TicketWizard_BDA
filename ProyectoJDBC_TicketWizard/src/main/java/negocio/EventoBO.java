/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.EventoDAO;
import dtos.EventoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConexion;
import interfaces.IEventoBO;
import interfaces.IEventoDAO;
import java.util.List;
import objetos.Evento;

/**
 * Clase que implementa la lógica de negocio para la gestión de eventos. Se
 * encarga de interactuar con el DAO para realizar operaciones sobre los eventos
 * en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class EventoBO implements IEventoBO {

    private IEventoDAO eventodao; // Interfaz para las operaciones de persistencia de eventos
    private IConexion conexion; // Interfaz para la conexión a la base de datos

    public EventoBO() {
        this.conexion = new ConexionBD(); // Inicializa la conexión a la base de datos
        this.eventodao = new EventoDAO(conexion); // Inicializa el DAO de eventos
    }

    /**
     * Agrega un nuevo evento.
     *
     * @param evento El evento a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al agregar el evento.
     */
    @Override
    public boolean agregar(EventoDTO evento) throws NegocioException {
        try {
            // Convierte el DTO a entidad y lo agrega a la base de datos.
            eventodao.agregar(ConvertidorGeneral.convertidorEntidad(evento, Evento.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo agregar el evento con id: " + evento.getIdEvento());
        }
    }

    /**
     * Actualiza un evento existente.
     *
     * @param evento El evento con los datos actualizados.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al actualizar el evento.
     */
    @Override
    public boolean actualizar(EventoDTO evento) throws NegocioException {
        try {
            // Convierte el DTO a entidad y actualiza el evento en la base de datos.
            eventodao.actualizar(ConvertidorGeneral.convertidorEntidad(evento, Evento.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo actualizar el evento con id: " + evento.getIdEvento());
        }
    }

    /**
     * Elimina un evento por su ID.
     *
     * @param id El ID del evento a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al eliminar el evento.
     */
    @Override
    public boolean eliminar(int id) throws NegocioException {
        try {
            // Elimina el evento a través del DAO.
            eventodao.eliminar(id);
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo eliminar el evento con id: " + id);
        }
    }

    /**
     * Consulta todos los eventos disponibles.
     *
     * @return Una lista de objetos EventoDTO que representan los eventos
     * disponibles.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public List<EventoDTO> consultar() throws NegocioException {
        try {
            // Obtiene la lista de eventos desde el DAO.
            List<Evento> eventos = eventodao.consultar();
            // Convierte la lista de eventos a una lista de EventoDTO.
            List<EventoDTO> eventosDTO = ConvertidorGeneral.convertidoraListaDTO(eventos, EventoDTO.class);

            return eventosDTO; // Retorna la lista de EventoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron consultar los eventos disponibles.");
        }
    }

    /**
     * Consulta un evento por su ID.
     *
     * @param id El ID del evento a consultar.
     * @return El objeto EventoDTO correspondiente al ID.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public EventoDTO consultar(int id) throws NegocioException {
        try {
            // Obtiene el evento desde el DAO por ID.
            Evento evento = eventodao.consultar(id);
            // Convierte la entidad a DTO antes de retornarlo.
            EventoDTO eventoDTO = ConvertidorGeneral.convertidorEntidad(evento);

            return eventoDTO; // Retorna el EventoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo consultar el evento con id: " + id);
        }
    }

    /**
     * Verifica si un evento con un nombre específico existe.
     *
     * @param nombre El nombre del evento a verificar.
     * @return true si el evento existe, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la verificación.
     */
    @Override
    public boolean existeEvento(String nombre) throws NegocioException {
        try {
            // Llama al DAO para verificar si existe el evento con el nombre dado.
            return eventodao.existeEvento(nombre);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No existe el nombre de evento.");
        }
    }

    /**
     * Consulta un evento por su nombre.
     *
     * @param nombre El nombre del evento a consultar.
     * @return El objeto EventoDTO correspondiente al nombre.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public EventoDTO consultarPorNombre(String nombre) throws NegocioException {
        try {
            // Validación: verificar si el nombre no es vacío o nulo
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new NegocioException("El nombre del evento no puede estar vacío.");
            }

            // Llamada al método del DAO para consultar por nombre
            Evento evento = eventodao.consultarPorNombre(nombre);

            // Validación adicional: verificar si se encontró el evento
            if (evento == null) {
                throw new NegocioException("No se encontró un evento con el nombre: " + nombre);
            }

            // Convertir el evento a DTO antes de retornarlo
            return ConvertidorGeneral.convertidorEntidad(evento, EventoDTO.class);
        } catch (PersistenciaException e) {
            // Manejo de excepciones relacionadas con la persistencia
            throw new NegocioException("Error al consultar el evento en la capa de negocio.", e);
        }
    }

}
