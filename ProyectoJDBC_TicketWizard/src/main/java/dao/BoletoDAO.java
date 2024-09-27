/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.PersistenciaException;
import interfaces.IBoletoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import objetos.Boleto;
import utilidades.EstadoAdquisicion;
import utilidades.TipoTransaccion;

/**
 *
 * @author pauli
 */
public class BoletoDAO implements IBoletoDAO {

    private IConexion conexion;

    public BoletoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Boleto boleto) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Boletos(num_serie, fila, asiento, precioOriginal, estado_adquisicion, id_usuario, id_evento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, boleto.getNumSerie());
            agregar.setString(2, boleto.getFila());
            agregar.setString(3, boleto.getAsiento());
            agregar.setDouble(4, boleto.getPrecioOriginal());
            agregar.setObject(5, boleto.getEstadoAdquisicion());
            agregar.setInt(6, boleto.getIdUsuario());
            agregar.setInt(7, boleto.getIdEvento());

            agregar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el boleto con id: " + boleto.getIdBoleto());
        }
        return true;
    }

    public boolean eliminar(int id) throws PersistenciaException {
        String eliminarBoleto = "DELETE FROM Boletos WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarBoleto)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo eliminar el boleto con id: " + id);
        }
        return true;
    }

    public boolean actualizar(Boleto boleto) throws PersistenciaException {
        String actualizarBoleto = "UPDATE Boletos SET num_serie = ?, fila = ?, asiento = ?, precioOriginal = ?, estado_adquisicion = ?, id_usuario = ?, id_evento = ? WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarBoleto)) {
            actualizar.setString(1, boleto.getNumSerie());
            actualizar.setString(2, boleto.getFila());
            actualizar.setString(3, boleto.getAsiento());
            actualizar.setDouble(4, boleto.getPrecioOriginal());
            actualizar.setObject(5, boleto.getEstadoAdquisicion());
            actualizar.setInt(6, boleto.getIdUsuario());
            actualizar.setInt(7, boleto.getIdEvento());
            actualizar.setInt(8, boleto.getIdBoleto());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el boleto con id: " + boleto.getIdBoleto());
        }
        return true;
    }

    public Boleto consultar(int id) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String buscarBoleto = "SELECT * FROM Boletos WHERE id_boleto = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarBoleto);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Boleto b = new Boleto();
                b.setIdBoleto(resultado.getInt("id_boleto"));
                b.setNumSerie(resultado.getString("num_serie"));
                b.setFila(resultado.getString("fila"));
                b.setAsiento(resultado.getString("asiento"));
                b.setPrecioOriginal(resultado.getDouble("precioOriginal"));
                String estadoAdquisicionStr = resultado.getString("estado_adquisicion");
                b.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionStr));
                b.setIdUsuario(resultado.getInt("id_usuario"));
                b.setIdEvento(resultado.getInt("id_evento"));
                b.setApartado(resultado.getBoolean("apartado"));
                b.setEn_venta(resultado.getBoolean("en_venta"));
                return b;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar el boleto con id: " + id);
        }
        return null;
    }

    public List<Boleto> consultar() throws PersistenciaException {
        List<Boleto> listaBoletos = new ArrayList<>();
        String consultarBoletos = "SELECT * FROM Boletos";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarBoletos); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Boleto b = new Boleto();
                b.setIdBoleto(resultados.getInt("id_boleto"));
                b.setNumSerie(resultados.getString("num_serie"));
                b.setFila(resultados.getString("fila"));
                b.setAsiento(resultados.getString("asiento"));
                b.setPrecioOriginal(resultados.getDouble("precioOriginal"));
                String estadoAdquisicionString = resultados.getString("estado_adquisicion");
                b.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionString));
                b.setIdUsuario(resultados.getInt("id_usuario"));
                b.setIdEvento(resultados.getInt("id_evento"));
                b.setApartado(resultados.getBoolean("apartado"));
                b.setEn_venta(resultados.getBoolean("en_venta"));
                listaBoletos.add(b);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos");
        }
        return listaBoletos;
    }

    public List<Boleto> consultarIdUsuario(int idUsuario) throws PersistenciaException {
        List<Boleto> listaBoletos = new ArrayList<>();
        String consultarBoletos = "SELECT * FROM Boletos WHERE id_usuario = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarBoletos)) {

            // Establecer el valor de idUsuario en el PreparedStatement
            consulta.setInt(1, idUsuario);

            try (ResultSet resultados = consulta.executeQuery()) {
                while (resultados.next()) {
                    Boleto b = new Boleto();
                    b.setIdBoleto(resultados.getInt("id_boleto"));
                    b.setNumSerie(resultados.getString("num_serie"));
                    b.setFila(resultados.getString("fila"));
                    b.setAsiento(resultados.getString("asiento"));
                    b.setPrecioOriginal(resultados.getDouble("precioOriginal"));
                    b.setPrecioReventa(resultados.getDouble("precioReventa"));

                    // Convertir el estado de adquisición de String a Enum
                    String estadoAdquisicionString = resultados.getString("estado_adquisicion");
                    b.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionString));

                    b.setIdUsuario(resultados.getInt("id_usuario"));
                    b.setIdEvento(resultados.getInt("id_evento"));
                    b.setApartado(resultados.getBoolean("apartado"));
                    b.setEn_venta(resultados.getBoolean("en_venta"));
                    listaBoletos.add(b);
                }
            }

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos del usuario con id: " + idUsuario, e);
        }

        return listaBoletos;
    }

    public List<Boleto> consultarAsignados() throws PersistenciaException {
        List<Boleto> listaBoletos = new ArrayList<>();
        String consultarBoletos = "SELECT * FROM boletos WHERE num_serie IS NOT NULL AND id_usuario IS NOT NULL";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarBoletos); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Boleto b = new Boleto();
                b.setIdBoleto(resultados.getInt("id_boleto"));
                b.setNumSerie(resultados.getString("num_serie"));
                b.setFila(resultados.getString("fila"));
                b.setAsiento(resultados.getString("asiento"));
                b.setPrecioOriginal(resultados.getDouble("precioOriginal"));
                String estadoAdquisicionString = resultados.getString("estado_adquisicion");
                b.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionString));
                b.setIdUsuario(resultados.getInt("id_usuario"));
                b.setIdEvento(resultados.getInt("id_evento"));
                listaBoletos.add(b);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos");
        }
        return listaBoletos;
    }

    public boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO boletos(asiento, fila, id_usuario, id_evento, precioOriginal, estado_adquisicion, en_venta,apartado) VALUES (?, ?, ?, ?,?,?,?,?)";

            for (int i = 0; i < numeroFilas; i++) {
                String fila = convertirAFormatoLetra(i); // Genera fila como A, B, ..., Z, AA, AB, ...
                for (int j = 1; j <= numeroAsientosPorFila; j++) {
                    PreparedStatement agregar = bd.prepareStatement(insertar);
                    agregar.setString(1, String.valueOf(j)); // Asiento numerado
                    agregar.setString(2, fila); // Fila como letra (A-Z, AA-ZZ, etc.)
                    agregar.setNull(3, java.sql.Types.INTEGER); // id_usuario, si no es necesario, se puede poner null
                    agregar.setInt(4, idEvento); // Asigna el id_evento
                    agregar.setDouble(5, precio);
                    agregar.setString(6, "directo");
                    agregar.setBoolean(7, true);
                    agregar.setBoolean(8, false);
                    agregar.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron crear los asientos.");
        }
        return true;
    }

    private String convertirAFormatoLetra(int numeroFila) {
        StringBuilder fila = new StringBuilder();

        while (numeroFila >= 0) {
            fila.insert(0, (char) ('A' + (numeroFila % 26)));  // Convertir el módulo del número a una letra
            numeroFila = (numeroFila / 26) - 1;  // Reducir el número y continuar con la siguiente letra
        }

        return fila.toString();
    }

    public void asignarBoletosDAO(List<Boleto> boletosSeleccionados) throws PersistenciaException {
        String query = "UPDATE boletos SET id_usuario = ?, num_serie = ? WHERE asiento = ? AND fila = ? AND id_evento = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement preparedStatement = bd.prepareStatement(query)) {
            // Itera sobre la lista de asientos seleccionados
            for (Boleto boleto : boletosSeleccionados) {
                preparedStatement.setInt(1, boleto.getIdUsuario());
                String numSerie = generarNumeroSerieUnico(bd);
                preparedStatement.setString(2, numSerie);
                preparedStatement.setString(3, boleto.getAsiento());
                preparedStatement.setString(4, boleto.getFila());
                preparedStatement.setInt(5, boleto.getIdEvento());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new PersistenciaException("No se pudo actualizar el asiento " + boleto.getAsiento() + " en la fila " + boleto.getFila());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al asignar boletos: " + e.getMessage());
        }
    }
    // Método para generar un número de serie único de 8 caracteres alfanuméricos

    private String generarNumeroSerie() {
        int longitud = 8;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder numSerie = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            numSerie.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }

        return numSerie.toString();
    }

// Método para verificar que el num_serie sea único en la base de datos
    private String generarNumeroSerieUnico(Connection bd) throws SQLException {
        String numSerie;
        boolean existe;

        do {
            numSerie = generarNumeroSerie(); // Genera un número de serie
            existe = verificarNumeroSerieExiste(bd, numSerie); // Verifica si ya existe en la BD
        } while (existe);

        return numSerie;
    }

// Método que verifica si el num_serie ya existe en la base de datos
    private boolean verificarNumeroSerieExiste(Connection bd, String numSerie) throws SQLException {
        String query = "SELECT COUNT(*) FROM boletos WHERE num_serie = ?";
        try (PreparedStatement statement = bd.prepareStatement(query)) {
            statement.setString(1, numSerie);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Si el conteo es mayor a 0, el num_serie ya existe
                }
            }
        }
        return false;
    }

    public List<Boleto> consultarPorEvento(int idEvento) throws PersistenciaException {
        List<Boleto> boletos = new ArrayList<>();
        String sql = "SELECT * FROM boletos WHERE id_evento = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement statement = bd.prepareStatement(sql)) {

            statement.setInt(1, idEvento);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Boleto boleto = new Boleto();
                boleto.setIdBoleto(resultSet.getInt("id_boleto"));
                boleto.setFila(resultSet.getString("fila"));
                boleto.setAsiento(resultSet.getString("asiento"));
                boleto.setPrecioOriginal(resultSet.getDouble("precioOriginal"));
                boleto.setIdUsuario(resultSet.getInt("id_usuario"));
                boleto.setApartado(resultSet.getBoolean("apartado"));
                boleto.setEn_venta(resultSet.getBoolean("en_venta"));
                boleto.setPrecioReventa(resultSet.getDouble("precioReventa"));
                // Asegúrate de que el campo id_evento exista
                boleto.setIdEvento(idEvento);
                boletos.add(boleto);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los boletos", e);
        }

        return boletos;
    }

    @Override
    public boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario, int idUsuarioAnteriorDueño) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String procedimiento = "{CALL ComprarBoleto(?, ?, ?, ?, ?, ?)}";
            PreparedStatement stmt = bd.prepareStatement(procedimiento);

            stmt.setInt(1, idBoleto);
            stmt.setString(2, generarNumeroSerieUnico(bd));
            stmt.setDouble(3, precio);
            stmt.setString(4, estadoAdquisicion.name());
            stmt.setString(5, tipoTransaccion.name());
            stmt.setInt(6, idUsuario);

            stmt.executeUpdate();

            if (estadoAdquisicion == EstadoAdquisicion.reventa) {
                double montoVenta = precio * 0.97;  // Restar el 3% de comisión
                String queryVenta = "INSERT INTO Transacciones (num_transaccion, monto, tipo, id_usuario) VALUES (?, ?, 'venta', ?)";
                PreparedStatement stmtVenta = bd.prepareStatement(queryVenta);

                // Generar el número de transacción (puedes adaptarlo según tu lógica actual)
                int numTransaccion = generarNumeroTransaccion(bd);
                stmtVenta.setInt(1, numTransaccion);
                stmtVenta.setDouble(2, montoVenta);
                stmtVenta.setInt(3, idUsuarioAnteriorDueño);
                stmtVenta.executeUpdate();

                // Insertar el detalle de la transacción en Detalles_BoletoTransaccion
                String queryDetalle = "INSERT INTO Detalles_BoletoTransaccion (id_boleto, num_transaccion, estado) VALUES (?, ?, ?)";
                PreparedStatement stmtDetalle = bd.prepareStatement(queryDetalle);
                stmtDetalle.setInt(1, idBoleto);
                stmtDetalle.setInt(2, numTransaccion);
                stmtDetalle.setString(3, "Completado");  // Estado de la transacción
                stmtDetalle.executeUpdate();
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al comprar los boletos", e);
        }
        return true;
    }

    private int generarNumeroTransaccion(Connection bd) throws SQLException {
        String query = "SELECT COALESCE(MAX(num_transaccion), 0) + 1 FROM Transacciones";
        try (PreparedStatement stmt = bd.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 1;  // Retorna 1 si no hay transacciones previas
    }

    public void apartarBoleto(int idBoleto, int idUsuario) throws PersistenciaException {
        String updateQuery = "UPDATE Boletos SET apartado = TRUE WHERE id_boleto = ?";
        String insertQuery = "INSERT INTO Apartados (id_usuario, id_boleto) VALUES (?, ?)";

        try (Connection bd = conexion.crearConexion(); PreparedStatement updateStmt = bd.prepareStatement(updateQuery); PreparedStatement insertStmt = bd.prepareStatement(insertQuery)) {

            // Actualizar el estado del boleto a apartado
            updateStmt.setInt(1, idBoleto);
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenciaException("No se pudo apartar el boleto con ID " + idBoleto);
            }

            // Insertar en la tabla Apartados
            insertStmt.setInt(1, idUsuario);
            insertStmt.setInt(2, idBoleto);
            insertStmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("Error al apartar el boleto: " + e.getMessage());
        }
    }

    public void liberarBoleto(int idBoleto) throws PersistenciaException {
        String selectQuery = "SELECT apartado FROM Boletos WHERE id_boleto = ?";
        String updateQuery = "UPDATE Boletos SET apartado = FALSE WHERE id_boleto = ?";
        String deleteQuery = "DELETE FROM Apartados WHERE id_boleto = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement selectStmt = bd.prepareStatement(selectQuery); PreparedStatement updateStmt = bd.prepareStatement(updateQuery); PreparedStatement deleteStmt = bd.prepareStatement(deleteQuery)) {

            selectStmt.setInt(1, idBoleto);
            try (ResultSet resultSet = selectStmt.executeQuery()) {
                if (resultSet.next()) {
                    boolean estaApartado = resultSet.getBoolean("apartado");

                    if (!estaApartado) {
                        System.out.println("El boleto con ID " + idBoleto + " no está apartado.");
                        return;
                    }
                } else {
                    throw new PersistenciaException("No se encontró el boleto con ID " + idBoleto);
                }
            }

            updateStmt.setInt(1, idBoleto);
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenciaException("No se pudo liberar el boleto con ID " + idBoleto);
            }

            deleteStmt.setInt(1, idBoleto);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("Error al liberar el boleto: " + e.getMessage());
        }
    }

    public boolean revenderBoleto(int idBoleto, double precioReventa, Date fechaLimite, int idUsuario) throws PersistenciaException {
        try (Connection bd = conexion.crearConexion()) {
            String sql = "{CALL ReventaBoleto(?, ?, ?, ?)}";
            PreparedStatement stmt = bd.prepareStatement(sql);

            stmt.setInt(1, idBoleto);
            stmt.setDouble(2, precioReventa);
            stmt.setDate(3, new java.sql.Date(fechaLimite.getTime()));
            stmt.setInt(4, idUsuario);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al realizar la reventa", e);
        }
    }

    public double obtenerPrecioOriginal(String numSerie) throws PersistenciaException {
        Connection bd = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double precioOriginal = 0.0;

        try {
            bd = conexion.crearConexion();
            String sql = "SELECT precioOriginal FROM Boletos WHERE numSerie = ?";
            stmt = bd.prepareStatement(sql);
            stmt.setString(1, numSerie);
            rs = stmt.executeQuery();

            if (rs.next()) {
                precioOriginal = rs.getDouble("precioOriginal");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el precio original del boleto.", e);
        }
        return precioOriginal;
    }

    public List<Boleto> consultarBoletosEnVenta(int idUsuario) throws PersistenciaException {
        List<Boleto> boletosEnVenta = new ArrayList<>();
        String sql = "SELECT * FROM Boletos WHERE id_usuario = ? AND en_venta = TRUE"; // Ajusta la condición según tu lógica

        try (Connection conn = this.conexion.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Boleto boleto = new Boleto();
                    boleto.setIdBoleto(rs.getInt("id_boleto"));
                    boleto.setNumSerie(rs.getString("num_serie"));
                    boleto.setFila(rs.getString("fila"));
                    boleto.setAsiento(rs.getString("asiento"));
                    boleto.setPrecioReventa(rs.getDouble("precioReventa"));
                    String estadoAdquisicionString = rs.getString("estado_adquisicion");
                    boleto.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionString));
                    boleto.setEn_venta(rs.getBoolean("en_venta"));
                    boleto.setIdEvento(rs.getInt("id_evento"));

                    boletosEnVenta.add(boleto);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los boletos en venta", e);
        }
        return boletosEnVenta;
    }

    public boolean actualizarBoletoParaReventa(int idBoleto) throws PersistenciaException {
        String updateQuery = "UPDATE Boletos SET precioReventa = 0, en_venta = FALSE WHERE id_boleto = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement updateStmt = bd.prepareStatement(updateQuery)) {

            // Establecer el ID del boleto en la consulta
            updateStmt.setInt(1, idBoleto);

            // Ejecutar la actualización
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Boleto con ID " + idBoleto + " actualizado correctamente.");
                return true; // Indica que la actualización fue exitosa
            } else {
                System.out.println("No se encontró un boleto con ID " + idBoleto);
                return false; // Indica que no se encontró el boleto
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el boleto: " + e.getMessage());
        }
    }

}
