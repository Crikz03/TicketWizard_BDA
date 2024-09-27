/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Clase personalizada de excepción que se utiliza para manejar errores
 * relacionados con la lógica del negocio en la aplicación. Extiende la clase
 * base Exception para proporcionar información más específica sobre las
 * excepciones que pueden ocurrir durante la ejecución del negocio.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class NegocioException extends Exception {

    /**
     * Constructor de la excepción que toma un mensaje.
     *
     * @param message Mensaje que describe la excepción.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepción que toma un mensaje y una causa.
     *
     * @param message Mensaje que describe la excepción.
     * @param cause La causa de la excepción, que puede ser otra excepción.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Obtiene la causa de la excepción.
     *
     * @return La causa de la excepción, que puede ser otro throwable.
     */
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    /**
     * Obtiene el mensaje de la excepción.
     *
     * @return El mensaje que describe la excepción.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
