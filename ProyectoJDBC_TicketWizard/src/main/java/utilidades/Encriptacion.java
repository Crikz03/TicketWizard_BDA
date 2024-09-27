/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Clase utilitaria para la encriptación de contraseñas utilizando el algoritmo
 * BCrypt. Proporciona métodos para encriptar contraseñas y verificar
 * contraseñas encriptadas.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class Encriptacion {

    public static final int COST = 12;

    private Encriptacion() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Encripta una contraseña en forma de cadena.
     *
     * @param password La contraseña en texto plano a encriptar.
     * @return La contraseña encriptada.
     */
    public static String encriptarPassword(String password) {
        return BCrypt.withDefaults().hashToString(COST, password.toCharArray());
    }

    /**
     * Encripta una contraseña en forma de arreglo de caracteres.
     *
     * @param password La contraseña en texto plano a encriptar.
     * @return La contraseña encriptada.
     */
    public static String encriptarPassword(char[] password) {
        return BCrypt.withDefaults().hashToString(COST, password);
    }

    /**
     * Verifica si una contraseña en texto plano coincide con una contraseña
     * encriptada.
     *
     * @param password La contraseña en texto plano a verificar.
     * @param hashedPassword La contraseña encriptada con la que se verificará.
     * @return true si la contraseña coincide, false de lo contrario.
     */
    public static boolean verificarPasswordConHash(String password, String hashedPassword) {
        char[] prueba = password.toCharArray();
        BCrypt.Result result = BCrypt.verifyer().verify(prueba, hashedPassword);
        return result.verified;
    }
}
