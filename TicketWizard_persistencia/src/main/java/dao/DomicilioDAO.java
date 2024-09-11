/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import interfaces.IConexion;
import interfaces.IDomicilioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Domicilio;

/**
 *
 * @author pauli
 */
public class DomicilioDAO implements IDomicilioDAO{

    private IConexion conexion;

    public DomicilioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Domicilio domicilio) {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Domicilios(calle, numero_exterior, colonia, id_usuario) VALUES (?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, domicilio.getCalle());
            agregar.setString(2, domicilio.getNumeroExterior());
            agregar.setString(3, domicilio.getColonia());
            agregar.setInt(4, domicilio.getIdUsuario());

            agregar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(int id) {
        String eliminarDomicilio = "DELETE FROM Domicilios WHERE id_domicilio = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarDomicilio)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean actualizar(Domicilio domicilio) {
        String actualizarDomicilio = "UPDATE Domicilios SET calle = ?, numero_exterior = ?, colonia = ?, id_usuario = ? WHERE id_domicilio = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarDomicilio)) {
            actualizar.setString(1, domicilio.getCalle());
            actualizar.setString(2, domicilio.getNumeroExterior());
            actualizar.setString(3, domicilio.getColonia());
            actualizar.setInt(4, domicilio.getIdUsuario());
            actualizar.setInt(5, domicilio.getIdDomicilio());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Domicilio consultar(int id) {
        try {
            Connection bd = conexion.crearConexion();
            String buscarDomicilio = "SELECT * FROM Domicilios WHERE id_domicilio = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarDomicilio);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Domicilio d = new Domicilio();
                d.setIdDomicilio(resultado.getInt("id_domicilio"));
                d.setCalle(resultado.getString("calle"));
                d.setNumeroExterior(resultado.getString("numero_exterior"));
                d.setColonia(resultado.getString("colonia"));
                d.setIdUsuario(resultado.getInt("id_usuario"));
                return d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Domicilio> consultar() {
        List<Domicilio> listaDomicilios = new ArrayList<>();
        String consultarDomicilios = "SELECT * FROM Domicilios";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarDomicilios); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Domicilio d = new Domicilio();
                d.setIdDomicilio(resultados.getInt("id_domicilio"));
                d.setCalle(resultados.getString("calle"));
                d.setNumeroExterior(resultados.getString("numero_exterior"));
                d.setColonia(resultados.getString("colonia"));
                d.setIdUsuario(resultados.getInt("id_usuario"));
                listaDomicilios.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDomicilios;
    }
}
