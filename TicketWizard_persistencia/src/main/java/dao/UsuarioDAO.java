/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
public class UsuarioDAO implements IUsuarioDAO{

    private IConexion conexion;

    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Usuario usuario) {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Usuarios(nombre_completo, correo, domicilio, edad, fecha_nacimiento, saldo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, usuario.getNombreCompleto());
            agregar.setString(2, usuario.getCorreo());
            agregar.setString(3, usuario.getDomicilio());
            agregar.setInt(4, usuario.getEdad());
            agregar.setString(5, usuario.getFechaNacimiento());
            agregar.setDouble(6, usuario.getSaldo());

            agregar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(int id) {
        String eliminarUsuario = "DELETE FROM Usuarios WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarUsuario)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean actualizar(Usuario usuario) {
        String actualizarUsuario = "UPDATE Usuarios SET nombre_completo = ?, correo = ?, domicilio = ?, edad = ?, fecha_nacimiento = ?, saldo = ? WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarUsuario)) {
            actualizar.setString(1, usuario.getNombreCompleto());
            actualizar.setString(2, usuario.getCorreo());
            actualizar.setString(3, usuario.getDomicilio());
            actualizar.setInt(4, usuario.getEdad());
            actualizar.setString(5, usuario.getFechaNacimiento());
            actualizar.setDouble(6, usuario.getSaldo());
            actualizar.setInt(7, usuario.getIdUsuario());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Usuario consultar(int id) {
        try {
            Connection bd = conexion.crearConexion();
            String buscarUsuario = "SELECT * FROM Usuarios WHERE id_usuario = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarUsuario);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultado.getInt("id_usuario"));
                u.setNombreCompleto(resultado.getString("nombre_completo"));
                u.setCorreo(resultado.getString("correo"));
                u.setDomicilio(resultado.getString("domicilio"));
                u.setEdad(resultado.getInt("edad"));
                u.setFechaNacimiento(resultado.getString("fecha_nacimiento"));
                u.setSaldo(resultado.getDouble("saldo"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> consultar() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String consultarUsuarios = "SELECT * FROM Usuarios";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarUsuarios); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultados.getInt("id_usuario"));
                u.setNombreCompleto(resultados.getString("nombre_completo"));
                u.setCorreo(resultados.getString("correo"));
                u.setDomicilio(resultados.getString("domicilio"));
                u.setEdad(resultados.getInt("edad"));
                u.setFechaNacimiento(resultados.getString("fecha_nacimiento"));
                u.setSaldo(resultados.getDouble("saldo"));
                listaUsuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }
}