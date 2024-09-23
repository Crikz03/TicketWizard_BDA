/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;


import excepciones.PersistenciaException;
import java.util.List;
import objetos.Evento;

/**
 *
 * @author pauli
 */
public interface IEventoDAO {

    boolean actualizar(Evento evento) throws PersistenciaException;

    boolean agregar(Evento evento) throws PersistenciaException;

    List<Evento> consultar() throws PersistenciaException;

    Evento consultar(int id) throws PersistenciaException;

    boolean eliminar(int id) throws PersistenciaException;
    
    boolean existeEvento(String nombre) throws PersistenciaException;

    Evento consultarPorNombre(String nombre) throws PersistenciaException;

}
