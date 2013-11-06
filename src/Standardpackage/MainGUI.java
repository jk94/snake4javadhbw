package Standardpackage;

import Controls.Control;
import Enums.EnumDirection;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Philip
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Creates new form MainGUI
     */
    private Control cnt = null;

    public MainGUI() {
        initComponents();
        cnt = new Control(this);
        cnt.init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        btn_Start = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(520, 520));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        canvas1.setBackground(new java.awt.Color(192, 192, 192));
        canvas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        canvas1.setMinimumSize(new java.awt.Dimension(500, 500));
        canvas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                canvas1MouseClicked(evt);
            }
        });
        canvas1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                canvas1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                canvas1FocusLost(evt);
            }
        });
        canvas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                canvas1KeyTyped(evt);
            }
        });

        btn_Start.setText("Neues Spiel");
        btn_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Start, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Start)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StartActionPerformed
        cnt.init();
        btn_Start.setEnabled(false);
        btn_Start.setVisible(false);
        //cnt.getZeichenflaeche().fillRect(0, 470, 500, 20);
    }//GEN-LAST:event_btn_StartActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped

    private void canvas1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_canvas1KeyTyped
        //System.out.println(evt.getKeyChar());
        EnumDirection dir = null;
        char eing = evt.getKeyChar();
        eing = Character.toLowerCase(eing);
        //System.out.println(eing);
        switch (eing) {
            case 'w':
                dir = EnumDirection.HOCH;
                break;
            case 's':
                dir = EnumDirection.RUNTER;
                break;
            case 'a':
                dir = EnumDirection.LINKS;
                break;
            case 'd':
                dir = EnumDirection.RECHTS;
                break;
            default:
                break;
        }
        if (dir != null) {
            cnt.changeDirection(dir);
        }
    }//GEN-LAST:event_canvas1KeyTyped

    private void canvas1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_canvas1FocusGained
        cnt.start();
    }//GEN-LAST:event_canvas1FocusGained

    private void canvas1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_canvas1FocusLost
        cnt.pause();
    }//GEN-LAST:event_canvas1FocusLost

    private void canvas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvas1MouseClicked
        cnt.SpielfeldClicked(evt);
    }//GEN-LAST:event_canvas1MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    public java.awt.Canvas getCanvas() {
        return canvas1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Start;
    private java.awt.Canvas canvas1;
    // End of variables declaration//GEN-END:variables
}
