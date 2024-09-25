/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.util.Date;

/**
 * Clase que representa un usuario en el sistema. Un usuario tiene un
 * identificador único, información personal y de contacto, así como información
 * sobre su rol en el sistema y su saldo.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 *
 */
public class Usuario {

    private int idUsuario; // Identificador único del usuario
    private String nombres; // Nombres del usuario
    private String apellidoPaterno; // Apellido paterno del usuario
    private String apellidoMaterno; // Apellido materno del usuario
    private String correo; // Correo electrónico del usuario
    private String contrasena; // Contraseña del usuario
    private String calle; // Dirección: calle del usuario
    private String numeroExterior; // Dirección: número exterior del usuario
    private String colonia; // Dirección: colonia del usuario
    private int edad; // Edad del usuario
    private Date fechaNacimiento; // Fecha de nacimiento del usuario
    private double saldo; // Saldo actual en la cuenta del usuario
    private boolean administrador; // Indica si el usuario es administrador (true) o no (false)

    // Constructor vacío
    public Usuario() {
    }

    /**
     * Constructor que inicializa un objeto Usuario con todos los parámetros.
     *
     * @param idUsuario Identificador único del usuario
     * @param nombres Nombres del usuario
     * @param apellidoPaterno Apellido paterno del usuario
     * @param apellidoMaterno Apellido materno del usuario
     * @param correo Correo electrónico del usuario
     * @param contrasena Contraseña del usuario
     * @param calle Dirección: calle del usuario
     * @param numeroExterior Dirección: número exterior del usuario
     * @param colonia Dirección: colonia del usuario
     * @param edad Edad del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     * @param saldo Saldo actual del usuario
     * @param administrador Indica si el usuario es administrador o no
     */
    public Usuario(int idUsuario, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena, String calle, String numeroExterior, String colonia, int edad, Date fechaNacimiento, double saldo, boolean administrador) {
        this.idUsuario = idUsuario;
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
     * Constructor que inicializa un objeto Usuario sin el id. Se puede usar
     * para crear un usuario antes de asignarle un ID en la base de datos.
     *
     * @param nombres Nombres del usuario
     * @param apellidoPaterno Apellido paterno del usuario
     * @param apellidoMaterno Apellido materno del usuario
     * @param correo Correo electrónico del usuario
     * @param contrasena Contraseña del usuario
     * @param calle Dirección: calle del usuario
     * @param numeroExterior Dirección: número exterior del usuario
     * @param colonia Dirección: colonia del usuario
     * @param edad Edad del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     * @param saldo Saldo actual del usuario
     * @param administrador Indica si el usuario es administrador o no
     */
    public Usuario(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasena, String calle, String numeroExterior, String colonia, int edad, Date fechaNacimiento, double saldo, boolean administrador) {
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

    // Getters y Setters
    /**
     * Obtiene el identificador único del usuario.
     *
     * @return el idUsuario del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param idUsuario el nuevo idUsuario para el usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene los nombres del usuario.
     *
     * @return los nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario.
     *
     * @param nombres los nuevos nombres para el usuario
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido paterno del usuario.
     *
     * @return el apellido paterno del usuario
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece el apellido paterno del usuario.
     *
     * @param apellidoPaterno el nuevo apellido paterno para el usuario
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del usuario.
     *
     * @return el apellido materno del usuario
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellido materno del usuario.
     *
     * @param apellidoMaterno el nuevo apellido materno para el usuario
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return el correo electrónico del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo el nuevo correo electrónico para el usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena la nueva contraseña para el usuario
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene la calle de la dirección del usuario.
     *
     * @return la calle del usuario
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Establece la calle de la dirección del usuario.
     *
     * @param calle la nueva calle para el usuario
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número exterior de la dirección del usuario.
     *
     * @return el número exterior del usuario
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Establece el número exterior de la dirección del usuario.
     *
     * @param numeroExterior el nuevo número exterior para el usuario
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * Obtiene la colonia de la dirección del usuario.
     *
     * @return la colonia del usuario
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Establece la colonia de la dirección del usuario.
     *
     * @param colonia la nueva colonia para el usuario
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return la edad del usuario
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad la nueva edad para el usuario
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return la fecha de nacimiento del usuario
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento la nueva fecha de nacimiento para el usuario
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el saldo actual del usuario.
     *
     * @return el saldo del usuario
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Establece el saldo del usuario.
     *
     * @param saldo el nuevo saldo para el usuario
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtiene el estado de administrador del usuario.
     *
     * @return true si el usuario es administrador, false en caso contrario
     */
    public boolean getAdministrador() {
        return administrador;
    }

    /**
     * Establece el estado de administrador del usuario.
     *
     * @param administrador true si el usuario debe ser administrador, false en
     * caso contrario
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    /**
     * Método toString para representar el objeto Usuario como una cadena de
     * texto.
     *
     * @return Una representación en cadena de los atributos del usuario
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombres=").append(nombres);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", correo=").append(correo);
        sb.append(", edad=").append(edad);
        sb.append(", saldo=").append(saldo);
        sb.append(", administrador=").append(administrador);
        sb.append('}');
        return sb.toString();
    }

}
