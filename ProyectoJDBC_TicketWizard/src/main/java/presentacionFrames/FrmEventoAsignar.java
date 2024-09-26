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
import interfaces.IEventoBO;
import interfaces.ITransaccionBO;
import interfaces.IUsuarioBO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import negocio.BoletoBO;
import negocio.EventoBO;
import negocio.UsuarioBO;
import utilidades.Forms;

/**
 *
 * @author pauli
 */
public class FrmEventoAsignar extends javax.swing.JFrame {

    private UsuarioDTO usuarioLoggeado;
    private IEventoBO eventobo;
    private ITransaccionBO transaccionbo;
    private IUsuarioBO usuariobo;
    private IBoletoBO boletobo;

    /**
     * Creates new form FrmAgregarSaldo
     */
    public FrmEventoAsignar(UsuarioDTO usuarioLoggeado) {
        initComponents();
        this.usuarioLoggeado = usuarioLoggeado;
        this.eventobo = new EventoBO();
        this.usuariobo = new UsuarioBO();
        this.boletobo= new BoletoBO();
        llenarComboBoxEventos();

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
        jLabel3 = new javax.swing.JLabel();
        labelFecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoletos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        comboBoxEventos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Evento:");

        jLabel3.setText("Fecha:");

        labelFecha.setText("FechaEvento");

        jLabel5.setText("Seleccionar boletos:");

        tblBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Select", "Fila", "Asiento"
            }
        ));
        jScrollPane1.setViewportView(tblBoletos);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "IdUsusario", "Usuario"
            }
        ));
        jScrollPane2.setViewportView(tblUsuarios);

        jLabel8.setText("Seleccionar usuario:");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        comboBoxEventos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEventosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelFecha)
                                .addGap(16, 16, 16))
                            .addComponent(comboBoxEventos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(414, 414, 414))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegresar)))))
                .addGap(225, 225, 225))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnRegresar))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEventosActionPerformed
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        actualizarFechaEvento();
        cargarUsuariosEnTabla();

        List<BoletoDTO> boletos = obtenerBoletosPorEvento(eventoSeleccionado);


        BoletoTableModel tableModel = new BoletoTableModel(boletos);
        tblBoletos.setModel(tableModel); // Suponiendo que jTable1 es tu tabla
        tblBoletos.repaint(); // Actualiza la tabla

    }//GEN-LAST:event_comboBoxEventosActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Forms.cargarForm(new FrmAdministrador(usuarioLoggeado), this);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Obtener el ID del usuario seleccionado
        int rowIndex = tblUsuarios.getSelectedRow();
        if (rowIndex == -1) {
            // No se ha seleccionado un usuario, muestra un mensaje
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario.");
            return;
        }

        int idUsuario = (int) tblUsuarios.getValueAt(rowIndex, 0); // Suponiendo que el ID de usuario está en la primera columna


        List<BoletoDTO> boletosSeleccionados = new ArrayList<>();
        BoletoTableModel model = (BoletoTableModel) tblBoletos.getModel();

        int idEvento = -1; // Inicializa la variable idEvento
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();
        if (eventoSeleccionado != null) {
            try {
                List<EventoDTO> eventos = eventobo.consultar(); // Obtener la lista de eventos nuevamente
                for (EventoDTO evento : eventos) {
                    if (evento.getNombre().equals(eventoSeleccionado)) {
                        // Actualiza el idEvento con el ID del evento encontrado
                        idEvento = evento.getIdEvento();
                        break; // Sale del bucle una vez que encuentra el evento
                    }
                }
            } catch (NegocioException e) {
                e.printStackTrace(); // Manejo de errores
            }
        }

// Verifica si se encontró un idEvento válido antes de continuar
        if (idEvento != -1) {
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0) != null && (Boolean) model.getValueAt(i, 0)) { // Verifica si está seleccionado
                    BoletoDTO boleto = new BoletoDTO(
                            model.getValueAt(i, 1).toString(), // fila
                            model.getValueAt(i, 2).toString(), // asiento
                            idEvento, // Usa el idEvento que obtuviste
                            idUsuario, // Asignar ID del usuario
                            true // Estado seleccionado
                    );
                    boletosSeleccionados.add(boleto);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el evento seleccionado.");
        }


        try {
            List<BoletoDTO> boletos = new ArrayList<>();
            for (BoletoDTO dto : boletosSeleccionados) {
                BoletoDTO boleto = new BoletoDTO();
                // Asigna los valores de dto a asiento
                boleto.setFila(dto.getFila());
                boleto.setAsiento(dto.getAsiento());
                boleto.setIdUsuario(dto.getIdUsuario());
                boleto.setIdEvento(dto.getIdEvento());
                boletos.add(boleto);
            }

            boletobo.asignarBoletos(boletos);
            JOptionPane.showMessageDialog(this, "Boletos asignados exitosamente.");
            Forms.cargarForm(new FrmBoletosAsignados(usuarioLoggeado), this);
        
        } catch (NegocioException ex) {
            Logger.getLogger(FrmEventoAsignar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Método para llenar el comboBox con los eventos
    private void llenarComboBoxEventos() {
        try {
            List<EventoDTO> eventos = eventobo.consultar();  // Llama al método consultar() para obtener los eventos

            // Limpiar cualquier elemento previo en el comboBox
            comboBoxEventos.removeAllItems();

            // Llenar el comboBox con los nombres de los eventos
            for (EventoDTO evento : eventos) {
                comboBoxEventos.addItem(evento.getNombre());  // Asegúrate de que EventoDTO tenga el método getNombre()
            }
        } catch (NegocioException e) {
            e.printStackTrace(); // Manejo de errores, puedes mostrar un mensaje al usuario
        }
    }

    private void actualizarFechaEvento() {
        String eventoSeleccionado = (String) comboBoxEventos.getSelectedItem();

        if (eventoSeleccionado != null) {
            try {
                List<EventoDTO> eventos = eventobo.consultar(); // Obtener la lista de eventos nuevamente
                for (EventoDTO evento : eventos) {
                    if (evento.getNombre().equals(eventoSeleccionado)) {
                        // Actualiza el JLabel con la fecha del evento seleccionado
                        labelFecha.setText(evento.getFecha().toString()); // Ajusta el formato según lo necesites
                        break; // Sale del bucle una vez que encuentra el evento
                    }
                }
            } catch (NegocioException e) {
                e.printStackTrace(); // Manejo de errores
            }
        }
    }

    public class BoletoTableModel extends AbstractTableModel {

        private String[] columnNames = {"Select", "Fila", "Asiento"};
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
                    return boleto.isSelected(); // estado del checkbox
                case 1:
                    return boleto.getFila();    // fila
                case 2:
                    return boleto.getAsiento();  // número de asiento
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            BoletoDTO boleto = boletos.get(row);
            if (col == 0) {
                boleto.setSelected((Boolean) value); // actualizar el estado del checkbox
            }
            fireTableCellUpdated(row, col);
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Class<?> getColumnClass(int c) {
            return (c == 0) ? Boolean.class : String.class; // La primera columna es un booleano
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0; // Solo la primera columna es editable
        }
    }

    private List<BoletoDTO> obtenerBoletosPorEvento(String eventoSeleccionado) {
        try {
            EventoDTO evento = eventobo.consultarPorNombre(eventoSeleccionado);
            if (evento != null) {
                List<BoletoDTO> boletos =boletobo.consultarPorEvento(evento.getIdEvento());
                
                return boletos.stream().filter(boleto ->  boleto.getApartado()==false && boleto.getEn_venta()==true).collect(Collectors.toList());
            }
        } catch (NegocioException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    //Tabla usuarios
    private void llenarTablaProductos(List<UsuarioDTO> usuarioLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblUsuarios.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (usuarioLista != null) {
            usuarioLista.forEach(row -> {
                Object[] fila = new Object[2];
                fila[0] = row.getIdUsuario();
                fila[1] = row.getNombres() + " " + row.getApellidoPaterno() + " " + row.getApellidoMaterno();

                modeloTabla.addRow(fila);
            });
        }
    }

    private void actualizarTablaUsuarios() {
        List<UsuarioDTO> listaUsuarios = null;
        try {
            listaUsuarios = usuariobo.consultar();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) this.tblUsuarios.getModel();
        model.setRowCount(0);
        for (UsuarioDTO usuario : listaUsuarios) {
            Object[] fila = {
                usuario.getIdUsuario(),
                usuario.getNombres() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno()

            };
            model.addRow(fila);
        }
    }

    private void cargarUsuariosEnTabla() {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = this.usuariobo.consultar();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmEventoAsignar.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.llenarTablaProductos(usuarios);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboBoxEventos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFecha;
    private javax.swing.JTable tblBoletos;
    private javax.swing.JTable tblUsuarios;
    // End of variables declaration//GEN-END:variables
}
