/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Estudiante;

import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import Entidades.EstudianteEntidad;
import Negocio.CentroComputoNegocio;
import Negocio.ComputadoraNegocio;
import Negocio.EstudianteNegocio;
import excepciones.NegocioException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FrmReservaComputadora extends javax.swing.JFrame {

    private JTable tablaComputadoras;
    private DefaultTableModel modelo;
    private JButton btnReservar;
    private EstudianteNegocio estudianteNegocio = new EstudianteNegocio();
    private CentroComputoNegocio centroComputoNegocio;
    private ComputadoraNegocio computadoraNegocio = new ComputadoraNegocio();

    /**
     * Creates new form FrmListaComputadoras
     */
    public FrmReservaComputadora(List<ComputadoraDTO> computadoras) {
        initComponents2();
        centroComputoNegocio = new CentroComputoNegocio();
        computadoraNegocio = new ComputadoraNegocio();
        cargarComputadoras();

    }

    private void initComponents2() {
        setTitle("Reservar Computadora");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(new Object[]{"Número de Computadora", "Estado", "Nombre Alumno"}, 0);
        tablaComputadoras = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaComputadoras);

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reservarComputadora();
            }
        });

        add(scrollPane);
        add(btnReservar, "South"); // Añade el botón al sur (parte inferior)
    }

    private void cargarComputadoras() {
        try {
            List<ComputadoraDTO> computadoras = computadoraNegocio.obtenerTodasLasComputadoras(); // Llama al método correcto
            for (ComputadoraDTO computadora : computadoras) {
                modelo.addRow(new Object[]{
                    computadora.getNumeroMaquina(),
                    computadora.getEstado().toString(),
                    computadora.getNombreAlumno() // Asumir que este campo está en el DTO
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar computadoras: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //RESERVAR COMPU POR MEDIO DEL NOMBRE
//    private void reservarComputadora() {
//    int filaSeleccionada = tablaComputadoras.getSelectedRow();
//    if (filaSeleccionada == -1) {
//        JOptionPane.showMessageDialog(this, "Por favor, seleccione una computadora para reservar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
//        return;
//    }
//
//    // Obtener el número de la computadora seleccionada
//    int numeroMaquina = (int) modelo.getValueAt(filaSeleccionada, 0);
//    String nombreAlumno = JOptionPane.showInputDialog(this, "Ingrese su nombre:");
//
//    if (nombreAlumno != null && !nombreAlumno.trim().isEmpty()) {
//        // Lógica para reservar la computadora
//        try {
//            EstudianteEntidad estudiante = estudianteNegocio.obtenerEstudiantePorNombre(nombreAlumno); // Obtener el estudiante por nombre
//            ComputadoraDTO computadora = new ComputadoraDTO(nombreAlumno, Estado.OCUPADO, numeroMaquina, "", TipoCompu.ADMIN, null); // Ajustar según sea necesario
//            
//            centroComputoNegocio.reservarComputadora(computadora, estudiante, LocalDateTime.now(), LocalDateTime.now().plusHours(1)); // Lógica de reserva
//            
//            JOptionPane.showMessageDialog(this, "Computadora reservada exitosamente.");
//            modelo.setValueAt(Estado.OCUPADO.toString(), filaSeleccionada, 1); // Actualizar el estado en la tabla
//            modelo.setValueAt(nombreAlumno, filaSeleccionada, 2); // Asignar nombre al alumno
//        } catch (NegocioException e) {
//            JOptionPane.showMessageDialog(this, "Error al reservar la computadora: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
    //RESERVAR COMPU POR MEDIO DEL ID
    private void reservarComputadora() {
        int filaSeleccionada = tablaComputadoras.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una computadora para reservar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el ID de la computadora seleccionada
        Integer idComputadoraInteger = (Integer) modelo.getValueAt(filaSeleccionada, 0);
        String idAlumnoStr = JOptionPane.showInputDialog(this, "Ingrese su ID:");

        if (idAlumnoStr != null && !idAlumnoStr.trim().isEmpty()) {
            try {
                // Convertir el ID ingresado a un número largo
                long idAlumno = Long.parseLong(idAlumnoStr.trim());
                EstudianteEntidad estudiante = estudianteNegocio.obtenerEstudianteEntidadPorID(idAlumno); // Obtener el estudiante por ID

                if (estudiante == null) {
                    JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear la computadora DTO
                ComputadoraDTO computadora = new ComputadoraDTO(estudiante.getNombre(), Estado.OCUPADO, idComputadoraInteger, "", TipoCompu.ADMIN, null); // Ajustar según sea necesario

                // Reservar la computadora
                centroComputoNegocio.reservarComputadora(computadora, estudiante, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

                JOptionPane.showMessageDialog(this, "Computadora reservada exitosamente.");
                modelo.setValueAt(Estado.OCUPADO.toString(), filaSeleccionada, 1); // Actualizar el estado en la tabla
                modelo.setValueAt(estudiante.getNombre(), filaSeleccionada, 2); // Asignar nombre al alumno
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, "Error al reservar la computadora: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblComputadoras = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("COMPUTADORAS");

        tblComputadoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N", "Estado", "Número de Máquina", "i"
            }
        ));
        jScrollPane1.setViewportView(tblComputadoras);

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jButton3.setText("Atrás");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(330, 330, 330)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel1)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(33, 33, 33)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(91, Short.MAX_VALUE))
        );

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmReservaComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReservaComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReservaComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReservaComputadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        List<ComputadoraDTO> computadoras = List.of(
                new ComputadoraDTO("Juan Perez", Estado.DISPONIBLE, 1, "192.168.1.10", TipoCompu.ESTUDIANTE, null),
                new ComputadoraDTO("Maria Lopez", Estado.OCUPADO, 2, "192.168.1.11", TipoCompu.ESTUDIANTE, null),
                new ComputadoraDTO("Pedro Martinez", Estado.OCUPADO, 3, "192.168.1.12", TipoCompu.ESTUDIANTE, null)
        );

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReservaComputadora(computadoras).setVisible(true); // Pasar la lista de computadoras
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblComputadoras;
    // End of variables declaration//GEN-END:variables
}
