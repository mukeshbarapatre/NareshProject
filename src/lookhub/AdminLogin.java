/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookhub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author vip
 */
public class AdminLogin extends javax.swing.JFrame {

    String LoginChosser = "ADMIN";
    /**
     * Creates new form AdminLogin
     */
    public AdminLogin() {
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();
        this.setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminPannel = new javax.swing.JPanel();
        Look = new javax.swing.JLabel();
        Hub = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        modelicon = new javax.swing.JLabel();
        text1 = new javax.swing.JLabel();
        Login = new javax.swing.JPanel();
        adminpic = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        forgetP = new javax.swing.JLabel();
        forgetU = new javax.swing.JLabel();
        registration = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        nextADMIN = new javax.swing.JButton();
        nextUSER = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        AdminPannel.setBackground(new java.awt.Color(38, 3, 3));

        Look.setFont(new java.awt.Font("Narkisim", 1, 36)); // NOI18N
        Look.setForeground(new java.awt.Color(255, 24, 204));
        Look.setText("LOOK");

        Hub.setFont(new java.awt.Font("Narkisim", 1, 36)); // NOI18N
        Hub.setForeground(new java.awt.Color(255, 24, 204));
        Hub.setText("HUB");

        modelicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/Unisex.png"))); // NOI18N
        modelicon.setText("jLabel1");

        text1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        text1.setForeground(new java.awt.Color(255, 255, 255));
        text1.setText(" FAMILY SALON & BEAUTY ACADEMY");

        Login.setBackground(new java.awt.Color(204, 204, 204));
        Login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        Login.setForeground(new java.awt.Color(255, 255, 255));

        adminpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/AdminLogin.png"))); // NOI18N
        adminpic.setText("jLabel1");
        adminpic.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        user.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        user.setText("Username ");

        pass.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        pass.setText("Password  ");

        username.setBackground(new java.awt.Color(153, 153, 153));
        username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFocusLost(evt);
            }
        });

        password.setBackground(new java.awt.Color(153, 153, 153));
        password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });

        login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        forgetP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        forgetP.setForeground(new java.awt.Color(0, 0, 204));
        forgetP.setText("forget password ?");
        forgetP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgetP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPMouseClicked(evt);
            }
        });

        forgetU.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        forgetU.setForeground(new java.awt.Color(0, 0, 204));
        forgetU.setText("forget username ?");
        forgetU.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgetU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetUMouseClicked(evt);
            }
        });

        registration.setText("REGISTRATION");
        registration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registration, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(user, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(username)
                    .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(forgetP)
                .addGap(32, 32, 32)
                .addComponent(forgetU)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forgetP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(forgetU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(registration, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        nextADMIN.setText("ADMIN");
        nextADMIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextADMINActionPerformed(evt);
            }
        });

        nextUSER.setText("USER");
        nextUSER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextUSERActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminPannelLayout = new javax.swing.GroupLayout(AdminPannel);
        AdminPannel.setLayout(AdminPannelLayout);
        AdminPannelLayout.setHorizontalGroup(
            AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPannelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modelicon, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Hub)
                    .addComponent(Look))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AdminPannelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(nextADMIN)
                .addGap(31, 31, 31)
                .addGroup(AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(nextUSER, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        AdminPannelLayout.setVerticalGroup(
            AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPannelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addComponent(Look)
                        .addGap(0, 0, 0)
                        .addComponent(Hub))
                    .addComponent(modelicon))
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(nextUSER, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(nextADMIN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
        // TODO add your handling code here:
        username.setBackground(Color.WHITE);
        username.setForeground(Color.BLACK);
    }//GEN-LAST:event_usernameFocusGained

    private void usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusLost
        // TODO add your handling code here:
        username.setBackground(new Color(153,153,153));
        username.setForeground(Color.BLACK);
    }//GEN-LAST:event_usernameFocusLost

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
        password.setBackground(Color.WHITE);
        password.setForeground(Color.BLACK);
    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        // TODO add your handling code here:
        password.setBackground(new Color(153,153,153));
        password.setForeground(Color.BLACK);
    }//GEN-LAST:event_passwordFocusLost

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        int exitopt = JOptionPane.showConfirmDialog(this, "Do you really want to exit ?","EXIT",JOptionPane.YES_NO_OPTION);
        
        if(exitopt==JOptionPane.YES_OPTION){
            dispose();
        }
    }//GEN-LAST:event_exitActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        if(LoginChosser.equals("USER")){
            String usrn = "username";
            String pswd = "vaibhav";
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
        if(password.getText().equals(pswd) && username.getText().equals(usrn)){
            System.out.println("Match");
            
            //code for login to main page
            HomePage hp = new HomePage();
            hp.setVisible(true);
            
        }else{
                    JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
        }
        }
        }
        if(LoginChosser.equals("ADMIN")){
            String usrn = "vip";
            String pswd = "vaibhav";
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
        if(password.getText().equals(pswd) && username.getText().equals(usrn)){
            System.out.println("Match");
            
            HomePage hp = new HomePage();
            hp.setVisible(true);
            
            //code for login to main page
            
        }else{
                    JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
        }
        }
        }
    }//GEN-LAST:event_loginActionPerformed

    private void registrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationActionPerformed
        // TODO add your handling code here:
        RegistrationForm rj = new RegistrationForm();
       dispose();
        rj.setVisible(true);
    }//GEN-LAST:event_registrationActionPerformed

    private void forgetPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPMouseClicked
        // TODO add your handling code here:
        ForgetPassWord fp = new ForgetPassWord();
        fp.setVisible(true);
    }//GEN-LAST:event_forgetPMouseClicked

    private void forgetUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetUMouseClicked
        // TODO add your handling code here:
        ForgetUserName fu = new ForgetUserName();
        fu.setVisible(true);
    }//GEN-LAST:event_forgetUMouseClicked

    private void nextUSERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextUSERActionPerformed
        // TODO add your handling code here:
        LoginChosser = "USER";
        password.setText("");
        username.setText("");
        ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\UserLogin.png");
        adminpic.setIcon(iconimg);
        
        
    }//GEN-LAST:event_nextUSERActionPerformed

    private void nextADMINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextADMINActionPerformed
        // TODO add your handling code here:
        LoginChosser = "ADMIN";
        password.setText("");
        username.setText("");
        ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\AdminLogin.png");
        adminpic.setIcon(iconimg);
    }//GEN-LAST:event_nextADMINActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPannel;
    private javax.swing.JLabel Hub;
    private javax.swing.JPanel Login;
    private javax.swing.JLabel Look;
    private javax.swing.JLabel adminpic;
    private javax.swing.JButton exit;
    private javax.swing.JLabel forgetP;
    private javax.swing.JLabel forgetU;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton login;
    private javax.swing.JLabel modelicon;
    private javax.swing.JButton nextADMIN;
    private javax.swing.JButton nextUSER;
    private javax.swing.JLabel pass;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registration;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel user;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
