/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Administrador;

import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import Negocio.CentroComputoNegocio;
import Negocio.ComputadoraNegocio;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author user
 */
public class FrmComputadoras extends javax.swing.JFrame {

    private int pagina = 1;
    private int LIMITE = 2;
    ComputadoraNegocio compNeg;

    /**
     * Creates new form CentroComputo
     */
    public FrmComputadoras() {
        initComponents();
        compNeg = new ComputadoraNegocio();
        cargarConfiguracionInicialTablaComputadora(); // Configurar la tabla
        cargarComputadorasEnTabla();// Cargar los estudiantes en la tabla
        NumeroDePagina.setEditable(false);
    }

    private long getIdSeleccionadoTablaComputadora() throws Exception {
        int selectedRow = TblComputadoras.getSelectedRow(); // Obtener la fila seleccionada
        if (selectedRow >= 0)
        {
            Object value = TblComputadoras.getValueAt(selectedRow, 0); // Obtener el valor de la primera columna
            if (value != null)
            {
                return (long) value; // Retornar el ID
            } else
            {
                throw new Exception("El ID del centro seleccionado es null");
            }
        } else
        {
            throw new Exception("No se ha seleccionado ningún centro");
        }
    }

    private void cargarMetodosIniciales() {
        this.cargarConfiguracionInicialTablaComputadora();
        this.cargarComputadorasEnTabla();
    }

    private void cargarConfiguracionInicialTablaComputadora() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        };
        int indiceColumnaEditar = 6;
        TableColumnModel modeloColumnas = this.TblComputadoras.getColumnModel();
        Color color = new Color(178, 218, 250);
        modeloColumnas.getColumn(indiceColumnaEditar).setCellRenderer(new JButtonRenderer("Editar", color));
        modeloColumnas.getColumn(indiceColumnaEditar).setCellEditor(new JButtonCellEditor("Editar", onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        };
        int indiceColumnaEliminar = 7;
        color = new Color(255, 105, 97);
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellRenderer(new JButtonRenderer("Eliminar", color));
        modeloColumnas.getColumn(indiceColumnaEliminar).setCellEditor(new JButtonCellEditor("Eliminar", onEliminarClickListener));
    }

    private void editar() {
        try
        {
            long id = this.getIdSeleccionadoTablaComputadora();
            System.out.println("ID de la computadora seleccionada: " + id);
            ComputadoraDTO editar = new ComputadoraDTO();
            this.setVisible(false);
            FrmEditarComputadora frm = new FrmEditarComputadora(id);
            frm.setVisible(true);
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        try
        {
            long id = this.getIdSeleccionadoTablaComputadora();
            ComputadoraDTO computadora = compNeg.obtenerComputadoraPorID(id);
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar al estudiante?\n"
                    + "ID: " + computadora.getId() + "\n"
                    + "Nombre: " + computadora.getNombreAlumno()+ "\n"
                    + "Contraseña: " + computadora.getEstado()+ "\n"
                    + "Hora Inicio: " + computadora.getNumeroMaquina()+ "\n"
                    + "Hora Fin: " + computadora.getDireccionIP()+ "\n"
                    + "Tipo Compu: "+ computadora.getTipoCompu(),
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION)
            {
                compNeg.eliminarComputadoraPorID(id);
                JOptionPane.showMessageDialog(this, "Computadora eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarComputadorasEnTabla();
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error al eliminar el Computadora: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TblComputadoras = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        BtnNuevo = new javax.swing.JButton();
        NumeroDePagina = new javax.swing.JTextField();
        BtnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TblComputadoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre alumno", "Estado", "Num Maquina", "IP", "Tipo Compu", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TblComputadoras);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 129, 800, 173));

        jLabel1.setBackground(new java.awt.Color(0, 102, 153));
        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Computadoras");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 80, 30));

        jButton2.setText("Siguiente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(719, 320, 90, 30));

        BtnNuevo.setText("Nuevo");
        BtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(BtnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, 80, 30));

        NumeroDePagina.setBackground(new java.awt.Color(255, 255, 255));
        NumeroDePagina.setForeground(new java.awt.Color(0, 0, 0));
        NumeroDePagina.setText("1");
        NumeroDePagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumeroDePaginaActionPerformed(evt);
            }
        });
        jPanel1.add(NumeroDePagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 30, 30));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try
        {
            List<ComputadoraDTO> todasLasComputadoras = compNeg.obtenerTodasLasComputadoras();

            int totalPaginas = (int) Math.ceil((double) todasLasComputadoras.size() / LIMITE);

            if (pagina < totalPaginas)
            {
                pagina++;
                cargarComputadorasEnTabla();
                actualizarNumeroDePagina();
            } else
            {

                JOptionPane.showMessageDialog(this, "No hay más páginas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NegocioException | HeadlessException ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoActionPerformed
        // TODO add your handling code here:
        FrmAgregarComputadora fac = new FrmAgregarComputadora();
        fac.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnNuevoActionPerformed

    private void NumeroDePaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumeroDePaginaActionPerformed

        try
        {
            List<ComputadoraDTO> todasLasComputadoras = compNeg.obtenerTodasLasComputadoras();

            int totalPaginas = (int) Math.ceil((double) todasLasComputadoras.size() / LIMITE);

            int nuevaPagina = Integer.parseInt(NumeroDePagina.getText());

            if (nuevaPagina >= 1 && nuevaPagina <= totalPaginas)
            {
                pagina = nuevaPagina;

                cargarComputadorasEnTabla();

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (pagina > 1)
        {
            pagina--;
            cargarComputadorasEnTabla();
            actualizarNumeroDePagina();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BtnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenuActionPerformed
        // TODO add your handling code here:
        FrmMenu fm = new FrmMenu();
        fm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnMenuActionPerformed

    private void llenarTablaComputadoras(List<ComputadoraDTO> listaComputadoras) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.TblComputadoras.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla antes de llenarla

        System.out.println("Total de computadoras a mostrar: " + listaComputadoras.size()); // Depuración

        if (listaComputadoras != null && !listaComputadoras.isEmpty())
        {
            for (ComputadoraDTO row : listaComputadoras)
            {
                System.out.println("ID Computadora: " + row.getId()); // Verifica el ID

                Object[] fila = new Object[8];
                fila[0] = row.getId();
                fila[1] = row.getNombreAlumno();
                fila[2] = row.getEstado();
                fila[3] = row.getNumeroMaquina();
                fila[4] = row.getDireccionIP();
                fila[5] = row.getTipoCompu();
                fila[6] = "Editar";
                fila[7] = "Eliminar";

                System.out.println("Contenido de fila antes de agregar: " + Arrays.toString(fila));

                modeloTabla.addRow(fila);
            }
        } else
        {
            System.out.println("No hay Computadoras para mostrar.");
        }
    }

    private void cargarComputadorasEnTabla() {
        try
        {
            int indiceInicio = (pagina - 1) * LIMITE;
            List<ComputadoraDTO> todasLasComputadoras = compNeg.obtenerTodasLasComputadoras();
            if (todasLasComputadoras == null || todasLasComputadoras.isEmpty())
            {
                System.out.println("No hay estudiantes para mostrar.");
                return; // Evita continuar si no hay estudiantes
            }

            int indiceFin = Math.min(indiceInicio + LIMITE, todasLasComputadoras.size());
            List<ComputadoraDTO> centroPagina = obtenerComputadoraPagina(indiceInicio, indiceFin);

            llenarTablaComputadoras(centroPagina);

            actualizarNumeroDePagina();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private List<ComputadoraDTO> obtenerComputadoraPagina(int indiceInicio, int indiceFin) throws Exception {
        List<ComputadoraDTO> todasLasComputadoras = compNeg.obtenerTodasLasComputadoras();
        List<ComputadoraDTO> compPaginas = new ArrayList<>();
        indiceFin = Math.min(indiceFin, todasLasComputadoras.size());
        for (int i = indiceInicio; i < indiceFin; i++)
        {
            compPaginas.add(todasLasComputadoras.get(i));
        }
        return compPaginas;
    }

//    private void cargarEstudiantesEnTabla(List<CentroComputoDTO> centrosEncontrados) {
//        DefaultTableModel modeloTabla = (DefaultTableModel) this.TblCentroComp.getModel();
//
//        modeloTabla.setRowCount(0);
//
//        if (centrosEncontrados != null)
//        {
//            centrosEncontrados.forEach(row ->
//            {
//                Object[] fila = new Object[7];
//                fila[0] = row.getId(); // Asegúrate de que esto no sea null
//                fila[1] = row.getNombre();
//                fila[2] = row.getContrasenaMaestra();
//                fila[3] = row.getHoraInicio();
//                fila[4] = row.getHoraFin();
//                fila[5] = "Eliminar";
//                fila[6] = "Editar";
//                modeloTabla.addRow(fila);
//            });
//        }
//    }
    private void actualizarNumeroDePagina() {
        NumeroDePagina.setText("" + pagina);
    }

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
            java.util.logging.Logger.getLogger(FrmComputadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmComputadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmComputadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmComputadoras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmComputadoras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnMenu;
    private javax.swing.JButton BtnNuevo;
    private javax.swing.JTextField NumeroDePagina;
    private javax.swing.JTable TblComputadoras;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
