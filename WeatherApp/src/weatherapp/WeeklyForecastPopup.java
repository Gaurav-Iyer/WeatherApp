/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iyerg0445
 */
public class WeeklyForecastPopup extends WeatherForm{
    //INITIAL VARIABLES
    String[] cols = new String[2];
    String[][] data = new String[8][2];
    DefaultTableModel model = new DefaultTableModel(data, cols);
    JTable table = new JTable(model);
    
    
    
    /**
     * Creates new form WeeklyForecastPopup
     */
    public WeeklyForecastPopup() {
        
        initComponents();
        JPanel panel = new JPanel(new BorderLayout());
        table.setGridColor(Color.BLACK);
        panel.add(table, BorderLayout.NORTH);
        panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        
        //HEADERS
        model.setValueAt("DAY OF THE WEEK",0,0);
        model.setValueAt("TEMPERATURE",0,1);

        
        //DATA VALUES
        for(int i = 0; i < 7 ; i ++){
            model.setValueAt(daysOfTheWeek[i], i + 1, 0);
            model.setValueAt(weeklyForecast[i]+ "°C", i + 1, 1);
        }
        
        //SETTING UP JFRAME
        JFrame frame = new JFrame();
        frame.add(panel);
        
        Dimension d = new Dimension(300,180);
        frame.setPreferredSize(d);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        WeeklyForecastPopup p = new WeeklyForecastPopup();
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
            java.util.logging.Logger.getLogger(WeeklyForecastPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeeklyForecastPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeeklyForecastPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeeklyForecastPopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    
                    new WeeklyForecastPopup();
                }
            });
        
        
        
            
            
            
            
            
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
