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
 *
 * @author lalo_
 */
public interface IApartadoDAO {

    List<Apartado> consultar(int idUsuario) throws PersistenciaException;
    
}
