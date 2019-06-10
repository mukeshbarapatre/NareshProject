package lookhub;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author KOMAL
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    EmployeePanel EP=new EmployeePanel();


    public HomePage() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
        int ysize = (int)tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        heading_panel = new javax.swing.JPanel();
        Look = new javax.swing.JLabel();
        Look1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        adminpic = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        modelicon = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        ParentPanel = new javax.swing.JPanel();
        ParentPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        homepanel = new javax.swing.JPanel();
        deltail_bt = new javax.swing.JButton();
        emp_bt = new javax.swing.JButton();
        client_bt = new javax.swing.JButton();
        product_bt = new javax.swing.JButton();
        service_bt = new javax.swing.JButton();
        supplier_bt = new javax.swing.JButton();
        report_bt = new javax.swing.JButton();
        offer_bt = new javax.swing.JButton();
        other_bt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(30, 3, 3));
        setForeground(new java.awt.Color(30, 3, 3));

        heading_panel.setBackground(new java.awt.Color(38, 3, 3));

        Look.setFont(new java.awt.Font("Georgia", 1, 58)); // NOI18N
        Look.setForeground(new java.awt.Color(255, 255, 255));
        Look.setText("LOOK  HUB");

        Look1.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        Look1.setForeground(new java.awt.Color(102, 204, 255));
        Look1.setText("The Family Salon");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        adminpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/AdminLogin.png"))); // NOI18N
        adminpic.setText("jLabel1");
        adminpic.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        logout.setText("Log Out");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("usernane");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminpic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        modelicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/Unisex.png"))); // NOI18N
        modelicon.setText("jLabel1");

        javax.swing.GroupLayout heading_panelLayout = new javax.swing.GroupLayout(heading_panel);
        heading_panel.setLayout(heading_panelLayout);
        heading_panelLayout.setHorizontalGroup(
            heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heading_panelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(modelicon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Look1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        heading_panelLayout.setVerticalGroup(
            heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heading_panelLayout.createSequentialGroup()
                .addGroup(heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(heading_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(heading_panelLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(Look1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(heading_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)))
                .addGap(1063, 1063, 1063))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Look HUb Family Salon");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Look HUb Family Salon");

        javax.swing.GroupLayout ParentPanel1Layout = new javax.swing.GroupLayout(ParentPanel1);
        ParentPanel1.setLayout(ParentPanel1Layout);
        ParentPanel1Layout.setHorizontalGroup(
            ParentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanel1Layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(jLabel3)
                .addContainerGap(282, Short.MAX_VALUE))
        );
        ParentPanel1Layout.setVerticalGroup(
            ParentPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ParentPanelLayout = new javax.swing.GroupLayout(ParentPanel);
        ParentPanel.setLayout(ParentPanelLayout);
        ParentPanelLayout.setHorizontalGroup(
            ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ParentPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ParentPanelLayout.setVerticalGroup(
            ParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ParentPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        homepanel.setBackground(new java.awt.Color(204, 204, 204));
        homepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        homepanel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        deltail_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        deltail_bt.setText("Salon Details");
        deltail_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deltail_bt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deltail_btMouseEntered(evt);
            }
        });
        deltail_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deltail_btActionPerformed(evt);
            }
        });

        emp_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        emp_bt.setText("Employees");
        emp_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        client_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        client_bt.setText("Clients");
        client_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        product_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        product_bt.setText("Products");
        product_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        service_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        service_bt.setText("Services");
        service_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        service_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                service_btActionPerformed(evt);
            }
        });

        supplier_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        supplier_bt.setText("Suppliers");
        supplier_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        report_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        report_bt.setText("Reports");
        report_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_btActionPerformed(evt);
            }
        });

        offer_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        offer_bt.setText("Offers");
        offer_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        other_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        other_bt.setText("Other");
        other_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout homepanelLayout = new javax.swing.GroupLayout(homepanel);
        homepanel.setLayout(homepanelLayout);
        homepanelLayout.setHorizontalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(other_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deltail_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(emp_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(client_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supplier_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(report_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(offer_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        homepanelLayout.setVerticalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(deltail_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emp_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(client_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(product_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(service_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supplier_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(report_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(offer_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(other_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(homepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(heading_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(458, 458, 458)
                    .addComponent(jLabel2)
                    .addContainerGap(459, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(heading_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(homepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(353, 353, 353)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(422, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(376, 376, 376)
                    .addComponent(jLabel2)
                    .addContainerGap(377, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deltail_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deltail_btActionPerformed
      //heading_panel.remove(ParentPanel);
      //heading_panel.add(EP);
     // heading_panel.revalidate();
      
      ParentPanel.remove(ParentPanel1);
       ParentPanel.add(EP);
       ParentPanel.revalidate();
       ParentPanel.validate();
       ParentPanel.repaint();
      pack();
    }//GEN-LAST:event_deltail_btActionPerformed

    private void deltail_btMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deltail_btMouseEntered
                   
    }//GEN-LAST:event_deltail_btMouseEntered

    private void service_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_btActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_service_btActionPerformed

    private void report_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_btActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_report_btActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Look;
    private javax.swing.JLabel Look1;
    private javax.swing.JPanel ParentPanel;
    private javax.swing.JPanel ParentPanel1;
    private javax.swing.JLabel adminpic;
    private javax.swing.JButton client_bt;
    private javax.swing.JButton deltail_bt;
    private javax.swing.JButton emp_bt;
    private javax.swing.JPanel heading_panel;
    private javax.swing.JPanel homepanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logout;
    private javax.swing.JLabel modelicon;
    private javax.swing.JButton offer_bt;
    private javax.swing.JButton other_bt;
    private javax.swing.JButton product_bt;
    private javax.swing.JButton report_bt;
    private javax.swing.JButton service_bt;
    private javax.swing.JButton supplier_bt;
    // End of variables declaration//GEN-END:variables
}
