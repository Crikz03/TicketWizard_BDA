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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.BoletoBO;
import negocio.EventoBO;
import negocio.TransaccionBO;
import negocio.UsuarioBO;
import utilidades.Forms;

/**
 *
 * @author pauli
 */
public class FrmBoletosAdquiridos extends javax.swing.JFrame {

    private ITransaccionBO transaccionbo;
    private UsuarioDTO usuarioLoggeado;
    private IBoletoBO boletobo;
    private IEventoBO eventobo;
    private IUsuarioBO usuariobo;

    /**
     * Creates new form FrmBoletosAdquiridos
     */
    public FrmBoletosAdquiridos(UsuarioDTO usuarioLoggeado) {
        initComponents();
        this.usuarioLoggeado = usuarioLoggeado;
        this.transaccionbo = new TransaccionBO();
        this.usuariobo = new UsuarioBO();
        this.boletobo = new BoletoBO();
        this.eventobo = new EventoBO();
        this.cargarBoletosTabla();
    }

    private void cargarBoletosTabla() {
        List<BoletoDTO> boletos = null;
        try {
            boletos = this.boletobo.consultarIdUsuario(usuarioLoggeado.getIdUsuario());
        } catch (NegocioException ex) {
            Logger.getLogger(FrmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.llenarTablaBoletos(boletos);
    }

    private void llenarTablaBoletos(List<BoletoDTO> boletoLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblBoletos.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (boletoLista != null) {
            boletoLista.forEach(row -> {
                Object[] fila = new Object[10];
                fila[0] = row.getNumSerie();
                fila[1] = row.getFila();
                fila[2] = row.getAsiento();
                fila[3] = row.getEstadoAdquisicion();
                Object[] detallesEvento = consultarDetallesEventoPorId(row.getIdEvento());
                if (detallesEvento != null) {
                    fila[4] = detallesEvento[0];
                    fila[5] = detallesEvento[1];
                    fila[6] = detallesEvento[2];
                    fila[7] = detallesEvento[3];
                    fila[8] = detallesEvento[4];
                }
                fila[9] = row.getIdBoleto();
                modeloTabla.addRow(fila);
            });
        }
    }

    private Object[] consultarDetallesEventoPorId(int idEvento) {
        try {
            EventoDTO evento = eventobo.consultar(idEvento);

            Object[] detallesEvento = new Object[6];
            detallesEvento[0] = evento.getNombre();
            detallesEvento[1] = evento.getLocalidad();
            detallesEvento[2] = evento.getVenue();
            detallesEvento[3] = evento.getFecha();
            detallesEvento[4] = evento.getDescripcion();

            return detallesEvento;
        } catch (NegocioException ex) {
            Logger.getLogger(FrmMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBoletos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Mis Boletos");

        tblBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero Serie", "Fila", "Asiento", "Tipo Adquisiscion", "Evento", "Localidad", "Venue", "Fecha", "Descripcion", "ID Boleto"
            }
        ));
        jScrollPane1.setViewportView(tblBoletos);

        jButton1.setText("Vender Boleto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(1113, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31))))
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(75, 75, 75)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(jButton1))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int selectedRow = tblBoletos.getSelectedRow();
        if (selectedRow != -1) {
            // Obtener el id_boleto de la columna oculta
            int idBoleto = (int) tblBoletos.getValueAt(selectedRow, 9); // Suponiendo que la columna 9 es el id_boleto
            String numSerie = (String) tblBoletos.getValueAt(selectedRow, 0);
            String fila = (String) tblBoletos.getValueAt(selectedRow, 1);
            String asiento = (String) tblBoletos.getValueAt(selectedRow, 2);
            String nombreEvento = (String) tblBoletos.getValueAt(selectedRow, 4);

            // Abrir el nuevo frame con los datos del boleto
            FrmVenderBoleto venderBoletoFrame = new FrmVenderBoleto(usuarioLoggeado, idBoleto, numSerie, fila, asiento, nombreEvento);
            venderBoletoFrame.setVisible(true);
            this.dispose(); // Opcional, para cerrar el frame actual si se desea
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un boleto para vender.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        Forms.cargarForm(new FrmMenuPrincipal(usuarioLoggeado), this);
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBoletos;
    // End of variables declaration//GEN-END:variables
}
