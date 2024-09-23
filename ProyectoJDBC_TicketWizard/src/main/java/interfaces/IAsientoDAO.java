/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.sql.SQLException;
import java.util.List;
import objetos.Asiento;

/**
 *
 * @author pauli
 */
public interface IAsientoDAO {

    boolean actualizar(Asiento asiento) throws PersistenciaException;

    boolean agregar(Asiento asiento) throws PersistenciaException, SQLException;

    List<Asiento> consultar() throws PersistenciaException;

    Asiento consultar(int id) throws PersistenciaException;

    boolean crearAsientos(int numeroFilas, int numeroAsientosPorFila, int idEvento) throws PersistenciaException;

    boolean eliminar(int id) throws PersistenciaException;

    // Método para consultar asientos por evento
    List<Asiento> consultarPorEvento(int idEvento) throws PersistenciaException;

    void asignarAsientosDAO(List<Asiento> asientosSeleccionados) throws PersistenciaException;

    
    
}
