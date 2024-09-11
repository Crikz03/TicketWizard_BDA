/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import interfaces.IConexion;
import interfaces.IEventoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Evento;

/**
 *
 * @author pauli
 */
public class EventoDAO implements IEventoDAO {

    private IConexion conexion;

    public EventoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    public boolean agregar(Evento evento) {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Eventos(nombre, fecha, localidad, capacidad, venue) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);

            agregar.setString(1, evento.getNombre());
            agregar.setString(2, evento.getFecha());
            agregar.setString(3, evento.getLocalidad());
            agregar.setInt(4, evento.getCapacidad());
            agregar.setString(5, evento.getVenue());

            agregar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean eliminar(int id) {
        String eliminarEvento = "DELETE FROM Eventos WHERE id_evento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarEvento)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean actualizar(Evento evento) {
        String actualizarEvento = "UPDATE Eventos SET nombre = ?, fecha = ?, localidad = ?, capacidad = ?, venue = ? WHERE id_evento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarEvento)) {
            actualizar.setString(1, evento.getNombre());
            actualizar.setString(2, evento.getFecha());
            actualizar.setString(3, evento.getLocalidad());
            actualizar.setInt(4, evento.getCapacidad());
            actualizar.setString(5, evento.getVenue());
            actualizar.setInt(6, evento.getIdEvento());

            actualizar.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Evento consultar(int id) {
        try {
            Connection bd = conexion.crearConexion();
            String buscarEvento = "SELECT * FROM Eventos WHERE id_evento = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarEvento);
            busqueda.setInt(1, id);
            ResultSet resultado = busqueda.executeQuery();

            if (resultado.next()) {
                Evento e = new Evento();
                e.setIdEvento(resultado.getInt("id_evento"));
                e.setNombre(resultado.getString("nombre"));
                e.setFecha(resultado.getString("fecha"));
                e.setLocalidad(resultado.getString("localidad"));
                e.setCapacidad(resultado.getInt("capacidad"));
                e.setVenue(resultado.getString("venue"));
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Evento> consultar() {
        List<Evento> listaEventos = new ArrayList<>();
        String consultarEventos = "SELECT * FROM Eventos";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarEventos); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Evento e = new Evento();
                e.setIdEvento(resultados.getInt("id_evento"));
                e.setNombre(resultados.getString("nombre"));
                e.setFecha(resultados.getString("fecha"));
                e.setLocalidad(resultados.getString("localidad"));
                e.setCapacidad(resultados.getInt("capacidad"));
                e.setVenue(resultados.getString("venue"));
                listaEventos.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEventos;
    }
}
