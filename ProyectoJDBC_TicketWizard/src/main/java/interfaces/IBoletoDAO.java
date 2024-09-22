/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Boleto;

/**
 *
 * @author pauli
 */
public interface IBoletoDAO {

    boolean agregar(Boleto boleto) throws PersistenciaException;

    boolean eliminar(int id) throws PersistenciaException;

    boolean actualizar(Boleto boleto) throws PersistenciaException;

    Boleto consultar(int id) throws PersistenciaException;

    List<Boleto> consultar() throws PersistenciaException;

   
}
