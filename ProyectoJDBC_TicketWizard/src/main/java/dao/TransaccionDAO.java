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
 *
 * @author pauli
 */
public class TransaccionDAO implements ITransaccionDAO {

    private IConexion conexion;

    public TransaccionDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean agregar(Transaccion transaccion) throws PersistenciaException {
        String insertar = "INSERT INTO Transacciones(monto, comision,tiempo_limite, tipo,fecha_hora, id_usuario) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection bd = conexion.crearConexion(); PreparedStatement agregar = bd.prepareStatement(insertar)) {
            agregar.setDouble(1, transaccion.getMonto());
            agregar.setDouble(2, transaccion.getComision());
            agregar.setTime(3, transaccion.getTiempoLimite());
            agregar.setObject(4, transaccion.getTipo());
            agregar.setDate(5, transaccion.getFecha());
            agregar.setInt(6, transaccion.getIdUsuario());

            agregar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar la transaccion con numero de transaccion: " + transaccion.getNumTransaccion());
        }
        return true;
    }

    @Override
    public boolean eliminar(int num_transaccion) throws PersistenciaException {
        String eliminarTransaccion = "DELETE FROM Transacciones WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarTransaccion)) {
            eliminar.setInt(1, num_transaccion);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo eliminar la transaccion con numero de transaccion: " + num_transaccion);
        }
        return true;
    }

    @Override
    public boolean actualizar(Transaccion transaccion) throws PersistenciaException {
        String actualizarTransaccion = "UPDATE Transacciones SET monto = ?, comision = ?, tiempo_limite = ?, tipo = ?, fecha_hora = ?, id_usuario = ? WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarTransaccion)) {
            actualizar.setDouble(1, transaccion.getMonto());
            actualizar.setDouble(2, transaccion.getComision());
            actualizar.setTime(3, transaccion.getTiempoLimite());
            actualizar.setObject(4, transaccion.getTipo());
            actualizar.setDate(5, transaccion.getFecha());
            actualizar.setInt(6, transaccion.getIdUsuario());
            actualizar.setInt(7, transaccion.getNumTransaccion());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar la transaccion con numero de transaccion: " + transaccion.getNumTransaccion());
        }
        return true;
    }

    @Override
    public Transaccion consultar(int num_transaccion) throws PersistenciaException {
        String buscarTransaccion = "SELECT * FROM Transacciones WHERE num_transaccion = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            busqueda.setInt(1, num_transaccion);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Transaccion t = new Transaccion();
                t.setNumTransaccion(resultado.getInt("num_transaccion"));
                t.setMonto(resultado.getDouble("monto"));
                t.setComision(resultado.getDouble("comision"));
                t.setTiempoLimite(resultado.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultado.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultado.getDate("fecha_hora"));
                t.setIdUsuario(resultado.getInt("id_usuario"));
                return t;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar la trasaccion con numero de transaccion: " + num_transaccion);
        }
        return null;
    }

    @Override
    public List<Transaccion> consultar() throws PersistenciaException {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        String consultarTransacciones = "SELECT * FROM Transacciones";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarTransacciones); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Transaccion t = new Transaccion();
                t.setNumTransaccion(resultados.getInt("num_transaccion"));
                t.setMonto(resultados.getDouble("monto"));
                t.setComision(resultados.getDouble("comision"));
                t.setTiempoLimite(resultados.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultados.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultados.getDate("fecha_hora"));
                t.setIdUsuario(resultados.getInt("id_usuario"));
                listaTransacciones.add(t);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar las trasacciones");
        }
        return listaTransacciones;
    }

    @Override
    public List<Transaccion> consultarIdUsuario(int idUsuario) throws PersistenciaException {
        List<Transaccion> listaTransacciones = new ArrayList<>();
        String buscarTransaccion = "SELECT * FROM Transacciones WHERE id_usuario = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarTransaccion)) {
            busqueda.setInt(1, idUsuario);
            ResultSet resultados = busqueda.executeQuery();

            while (resultados.next()) {
                Transaccion t = new Transaccion();
                t.setNumTransaccion(resultados.getInt("num_transaccion"));
                t.setMonto(resultados.getDouble("monto"));
                t.setComision(resultados.getDouble("comision"));
                t.setTiempoLimite(resultados.getTime("tiempo_limite"));
                String tipoTransaccionStr = resultados.getString("tipo");
                t.setTipo(TipoTransaccion.valueOf(tipoTransaccionStr));
                t.setFecha(resultados.getDate("fecha_hora"));
                t.setIdUsuario(resultados.getInt("id_usuario"));
                listaTransacciones.add(t);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar la trasaccion del usuario con id: " + idUsuario);
        }
        return listaTransacciones;
    }

    public boolean agregarSaldo(Usuario usuario, double monto) throws PersistenciaException {
        String sql = "INSERT INTO Transacciones (id_usuario, monto, tipo) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.crearConexion().prepareStatement(sql)) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.setDouble(2, monto);
            ps.setString(3, TipoTransaccion.saldo.name());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el saldo a la cuenta con id: " + usuario.getIdUsuario());
        }
        return true;
    }

}
