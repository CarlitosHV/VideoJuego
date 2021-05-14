/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;


import animacionesP.Araña;
import animacionesP.LuciMorning;
import animacionesP.Robot;
import clases.Clase_Sonido;
import com.sun.opengl.util.Animator;
import java.io.File;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/**
 *
 * @author vicOMG99
 */
public class frmSeleccion2 extends javax.swing.JFrame {

    private Animator animatorLuci;
    private Animator animatorRobot;
    private Animator animatorAraña;
    public boolean bander = true;
    int bandera;
    Clase_Sonido obj=new Clase_Sonido();

    public frmSeleccion2() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        canvasLuci.addGLEventListener(new LuciMorning());
        canvasRobot.addGLEventListener(new Robot());
        canvasAraña.addGLEventListener(new Araña());

        animatorLuci = new Animator(canvasLuci);
        animatorLuci.start();
        animatorRobot = new Animator(canvasRobot);
        animatorRobot.start();
        animatorAraña = new Animator(canvasAraña);
        animatorAraña.start();
        
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
        btnBack = new javax.swing.JButton();
        canvasLuci = new javax.media.opengl.GLCanvas();
        canvasRobot = new javax.media.opengl.GLCanvas();
        canvasAraña = new javax.media.opengl.GLCanvas();
        jLabel1 = new javax.swing.JLabel();
        jlFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        btnBack.setText("Atrás");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });
        jPanel1.add(btnBack);
        btnBack.setBounds(10, 10, 63, 25);

        canvasLuci.setBackground(new java.awt.Color(0, 0, 0));
        canvasLuci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasLuciMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                canvasLuciMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canvasLuciMouseExited(evt);
            }
        });
        jPanel1.add(canvasLuci);
        canvasLuci.setBounds(20, 120, 225, 180);

        canvasRobot.setBackground(new java.awt.Color(0, 0, 0));
        canvasRobot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasRobotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                canvasRobotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canvasRobotMouseExited(evt);
            }
        });
        jPanel1.add(canvasRobot);
        canvasRobot.setBounds(300, 120, 225, 180);

        canvasAraña.setBackground(new java.awt.Color(153, 153, 153));
        canvasAraña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvasArañaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                canvasArañaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canvasArañaMouseExited(evt);
            }
        });
        jPanel1.add(canvasAraña);
        canvasAraña.setBounds(630, 120, 225, 180);

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecciona tu personaje");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(200, 40, 420, 25);

        jlFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fandoGamer2.png"))); // NOI18N
        jPanel1.add(jlFondo);
        jlFondo.setBounds(-4, 6, 880, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        menu obj = new menu();
        this.setVisible(false);
        obj.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnBackMouseClicked

    private void canvasLuciMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasLuciMouseEntered
        canvasRobot.setVisible(false);
        canvasAraña.setVisible(false);
    }//GEN-LAST:event_canvasLuciMouseEntered

    private void canvasLuciMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasLuciMouseExited
        canvasRobot.setVisible(true);
        canvasAraña.setVisible(true);
    }//GEN-LAST:event_canvasLuciMouseExited

    private void canvasRobotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasRobotMouseEntered
        canvasLuci.setVisible(false);
        canvasAraña.setVisible(false);
    }//GEN-LAST:event_canvasRobotMouseEntered

    private void canvasRobotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasRobotMouseExited
        canvasLuci.setVisible(true);
        canvasAraña.setVisible(true);
    }//GEN-LAST:event_canvasRobotMouseExited

    private void canvasArañaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasArañaMouseEntered
        canvasLuci.setVisible(false);
        canvasRobot.setVisible(false);
    }//GEN-LAST:event_canvasArañaMouseEntered

    private void canvasArañaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasArañaMouseExited
        canvasLuci.setVisible(true);
        canvasRobot.setVisible(true);
    }//GEN-LAST:event_canvasArañaMouseExited

    private void canvasLuciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasLuciMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Confirmas el personaje?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Niveles niv = new Niveles();
            niv.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_canvasLuciMouseClicked

    private void canvasRobotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasRobotMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Confirmas el personaje?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Niveles niv = new Niveles();
            niv.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_canvasRobotMouseClicked

    private void canvasArañaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasArañaMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Confirmas el personaje?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            Niveles niv = new Niveles();
            niv.setVisible(true);
            this.dispose();
        } else {

        }
    }//GEN-LAST:event_canvasArañaMouseClicked

    public void stopaudio(boolean b) {
        bander = false;
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
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmSeleccion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSeleccion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSeleccion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSeleccion2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSeleccion2().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.media.opengl.GLCanvas canvasAraña;
    private javax.media.opengl.GLCanvas canvasLuci;
    private javax.media.opengl.GLCanvas canvasRobot;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlFondo;
    // End of variables declaration//GEN-END:variables
}
