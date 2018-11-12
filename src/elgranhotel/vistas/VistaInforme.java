/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elgranhotel.vistas;

import elgranhotel.modelo.Conexion;
import elgranhotel.modelo.Huesped;
import elgranhotel.modelo.HuespedData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHIDORY
 */
public class VistaInforme extends javax.swing.JInternalFrame {
    private List <Huesped> listaHuesped;
    private HuespedData huesD;
    private DefaultTableModel modelo;
    private Conexion conexion;
   
     
        

    /**
     * Creates new form VistaInforme
     */
    public VistaInforme() {
        try {
            initComponents();
            conexion = new Conexion("jdbc:mysql://localHost/ElGranHotel", "root", "");
            
            modelo = new DefaultTableModel();
            
            huesD = new HuespedData(conexion);
            listaHuesped = huesD.obtenerHuesped();
            
            
            tablaCo();
            
            borrarFilas();
            
            cargaTabla();
            
        } catch (ClassNotFoundException ex) {
             System.out.println("Error al conectar con la base de datos..." + ex.getMessage());
        }
        
        
    }
    
    public void tablaCo(){
        ArrayList<Object> cabecera = new ArrayList<Object>();
        cabecera.add("DNI");
        cabecera.add("Nombre Huesped");
        
       
        for (Object columna : cabecera) {
            modelo.addColumn(columna);
        }
        ttabla.setModel(modelo);
        
    }
    
    
    
    public void borrarFilas(){
        int numFilas = modelo.getRowCount()-1;
        System.out.println("Tabla "+numFilas);
        
        for (int i = numFilas; i >= 0; i--) {
            modelo.removeRow(i);
            System.out.println("Tabla "+i);
        }
    }
    
    public void cargaTabla(){
        
        for (Huesped hues : listaHuesped) {
            
            modelo.addRow(new Object[]{hues.getDni(), hues.getNombre()
            
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ttabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        b3 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        tfDni = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        bInforme = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bBuscar1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setClosable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ttabla.setBackground(new java.awt.Color(0, 102, 153));
        ttabla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ttabla.setForeground(new java.awt.Color(102, 0, 51));
        ttabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(ttabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 1000, 270));

        jButton1.setBackground(new java.awt.Color(102, 0, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 240, 10));

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informes");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 50));

        b3.setBackground(new java.awt.Color(102, 0, 51));
        b3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b3.setForeground(new java.awt.Color(102, 0, 51));
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 460, -1));

        b2.setBackground(new java.awt.Color(102, 0, 51));
        b2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b2.setForeground(new java.awt.Color(102, 0, 51));
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel1.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 520, -1));

        b1.setBackground(new java.awt.Color(102, 0, 51));
        b1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b1.setForeground(new java.awt.Color(102, 0, 51));
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 590, -1));

        tfDni.setBackground(new java.awt.Color(0, 102, 153));
        tfDni.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        tfDni.setForeground(new java.awt.Color(255, 255, 255));
        tfDni.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfDni.setBorder(null);
        tfDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDniActionPerformed(evt);
            }
        });
        jPanel1.add(tfDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 290, -1));

        jButton2.setBackground(new java.awt.Color(102, 0, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 310, -1));

        bInforme.setBackground(new java.awt.Color(102, 0, 51));
        bInforme.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bInforme.setForeground(new java.awt.Color(255, 255, 255));
        bInforme.setText("Informe");
        bInforme.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInformeActionPerformed(evt);
            }
        });
        jPanel1.add(bInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, 340, 30));

        jButton4.setBackground(new java.awt.Color(102, 0, 51));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 370, 10));

        jButton5.setBackground(new java.awt.Color(102, 0, 51));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 300, 10));

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("DNI");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        bBuscar1.setBackground(new java.awt.Color(102, 0, 51));
        bBuscar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bBuscar1.setForeground(new java.awt.Color(255, 255, 255));
        bBuscar1.setText("Buscar");
        bBuscar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBuscar1ActionPerformed(evt);
            }
        });
        jPanel1.add(bBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 400, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 51));
        jLabel10.setText("___  >");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed

    }//GEN-LAST:event_b3ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b2ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed

    }//GEN-LAST:event_b1ActionPerformed

    private void tfDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDniActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInformeActionPerformed
       
        modelo.setColumnCount(0);
        borrarFilas();
        tablaCo();
        cargaTabla();
        


    }//GEN-LAST:event_bInformeActionPerformed

    private void bBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBuscar1ActionPerformed
        if (tfDni.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Ingrese un DNI para la busquea");

        } else {
            
            modelo.setColumnCount(0);
            int d = Integer.parseInt(tfDni.getText());

            borrarFilas();

            ArrayList<Object> cabecera = new ArrayList<Object>();
            cabecera.add("DNI");
            cabecera.add("Nombre Huesped");
            cabecera.add("Domicilio");
            cabecera.add("Correo");
            cabecera.add("Celular");

            for (Object columna : cabecera) {
                modelo.addColumn(columna);
            }
            ttabla.setModel(modelo);

            List<Huesped> huesDni = huesD.obtenerHuespedXDNI(d);

            for (Huesped dni : huesDni) {
                modelo.addRow(new Object[]{dni.getDni(), dni.getNombre(), dni.getDomicilio(), dni.getCorreo(), dni.getCelular()});

            }

        }
    }//GEN-LAST:event_bBuscar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton bBuscar1;
    private javax.swing.JButton bInforme;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfDni;
    private javax.swing.JTable ttabla;
    // End of variables declaration//GEN-END:variables
}
