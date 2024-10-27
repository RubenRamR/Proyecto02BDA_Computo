/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Administrador;

import DAOs.BloqueoDAO;
import DTOs.BloqueoDTO;
import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import ENUM_P.Estatus;
import Negocio.BloqueoNegocio;
import Negocio.EstudianteNegocio;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class FrmEditarEstudiante extends javax.swing.JFrame {

    Long idEstudiante;
    EstudianteNegocio estudianteNegocio;
    BloqueoNegocio bloqueoNegocio;
    BloqueoDAO bloqueoDAO;

    /**
     * Creates new form FrmEditarEstudiante
     *
     * @param idEstudiante
     */
    public FrmEditarEstudiante(Long idEstudiante) throws NegocioException {
         initComponents();
        this.idEstudiante = idEstudiante;
        estudianteNegocio = new EstudianteNegocio();
        bloqueoDAO = new BloqueoDAO();
        bloqueoNegocio = new BloqueoNegocio(bloqueoDAO);
        cargarCampos();
    }

    public void cargarCampos() throws NegocioException {
        System.out.println("ID del estudiante: " + idEstudiante);  // Depuración
        if (idEstudiante != null)
        {
            EstudianteDTO estudiante = estudianteNegocio.obtenerEstudiantePorID(idEstudiante);
            if (estudiante != null)
            {
                LblNombre.setText(estudiante.getNombre());
                LblApPaterno.setText(estudiante.getApPaterno());
                LblApMaterno.setText(estudiante.getApMaterno());
                CBCarrera.setSelectedItem(estudiante.getEstatus());
                LblContraseña.setText(estudiante.getContrasena());
                CBEstatus.setSelectedItem(estudiante.getEstatus());
            } else
            {
                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "ID de estudiante no válido.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel4 = new javax.swing.JPanel();
        LblEE = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LblNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        LblApPaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        LblApMaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        CBCarrera = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        LblContraseña = new javax.swing.JPasswordField();
        LblEstatus = new javax.swing.JLabel();
        CBEstatus = new javax.swing.JComboBox<>();
        BtnConfirmarEdicion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LblEE.setBackground(new java.awt.Color(0, 102, 153));
        LblEE.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        LblEE.setForeground(new java.awt.Color(255, 255, 255));
        LblEE.setText("Editar Estudiante");
        jPanel4.add(LblEE, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        LblNombre.setBackground(new java.awt.Color(255, 255, 255));
        LblNombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 255, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido Paterno");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        LblApPaterno.setBackground(new java.awt.Color(255, 255, 255));
        LblApPaterno.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 255, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido Materno");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        LblApMaterno.setBackground(new java.awt.Color(255, 255, 255));
        LblApMaterno.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 255, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Carrera");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, -1, -1));

        CBCarrera.setBackground(new java.awt.Color(255, 255, 255));
        CBCarrera.setForeground(new java.awt.Color(0, 0, 0));
        CBCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingeniería en Computación", "ISW" }));
        jPanel4.add(CBCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 190, -1));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        LblContraseña.setBackground(new java.awt.Color(255, 255, 255));
        LblContraseña.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(LblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 260, -1));

        LblEstatus.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        LblEstatus.setForeground(new java.awt.Color(255, 255, 255));
        LblEstatus.setText("Estatus");
        jPanel4.add(LblEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, -1, -1));

        CBEstatus.setBackground(new java.awt.Color(255, 255, 255));
        CBEstatus.setForeground(new java.awt.Color(0, 0, 0));
        CBEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "INSCRITO", "DESINSCRICTO" }));
        jPanel4.add(CBEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        BtnConfirmarEdicion.setBackground(new java.awt.Color(102, 204, 255));
        BtnConfirmarEdicion.setForeground(new java.awt.Color(0, 0, 0));
        BtnConfirmarEdicion.setText("Confirmar edición");
        BtnConfirmarEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConfirmarEdicionActionPerformed(evt);
            }
        });
        jPanel4.add(BtnConfirmarEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnConfirmarEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConfirmarEdicionActionPerformed
        // TODO add your handling code here:
        // Verifica que idEstudiante no sea null antes de proceder
        if (idEstudiante == null)
        {
            JOptionPane.showMessageDialog(this, "ID de estudiante no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = LblNombre.getText().trim();
        String apPaterno = LblApPaterno.getText().trim();
        String apMaterno = LblApMaterno.getText().trim();
        Estatus estatus = Estatus.valueOf((String) CBEstatus.getSelectedItem());
        String contrasena = LblContraseña.getText().trim();
        String carreraNombre = (String) CBCarrera.getSelectedItem();

        // Validar campos requeridos
        if (nombre.isEmpty() || apPaterno.isEmpty() || apMaterno.isEmpty() || contrasena.isEmpty() || carreraNombre.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos requeridos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener carrera
        CarreraDTO carreraEstudiante;
        try
        {
            carreraEstudiante = estudianteNegocio.obtenerIdCarreraPorNombre(carreraNombre);
            if (carreraEstudiante == null)
            {
                throw new NegocioException("Carrera no encontrada.");
            }
        } catch (NegocioException ex)
        {
            Logger.getLogger(FrmEditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al obtener carrera: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener bloqueos
        List<BloqueoDTO> bloqueos = bloqueoNegocio.obtenerBloqueosPorIdEstudiante(idEstudiante);
        if (bloqueos == null || bloqueos.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No se encontraron bloqueos para el estudiante.", "Información", JOptionPane.INFORMATION_MESSAGE);
            bloqueos = new ArrayList<>(); // Crear lista vacía si no hay bloqueos
        }

        // Crear el DTO del estudiante con bloqueos
        EstudianteDTO estudianteDTO = new EstudianteDTO(nombre, apPaterno, apMaterno, estatus, contrasena);

        try
        {
            estudianteNegocio.editarEstudiante(estudianteDTO);
            JOptionPane.showMessageDialog(this, "Estudiante editado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NegocioException ex)
        {
            Logger.getLogger(FrmEditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al editar el estudiante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BtnConfirmarEdicionActionPerformed

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
            java.util.logging.Logger.getLogger(FrmEditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmEditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmEditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmEditarEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try
                {
                    new FrmEditarEstudiante(3L).setVisible(true);
                } catch (NegocioException ex)
                {
                    Logger.getLogger(FrmEditarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConfirmarEdicion;
    private javax.swing.JComboBox<String> CBCarrera;
    private javax.swing.JComboBox<String> CBEstatus;
    private javax.swing.JTextField LblApMaterno;
    private javax.swing.JTextField LblApPaterno;
    private javax.swing.JPasswordField LblContraseña;
    private javax.swing.JLabel LblEE;
    private javax.swing.JLabel LblEstatus;
    private javax.swing.JTextField LblNombre;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
