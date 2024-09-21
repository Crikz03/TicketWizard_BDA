/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import conexion.ConexionBD;
import dao.UsuarioDAO;
import excepciones.PersistenciaException;
import interfaces.IConexion;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import objetos.Usuario;

/**
 *
 * @author Chris
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        Calendar cal = Calendar.getInstance();
        cal.set(1990, Calendar.JANUARY, 1); // Ejemplo de fecha de nacimiento
        Date fechaNacimiento = cal.getTime();

        Usuario nuevoUsuario = new Usuario(
                "Chris", // Nombres
                "Smith", // Apellido Paterno
                "Johnson", // Apellido Materno
                "chris@email.com", // Correo
                "password123", // Contraseña
                "Main Street", // Calle
                "123", // Número Exterior
                "Centro", // Colonia
                33, // Edad
                fechaNacimiento, // Fecha de Nacimiento
                100.0, // Saldo
                false //administrador
        );

        // Crear la conexión a la base de datos
        IConexion conexion = new ConexionBD();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);

        // Intentar agregar el usuario a la base de datos
        boolean resultado = usuarioDAO.agregar(nuevoUsuario);
        if (resultado) {
            System.out.println("Usuario agregado con éxito.");
        } else {
            System.out.println("No se pudo agregar el usuario.");
        }
    }
}
