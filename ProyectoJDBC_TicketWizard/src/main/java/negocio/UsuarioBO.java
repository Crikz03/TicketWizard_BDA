/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.UsuarioDAO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConexion;
import interfaces.IUsuarioBO;
import interfaces.IUsuarioDAO;
import java.util.List;
import objetos.Usuario;

/**
 *
 * @author Chris
 */
public class UsuarioBO implements IUsuarioBO {

    private IUsuarioDAO usuariodao;
    private IConexion conexion;

    public UsuarioBO() {
        this.conexion = new ConexionBD();
        this.usuariodao = new UsuarioDAO(conexion);
    }

    @Override
    public boolean agregar(UsuarioDTO usuario) throws NegocioException {
        try {
            usuariodao.agregar(ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo agregar el usuario con correo: " + usuario.getCorreo());
        }
    }

    @Override
    public boolean actualizar(UsuarioDTO usuario) throws NegocioException {
        try {
            usuariodao.actualizar(ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el usuario con id: " + usuario.getIdUsuario());
        }
    }

    @Override
    public List<UsuarioDTO> consultar() throws NegocioException {
        try {
            List<Usuario> usuarios = usuariodao.consultar();

            List<UsuarioDTO> usuariosdto = ConvertidorGeneral.convertidoraListaDTO(usuarios, UsuarioDTO.class);

            return usuariosdto;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se puedieron consultar los usuarios registrados.");
        }
    }

    @Override
    public UsuarioDTO consultarPorCorreo(String correo) throws NegocioException {
        try {
            Usuario usuario = usuariodao.consultarPorCorreo(correo);

            UsuarioDTO usuariodto = ConvertidorGeneral.convertidoraDTO(usuario, UsuarioDTO.class);

            return usuariodto;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar al usuario con el correo: " + correo);
        }
    }

    @Override
    public UsuarioDTO consultar(int id) throws NegocioException {
        try {
            Usuario usuario = usuariodao.consultar(id);

            UsuarioDTO usuariodto = ConvertidorGeneral.convertidoraDTO(usuario, UsuarioDTO.class);

            return usuariodto;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar al usuario con el id: " + id);
        }
    }

    @Override
    public boolean existeCorreo(String correo) throws NegocioException {
        try {
            return usuariodao.existeCorreo(correo);
        } catch (PersistenciaException e) {
            throw new NegocioException("No existe el correo ingresado, intentelo denuevo.");
        }
    }

    @Override
    public double obtenerSaldoActual(int idUsuario) throws NegocioException {
        try {
            double saldo = usuariodao.obtenerSaldoActual(idUsuario);
            return saldo;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo obtener el saldo actual del usuario con id: " + idUsuario);
        }
    }

    @Override
    public boolean puedeAgregarSaldo(UsuarioDTO usuario, double montoAgregar) throws NegocioException {
        try {
            Usuario u = usuariodao.consultar(usuario.getIdUsuario());

            usuariodao.puedeAgregarSaldo(u, montoAgregar);

            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("El usuario no tiene permiso para agregar mas saldo.");
        }
    }

}
