/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion.Administrador;

import DTOs.CarreraDTO;
import DTOs.DatosReporteCarrerasDTO;
import InterfacesNegocio.IReporteCarreraBO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import excepciones.NegocioException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class FrmReporteCarreras extends javax.swing.JFrame {

    private IReporteCarreraBO reporteCarreraBO;
    //private ICarreraBO carreraBO; // Suponiendo que tienes una interfaz para manejar las carreras
    private List<CarreraDTO> listaCarreras; // Lista de carreras
    private Set<Integer> lista;
    private List<Integer> listaCarrerasID;

    /**
     * Creates new form FrmReporteCarreras
     */
    public FrmReporteCarreras(IReporteCarreraBO reporteCarreraBO) {
        initComponents();
        this.reporteCarreraBO = reporteCarreraBO;
        //this.carreraBO = carreraBO;
        lista = new HashSet<>();

        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
       // this.llenarComboBoxCarreras(); // Método para llenar el combo box con las carreras
        this.setTitle("Reporte de Carreras");
        this.setResizable(false);
        this.setSize(1280, 775);
        this.setLocationRelativeTo(null);
    }

    private void cargarDatosEnTabla(String fechaInicio, String fechaFin) {
        try {
            // Obtén el reporte de carreras
            List<DatosReporteCarrerasDTO> reporteLista = (List<DatosReporteCarrerasDTO>) this.reporteCarreraBO.obtenerReporteCarreras(Long.MIN_VALUE, LocalDateTime.MIN, LocalDateTime.MIN);

            // Agrega los registros a la tabla
            this.llenarTablaCarreras(reporteLista);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTablaCarreras(List<DatosReporteCarrerasDTO> carreraLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblReporteCarreras.getModel(); // Asegúrate de que tu JTable tenga el nombre correcto

        // Limpiar la tabla
        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        // Llenar la tabla con los datos
        if (carreraLista != null) {
            carreraLista.forEach(row -> {
                Object[] fila = new Object[3]; // Ajusta el tamaño según tus datos
                fila[0] = row.getNombreCarrera();
                fila[1] = row.getMinutosUsoPorDia();
                fila[2] = row.getCantidadAlumnos();

                modeloTabla.addRow(fila);
            });
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

        btnGenerarReporte = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteCarreras = new javax.swing.JTable();
        cbCarrera = new javax.swing.JComboBox<>();
        fechaFinDP = new com.github.lgooddatepicker.components.DatePicker();
        fechaInicioDP = new com.github.lgooddatepicker.components.DatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        tblReporteCarreras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReporteCarreras);

        cbCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(btnImprimir)
                        .addGap(114, 114, 114)
                        .addComponent(btnGenerarReporte))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(cbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(383, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(335, 335, 335))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(426, 426, 426)
                    .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(637, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(cbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarReporte)
                    .addComponent(btnImprimir))
                .addGap(30, 30, 30))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(692, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:
        // Verifica que se seleccionaron las fechas
        if (fechaFinDP.getDate() == null || fechaFinDP.getDate() == null) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Limpia la lista para las carreras
        lista.clear();
        for (int i = 0; i < cbCarrera.getItemCount(); i++) { //
//            CarreraDTO carrera = cbCarrera.getItemAt(i);
//            this.lista.add(carrera.getId());
        }
        listaCarrerasID = new ArrayList<>(lista); // Crear una nueva lista con los IDs de las carreras

// Obtener las fechas seleccionadas
//        LocalDate fechaInicio = fechaFinDP.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate fechaFin = fechaFinDP.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        String fInicio = convertLocalDateToString(fechaInicio);
//        String fFin = convertLocalDateToString(fechaFin);
//
//// Cargar datos en la tabla (debes implementar este método)
//        cargarDatosEnTabla(listaCarrerasID, fInicio, fFin);

    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(String.format("%s/ReporteCarreras.pdf", path)));
            doc.open();

            // Descripción de los filtros
            doc.add(new Paragraph("Reporte de Uso de Carreras", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Filtros Aplicados:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            doc.add(new Paragraph("Fechas: " + fechaFinDP.getText() + " a " + fechaFinDP.getText())); 
            //doc.add(new Paragraph("Carreras: " + obtenerValoresSeparadosPorComaCarrera(cbCarrera))); 
            doc.add(new Paragraph("\n")); // Espacio en blanco

            // Tabla
            PdfPTable tbl = new PdfPTable(3); 
            tbl.addCell("Nombre Carrera");
            tbl.addCell("Minutos de Uso");
            tbl.addCell("Cantidad de Alumnos");

            BigDecimal sumaMinutos = BigDecimal.ZERO;
            int totalAlumnos = 0;
            for (int i = 0; i < tblReporteCarreras.getRowCount(); i++) { // Asegúrate de que este es el nombre de tu tabla
                String carrera = tblReporteCarreras.getValueAt(i, 0).toString();
                String minutosUso = tblReporteCarreras.getValueAt(i, 1).toString();
                String cantidadAlumnos = tblReporteCarreras.getValueAt(i, 2).toString();

                //sumaMinutos = sumaMinutos.add(convertToBigDecimal(minutosUso)); 
                totalAlumnos += Integer.parseInt(cantidadAlumnos);

                tbl.addCell(carrera);
                tbl.addCell(minutosUso);
                tbl.addCell(cantidadAlumnos);
            }

            doc.add(tbl);
            doc.add(new Paragraph("Total Minutos de Uso: " + sumaMinutos, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Total Alumnos: " + totalAlumnos, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            JOptionPane.showMessageDialog(this, "Se imprimió con éxito el documento!");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al crear el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(FrmReporteCarreras.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doc.close(); 
        }

    }//GEN-LAST:event_btnImprimirActionPerformed

    
    
  
    
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
            java.util.logging.Logger.getLogger(FrmReporteCarreras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReporteCarreras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReporteCarreras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReporteCarreras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new FrmReporteCarreras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JComboBox<String> cbCarrera;
    private com.github.lgooddatepicker.components.DatePicker fechaFinDP;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioDP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporteCarreras;
    // End of variables declaration//GEN-END:variables
}
