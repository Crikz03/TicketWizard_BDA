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

/**
 *
 * @author pauli
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexion;

    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Usuario usuario) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Usuarios(nombres,apellidoPaterno,apellidoMaterno, correo, calle,numero_exterior,colonia, edad, fecha_nacimiento, saldo) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, usuario.getNombres());
            agregar.setString(2, usuario.getApellidoPaterno());
            agregar.setString(3, usuario.getApellidoMaterno());
            agregar.setString(4, usuario.getCorreo());
            agregar.setString(5, usuario.getCalle());
            agregar.setString(6, usuario.getNumeroExterior());
            agregar.setString(7, usuario.getColonia());
            agregar.setInt(8, usuario.getEdad());
            agregar.setDate(9, usuario.getFechaNacimiento());
            agregar.setDouble(10, usuario.getSaldo());

            agregar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el usuario con id: " + usuario.getIdUsuario());
        }
        return true;
    }

    public boolean actualizar(Usuario usuario) throws PersistenciaException {
        String actualizarUsuario = "UPDATE Usuarios SET nombres = ?,apellidoPaterno = ?,apellidoMaterno =?, correo = ?, calle = ?,numero_exterior = ?,colonia = ?, edad = ?, fecha_nacimiento = ?, saldo = ? WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarUsuario)) {
            actualizar.setString(1, usuario.getNombres());
            actualizar.setString(2, usuario.getApellidoPaterno());
            actualizar.setString(3, usuario.getApellidoMaterno());
            actualizar.setString(4, usuario.getCorreo());
            actualizar.setString(5, usuario.getCalle());
            actualizar.setString(6, usuario.getNumeroExterior());
            actualizar.setString(7, usuario.getColonia());
            actualizar.setInt(8, usuario.getEdad());
            actualizar.setDate(9, usuario.getFechaNacimiento());
            actualizar.setDouble(10, usuario.getSaldo());
            actualizar.setInt(11, usuario.getIdUsuario());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el usuario con id: " + usuario.getIdUsuario());
        }
        return true;
    }

    public Usuario consultar(int id) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String buscarUsuario = "SELECT * FROM Usuarios WHERE id_usuario = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("id_usuario"));
                u.setNombres(resultado.getString("nombres"));
                u.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                u.setCorreo(resultado.getString("correo"));
                u.setCalle(resultado.getString("calle"));
                u.setNumeroExterior(resultado.getString("numero_exterior"));
                u.setColonia(resultado.getString("colonia"));
                u.setEdad(resultado.getInt("edad"));
                u.setFechaNacimiento(resultado.getDate("fecha_nacimiento"));
                u.setSaldo(resultado.getDouble("saldo"));
                return u;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar el usuario con id: " + id);
        }
        return null;
    }

    public List<Usuario> consultar() throws PersistenciaException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String consultarUsuarios = "SELECT * FROM Usuarios";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarUsuarios); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultados.getInt("id_usuario"));
                u.setNombres(resultados.getString("nombres"));
                u.setApellidoPaterno(resultados.getString("apellidoPaterno"));
                u.setApellidoMaterno(resultados.getString("apellidoMaterno"));
                u.setCorreo(resultados.getString("correo"));
                u.setCalle(resultados.getString("calle"));
                u.setNumeroExterior(resultados.getString("numero_exterior"));
                u.setColonia(resultados.getString("colonia"));
                u.setEdad(resultados.getInt("edad"));
                u.setFechaNacimiento(resultados.getDate("fecha_nacimiento"));
                u.setSaldo(resultados.getDouble("saldo"));
                listaUsuarios.add(u);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los usuarios");
        }
        return listaUsuarios;
    }
}
