/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.PersistenciaException;
import interfaces.IConexion;
import interfaces.ITransaccionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Transaccion;
import objetos.Usuario;
import utilidades.TipoTransaccion;

/**
 * Clase que implementa la interfaz ITransaccionDAO para realizar operaciones
 * CRUD relacionadas con las transacciones en la base de datos. Se encarga de
 * gestionar las operaciones necesarias para la creación, lectura, actualización
 * y eliminación de transacciones, así como la consulta de información
 * relacionada con ellas.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class TransaccionDAO implements ITransaccionDAO {

    private IConexion conexion; // La variable que contendrá la conexión de la base de datos.

    /**
     * Constructor de la clase TransaccionDAO que inicializa la conexión a la
     * base de datos.
     *
     * @param conexion Objeto que implementa la interfaz IConexion para
     * gestionar la conexión.
     */
    public TransaccionDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Agrega una nueva transacción a la base de datos.
     *
     * @param transaccion Objeto Transaccion a agregar.
     * @return true si la transacción se agregó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean agregar(Transaccion transaccion) throws PersistenciaException {
        // SQL para insertar una nueva transacción
        String insertar = "INSERT INTO Transacciones(monto, comision, tiempo_limite, tipo, fecha_hora, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection bd = conexion.crearConexion(); PreparedStatement agregar = bd.prepareStatement(insertar)) {
            // Establecer los parámetros de la consulta
            agregar.setDouble(1, transaccion.getMonto());
            agregar.setDouble(2, transaccion.getComision());
            agregar.setTime(3, transaccion.getTiempoLimite());
            agregar.setObject(4, transaccion.getTipo());
            agregar.setTimestamp(5, transaccion.getFecha());
            agregar.setInt(6, transaccion.getIdUsuario());

            // Ejecutar la inserción
            agregar.executeUpdate();
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al agregar la transacción
            throw new PersistenciaException("No se pudo agregar la transaccion con numero de transaccion: " + transaccion.getNumTransaccion());
        }
        return true; // Retornar verdadero si la operación fue exitosa
    }

    /**
     * Elimina una transacción de la base de datos.
     *
     * @param num_transaccion Número de la transacción a eliminar.
     * @return true si la transacción se eliminó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean eliminar(int num_transaccion) throws PersistenciaException {
        // SQL para eliminar una transacción por su número
        String eliminarTransaccion = "DELETE FROM Transacciones WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarTransaccion)) {
            // Establecer el parámetro para la consulta
            eliminar.setInt(1, num_transaccion);
            eliminar.executeUpdate(); // Ejecutar la eliminación
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al eliminar la transacción
            throw new PersistenciaException("No se pudo eliminar la transaccion con numero de transaccion: " + num_transaccion);
        }
        return true; // Retornar verdadero si la operación fue exitosa
    }

    /**
     * Actualiza una transacción existente en la base de datos.
     *
     * @param transaccion Objeto Transaccion con los nuevos datos.
     * @return true si la transacción se actualizó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean actualizar(Transaccion transaccion) throws PersistenciaException {
        // SQL para actualizar una transacción
        String actualizarTransaccion = "UPDATE Transacciones SET monto = ?, comision = ?, tiempo_limite = ?, tipo = ?, fecha_hora = ?, id_usuario = ? WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarTransaccion)) {
            // Establecer los parámetros para la consulta
            actualizar.setDouble(1, transaccion.getMonto());
            actualizar.setDouble(2, transaccion.getComision());
            actualizar.setTime(3, transaccion.getTiempoLimite());
            actualizar.setObject(4, transaccion.getTipo());
            actualizar.setTimestamp(5, transaccion.getFecha());
            actualizar.setInt(6, transaccion.getIdUsuario());
            actualizar.setInt(7, transaccion.getNumTransaccion());

            // Ejecutar la actualización
            actualizar.executeUpdate();
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al actualizar la transacción
            throw new PersistenciaException("No se pudo actualizar la transaccion con numero de transaccion: " + transaccion.getNumTransaccion());
        }
        return true; // Retornar verdadero si la operación fue exitosa
    }

    /**
     * Consulta una transacción por su número.
     *
     * @param num_transaccion Número de la transacción a consultar.
     * @return Objeto Transaccion si se encuentra, null en caso contrario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Transaccion consultar(int num_transaccion) throws PersistenciaException {
        // SQL para buscar una transacción por su número
        String buscarTransaccion = "SELECT * FROM Transacciones WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            // Establecer el parámetro para la consulta
            busqueda.setInt(1, num_transaccion);
            ResultSet resultado = busqueda.executeQuery(); // Ejecutar la búsqueda

            if (resultado.next()) { // Si se encuentra la transacción
                Transaccion t = new Transaccion();
                // Establecer los valores del resultado en el objeto Transaccion
                t.setNumTransaccion(resultado.getInt("num_transaccion"));
                t.setMonto(resultado.getDouble("monto"));
                t.setComision(resultado.getDouble("comision"));
                t.setTiempoLimite(resultado.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultado.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultado.getTimestamp("fecha_hora"));
                t.setIdUsuario(resultado.getInt("id_usuario"));
                return t; // Retornar la transacción encontrada
            }
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al consultar la transacción
            throw new PersistenciaException("No se pudo encontrar la trasaccion con numero de transaccion: " + num_transaccion);
        }
        return null; // Retornar null si no se encuentra la transacción
    }

    /**
     * Consulta todas las transacciones en la base de datos.
     *
     * @return Lista de transacciones.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Transaccion> consultar() throws PersistenciaException {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        // SQL para consultar todas las transacciones
        String consultarTransacciones = "SELECT * FROM Transacciones";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarTransacciones); ResultSet resultados = consulta.executeQuery()) {
            // Iterar sobre los resultados y agregar cada transacción a la lista
            while (resultados.next()) {
                Transaccion t = new Transaccion();
                t.setNumTransaccion(resultados.getInt("num_transaccion"));
                t.setMonto(resultados.getDouble("monto"));
                t.setComision(resultados.getDouble("comision"));
                t.setTiempoLimite(resultados.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultados.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultados.getTimestamp("fecha_hora"));
                t.setIdUsuario(resultados.getInt("id_usuario"));
                listaTransacciones.add(t); // Agregar transacción a la lista
            }
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al consultar las transacciones
            throw new PersistenciaException("No se pudo encontrar las trasacciones");
        }
        return listaTransacciones; // Retornar la lista de transacciones
    }

    /**
     * Consulta las transacciones de un usuario específico.
     *
     * @param idUsuario ID del usuario cuyas transacciones se consultan.
     * @return Lista de transacciones del usuario.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Transaccion> consultarIdUsuario(int idUsuario) throws PersistenciaException {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        // SQL para buscar transacciones por ID de usuario
        String buscarTransaccion = "SELECT * FROM Transacciones WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            // Establecer el parámetro para la consulta
            busqueda.setInt(1, idUsuario);
            ResultSet resultados = busqueda.executeQuery(); // Ejecutar la búsqueda

            // Iterar sobre los resultados y agregar cada transacción a la lista
            while (resultados.next()) {
                Transaccion t = new Transaccion();
                t.setNumTransaccion(resultados.getInt("num_transaccion"));
                t.setMonto(resultados.getDouble("monto"));
                t.setComision(resultados.getDouble("comision"));
                t.setTiempoLimite(resultados.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultados.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultados.getTimestamp("fecha_hora"));
                t.setIdUsuario(resultados.getInt("id_usuario"));
                listaTransacciones.add(t); // Agregar transacción a la lista
            }
        } catch (SQLException e) {
            // Lanzar excepción si ocurre un error al consultar las transacciones
            throw new PersistenciaException("No se pudo encontrar las trasacciones del usuario con id: " + idUsuario);
        }
        return listaTransacciones; // Retornar la lista de transacciones
    }

    /**
     * Agrega un saldo a la cuenta de un usuario en la base de datos.
     *
     * @param usuario El objeto Usuario cuyo saldo se va a incrementar.
     * @param monto La cantidad de saldo que se va a agregar.
     * @return true si el saldo se agregó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean agregarSaldo(Usuario usuario, double monto) throws PersistenciaException {
        // SQL para insertar una nueva transacción de saldo
        String sql = "INSERT INTO Transacciones (id_usuario, monto, tipo) VALUES (?, ?, ?)";

        // Intentar crear un PreparedStatement para ejecutar la consulta
        try (PreparedStatement ps = conexion.crearConexion().prepareStatement(sql)) {
            // Establecer el ID del usuario en el primer parámetro de la consulta
            ps.setInt(1, usuario.getIdUsuario());
            // Establecer el monto del saldo en el segundo parámetro
            ps.setDouble(2, monto);
            // Establecer el tipo de transacción como 'saldo' en el tercer parámetro
            ps.setString(3, TipoTransaccion.saldo.name());

            // Ejecutar la actualización de la base de datos
            ps.executeUpdate();

        } catch (SQLException e) {
            // Lanzar una excepción si ocurre un error al agregar el saldo
            throw new PersistenciaException("No se pudo agregar el saldo a la cuenta con id: " + usuario.getIdUsuario());
        }
        return true; // Retornar verdadero si la operación fue exitosa
    }

}
