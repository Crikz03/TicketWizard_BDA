/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Domicilio;

/**
 *
 * @author pauli
 */
public interface IDomicilioDAO {

    boolean actualizar(Domicilio domicilio);

    boolean agregar(Domicilio domicilio);

    List<Domicilio> consultar();

    Domicilio consultar(int id);

    boolean eliminar(int id);
    
}
