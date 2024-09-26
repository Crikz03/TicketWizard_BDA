/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBoletoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            String insertar = "INSERT INTO Boletos(num_serie, fila, asiento, precio, estado_adquisicion, id_usuario, id_evento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, boleto.getNumSerie());
            agregar.setString(2, boleto.getFila());
            agregar.setString(3, boleto.getAsiento());
            agregar.setDouble(4, boleto.getPrecio());
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
        String actualizarBoleto = "UPDATE Boletos SET num_serie = ?, fila = ?, asiento = ?, precio = ?, estado_adquisicion = ?, id_usuario = ?, id_evento = ? WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarBoleto)) {
            actualizar.setString(1, boleto.getNumSerie());
            actualizar.setString(2, boleto.getFila());
            actualizar.setString(3, boleto.getAsiento());
            actualizar.setDouble(4, boleto.getPrecio());
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
                b.setPrecio(resultado.getDouble("precio"));
                b.setEstadoAdquisicion((EstadoAdquisicion) resultado.getObject("estado_adquisicion"));
                b.setIdUsuario(resultado.getInt("id_usuario"));
                b.setIdEvento(resultado.getInt("id_evento"));
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
                b.setPrecio(resultados.getDouble("precio"));
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
                b.setPrecio(resultados.getDouble("precio"));
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
            String insertar = "INSERT INTO boletos(asiento, fila, id_usuario, id_evento, precio, estado_adquisicion) VALUES (?, ?, ?, ?,?,?)";

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
                boleto.setPrecio(resultSet.getDouble("precio"));
                boleto.setIdUsuario(resultSet.getInt("id_usuario"));
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
    public boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario) throws PersistenciaException {
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
        } catch (SQLException e) {
            throw new PersistenciaException("Error al comprar los boletos", e);
        }
        return true;
    }
}
