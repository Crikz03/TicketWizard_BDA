/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.BoletoDTO;
import excepciones.NegocioException;
import java.util.List;
import utilidades.EstadoAdquisicion;

/**
 *
 * @author Chris
 */
public interface IBoletoBO {
    boolean agregar(BoletoDTO boleto) throws NegocioException;

    boolean eliminar(int id) throws NegocioException;

    boolean actualizar(BoletoDTO boleto) throws NegocioException;

    BoletoDTO consultar(int id) throws NegocioException;

    List<BoletoDTO> consultar() throws NegocioException;

    boolean comprarBoleto(int idBoleto, String numSerie, double precio, EstadoAdquisicion estadoAdquisicion, int idUsuario) throws NegocioException;
}
