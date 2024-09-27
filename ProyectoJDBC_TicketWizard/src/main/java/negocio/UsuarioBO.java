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
 * Clase que implementa la lógica de negocio para la gestión de usuarios. Se
 * encarga de interactuar con el DAO para realizar operaciones sobre los
 * usuarios en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class UsuarioBO implements IUsuarioBO {

    private IUsuarioDAO usuariodao; // Interfaz para las operaciones de persistencia de usuarios
    private IConexion conexion; // Interfaz para la conexión a la base de datos

    public UsuarioBO() {
        this.conexion = new ConexionBD(); // Inicializa la conexión a la base de datos
        this.usuariodao = new UsuarioDAO(conexion); // Inicializa el DAO de usuarios
    }

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuario El usuario a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al agregar el usuario.
     */
    @Override
    public boolean agregar(UsuarioDTO usuario) throws NegocioException {
        try {
            usuariodao.agregar(ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo agregar el usuario con correo: " + usuario.getCorreo());
        }
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param usuario El usuario con los datos actualizados.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al actualizar el usuario.
     */
    @Override
    public boolean actualizar(UsuarioDTO usuario) throws NegocioException {
        try {
            usuariodao.actualizar(ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el usuario con id: " + usuario.getIdUsuario());
        }
    }

    /**
     * Consulta todos los usuarios registrados.
     *
     * @return Una lista de objetos UsuarioDTO que representan los usuarios
     * registrados.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public List<UsuarioDTO> consultar() throws NegocioException {
        try {
            List<Usuario> usuarios = usuariodao.consultar();
            List<UsuarioDTO> usuariosdto = ConvertidorGeneral.convertidoraListaDTO(usuarios, UsuarioDTO.class);
            return usuariosdto; // Retorna la lista de UsuarioDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los usuarios registrados.");
        }
    }

    /**
     * Consulta un usuario por su correo electrónico.
     *
     * @param correo El correo del usuario a consultar.
     * @return El objeto UsuarioDTO correspondiente al correo.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public UsuarioDTO consultarPorCorreo(String correo) throws NegocioException {
        try {
            Usuario usuario = usuariodao.consultarPorCorreo(correo);
            UsuarioDTO usuariodto = ConvertidorGeneral.convertidoraDTO(usuario, UsuarioDTO.class);
            return usuariodto; // Retorna el UsuarioDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar al usuario con el correo: " + correo);
        }
    }

    /**
     * Consulta un usuario por su ID.
     *
     * @param id El ID del usuario a consultar.
     * @return El objeto UsuarioDTO correspondiente al ID.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public UsuarioDTO consultar(int id) throws NegocioException {
        try {
            Usuario usuario = usuariodao.consultar(id);
            UsuarioDTO usuariodto = ConvertidorGeneral.convertidoraDTO(usuario, UsuarioDTO.class);
            return usuariodto; // Retorna el UsuarioDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar al usuario con el id: " + id);
        }
    }

    /**
     * Verifica si un correo electrónico ya existe en la base de datos.
     *
     * @param correo El correo a verificar.
     * @return true si el correo existe, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la verificación.
     */
    @Override
    public boolean existeCorreo(String correo) throws NegocioException {
        try {
            return usuariodao.existeCorreo(correo); // Retorna el resultado de la verificación.
        } catch (PersistenciaException e) {
            throw new NegocioException("No existe el correo ingresado, intentelo de nuevo.");
        }
    }

    /**
     * Obtiene el saldo actual de un usuario.
     *
     * @param idUsuario El ID del usuario cuyo saldo se obtendrá.
     * @return El saldo actual del usuario.
     * @throws NegocioException Si ocurre un error al obtener el saldo.
     */
    @Override
    public double obtenerSaldoActual(int idUsuario) throws NegocioException {
        try {
            double saldo = usuariodao.obtenerSaldoActual(idUsuario);
            return saldo; // Retorna el saldo actual.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo obtener el saldo actual del usuario con id: " + idUsuario);
        }
    }

    /**
     * Verifica si un usuario puede agregar más saldo.
     *
     * @param usuario El usuario a verificar.
     * @param montoAgregar El monto a agregar.
     * @return true si puede agregar saldo, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la verificación.
     */
    @Override
    public boolean puedeAgregarSaldo(UsuarioDTO usuario, double montoAgregar) throws NegocioException {
        try {
            Usuario u = usuariodao.consultar(usuario.getIdUsuario());
            usuariodao.puedeAgregarSaldo(u, montoAgregar);
            return true; // Retorna verdadero si puede agregar saldo.
        } catch (PersistenciaException e) {
            throw new NegocioException("El usuario no tiene permiso para agregar más saldo.");
        }
    }

}
