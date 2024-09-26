/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dtos.ApartadoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author lalo_
 */
public interface IApartadoBO {

    List<ApartadoDTO> consultar(int id) throws NegocioException;
    
}
