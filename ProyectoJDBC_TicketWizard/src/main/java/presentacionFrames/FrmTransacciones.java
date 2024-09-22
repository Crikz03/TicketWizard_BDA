/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacionFrames;

import dtos.TransaccionDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import interfaces.ITransaccionBO;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.TransaccionBO;

/**
 *
 * @author pauli
 */
public class FrmTransacciones extends javax.swing.JFrame {

    private UsuarioDTO usuarioLoggeado;
    private ITransaccionBO transaccionbo;

    /**
     * Creates new form FrmTransacciones
     */
    public FrmTransacciones(UsuarioDTO usuarioLoggeado) {
        initComponents();
        this.usuarioLoggeado = usuarioLoggeado;
        this.transaccionbo = new TransaccionBO();
        this.cargarMetodosIniciales();

    }

    private void cargarMetodosIniciales() {
        this.cargarEventosEnTabla();
    }

    private void llenarTablaEventos(List<TransaccionDTO> transaccionLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tlbTransacciones.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (transaccionLista != null) {
            transaccionLista.forEach(row -> {
                Object[] fila = new Object[5];
                fila[0] = row.getNumTransaccion();
                fila[1] = row.getTipo();
                fila[2] = row.getMonto();
                fila[3] = new SimpleDateFormat("dd/MM/yyyy").format(row.getFecha());
                fila[4] = new SimpleDateFormat("HH:mm:ss").format(row.getFecha());

                modeloTabla.addRow(fila);
            });
        }
    }

    private void cargarEventosEnTabla() {
        List<TransaccionDTO> transacciones = null;
        try {
            transacciones = this.transaccionbo.consultarIdUsuario(usuarioLoggeado.getIdUsuario());
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "No se han podido cargar las transacciones del usuario con id: " + usuarioLoggeado.getIdUsuario(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.llenarTablaEventos(transacciones);
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
        tlbTransacciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Transacciones");

        tlbTransacciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Num Transaccion", "Tipo", "Monto", "Fecha", "Hora"
            }
        ));
        jScrollPane1.setViewportView(tlbTransacciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tlbTransacciones;
    // End of variables declaration//GEN-END:variables
}
