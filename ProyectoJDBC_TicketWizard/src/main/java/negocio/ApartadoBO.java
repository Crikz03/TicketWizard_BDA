/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.ApartadoDAO;
import dtos.ApartadoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IApartadoBO;
import interfaces.IApartadoDAO;
import interfaces.IConexion;
import java.util.List;
import objetos.Apartado;

/**
 *
 * @author lalo_
 */
public class ApartadoBO implements IApartadoBO {

    private IConexion conexion;
    private IApartadoDAO apartadoDao;

    public ApartadoBO() {
        this.conexion = new ConexionBD();
        this.apartadoDao = new ApartadoDAO(conexion);
    }

    public List<ApartadoDTO> consultar(int id) throws NegocioException {
        try {
            List<Apartado> apartados = apartadoDao.consultar(id);
            List<ApartadoDTO> apartadosDTO = ConvertidorGeneral.convertidoraListaDTO(apartados, ApartadoDTO.class);

            return apartadosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }
}
