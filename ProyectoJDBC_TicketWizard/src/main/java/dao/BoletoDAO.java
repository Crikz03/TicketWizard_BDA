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
 * Clase que implementa la interfaz IBoletoDAO para realizar operaciones CRUD
 * relacionadas con los boletos en la base de datos. Se encarga de gestionar las
 * operaciones necesarias para la creación, lectura, actualización y eliminación
 * de boletos, así como para la reventa de boletos y consulta de precios
 * originales.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class BoletoDAO implements IBoletoDAO {

    private IConexion conexion; //La variable que contendra la conexion de la base de datos.

    /**
     * Constructor que recibe una implementación de la interfaz IConexion para
     * manejar la conexión a la base de datos.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos.
     */
    public BoletoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Agrega un nuevo boleto a la base de datos.
     *
     * @param boleto El objeto Boleto a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
            return true; // Se agregó correctamente
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el boleto con id: " + boleto.getIdBoleto(), e);
        }
    }

    /**
     * Elimina un boleto de la base de datos.
     *
     * @param id El ID del boleto a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean eliminar(int id) throws PersistenciaException {
        String eliminarBoleto = "DELETE FROM Boletos WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarBoleto)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
            return true; // Se eliminó correctamente
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo eliminar el boleto con id: " + id, e);
        }
    }

    /**
     * Actualiza los detalles de un boleto en la base de datos.
     *
     * @param boleto El objeto Boleto con los nuevos datos.
     * @return true si se actualizó correctamente, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
            return true; // Se actualizó correctamente
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el boleto con id: " + boleto.getIdBoleto(), e);
        }
    }

    /**
     * Consulta un boleto específico por su ID.
     *
     * @param id El ID del boleto a consultar.
     * @return El objeto Boleto encontrado, o null si no se encuentra.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
                return b; // Retorna el boleto encontrado
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar el boleto con id: " + id, e);
        }
        return null; // Si no se encuentra el boleto
    }

    /**
     * Consulta todos los boletos en la base de datos.
     *
     * @return Una lista de todos los boletos.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
                listaBoletos.add(b); // Agregar el boleto a la lista
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos", e);
        }
        return listaBoletos; // Retornar la lista de boletos
    }

    /**
     * Consulta todos los boletos de un usuario específico por su ID.
     *
     * @param idUsuario El ID del usuario cuyos boletos se desean consultar.
     * @return Una lista de boletos pertenecientes al usuario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
                    listaBoletos.add(b); // Agregar el boleto a la lista
                }
            }

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos del usuario con id: " + idUsuario, e);
        }

        return listaBoletos; // Retornar la lista de boletos encontrados
    }

    /**
     * Consulta todos los boletos que están asignados a un usuario.
     *
     * @return Una lista de boletos asignados.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
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
                listaBoletos.add(b); // Agregar el boleto a la lista
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos", e);
        }
        return listaBoletos; // Retornar la lista de boletos asignados
    }

    /**
     * Crea nuevos boletos para un evento específico, generando una serie de
     * asientos.
     *
     * @param numeroFilas El número de filas que se crearán.
     * @param numeroAsientosPorFila El número de asientos por fila.
     * @param idEvento El ID del evento al que pertenecen los boletos.
     * @param precio El precio original de los boletos.
     * @return true si los boletos se crearon correctamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO boletos(asiento, fila, id_usuario, id_evento, precioOriginal, estado_adquisicion, en_venta, apartado) VALUES (?, ?, ?, ?,?,?,?,?)";

            // Iterar sobre el número de filas para crear asientos
            for (int i = 0; i < numeroFilas; i++) {
                String fila = convertirAFormatoLetra(i); // Genera fila como A, B, ..., Z, AA, AB, ...
                for (int j = 1; j <= numeroAsientosPorFila; j++) {
                    PreparedStatement agregar = bd.prepareStatement(insertar);
                    agregar.setString(1, String.valueOf(j)); // Asiento numerado
                    agregar.setString(2, fila); // Fila como letra (A-Z, AA-ZZ, etc.)
                    agregar.setNull(3, java.sql.Types.INTEGER); // id_usuario, se establece como null si no hay usuario asignado
                    agregar.setInt(4, idEvento); // Asigna el id_evento
                    agregar.setDouble(5, precio); // Precio original del boleto
                    agregar.setString(6, "directo"); // Estado de adquisición
                    agregar.setBoolean(7, true); // Marcar como en venta
                    agregar.setBoolean(8, false); // Marcar como no apartado
                    agregar.executeUpdate(); // Ejecutar la inserción
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudieron crear los asientos.", e);
        }
        return true; // Retorna true si se crearon los boletos
    }

    /**
     * Convierte un número de fila en un formato de letra (A, B, C, ..., Z, AA,
     * AB, ...).
     *
     * @param numeroFila El número de fila a convertir.
     * @return La representación en letra de la fila.
     */
    private String convertirAFormatoLetra(int numeroFila) {
        StringBuilder fila = new StringBuilder();

        // Convertir el número de fila en letras
        while (numeroFila >= 0) {
            fila.insert(0, (char) ('A' + (numeroFila % 26)));  // Convertir el módulo del número a una letra
            numeroFila = (numeroFila / 26) - 1;  // Reducir el número y continuar con la siguiente letra
        }

        return fila.toString(); // Retornar la fila en formato de letra
    }

    /**
     * Asigna boletos seleccionados a un usuario, actualizando sus datos en la
     * base de datos.
     *
     * @param boletosSeleccionados Lista de boletos que se desean asignar.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public void asignarBoletosDAO(List<Boleto> boletosSeleccionados) throws PersistenciaException {
        String query = "UPDATE boletos SET id_usuario = ?, num_serie = ? WHERE asiento = ? AND fila = ? AND id_evento = ?";

        try (Connection bd = conexion.crearConexion(); PreparedStatement preparedStatement = bd.prepareStatement(query)) {
            // Iterar sobre la lista de asientos seleccionados
            for (Boleto boleto : boletosSeleccionados) {
                preparedStatement.setInt(1, boleto.getIdUsuario()); // Asignar ID de usuario
                String numSerie = generarNumeroSerieUnico(bd); // Generar número de serie único
                preparedStatement.setString(2, numSerie); // Asignar número de serie
                preparedStatement.setString(3, boleto.getAsiento()); // Asignar asiento
                preparedStatement.setString(4, boleto.getFila()); // Asignar fila
                preparedStatement.setInt(5, boleto.getIdEvento()); // Asignar ID de evento

                int rowsAffected = preparedStatement.executeUpdate(); // Ejecutar actualización
                if (rowsAffected == 0) {
                    throw new PersistenciaException("No se pudo actualizar el asiento " + boleto.getAsiento() + " en la fila " + boleto.getFila());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al asignar boletos: " + e.getMessage());
        }
    }

    /**
     * Genera un número de serie único de 8 caracteres alfanuméricos.
     *
     * @return Un número de serie aleatorio.
     */
    private String generarNumeroSerie() {
        int longitud = 8; // Longitud del número de serie
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Caracteres permitidos
        Random random = new Random();
        StringBuilder numSerie = new StringBuilder(longitud);

        // Generar un número de serie aleatorio
        for (int i = 0; i < longitud; i++) {
            numSerie.append(caracteres.charAt(random.nextInt(caracteres.length()))); // Seleccionar un carácter aleatorio
        }

        return numSerie.toString(); //
    }

    /**
     * Genera un número de serie único para un boleto, asegurando que no se
     * repita en la base de datos.
     *
     * @param bd Conexión a la base de datos.
     * @return Un número de serie único.
     * @throws PersistenciaException Si no se puede generar un número de serie
     * único.
     */
    private String generarNumeroSerieUnico(Connection bd) throws PersistenciaException {
        String numSerie = generarNumeroSerie(); // Generar número de serie

        // Verificar si el número de serie ya existe en la base de datos
        String verificarNumeroSerie = "SELECT COUNT(*) FROM boletos WHERE num_serie = ?";
        try (PreparedStatement verificarStmt = bd.prepareStatement(verificarNumeroSerie)) {
            verificarStmt.setString(1, numSerie);
            ResultSet rs = verificarStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) { // Si existe, generar uno nuevo
                return generarNumeroSerieUnico(bd);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar número de serie: " + e.getMessage());
        }

        return numSerie; // Retornar el número de serie único
    }

    /**
     * Método que verifica si el num_serie ya existe en la base de datos.
     *
     * @param bd la conexión a la base de datos
     * @param numSerie el número de serie a verificar
     * @return true si el número de serie ya existe, false de lo contrario
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
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
        return false; // El num_serie no existe
    }

    /**
     * Método que consulta todos los boletos asociados a un evento específico.
     *
     * @param idEvento el ID del evento para consultar sus boletos
     * @return una lista de objetos Boleto asociados al evento
     * @throws PersistenciaException si ocurre un error al consultar los boletos
     */
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
                boleto.setIdEvento(idEvento); // Asegúrate de que el campo id_evento exista
                boletos.add(boleto);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los boletos", e);
        }

        return boletos; // Retorna la lista de boletos
    }

    /**
     * Método que gestiona la compra de un boleto.
     *
     * @param idBoleto el ID del boleto a comprar
     * @param precio el precio del boleto
     * @param estadoAdquisicion el estado de adquisición del boleto
     * @param tipoTransaccion el tipo de transacción (compra o reventa)
     * @param idUsuario el ID del usuario que compra el boleto
     * @param idUsuarioAnteriorDueño el ID del usuario anterior dueño del boleto
     * (si aplica)
     * @return true si la compra se realiza con éxito, false de lo contrario
     * @throws PersistenciaException si ocurre un error al realizar la compra
     */
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

            stmt.executeUpdate(); // Ejecuta la compra del boleto

            // Si el estado de adquisición es reventa, registra la transacción de venta
            if (estadoAdquisicion == EstadoAdquisicion.reventa) {
                double montoVenta = precio * 0.97;  // Restar el 3% de comisión
                String queryVenta = "INSERT INTO Transacciones (num_transaccion, monto, tipo, id_usuario) VALUES (?, ?, 'venta', ?)";
                PreparedStatement stmtVenta = bd.prepareStatement(queryVenta);

                int numTransaccion = generarNumeroTransaccion(bd); // Genera el número de transacción
                stmtVenta.setInt(1, numTransaccion);
                stmtVenta.setDouble(2, montoVenta);
                stmtVenta.setInt(3, idUsuarioAnteriorDueño);
                stmtVenta.executeUpdate(); // Registra la transacción de venta

                // Inserta el detalle de la transacción en Detalles_BoletoTransaccion
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
        return true; // Indica que la compra fue exitosa
    }

    /**
     * Método que genera un nuevo número de transacción.
     *
     * @param bd la conexión a la base de datos
     * @return el nuevo número de transacción
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    private int generarNumeroTransaccion(Connection bd) throws SQLException {
        String query = "SELECT COALESCE(MAX(num_transaccion), 0) + 1 FROM Transacciones";
        try (PreparedStatement stmt = bd.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Retorna el siguiente número de transacción
            }
        }
        return 1;  // Retorna 1 si no hay transacciones previas
    }

    /**
     * Método que aparta un boleto para un usuario específico.
     *
     * @param idBoleto el ID del boleto a apartar
     * @param idUsuario el ID del usuario que aparta el boleto
     * @throws PersistenciaException si ocurre un error al apartar el boleto
     */
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

    /**
     * Método que libera un boleto apartado.
     *
     * @param idBoleto el ID del boleto a liberar
     * @throws PersistenciaException si ocurre un error al liberar el boleto
     */
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
                        return; // El boleto no está apartado, no se puede liberar
                    }
                } else {
                    throw new PersistenciaException("No se encontró el boleto con ID " + idBoleto);
                }
            }

            // Liberar el boleto
            updateStmt.setInt(1, idBoleto);
            int rowsAffected = updateStmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PersistenciaException("No se pudo liberar el boleto con ID " + idBoleto);
            }

            // Eliminar el registro de Apartados
            deleteStmt.setInt(1, idBoleto);
            deleteStmt.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("Error al liberar el boleto: " + e.getMessage());
        }
    }

    /**
     * Método que realiza la reventa de un boleto.
     *
     * @param idBoleto el ID del boleto a revender
     * @param precioReventa el nuevo precio de reventa del boleto
     * @param fechaLimite la fecha límite para la reventa del boleto
     * @param idUsuario el ID del usuario que realiza la reventa
     * @return true si la reventa se realiza con éxito, false de lo contrario
     * @throws PersistenciaException si ocurre un error al realizar la reventa
     */
    public boolean revenderBoleto(int idBoleto, double precioReventa, Date fechaLimite, int idUsuario) throws PersistenciaException {
        try (Connection bd = conexion.crearConexion()) {
            String sql = "{CALL ReventaBoleto(?, ?, ?, ?)}"; // Llamada al procedimiento almacenado para reventa
            PreparedStatement stmt = bd.prepareStatement(sql);

            stmt.setInt(1, idBoleto); // Establece el ID del boleto
            stmt.setDouble(2, precioReventa); // Establece el nuevo precio de reventa
            stmt.setDate(3, new java.sql.Date(fechaLimite.getTime())); // Establece la fecha límite
            stmt.setInt(4, idUsuario); // Establece el ID del usuario que realiza la reventa

            stmt.executeUpdate(); // Ejecuta la actualización en la base de datos
            return true; // Indica que la reventa fue exitosa
        } catch (SQLException e) {
            throw new PersistenciaException("Error al realizar la reventa", e); // Maneja cualquier error de SQL
        }
    }

    /**
     * Método que obtiene el precio original de un boleto dado su número de
     * serie.
     *
     * @param numSerie el número de serie del boleto
     * @return el precio original del boleto
     * @throws PersistenciaException si ocurre un error al obtener el precio
     * original
     */
    public double obtenerPrecioOriginal(String numSerie) throws PersistenciaException {
        Connection bd = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double precioOriginal = 0.0; // Inicializa el precio original a 0.0

        try {
            bd = conexion.crearConexion(); // Crea la conexión a la base de datos
            String sql = "SELECT precioOriginal FROM Boletos WHERE numSerie = ?"; // Consulta SQL para obtener el precio original
            stmt = bd.prepareStatement(sql);
            stmt.setString(1, numSerie); // Establece el número de serie en la consulta
            rs = stmt.executeQuery(); // Ejecuta la consulta y obtiene el resultado

            if (rs.next()) {
                precioOriginal = rs.getDouble("precioOriginal"); // Obtiene el precio original si se encuentra el boleto
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el precio original del boleto.", e); // Maneja cualquier error de SQL
        }
        return precioOriginal; // Retorna el precio original
    }

    /**
     * Método que consulta los boletos que están en venta para un usuario
     * específico.
     *
     * @param idUsuario el ID del usuario para consultar sus boletos en venta
     * @return una lista de boletos en venta del usuario
     * @throws PersistenciaException si ocurre un error al consultar los boletos
     */
    public List<Boleto> consultarBoletosEnVenta(int idUsuario) throws PersistenciaException {
        List<Boleto> boletosEnVenta = new ArrayList<>(); // Inicializa la lista de boletos en venta
        String sql = "SELECT * FROM Boletos WHERE id_usuario = ? AND en_venta = TRUE"; // Consulta SQL para obtener boletos en venta

        try (Connection conn = this.conexion.crearConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario); // Establece el ID del usuario en la consulta

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Boleto boleto = new Boleto(); // Crea un nuevo objeto Boleto
                    boleto.setIdBoleto(rs.getInt("id_boleto")); // Establece el ID del boleto
                    boleto.setNumSerie(rs.getString("num_serie")); // Establece el número de serie del boleto
                    boleto.setFila(rs.getString("fila")); // Establece la fila del boleto
                    boleto.setAsiento(rs.getString("asiento")); // Establece el asiento del boleto
                    boleto.setPrecioReventa(rs.getDouble("precioReventa")); // Establece el precio de reventa
                    String estadoAdquisicionString = rs.getString("estado_adquisicion"); // Obtiene el estado de adquisición
                    boleto.setEstadoAdquisicion(EstadoAdquisicion.valueOf(estadoAdquisicionString)); // Establece el estado de adquisición
                    boleto.setEn_venta(rs.getBoolean("en_venta")); // Establece si el boleto está en venta
                    boleto.setIdEvento(rs.getInt("id_evento")); // Establece el ID del evento asociado

                    boletosEnVenta.add(boleto); // Agrega el boleto a la lista
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar los boletos en venta", e); // Maneja cualquier error de SQL
        }
        return boletosEnVenta; // Retorna la lista de boletos en venta
    }

    /**
     * Método que actualiza un boleto para que ya no esté en venta y restablece
     * su precio de reventa.
     *
     * @param idBoleto el ID del boleto a actualizar
     * @return true si la actualización fue exitosa, false si no se encontró el
     * boleto
     * @throws PersistenciaException si ocurre un error al actualizar el boleto
     */
    public boolean actualizarBoletoParaReventa(int idBoleto) throws PersistenciaException {
        String updateQuery = "UPDATE Boletos SET precioReventa = 0, en_venta = FALSE WHERE id_boleto = ?"; // Consulta para actualizar el boleto

        try (Connection bd = conexion.crearConexion(); PreparedStatement updateStmt = bd.prepareStatement(updateQuery)) {
            updateStmt.setInt(1, idBoleto); // Establece el ID del boleto en la consulta

            int rowsAffected = updateStmt.executeUpdate(); // Ejecuta la actualización
            if (rowsAffected > 0) {
                System.out.println("Boleto con ID " + idBoleto + " actualizado correctamente."); // Indica que la actualización fue exitosa
                return true; // Indica que la actualización fue exitosa
            } else {
                System.out.println("No se encontró un boleto con ID " + idBoleto); // Indica que no se encontró el boleto
                return false; // Indica que no se encontró el boleto
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el boleto: " + e.getMessage()); // Maneja cualquier error de SQL
        }
    }

}
