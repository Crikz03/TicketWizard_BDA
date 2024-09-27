/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Clase utilitaria para mostrar diálogos de mensajes en la interfaz de usuario.
 * Proporciona métodos para mostrar mensajes de error, éxito y para solicitar
 * input del usuario.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class Dialogos {

    private Dialogos() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Muestra un mensaje de error sobre el componente especificado.
     *
     * @param componente El componente padre para el diálogo.
     * @param msg El mensaje de error a mostrar.
     */
    public static void mostrarMensajeError(
            final Component componente,
            final String msg
    ) {
        JOptionPane.showMessageDialog(
                componente,
                msg,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Muestra un mensaje de éxito sobre el componente especificado.
     *
     * @param componente El componente padre para el diálogo.
     * @param msg El mensaje de éxito a mostrar.
     */
    public static void mostrarMensajeExito(
            final Component componente,
            final String msg
    ) {
        JOptionPane.showMessageDialog(
                componente,
                msg,
                "Éxito",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    /**
     * Pide input al usuario y lo regresa.
     *
     * @param componente El componente padre para el diálogo.
     * @param titulo El título del diálogo.
     * @param texto El texto que se mostrará en el cuadro de entrada.
     * @return Input del usuario, o null si se cancela.
     */
    public static String pedirInputUsuario(
            final Component componente,
            final String titulo,
            final String texto
    ) {
        return JOptionPane.showInputDialog(
                componente,
                texto,
                titulo,
                JOptionPane.QUESTION_MESSAGE
        );
    }
}
