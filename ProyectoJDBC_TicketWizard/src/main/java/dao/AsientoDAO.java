/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.PersistenciaException;
import interfaces.IAsientoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Asiento;

/**
 *
 * @author pauli
 */
public class AsientoDAO implements IAsientoDAO {

    private IConexion conexion;

    public AsientoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Asiento asiento) throws PersistenciaException, SQLException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Asientos(asiento, fila, id_usuario) VALUES (?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setInt(1, asiento.getAsiento());
            agregar.setString(2, asiento.getFila());
            agregar.setInt(3, asiento.getIdUsuario());

            agregar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el asiento con id: " + asiento.getIdAsiento());
        }
        return true;
    }

    public boolean eliminar(int id) throws PersistenciaException {
        String eliminarAsiento = "DELETE FROM Asientos WHERE id_asiento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarAsiento)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo eliminar el asiento con id: " + id);
        }
        return true;
    }

    public boolean actualizar(Asiento asiento) throws PersistenciaException {
        String actualizarAsiento = "UPDATE Asientos SET asiento = ?, fila = ?, id_usuario = ? WHERE id_asiento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarAsiento)) {
            actualizar.setInt(1, asiento.getAsiento());
            actualizar.setString(2, asiento.getFila());
            actualizar.setInt(3, asiento.getIdUsuario());
            actualizar.setInt(4, asiento.getIdAsiento());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el asiento con id: " + asiento.getIdAsiento());
        }
        return true;
    }

    public Asiento consultar(int id) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String buscarAsiento = "SELECT * FROM Asientos WHERE id_asiento = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarAsiento);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Asiento asiento = new Asiento();
                asiento.setIdAsiento(resultado.getInt("id_asiento"));
                asiento.setAsiento(resultado.getInt("asiento"));
                asiento.setFila(resultado.getString("fila"));
                asiento.setIdUsuario(resultado.getInt("id_usuario"));
                return asiento;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar el asiento con id: " + id);
        }
        return null;
    }

    public List<Asiento> consultar() throws PersistenciaException {
        List<Asiento> listaAsientos = new ArrayList<>();
        String consultarAsientos = "SELECT * FROM Asientos";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarAsientos); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Asiento asiento = new Asiento();
                asiento.setIdAsiento(resultados.getInt("id_asiento"));
                asiento.setAsiento(resultados.getInt("asiento"));
                asiento.setFila(resultados.getString("fila"));
                asiento.setIdUsuario(resultados.getInt("id_usuario"));
                listaAsientos.add(asiento);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron encontrar los asientos.");
        }
        return listaAsientos;
    }

    public boolean crearAsientos(int numeroFilas, int numeroAsientosPorFila, int idEvento) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Asientos(asiento, fila, id_usuario, id_evento) VALUES (?, ?, ?, ?)";

            for (int i = 0; i < numeroFilas; i++) {
                String fila = convertirAFormatoLetra(i); // Genera fila como A, B, ..., Z, AA, AB, ...
                for (int j = 1; j <= numeroAsientosPorFila; j++) {
                    PreparedStatement agregar = bd.prepareStatement(insertar);
                    agregar.setInt(1, j); // Asiento numerado
                    agregar.setString(2, fila); // Fila como letra (A-Z, AA-ZZ, etc.)
                    agregar.setNull(3, java.sql.Types.INTEGER); // id_usuario, si no es necesario, se puede poner null
                    agregar.setInt(4, idEvento); // Asigna el id_evento

                    agregar.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron crear los asientos.");
        }
        return true;
    }

// Método para convertir un número en una representación de letras (A-Z, AA-ZZ, etc.)
    private String convertirAFormatoLetra(int numeroFila) {
        StringBuilder fila = new StringBuilder();

        while (numeroFila >= 0) {
            fila.insert(0, (char) ('A' + (numeroFila % 26)));  // Convertir el módulo del número a una letra
            numeroFila = (numeroFila / 26) - 1;  // Reducir el número y continuar con la siguiente letra
        }

        return fila.toString();
    }

// Método para consultar asientos por evento
    public List<Asiento> consultarPorEvento(int idEvento) throws PersistenciaException {
        List<Asiento> asientos = new ArrayList<>();
        String sql = "SELECT * FROM Asientos WHERE id_evento = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement statement = bd.prepareStatement(sql)) {

            statement.setInt(1, idEvento);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Asiento asiento = new Asiento();
                asiento.setIdAsiento(resultSet.getInt("id_asiento")); // Asigna el id correcto
                asiento.setFila(resultSet.getString("fila"));
                asiento.setAsiento(resultSet.getInt("asiento"));
                asiento.setIdUsuario(resultSet.getInt("id_usuario"));
                // Asegúrate de que el campo id_evento exista
                asiento.setIdEvento(idEvento);
                asientos.add(asiento);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los asientos", e);
        }

        return asientos;
    }

    public void asignarAsientosDAO(List<Asiento> asientosSeleccionados) throws PersistenciaException {
        String query = "UPDATE Asientos SET id_usuario = ? WHERE asiento = ? AND fila = ? AND id_evento = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement preparedStatement = bd.prepareStatement(query)) {
            // Itera sobre la lista de asientos seleccionados
            for (Asiento asiento : asientosSeleccionados) {
                preparedStatement.setInt(1, asiento.getIdUsuario()); 
                preparedStatement.setInt(2, asiento.getAsiento());
                preparedStatement.setString(3, asiento.getFila());
                preparedStatement.setInt(4, asiento.getIdEvento());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new PersistenciaException("No se pudo actualizar el asiento " + asiento.getAsiento() + " en la fila " + asiento.getFila());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al asignar asientos: " + e.getMessage());
        }
    }
}
