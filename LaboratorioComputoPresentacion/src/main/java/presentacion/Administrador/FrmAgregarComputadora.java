/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Administrador;

import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import Negocio.CentroComputoNegocio;
import Negocio.ComputadoraNegocio;
import excepciones.NegocioException;
import jakarta.persistence.PersistenceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class FrmAgregarComputadora extends javax.swing.JFrame {

    ComputadoraNegocio compuNeg;
    CentroComputoNegocio centroNeg;
    List<CentroComputoDTO> centros;

    /**
     * Creates new form FrmAgregarComputadora
     */
    public FrmAgregarComputadora() {
        initComponents();
        compuNeg = new ComputadoraNegocio();
        centroNeg = new CentroComputoNegocio();
        cargarCentroComputo();
    }
    
    private void cargarCentroComputo() {
        try
        {
            centros = centroNeg.obtenerTodosLosCentroComputo(); // Asigna directamente a la lista de instancia
            CBCentroComputo.removeAllItems();
            for (CentroComputoDTO centro : centros)
            {
                if (centro/*.getNombre()*/ != null)
                { // Asegúrate de que el nombre no sea nulo
                    CBCentroComputo.addItem(centro.getNombre());
                } else
                {
                    System.err.println("Unidad académica con nombre nulo encontrada.");
                }
            }
        } catch (NullPointerException e)
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e)
        {
            System.err.println("Se produjo un error al cargar las unidades: " + e.getMessage());
            e.printStackTrace();
        }
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BtnAgregarC = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        LblNombreAlumno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LblNumMaq = new javax.swing.JTextField();
        LblIp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CBTipoCompu = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BtnRegresar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        CBCentroComputo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar Computadora");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 28, -1, -1));

        BtnAgregarC.setBackground(new java.awt.Color(102, 153, 255));
        BtnAgregarC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAgregarC.setForeground(new java.awt.Color(255, 255, 255));
        BtnAgregarC.setText("Agregar Computadora");
        BtnAgregarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarCActionPerformed(evt);
            }
        });
        jPanel4.add(BtnAgregarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 200, 40));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre Alumno");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        LblNombreAlumno.setBackground(new java.awt.Color(255, 255, 255));
        LblNombreAlumno.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 255, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 30, 10));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DISPONIBLE");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Numero Maquina");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        LblNumMaq.setBackground(new java.awt.Color(255, 255, 255));
        LblNumMaq.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblNumMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 60, -1));

        LblIp.setBackground(new java.awt.Color(255, 255, 255));
        LblIp.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 255, -1));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Dirección IP");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        CBTipoCompu.setBackground(new java.awt.Color(255, 255, 255));
        CBTipoCompu.setForeground(new java.awt.Color(0, 0, 0));
        CBTipoCompu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "ESTUDIANTE", "MAESTRO" }));
        jPanel4.add(CBTipoCompu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 130, 30));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Centro de Computo");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Estado");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        BtnRegresar.setText("Regresar");
        BtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegresarActionPerformed(evt);
            }
        });
        jPanel4.add(BtnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 80, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jLabel11.setText("(No hubo tiempo de quitarlo xd)");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        CBCentroComputo.setBackground(new java.awt.Color(255, 255, 255));
        CBCentroComputo.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(CBCentroComputo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 130, 30));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tipo de Computadora");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAgregarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarCActionPerformed
        // TODO add your handling code here:
        try
        {
            String nombreAlumno = LblNombreAlumno.getText();
            String direccionIP = LblIp.getText();
            int numeroMaquina = Integer.parseInt(LblNumMaq.getText());
            Object selectedItemT = CBTipoCompu.getSelectedItem();
            Object selectedItemC = CBCentroComputo.getSelectedItem();
            if (selectedItemT != null || selectedItemC != null)
            {
                // Convierte el objeto seleccionado a String
                String tipoSeleccionado = selectedItemT.toString();
                TipoCompu tipoCompu = TipoCompu.valueOf(tipoSeleccionado);
                
                String centroSeleccionado = selectedItemC.toString();
                CentroComputoDTO centro = new CentroComputoDTO();
                centro = centroNeg.obtenerCentroComputoPorNombre(centroSeleccionado);

                // Crear instancia de ComputadoraDTO con los datos de los campos
                ComputadoraDTO computadoraDTO = new ComputadoraDTO();
                computadoraDTO.setNombreAlumno(nombreAlumno);
                computadoraDTO.setDireccionIP(direccionIP);
                computadoraDTO.setNumeroMaquina(numeroMaquina);
                computadoraDTO.setEstado(Estado.DISPONIBLE);
                computadoraDTO.setTipoCompu(tipoCompu);
                computadoraDTO.setCentroComputo(centro);

                compuNeg.insertarComputadora(computadoraDTO);

                JOptionPane.showMessageDialog(this, "Computadora agregada exitosamente.");
            } else
            {
                System.out.println("No se seleccionó ningún tipo de computadora.");
            }

        } catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Número de computadora inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (PersistenceException e)
        {
            JOptionPane.showMessageDialog(this, "Error al agregar la computadora: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NegocioException ex)
        {
            Logger.getLogger(FrmAgregarComputadora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BtnAgregarCActionPerformed

    private void BtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegresarActionPerformed
        // TODO add your handling code here:
        FrmEstudiantes fe = new FrmEstudiantes();
        fe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FrmAgregarComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmAgregarComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmAgregarComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmAgregarComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregarComputadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarC;
    private javax.swing.JButton BtnRegresar;
    private javax.swing.JComboBox<String> CBCentroComputo;
    private javax.swing.JComboBox<String> CBTipoCompu;
    private javax.swing.JTextField LblIp;
    private javax.swing.JTextField LblNombreAlumno;
    private javax.swing.JTextField LblNumMaq;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
