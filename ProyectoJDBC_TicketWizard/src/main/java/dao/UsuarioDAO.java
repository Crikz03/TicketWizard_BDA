/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.PersistenciaException;
import interfaces.IConexion;
import interfaces.IUsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Usuario;
import utilidades.Encriptacion;

/**
 * Clase que maneja las operaciones de persistencia para los usuarios en la base
 * de datos. Implementa la interfaz IUsuarioDAO.
 *
 * Esta clase se encarga de realizar las operaciones CRUD relacionadas con los
 * usuarios. Utiliza una conexión a la base de datos para ejecutar consultas y
 * actualizar registros.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexion; // La variable que contendrá la conexión de la base de datos.

    /**
     * Constructor de la clase UsuarioDAO que inicializa la conexión a la base
     * de datos.
     *
     * @param conexion Objeto que implementa la interfaz IConexion para
     * gestionar la conexión a la base de datos.
     */
    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que agrega un nuevo usuario a la base de datos.
     *
     * @param usuario Objeto Usuario que contiene la información del nuevo
     * usuario.
     * @return true si el usuario fue agregado exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al agregar el usuario.
     */
    public boolean agregar(Usuario usuario) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion(); // Establece la conexión a la base de datos.
            String insertar = "INSERT INTO Usuarios(nombres,apellidoPaterno,apellidoMaterno, correo,contrasena, calle,numero_exterior,colonia, edad, fecha_nacimiento, saldo, administrador) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?, false)";
            PreparedStatement agregar = bd.prepareStatement(insertar); // Prepara la instrucción SQL.

            String hashedPassword = Encriptacion.encriptarPassword(usuario.getContrasena()); // Encripta la contraseña del usuario.

            // Establece los valores del PreparedStatement.
            agregar.setString(1, usuario.getNombres());
            agregar.setString(2, usuario.getApellidoPaterno());
            agregar.setString(3, usuario.getApellidoMaterno());
            agregar.setString(4, usuario.getCorreo());
            agregar.setString(5, hashedPassword);
            agregar.setString(6, usuario.getCalle());
            agregar.setString(7, usuario.getNumeroExterior());
            agregar.setString(8, usuario.getColonia());
            agregar.setInt(9, usuario.getEdad());

            // Convierte la fecha de nacimiento a java.sql.Date y la establece en el PreparedStatement.
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFechaNacimiento().getTime());
            agregar.setDate(10, sqlDate);
            agregar.setDouble(11, usuario.getSaldo());

            agregar.executeUpdate(); // Ejecuta la inserción en la base de datos.
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el usuario con id: " + usuario.getIdUsuario());
        }
        return true; // Retorna true si se agregó el usuario correctamente.
    }

    /**
     * Método que actualiza la información de un usuario existente en la base de
     * datos.
     *
     * @param usuario Objeto Usuario que contiene la información actualizada del
     * usuario.
     * @return true si el usuario fue actualizado exitosamente, false en caso
     * contrario.
     * @throws PersistenciaException Si ocurre un error al actualizar el
     * usuario.
     */
    public boolean actualizar(Usuario usuario) throws PersistenciaException {
        String actualizarUsuario = "UPDATE Usuarios SET nombres = ?,apellidoPaterno = ?,apellidoMaterno =?, correo = ?,contrasena = ?, calle = ?,numero_exterior = ?,colonia = ?, edad = ?, fecha_nacimiento = ?, saldo = ?, administrador = ? WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarUsuario)) {

            // Establece los valores del PreparedStatement.
            actualizar.setString(1, usuario.getNombres());
            actualizar.setString(2, usuario.getApellidoPaterno());
            actualizar.setString(3, usuario.getApellidoMaterno());
            actualizar.setString(4, usuario.getCorreo());
            actualizar.setString(5, usuario.getContrasena());
            actualizar.setString(6, usuario.getCalle());
            actualizar.setString(7, usuario.getNumeroExterior());
            actualizar.setString(8, usuario.getColonia());
            actualizar.setInt(9, usuario.getEdad());

            // Convierte la fecha de nacimiento a java.sql.Date y la establece en el PreparedStatement.
            java.sql.Date sqlDate = new java.sql.Date(usuario.getFechaNacimiento().getTime());
            actualizar.setDate(10, sqlDate);
            actualizar.setDouble(11, usuario.getSaldo());

            actualizar.setBoolean(12, usuario.getAdministrador()); // Establece si el usuario es administrador.
            actualizar.setInt(13, usuario.getIdUsuario()); // Establece el id del usuario a actualizar.

            actualizar.executeUpdate(); // Ejecuta la actualización en la base de datos.
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el usuario con id: " + usuario.getIdUsuario());
        }
        return true; // Retorna true si se actualizó el usuario correctamente.
    }

    /**
     * Método que consulta un usuario por su ID en la base de datos.
     *
     * @param id ID del usuario a consultar.
     * @return Objeto Usuario con la información del usuario encontrado, o null
     * si no se encontró.
     * @throws PersistenciaException Si ocurre un error al consultar el usuario.
     */
    public Usuario consultar(int id) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion(); // Establece la conexión a la base de datos.
            String buscarUsuario = "SELECT * FROM Usuarios WHERE id_usuario = ?"; // Consulta SQL para buscar al usuario por ID.
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            busqueda.setInt(1, id); // Establece el parámetro ID en la consulta.
            ResultSet resultado = busqueda.executeQuery(); // Ejecuta la consulta.

            if (resultado.next()) { // Si se encuentra un resultado.
                Usuario u = new Usuario(); // Crea un nuevo objeto Usuario.
                // Establece los valores del objeto Usuario con los resultados de la consulta.
                u.setIdUsuario(resultado.getInt("id_usuario"));
                u.setNombres(resultado.getString("nombres"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setCalle(resultado.getString("calle"));
                u.setNumeroExterior(resultado.getString("numero_exterior"));
                u.setColonia(resultado.getString("colonia"));
                u.setEdad(resultado.getInt("edad"));
                u.setFechaNacimiento(resultado.getDate("fecha_nacimiento"));
                u.setSaldo(resultado.getDouble("saldo"));
                u.setAdministrador(resultado.getBoolean("administrador"));
                return u; // Retorna el objeto Usuario.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar el usuario con id: " + id);
        }
        return null; // Retorna null si no se encuentra el usuario.
    }

    /**
     * Método que consulta un usuario por su correo electrónico en la base de
     * datos.
     *
     * @param correo Correo electrónico del usuario a consultar.
     * @return Objeto Usuario con la información del usuario encontrado, o null
     * si no se encontró.
     * @throws PersistenciaException Si ocurre un error al consultar el usuario.
     */
    public Usuario consultarPorCorreo(String correo) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion(); // Establece la conexión a la base de datos.
            String buscarUsuario = "SELECT * FROM Usuarios WHERE correo = ?"; // Consulta SQL para buscar al usuario por correo.
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            busqueda.setString(1, correo); // Establece el parámetro correo en la consulta.
            ResultSet resultado = busqueda.executeQuery(); // Ejecuta la consulta.

            if (resultado.next()) { // Si se encuentra un resultado.
                Usuario u = new Usuario(); // Crea un nuevo objeto Usuario.
                // Establece los valores del objeto Usuario con los resultados de la consulta.
                u.setIdUsuario(resultado.getInt("id_usuario"));
                u.setNombres(resultado.getString("nombres"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setCorreo(resultado.getString("correo"));
                u.setContrasena(resultado.getString("contrasena"));
                u.setCalle(resultado.getString("calle"));
                u.setNumeroExterior(resultado.getString("numero_exterior"));
                u.setColonia(resultado.getString("colonia"));
                u.setEdad(resultado.getInt("edad"));
                u.setFechaNacimiento(resultado.getDate("fecha_nacimiento"));
                u.setSaldo(resultado.getDouble("saldo"));
                u.setAdministrador(resultado.getBoolean("administrador"));
                return u; // Retorna el objeto Usuario.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar el usuario con el correo: " + correo);
        }
        return null; // Retorna null si no se encuentra el usuario.
    }

    /**
     * Método que consulta todos los usuarios en la base de datos.
     *
     * @return Lista de objetos Usuario con la información de todos los
     * usuarios.
     * @throws PersistenciaException Si ocurre un error al consultar los
     * usuarios.
     */
    public List<Usuario> consultar() throws PersistenciaException {
        List<Usuario> listaUsuarios = new ArrayList<>(); // Crea una lista para almacenar los usuarios.
        String consultarUsuarios = "SELECT * FROM Usuarios"; // Consulta SQL para obtener todos los usuarios.
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarUsuarios); ResultSet resultados = consulta.executeQuery()) { // Ejecuta la consulta.

            while (resultados.next()) { // Itera sobre los resultados.
                Usuario u = new Usuario(); // Crea un nuevo objeto Usuario.
                // Establece los valores del objeto Usuario con los resultados de la consulta.
                u.setIdUsuario(resultados.getInt("id_usuario"));
                u.setNombres(resultados.getString("nombres"));
                u.setApellidoPaterno(resultados.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultados.getString("apellidoMaterno"));
                u.setCorreo(resultados.getString("correo"));
                u.setContrasena(resultados.getString("contrasena"));
                u.setCalle(resultados.getString("calle"));
                u.setNumeroExterior(resultados.getString("numero_exterior"));
                u.setColonia(resultados.getString("colonia"));
                u.setEdad(resultados.getInt("edad"));
                u.setFechaNacimiento(resultados.getDate("fecha_nacimiento"));
                u.setSaldo(resultados.getDouble("saldo"));
                u.setAdministrador(resultados.getBoolean("administrador"));
                listaUsuarios.add(u); // Agrega el objeto Usuario a la lista.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los usuarios"); // Lanza una excepción si ocurre un error.
        }
        return listaUsuarios; // Retorna la lista de usuarios.
    }

    /**
     * Método que verifica si un correo electrónico ya existe en la base de
     * datos.
     *
     * @param correo Correo electrónico a verificar.
     * @return true si el correo existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al verificar el correo.
     */
    public boolean existeCorreo(String correo) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion(); // Establece la conexión a la base de datos.
            String query = "SELECT COUNT(*) FROM Usuarios WHERE correo = ?"; // Consulta SQL para contar usuarios con el correo.
            PreparedStatement consulta = bd.prepareStatement(query);
            consulta.setString(1, correo); // Establece el parámetro correo en la consulta.

            ResultSet resultado = consulta.executeQuery(); // Ejecuta la consulta.
            if (resultado.next()) { // Si se encuentra un resultado.
                return resultado.getInt(1) > 0; // Retorna true si hay uno o más usuarios con el correo.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar si el correo existe."); // Lanza una excepción si ocurre un error.
        }
        return false; // Retorna false si no se encuentra ningún usuario con el correo.
    }

    /**
     * Método que obtiene el saldo actual de un usuario dado su ID.
     *
     * @param idUsuario ID del usuario cuyo saldo se desea consultar.
     * @return El saldo actual del usuario.
     * @throws PersistenciaException Si ocurre un error al consultar el saldo o
     * si no se encuentra el usuario.
     */
    public double obtenerSaldoActual(int idUsuario) throws PersistenciaException {
        String sql = "SELECT saldo FROM Usuarios WHERE id_usuario = ?"; // Consulta SQL para obtener el saldo.

        try (PreparedStatement ps = conexion.crearConexion().prepareStatement(sql)) { // Prepara la consulta.
            ps.setInt(1, idUsuario); // Establece el parámetro idUsuario en la consulta.
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta.

            if (rs.next()) { // Si se encuentra un resultado.
                return rs.getDouble("saldo"); // Retorna el saldo del usuario.
            } else {
                throw new PersistenciaException("No se pudo encontrar el usuario con id: " + idUsuario); // Lanza excepción si no se encuentra el usuario.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el saldo del usuario con id: " + idUsuario, e); // Lanza excepción en caso de error SQL.
        }
    }

    /**
     * Método que verifica si un usuario puede agregar un monto a su saldo.
     *
     * @param usuario Usuario que desea agregar saldo.
     * @param montoAgregar Monto a agregar al saldo del usuario.
     * @return true si se puede agregar el saldo, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al verificar las
     * restricciones diarias.
     */
    public boolean puedeAgregarSaldo(Usuario usuario, double montoAgregar) throws PersistenciaException {
        String sql = "SELECT COUNT(*) AS transaccionesHoy, SUM(monto) AS totalAgregadoHoy "
                + "FROM Transacciones WHERE id_usuario = ? AND DATE(fecha_transaccion) = CURDATE()"; // Consulta SQL para obtener transacciones del día.

        try (PreparedStatement ps = conexion.crearConexion().prepareStatement(sql)) { // Prepara la consulta.
            ps.setInt(1, usuario.getIdUsuario()); // Establece el parámetro idUsuario en la consulta.
            ResultSet rs = ps.executeQuery(); // Ejecuta la consulta.

            if (rs.next()) { // Si se encuentra un resultado.
                int transaccionesHoy = rs.getInt("transaccionesHoy"); // Obtiene el conteo de transacciones.
                double totalAgregadoHoy = rs.getDouble("totalAgregadoHoy"); // Obtiene el total agregado hoy.

                int maxTransaccionesDiarias = 3; // Máximo de transacciones permitidas por día.
                double maxMontoDiario = 2000.0; // Máximo de monto que se puede agregar por día.

                // Verifica si se ha alcanzado el número máximo de transacciones diarias.
                if (transaccionesHoy >= maxTransaccionesDiarias) {
                    throw new PersistenciaException("Has alcanzado el número máximo de transacciones diarias.");
                }

                // Verifica si el monto a agregar excede el máximo permitido por día.
                if ((totalAgregadoHoy + montoAgregar) > maxMontoDiario) {
                    throw new PersistenciaException("No puedes agregar más de " + maxMontoDiario + " en un solo día.");
                }

                return true; // Retorna true si se puede agregar el saldo.
            } else {
                throw new PersistenciaException("Error al verificar las transacciones del día."); // Lanza excepción si no se encuentra el resultado.
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar las restricciones diarias.", e); // Lanza excepción en caso de error SQL.
        }
    }

}
