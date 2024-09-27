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
 * Clase que implementa la lógica de negocio para la gestión de apartados. Se
 * encarga de interactuar con el DAO para realizar operaciones sobre los
 * apartados en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class ApartadoBO implements IApartadoBO {

    private IConexion conexion; // Interfaz para manejar la conexión a la base de datos.
    private IApartadoDAO apartadoDao; // Interfaz para las operaciones de acceso a datos de Apartados.

    /**
     * Constructor de la clase ApartadoBO. Inicializa la conexión y el DAO de
     * Apartado.
     */
    public ApartadoBO() {
        this.conexion = new ConexionBD(); // Crea una nueva instancia de conexión a la base de datos.
        this.apartadoDao = new ApartadoDAO(conexion); // Crea una nueva instancia del DAO de Apartado.
    }

    /**
     * Consulta los apartados asociados a un identificador específico.
     *
     * @param id El identificador del usuario cuyas reservas se desean
     * consultar.
     * @return Una lista de objetos ApartadoDTO que representan los apartados
     * del usuario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    public List<ApartadoDTO> consultar(int id) throws NegocioException {
        try {
            List<Apartado> apartados = apartadoDao.consultar(id); // Consulta los apartados desde el DAO.
            List<ApartadoDTO> apartadosDTO = ConvertidorGeneral.convertidoraListaDTO(apartados, ApartadoDTO.class); // Convierte la lista de Apartados a ApartadoDTO.

            return apartadosDTO; // Retorna la lista de DTOs.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio en caso de error en la persistencia.
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }
}
