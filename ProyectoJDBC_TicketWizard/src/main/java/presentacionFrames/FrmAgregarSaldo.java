/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacionFrames;

import dtos.ApartadoDTO;
import dtos.BoletoDTO;
import dtos.UsuarioDTO;
import excepciones.NegocioException;
import interfaces.IApartadoBO;
import interfaces.IBoletoBO;
import interfaces.ITransaccionBO;
import interfaces.IUsuarioBO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import negocio.ApartadoBO;
import negocio.BoletoBO;
import negocio.TransaccionBO;
import negocio.UsuarioBO;
import utilidades.EstadoAdquisicion;
import utilidades.Forms;
import utilidades.TipoTransaccion;

/**
 * Clase que representa la interfaz gráfica del formulario para agregar saldo a
 * la cuenta de un usuario. Permite a los usuarios ingresar una cantidad de
 * saldo que desean añadir a su cuenta.
 *
 * @author Cristopher Alberto Elizalde Andrade - 240005
 * @author Paulina Rodríguez Rodríguez Rayos - 117262
 */
public class FrmAgregarSaldo extends javax.swing.JFrame {

    private UsuarioDTO usuarioLoggeado;
    private ITransaccionBO transaccionbo;
    private IUsuarioBO usuariobo;
    private IApartadoBO apartadobo;
    private IBoletoBO boletobo;

    /**
     * Creates new form FrmAgregarSaldo
     */
    public FrmAgregarSaldo(UsuarioDTO usuarioLoggeado) {
        initComponents();
        this.usuarioLoggeado = usuarioLoggeado;
        this.transaccionbo = new TransaccionBO();
        this.usuariobo = new UsuarioBO();
        this.apartadobo = new ApartadoBO();
        this.boletobo = new BoletoBO();
        this.mostrarSaldo();
        this.setLocationRelativeTo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlabelsaldo = new javax.swing.JLabel();
        bAgrega = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jlabelsaldo.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jlabelsaldo.setText("$ ");

        bAgrega.setBackground(new java.awt.Color(255, 255, 255));
        bAgrega.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        bAgrega.setText("Agregar saldo");
        bAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("¿Desea agregar saldo a su cuenta? ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Pulse aqui");

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(bAgrega))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jlabelsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jButton1)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jlabelsaldo)
                .addGap(147, 147, 147)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAgrega)
                .addGap(14, 14, 14)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregaActionPerformed
        String input = JOptionPane.showInputDialog(this, "Ingrese la cantidad a agregar:");

        try {
            double cantidad = Double.parseDouble(input);

            if (cantidad > 0) {
                try {
                    double saldoAponer = usuarioLoggeado.getSaldo() + cantidad;
                    List<ApartadoDTO> apartados = apartadobo.consultar(usuarioLoggeado.getIdUsuario());
                    transaccionbo.agregarSaldo(usuarioLoggeado, cantidad);
                    usuarioLoggeado.setSaldo(saldoAponer);
                    if (apartados.size() > 0) {
                        double total = 0;
                        List<BoletoDTO> boletos = new ArrayList<>();
                        for (ApartadoDTO apartado : apartados) {
                            BoletoDTO boleto = boletobo.consultar(apartado.getIdBoleto());
                            boletos.add(boleto);
                            total += boleto.getPrecioOriginal();
                        }

                        if (total <= saldoAponer) {
                            for (BoletoDTO boleto : boletos) {
                                double precio = boleto.getPrecioOriginal();
                                int idUsuario = usuarioLoggeado.getIdUsuario();

                                try {
                                    boolean exito = boletobo.comprarBoleto(boleto.getIdBoleto(), precio, estadoAdquisicion(boleto.getIdUsuario()), TipoTransaccion.compra, idUsuario, boleto.getIdUsuario());
                                    boletobo.liberarBoleto(boleto.getIdBoleto());
                                    if (exito) {
                                        JOptionPane.showMessageDialog(this, "Boletos Apartado comprados exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Error al comprar el boleto.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (NegocioException e) {
                                    JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar la compra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Tiene Boletos apartados pero aun no cuenta con el saldo suficiente.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NegocioException ex) {
                    JOptionPane.showMessageDialog(this, "No se ha podido agregar el saldo a la cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                mostrarSaldo();
                JOptionPane.showMessageDialog(this, "Saldo agregado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_bAgregaActionPerformed
    private EstadoAdquisicion estadoAdquisicion(int idUsuario) {
        if (idUsuario == 0) {
            return EstadoAdquisicion.directo;
        } else {
            return EstadoAdquisicion.reventa;
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Forms.cargarForm(new FrmMenuPrincipal(usuarioLoggeado), this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgrega;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlabelsaldo;
    // End of variables declaration//GEN-END:variables

    private void mostrarSaldo() {
        try {
            double saldo = usuariobo.obtenerSaldoActual(usuarioLoggeado.getIdUsuario());
            jlabelsaldo.setText("Saldo actual: " + saldo);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el saldo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
