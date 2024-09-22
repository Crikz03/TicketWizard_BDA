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
 *
 * @author Chris
 */
public class TransaccionBO implements ITransaccionBO {

    private ITransaccionDAO transacciondao;
    private IConexion conexion;

    public TransaccionBO() {
        this.conexion = new ConexionBD();
        this.transacciondao = new TransaccionDAO(conexion);
    }

    @Override
    public boolean agregar(TransaccionDTO transaccion) throws NegocioException {
        try {
            transacciondao.agregar(ConvertidorGeneral.convertidorEntidad(transaccion, Transaccion.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido realizar la transaccion con numero: " + transaccion.getNumTransaccion());
        }
    }

    @Override
    public boolean actualizar(TransaccionDTO transaccion) throws NegocioException {
        try {
            transacciondao.actualizar(ConvertidorGeneral.convertidorEntidad(transaccion, Transaccion.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido actualizar la transaccion con numero: " + transaccion.getNumTransaccion());
        }
    }

    @Override
    public boolean eliminar(int num_transaccion) throws NegocioException {
        try {
            transacciondao.eliminar(num_transaccion);
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido eliminar la transaccion con numero: " + num_transaccion);
        }
    }

    @Override
    public List<TransaccionDTO> consultar() throws NegocioException {
        try {
            List<Transaccion> transacciones = transacciondao.consultar();

            List<TransaccionDTO> transaccionesDTO = ConvertidorGeneral.convertidoraListaDTO(transacciones, TransaccionDTO.class);

            return transaccionesDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar las transacciones de los usuarios.");
        }
    }

    @Override
    public TransaccionDTO consultar(int num_transaccion) throws NegocioException {
        try {
            Transaccion transaccion = transacciondao.consultar(num_transaccion);

            TransaccionDTO transaccionDTO = ConvertidorGeneral.convertidoraDTO(transaccion, TransaccionDTO.class);

            return transaccionDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar la transaccion con el numero: " + num_transaccion);
        }
    }

    @Override
    public List<TransaccionDTO> consultarIdUsuario(int idUsuario) throws NegocioException {
        try {
            List<Transaccion> transacciones = transacciondao.consultarIdUsuario(idUsuario);

            List<TransaccionDTO> transaccionesDTO = ConvertidorGeneral.convertidoraListaDTO(transacciones, TransaccionDTO.class);

            return transaccionesDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido consultar las transacciones del usuario con id: " + idUsuario);
        }
    }

    @Override
    public boolean agregarSaldo(UsuarioDTO usuario, double monto) throws NegocioException {
        try {
            Usuario u = ConvertidorGeneral.convertidorEntidad(usuario, Usuario.class);

            transacciondao.agregarSaldo(u, monto);

            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido agregar saldo a la cuenta con id: " + usuario.getIdUsuario());
        }
    }

}
