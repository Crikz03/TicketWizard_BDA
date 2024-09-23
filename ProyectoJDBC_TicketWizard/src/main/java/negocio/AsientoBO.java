/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.AsientoDAO;
import dtos.AsientoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IAsientoBO;
import interfaces.IAsientoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import objetos.Asiento;

/**
 *
 * @author pauli
 */
public class AsientoBO implements IAsientoBO {

    private IAsientoDAO asientoDAO;
    private IConexion conexion;

    public AsientoBO() {
        this.conexion = new ConexionBD();
        this.asientoDAO = new AsientoDAO(conexion);
    }

    public boolean agregar(AsientoDTO asiento) throws NegocioException, PersistenciaException, SQLException {
        try {
            asientoDAO.agregar(ConvertidorGeneral.convertidorEntidad(asiento, Asiento.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo agregar el asiento con id:" + asiento.getIdAsiento());
        }
    }

    public boolean eliminar(int id) throws NegocioException {
        try {
            asientoDAO.eliminar(id);
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo eliminar el asiento con id:" + id);
        }
    }

    public boolean actualizar(AsientoDTO asiento) throws NegocioException {
        try {
            asientoDAO.actualizar(ConvertidorGeneral.convertidorEntidad(asiento, Asiento.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el asiento con id:" + asiento.getIdAsiento());
        }
    }

    public List<AsientoDTO> consultarPorEvento(int idEvento) throws NegocioException {
        try {
            List<Asiento> asientos = asientoDAO.consultarPorEvento(idEvento);
            List<AsientoDTO> asientosDTO = ConvertidorGeneral.convertidoraListaDTO(asientos, AsientoDTO.class);
            return asientosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los asientos.");
        }
    }

    public AsientoDTO consultar(int id) throws NegocioException {
        try {
            Asiento asiento = asientoDAO.consultar(id);
            AsientoDTO asientoDTO = ConvertidorGeneral.convertidoraDTO(asiento, AsientoDTO.class);
            return asientoDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar el id: " + id);
        }
    }

    public List<AsientoDTO> consultar() throws NegocioException {
        try {
            List<Asiento> asientos = asientoDAO.consultar();
            List<AsientoDTO> asientosDTO = ConvertidorGeneral.convertidoraListaDTO(asientos, AsientoDTO.class);
            return asientosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los asientos.");
        }
    }

    // Método adicional para comprar asiento si es necesario
    public boolean comprarAsiento(int idAsiento, int idUsuario) throws NegocioException {
        try {
            Connection bd = conexion.crearConexion();
            String procedimiento = "{CALL ComprarAsiento(?, ?)}"; // Procedimiento correspondiente
            PreparedStatement stmt = bd.prepareStatement(procedimiento);

            stmt.setInt(1, idAsiento);
            stmt.setInt(2, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean crearAsientos(int numeroFilas, int numeroAsientosPorFila, int idEvento) throws NegocioException {
        try {
            return asientoDAO.crearAsientos(numeroFilas, numeroAsientosPorFila, idEvento);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron crear los asientos: " + e.getMessage());
        }
    }

    public void asignarAsientos(List<Asiento> asientosSeleccionados) throws PersistenciaException {
        // Validar que la lista de asientos no esté vacía
        if (asientosSeleccionados == null || asientosSeleccionados.isEmpty()) {
            throw new PersistenciaException("No hay asientos seleccionados para asignar.");
        }

        // Llamar al método del DAO para asignar los asientos
        asientoDAO.asignarAsientosDAO(asientosSeleccionados);
    }

}
