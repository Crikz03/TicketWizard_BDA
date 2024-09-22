/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Chris
 */
public interface IUsuarioBO {

    boolean agregar(UsuarioDTO usuario) throws NegocioException;

    boolean actualizar(UsuarioDTO usuario) throws NegocioException;

    List<UsuarioDTO> consultar() throws NegocioException;

    UsuarioDTO consultarPorCorreo(String correo) throws NegocioException;

    UsuarioDTO consultar(int id) throws NegocioException;

    boolean existeCorreo(String correo) throws NegocioException;

    double obtenerSaldoActual(int idUsuario) throws NegocioException;

    boolean puedeAgregarSaldo(UsuarioDTO usuario, double montoAgregar) throws NegocioException;
}
