/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.UsuarioDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz para la gestión de usuarios en el nivel de negocio. Define los
 * métodos necesarios para agregar, actualizar y consultar usuarios, así como
 * para verificar información relacionada con ellos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IUsuarioBO {

    /**
     * Agrega un nuevo usuario al sistema.
     *
     * @param usuario el objeto UsuarioDTO que representa al usuario a agregar
     * @return true si el usuario se agregó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean agregar(UsuarioDTO usuario) throws NegocioException;

    /**
     * Actualiza la información de un usuario existente en el sistema.
     *
     * @param usuario el objeto UsuarioDTO que contiene los datos actualizados
     * del usuario
     * @return true si el usuario se actualizó exitosamente, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean actualizar(UsuarioDTO usuario) throws NegocioException;

    /**
     * Consulta todos los usuarios en el sistema.
     *
     * @return una lista de objetos UsuarioDTO que representan todos los
     * usuarios
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    List<UsuarioDTO> consultar() throws NegocioException;

    /**
     * Consulta un usuario por su dirección de correo electrónico.
     *
     * @param correo la dirección de correo electrónico del usuario a consultar
     * @return el objeto UsuarioDTO que representa al usuario, o null si no
     * existe
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    UsuarioDTO consultarPorCorreo(String correo) throws NegocioException;

    /**
     * Consulta un usuario por su identificador.
     *
     * @param id el identificador del usuario a consultar
     * @return el objeto UsuarioDTO que representa al usuario, o null si no
     * existe
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    UsuarioDTO consultar(int id) throws NegocioException;

    /**
     * Verifica si un correo electrónico ya existe en el sistema.
     *
     * @param correo la dirección de correo electrónico a verificar
     * @return true si el correo existe, false en caso contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean existeCorreo(String correo) throws NegocioException;

    /**
     * Obtiene el saldo actual de un usuario.
     *
     * @param idUsuario el identificador del usuario cuyo saldo se va a obtener
     * @return el saldo actual del usuario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    double obtenerSaldoActual(int idUsuario) throws NegocioException;

    /**
     * Verifica si un usuario puede agregar una cantidad de saldo específica.
     *
     * @param usuario el objeto UsuarioDTO que representa al usuario
     * @param montoAgregar la cantidad de saldo a agregar
     * @return true si el usuario puede agregar el saldo, false en caso
     * contrario
     * @throws NegocioException si ocurre un error en la lógica de negocio
     */
    boolean puedeAgregarSaldo(UsuarioDTO usuario, double montoAgregar) throws NegocioException;
}
