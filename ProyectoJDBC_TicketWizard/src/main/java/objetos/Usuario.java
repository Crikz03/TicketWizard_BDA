/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.util.Date;

/**
 *
 * @author pauli
 */
public class Usuario {

    private int idUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasena;
    private String calle;
    private String numeroExterior;
    private String colonia;
    private int edad;
    private Date fechaNacimiento;
    private double saldo;
    private boolean administrador;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
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
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

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
