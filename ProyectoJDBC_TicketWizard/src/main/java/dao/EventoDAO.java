/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import excepciones.PersistenciaException;
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
    
    public boolean agregar(Evento evento) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String insertar = "INSERT INTO Eventos(nombre, fecha, localidad, capacidad, venue, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement agregar = bd.prepareStatement(insertar);
            
            agregar.setString(1, evento.getNombre());
            agregar.setDate(2, evento.getFecha());
            agregar.setString(3, evento.getLocalidad());
            agregar.setInt(4, evento.getCapacidad());
            agregar.setString(5, evento.getVenue());
            agregar.setString(6, evento.getDescripcion());
            
            agregar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo agregar el boleto con id: " + evento.getIdEvento());
        }
        return true;
    }
    
    public boolean eliminar(int id) throws PersistenciaException {
        String eliminarEvento = "DELETE FROM Eventos WHERE id_evento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement eliminar = bd.prepareStatement(eliminarEvento)) {
            eliminar.setInt(1, id);
            eliminar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo eliminar el boleto con id: " + id);
        }
        return true;
    }
    
    public boolean actualizar(Evento evento) throws PersistenciaException {
        String actualizarEvento = "UPDATE Eventos SET nombre = ?, fecha = ?, localidad = ?, capacidad = ?, venue = ?, descripcion = ? WHERE id_evento = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement actualizar = bd.prepareStatement(actualizarEvento)) {
            actualizar.setString(1, evento.getNombre());
            actualizar.setDate(2, evento.getFecha());
            actualizar.setString(3, evento.getLocalidad());
            actualizar.setInt(4, evento.getCapacidad());
            actualizar.setString(5, evento.getVenue());
            actualizar.setString(6, evento.getDescripcion());
            actualizar.setInt(7, evento.getIdEvento());
            
            actualizar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo actualizar el boleto con id: " + evento.getIdEvento());
        }
        return true;
    }
    
    public Evento consultar(int id) throws PersistenciaException {
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
                e.setFecha(resultado.getDate("fecha"));
                e.setLocalidad(resultado.getString("localidad"));
                e.setCapacidad(resultado.getInt("capacidad"));
                e.setVenue(resultado.getString("venue"));
                e.setDescripcion(resultado.getString("descripcion"));
                return e;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar el boleto con id: " + id);
        }
        return null;
    }
    
    public List<Evento> consultar() throws PersistenciaException {
        List<Evento> listaEventos = new ArrayList<>();
        String consultarEventos = "SELECT * FROM Eventos";
        try (Connection bd = conexion.crearConexion(); PreparedStatement consulta = bd.prepareStatement(consultarEventos); ResultSet resultados = consulta.executeQuery()) {
            while (resultados.next()) {
                Evento e = new Evento();
                e.setIdEvento(resultados.getInt("id_evento"));
                e.setNombre(resultados.getString("nombre"));
                e.setFecha(resultados.getDate("fecha"));
                e.setLocalidad(resultados.getString("localidad"));
                e.setCapacidad(resultados.getInt("capacidad"));
                e.setVenue(resultados.getString("venue"));
                e.setDescripcion(resultados.getString("descripcion"));
                listaEventos.add(e);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo encontrar los boletos");
        }
        return listaEventos;
    }
    
    public boolean existeEvento(String nombre) throws PersistenciaException {
        try {
            Connection bd = conexion.crearConexion();
            String query = "SELECT COUNT(*) FROM Eventos WHERE nombre = ?";
            PreparedStatement consulta = bd.prepareStatement(query);
            consulta.setString(1, nombre);
            
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                return resultado.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar si el evento existe.");
        }
        return false;
    }
    
    public Evento consultarPorNombre(String nombre) throws PersistenciaException {
        String buscarEvento = "SELECT * FROM Eventos WHERE nombre = ?";
        try (Connection bd = conexion.crearConexion(); PreparedStatement busqueda = bd.prepareStatement(buscarEvento)) {
            
            busqueda.setString(1, nombre);
            ResultSet resultado = busqueda.executeQuery();
            
            if (resultado.next()) {
                Evento evento = new Evento();
                evento.setIdEvento(resultado.getInt("id_evento"));
                evento.setNombre(resultado.getString("nombre"));
                evento.setFecha(resultado.getDate("fecha"));
                evento.setLocalidad(resultado.getString("localidad"));
                evento.setCapacidad(resultado.getInt("capacidad"));
                evento.setVenue(resultado.getString("venue"));
                evento.setDescripcion(resultado.getString("descripcion"));
                return evento;
            } else {
                throw new PersistenciaException("No se encontró el evento con nombre: " + nombre);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al consultar el evento con nombre: " + nombre, e);
        }
    }
}
