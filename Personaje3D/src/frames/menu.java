/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import javax.sound.sampled.Clip;
import clases.Clase_Sonido;

/**
 *
 * @author vicOMG99
 */
public class menu extends javax.swing.JFrame {

    public boolean musicaoff = true;
    Clip sonidomen = null;
    int band, bndS = 0;
    public static Clip clipFondo;
    boolean bandera1, bandera2 = false;
    Clase_Sonido son = new Clase_Sonido();
    public String Menu = "mainsong";
    public String Boton = "boton";

    public menu() {
        initComponents();
        son.sonidomenu(Menu);
        bandera1 = true;
        this.setLocationRelativeTo(null);

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
        botonPlay = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();
        btnCreditos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        so = new javax.swing.JButton();
        soff = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        botonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/play.png"))); // NOI18N
        botonPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonPlayMouseClicked(evt);
            }
        });
        botonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPlayActionPerformed(evt);
            }
        });
        jPanel1.add(botonPlay);
        botonPlay.setBounds(250, 140, 98, 74);

        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/informacion.png"))); // NOI18N
        btnAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAyudaMouseClicked(evt);
            }
        });
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        jPanel1.add(btnAyuda);
        btnAyuda.setBounds(20, 370, 40, 40);

        btnCreditos.setText("Cr�ditos");
        btnCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreditosMouseClicked(evt);
            }
        });
        jPanel1.add(btnCreditos);
        btnCreditos.setBounds(490, 390, 100, 25);

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("..Run Bot Run..");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(170, 50, 270, 50);

        so.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sonidoon.png"))); // NOI18N
        so.setToolTipText("");
        so.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                soMouseClicked(evt);
            }
        });
        so.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soActionPerformed(evt);
            }
        });
        jPanel1.add(so);
        so.setBounds(160, 270, 80, 80);

        soff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sonidooff.png"))); // NOI18N
        soff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                soffMouseClicked(evt);
            }
        });
        soff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soffActionPerformed(evt);
            }
        });
        jPanel1.add(soff);
        soff.setBounds(350, 270, 80, 80);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoM.jpg"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(-9, -4, 630, 430);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonPlayMouseClicked

        //this.setVisible(false);

    }//GEN-LAST:event_botonPlayMouseClicked

    private void botonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPlayActionPerformed
        son.sonidoOff();
        frmSeleccion2 per = new frmSeleccion2();
        per.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonPlayActionPerformed

    private void btnAyudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAyudaMouseClicked
        son.sonidoOff();
        this.setVisible(false);
        frmAyuda obj = new frmAyuda();
        obj.setVisible(true);

    }//GEN-LAST:event_btnAyudaMouseClicked

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnCreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreditosMouseClicked
        son.sonidoOff();
        frmCreditos2 obj = new frmCreditos2();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCreditosMouseClicked

    private void soffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soffMouseClicked
        son.sonidoOff();
        bandera2 = false;
        bandera1 = false;
        son.entrabandera(bandera2);
    }//GEN-LAST:event_soffMouseClicked

    private void soMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soMouseClicked
        son.sonidomenu(Menu);
        bandera2 = true;
        son.entrabandera(bandera2);
    }//GEN-LAST:event_soMouseClicked

    private void soActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soActionPerformed

    private void soffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soffActionPerformed

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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu().setVisible(true);

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonPlay;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnCreditos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton so;
    private javax.swing.JButton soff;
    // End of variables declaration//GEN-END:variables
}
