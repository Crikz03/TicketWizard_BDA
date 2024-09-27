/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import excepciones.PersistenciaException;
import java.util.List;
import objetos.Apartado;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con los
 * apartados en el sistema. Proporciona un método para consultar los apartados
 * de un usuario específico.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public interface IApartadoDAO {

    /**
     * Consulta los apartados asociados a un usuario específico.
     *
     * @param idUsuario El identificador del usuario para el cual se desean
     * consultar los apartados.
     * @return Una lista de objetos Apartado que representan los apartados del
     * usuario.
     * @throws PersistenciaException Si ocurre un error en la persistencia de
     * datos al realizar la consulta.
     */
    List<Apartado> consultar(int idUsuario) throws PersistenciaException;
}
