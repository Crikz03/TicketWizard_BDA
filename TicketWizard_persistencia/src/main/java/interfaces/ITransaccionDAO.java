/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Transaccion;

/**
 *
 * @author pauli
 */
public interface ITransaccionDAO {

    boolean actualizar(Transaccion transaccion);

    boolean agregar(Transaccion transaccion);

    List<Transaccion> consultar();

    Transaccion consultar(int id);

    boolean eliminar(int id);
    
}
