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
 * Interfaz que define las operaciones relacionadas con la gestión de apartados
 * en el sistema. Proporciona un método para consultar los apartados de un
 * usuario específico.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IApartadoBO {

    /**
     * Consulta los apartados asociados a un usuario específico.
     *
     * @param id El identificador del usuario para el cual se desean consultar
     * los apartados.
     * @return Una lista de objetos ApartadoDTO que representan los apartados
     * del usuario.
     * @throws NegocioException Si ocurre un error en la lógica de negocio al
     * realizar la consulta.
     */
    List<ApartadoDTO> consultar(int id) throws NegocioException;
}
