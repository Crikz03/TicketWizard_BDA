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
 * Clase que implementa la interfaz IEventoDAO para realizar operaciones CRUD
 * relacionadas con los eventos en la base de datos. Se encarga de gestionar las
 * operaciones necesarias para la creación, lectura, actualización y eliminación
 * de eventos, así como la consulta de información relacionada con ellos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class EventoDAO implements IEventoDAO {

    private IConexion conexion; //La variable que contendra la conexion de la base de datos.

    /**
     * Constructor de la clase EventoDAO que inicializa la conexión a la base de
     * datos.
     *
     * @param conexion Objeto que implementa la interfaz IConexion para
     * gestionar la conexión.
     */
    public EventoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Agrega un nuevo evento a la base de datos.
     *
     * @param evento El objeto Evento que se desea agregar.
     * @return true si el evento fue agregado exitosamente.
     * @throws PersistenciaException Si ocurre un error al intentar agregar el
     * evento.
     */
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

    /**
     * Elimina un evento de la base de datos utilizando su ID.
     *
     * @param id El ID del evento a eliminar.
     * @return true si el evento fue eliminado exitosamente.
     * @throws PersistenciaException Si ocurre un error al intentar eliminar el
     * evento.
     */
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

    /**
     * Actualiza la información de un evento existente en la base de datos.
     *
     * @param evento El objeto Evento que contiene la nueva información.
     * @return true si el evento fue actualizado exitosamente.
     * @throws PersistenciaException Si ocurre un error al intentar actualizar
     * el evento.
     */
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

    /**
     * Consulta un evento específico de la base de datos utilizando su ID.
     *
     * @param id El ID del evento a consultar.
     * @return Un objeto Evento que representa el evento encontrado.
     * @throws PersistenciaException Si ocurre un error al intentar consultar el
     * evento.
     */
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

    /**
     * Consulta todos los eventos disponibles en la base de datos.
     *
     * @return Una lista de objetos Evento que representan todos los eventos.
     * @throws PersistenciaException Si ocurre un error al intentar consultar
     * los eventos.
     */
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

    /**
     * Verifica si un evento con el nombre especificado existe en la base de
     * datos.
     *
     * @param nombre El nombre del evento a verificar.
     * @return true si el evento existe, false en caso contrario.
     * @throws PersistenciaException Si ocurre un error al intentar verificar la
     * existencia del evento.
     */
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

    /**
     * Consulta un evento específico de la base de datos utilizando su nombre.
     *
     * @param nombre El nombre del evento a consultar.
     * @return Un objeto Evento que representa el evento encontrado.
     * @throws PersistenciaException Si ocurre un error al intentar consultar el
     * evento.
     */
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
