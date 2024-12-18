/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Estudiante;

import presentacion.Computadora.FrmDesbloquear;
import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;

/**
 *
 * @author user
 */
public class FrmConfirmacionReserva extends javax.swing.JFrame {

    ComputadoraDTO computadora;
    CentroComputoDTO centro;

    /**
     * Creates new form confirmacionReserva
     */
    public FrmConfirmacionReserva(ComputadoraDTO computadora) {
        initComponents();
        this.computadora = computadora;
        caragarCampos();
    }

    public void caragarCampos() {
        int numMaq = computadora.getNumeroMaquina();
        centro = computadora.getCentroComputo();

        LblNumCom.setText(String.valueOf(numMaq));
        LblLabComputo3.setText(centro.getNombre());

        LblHoraInicio.setText(String.valueOf(centro.getHoraInicio()));
        LblHoraFin.setText(String.valueOf(centro.getHoraFin()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LblLabComputo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LblNumCom = new javax.swing.JLabel();
        LblHoraFin = new javax.swing.JLabel();
        BtnConfirmar = new javax.swing.JButton();
        LblCompu = new javax.swing.JLabel();
        LblLabComputo2 = new javax.swing.JLabel();
        LblLabComputo3 = new javax.swing.JLabel();
        LblHoraInicio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Confirmación Reserva");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        LblLabComputo.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        LblLabComputo.setForeground(new java.awt.Color(255, 255, 255));
        LblLabComputo.setText("Hora");
        jPanel1.add(LblLabComputo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, -1, -1));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 340, 10, 10));

        LblNumCom.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        LblNumCom.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblNumCom, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 120, 30));

        LblHoraFin.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        LblHoraFin.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 90, 30));

        BtnConfirmar.setBackground(new java.awt.Color(102, 153, 255));
        BtnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        BtnConfirmar.setText("Confirmar");
        BtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(BtnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 130, 40));

        LblCompu.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        LblCompu.setForeground(new java.awt.Color(255, 255, 255));
        LblCompu.setText("Computadora");
        jPanel1.add(LblCompu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        LblLabComputo2.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        LblLabComputo2.setForeground(new java.awt.Color(255, 255, 255));
        LblLabComputo2.setText("Lab Computo");
        jPanel1.add(LblLabComputo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        LblLabComputo3.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        LblLabComputo3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblLabComputo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 270, 30));

        LblHoraInicio.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        LblHoraInicio.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 90, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel3.setText("-");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 20, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmarActionPerformed
        // TODO add your handling code here:
        FrmDesbloquear fd = new FrmDesbloquear();
    }//GEN-LAST:event_BtnConfirmarActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FrmConfirmacionReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FrmConfirmacionReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FrmConfirmacionReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FrmConfirmacionReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrmConfirmacionReserva().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfirmar;
    private javax.swing.JLabel LblCompu;
    private javax.swing.JLabel LblHoraFin;
    private javax.swing.JLabel LblHoraInicio;
    private javax.swing.JLabel LblLabComputo;
    private javax.swing.JLabel LblLabComputo2;
    private javax.swing.JLabel LblLabComputo3;
    private javax.swing.JLabel LblNumCom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
