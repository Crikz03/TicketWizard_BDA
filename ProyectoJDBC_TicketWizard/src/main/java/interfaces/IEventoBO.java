/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EventoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Chris
 */
public interface IEventoBO {

    boolean agregar(EventoDTO evento) throws NegocioException;

    boolean actualizar(EventoDTO evento) throws NegocioException;

    boolean eliminar(int id) throws NegocioException;

    List<EventoDTO> consultar() throws NegocioException;

    EventoDTO consultar(int id) throws NegocioException;
    
    boolean existeEvento(String nombre) throws NegocioException;

}
