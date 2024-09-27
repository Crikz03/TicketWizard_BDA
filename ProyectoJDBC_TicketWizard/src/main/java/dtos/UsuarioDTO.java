/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.util.Date;

/**
 * Clase que representa a un usuario del sistema de gestión de boletos. Esta
 * clase contiene información personal del usuario, así como su saldo y rol de
 * administrador. Se utiliza para gestionar la información de los usuarios
 * dentro de la aplicación.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class UsuarioDTO {

    private int idUsuario; // Identificador único del usuario
    private String nombres; // Nombres del usuario
    private String apellidoPaterno; // Apellido paterno del usuario
    private String apellidoMaterno; // Apellido materno del usuario
    private String correo; // Correo electrónico del usuario
    private String contrasena; // Contraseña del usuario (debería estar encriptada)
    private String calle; // Calle de la dirección del usuario
    private String numeroExterior; // Número exterior de la dirección del usuario
    private String colonia; // Colonia de la dirección del usuario
    private int edad; // Edad del usuario
    private Date fechaNacimiento; // Fecha de nacimiento del usuario
    private double saldo; // Saldo disponible del usuario
    private boolean administrador; // Indica si el usuario es administrador

    /**
     * Constructor vacío para crear un objeto UsuarioDTO sin parámetros
     * iniciales. Se utiliza cuando los datos del usuario aún no están
     * disponibles.
     */
    public UsuarioDTO() {
    }

    /**
     * Constructor con parámetros para crear un objeto UsuarioDTO. Se utiliza
     * cuando se tiene la información completa del usuario.
     *
     * @param nombres Nombres del usuario.
     * @param apellidoPaterno Apellido paterno del usuario.
     * @param apellidoMaterno Apellido materno del usuario.
     * @param correo Correo electrónico del usuario.
     * @param contrasena Contraseña del usuario.
     * @param calle Calle de la dirección del usuario.
     * @param numeroExterior Número exterior de la dirección del usuario.
     * @param colonia Colonia de la dirección del usuario.
     * @param edad Edad del usuario.
     * @param fechaNacimiento Fecha de nacimiento del usuario.
     * @param saldo Saldo disponible del usuario.
     * @param administrador Indica si el usuario es administrador.
     */
    public UsuarioDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena, String calle, String numeroExterior, String colonia, int edad, Date fechaNacimiento, double saldo, boolean administrador) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasena = contrasena;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.colonia = colonia;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
        this.administrador = administrador;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return El identificador del usuario.
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param idUsuario El identificador del usuario a asignar.
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene los nombres del usuario.
     *
     * @return Los nombres del usuario.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario.
     *
     * @param nombres Los nombres del usuario a asignar.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del usuario.
     *
     * @return El apellido paterno del usuario.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del usuario.
     *
     * @param apellidoPaterno El apellido paterno del usuario a asignar.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del usuario.
     *
     * @return El apellido materno del usuario.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del usuario.
     *
     * @param apellidoMaterno El apellido materno del usuario a asignar.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo El correo electrónico del usuario a asignar.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena La contraseña del usuario a asignar.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la calle de la dirección del usuario.
     *
     * @return La calle de la dirección del usuario.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección del usuario.
     *
     * @param calle La calle de la dirección del usuario a asignar.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número exterior de la dirección del usuario.
     *
     * @return El número exterior de la dirección del usuario.
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Establece el número exterior de la dirección del usuario.
     *
     * @param numeroExterior El número exterior de la dirección del usuario a
     * asignar.
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * Obtiene la colonia de la dirección del usuario.
     *
     * @return La colonia de la dirección del usuario.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia de la dirección del usuario.
     *
     * @param colonia La colonia de la dirección del usuario a asignar.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return La edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad La edad del usuario a asignar.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return La fecha de nacimiento del usuario.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento La fecha de nacimiento del usuario a asignar.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el saldo del usuario.
     *
     * @return El saldo del usuario.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo del usuario.
     *
     * @param saldo El saldo del usuario a asignar.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Verifica si el usuario es administrador.
     *
     * @return true si el usuario es administrador, false en caso contrario.
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * Establece si el usuario es administrador.
     *
     * @param administrador true si el usuario debe ser administrador, false en
     * caso contrario.
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
}
