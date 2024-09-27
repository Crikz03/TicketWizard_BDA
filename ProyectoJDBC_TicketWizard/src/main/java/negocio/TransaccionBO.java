/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.TransaccionDAO;
import dtos.TransaccionDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IConexion;
import interfaces.ITransaccionBO;
import interfaces.ITransaccionDAO;
import java.util.List;
import objetos.Transaccion;
import objetos.Usuario;

/**
 * Clase que implementa la lógica de negocio para la gestión de transacciones.
 * Se encarga de interactuar con el DAO para realizar operaciones sobre las
 * transacciones en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class TransaccionBO implements ITransaccionBO {

    private ITransaccionDAO transacciondao; // Interfaz para las operaciones de persistencia de transacciones
    private IConexion conexion; // Interfaz para la conexión a la base de datos

    public TransaccionBO() {
        this.conexion = new ConexionBD(); // Inicializa la conexión a la base de datos
        this.transacciondao = new TransaccionDAO(conexion); // Inicializa el DAO de transacciones
    }

    /**
     * Agrega una nueva transacción.
     *
     * @param transaccion La transacción a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al agregar la transacción.
     */
    @Override
    public boolean agregar(TransaccionDTO transaccion) throws NegocioException {
        try {
            transacciondao.agregar(ConvertidorGeneral.convertidorEntidad(transaccion, Transaccion.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido realizar la transacción con número: " + transaccion.getNumTransaccion());
        }
    }

    /**
     * Actualiza una transacción existente.
     *
     * @param transaccion La transacción con los datos actualizados.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al actualizar la transacción.
     */
    @Override
    public boolean actualizar(TransaccionDTO transaccion) throws NegocioException {
        try {
            transacciondao.actualizar(ConvertidorGeneral.convertidorEntidad(transaccion, Transaccion.class));
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido actualizar la transacción con número: " + transaccion.getNumTransaccion());
        }
    }

    /**
     * Elimina una transacción por su número.
     *
     * @param num_transaccion El número de la transacción a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al eliminar la transacción.
     */
    @Override
    public boolean eliminar(int num_transaccion) throws NegocioException {
        try {
            transacciondao.eliminar(num_transaccion);
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido eliminar la transacción con número: " + num_transaccion);
        }
    }

    /**
     * Consulta todas las transacciones disponibles.
     *
     * @return Una lista de objetos TransaccionDTO que representan las
     * transacciones disponibles.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public List<TransaccionDTO> consultar() throws NegocioException {
        try {
            List<Transaccion> transacciones = transacciondao.consultar();
            List<TransaccionDTO> transaccionesDTO = ConvertidorGeneral.convertidoraListaDTO(transacciones, TransaccionDTO.class);
            return transaccionesDTO; // Retorna la lista de TransaccionDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar las transacciones de los usuarios.");
        }
    }

    /**
     * Consulta una transacción por su número.
     *
     * @param num_transaccion El número de la transacción a consultar.
     * @return El objeto TransaccionDTO correspondiente al número.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public TransaccionDTO consultar(int num_transaccion) throws NegocioException {
        try {
            Transaccion transaccion = transacciondao.consultar(num_transaccion);
            TransaccionDTO transaccionDTO = ConvertidorGeneral.convertidoraDTO(transaccion, TransaccionDTO.class);
            return transaccionDTO; // Retorna el TransaccionDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar la transacción con el número: " + num_transaccion);
        }
    }

    /**
     * Consulta las transacciones de un usuario específico por su ID.
     *
     * @param idUsuario El ID del usuario cuyas transacciones se consultarán.
     * @return Una lista de objetos TransaccionDTO correspondientes a las
     * transacciones del usuario.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    @Override
    public List<TransaccionDTO> consultarIdUsuario(int idUsuario) throws NegocioException {
        try {
            List<Transaccion> transacciones = transacciondao.consultarIdUsuario(idUsuario);
            List<TransaccionDTO> transaccionesDTO = ConvertidorGeneral.convertidoraListaDTO(transacciones, TransaccionDTO.class);
            return transaccionesDTO; // Retorna la lista de TransaccionDTO.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar las transacciones del usuario con id: " + idUsuario);
        }
    }

    /**
     * Agrega saldo a la cuenta de un usuario.
     *
     * @param usuario El usuario al que se le agregará saldo.
     * @param monto El monto a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error al agregar saldo.
     */
    @Override
    public boolean agregarSaldo(UsuarioDTO usuario, double monto) throws NegocioException {
        try {
            Usuario u = ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class);
            transacciondao.agregarSaldo(u, monto);
            return true; // Retorna verdadero si la operación fue exitosa.
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido agregar saldo a la cuenta con id: " + usuario.getIdUsuario());
        }
    }

}
