/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AsientoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.sql.SQLException;
import java.util.List;
import objetos.Asiento;

/**
 *
 * @author pauli
 */
public interface IAsientoBO {

    boolean actualizar(AsientoDTO asiento) throws NegocioException;

    boolean agregar(AsientoDTO asiento) throws NegocioException, PersistenciaException, SQLException;

    // Método adicional para comprar asiento si es necesario
    boolean comprarAsiento(int idAsiento, int idUsuario) throws NegocioException;

    List<AsientoDTO> consultar() throws NegocioException;

    AsientoDTO consultar(int id) throws NegocioException;

    boolean crearAsientos(int numeroFilas, int numeroAsientosPorFila, int idEvento) throws NegocioException;

    boolean eliminar(int id) throws NegocioException;

    List<AsientoDTO> consultarPorEvento(int idEvento) throws NegocioException;

    void asignarAsientos(List<Asiento> asientosSeleccionados) throws PersistenciaException;
    
}
