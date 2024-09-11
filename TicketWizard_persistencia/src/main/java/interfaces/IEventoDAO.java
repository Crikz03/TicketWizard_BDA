/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Evento;

/**
 *
 * @author pauli
 */
public interface IEventoDAO {

    boolean actualizar(Evento evento);

    boolean agregar(Evento evento);

    List<Evento> consultar();

    Evento consultar(int id);

    boolean eliminar(int id);
    
}
