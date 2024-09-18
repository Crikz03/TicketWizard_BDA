/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import javax.swing.JFrame;

/**
 *
 * @author Chris
 */
public class Forms {

    private Forms() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends JFrame> void cargarForm(
            final T cargar,
            final JFrame actual
    ) {
        if (cargar != null) {
            actual.setVisible(false);
            cargar.setVisible(true);
            return;
        }
        throw new InstantiationError("Form no instanciado");
    }

    public static <T extends JFormActualizado> void cargarForm(T cargar, JFrame actual) {
        if (cargar != null) {
            actual.setVisible(false);
            cargar.actualizaFrame();
            cargar.setVisible(true);
            return;
        }
        throw new InstantiationError("Form no instanciado");
    }

    public static void regresar(JFormActualizado frmAnterior, JFrame actual) {
        frmAnterior.actualizaFrame();
        frmAnterior.setVisible(true);
        actual.setVisible(false);
    }
}
