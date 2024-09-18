/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Transaccion;
import objetos.Usuario;

/**
 *
 * @author pauli
 */
public interface ITransaccionDAO {

    boolean actualizar(Transaccion transaccion) throws PersistenciaException;

    boolean agregar(Transaccion transaccion) throws PersistenciaException;

    List<Transaccion> consultar() throws PersistenciaException;

    Transaccion consultar(int num_transaccion) throws PersistenciaException;

    boolean eliminar(int num_transaccion) throws PersistenciaException;
    
    boolean agregarSaldo(Usuario usuario, double monto) throws PersistenciaException;

}
