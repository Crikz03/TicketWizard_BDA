/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Usuario;

/**
 *
 * @author pauli
 */
public interface IUsuarioDAO {

    boolean actualizar(Usuario usuario);

    boolean agregar(Usuario usuario);

    List<Usuario> consultar();

    Usuario consultar(int id);

    boolean eliminar(int id);
    
}
