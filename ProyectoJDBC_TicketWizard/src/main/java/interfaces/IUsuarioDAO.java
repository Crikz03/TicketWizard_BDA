/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Usuario;

/**
 *
 * @author pauli
 */
public interface IUsuarioDAO {

    boolean actualizar(Usuario usuario) throws PersistenciaException;

    boolean agregar(Usuario usuario) throws PersistenciaException;

    List<Usuario> consultar() throws PersistenciaException;

    Usuario consultar(int id) throws PersistenciaException;

}
