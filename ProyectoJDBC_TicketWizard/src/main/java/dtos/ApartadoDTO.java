/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author lalo_
 */
public class ApartadoDTO {
    private int idBoleto;
    private int idUsuario; 

    public ApartadoDTO() {
    }

    public ApartadoDTO(int idBoleto, int idUsuario) {
        this.idBoleto = idBoleto;
        this.idUsuario = idUsuario;
    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
