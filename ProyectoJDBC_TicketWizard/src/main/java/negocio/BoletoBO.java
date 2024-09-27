/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import conexion.ConexionBD;
import conversiones.ConvertidorGeneral;
import dao.BoletoDAO;
import dtos.BoletoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfaces.IBoletoBO;
import interfaces.IBoletoDAO;
import interfaces.IConexion;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Boleto;
import utilidades.EstadoAdquisicion;
import utilidades.TipoTransaccion;

/**
 * Clase que implementa la lógica de negocio para la gestión de boletos. Se
 * encarga de interactuar con el DAO para realizar operaciones sobre los boletos
 * en la base de datos.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class BoletoBO implements IBoletoBO {

    private IBoletoDAO boletodao; // Interfaz para las operaciones de acceso a datos de Boletos.
    private IConexion conexion; // Interfaz para manejar la conexión a la base de datos.

    /**
     * Constructor de la clase BoletoBO. Inicializa la conexión y el DAO de
     * Boleto.
     */
    public BoletoBO() {
        this.conexion = new ConexionBD(); // Crea una nueva instancia de conexión a la base de datos.
        this.boletodao = new BoletoDAO(conexion); // Crea una nueva instancia del DAO de Boleto.
    }

    /**
     * Agrega un nuevo boleto al sistema.
     *
     * @param boleto El objeto BoletoDTO que representa el boleto a agregar.
     * @return true si el boleto fue agregado exitosamente, false en caso
     * contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    @Override
    public boolean agregar(BoletoDTO boleto) throws NegocioException {
        try {
            // Convierte el BoletoDTO a Boleto y lo agrega a la base de datos.
            boletodao.agregar(ConvertidorGeneral.convertidorEntidad(boleto, Boleto.class));
            return true; // Retorna true si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio en caso de error en la persistencia.
            throw new NegocioException("No se pudo agregar el boleto con id:" + boleto.getIdBoleto());
        }
    }

    /**
     * Elimina un boleto existente del sistema.
     *
     * @param id El identificador del boleto a eliminar.
     * @return true si el boleto fue eliminado exitosamente, false en caso
     * contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    @Override
    public boolean eliminar(int id) throws NegocioException {
        try {
            boletodao.eliminar(id); // Elimina el boleto a través del DAO.
            return true; // Retorna true si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio en caso de error en la persistencia.
            throw new NegocioException("No se pudo eliminar el boleto con id:" + id);
        }
    }

    /**
     * Actualiza la información de un boleto existente en el sistema.
     *
     * @param boleto El objeto BoletoDTO que contiene los nuevos datos del
     * boleto.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    @Override
    public boolean actualizar(BoletoDTO boleto) throws NegocioException {
        try {
            // Convierte el BoletoDTO a Boleto y actualiza la información en la base de datos.
            boletodao.actualizar(ConvertidorGeneral.convertidorEntidad(boleto, Boleto.class));
            return true; // Retorna true si la operación fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio en caso de error en la persistencia.
            throw new NegocioException("No se pudo actualizar el boleto con id:" + boleto.getIdBoleto());
        }
    }

    /**
     * Consulta un boleto específico por su identificador.
     *
     * @param id El identificador del boleto a consultar.
     * @return El objeto BoletoDTO correspondiente al id proporcionado.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    @Override
    public BoletoDTO consultar(int id) throws NegocioException {
        try {
            // Consulta el boleto a través del DAO y convierte el resultado a BoletoDTO.
            Boleto boleto = boletodao.consultar(id);
            BoletoDTO boletodto = ConvertidorGeneral.convertidoraDTO(boleto, BoletoDTO.class);
            return boletodto; // Retorna el BoletoDTO consultado.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio en caso de error en la persistencia.
            throw new NegocioException("No se pudo encontrar el id: " + id);
        }
    }

    @Override
    /**
     * Consulta todos los boletos disponibles en el sistema.
     *
     * @return Una lista de objetos BoletoDTO que representan todos los boletos.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<BoletoDTO> consultar() throws NegocioException {
        try {
            // Obtiene la lista de boletos desde el DAO.
            List<Boleto> boletos = boletodao.consultar();
            // Convierte la lista de boletos a una lista de BoletoDTO.
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);
            return boletosDTO; // Retorna la lista de BoletoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }

    @Override
    /**
     * Consulta los boletos que han sido asignados a usuarios.
     *
     * @return Una lista de objetos BoletoDTO que representan los boletos
     * asignados.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<BoletoDTO> consultarAsignados() throws NegocioException {
        try {
            // Obtiene la lista de boletos asignados desde el DAO.
            List<Boleto> boletos = boletodao.consultarAsignados();
            // Convierte la lista de boletos a una lista de BoletoDTO.
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);
            return boletosDTO; // Retorna la lista de BoletoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }

    @Override
    /**
     * Consulta los boletos asignados a un usuario específico.
     *
     * @param idUsuario El ID del usuario del cual se desean consultar los
     * boletos.
     * @return Una lista de objetos BoletoDTO que representan los boletos del
     * usuario.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<BoletoDTO> consultarIdUsuario(int idUsuario) throws NegocioException {
        try {
            // Obtiene la lista de boletos del usuario desde el DAO.
            List<Boleto> boletos = boletodao.consultarIdUsuario(idUsuario);
            // Convierte la lista de boletos a una lista de BoletoDTO.
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);
            return boletosDTO; // Retorna la lista de BoletoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron consultar los boletos del usuario con id: " + idUsuario);
        }
    }

    /**
     * Permite realizar la compra de un boleto.
     *
     * @param idBoleto El ID del boleto a comprar.
     * @param precio El precio del boleto.
     * @param estadoAdquisicion El estado de adquisición del boleto.
     * @param tipoTransaccion El tipo de transacción realizada.
     * @param idUsuario El ID del usuario que compra el boleto.
     * @param idUsuarioAnteriorDueño El ID del usuario anterior dueño del
     * boleto.
     * @return true si la compra fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    public boolean comprarBoleto(int idBoleto, double precio, EstadoAdquisicion estadoAdquisicion, TipoTransaccion tipoTransaccion, int idUsuario, int idUsuarioAnteriorDueño) throws NegocioException {
        try {
            // Llama al DAO para realizar la compra del boleto.
            return boletodao.comprarBoleto(idBoleto, precio, estadoAdquisicion, tipoTransaccion, idUsuario, idUsuarioAnteriorDueño);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron comprar los boletos: " + e.getMessage());
        }
    }

    /**
     * Crea nuevos boletos para un evento específico.
     *
     * @param numeroFilas El número de filas de asientos.
     * @param numeroAsientosPorFila El número de asientos por fila.
     * @param idEvento El ID del evento para el cual se crean los boletos.
     * @param precio El precio de los nuevos boletos.
     * @return true si la creación fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * negocio.
     */
    public boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento, double precio) throws NegocioException {
        try {
            // Llama al DAO para crear los boletos.
            return boletodao.crearBoletos(numeroFilas, numeroAsientosPorFila, idEvento, precio);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron crear los boletos: " + e.getMessage());
        }
    }

    /**
     * Asigna una lista de boletos seleccionados a un usuario.
     *
     * @param boletosSeleccionados La lista de boletos a asignar.
     * @throws NegocioException Si no hay boletos seleccionados o si ocurre un
     * error en la asignación.
     */
    public void asignarBoletos(List<BoletoDTO> boletosSeleccionados) throws NegocioException {
        // Validar que la lista de boletos seleccionados no esté vacía
        if (boletosSeleccionados == null || boletosSeleccionados.isEmpty()) {
            throw new NegocioException("No hay boletos seleccionados para asignar.");
        }
        try {
            // Llama al método del DAO para asignar los boletos.
            boletodao.asignarBoletosDAO(ConvertidorGeneral.convertidorListaEntidad(boletosSeleccionados, Boleto.class));
        } catch (PersistenciaException ex) {
            // Registra el error en el logger si ocurre un problema en la asignación.
            Logger.getLogger(BoletoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Consulta los boletos disponibles para un evento específico.
     *
     * @param idEvento El ID del evento del cual se desean consultar los
     * boletos.
     * @return Una lista de objetos BoletoDTO que representan los boletos del
     * evento.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<BoletoDTO> consultarPorEvento(int idEvento) throws NegocioException {
        try {
            // Obtiene la lista de boletos del evento desde el DAO.
            List<Boleto> boletos = boletodao.consultarPorEvento(idEvento);
            // Convierte la lista de boletos a una lista de BoletoDTO.
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);
            return boletosDTO; // Retorna la lista de BoletoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }

    /**
     * Aparta un boleto para un usuario específico.
     *
     * @param idBoleto El ID del boleto a apartar.
     * @param idUsuario El ID del usuario que aparta el boleto.
     * @throws NegocioException Si ocurre un error durante la operación de
     * apartar el boleto.
     */
    public void apartarBoleto(int idBoleto, int idUsuario) throws NegocioException {
        try {
            // Llama al DAO para apartar el boleto.
            boletodao.apartarBoleto(idBoleto, idUsuario);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron apartar los boletos: " + e.getMessage());
        }
    }

    /**
     * Libera un boleto previamente apartado.
     *
     * @param idBoleto El ID del boleto a liberar.
     * @throws NegocioException Si ocurre un error durante la operación de
     * liberar el boleto.
     */
    public void liberarBoleto(int idBoleto) throws NegocioException {
        try {
            // Llama al DAO para liberar el boleto.
            boletodao.liberarBoleto(idBoleto);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudieron liberar los boletos: " + e.getMessage());
        }
    }

    /**
     * Obtiene el precio original de un boleto utilizando su número de serie.
     *
     * @param numSerie El número de serie del boleto.
     * @return El precio original del boleto.
     * @throws NegocioException Si ocurre un error al obtener el precio
     * original.
     */
    public double obtenerPrecioOriginal(String numSerie) throws NegocioException {
        try {
            // Llama al DAO para obtener el precio original del boleto.
            return boletodao.obtenerPrecioOriginal(numSerie);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("Error al obtener el precio original del boleto.", e);
        }
    }

    /**
     * Permite la reventa de un boleto.
     *
     * @param idBoleto El ID del boleto a revender.
     * @param precioReventa El precio al cual se desea revender el boleto.
     * @param fechaLimite La fecha límite para la reventa del boleto.
     * @param idUsuario El ID del usuario que realiza la reventa.
     * @return true si la reventa fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la operación de
     * reventa.
     */
    public boolean revenderBoleto(int idBoleto, double precioReventa, Date fechaLimite, int idUsuario) throws NegocioException {
        try {
            // Consulta el boleto desde el DAO.
            Boleto boleto = boletodao.consultar(idBoleto);
            // Convierte el boleto a BoletoDTO.
            BoletoDTO boletodto = ConvertidorGeneral.convertidoraDTO(boleto, BoletoDTO.class);

            // Verifica que el precio de reventa no exceda el 3% del precio original.
            if (precioReventa > boletodto.getPrecioOriginal() * 1.03) {
                throw new NegocioException("El precio de reventa no puede exceder el 3% del precio original");
            }

            // Llama al DAO para realizar la reventa del boleto.
            return boletodao.revenderBoleto(boletodto.getIdBoleto(), precioReventa, fechaLimite, idUsuario);
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("Error en la capa de negocio al revender el boleto", e);
        }
    }

    /**
     * Consulta los boletos en venta para un usuario específico.
     *
     * @param idUsuario El ID del usuario para el cual se desean consultar los
     * boletos en venta.
     * @return Una lista de objetos BoletoDTO que representan los boletos en
     * venta del usuario.
     * @throws NegocioException Si ocurre un error durante la consulta.
     */
    public List<BoletoDTO> consultarBoletosEnVenta(int idUsuario) throws NegocioException {
        try {
            // Obtiene la lista de boletos en venta del usuario desde el DAO.
            List<Boleto> boletos = boletodao.consultarBoletosEnVenta(idUsuario);
            // Convierte la lista de boletos a una lista de BoletoDTO.
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);

            return boletosDTO; // Retorna la lista de BoletoDTO.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo obtener los boletos en venta", e);
        }
    }

    /**
     * Actualiza un boleto para que pueda ser puesto en reventa.
     *
     * @param idBoleto El ID del boleto que se desea actualizar.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws NegocioException Si ocurre un error durante la actualización.
     */
    public boolean actualizarBoletoParaReventa(int idBoleto) throws NegocioException {
        try {
            // Llama al DAO para actualizar el boleto para reventa.
            boletodao.actualizarBoletoParaReventa(idBoleto);
            return true; // Retorna verdadero si la actualización fue exitosa.
        } catch (PersistenciaException e) {
            // Lanza una excepción de negocio si ocurre un error en la persistencia.
            throw new NegocioException("No se pudo actualizar el boleto con id:" + idBoleto);
        }
    }

}
