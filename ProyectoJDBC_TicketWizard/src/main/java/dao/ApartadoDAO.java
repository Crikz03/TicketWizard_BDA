/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excepciones.PersistenciaException;
import interfaces.IApartadoDAO;
import interfaces.IConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetos.Apartado;

/**
 * Clase que implementa la interfaz IApartadoDAO para realizar operaciones CRUD
 * relacionadas con los apartados en la base de datos. Se encarga de consultar
 * los apartados asociados a un usuario específico.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class ApartadoDAO implements IApartadoDAO {

    private IConexion conexion; //La variable que contendra la conexion de la base de datos.

    /**
     * Constructor que recibe una implementación de la interfaz IConexion para
     * manejar la conexión a la base de datos.
     *
     * @param conexion Objeto que proporciona la conexión a la base de datos.
     */
    public ApartadoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Consulta los apartados de boletos asociados a un usuario específico.
     *
     * @param idUsuario El ID del usuario del cual se desean consultar los
     * apartados.
     * @return Una lista de objetos Apartado que representan los apartados
     * encontrados para el usuario.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<Apartado> consultar(int idUsuario) throws PersistenciaException {
        List<Apartado> apartados = new ArrayList<>();
        try {
            // Crear la conexión a la base de datos
            Connection bd = conexion.crearConexion();

            // Consulta SQL para buscar los apartados del usuario
            String buscarApartados = "SELECT * FROM Apartados WHERE id_usuario = ?";

            // Preparar la consulta con el parámetro idUsuario
            PreparedStatement busqueda = bd.prepareStatement(buscarApartados);
            busqueda.setInt(1, idUsuario);

            // Ejecutar la consulta y obtener los resultados
            ResultSet resultados = busqueda.executeQuery();

            // Iterar sobre los resultados y crear objetos Apartado
            while (resultados.next()) {
                Apartado apartado = new Apartado();
                apartado.setIdBoleto(resultados.getInt("id_boleto"));
                apartado.setIdUsuario(resultados.getInt("id_usuario"));
                apartados.add(apartado);
            }

            // Cerrar los recursos utilizados
            resultados.close();
            busqueda.close();
            bd.close();

        } catch (SQLException e) {
            // En caso de error en la consulta, lanzar una excepción personalizada
            throw new PersistenciaException("No se pudo consultar el apartado con id: " + idUsuario, e);
        }
        return apartados;
    }
}
