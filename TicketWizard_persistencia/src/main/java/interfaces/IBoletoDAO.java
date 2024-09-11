/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import objetos.Boleto;

/**
 *
 * @author pauli
 */
public interface IBoletoDAO {
    boolean agregar(Boleto boleto);
    boolean eliminar(int id);
    boolean actualizar(Boleto boleto);
    Boleto consultar(int id);
    List<Boleto> consultar();

    boolean comprarBoleto(int idBoleto, String numSerie, double precio, String estadoAdquisicion, int idUsuario);
   
}
