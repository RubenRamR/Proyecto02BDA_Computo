/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Administrador;

import DTOs.EstudianteDTO;
import Negocio.BloqueoNegocio;
import Negocio.EstudianteNegocio;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author user
 */
public class FrmBloquearEstudianteTabla extends javax.swing.JFrame {

    BloqueoNegocio bloqNeg;
    EstudianteNegocio estudianteNegocio;
    private int pagina = 1;
    private int LIMITE = 2;
    boolean conFiltro;

    /**
     * Creates new form Bloqueos
     */
    public FrmBloquearEstudianteTabla() {
        initComponents();
        bloqNeg = new BloqueoNegocio();
        estudianteNegocio = new EstudianteNegocio();
        cargarConfiguracionInicialTablaEstudiantes(); // Configurar la tabla
        cargarEstudiantesEnTabla(); // Cargar los estudiantes en la tabla
        NumeroDePagina.setEditable(false); // Hacer el campo de página no editable
        conFiltro = false; // Inicializar filtro
    }

    private long getIdSeleccionadoTablaEstudiantes() throws Exception {
        int selectedRow = TblEstudiantes.getSelectedRow(); // Obtener la fila seleccionada
        if (selectedRow >= 0)
        {
            Object value = TblEstudiantes.getValueAt(selectedRow, 0); // Obtener el valor de la primera columna
            if (value != null)
            {
                return (long) value; // Retornar el ID
            } else
            {
                throw new Exception("El ID del estudiante seleccionado es null");
            }
        } else
        {
            throw new Exception("No se ha seleccionado ningún estudiante");
        }
    }

    private void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaEstudiantes();
        this.cargarEstudiantesEnTabla();
    }

    private void cargarConfiguracionInicialTablaEstudiantes() {
        TableColumnModel modeloColumnas = this.TblEstudiantes.getColumnModel();
        Color color = new Color(178, 218, 250);

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                bloquear();
            }
        };
        int indiceColumnaEliminar = 5;
        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Bloquear", color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Bloquear", onEliminarClickListener));
    }

    private void bloquear() {
        FrmEstudianteBloqueo feb = new FrmEstudianteBloqueo();
        feb.setVisible(true);
        this.setVisible(false);
    }

    private void llenarTablaEstudiantes(List<EstudianteDTO> listaEstudiantes) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.TblEstudiantes.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla

        System.out.println("Total de estudiantes a mostrar: " + listaEstudiantes.size()); // Depuración

        if (listaEstudiantes != null && !listaEstudiantes.isEmpty())
        {
            for (EstudianteDTO row : listaEstudiantes)
            {
                System.out.println("ID Estudiante: " + row.getId()); // Verifica el ID
                Object[] fila = new Object[5];
                fila[0] = row.getId(); // Asegúrate de que esto no sea null
                fila[1] = row.getNombre();
                fila[2] = row.getEstatus();
                fila[3] = row.getContrasena();
                fila[4] = "Bloquear";
                modeloTabla.addRow(fila);
            }
        } else
        {
            System.out.println("No hay estudiantes para mostrar."); // Mensaje para depuración
        }
    }

    private void cargarEstudiantesEnTabla() {
        try
        {
            int indiceInicio = (pagina - 1) * LIMITE;
            List<EstudianteDTO> todosLosEstudiantes = estudianteNegocio.obtenerTodosLosEstudiantes();
            if (todosLosEstudiantes == null || todosLosEstudiantes.isEmpty())
            {
                System.out.println("No hay estudiantes para mostrar.");
                return; // Evita continuar si no hay estudiantes
            }

            int indiceFin = Math.min(indiceInicio + LIMITE, todosLosEstudiantes.size());
            List<EstudianteDTO> estudiantePagina = obtenerEstudinatesPagina(indiceInicio, indiceFin);

            llenarTablaEstudiantes(estudiantePagina);

            actualizarNumeroDePagina();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private List<EstudianteDTO> obtenerEstudinatesPagina(int indiceInicio, int indiceFin) throws Exception {
        List<EstudianteDTO> todosLosEstudinates = estudianteNegocio.obtenerTodosLosEstudiantes();
        List<EstudianteDTO> estudiantesPaginas = new ArrayList<>();
        indiceFin = Math.min(indiceFin, todosLosEstudinates.size());
        for (int i = indiceInicio; i < indiceFin; i++)
        {
            estudiantesPaginas.add(todosLosEstudinates.get(i));
        }
        return estudiantesPaginas;
    }

    private void cargarEstudiantesEnTabla(List<EstudianteDTO> estudiantesEncontrados) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.TblEstudiantes.getModel();

        modeloTabla.setRowCount(0);

        if (estudiantesEncontrados != null)
        {
            estudiantesEncontrados.forEach(row ->
            {
                Object[] fila = new Object[5];
                fila[0] = row.getId();
                fila[1] = row.getNombre();
                fila[2] = row.getEstatus();
                fila[3] = row.getContrasena();
                fila[4] = "Bloquear";
                modeloTabla.addRow(fila);
            });
        }
    }

    private void actualizarNumeroDePagina() {
        NumeroDePagina.setText("" + pagina);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        BtnMenu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblEstudiantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        NumeroDePagina = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Estudiantes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        jButton1.setText("Atrás");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, -1));

        jButton2.setText("Siguiente");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 340, -1, -1));

        jButton3.setText("Nuevo");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        BtnMenu.setBackground(new java.awt.Color(102, 153, 255));
        BtnMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnMenu.setForeground(new java.awt.Color(255, 255, 255));
        BtnMenu.setText("Menú");
        BtnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMenuActionPerformed(evt);
            }
        });
        jPanel1.add(BtnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 40));

        TblEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        TblEstudiantes.setForeground(new java.awt.Color(0, 0, 0));
        TblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estatus", "Contraseña", "Bloquear"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblEstudiantes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 670, 173));

        jLabel2.setBackground(new java.awt.Color(0, 102, 153));
        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Bloquear");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, -1));

        NumeroDePagina.setBackground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setForeground(new java.awt.Color(0, 0, 0));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
        // TODO add your handling code here:
        FrmMenu fm = new FrmMenu();
        fm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnMenuActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed
        // TODO add your handling code here:
        try
        {
            List<EstudianteDTO> todosLosEstudiante = estudianteNegocio.obtenerTodosLosEstudiantes();

            int totalPaginas = (int) Math.ceil((double) todosLosEstudiante.size() / LIMITE);

            int nuevaPagina = Integer.parseInt(NumeroDePagina.getText());

            if (nuevaPagina >= 1 && nuevaPagina <= totalPaginas)
            {
                pagina = nuevaPagina;

                cargarEstudiantesEnTabla();

                actualizarNumeroDePagina();
            } else
            {
                JOptionPane.showMessageDialog(this, "Número de página inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para la página", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_NumeroDePaginaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBloquearEstudianteTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmBloquearEstudianteTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmBloquearEstudianteTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmBloquearEstudianteTabla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBloquearEstudianteTabla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnMenu;
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JTable TblEstudiantes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
