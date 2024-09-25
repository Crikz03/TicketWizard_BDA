/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interfaz para la gestión de conexiones a la base de datos. Define un método
 * para crear y manejar conexiones a la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IConexion {

    /**
     * Crea y devuelve una conexión a la base de datos.
     *
     * @return una instancia de Connection que representa la conexión a la base
     * de datos
     * @throws SQLException si ocurre un error al intentar establecer la
     * conexión
     */
    public Connection crearConexion() throws SQLException;
}
