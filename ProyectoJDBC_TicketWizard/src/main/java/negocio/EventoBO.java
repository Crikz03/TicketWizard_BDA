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
 *
 * @author Chris
 */
public class EventoBO implements IEventoBO {

    private IEventoDAO eventodao;
    private IConexion conexion;

    public EventoBO() {
        this.conexion = new ConexionBD();
        this.eventodao = new EventoDAO(conexion);
    }

    @Override
    public boolean agregar(EventoDTO evento) throws NegocioException {
        try {
            eventodao.agregar(ConvertidorGeneral.convertidorEntidad(evento, Evento.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo agregar el evento con id: " + evento.getIdEvento());
        }
    }

    @Override
    public boolean actualizar(EventoDTO evento) throws NegocioException {
        try {
            eventodao.actualizar(ConvertidorGeneral.convertidorEntidad(evento, Evento.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el evento con id: " + evento.getIdEvento());
        }
    }

    @Override
    public boolean eliminar(int id) throws NegocioException {
        try {
            eventodao.eliminar(id);
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo el evento con id: " + id);
        }
    }

    @Override
    public List<EventoDTO> consultar() throws NegocioException {
        try {
            List<Evento> eventos = eventodao.consultar();
            List<EventoDTO> eventosDTO = ConvertidorGeneral.convertidoraListaDTO(eventos, EventoDTO.class);

            return eventosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los eventos disponibles.");
        }
    }

    @Override
    public EventoDTO consultar(int id) throws NegocioException {
        try {
            Evento evento = eventodao.consultar(id);

            EventoDTO boletodto = ConvertidorGeneral.convertidorEntidad(evento, EventoDTO.class);

            return boletodto;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo consultar el evento con id: " + id);
        }
    }
    
    @Override
    public boolean existeEvento(String nombre) throws NegocioException {
        try {
            return eventodao.existeEvento(nombre);
        } catch (PersistenciaException e) {
            throw new NegocioException("No existe el nombre de evento.");
        }
    }

}
