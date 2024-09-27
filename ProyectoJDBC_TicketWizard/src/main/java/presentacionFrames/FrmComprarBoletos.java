/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacionFrames;

import dtos.BoletoDTO;
import dtos.EventoDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import interfaces.IBoletoBO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import negocio.BoletoBO;
import utilidades.EstadoAdquisicion;
import utilidades.Forms;
import utilidades.TipoTransaccion;

/**
 * Clase que representa la interfaz gráfica del formulario para la compra de
 * boletos. Permite a los usuarios seleccionar eventos, elegir asientos y
 * realizar la transacción para adquirir boletos, gestionando así el proceso de
 * compra.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class FrmComprarBoletos extends javax.swing.JFrame {

    private UsuarioDTO usuarioLoggeado;
    private EventoDTO eventodto;
    private IBoletoBO boletobo;
    private List<BoletoDTO> boletosDisponibles;

    /**
     * Creates new form FrmComprarBoletos
     */
    public FrmComprarBoletos(UsuarioDTO usuarioLoggeado, EventoDTO eventodto) {
        initComponents();
        this.usuarioLoggeado = usuarioLoggeado;
        this.eventodto = eventodto;
        this.boletobo = new BoletoBO();
        this.cargarDatosIniciales();
        // Establecer el modelo de la tabla
        jTable1.setModel(new BoletoTableModel(boletosDisponibles));
        jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Agregar el listener de selección
        jTable1.getSelectionModel().addListSelectionListener(e -> actualizarTotal());
        jTable1.getColumnModel().getSelectionModel().addListSelectionListener(e -> actualizarTotal());

    }

    public class BoletoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Select", "Fila", "Asiento", "Precio"};
        private List<BoletoDTO> boletos;

        public BoletoTableModel(List<BoletoDTO> boletos) {
            this.boletos = boletos;
        }

        @Override
        public int getRowCount() {
            return boletos.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            BoletoDTO boleto = boletos.get(row);
            switch (col) {
                case 0:
                    return boleto.isSelected();
                case 1:
                    return boleto.getFila();
                case 2:
                    return boleto.getAsiento();
                case 3:
                    return String.format("%.2f", boleto.getPrecioOriginal()); // Formatea el precio con dos decimales
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            BoletoDTO boleto = boletos.get(row);
            if (col == 0) {
                boleto.setSelected((Boolean) value); // actualizar el estado del checkbox
                fireTableCellUpdated(row, col); // Notificar que se ha actualizado la celda
                // Llama a actualizarTotal desde el frame
                FrmComprarBoletos.this.actualizarTotal(); // Asegúrate de que esté accesible
            }
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }
    }

    private List<BoletoDTO> adecuarPrecioBoletoReventa(List<BoletoDTO> boletos) {
        for (BoletoDTO boleto : boletos) {
            if (boleto.getIdUsuario() != 0 && boleto.getEn_venta() && boleto.getPrecioReventa() != 0) {
                boleto.setPrecioOriginal(boleto.getPrecioReventa());
            }
        }
        return boletos;
    }

    private void cargarBoletosDisponibles() {
        try {
            boletosDisponibles = boletobo.consultarPorEvento(eventodto.getIdEvento());
            // Filtra boletos que aún no han sido adquiridos
            boletosDisponibles = boletosDisponibles.stream()
                    .filter(boleto -> boleto.getApartado() == false && boleto.getEn_venta() == true)
                    .collect(Collectors.toList());
            boletosDisponibles = adecuarPrecioBoletoReventa(boletosDisponibles);
            // Usa BoletoTableModel en lugar de DefaultTableModel
            jTable1.setModel(new BoletoTableModel(boletosDisponibles));

            // Configura el renderizador para la columna de precio (si es necesario)
            TableColumn precioColumn = jTable1.getColumnModel().getColumn(3);
            precioColumn.setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    if (value instanceof Double) {
                        value = String.format("%.2f", value);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            });
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "No se han podido cargar los boletos disponibles!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTotal() {
        int boletosSeleccionados = 0;
        double total = 0.0;

        for (BoletoDTO boleto : boletosDisponibles) {
            if (boleto.isSelected()) { // Asegúrate de que este método esté funcionando
                boletosSeleccionados++;
                total += boleto.getPrecioOriginal();
            }
        }

        jLabel8.setText("Num de boletos: " + boletosSeleccionados);
        jLabel9.setText("Total: " + total);
    }

    private void cargarDatosIniciales() {
        this.cargarDatosEvento();
        this.cargarBoletosDisponibles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnCompra = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Evento:");

        jLabel2.setText("Nombre Evento");

        jLabel3.setText("Fecha:");

        jLabel4.setText("FechaEvento");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Select", "Fila", "Asiento", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("Seleccionar asientos:");

        btnCompra.setText("Comprar");
        btnCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraActionPerformed(evt);
            }
        });

        jLabel6.setText("Num de boletos:");

        jLabel7.setText("Total:");

        jLabel8.setText("boletos seleccionados");

        jLabel9.setText("total por boletos");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCompra)
                                        .addGap(76, 76, 76)
                                        .addComponent(btnRegresar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompra)
                    .addComponent(btnRegresar))
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraActionPerformed
        try {
            List<BoletoDTO> boletosSeleccionados = new ArrayList<>();
            for (BoletoDTO boleto : boletosDisponibles) {
                if (boleto.isSelected()) {
                    boletosSeleccionados.add(boleto);
                }
            }

            if (boletosSeleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona al menos un boleto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double total = 0;
            for (BoletoDTO boleto : boletosSeleccionados) {
                double precio = boleto.getPrecioOriginal();
                total += precio;
            }
            if (total > usuarioLoggeado.getSaldo()) {
                for (BoletoDTO boleto : boletosSeleccionados) {
                    boletobo.apartarBoleto(boleto.getIdBoleto(), usuarioLoggeado.getIdUsuario());
                }
                JOptionPane.showMessageDialog(this, "Boletos apartados durante 1 minutos. Por favor, agrega saldo a tu cuenta.", "Información", JOptionPane.INFORMATION_MESSAGE);
                new Thread(() -> {
                    try {
                        Thread.sleep(1 * 60 * 1000);
                        for (BoletoDTO boleto : boletosSeleccionados) {
                            boletobo.liberarBoleto(boleto.getIdBoleto());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NegocioException ex) {
                        Logger.getLogger(FrmComprarBoletos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();

                return;
            }

            for (BoletoDTO boleto : boletosSeleccionados) {
                double precio = boleto.getPrecioOriginal();
                int idUsuario = usuarioLoggeado.getIdUsuario(); // Asumiendo que tienes un método para obtener el ID del usuario

                try {
                    boolean exito = boletobo.comprarBoleto(boleto.getIdBoleto(), precio, estadoAdquisicion(boleto.getIdUsuario()), TipoTransaccion.compra, idUsuario, boleto.getIdUsuario());
                    if (exito) {
                        double saldo = usuarioLoggeado.getSaldo() - precio;
                        usuarioLoggeado.setSaldo(saldo);
                        JOptionPane.showMessageDialog(this, "Boleto comprado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al comprar el boleto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NegocioException e) {
                    JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // Actualiza la interfaz de usuario según sea necesario, como limpiar selecciones o actualizar la tabla
            actualizarTotal();
            cargarBoletosDisponibles();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmEventoAsignar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompraActionPerformed

    private EstadoAdquisicion estadoAdquisicion(int idUsuario) {
        if (idUsuario == 0) {
            return EstadoAdquisicion.directo;
        } else {
            return EstadoAdquisicion.reventa;
        }
    }
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        Forms.cargarForm(new FrmMenuPrincipal(usuarioLoggeado), this);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void cargarDatosEvento() {
        jLabel2.setText(eventodto.getNombre());
        jLabel4.setText(eventodto.getFecha().toString());

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompra;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
