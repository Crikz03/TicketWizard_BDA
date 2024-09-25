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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import objetos.Boleto;
import utilidades.EstadoAdquisicion;

/**
 *
 * @author Chris
 */
public class BoletoBO implements IBoletoBO {

    private IBoletoDAO boletodao;
    private IConexion conexion;

    public BoletoBO() {
        this.conexion = new ConexionBD();
        this.boletodao = new BoletoDAO(conexion);
    }

    @Override
    public boolean agregar(BoletoDTO boleto) throws NegocioException {
        try {
            boletodao.agregar(ConvertidorGeneral.convertidorEntidad(boleto, Boleto.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo agregar el boleto con id:" + boleto.getIdBoleto());
        }
    }

    @Override
    public boolean eliminar(int id) throws NegocioException {
        try {
            boletodao.eliminar(id);
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo eliminar el boleto con id:" + id);
        }
    }

    @Override
    public boolean actualizar(BoletoDTO boleto) throws NegocioException {
        try {
            boletodao.actualizar(ConvertidorGeneral.convertidorEntidad(boleto, Boleto.class));
            return true;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo actualizar el boleto con id:" + boleto.getIdBoleto());
        }
    }

    @Override
    public BoletoDTO consultar(int id) throws NegocioException {
        try {
            Boleto boleto = boletodao.consultar(id);
            BoletoDTO boletodto = ConvertidorGeneral.convertidoraDTO(boleto, BoletoDTO.class);
            return boletodto;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo encontrar el id: " + id);
        }
    }

    @Override
    public List<BoletoDTO> consultar() throws NegocioException {
        try {
            List<Boleto> boletos = boletodao.consultar();
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);

            return boletosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }
    @Override
    public List<BoletoDTO> consultarAsignados() throws NegocioException {
        try {
            List<Boleto> boletos = boletodao.consultarAsignados();
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);

            return boletosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }

    @Override
    public boolean comprarBoleto(int idBoleto, String numSerie, double precio, EstadoAdquisicion estadoAdquisicion, int idUsuario) throws NegocioException {
        try {
            Connection bd = conexion.crearConexion();
            String procedimiento = "{CALL ComprarBoleto(?, ?, ?, ?, ?)}";
            PreparedStatement stmt = bd.prepareStatement(procedimiento);

            stmt.setInt(1, idBoleto);
            stmt.setString(2, numSerie);
            stmt.setDouble(3, precio);
            stmt.setObject(4, estadoAdquisicion);
            stmt.setInt(5, idUsuario);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean crearBoletos(int numeroFilas, int numeroAsientosPorFila, int idEvento,double precio) throws NegocioException {
        try {
            return boletodao.crearBoletos(numeroFilas, numeroAsientosPorFila, idEvento,precio);
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron crear los boletos: " + e.getMessage());
        }
    }
    public void asignarBoletos(List<Boleto> boletosSeleccionados) throws PersistenciaException {
        // Validar que la lista de asientos no esté vacía
        if (boletosSeleccionados == null || boletosSeleccionados.isEmpty()) {
            throw new PersistenciaException("No hay boletos seleccionados para asignar.");
        }

        // Llamar al método del DAO para asignar los asientos
        boletodao.asignarBoletosDAO(boletosSeleccionados);
    }
    public List<BoletoDTO> consultarPorEvento(int idEvento) throws NegocioException {
        try {
            List<Boleto> boletos = boletodao.consultarPorEvento(idEvento);
            List<BoletoDTO> boletosDTO = ConvertidorGeneral.convertidoraListaDTO(boletos, BoletoDTO.class);
            return boletosDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudieron consultar los boletos.");
        }
    }
}
