/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Chris
 */
public class NegocioException extends Exception {

    /**
     * Constructor de excepcion con mensaje
     *
     * @param message Mensaje de excepcion
     */
    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Obtener la causa de la excepcion
     *
     * @return throwable
     */
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }

    /**
     * Obtiene el mensaje de la excepcion
     *
     * @return Mensaje de excepcion
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
