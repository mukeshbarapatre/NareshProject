/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lookhub;

import com.itextpdf.text.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author vip
 */
public class AdminLogin extends javax.swing.JFrame {
    ImageIcon iconimg1 = new ImageIcon(getClass().getResource("/lookhub/Images/success.png"));
    String LoginChosser = "ADMIN";
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    /**
     * Creates new form AdminLogin
     */
    public AdminLogin() {
        initComponents();
        this.setTitle("Look Hub - Login");
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lookhub/Images/Unisex.png"));  
        this.setIconImage(icon);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension size = tk.getScreenSize();
        this.setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
        this.username.requestFocus(); 
        salondetail();
    }

    public void salondetail(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from salondetail");
            if(rs.next()){
                Look.setText(rs.getString(2));
                text1.setText("");
                byte[] img = rs.getBytes(7);
                ImageIcon image = new ImageIcon(img);
                java.awt.Image im = image.getImage();
                java.awt.Image myImage = im.getScaledInstance(modelicon.getWidth(), modelicon.getHeight()+2,Image.ORIGINAL_PNG);
                ImageIcon newimg = new ImageIcon(myImage);
                modelicon.setIcon(newimg);
                Hub.setText("");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jSeparator3 = new javax.swing.JSeparator();
        ADMINLOGIN = new javax.swing.JRadioButton();
        USERLOGIN = new javax.swing.JRadioButton();

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
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
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
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        login.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginKeyPressed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        forgetP.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        forgetP.setForeground(new java.awt.Color(0, 0, 204));
        forgetP.setText("forgot password ?");
        forgetP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        forgetP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPMouseClicked(evt);
            }
        });

        ADMINLOGIN.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(ADMINLOGIN);
        ADMINLOGIN.setSelected(true);
        ADMINLOGIN.setText("Admin Login");
        ADMINLOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADMINLOGINActionPerformed(evt);
            }
        });

        USERLOGIN.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(USERLOGIN);
        USERLOGIN.setText("User Login");
        USERLOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                USERLOGINActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(user, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(username)
                            .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LoginLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(ADMINLOGIN)
                        .addGap(27, 27, 27)
                        .addComponent(USERLOGIN)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(forgetP)
                .addGap(131, 131, 131))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ADMINLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(USERLOGIN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(10, 10, 10)
                .addComponent(forgetP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
                .addGap(123, 123, 123)
                .addGroup(AdminPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                    .addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(AdminPannelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 123, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
            try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.runQueryforLogin("SELECT * FROM `userdetails` WHERE FirstName = ? AND Password = ? AND Type = ?", username,password,"USER");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successfully","LOGIN",JOptionPane.INFORMATION_MESSAGE,iconimg1);
                        HomePage hp = new HomePage(rs);
                        hp.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
                    }
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,e);
                }
        }
        }
        if(LoginChosser.equals("ADMIN")){
            
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
            try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.runQueryforLogin("SELECT * FROM `userdetails` WHERE FirstName = ? AND Password = ? AND Type = ?", username,password,"ADMIN");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successfully","LOGIN",JOptionPane.INFORMATION_MESSAGE,iconimg1);
                        HomePage hp = new HomePage(rs);
                        hp.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
                    }
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,e);
                }
        }
        }
    }//GEN-LAST:event_loginActionPerformed

    private void forgetPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPMouseClicked
        // TODO add your handling code here:
        ForgetPassword fp = new ForgetPassword();
        fp.setVisible(true);
    }//GEN-LAST:event_forgetPMouseClicked

    private void ADMINLOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADMINLOGINActionPerformed
        // TODO add your handling code here:
        LoginChosser = "ADMIN";
        password.setText("");
        username.setText("");
        //ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\AdminLogin.png");
        //adminpic.setIcon(iconimg);
        adminpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/AdminLogin.png")));
        username.requestFocus();
    }//GEN-LAST:event_ADMINLOGINActionPerformed

    private void USERLOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_USERLOGINActionPerformed
        // TODO add your handling code here:
        LoginChosser = "USER";
        password.setText("");
        username.setText("");
        //ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\UserLogin.png");
        //adminpic.setIcon(iconimg);
        adminpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/UserLogin.png")));
        username.requestFocus();
    }//GEN-LAST:event_USERLOGINActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(username.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Username","Username",JOptionPane.OK_OPTION);
            }else{
                password.requestFocus();
            }
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(password.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Password","Password",JOptionPane.OK_OPTION);
            }else{
                login.requestFocus();
            }
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void loginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(LoginChosser.equals("USER")){
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
                    try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.runQueryforLogin("SELECT * FROM `userdetails` WHERE FirstName = ? AND Password = ? AND Type = ?", username,password,"USER");
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successfully","LOGIN",JOptionPane.INFORMATION_MESSAGE,iconimg1);
                        HomePage hp = new HomePage(rs);
                        hp.setVisible(true);
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
                    }
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,e);
                }
        }
        }
        if(LoginChosser.equals("ADMIN")){
        if (password.getText().equals("") || username.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter  Password or Username","Password or Username",JOptionPane.OK_OPTION);
        }else{
            try {
                con=DbUtil.loadDriver();
                rs=DbUtil.runQueryforLogin("select * from userdetails where FirstName = ? AND Password = ? AND Type = ? ", username,password,"ADMIN");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successfully","LOGIN",JOptionPane.INFORMATION_MESSAGE,iconimg1);
                    HomePage hp = new HomePage(rs);
                    hp.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Enter a valid Password or Username","Invalid Password or Username",JOptionPane.OK_OPTION);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e);
            }
        }
        }
        }
    }//GEN-LAST:event_loginKeyPressed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ADMINLOGIN;
    private javax.swing.JPanel AdminPannel;
    private javax.swing.JLabel Hub;
    private javax.swing.JPanel Login;
    private javax.swing.JLabel Look;
    private javax.swing.JRadioButton USERLOGIN;
    private javax.swing.JLabel adminpic;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton exit;
    private javax.swing.JLabel forgetP;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton login;
    private javax.swing.JLabel modelicon;
    private javax.swing.JLabel pass;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel user;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
