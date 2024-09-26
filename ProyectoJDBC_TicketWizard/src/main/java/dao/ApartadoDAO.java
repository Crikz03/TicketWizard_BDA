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
import objetos.Boleto;
import utilidades.EstadoAdquisicion;

/**
 *
 * @author lalo_
 */
public class ApartadoDAO implements IApartadoDAO{
    private IConexion conexion;
    public ApartadoDAO(IConexion conexion){
        this.conexion = conexion;
    }
    public List<Apartado> consultar(int idUsuario) throws PersistenciaException {
        List<Apartado> apartados = new ArrayList<>();
        try {
            Connection bd = conexion.crearConexion();
            String buscarApartados = "SELECT * FROM Apartados WHERE id_usuario = ?";
            PreparedStatement busqueda = bd.prepareStatement(buscarApartados);
            busqueda.setInt(1, idUsuario);
            ResultSet resultados = busqueda.executeQuery();

            while (resultados.next()) {
                Apartado apartado= new Apartado();
                apartado.setIdBoleto(resultados.getInt("id_boleto"));
                apartado.setIdUsuario(resultados.getInt("id_usuario"));
                apartados.add(apartado);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo consultar el apartado con id: " + idUsuario);
        }
        return apartados;
    }
}
