package lookhub;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

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
    Connection con;
    Statement stmt;
    ResultSet rs;

    public HomePage()  {
       
        
        
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
        int ysize = (int)tk.getScreenSize().getHeight();
        this.setSize(xsize,ysize);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(HomeTab), getTitlePanel(jTabbedPane1, HomeTab, "HOME  "));
        
        RegistryScrool.getVerticalScrollBar().setUnitIncrement(20);
        ReportScroll.getVerticalScrollBar().setUnitIncrement(20);
        ServiceScroll1.getVerticalScrollBar().setUnitIncrement(20);
        ProductScroll.getVerticalScrollBar().setUnitIncrement(20);
        //here is method to fetch data
        
        getServiceTabelData();
        getProductTabelData();
        
        
        
        
        //here is code for passLength
        
        PasswordTextfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                passLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                passLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                passLength();
            }
            public void passLength(){
                if(((PasswordTextfield.getText()).length())<8){
                    passLabel.setForeground(Color.RED);
                    passLabel.setText("Passwords must be min 8 characters");
                }
                else{
                    passLabel.setText("");
                    if(((PasswordTextfield.getText()).length())>16){
                    passLabel.setForeground(Color.RED);
                    passLabel.setText("Passwords must be max 16 characters");
                }
                else{
                    passLabel.setText("");
                }
                }
            }
        });
        
        
        //here is code for confirm
        
        ConfirmPasswordTextfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                confirm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                confirm();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                confirm();
            }
            public void confirm(){
                if ((PasswordTextfield.getText()).equals(ConfirmPasswordTextfield.getText())) {
                    confirm_pswd.setForeground(new Color(0,102,51));
                    confirm_pswd.setText("password match");
                }else{
                    confirm_pswd.setForeground(Color.RED);
                    confirm_pswd.setText("password not match");
                }
            }
        });
        EditService.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
            public void search(){
                
            }
        });
        
    }
    private static JPanel getTitlePanel(final JTabbedPane tabbedPane, final JPanel panel, String title)
 {
  JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
  titlePanel.setOpaque(false);
  JLabel titleLbl = new JLabel(title);
  //titleLbl.setBorder(BorderLayout.createEmptyBorder(0, 0, 0, 5));
  titlePanel.add(titleLbl);
  JLabel closeButton = new JLabel("x");
  closeButton.setForeground(Color.red);
  closeButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
  closeButton.addMouseListener(new MouseAdapter()
  {
   @Override
   public void mouseClicked(MouseEvent e)
   {
    tabbedPane.remove(panel);
   }
  });


  titlePanel.add(closeButton);
  return titlePanel;
 }
    
    
    
    /*here is code for fetchting Service Data into Service Table*/
    public void getServiceTabelData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from services");
            ServiceTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }
    /*here is code for fetchting Product Data into Service Table*/
    public void getProductTabelData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from product");
            ProductTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
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

        SalonDTab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        EmployeeDTab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ProductDtab = new javax.swing.JPanel();
        ProductScroll = new javax.swing.JScrollPane();
        ServicePanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        mainservicepan1 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        AddSpan1 = new javax.swing.JPanel();
        NameL = new javax.swing.JLabel();
        PricceL = new javax.swing.JLabel();
        NameTF = new javax.swing.JTextField();
        PriceTF = new javax.swing.JTextField();
        codeL = new javax.swing.JLabel();
        codeTF = new javax.swing.JTextField();
        QuantityL = new javax.swing.JLabel();
        QuantityTF = new javax.swing.JTextField();
        SupplierL = new javax.swing.JLabel();
        SupplierTF = new javax.swing.JTextField();
        CostL = new javax.swing.JLabel();
        CostTf = new javax.swing.JTextField();
        ProductAdd = new javax.swing.JButton();
        EditSpan1 = new javax.swing.JPanel();
        editservice_btn1 = new javax.swing.JButton();
        EditSer1 = new javax.swing.JButton();
        ResetEdit1 = new javax.swing.JButton();
        ServicT6 = new javax.swing.JLabel();
        AddServiceField4 = new javax.swing.JTextField();
        ServicT7 = new javax.swing.JLabel();
        AddServiceField5 = new javax.swing.JTextField();
        S_Pricce4 = new javax.swing.JLabel();
        AddPriceField4 = new javax.swing.JTextField();
        ServicT8 = new javax.swing.JLabel();
        AddServiceField6 = new javax.swing.JTextField();
        S_Pricce5 = new javax.swing.JLabel();
        AddPriceField5 = new javax.swing.JTextField();
        ServicT9 = new javax.swing.JLabel();
        AddServiceField7 = new javax.swing.JTextField();
        S_Pricce6 = new javax.swing.JLabel();
        AddPriceField6 = new javax.swing.JTextField();
        ServiceOperation1 = new javax.swing.JLabel();
        ServiceTableLAble1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        AddSpan2 = new javax.swing.JPanel();
        ServicT11 = new javax.swing.JLabel();
        AddServiceField10 = new javax.swing.JTextField();
        S_Pricce9 = new javax.swing.JLabel();
        AddPriceField9 = new javax.swing.JTextField();
        addservice_btn2 = new javax.swing.JButton();
        ServicT10 = new javax.swing.JLabel();
        AddServiceField8 = new javax.swing.JTextField();
        editservice_btn2 = new javax.swing.JButton();
        RegistrationDTab = new javax.swing.JPanel();
        RegistryScrool = new javax.swing.JScrollPane();
        RegistryPanel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        Surname = new javax.swing.JLabel();
        ContactNumebe = new javax.swing.JLabel();
        Gender = new javax.swing.JLabel();
        UserId = new javax.swing.JLabel();
        NameTextfield = new javax.swing.JTextField();
        SurnameTextfield = new javax.swing.JTextField();
        ContactTextfield = new javax.swing.JTextField();
        UserIdTextfield = new javax.swing.JTextField();
        ConfirmPassword = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        PasswordTextfield = new javax.swing.JPasswordField();
        ConfirmPasswordTextfield = new javax.swing.JPasswordField();
        MailTextfield = new javax.swing.JTextField();
        Submit = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        addrees = new javax.swing.JLabel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jTextArea = new javax.swing.JTextArea();
        confirm_pswd = new javax.swing.JLabel();
        emaillabel = new javax.swing.JLabel();
        passLabel = new javax.swing.JLabel();
        Look4 = new javax.swing.JLabel();
        Hub2 = new javax.swing.JLabel();
        modelicon3 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        Registration2 = new javax.swing.JLabel();
        BillingDTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BillingPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BillSheet = new javax.swing.JPanel();
        DetailPanel = new javax.swing.JPanel();
        CustumerN = new javax.swing.JLabel();
        CustumerjTextField = new javax.swing.JTextField();
        Mobile = new javax.swing.JLabel();
        MobilejTextField = new javax.swing.JTextField();
        BillDate = new javax.swing.JLabel();
        BillNo = new javax.swing.JLabel();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        billNOjTextField1 = new javax.swing.JTextField();
        EmailCust = new javax.swing.JLabel();
        EmailCustjTextField1 = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        ServiceCPanel = new javax.swing.JPanel();
        Service = new javax.swing.JLabel();
        Discount = new javax.swing.JLabel();
        Qty1 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        ServiceSearch = new javax.swing.JTextField();
        Quantity = new javax.swing.JTextField();
        UnitPrice = new javax.swing.JTextField();
        ServiceTotal = new javax.swing.JTextField();
        ServiceDiscount = new javax.swing.JTextField();
        BServiceAdd = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        BillingTable = new javax.swing.JTable();
        BServiceDel = new javax.swing.JButton();
        FinalBillPanal = new javax.swing.JPanel();
        Total1 = new javax.swing.JLabel();
        Discount1 = new javax.swing.JLabel();
        BarberName = new javax.swing.JLabel();
        AllTotal = new javax.swing.JTextField();
        BarberNameField = new javax.swing.JTextField();
        TotalDiscount2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ResetBill = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        Print = new javax.swing.JButton();
        SaveasPDF = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ServiceDTab2 = new javax.swing.JPanel();
        ServiceScroll1 = new javax.swing.JScrollPane();
        ServicePanel = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        mainservicepan = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        AddSpan = new javax.swing.JPanel();
        ServicT = new javax.swing.JLabel();
        S_Pricce = new javax.swing.JLabel();
        addservice_btn = new javax.swing.JButton();
        AddServiceField = new javax.swing.JTextField();
        AddPriceField = new javax.swing.JTextField();
        DeleteSpan = new javax.swing.JPanel();
        ServiceT2 = new javax.swing.JLabel();
        Deleteservice_btn = new javax.swing.JButton();
        DeleteService = new javax.swing.JTextField();
        EditSpan = new javax.swing.JPanel();
        ServicT1 = new javax.swing.JLabel();
        editservice_btn = new javax.swing.JButton();
        EditService = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        ChangeName = new javax.swing.JLabel();
        AddService1 = new javax.swing.JTextField();
        ChangePrice = new javax.swing.JLabel();
        AddPrice1 = new javax.swing.JTextField();
        EditSer = new javax.swing.JButton();
        ResetEdit = new javax.swing.JButton();
        ServiceOperation = new javax.swing.JLabel();
        ServiceTableLAble = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ServiceTable = new javax.swing.JTable();
        buttonGroupMaleFemale = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        ReportScroll = new javax.swing.JScrollPane();
        ReportPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        reportlabel = new javax.swing.JLabel();
        homepanel = new javax.swing.JPanel();
        Look = new javax.swing.JLabel();
        Look1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        adminpic = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        modelicon = new javax.swing.JLabel();
        buttonpanel = new javax.swing.JPanel();
        deltail_bt = new javax.swing.JButton();
        emp_bt = new javax.swing.JButton();
        client_bt = new javax.swing.JButton();
        product_bt = new javax.swing.JButton();
        service_bt = new javax.swing.JButton();
        supplier_bt = new javax.swing.JButton();
        report_bt = new javax.swing.JButton();
        billing_bt = new javax.swing.JButton();
        other_bt = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        HomeTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        SalonDTab.setBackground(new java.awt.Color(204, 204, 204));
        SalonDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Salon Details");

        javax.swing.GroupLayout SalonDTabLayout = new javax.swing.GroupLayout(SalonDTab);
        SalonDTab.setLayout(SalonDTabLayout);
        SalonDTabLayout.setHorizontalGroup(
            SalonDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SalonDTabLayout.createSequentialGroup()
                .addContainerGap(402, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(362, 362, 362))
        );
        SalonDTabLayout.setVerticalGroup(
            SalonDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalonDTabLayout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        EmployeeDTab.setBackground(new java.awt.Color(204, 204, 204));
        EmployeeDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Employee");

        javax.swing.GroupLayout EmployeeDTabLayout = new javax.swing.GroupLayout(EmployeeDTab);
        EmployeeDTab.setLayout(EmployeeDTabLayout);
        EmployeeDTabLayout.setHorizontalGroup(
            EmployeeDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeDTabLayout.createSequentialGroup()
                .addContainerGap(447, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(374, 374, 374))
        );
        EmployeeDTabLayout.setVerticalGroup(
            EmployeeDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeDTabLayout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        ProductDtab.setBackground(new java.awt.Color(38, 3, 3));

        ProductScroll.setPreferredSize(new java.awt.Dimension(946, 515));

        ServicePanel1.setPreferredSize(new java.awt.Dimension(944, 515));

        jPanel9.setBackground(new java.awt.Color(38, 3, 3));

        mainservicepan1.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        NameL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameL.setText("Product Name :");

        PricceL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PricceL.setText("Price       :");

        NameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        PriceTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        codeL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codeL.setText("Product Code  :");

        codeTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        codeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeTFActionPerformed(evt);
            }
        });

        QuantityL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        QuantityL.setText("Quantity :");

        QuantityTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        SupplierL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SupplierL.setText("Supplier Name :");

        SupplierTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SupplierTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierTFActionPerformed(evt);
            }
        });

        CostL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CostL.setText("Cost       :");

        CostTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ProductAdd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ProductAdd.setText("Add ");
        ProductAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan1Layout = new javax.swing.GroupLayout(AddSpan1);
        AddSpan1.setLayout(AddSpan1Layout);
        AddSpan1Layout.setHorizontalGroup(
            AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameL, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeL)
                    .addComponent(SupplierL))
                .addGap(18, 18, 18)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SupplierTF, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan1Layout.createSequentialGroup()
                        .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QuantityL, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(CostL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QuantityTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CostTf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddSpan1Layout.createSequentialGroup()
                        .addComponent(PricceL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(AddSpan1Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(ProductAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddSpan1Layout.setVerticalGroup(
            AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PricceL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuantityL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SupplierL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SupplierTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CostL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CostTf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ProductAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editservice_btn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        editservice_btn1.setText("Search");
        editservice_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editservice_btn1ActionPerformed(evt);
            }
        });

        EditSer1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        EditSer1.setText("Edit");

        ResetEdit1.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        ResetEdit1.setText("Reset");

        ServicT6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT6.setText("Product Name :");

        AddServiceField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServicT7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT7.setText("Edit Name       :");

        AddServiceField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        S_Pricce4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce4.setText("Edit Price :");

        AddPriceField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddPriceField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPriceField4ActionPerformed(evt);
            }
        });

        ServicT8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT8.setText("Edit Code        :");

        AddServiceField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        S_Pricce5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce5.setText("Quantity :");

        AddPriceField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServicT9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT9.setText("Supplier Name :");

        AddServiceField7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        S_Pricce6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce6.setText("Cost        :");

        AddPriceField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout EditSpan1Layout = new javax.swing.GroupLayout(EditSpan1);
        EditSpan1.setLayout(EditSpan1Layout);
        EditSpan1Layout.setHorizontalGroup(
            EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpan1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditSpan1Layout.createSequentialGroup()
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(EditSer1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)
                                .addComponent(ResetEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(ServicT6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddServiceField4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(editservice_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(ServicT9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddServiceField7, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(EditSpan1Layout.createSequentialGroup()
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(ServicT8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddServiceField6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(ServicT7, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddServiceField5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(S_Pricce5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(S_Pricce4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddPriceField4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddPriceField5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(S_Pricce6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(AddPriceField6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))))
        );
        EditSpan1Layout.setVerticalGroup(
            EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpan1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editservice_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ServicT6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPriceField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPriceField5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPriceField6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditSer1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResetEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ServiceOperation1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceOperation1.setText("Product Operation");

        ServiceTableLAble1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceTableLAble1.setText("Product Tabel");

        ProductTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(ProductTable);
        if (ProductTable.getColumnModel().getColumnCount() > 0) {
            ProductTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            ProductTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            ProductTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            ProductTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        ServicT11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT11.setText("Supplier Name :");

        AddServiceField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        S_Pricce9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce9.setText("   Cost :");

        AddPriceField9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        addservice_btn2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addservice_btn2.setText("Add ");
        addservice_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addservice_btn2ActionPerformed(evt);
            }
        });

        ServicT10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT10.setText("Product Name :");

        AddServiceField8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        editservice_btn2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        editservice_btn2.setText("Delete");
        editservice_btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editservice_btn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan2Layout = new javax.swing.GroupLayout(AddSpan2);
        AddSpan2.setLayout(AddSpan2Layout);
        AddSpan2Layout.setHorizontalGroup(
            AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan2Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(addservice_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AddSpan2Layout.createSequentialGroup()
                .addGroup(AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(ServicT11))
                    .addGroup(AddSpan2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ServicT10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddServiceField8, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan2Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(AddServiceField10, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(S_Pricce9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddPriceField9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(AddSpan2Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(editservice_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        AddSpan2Layout.setVerticalGroup(
            AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editservice_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPriceField9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addservice_btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainservicepan1Layout = new javax.swing.GroupLayout(mainservicepan1);
        mainservicepan1.setLayout(mainservicepan1Layout);
        mainservicepan1Layout.setHorizontalGroup(
            mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainservicepan1Layout.createSequentialGroup()
                .addGroup(mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ServiceOperation1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainservicepan1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EditSpan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddSpan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddSpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainservicepan1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(ServiceTableLAble1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        mainservicepan1Layout.setVerticalGroup(
            mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainservicepan1Layout.createSequentialGroup()
                .addGroup(mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainservicepan1Layout.createSequentialGroup()
                        .addComponent(ServiceOperation1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(EditSpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddSpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainservicepan1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(mainservicepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addGroup(mainservicepan1Layout.createSequentialGroup()
                                .addComponent(ServiceTableLAble1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainservicepan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(mainservicepan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout ServicePanel1Layout = new javax.swing.GroupLayout(ServicePanel1);
        ServicePanel1.setLayout(ServicePanel1Layout);
        ServicePanel1Layout.setHorizontalGroup(
            ServicePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicePanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 962, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ServicePanel1Layout.setVerticalGroup(
            ServicePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ProductScroll.setViewportView(ServicePanel1);

        javax.swing.GroupLayout ProductDtabLayout = new javax.swing.GroupLayout(ProductDtab);
        ProductDtab.setLayout(ProductDtabLayout);
        ProductDtabLayout.setHorizontalGroup(
            ProductDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductDtabLayout.createSequentialGroup()
                .addComponent(ProductScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        ProductDtabLayout.setVerticalGroup(
            ProductDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductDtabLayout.createSequentialGroup()
                .addComponent(ProductScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        RegistrationDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        RegistryPanel.setBackground(new java.awt.Color(38, 3, 3));
        RegistryPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        RegistryPanel.setForeground(new java.awt.Color(221, 0, 0));
        RegistryPanel.setAutoscrolls(true);
        RegistryPanel.setName(""); // NOI18N
        RegistryPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegistryPanelMouseClicked(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        Name.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Name.setText("First Name");

        Surname.setBackground(new java.awt.Color(255, 255, 255));
        Surname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Surname.setText("Last Name");

        ContactNumebe.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ContactNumebe.setText("Contact Number");

        Gender.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Gender.setText("Gender");

        UserId.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        UserId.setText("User ID");

        NameTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        NameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTextfieldActionPerformed(evt);
                NameTextfieldVishal(evt);
            }
        });
        NameTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NameTextfieldKeyPressed(evt);
            }
        });

        SurnameTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        SurnameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SurnameTextfieldActionPerformed(evt);
            }
        });
        SurnameTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SurnameTextfieldKeyPressed(evt);
            }
        });

        ContactTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ContactTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactTextfieldActionPerformed(evt);
            }
        });
        ContactTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ContactTextfieldKeyPressed(evt);
            }
        });

        UserIdTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        UserIdTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserIdTextfieldActionPerformed(evt);
            }
        });
        UserIdTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UserIdTextfieldKeyPressed(evt);
            }
        });

        ConfirmPassword.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ConfirmPassword.setText("Confirm Password");

        Password.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Password.setText("Password");

        Email.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Email.setText("Email Address");

        PasswordTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        PasswordTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTextfieldActionPerformed(evt);
            }
        });
        PasswordTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordTextfieldKeyPressed(evt);
            }
        });

        ConfirmPasswordTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ConfirmPasswordTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmPasswordTextfieldActionPerformed(evt);
            }
        });
        ConfirmPasswordTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ConfirmPasswordTextfieldKeyPressed(evt);
            }
        });

        MailTextfield.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        MailTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MailTextfieldActionPerformed(evt);
            }
        });
        MailTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MailTextfieldKeyPressed(evt);
            }
        });

        Submit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Submit.setText("Submit");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        Reset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        addrees.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addrees.setText("Address");

        male.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroupMaleFemale.add(male);
        male.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        male.setText("Male");
        male.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                maleKeyPressed(evt);
            }
        });

        female.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroupMaleFemale.add(female);
        female.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        female.setText("Female");
        female.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                femaleKeyPressed(evt);
            }
        });

        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextArea.setRows(5);
        jTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaKeyPressed(evt);
            }
        });

        emaillabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emaillabelFocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(passLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addrees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Gender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ContactNumebe, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(Surname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UserId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(male)
                                        .addGap(28, 28, 28)
                                        .addComponent(female)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(SurnameTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ContactTextfield)
                                            .addComponent(NameTextfield)
                                            .addComponent(jTextArea, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(UserIdTextfield))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(ConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addGap(6, 6, 6))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(Password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emaillabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(confirm_pswd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(MailTextfield)
                                    .addComponent(ConfirmPasswordTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(PasswordTextfield, javax.swing.GroupLayout.Alignment.TRAILING))))))
                .addGap(29, 29, 29))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SurnameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContactNumebe)
                    .addComponent(ContactTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(addrees, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(male)
                            .addComponent(female))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserIdTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfirmPasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirm_pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MailTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Look4.setFont(new java.awt.Font("Narkisim", 1, 36)); // NOI18N
        Look4.setForeground(new java.awt.Color(255, 24, 204));
        Look4.setText("LOOK");

        Hub2.setFont(new java.awt.Font("Narkisim", 1, 36)); // NOI18N
        Hub2.setForeground(new java.awt.Color(255, 24, 204));
        Hub2.setText("HUB");

        modelicon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/Unisex.png"))); // NOI18N
        modelicon3.setText("jLabel1");

        Registration2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Registration2.setForeground(java.awt.Color.white);
        Registration2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Registration2.setText("Registration Form");
        Registration2.setName(""); // NOI18N

        javax.swing.GroupLayout RegistryPanelLayout = new javax.swing.GroupLayout(RegistryPanel);
        RegistryPanel.setLayout(RegistryPanelLayout);
        RegistryPanelLayout.setHorizontalGroup(
            RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistryPanelLayout.createSequentialGroup()
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(modelicon3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Look4)
                            .addComponent(Hub2)))
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(Registration2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        RegistryPanelLayout.setVerticalGroup(
            RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addComponent(Look4)
                        .addGap(0, 0, 0)
                        .addComponent(Hub2))
                    .addComponent(modelicon3))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(Registration2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        RegistryScrool.setViewportView(RegistryPanel);

        javax.swing.GroupLayout RegistrationDTabLayout = new javax.swing.GroupLayout(RegistrationDTab);
        RegistrationDTab.setLayout(RegistrationDTabLayout);
        RegistrationDTabLayout.setHorizontalGroup(
            RegistrationDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegistryScrool, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
        );
        RegistrationDTabLayout.setVerticalGroup(
            RegistrationDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegistryScrool, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
        );

        BillingDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(944, 586));

        BillingPanel.setBackground(new java.awt.Color(38, 3, 3));
        BillingPanel.setPreferredSize(new java.awt.Dimension(944, 586));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout BillSheetLayout = new javax.swing.GroupLayout(BillSheet);
        BillSheet.setLayout(BillSheetLayout);
        BillSheetLayout.setHorizontalGroup(
            BillSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );
        BillSheetLayout.setVerticalGroup(
            BillSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        CustumerN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustumerN.setText("Custumer Name ");

        CustumerjTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustumerjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CustumerjTextFieldKeyPressed(evt);
            }
        });

        Mobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Mobile.setText("Cust Mobile No.");

        MobilejTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MobilejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobilejTextFieldKeyPressed(evt);
            }
        });

        BillDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillDate.setText("BILL Date");

        BillNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillNo.setText("BILL No.");

        billNOjTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EmailCust.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailCust.setText("Cust Email ");

        EmailCustjTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailCustjTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailCustjTextField1ActionPerformed(evt);
            }
        });
        EmailCustjTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailCustjTextField1KeyPressed(evt);
            }
        });

        emailLabel.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout DetailPanelLayout = new javax.swing.GroupLayout(DetailPanel);
        DetailPanel.setLayout(DetailPanelLayout);
        DetailPanelLayout.setHorizontalGroup(
            DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Mobile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CustumerN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EmailCust, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DetailPanelLayout.createSequentialGroup()
                        .addComponent(CustumerjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailPanelLayout.createSequentialGroup()
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EmailCustjTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DetailPanelLayout.createSequentialGroup()
                                .addComponent(MobilejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(billNOjTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(28, 28, 28))
        );
        DetailPanelLayout.setVerticalGroup(
            DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CustumerN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CustumerjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DetailPanelLayout.createSequentialGroup()
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Mobile, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MobilejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(EmailCustjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EmailCust, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(billNOjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        Service.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Service.setText("Service");

        Discount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Discount.setText("Discount");

        Qty1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Qty1.setText("Qty");

        Total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Total.setText("Total Price");

        unit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        unit.setText("Unit Price");

        ServiceSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        UnitPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServiceTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServiceDiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServiceDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServiceDiscountActionPerformed(evt);
            }
        });

        BServiceAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BServiceAdd.setText("Add");

        BillingTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillingTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(BillingTable);

        BServiceDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BServiceDel.setText("Del");

        javax.swing.GroupLayout ServiceCPanelLayout = new javax.swing.GroupLayout(ServiceCPanel);
        ServiceCPanel.setLayout(ServiceCPanelLayout);
        ServiceCPanelLayout.setHorizontalGroup(
            ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServiceCPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServiceCPanelLayout.createSequentialGroup()
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ServiceSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Service, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Qty1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unit))
                        .addGap(10, 10, 10)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Discount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ServiceDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Total)
                            .addComponent(ServiceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BServiceAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BServiceDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );
        ServiceCPanelLayout.setVerticalGroup(
            ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServiceCPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServiceCPanelLayout.createSequentialGroup()
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unit, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Service)
                                .addComponent(Qty1)
                                .addComponent(Total)
                                .addComponent(Discount)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ServiceCPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ServiceSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ServiceDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ServiceTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ServiceCPanelLayout.createSequentialGroup()
                        .addComponent(BServiceAdd)
                        .addGap(3, 3, 3)
                        .addComponent(BServiceDel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );

        Total1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Total1.setText("Total Price");

        Discount1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Discount1.setText("Discount");

        BarberName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BarberName.setText("Barber Name");

        AllTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AllTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AllTotalActionPerformed(evt);
            }
        });

        BarberNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BarberNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarberNameFieldActionPerformed(evt);
            }
        });

        TotalDiscount2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TotalDiscount2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalDiscount2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FinalBillPanalLayout = new javax.swing.GroupLayout(FinalBillPanal);
        FinalBillPanal.setLayout(FinalBillPanalLayout);
        FinalBillPanalLayout.setHorizontalGroup(
            FinalBillPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalBillPanalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Discount1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Total1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BarberName, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BarberNameField)
                .addContainerGap())
        );
        FinalBillPanalLayout.setVerticalGroup(
            FinalBillPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FinalBillPanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FinalBillPanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AllTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Total1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalDiscount2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Discount1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BarberName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BarberNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/bill.png"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("New");

        ResetBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ResetBill.setText("Reset");

        Save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Save.setText("Save");

        Cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Cancel.setText("Cancel");

        Print.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Print.setText("Print");

        SaveasPDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SaveasPDF.setText("PDF");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ServiceCPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FinalBillPanal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(ResetBill, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(Cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BillSheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(SaveasPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(DetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ServiceCPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FinalBillPanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BillSheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(ResetBill, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Print, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addComponent(SaveasPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))))
                .addGap(5, 5, 5))
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("BILLING");

        javax.swing.GroupLayout BillingPanelLayout = new javax.swing.GroupLayout(BillingPanel);
        BillingPanel.setLayout(BillingPanelLayout);
        BillingPanelLayout.setHorizontalGroup(
            BillingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BillingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BillingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BillingPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        BillingPanelLayout.setVerticalGroup(
            BillingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BillingPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        jScrollPane1.setViewportView(BillingPanel);

        javax.swing.GroupLayout BillingDTabLayout = new javax.swing.GroupLayout(BillingDTab);
        BillingDTab.setLayout(BillingDTabLayout);
        BillingDTabLayout.setHorizontalGroup(
            BillingDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BillingDTabLayout.setVerticalGroup(
            BillingDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ServiceDTab2.setPreferredSize(new java.awt.Dimension(944, 517));

        ServiceScroll1.setPreferredSize(new java.awt.Dimension(946, 515));

        ServicePanel.setPreferredSize(new java.awt.Dimension(944, 515));

        jPanel8.setBackground(new java.awt.Color(38, 3, 3));

        mainservicepan.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        ServicT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT.setText("Service Name");

        S_Pricce.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce.setText("Price");

        addservice_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        addservice_btn.setText("Add ");
        addservice_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addservice_btnActionPerformed(evt);
            }
        });

        AddServiceField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        AddPriceField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout AddSpanLayout = new javax.swing.GroupLayout(AddSpan);
        AddSpan.setLayout(AddSpanLayout);
        AddSpanLayout.setHorizontalGroup(
            AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(ServicT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddServiceField)
                .addGap(5, 5, 5)
                .addComponent(S_Pricce, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(addservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        AddSpanLayout.setVerticalGroup(
            AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpanLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddServiceField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        ServiceT2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServiceT2.setText("Service Name");

        Deleteservice_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Deleteservice_btn.setText("Delete ");
        Deleteservice_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Deleteservice_btnActionPerformed(evt);
            }
        });

        DeleteService.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout DeleteSpanLayout = new javax.swing.GroupLayout(DeleteSpan);
        DeleteSpan.setLayout(DeleteSpanLayout);
        DeleteSpanLayout.setHorizontalGroup(
            DeleteSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeleteSpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(ServiceT2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DeleteService)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Deleteservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        DeleteSpanLayout.setVerticalGroup(
            DeleteSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteSpanLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(DeleteSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServiceT2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deleteservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteService, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        ServicT1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT1.setText("Service Name");

        editservice_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        editservice_btn.setText("Search");
        editservice_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editservice_btnActionPerformed(evt);
            }
        });

        EditService.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 0));
        jLabel13.setText("Example = hair cut");

        ChangeName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChangeName.setText("Edit Name");

        AddService1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ChangePrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChangePrice.setText("Edit Price");

        AddPrice1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EditSer.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        EditSer.setText("Edit");

        ResetEdit.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        ResetEdit.setText("Reset");

        javax.swing.GroupLayout EditSpanLayout = new javax.swing.GroupLayout(EditSpan);
        EditSpan.setLayout(EditSpanLayout);
        EditSpanLayout.setHorizontalGroup(
            EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditSpanLayout.createSequentialGroup()
                        .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ChangePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(ChangeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(EditSer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(ResetEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddService1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(EditSpanLayout.createSequentialGroup()
                        .addComponent(ServicT1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addComponent(EditService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))))
        );
        EditSpanLayout.setVerticalGroup(
            EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpanLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditService, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangeName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddService1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ResetEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditSer, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ServiceOperation.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceOperation.setText("Service Operation");

        ServiceTableLAble.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceTableLAble.setText("Service Tabel");

        ServiceTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServiceTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(ServiceTable);
        if (ServiceTable.getColumnModel().getColumnCount() > 0) {
            ServiceTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            ServiceTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            ServiceTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            ServiceTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        javax.swing.GroupLayout mainservicepanLayout = new javax.swing.GroupLayout(mainservicepan);
        mainservicepan.setLayout(mainservicepanLayout);
        mainservicepanLayout.setHorizontalGroup(
            mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainservicepanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainservicepanLayout.createSequentialGroup()
                        .addGroup(mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(AddSpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DeleteSpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EditSpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainservicepanLayout.createSequentialGroup()
                        .addComponent(ServiceOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainservicepanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(ServiceTableLAble, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(mainservicepanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        mainservicepanLayout.setVerticalGroup(
            mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainservicepanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainservicepanLayout.createSequentialGroup()
                        .addComponent(ServiceTableLAble, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainservicepanLayout.createSequentialGroup()
                        .addComponent(ServiceOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(EditSpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteSpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainservicepan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(mainservicepan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ServicePanelLayout = new javax.swing.GroupLayout(ServicePanel);
        ServicePanel.setLayout(ServicePanelLayout);
        ServicePanelLayout.setHorizontalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ServicePanelLayout.setVerticalGroup(
            ServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ServiceScroll1.setViewportView(ServicePanel);

        javax.swing.GroupLayout ServiceDTab2Layout = new javax.swing.GroupLayout(ServiceDTab2);
        ServiceDTab2.setLayout(ServiceDTab2Layout);
        ServiceDTab2Layout.setHorizontalGroup(
            ServiceDTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServiceDTab2Layout.createSequentialGroup()
                .addComponent(ServiceScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        ServiceDTab2Layout.setVerticalGroup(
            ServiceDTab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ServiceScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setPreferredSize(new java.awt.Dimension(944, 586));

        ReportScroll.setPreferredSize(new java.awt.Dimension(944, 586));

        ReportPanel.setBackground(new java.awt.Color(38, 3, 3));
        ReportPanel.setPreferredSize(new java.awt.Dimension(1016, 762));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jRadioButton1.setText("Daily");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jRadioButton2.setText("Weekly");

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jRadioButton3.setText("Monthly");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("Select");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("Or");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel8.setText("From :");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel9.setText("To :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton2)
                        .addGap(121, 121, 121)
                        .addComponent(jRadioButton3)
                        .addGap(9, 9, 9)))
                .addGap(42, 42, 42))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTable2.setBackground(new java.awt.Color(204, 204, 204));
        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable2.setForeground(new java.awt.Color(204, 204, 204));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        reportlabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        reportlabel.setForeground(new java.awt.Color(255, 255, 255));
        reportlabel.setText("Report");

        javax.swing.GroupLayout ReportPanelLayout = new javax.swing.GroupLayout(ReportPanel);
        ReportPanel.setLayout(ReportPanelLayout);
        ReportPanelLayout.setHorizontalGroup(
            ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportPanelLayout.createSequentialGroup()
                .addGroup(ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportPanelLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReportPanelLayout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(reportlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(222, 222, 222))
        );
        ReportPanelLayout.setVerticalGroup(
            ReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(reportlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );

        ReportScroll.setViewportView(ReportPanel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReportScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ReportScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(30, 3, 3));
        setForeground(new java.awt.Color(30, 3, 3));

        homepanel.setBackground(new java.awt.Color(38, 3, 3));

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
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        javax.swing.GroupLayout homepanelLayout = new javax.swing.GroupLayout(homepanel);
        homepanel.setLayout(homepanelLayout);
        homepanelLayout.setHorizontalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(modelicon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Look1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        homepanelLayout.setVerticalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homepanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homepanelLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(Look1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4))
                    .addGroup(homepanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)))
                .addGap(1063, 1063, 1063))
        );

        buttonpanel.setBackground(new java.awt.Color(204, 204, 204));
        buttonpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));
        buttonpanel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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
        emp_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_btActionPerformed(evt);
            }
        });

        client_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        client_bt.setText("Registration");
        client_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        client_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                client_btActionPerformed(evt);
            }
        });

        product_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        product_bt.setText("Products");
        product_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        product_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_btActionPerformed(evt);
            }
        });

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

        billing_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        billing_bt.setText("Billing");
        billing_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        billing_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billing_btActionPerformed(evt);
            }
        });

        other_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        other_bt.setText("Other");
        other_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout buttonpanelLayout = new javax.swing.GroupLayout(buttonpanel);
        buttonpanel.setLayout(buttonpanelLayout);
        buttonpanelLayout.setHorizontalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(other_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deltail_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(emp_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(client_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supplier_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(report_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billing_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        buttonpanelLayout.setVerticalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deltail_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emp_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addComponent(billing_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(other_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        HomeTab.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("WELCOME TO LOOKHUB");

        javax.swing.GroupLayout HomeTabLayout = new javax.swing.GroupLayout(HomeTab);
        HomeTab.setLayout(HomeTabLayout);
        HomeTabLayout.setHorizontalGroup(
            HomeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeTabLayout.createSequentialGroup()
                .addGap(426, 426, 426)
                .addComponent(jLabel2)
                .addGap(255, 255, 255))
        );
        HomeTabLayout.setVerticalGroup(
            HomeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeTabLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );

        jTabbedPane1.addTab("tab1", HomeTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1))
                    .addComponent(homepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(homepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deltail_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deltail_btActionPerformed
      jTabbedPane1.add(SalonDTab);
      jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(SalonDTab), getTitlePanel(jTabbedPane1, SalonDTab, "Salon Details    "));
      jTabbedPane1.setSelectedComponent(SalonDTab);
    }//GEN-LAST:event_deltail_btActionPerformed

    private void deltail_btMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deltail_btMouseEntered
                   
    }//GEN-LAST:event_deltail_btMouseEntered

    private void service_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_service_btActionPerformed
           // TODO add your handling code here:
           jTabbedPane1.add(ServiceDTab2);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(ServiceDTab2), getTitlePanel(jTabbedPane1, ServiceDTab2, "Service    "));
        jTabbedPane1.setSelectedComponent(ServiceDTab2);
    }//GEN-LAST:event_service_btActionPerformed

    private void report_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(jPanel5);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(jPanel5), getTitlePanel(jTabbedPane1, jPanel5, "Report    "));
        jTabbedPane1.setSelectedComponent(jPanel5);
    }//GEN-LAST:event_report_btActionPerformed

    private void emp_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(EmployeeDTab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(EmployeeDTab), getTitlePanel(jTabbedPane1, EmployeeDTab, "Employee    "));
        jTabbedPane1.setSelectedComponent(EmployeeDTab);
    }//GEN-LAST:event_emp_btActionPerformed

    private void client_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_client_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(RegistrationDTab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(RegistrationDTab), getTitlePanel(jTabbedPane1, RegistrationDTab, "Registration    "));
        jTabbedPane1.setSelectedComponent(RegistrationDTab);
        
        
    }//GEN-LAST:event_client_btActionPerformed

    private void product_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(ProductDtab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(ProductDtab), getTitlePanel(jTabbedPane1, ProductDtab, "Product    "));
        jTabbedPane1.setSelectedComponent(ProductDtab);
    }//GEN-LAST:event_product_btActionPerformed

    private void NameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTextfieldActionPerformed

    private void NameTextfieldVishal(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextfieldVishal

    }//GEN-LAST:event_NameTextfieldVishal

    private void NameTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(NameTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                SurnameTextfield.requestFocus();
            }
        }

    }//GEN-LAST:event_NameTextfieldKeyPressed

    private void SurnameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SurnameTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SurnameTextfieldActionPerformed

    private void SurnameTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SurnameTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(SurnameTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Last Name","LastName",JOptionPane.OK_OPTION);
            }else{
                ContactTextfield.requestFocus();
            }
        }
    }//GEN-LAST:event_SurnameTextfieldKeyPressed

    private void ContactTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactTextfieldActionPerformed

    }//GEN-LAST:event_ContactTextfieldActionPerformed

    private void ContactTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactTextfieldKeyPressed
        // TODO add your handling code here:
        String regex ="[0-9]";
        String data;
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(ContactTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Contact","Contact",JOptionPane.OK_OPTION);
            }else{
                jTextArea.requestFocus();
            }
            if(ContactTextfield.getText().matches(regex))
            {
                jTextArea.requestFocus();            }
        }
    }//GEN-LAST:event_ContactTextfieldKeyPressed

    private void UserIdTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserIdTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserIdTextfieldActionPerformed

    private void UserIdTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UserIdTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(UserIdTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter UserId","UserId",JOptionPane.OK_OPTION);
            }else{
                PasswordTextfield.requestFocus();
            }
        }
    }//GEN-LAST:event_UserIdTextfieldKeyPressed

    private void PasswordTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordTextfieldActionPerformed

    private void PasswordTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(PasswordTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Password","Password",JOptionPane.OK_OPTION);
            }else{
                if(((PasswordTextfield.getText()).length())>=8){
                    ConfirmPasswordTextfield.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_PasswordTextfieldKeyPressed

    private void ConfirmPasswordTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmPasswordTextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmPasswordTextfieldActionPerformed

    private void ConfirmPasswordTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConfirmPasswordTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(PasswordTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Password not Confirmed","Confirm Password",JOptionPane.OK_OPTION);
            }else{
                
                MailTextfield.requestFocus();

            }
        }
    }//GEN-LAST:event_ConfirmPasswordTextfieldKeyPressed

    private void MailTextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MailTextfieldActionPerformed

        String email = MailTextfield.getText();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound)
        emaillabel.setText("");
        else
        emaillabel.setText("Invalid email ID");

    }//GEN-LAST:event_MailTextfieldActionPerformed

    private void MailTextfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MailTextfieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(MailTextfield.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter email address","Invalid Email",JOptionPane.OK_OPTION);
            }else{

                Submit.requestFocus();

            }
        }
    }//GEN-LAST:event_MailTextfieldKeyPressed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed

    }//GEN-LAST:event_SubmitActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            NameTextfield.setText("");
            SurnameTextfield.setText("");
            ContactTextfield.setText("");
            jTextArea.setText("");
            male.setSelected(false);
            female.setSelected(false);
            UserIdTextfield.setText("");
            PasswordTextfield.setText("");
            ConfirmPasswordTextfield.setText("");
            MailTextfield.setText("");
            
        }
    }//GEN-LAST:event_ResetActionPerformed

    private void maleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maleKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if((!(male.isSelected()))&&(!(female.isSelected()))){
                JOptionPane.showMessageDialog(this, "Choose Gender","Gender",JOptionPane.OK_OPTION);
            }else{
                UserIdTextfield.requestFocus();
            }
        }
    }//GEN-LAST:event_maleKeyPressed

    private void femaleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_femaleKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if((!(male.isSelected()))&&(!(female.isSelected()))){
                JOptionPane.showMessageDialog(this, "Choose Gender","Gender",JOptionPane.OK_OPTION);
            }else{
                UserIdTextfield.requestFocus();
            }
        }
    }//GEN-LAST:event_femaleKeyPressed

    private void jTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(jTextArea.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Address","Address",JOptionPane.OK_OPTION);
            }else{
                male.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextAreaKeyPressed

    private void emaillabelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emaillabelFocusGained

    }//GEN-LAST:event_emaillabelFocusGained

    private void RegistryPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistryPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RegistryPanelMouseClicked

    private void billing_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billing_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(BillingPanel);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(BillingPanel), getTitlePanel(jTabbedPane1, BillingPanel, "Billing    "));
        jTabbedPane1.setSelectedComponent(BillingPanel);
    }//GEN-LAST:event_billing_btActionPerformed

    private void addservice_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addservice_btnActionPerformed
      try {
            con=DbUtil.loadDriver();
            DbUtil.runQuery("insert into services values('"+AddServiceField.getText()+"','"+AddPriceField.getText()+"');");
            JOptionPane.showMessageDialog(this, "Service added Succesfully","information",JOptionPane.OK_OPTION);
            getServiceTabelData();
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_addservice_btnActionPerformed

    private void ServiceDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServiceDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ServiceDiscountActionPerformed

    private void AllTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AllTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AllTotalActionPerformed

    private void BarberNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarberNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BarberNameFieldActionPerformed

    private void TotalDiscount2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalDiscount2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalDiscount2ActionPerformed

    private void CustumerjTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CustumerjTextFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(CustumerjTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Custumer Name","Name",JOptionPane.OK_OPTION);
            }else{
                MobilejTextField.requestFocus();
            }
        }
    }//GEN-LAST:event_CustumerjTextFieldKeyPressed

    private void MobilejTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobilejTextFieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(MobilejTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                EmailCustjTextField1.requestFocus();
            }
        }
    }//GEN-LAST:event_MobilejTextFieldKeyPressed

    private void EmailCustjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailCustjTextField1ActionPerformed
        // TODO add your handling code here:
        String email = EmailCustjTextField1.getText();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";

        Pattern p = Pattern.compile(emailRegex);
        Matcher m = p.matcher(email);
        boolean matchFound = m.matches();
        if (matchFound)
        emailLabel.setText("");
        else{
        emailLabel.setText("Invalid email ID");
        }
    }//GEN-LAST:event_EmailCustjTextField1ActionPerformed

    private void EmailCustjTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailCustjTextField1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(EmailCustjTextField1.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                ServiceSearch.requestFocus();
            }
        }
    }//GEN-LAST:event_EmailCustjTextField1KeyPressed

    private void Deleteservice_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Deleteservice_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Deleteservice_btnActionPerformed

    private void editservice_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editservice_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editservice_btnActionPerformed

    private void ProductAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductAddActionPerformed
        try {
            con=DbUtil.loadDriver();
            DbUtil.runQuery("insert into product values('"+NameTF.getText()+"','"+PriceTF.getText()+"','"+codeTF.getText()+"','"+QuantityTF.getText()+"','"+SupplierTF.getText()+"','"+CostTf.getText()+"');");
            JOptionPane.showMessageDialog(this, "Product added Succesfully","information",JOptionPane.OK_OPTION);
            getProductTabelData();
            
        } catch (Exception e) {
        } 
    }//GEN-LAST:event_ProductAddActionPerformed

    private void editservice_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editservice_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editservice_btn1ActionPerformed

    private void addservice_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addservice_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addservice_btn2ActionPerformed

    private void editservice_btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editservice_btn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editservice_btn2ActionPerformed

    private void AddPriceField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPriceField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPriceField4ActionPerformed

    private void codeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTFActionPerformed

    private void SupplierTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierTFActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddPrice1;
    private javax.swing.JTextField AddPriceField;
    private javax.swing.JTextField AddPriceField4;
    private javax.swing.JTextField AddPriceField5;
    private javax.swing.JTextField AddPriceField6;
    private javax.swing.JTextField AddPriceField9;
    private javax.swing.JTextField AddService1;
    private javax.swing.JTextField AddServiceField;
    private javax.swing.JTextField AddServiceField10;
    private javax.swing.JTextField AddServiceField4;
    private javax.swing.JTextField AddServiceField5;
    private javax.swing.JTextField AddServiceField6;
    private javax.swing.JTextField AddServiceField7;
    private javax.swing.JTextField AddServiceField8;
    private javax.swing.JPanel AddSpan;
    private javax.swing.JPanel AddSpan1;
    private javax.swing.JPanel AddSpan2;
    private javax.swing.JTextField AllTotal;
    private javax.swing.JButton BServiceAdd;
    private javax.swing.JButton BServiceDel;
    private javax.swing.JLabel BarberName;
    private javax.swing.JTextField BarberNameField;
    private javax.swing.JLabel BillDate;
    private javax.swing.JLabel BillNo;
    private javax.swing.JPanel BillSheet;
    private javax.swing.JPanel BillingDTab;
    private javax.swing.JPanel BillingPanel;
    private javax.swing.JTable BillingTable;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel ChangeName;
    private javax.swing.JLabel ChangePrice;
    private javax.swing.JLabel ConfirmPassword;
    private javax.swing.JPasswordField ConfirmPasswordTextfield;
    private javax.swing.JLabel ContactNumebe;
    private javax.swing.JTextField ContactTextfield;
    private javax.swing.JLabel CostL;
    private javax.swing.JTextField CostTf;
    private javax.swing.JLabel CustumerN;
    private javax.swing.JTextField CustumerjTextField;
    private javax.swing.JTextField DeleteService;
    private javax.swing.JPanel DeleteSpan;
    private javax.swing.JButton Deleteservice_btn;
    private javax.swing.JPanel DetailPanel;
    private javax.swing.JLabel Discount;
    private javax.swing.JLabel Discount1;
    private javax.swing.JButton EditSer;
    private javax.swing.JButton EditSer1;
    private javax.swing.JTextField EditService;
    private javax.swing.JPanel EditSpan;
    private javax.swing.JPanel EditSpan1;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel EmailCust;
    private javax.swing.JTextField EmailCustjTextField1;
    private javax.swing.JPanel EmployeeDTab;
    private javax.swing.JPanel FinalBillPanal;
    private javax.swing.JLabel Gender;
    private javax.swing.JPanel HomeTab;
    private javax.swing.JLabel Hub2;
    private javax.swing.JLabel Look;
    private javax.swing.JLabel Look1;
    private javax.swing.JLabel Look4;
    private javax.swing.JTextField MailTextfield;
    private javax.swing.JLabel Mobile;
    private javax.swing.JTextField MobilejTextField;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel NameL;
    private javax.swing.JTextField NameTF;
    private javax.swing.JTextField NameTextfield;
    private javax.swing.JLabel Password;
    private javax.swing.JPasswordField PasswordTextfield;
    private javax.swing.JLabel PricceL;
    private javax.swing.JTextField PriceTF;
    private javax.swing.JButton Print;
    private javax.swing.JButton ProductAdd;
    private javax.swing.JPanel ProductDtab;
    private javax.swing.JScrollPane ProductScroll;
    private javax.swing.JTable ProductTable;
    private javax.swing.JLabel Qty1;
    private javax.swing.JTextField Quantity;
    private javax.swing.JLabel QuantityL;
    private javax.swing.JTextField QuantityTF;
    private javax.swing.JLabel Registration2;
    private javax.swing.JPanel RegistrationDTab;
    private javax.swing.JPanel RegistryPanel;
    private javax.swing.JScrollPane RegistryScrool;
    private javax.swing.JPanel ReportPanel;
    private javax.swing.JScrollPane ReportScroll;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetBill;
    private javax.swing.JButton ResetEdit;
    private javax.swing.JButton ResetEdit1;
    private javax.swing.JLabel S_Pricce;
    private javax.swing.JLabel S_Pricce4;
    private javax.swing.JLabel S_Pricce5;
    private javax.swing.JLabel S_Pricce6;
    private javax.swing.JLabel S_Pricce9;
    private javax.swing.JPanel SalonDTab;
    private javax.swing.JButton Save;
    private javax.swing.JButton SaveasPDF;
    private javax.swing.JLabel ServicT;
    private javax.swing.JLabel ServicT1;
    private javax.swing.JLabel ServicT10;
    private javax.swing.JLabel ServicT11;
    private javax.swing.JLabel ServicT6;
    private javax.swing.JLabel ServicT7;
    private javax.swing.JLabel ServicT8;
    private javax.swing.JLabel ServicT9;
    private javax.swing.JLabel Service;
    private javax.swing.JPanel ServiceCPanel;
    private javax.swing.JPanel ServiceDTab2;
    private javax.swing.JTextField ServiceDiscount;
    private javax.swing.JLabel ServiceOperation;
    private javax.swing.JLabel ServiceOperation1;
    private javax.swing.JPanel ServicePanel;
    private javax.swing.JPanel ServicePanel1;
    private javax.swing.JScrollPane ServiceScroll1;
    private javax.swing.JTextField ServiceSearch;
    private javax.swing.JLabel ServiceT2;
    private javax.swing.JTable ServiceTable;
    private javax.swing.JLabel ServiceTableLAble;
    private javax.swing.JLabel ServiceTableLAble1;
    private javax.swing.JTextField ServiceTotal;
    private javax.swing.JButton Submit;
    private javax.swing.JLabel SupplierL;
    private javax.swing.JTextField SupplierTF;
    private javax.swing.JLabel Surname;
    private javax.swing.JTextField SurnameTextfield;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Total1;
    private javax.swing.JTextField TotalDiscount2;
    private javax.swing.JTextField UnitPrice;
    private javax.swing.JLabel UserId;
    private javax.swing.JTextField UserIdTextfield;
    private javax.swing.JLabel addrees;
    private javax.swing.JButton addservice_btn;
    private javax.swing.JButton addservice_btn2;
    private javax.swing.JLabel adminpic;
    private javax.swing.JTextField billNOjTextField1;
    private javax.swing.JButton billing_bt;
    private javax.swing.ButtonGroup buttonGroupMaleFemale;
    private javax.swing.JPanel buttonpanel;
    private javax.swing.JButton client_bt;
    private javax.swing.JLabel codeL;
    private javax.swing.JTextField codeTF;
    private javax.swing.JLabel confirm_pswd;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JButton deltail_bt;
    private javax.swing.JButton editservice_btn;
    private javax.swing.JButton editservice_btn1;
    private javax.swing.JButton editservice_btn2;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JButton emp_bt;
    private javax.swing.JRadioButton female;
    private javax.swing.JPanel homepanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JButton logout;
    private javax.swing.JPanel mainservicepan;
    private javax.swing.JPanel mainservicepan1;
    private javax.swing.JRadioButton male;
    private javax.swing.JLabel modelicon;
    private javax.swing.JLabel modelicon3;
    private javax.swing.JButton other_bt;
    private javax.swing.JLabel passLabel;
    private javax.swing.JButton product_bt;
    private javax.swing.JButton report_bt;
    private javax.swing.JLabel reportlabel;
    private javax.swing.JButton service_bt;
    private javax.swing.JButton supplier_bt;
    private javax.swing.JLabel unit;
    // End of variables declaration//GEN-END:variables
}
