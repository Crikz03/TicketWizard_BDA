/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IConexion;
import interfaces.ITransaccionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import objetos.Transaccion;

/**
 *
 * @author pauli
 */
public class TransaccionDAO implements ITransaccionDAO {

    private IConexion conexion;

    public TransaccionDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Transaccion transaccion) {
        String insertar = "INSERT INTO Transacciones(num_transaccion, monto, fecha, id_usuario, id_boleto) VALUES (?, ?, ?, ?, ?)";
        try (Connection bd = conexion.crearConexion(); PreparedStatement agregar = bd.prepareStatement(insertar)) {
            agregar.setString(1, transaccion.getNumTransaccion());
            agregar.setDouble(2, transaccion.getMonto());
            agregar.setDate(3, new java.sql.Date(transaccion.getFecha().getTime()));
            agregar.setInt(4, transaccion.getIdUsuario());
            agregar.setInt(5, transaccion.getIdBoleto());

            agregar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(int id) {
        String eliminarTransaccion = "DELETE FROM Transacciones WHERE id_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarTransaccion)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean actualizar(Transaccion transaccion) {
        String actualizarTransaccion = "UPDATE Transacciones SET num_transaccion = ?, monto = ?, fecha = ?, id_usuario = ?, id_boleto = ? WHERE id_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarTransaccion)) {
            actualizar.setString(1, transaccion.getNumTransaccion());
            actualizar.setDouble(2, transaccion.getMonto());
            actualizar.setDate(3, new java.sql.Date(transaccion.getFecha().getTime()));
            actualizar.setInt(4, transaccion.getIdUsuario());
            actualizar.setInt(5, transaccion.getIdBoleto());
            actualizar.setInt(6, transaccion.getIdTransaccion());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Transaccion consultar(int id) {
        String buscarTransaccion = "SELECT * FROM Transacciones WHERE id_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Transaccion t = new Transaccion();
                t.setIdTransaccion(resultado.getInt("id_transaccion"));
                t.setNumTransaccion(resultado.getString("num_transaccion"));
                t.setMonto(resultado.getDouble("monto"));
                t.setFecha(resultado.getDate("fecha"));
                t.setIdUsuario(resultado.getInt("id_usuario"));
                t.setIdBoleto(resultado.getInt("id_boleto"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaccion> consultar() {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        String consultarTransacciones = "SELECT * FROM Transacciones";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarTransacciones); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Transaccion t = new Transaccion();
                t.setIdTransaccion(resultados.getInt("id_transaccion"));
                t.setNumTransaccion(resultados.getString("num_transaccion"));
                t.setMonto(resultados.getDouble("monto"));
                t.setFecha(resultados.getDate("fecha"));
                t.setIdUsuario(resultados.getInt("id_usuario"));
                t.setIdBoleto(resultados.getInt("id_boleto"));
                listaTransacciones.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTransacciones;
    }
}