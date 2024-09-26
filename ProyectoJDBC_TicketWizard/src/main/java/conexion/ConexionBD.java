/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import interfaces.IConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class ConexionBD implements IConexion {

    private String cadenaConex = "jdbc:mysql://localhost/ticketwizard";
    private String usuario = "root";
    private String pass = "itson";

    @Override
    public Connection crearConexion() throws SQLException{
        try {
            Connection c = DriverManager.getConnection(cadenaConex, usuario, pass);
            return c;
        } catch (SQLException e) {
            System.out.println("Hubo error al conectar con la base de datos: " + e);
        }
        return null;
    }

}
