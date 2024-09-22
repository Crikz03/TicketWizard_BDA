/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.TransaccionDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Chris
 */
public interface ITransaccionBO {

    boolean actualizar(TransaccionDTO transaccion) throws NegocioException;

    boolean agregar(TransaccionDTO transaccion) throws NegocioException;

    List<TransaccionDTO> consultar() throws NegocioException;

    TransaccionDTO consultar(int num_transaccion) throws NegocioException;
    
    List<TransaccionDTO> consultarIdUsuario(int idUsuario) throws NegocioException;

    boolean eliminar(int num_transaccion) throws NegocioException;

    boolean agregarSaldo(UsuarioDTO usuario, double monto) throws NegocioException;
}
