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
 * Clase que gestiona la conexión a la base de datos MySQL para la aplicación
 * TicketWizard. Implementa la interfaz IConexion para establecer conexiones a
 * la base de datos utilizando JDBC.
 *
 * Esta clase se utiliza para crear y devolver una conexión a la base de datos
 * de forma centralizada y manejar posibles errores de conexión.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class ConexionBD implements IConexion {

    private String cadenaConex = "jdbc:mysql://127.0.0.1:3306/ticketwizard"; // URL de conexión a la base de datos
    private String usuario = "Paulina"; // Nombre de usuario de la base de datos
    private String pass = "leydemurphy"; // Contraseña del usuario de la base de datos

    /**
     * Método que crea una conexión a la base de datos MySQL. Utiliza los
     * parámetros de conexión definidos en los atributos cadenaConex, usuario y
     * pass.
     *
     * @return Una conexión activa a la base de datos, o null si ocurre un
     * error.
     * @throws SQLException Si ocurre un error al intentar conectarse a la base
     * de datos.
     */
    @Override
    public Connection crearConexion() throws SQLException {
        try {
            Connection c = DriverManager.getConnection(cadenaConex, usuario, pass);
            return c;
        } catch (SQLException e) {
            System.out.println("Hubo error al conectar con la base de datos: " + e);
        }
        return null;
    }
}
