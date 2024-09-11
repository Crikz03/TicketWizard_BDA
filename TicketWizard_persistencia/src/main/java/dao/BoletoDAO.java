/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IBoletoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Boleto;

/**
 *
 * @author pauli
 */
public class BoletoDAO implements IBoletoDAO{

    private IConexion conexion;

    public BoletoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Boleto boleto) {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Boletos(num_serie, fila, asiento, precio, estado_adquisicion, id_usuario, id_evento) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, boleto.getNumSerie());
            agregar.setString(2, boleto.getFila());
            agregar.setString(3, boleto.getAsiento());
            agregar.setDouble(4, boleto.getPrecio());
            agregar.setString(5, boleto.getEstadoAdquisicion());
            agregar.setInt(6, boleto.getIdUsuario());
            agregar.setInt(7, boleto.getIdEvento());

            agregar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(int id) {
        String eliminarBoleto = "DELETE FROM Boletos WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarBoleto)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean actualizar(Boleto boleto) {
        String actualizarBoleto = "UPDATE Boletos SET num_serie = ?, fila = ?, asiento = ?, precio = ?, estado_adquisicion = ?, id_usuario = ?, id_evento = ? WHERE id_boleto = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarBoleto)) {
            actualizar.setString(1, boleto.getNumSerie());
            actualizar.setString(2, boleto.getFila());
            actualizar.setString(3, boleto.getAsiento());
            actualizar.setDouble(4, boleto.getPrecio());
            actualizar.setString(5, boleto.getEstadoAdquisicion());
            actualizar.setInt(6, boleto.getIdUsuario());
            actualizar.setInt(7, boleto.getIdEvento());
            actualizar.setInt(8, boleto.getIdBoleto());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boleto consultar(int id) {
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
                b.setEstadoAdquisicion(resultado.getString("estado_adquisicion"));
                b.setIdUsuario(resultado.getInt("id_usuario"));
                b.setIdEvento(resultado.getInt("id_evento"));
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Boleto> consultar() {
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
                b.setEstadoAdquisicion(resultados.getString("estado_adquisicion"));
                b.setIdUsuario(resultados.getInt("id_usuario"));
                b.setIdEvento(resultados.getInt("id_evento"));
                listaBoletos.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBoletos;
    }

   
    public boolean comprarBoleto(int idBoleto, String numSerie, double precio, String estadoAdquisicion, int idUsuario) {
        try {
            Connection bd = conexion.crearConexion();
            String procedimiento = "{CALL ComprarBoleto(?, ?, ?, ?, ?)}";
            PreparedStatement stmt = bd.prepareStatement(procedimiento);

            stmt.setInt(1, idBoleto);
            stmt.setString(2, numSerie);
            stmt.setDouble(3, precio);
            stmt.setString(4, estadoAdquisicion);
            stmt.setInt(5, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
