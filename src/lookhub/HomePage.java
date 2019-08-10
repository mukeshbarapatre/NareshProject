package lookhub;


import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.Pfm2afm;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.border.Border;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;
import javax.print.event.PrintServiceAttributeListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.icepdf.core.views.DocumentViewController;
import org.icepdf.ri.common.PrintHelper;
import org.icepdf.ri.common.PrintJobWatcher;
import org.icepdf.ri.common.views.DocumentViewControllerImpl;
import java.net.*;
import java.io.*;
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
    Connection con;
    Statement stmt;
    ResultSet rs;
    Double totalprice=0d;
    Double totaldiscout=0d;
    java.sql.Date sqlDate;
    String ADMINorUSER;
    String Imagepath;
    ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\success.png");
    public HomePage(ResultSet RS)  {
       
        
        
        initComponents();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
        int ysize = (int)tk.getScreenSize().getHeight();
        
        this.setExtendedState(MAXIMIZED_BOTH);
        
        
       if(ysize>769||xsize>1367){
       int x = (int)buttonpanel.getSize().getWidth();
       int y = (int)buttonpanel.getSize().getHeight()+132;
       int xz = (int)jTabbedPane1.getSize().getWidth();
       int yz = (int)jTabbedPane1.getSize().getHeight()+132;
           
        buttonpanel.setPreferredSize(new Dimension(x,y));
        jTabbedPane1.setPreferredSize(new Dimension(xz,yz));
        
       }
        try {
            usernameLabel.setText(RS.getString(1)+" "+RS.getString(2));
            ADMINorUSER = RS.getString(9);
            if (RS.getString(9).equals("ADMIN")) {
                ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\AdminLogin.png");
                adminpic.setIcon(iconimg);
            }
            if (RS.getString(9).equals("USER")) {
                ImageIcon iconimg = new ImageIcon("src\\lookhub\\Images\\UserLogin.png");
                adminpic.setIcon(iconimg);
            } 
        } catch (Exception e) {
        }
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(HomeTab), getTitlePanel(jTabbedPane1, HomeTab, "HOME  "));
        
        AppointScroll.getVerticalScrollBar().setUnitIncrement(20);
        ServiceScroll1.getVerticalScrollBar().setUnitIncrement(20);
        ProductScroll.getVerticalScrollBar().setUnitIncrement(20);
        EmpolyeeScroll.getVerticalScrollBar().setUnitIncrement(20);
        SolonDScroll.getVerticalScrollBar().setUnitIncrement(20);
        ReportScroll.getVerticalScrollBar().setUnitIncrement(20);
        otherscroll.getVerticalScrollBar().setUnitIncrement(20);
        MessageScroll.getVerticalScrollBar().setUnitIncrement(20);
        
        //here is method to fetch data
        getEmployeeData();
        getServiceTabelData();
        getProductTabelData();
        getSupplierTabelData();
        getBillNo();
        getAppointmentData();
        getCustumerData();
        
        //here is a Method for Autosuggetion panel
        getSuggestionPane(EditService,"services");
        getSuggestionPane(DeleteService,"services");
        getSuggestionPane(ServiceSearch, "services");
        getSuggestionPane(productnameTF, "product");
        getSuggestionPane(DeleteProductTF,"product");
        getSuggestionPane(EditCtf, "supplier");
        getSuggestionPane(Deltf,"supplier");
        getSuggestionPane(CustumerjTextField,"custumerdetails");
        getSuggestionPane(DeleteEmployee1,"userdetails");
        getSuggestionPane(search_Tf,"userdetails");
        getSuggestionPane(BarberNameField, "userdetails");
        getSuggestionPane(CustomerNameop, "custumerdetails");
        getSuggestionPane(Search, "custumerdetails");
      //  getSuggestionPane(productnameTF,"product");
      //  getSuggestionPane(productnameTF,"services");
        
         //here is code for combobox
           
        //report screen set blank
                        Column1.setText("");
                        row1.setText("");
                        Column2.setText("");
                        row2.setText("");
                        Column3.setText("");
                        row3.setText("");
                        Column4.setText("");
                        row4.setText("");
                        Column5.setText("");
                        row5.setText("");
                        bill.setVisible(false);
                        SearchTable.setVisible(false);
                        RegistryPanel.setVisible(false);
                        NandMpan.setVisible(false);
   
        
                        
                        
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
        ServiceDiscount.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                totalp();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                totalp();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                totalp();
            }
            public void totalp(){
                
                try {
                    double totals = Integer.parseInt(Quantity.getText())*Double.parseDouble(UnitPrice.getText())-Double.parseDouble(ServiceDiscount.getText());
                    ServiceTotal.setText(Double.toString(totals));
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "Enter a Valid Number Ex=1,2,3,etc","Invalid",JOptionPane.OK_OPTION);
                }
            
            }
        });
        Quantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                totald();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                totald();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                totald();
            }
            public void totald(){
                
                try {
                    double totals = Integer.parseInt(Quantity.getText())*Double.parseDouble(UnitPrice.getText())-Double.parseDouble(ServiceDiscount.getText());
                    ServiceTotal.setText(Double.toString(totals));
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, "Enter a Valid Number Ex=1,2,3,etc","Invalid",JOptionPane.OK_OPTION);
                }
            }
            
        });
        ServiceSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                fillTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fillTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fillTextField();
            }
            public void fillTextField(){
                
            if(ServiceSearch.getText().equals("")){
                UnitPrice.setText("");
                Quantity.setText("");
                ServiceDiscount.setText("");
                ServiceTotal.setText("");
            }else{
                try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from services where Servicename = ?", ServiceSearch);
            if(rs.next()){
                UnitPrice.setText(rs.getString(2));
                Quantity.setText("1");
                ServiceDiscount.setText("0");
                ServiceTotal.setText(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            
                    }
                }
            }
            
        });
        
        
        //here is code to initialize salon detail
        salondetail();
        
        
        //here is code for timer or notification
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime ldt =LocalDateTime.now();
                String hr = Integer.toString(ldt.getHour());
                String min = Integer.toString(ldt.getMinute());
                String sec = Integer.toString(ldt.getSecond());
                if(sec.length()==1){
                    sec = "0".concat(sec);
                }
               if(min.length()==1){
                    min = "0".concat(min);
                }
                if(hr.length()==1){
                    hr = "0".concat(hr);
                }
                
                try {
                    String timerString2 = hr+":"+min+":"+sec;
                    clock.setText(timerString2);
                    con = DbUtil.loadDriver();
                    rs= DbUtil.getResultSet("SELECT * FROM `appointment` WHERE `Time` >= Time(NOW())");
                    
                    while(rs.next()) {
                        String timerString1 = rs.getString(5);
                        LocalTime lt = LocalTime.parse(timerString1);
                        lt = lt.minusMinutes(60);
                        timerString1 = lt.toString();
                        if (timerString1.length() == 5) {
                            timerString1 = timerString1.concat(":00");
                        }
                        System.out.println(timerString1+" = "+timerString2);
                        if (timerString1.equals(timerString2)){
                            NotificationText.setText(" "+rs.getString(1)+"\n appointment time \n is gone please \n check notification");
                            NandMpan.setVisible(true);
                            getAppointmentData();
                            con.close();
                        }
                    }
                } catch (Exception evt) {
                    System.out.println(e);
                }
            }
        }).start();
        
        
        
    }
    public void salondetail(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from salondetail");
            if(rs.next()){
                Look.setText(rs.getString(2));
                description.setText(rs.getString(6));
                byte[] img = rs.getBytes(7);
                ImageIcon image = new ImageIcon(img);
                java.awt.Image im = image.getImage();
                java.awt.Image myImage = im.getScaledInstance(modelicon.getWidth(), modelicon.getHeight()+2,Image.ORIGINAL_PNG);
                ImageIcon newimg = new ImageIcon(myImage);
                modelicon.setIcon(newimg);
                
                
                
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
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
    
    
    //here is code for get employe data from tab
    public void getEmployeeData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select FirstName, `User Id`, Type from userdetails");
            Emp_table.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void getCustumerData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select CustumerName, MobileNo from custumerdetails");
            MsgTable.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /*here is code for fetchting Service Data into Service Table*/
    public void getServiceTabelData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from services");
            ServiceTable.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
        }
    }
    /*here is code for fetchting Product Data into Service Table*/
    public void getProductTabelData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select `ProductName`, `cost`, `ProductCode`, `SupplierName` from product");
            ProductTable.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
        }
    }
    
    //here is code for fetching supplier Data
    public void getSupplierTabelData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from supplier");
            SupplierTable.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
        }
    }
    public void getAppointmentData(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select `Customer Name`, `Mobile No`, `Date`, `Time` from appointment");
            AppointmentTable.setModel(DbUtils.resultSetToTableModel(rs));
            con.close();
        } catch (Exception e) {
        }
    }
    //here is gode for getBillNo
    public void getBillNo(){
        try {
            con = DbUtil.loadDriver();
            rs = DbUtil.getResultSet("select * from billing ");
            while (rs.next()) {                
                if(rs.last()){
                    billNOjTextField1.setText(Integer.toString(rs.getInt("Bill No")+1));
                }else{
                        billNOjTextField1.setText("1");
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void getSuggestionPane(JTextField textField,String tabelName){
    AutoSuggestor autoSuggestor = new AutoSuggestor(textField, this, null, Color.WHITE, Color.BLUE, Color.RED, 1f) {
            @Override
            boolean wordTyped(String typedWord) {

                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
                ArrayList<String> words = new ArrayList<>();
                try {
                con = DbUtil.loadDriver();
                rs = DbUtil.getResultSet("select * from "+tabelName);
                while (rs.next()) {                    
                    words.add(rs.getString(1));
                }
                con.close();
            } catch (Exception e) {
                System.out.println("not done error =  "+e);
            }
                

                setDictionary(words);
                //addToDictionary("bye");//adds a single word

                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProductDtab = new javax.swing.JPanel();
        ProductScroll = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        mainproductpan = new javax.swing.JPanel();
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
        AddReset = new javax.swing.JButton();
        EditSpan1 = new javax.swing.JPanel();
        Searchbtn = new javax.swing.JButton();
        EditProduct = new javax.swing.JButton();
        ResetProduct = new javax.swing.JButton();
        pname = new javax.swing.JLabel();
        productnameTF = new javax.swing.JTextField();
        EditnameL = new javax.swing.JLabel();
        EditnameTF = new javax.swing.JTextField();
        EpriceL = new javax.swing.JLabel();
        EpriceTF = new javax.swing.JTextField();
        EditcodeL = new javax.swing.JLabel();
        EditcodeTF = new javax.swing.JTextField();
        EQuantity = new javax.swing.JLabel();
        EquantityTF = new javax.swing.JTextField();
        SnameL = new javax.swing.JLabel();
        SnameTF = new javax.swing.JTextField();
        S_Pricce6 = new javax.swing.JLabel();
        EditCost = new javax.swing.JTextField();
        ServiceOperation1 = new javax.swing.JLabel();
        ServiceTableLAble1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        AddSpan2 = new javax.swing.JPanel();
        ServicT10 = new javax.swing.JLabel();
        DeleteProductTF = new javax.swing.JTextField();
        DeleteProduct = new javax.swing.JButton();
        AppointmentDTab = new javax.swing.JPanel();
        AppointScroll = new javax.swing.JScrollPane();
        AppointPanel = new javax.swing.JPanel();
        Opoinment = new javax.swing.JPanel();
        DetailPanel1 = new javax.swing.JPanel();
        CustumerN1 = new javax.swing.JLabel();
        CustomerNameop = new javax.swing.JTextField();
        Mobile1 = new javax.swing.JLabel();
        MobilejTextField1 = new javax.swing.JTextField();
        BillDate1 = new javax.swing.JLabel();
        dateChooserCombo4 = new datechooser.beans.DateChooserCombo();
        EmailCust1 = new javax.swing.JLabel();
        EmailCustjTextField2 = new javax.swing.JTextField();
        emailLabel1 = new javax.swing.JLabel();
        SearchCust1 = new javax.swing.JButton();
        BillDate2 = new javax.swing.JLabel();
        ADD = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();
        Date date = new Date();
        SpinnerDateModel sp = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sp);
        ReseT = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        AppointmentTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        MobileNo = new javax.swing.JLabel();
        ApDate = new javax.swing.JLabel();
        ApTime = new javax.swing.JLabel();
        c12 = new javax.swing.JLabel();
        c11 = new javax.swing.JLabel();
        c14 = new javax.swing.JLabel();
        c15 = new javax.swing.JLabel();
        BillingDTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BillingPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        BillSheet = new javax.swing.JPanel();
        billscroll = new javax.swing.JScrollPane();
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
        SearchCust = new javax.swing.JButton();
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
        AddPriceField = new javax.swing.JTextField();
        AddServiceField = new javax.swing.JTextField();
        DeleteSpan = new javax.swing.JPanel();
        ServiceT2 = new javax.swing.JLabel();
        Deleteservice_btn = new javax.swing.JButton();
        DeleteService = new javax.swing.JTextField();
        EditSpan = new javax.swing.JPanel();
        ServicT1 = new javax.swing.JLabel();
        editservice_srh_btn = new javax.swing.JButton();
        EditService = new javax.swing.JTextField();
        popUPservice = new javax.swing.JLabel();
        ChangeName = new javax.swing.JLabel();
        EditServiceNF = new javax.swing.JTextField();
        ChangePrice = new javax.swing.JLabel();
        EditPriceF = new javax.swing.JTextField();
        EditSer = new javax.swing.JButton();
        ResetEdit = new javax.swing.JButton();
        ServiceOperation = new javax.swing.JLabel();
        ServiceTableLAble = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ServiceTable = new javax.swing.JTable();
        buttonGroupMaleFemale = new javax.swing.ButtonGroup();
        EmployeeDTab = new javax.swing.JPanel();
        EmpolyeeScroll = new javax.swing.JScrollPane();
        employepan = new javax.swing.JPanel();
        mainEmployeepan1 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        AddSpan3 = new javax.swing.JPanel();
        emp_conctact = new javax.swing.JLabel();
        emp_first = new javax.swing.JLabel();
        editEmployee_btn1 = new javax.swing.JButton();
        emp_textF1 = new javax.swing.JTextField();
        emp_textF2 = new javax.swing.JTextField();
        emp_last = new javax.swing.JLabel();
        emp_textF3 = new javax.swing.JTextField();
        emp_Address = new javax.swing.JLabel();
        emp_gender = new javax.swing.JLabel();
        emp_pass = new javax.swing.JLabel();
        emp_email = new javax.swing.JLabel();
        emp_textF4 = new javax.swing.JTextField();
        user_Tf = new javax.swing.JTextField();
        Pass_Tf = new javax.swing.JTextField();
        female_rbt = new javax.swing.JRadioButton();
        male_rbt = new javax.swing.JRadioButton();
        reset_bt = new javax.swing.JButton();
        user = new javax.swing.JLabel();
        UserTypeCom1 = new javax.swing.JComboBox<>();
        Edit_User = new javax.swing.JLabel();
        email_Tf = new javax.swing.JTextField();
        DeleteSpan1 = new javax.swing.JPanel();
        emp_dellebal = new javax.swing.JLabel();
        DeleteEmployee_btn1 = new javax.swing.JButton();
        DeleteEmployee1 = new javax.swing.JTextField();
        Employee_l1 = new javax.swing.JLabel();
        Emp_Tablel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Emp_searchlable = new javax.swing.JLabel();
        search_Tf = new javax.swing.JTextField();
        Emp_searchbt = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        Emp_table = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        Registrationbtn = new javax.swing.JButton();
        Account = new javax.swing.JLabel();
        RegistryPanel = new javax.swing.JPanel();
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
        UserTypeCom = new javax.swing.JComboBox<>();
        UserType = new javax.swing.JLabel();
        Registration2 = new javax.swing.JLabel();
        BACK = new javax.swing.JButton();
        SalonDTab1 = new javax.swing.JPanel();
        SolonDScroll = new javax.swing.JScrollPane();
        SalonDTab = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        SalonNametf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        PhoneL = new javax.swing.JLabel();
        EmailTF = new javax.swing.JTextField();
        PhoneTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        Addresstf = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        DescriptionTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        IMAGE = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        ResetDefault = new javax.swing.JButton();
        ReportDTab = new javax.swing.JPanel();
        ReportScroll = new javax.swing.JScrollPane();
        Report = new javax.swing.JPanel();
        Reportpan = new javax.swing.JPanel();
        ReportSearchPanel = new javax.swing.JPanel();
        ReportCombo = new javax.swing.JComboBox<>();
        SearchReortLab = new javax.swing.JLabel();
        SearchReport = new javax.swing.JButton();
        PeriodLable = new javax.swing.JLabel();
        PeriodLable1 = new javax.swing.JLabel();
        datefrom = new datechooser.beans.DateChooserCombo();
        todate = new datechooser.beans.DateChooserCombo();
        Reportpanofpan = new javax.swing.JPanel();
        SearchNameReport = new javax.swing.JLabel();
        ReportNameTextFielad = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        ReportTable = new javax.swing.JTable();
        ReportScreen = new javax.swing.JPanel();
        Column2 = new javax.swing.JLabel();
        Column1 = new javax.swing.JLabel();
        Column3 = new javax.swing.JLabel();
        Column4 = new javax.swing.JLabel();
        Column5 = new javax.swing.JLabel();
        row2 = new javax.swing.JLabel();
        row3 = new javax.swing.JLabel();
        row4 = new javax.swing.JLabel();
        row1 = new javax.swing.JLabel();
        row5 = new javax.swing.JLabel();
        bill = new javax.swing.JLabel();
        SearchIteam = new javax.swing.JButton();
        printTable = new javax.swing.JButton();
        SearchTable = new javax.swing.JButton();
        SupplierDtab = new javax.swing.JPanel();
        SupplierScroll1 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        mainproductpan1 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        AddSpan4 = new javax.swing.JPanel();
        CName = new javax.swing.JLabel();
        CNameTF = new javax.swing.JTextField();
        AddressL = new javax.swing.JLabel();
        Contact = new javax.swing.JLabel();
        ContactTF = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        AddressTA = new javax.swing.JTextArea();
        SEmail = new javax.swing.JLabel();
        SEmailTF = new javax.swing.JTextField();
        Sadd = new javax.swing.JButton();
        Sreset = new javax.swing.JButton();
        EditSpan2 = new javax.swing.JPanel();
        searchCN = new javax.swing.JButton();
        EditC = new javax.swing.JLabel();
        EditCtf = new javax.swing.JTextField();
        EditSupplier = new javax.swing.JLabel();
        EditSupplirttf = new javax.swing.JTextField();
        EdditAdd = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        AddTextArea = new javax.swing.JTextArea();
        Contactsupp = new javax.swing.JLabel();
        SupplierCN = new javax.swing.JTextField();
        Suppliemaail = new javax.swing.JLabel();
        SuppEditM = new javax.swing.JTextField();
        EditSupp = new javax.swing.JButton();
        ResetSupp = new javax.swing.JButton();
        ServiceOperation2 = new javax.swing.JLabel();
        ServiceTableLAble2 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        SupplierTable = new javax.swing.JTable();
        AddSpan5 = new javax.swing.JPanel();
        nameDel = new javax.swing.JLabel();
        Deltf = new javax.swing.JTextField();
        DelSupp = new javax.swing.JButton();
        OtherDTab = new javax.swing.JPanel();
        otherscroll = new javax.swing.JScrollPane();
        otherpan = new javax.swing.JPanel();
        Developer = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        Developers = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        MessageDTab = new javax.swing.JPanel();
        MessageScroll = new javax.swing.JScrollPane();
        MessagePanel = new javax.swing.JPanel();
        mpl = new javax.swing.JPanel();
        mainmsgpan = new javax.swing.JPanel();
        to = new javax.swing.JLabel();
        TO = new javax.swing.JTextField();
        message = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        Msg = new javax.swing.JTextArea();
        reset = new javax.swing.JButton();
        Send1 = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        message1 = new javax.swing.JLabel();
        SearchKey = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        MsgTable = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        homepanel = new javax.swing.JPanel();
        Look = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        adminpic = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        modelicon = new javax.swing.JLabel();
        NandMpan = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        NotificationText = new javax.swing.JTextArea();
        Notification = new javax.swing.JButton();
        Message = new javax.swing.JButton();
        clock = new javax.swing.JLabel();
        buttonpanel = new javax.swing.JPanel();
        deltail_bt = new javax.swing.JButton();
        emp_bt = new javax.swing.JButton();
        Appoint_bt = new javax.swing.JButton();
        product_bt = new javax.swing.JButton();
        service_bt = new javax.swing.JButton();
        supplier_bt = new javax.swing.JButton();
        report_bt = new javax.swing.JButton();
        billing_bt = new javax.swing.JButton();
        credit_btn = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        HomeTab = new javax.swing.JPanel();
        Welcome = new javax.swing.JLabel();
        developed = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        ProductDtab.setBackground(new java.awt.Color(38, 3, 3));

        ProductScroll.setPreferredSize(new java.awt.Dimension(946, 515));

        jPanel9.setBackground(new java.awt.Color(38, 3, 3));

        mainproductpan.setBackground(new java.awt.Color(204, 204, 204));

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

        AddReset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        AddReset.setText("Reset");
        AddReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddResetActionPerformed(evt);
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
                    .addGroup(AddSpan1Layout.createSequentialGroup()
                        .addComponent(ProductAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddReset, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))
                    .addGroup(AddSpan1Layout.createSequentialGroup()
                        .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(codeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SupplierTF, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddSpan1Layout.createSequentialGroup()
                                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(QuantityL, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(CostL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(QuantityTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CostTf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(AddSpan1Layout.createSequentialGroup()
                                .addComponent(PricceL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(PriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGap(22, 22, 22)
                .addGroup(AddSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductAdd)
                    .addComponent(AddReset, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Searchbtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Searchbtn.setText("Search");
        Searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchbtnActionPerformed(evt);
            }
        });

        EditProduct.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        EditProduct.setText("Edit");
        EditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProductActionPerformed(evt);
            }
        });

        ResetProduct.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ResetProduct.setText("Reset");
        ResetProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetProductActionPerformed(evt);
            }
        });

        pname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pname.setText("Product Name :");

        productnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EditnameL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditnameL.setText("Edit Name       :");

        EditnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EpriceL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EpriceL.setText("Edit Price :");

        EpriceTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EpriceTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EpriceTFActionPerformed(evt);
            }
        });

        EditcodeL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditcodeL.setText("Edit Code        :");

        EditcodeTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EQuantity.setText("Quantity :");

        EquantityTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        SnameL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SnameL.setText("Supplier Name :");

        SnameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SnameTFActionPerformed(evt);
            }
        });

        S_Pricce6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S_Pricce6.setText("Cost       :");

        EditCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                                .addComponent(EditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136)
                                .addComponent(ResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(Searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(SnameL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(EditSpan1Layout.createSequentialGroup()
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(EditcodeL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EditcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EditSpan1Layout.createSequentialGroup()
                                .addComponent(EditnameL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EditnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(EpriceL)
                            .addComponent(S_Pricce6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EpriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EquantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditCost, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        EditSpan1Layout.setVerticalGroup(
            EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpan1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Searchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditnameL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EpriceL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EpriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditcodeL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditcodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EquantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SnameL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(S_Pricce6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditCost, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResetProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ProductTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(ProductTable);
        if (ProductTable.getColumnModel().getColumnCount() > 0) {
            ProductTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            ProductTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            ProductTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            ProductTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        ServicT10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT10.setText("Product Name :");

        DeleteProductTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        DeleteProduct.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        DeleteProduct.setText("Delete");
        DeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan2Layout = new javax.swing.GroupLayout(AddSpan2);
        AddSpan2.setLayout(AddSpan2Layout);
        AddSpan2Layout.setHorizontalGroup(
            AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ServicT10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(DeleteProductTF, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(DeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        AddSpan2Layout.setVerticalGroup(
            AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteProductTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainproductpanLayout = new javax.swing.GroupLayout(mainproductpan);
        mainproductpan.setLayout(mainproductpanLayout);
        mainproductpanLayout.setHorizontalGroup(
            mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainproductpanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(EditSpan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSpan1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSpan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ServiceOperation1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainproductpanLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainproductpanLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(ServiceTableLAble1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        mainproductpanLayout.setVerticalGroup(
            mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainproductpanLayout.createSequentialGroup()
                .addGroup(mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainproductpanLayout.createSequentialGroup()
                        .addComponent(ServiceOperation1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(EditSpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddSpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainproductpanLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(mainproductpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addGroup(mainproductpanLayout.createSequentialGroup()
                                .addComponent(ServiceTableLAble1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(mainproductpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(mainproductpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        ProductScroll.setViewportView(jPanel9);

        javax.swing.GroupLayout ProductDtabLayout = new javax.swing.GroupLayout(ProductDtab);
        ProductDtab.setLayout(ProductDtabLayout);
        ProductDtabLayout.setHorizontalGroup(
            ProductDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductDtabLayout.createSequentialGroup()
                .addComponent(ProductScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        ProductDtabLayout.setVerticalGroup(
            ProductDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductDtabLayout.createSequentialGroup()
                .addComponent(ProductScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        AppointmentDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        AppointPanel.setBackground(new java.awt.Color(38, 3, 3));
        AppointPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AppointPanel.setForeground(new java.awt.Color(221, 0, 0));
        AppointPanel.setAutoscrolls(true);
        AppointPanel.setName(""); // NOI18N
        AppointPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AppointPanelMouseClicked(evt);
            }
        });

        Opoinment.setBackground(new java.awt.Color(204, 204, 204));

        CustumerN1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustumerN1.setText("Customer Name ");

        CustomerNameop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustomerNameop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CustomerNameopKeyPressed(evt);
            }
        });

        Mobile1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Mobile1.setText("Cust Mobile No.");

        MobilejTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MobilejTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobilejTextField1ActionPerformed(evt);
            }
        });
        MobilejTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MobilejTextField1KeyPressed(evt);
            }
        });

        BillDate1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillDate1.setText("Appoinment Date");

        EmailCust1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailCust1.setText("Cust Email ");

        EmailCustjTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailCustjTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailCustjTextField2ActionPerformed(evt);
            }
        });
        EmailCustjTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailCustjTextField2KeyPressed(evt);
            }
        });

        emailLabel1.setForeground(new java.awt.Color(204, 0, 0));

        SearchCust1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/search2.jpg"))); // NOI18N
        SearchCust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCust1ActionPerformed(evt);
            }
        });

        BillDate2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillDate2.setText("Appoinment Time");

        ADD.setText("ADD ");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        DELETE.setText("DELETE");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        JSpinner.DateEditor  de = new JSpinner.DateEditor(jSpinner1,"HH:mm:ss");
        jSpinner1.setEditor(de);

        ReseT.setText("RESET");
        ReseT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReseTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DetailPanel1Layout = new javax.swing.GroupLayout(DetailPanel1);
        DetailPanel1.setLayout(DetailPanel1Layout);
        DetailPanel1Layout.setHorizontalGroup(
            DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanel1Layout.createSequentialGroup()
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DetailPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(BillDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Mobile1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CustumerN1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(EmailCust1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
                        .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(CustomerNameop, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(EmailCustjTextField2)
                            .addComponent(MobilejTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DetailPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DetailPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(SearchCust1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(DetailPanel1Layout.createSequentialGroup()
                        .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DetailPanel1Layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BillDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DELETE)
                                .addGap(32, 32, 32)
                                .addComponent(ReseT)))
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        DetailPanel1Layout.setVerticalGroup(
            DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchCust1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CustumerN1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CustomerNameop, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mobile1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MobilejTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EmailCustjTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EmailCust1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emailLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BillDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DetailPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ADD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReseT)
                    .addComponent(DELETE))
                .addContainerGap())
        );

        AppointmentTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AppointmentTable.setModel(new javax.swing.table.DefaultTableModel(
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
        AppointmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AppointmentTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(AppointmentTable);

        name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        name.setText("Customer Name :");

        MobileNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MobileNo.setText("Mobile No :");

        ApDate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ApDate.setText("Appointment Date :");

        ApTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ApTime.setText("Appointment Time :");

        c12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c12.setForeground(new java.awt.Color(51, 51, 255));

        c11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c11.setForeground(new java.awt.Color(51, 51, 255));

        c14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c14.setForeground(new java.awt.Color(51, 51, 255));

        c15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        c15.setForeground(new java.awt.Color(51, 51, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(ApDate, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c11, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(MobileNo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c12, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(ApTime, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MobileNo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ApDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ApTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OpoinmentLayout = new javax.swing.GroupLayout(Opoinment);
        Opoinment.setLayout(OpoinmentLayout);
        OpoinmentLayout.setHorizontalGroup(
            OpoinmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpoinmentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(OpoinmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DetailPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );
        OpoinmentLayout.setVerticalGroup(
            OpoinmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OpoinmentLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(OpoinmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(OpoinmentLayout.createSequentialGroup()
                        .addComponent(DetailPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AppointPanelLayout = new javax.swing.GroupLayout(AppointPanel);
        AppointPanel.setLayout(AppointPanelLayout);
        AppointPanelLayout.setHorizontalGroup(
            AppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 991, Short.MAX_VALUE)
            .addGroup(AppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AppointPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Opoinment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        AppointPanelLayout.setVerticalGroup(
            AppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 507, Short.MAX_VALUE)
            .addGroup(AppointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(AppointPanelLayout.createSequentialGroup()
                    .addGap(0, 24, Short.MAX_VALUE)
                    .addComponent(Opoinment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 24, Short.MAX_VALUE)))
        );

        AppointScroll.setViewportView(AppointPanel);

        javax.swing.GroupLayout AppointmentDTabLayout = new javax.swing.GroupLayout(AppointmentDTab);
        AppointmentDTab.setLayout(AppointmentDTabLayout);
        AppointmentDTabLayout.setHorizontalGroup(
            AppointmentDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AppointScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
        );
        AppointmentDTabLayout.setVerticalGroup(
            AppointmentDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AppointScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
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
            .addGroup(BillSheetLayout.createSequentialGroup()
                .addComponent(billscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BillSheetLayout.setVerticalGroup(
            BillSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(billscroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
        );

        CustumerN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustumerN.setText("Customer Name ");

        CustumerjTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CustumerjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CustumerjTextFieldKeyPressed(evt);
            }
        });

        Mobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Mobile.setText("Cust Mobile No.");

        MobilejTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MobilejTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobilejTextFieldActionPerformed(evt);
            }
        });
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

        SearchCust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/search2.jpg"))); // NOI18N
        SearchCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCustActionPerformed(evt);
            }
        });

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
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EmailCustjTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DetailPanelLayout.createSequentialGroup()
                                .addComponent(MobilejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DetailPanelLayout.createSequentialGroup()
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DetailPanelLayout.createSequentialGroup()
                                .addComponent(BillNo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(billNOjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(DetailPanelLayout.createSequentialGroup()
                        .addComponent(CustumerjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchCust, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(BillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        DetailPanelLayout.setVerticalGroup(
            DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DetailPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CustumerN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CustumerjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BillDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SearchCust, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        ServiceSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ServiceSearchKeyPressed(evt);
            }
        });

        Quantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QuantityKeyPressed(evt);
            }
        });

        UnitPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServiceTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ServiceDiscount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServiceDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServiceDiscountActionPerformed(evt);
            }
        });
        ServiceDiscount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ServiceDiscountKeyPressed(evt);
            }
        });

        BServiceAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BServiceAdd.setText("Add");
        BServiceAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BServiceAddActionPerformed(evt);
            }
        });

        BillingTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BillingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Services", "Qty", "Unit Price", "Discount", "Total Price"
            }
        ));
        BillingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BillingTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(BillingTable);

        BServiceDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BServiceDel.setText("Del");
        BServiceDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BServiceDelActionPerformed(evt);
            }
        });

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
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ResetBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ResetBill.setText("Reset");
        ResetBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetBillActionPerformed(evt);
            }
        });

        Save.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        Print.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        SaveasPDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SaveasPDF.setText("PDF");
        SaveasPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveasPDFActionPerformed(evt);
            }
        });

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

        AddPriceField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        AddServiceField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout AddSpanLayout = new javax.swing.GroupLayout(AddSpan);
        AddSpan.setLayout(AddSpanLayout);
        AddSpanLayout.setHorizontalGroup(
            AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(ServicT, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddServiceField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addContainerGap()
                .addGroup(AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddSpanLayout.createSequentialGroup()
                        .addComponent(AddServiceField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(AddSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ServicT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addservice_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(S_Pricce, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ServicT1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ServicT1.setText("Service Name");

        editservice_srh_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        editservice_srh_btn.setText("Search");
        editservice_srh_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editservice_srh_btnActionPerformed(evt);
            }
        });

        EditService.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditServiceActionPerformed(evt);
            }
        });
        EditService.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EditServiceKeyPressed(evt);
            }
        });

        popUPservice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        popUPservice.setForeground(new java.awt.Color(0, 102, 0));
        popUPservice.setText("Example = hair cut");

        ChangeName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChangeName.setText("Edit Name");

        EditServiceNF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ChangePrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChangePrice.setText("Edit Price");

        EditPriceF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EditSer.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        EditSer.setText("Edit");
        EditSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSerActionPerformed(evt);
            }
        });

        ResetEdit.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ResetEdit.setText("Reset");
        ResetEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetEditActionPerformed(evt);
            }
        });

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
                                    .addComponent(EditPriceF, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EditServiceNF, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(EditSpanLayout.createSequentialGroup()
                        .addComponent(ServicT1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addComponent(popUPservice, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
                            .addGroup(EditSpanLayout.createSequentialGroup()
                                .addComponent(EditService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editservice_srh_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))))))
        );
        EditSpanLayout.setVerticalGroup(
            EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpanLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ServicT1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editservice_srh_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditService, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(popUPservice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangeName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditServiceNF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditPriceF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ServiceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ServiceTableMouseClicked(evt);
            }
        });
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainservicepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(mainservicepanLayout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addComponent(ServiceTableLAble, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 50, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addContainerGap(75, Short.MAX_VALUE))
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
            .addComponent(ServiceScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        EmployeeDTab.setBackground(new java.awt.Color(204, 204, 204));

        employepan.setBackground(new java.awt.Color(38, 3, 3));

        mainEmployeepan1.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        emp_conctact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_conctact.setText("Contact Number");

        emp_first.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_first.setText("First Name");

        editEmployee_btn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        editEmployee_btn1.setText("Edit");
        editEmployee_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmployee_btn1ActionPerformed(evt);
            }
        });
        editEmployee_btn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editEmployee_btn1KeyPressed(evt);
            }
        });

        emp_textF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_textF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emp_textF1KeyPressed(evt);
            }
        });

        emp_textF2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_textF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_textF2ActionPerformed(evt);
            }
        });
        emp_textF2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emp_textF2KeyPressed(evt);
            }
        });

        emp_last.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_last.setText("Last Name");

        emp_textF3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_textF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_textF3ActionPerformed(evt);
            }
        });
        emp_textF3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emp_textF3KeyPressed(evt);
            }
        });

        emp_Address.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_Address.setText("Address");

        emp_gender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_gender.setText("Gender");

        emp_pass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_pass.setText("Password");

        emp_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_email.setText("Email Address");

        emp_textF4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_textF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emp_textF4ActionPerformed(evt);
            }
        });
        emp_textF4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emp_textF4KeyPressed(evt);
            }
        });

        user_Tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user_Tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_TfActionPerformed(evt);
            }
        });
        user_Tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_TfKeyPressed(evt);
            }
        });

        Pass_Tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Pass_Tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pass_TfActionPerformed(evt);
            }
        });
        Pass_Tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pass_TfKeyPressed(evt);
            }
        });

        buttonGroup1.add(female_rbt);
        female_rbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        female_rbt.setText("Female");
        female_rbt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                female_rbtKeyPressed(evt);
            }
        });

        buttonGroup1.add(male_rbt);
        male_rbt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        male_rbt.setText("Male");
        male_rbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                male_rbtActionPerformed(evt);
            }
        });

        reset_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reset_bt.setText("Reset");
        reset_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_btActionPerformed(evt);
            }
        });

        user.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        user.setText("User Type");

        UserTypeCom1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "USER" }));

        Edit_User.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Edit_User.setText("User ID");

        email_Tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        email_Tf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_TfActionPerformed(evt);
            }
        });
        email_Tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                email_TfKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan3Layout = new javax.swing.GroupLayout(AddSpan3);
        AddSpan3.setLayout(AddSpan3Layout);
        AddSpan3Layout.setHorizontalGroup(
            AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpan3Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(editEmployee_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reset_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(AddSpan3Layout.createSequentialGroup()
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emp_gender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emp_Address, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addGap(64, 64, 64)
                        .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(emp_textF3)
                                .addComponent(emp_textF2)
                                .addComponent(emp_textF1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                .addGroup(AddSpan3Layout.createSequentialGroup()
                                    .addComponent(female_rbt)
                                    .addGap(44, 44, 44)
                                    .addComponent(male_rbt, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(emp_textF4, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddSpan3Layout.createSequentialGroup()
                        .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(AddSpan3Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(emp_first, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emp_last, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emp_conctact))
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpan3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(emp_email)
                                    .addComponent(emp_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Edit_User, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)))
                        .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Pass_Tf)
                            .addComponent(UserTypeCom1, 0, 250, Short.MAX_VALUE)
                            .addComponent(user_Tf)
                            .addComponent(email_Tf))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddSpan3Layout.setVerticalGroup(
            AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emp_textF1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_first, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_textF2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_last, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_conctact, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_textF3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_textF4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(emp_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(female_rbt))
                    .addComponent(male_rbt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(user_Tf, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(Edit_User, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pass_Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserTypeCom1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddSpan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editEmployee_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        emp_dellebal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emp_dellebal.setText("Employee Name");

        DeleteEmployee_btn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        DeleteEmployee_btn1.setText("Delete ");
        DeleteEmployee_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployee_btn1ActionPerformed(evt);
            }
        });

        DeleteEmployee1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DeleteEmployee1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployee1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DeleteSpan1Layout = new javax.swing.GroupLayout(DeleteSpan1);
        DeleteSpan1.setLayout(DeleteSpan1Layout);
        DeleteSpan1Layout.setHorizontalGroup(
            DeleteSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DeleteSpan1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(emp_dellebal)
                .addGap(30, 30, 30)
                .addComponent(DeleteEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteEmployee_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        DeleteSpan1Layout.setVerticalGroup(
            DeleteSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeleteSpan1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(DeleteSpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emp_dellebal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteEmployee_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Employee_l1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Employee_l1.setText("Employee");

        Emp_Tablel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Emp_Tablel2.setText("Employee Details");

        Emp_searchlable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Emp_searchlable.setText("Employee Name");

        search_Tf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        Emp_searchbt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Emp_searchbt.setText("Search");
        Emp_searchbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Emp_searchbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Emp_searchlable)
                .addGap(31, 31, 31)
                .addComponent(search_Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Emp_searchbt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_Tf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emp_searchlable, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emp_searchbt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Emp_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Emp_table.setModel(new javax.swing.table.DefaultTableModel(
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
        Emp_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Emp_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Emp_table);

        Registrationbtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Registrationbtn.setText("Registration");
        Registrationbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrationbtnActionPerformed(evt);
            }
        });

        Account.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Account.setText("Create a new Account");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(Registrationbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(Account, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(Account, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Registrationbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout mainEmployeepan1Layout = new javax.swing.GroupLayout(mainEmployeepan1);
        mainEmployeepan1.setLayout(mainEmployeepan1Layout);
        mainEmployeepan1Layout.setHorizontalGroup(
            mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DeleteSpan1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(AddSpan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Employee_l1))
                .addGap(5, 5, 5)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                        .addComponent(Emp_Tablel2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainEmployeepan1Layout.setVerticalGroup(
            mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                .addGroup(mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator6)
                    .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainEmployeepan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                                .addComponent(Emp_Tablel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainEmployeepan1Layout.createSequentialGroup()
                                .addComponent(Employee_l1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddSpan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DeleteSpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RegistryPanel.setBackground(new java.awt.Color(204, 204, 204));
        RegistryPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

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

        NameTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NameTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        SurnameTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SurnameTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        ContactTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ContactTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        UserIdTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UserIdTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        PasswordTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PasswordTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        ConfirmPasswordTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ConfirmPasswordTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        MailTextfield.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MailTextfield.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        jTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea.setRows(5);
        jTextArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        UserTypeCom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USER", "ADMIN" }));

        UserType.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        UserType.setText("User Type");

        Registration2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        Registration2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Registration2.setText("Registration Form");
        Registration2.setName(""); // NOI18N

        BACK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        BACK.setText("Back");
        BACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RegistryPanelLayout = new javax.swing.GroupLayout(RegistryPanel);
        RegistryPanel.setLayout(RegistryPanelLayout);
        RegistryPanelLayout.setHorizontalGroup(
            RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistryPanelLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addrees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Gender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ContactNumebe, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(Surname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(UserId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(RegistryPanelLayout.createSequentialGroup()
                                .addComponent(male)
                                .addGap(18, 18, 18)
                                .addComponent(female))
                            .addComponent(UserIdTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NameTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SurnameTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ContactTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(PasswordTextfield)
                            .addComponent(passLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, RegistryPanelLayout.createSequentialGroup()
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RegistryPanelLayout.createSequentialGroup()
                                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UserType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistryPanelLayout.createSequentialGroup()
                                .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)))
                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RegistryPanelLayout.createSequentialGroup()
                                .addGap(0, 11, Short.MAX_VALUE)
                                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(MailTextfield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                            .addComponent(ConfirmPasswordTextfield, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(confirm_pswd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(UserTypeCom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(RegistryPanelLayout.createSequentialGroup()
                                .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(BACK, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 15, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Registration2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        RegistryPanelLayout.setVerticalGroup(
            RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegistryPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Registration2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SurnameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ContactNumebe)
                    .addComponent(ContactTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RegistryPanelLayout.createSequentialGroup()
                        .addComponent(addrees, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RegistryPanelLayout.createSequentialGroup()
                        .addComponent(jTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(male, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(female, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserIdTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfirmPasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirm_pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MailTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserTypeCom, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RegistryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BACK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout employepanLayout = new javax.swing.GroupLayout(employepan);
        employepan.setLayout(employepanLayout);
        employepanLayout.setHorizontalGroup(
            employepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employepanLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(mainEmployeepan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(employepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(employepanLayout.createSequentialGroup()
                    .addGap(273, 273, 273)
                    .addComponent(RegistryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(274, Short.MAX_VALUE)))
        );
        employepanLayout.setVerticalGroup(
            employepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employepanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainEmployeepan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(employepanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(employepanLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(RegistryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(60, Short.MAX_VALUE)))
        );

        EmpolyeeScroll.setViewportView(employepan);

        javax.swing.GroupLayout EmployeeDTabLayout = new javax.swing.GroupLayout(EmployeeDTab);
        EmployeeDTab.setLayout(EmployeeDTabLayout);
        EmployeeDTabLayout.setHorizontalGroup(
            EmployeeDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmpolyeeScroll)
        );
        EmployeeDTabLayout.setVerticalGroup(
            EmployeeDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EmpolyeeScroll, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        SalonDTab1.setPreferredSize(new java.awt.Dimension(956, 624));

        SolonDScroll.setPreferredSize(new java.awt.Dimension(956, 624));

        SalonDTab.setBackground(new java.awt.Color(38, 3, 3));
        SalonDTab.setPreferredSize(new java.awt.Dimension(944, 586));

        jPanel10.setBackground(new java.awt.Color(204, 204, 204));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Salon Name :");

        SalonNametf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Address      :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Email          :");

        PhoneL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        PhoneL.setText("Phone No.   :");

        EmailTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EmailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTFActionPerformed(evt);
            }
        });

        PhoneTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PhoneTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneTFActionPerformed(evt);
            }
        });
        PhoneTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PhoneTFKeyPressed(evt);
            }
        });

        Addresstf.setColumns(20);
        Addresstf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Addresstf.setRows(5);
        jScrollPane2.setViewportView(Addresstf);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Description  :");

        DescriptionTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Salon Logo :");

        IMAGE.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        IMAGE.setPreferredSize(new java.awt.Dimension(81, 81));
        IMAGE.setRequestFocusEnabled(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        ResetDefault.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ResetDefault.setText("Resest Default");
        ResetDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetDefaultActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(SalonNametf)
                    .addComponent(EmailTF)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(IMAGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 152, Short.MAX_VALUE))
                    .addComponent(DescriptionTF))
                .addGap(50, 50, 50))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(ResetDefault, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SalonNametf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PhoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PhoneL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IMAGE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(ResetDefault)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SalonDTabLayout = new javax.swing.GroupLayout(SalonDTab);
        SalonDTab.setLayout(SalonDTabLayout);
        SalonDTabLayout.setHorizontalGroup(
            SalonDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalonDTabLayout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );
        SalonDTabLayout.setVerticalGroup(
            SalonDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalonDTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        SolonDScroll.setViewportView(SalonDTab);

        javax.swing.GroupLayout SalonDTab1Layout = new javax.swing.GroupLayout(SalonDTab1);
        SalonDTab1.setLayout(SalonDTab1Layout);
        SalonDTab1Layout.setHorizontalGroup(
            SalonDTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SolonDScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SalonDTab1Layout.setVerticalGroup(
            SalonDTab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SolonDScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ReportDTab.setPreferredSize(new java.awt.Dimension(956, 624));

        Report.setBackground(new java.awt.Color(38, 3, 3));

        Reportpan.setBackground(new java.awt.Color(204, 204, 204));

        ReportCombo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ReportCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barber", "Billing", "Product", "Suppliers", "Customers" }));

        SearchReortLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchReortLab.setText("Search Report");

        SearchReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchReport.setText("Search");
        SearchReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchReportActionPerformed(evt);
            }
        });

        PeriodLable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PeriodLable.setText("period");

        PeriodLable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PeriodLable1.setText("to");

        javax.swing.GroupLayout ReportSearchPanelLayout = new javax.swing.GroupLayout(ReportSearchPanel);
        ReportSearchPanel.setLayout(ReportSearchPanelLayout);
        ReportSearchPanelLayout.setHorizontalGroup(
            ReportSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportSearchPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(SearchReortLab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(PeriodLable)
                .addGap(24, 24, 24)
                .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PeriodLable1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(todate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(SearchReport, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        ReportSearchPanelLayout.setVerticalGroup(
            ReportSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportSearchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReportSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ReportSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchReortLab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ReportCombo)
                        .addComponent(PeriodLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PeriodLable1))
                    .addGroup(ReportSearchPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ReportSearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(todate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ReportSearchPanelLayout.createSequentialGroup()
                                .addComponent(SearchReport)
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );

        SearchNameReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchNameReport.setText("Name ");

        ReportNameTextFielad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ReportTable.setModel(new javax.swing.table.DefaultTableModel(
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
        ReportTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(ReportTable);

        ReportScreen.setBackground(new java.awt.Color(51, 51, 51));

        Column2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Column2.setForeground(new java.awt.Color(255, 255, 255));
        Column2.setText("c2");

        Column1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Column1.setForeground(new java.awt.Color(255, 255, 255));
        Column1.setText("c1");

        Column3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Column3.setForeground(new java.awt.Color(255, 255, 255));
        Column3.setText("c3");

        Column4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Column4.setForeground(new java.awt.Color(255, 255, 255));
        Column4.setText("c4");

        Column5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Column5.setForeground(new java.awt.Color(255, 255, 255));
        Column5.setText("c5");

        row2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        row2.setForeground(new java.awt.Color(255, 255, 255));
        row2.setText("r2");

        row3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        row3.setForeground(new java.awt.Color(255, 255, 255));
        row3.setText("r3");

        row4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        row4.setForeground(new java.awt.Color(255, 255, 255));
        row4.setText("r4");

        row1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        row1.setForeground(new java.awt.Color(255, 255, 255));
        row1.setText("r1");

        row5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        row5.setForeground(new java.awt.Color(255, 255, 255));
        row5.setText("r5");

        bill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bill.setForeground(new java.awt.Color(51, 255, 255));
        bill.setText("jLabel1");
        bill.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ReportScreenLayout = new javax.swing.GroupLayout(ReportScreen);
        ReportScreen.setLayout(ReportScreenLayout);
        ReportScreenLayout.setHorizontalGroup(
            ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Column1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(row1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Column2, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(row2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Column3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(row3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(row4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Column4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bill)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(row5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Column5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        ReportScreenLayout.setVerticalGroup(
            ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Column1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Column3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Column4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Column2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Column5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(row1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(row2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ReportScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(row4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(row3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(row5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportScreenLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bill, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        SearchIteam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchIteam.setText("Search");
        SearchIteam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchIteamActionPerformed(evt);
            }
        });

        printTable.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        printTable.setText("PRINT");
        printTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableActionPerformed(evt);
            }
        });

        SearchTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchTable.setText("Search Table");
        SearchTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReportpanofpanLayout = new javax.swing.GroupLayout(Reportpanofpan);
        Reportpanofpan.setLayout(ReportpanofpanLayout);
        ReportpanofpanLayout.setHorizontalGroup(
            ReportpanofpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportpanofpanLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ReportpanofpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportpanofpanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(SearchNameReport, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ReportNameTextFielad, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SearchIteam, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SearchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ReportScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportpanofpanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(printTable)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReportpanofpanLayout.setVerticalGroup(
            ReportpanofpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportpanofpanLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(ReportpanofpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchNameReport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReportNameTextFielad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchIteam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ReportScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(printTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout ReportpanLayout = new javax.swing.GroupLayout(Reportpan);
        Reportpan.setLayout(ReportpanLayout);
        ReportpanLayout.setHorizontalGroup(
            ReportpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportpanLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(ReportpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ReportSearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Reportpanofpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        ReportpanLayout.setVerticalGroup(
            ReportpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReportSearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Reportpanofpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout ReportLayout = new javax.swing.GroupLayout(Report);
        Report.setLayout(ReportLayout);
        ReportLayout.setHorizontalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(Reportpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        ReportLayout.setVerticalGroup(
            ReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(Reportpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ReportScroll.setViewportView(Report);

        javax.swing.GroupLayout ReportDTabLayout = new javax.swing.GroupLayout(ReportDTab);
        ReportDTab.setLayout(ReportDTabLayout);
        ReportDTabLayout.setHorizontalGroup(
            ReportDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReportScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        ReportDTabLayout.setVerticalGroup(
            ReportDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReportScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
        );

        SupplierDtab.setBackground(new java.awt.Color(38, 3, 3));

        SupplierScroll1.setPreferredSize(new java.awt.Dimension(946, 515));

        jPanel15.setBackground(new java.awt.Color(38, 3, 3));

        mainproductpan1.setBackground(new java.awt.Color(204, 204, 204));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        CName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CName.setText("Company Name :");

        CNameTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        AddressL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        AddressL.setText("Address              :");

        Contact.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Contact.setText("Contact No.       :");

        ContactTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ContactTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactTFActionPerformed(evt);
            }
        });

        AddressTA.setColumns(20);
        AddressTA.setRows(5);
        jScrollPane10.setViewportView(AddressTA);

        SEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SEmail.setText("Email                  :");

        SEmailTF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SEmailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEmailTFActionPerformed(evt);
            }
        });

        Sadd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Sadd.setText("Add");
        Sadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaddActionPerformed(evt);
            }
        });

        Sreset.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Sreset.setText("Reset");
        Sreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan4Layout = new javax.swing.GroupLayout(AddSpan4);
        AddSpan4.setLayout(AddSpan4Layout);
        AddSpan4Layout.setHorizontalGroup(
            AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddressL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpan4Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Contact)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ContactTF))
                    .addComponent(CNameTF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddSpan4Layout.createSequentialGroup()
                        .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddSpan4Layout.createSequentialGroup()
                                .addComponent(Sadd, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(Sreset, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        AddSpan4Layout.setVerticalGroup(
            AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddressL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Contact, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ContactTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddSpan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sadd)
                    .addComponent(Sreset))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        searchCN.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        searchCN.setText("Search");
        searchCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCNActionPerformed(evt);
            }
        });

        EditC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditC.setText("Company Name :");

        EditCtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EditSupplier.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EditSupplier.setText("Edit Name       :");

        EditSupplirttf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        EdditAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EdditAdd.setText("Address          :");

        AddTextArea.setColumns(20);
        AddTextArea.setRows(5);
        jScrollPane11.setViewportView(AddTextArea);

        Contactsupp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Contactsupp.setText("Contact No.       :");

        SupplierCN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SupplierCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupplierCNActionPerformed(evt);
            }
        });

        Suppliemaail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Suppliemaail.setText("Email              :");

        SuppEditM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SuppEditM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppEditMActionPerformed(evt);
            }
        });

        EditSupp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        EditSupp.setText("Edit");
        EditSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSuppActionPerformed(evt);
            }
        });

        ResetSupp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        ResetSupp.setText("Reset");
        ResetSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetSuppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditSpan2Layout = new javax.swing.GroupLayout(EditSpan2);
        EditSpan2.setLayout(EditSpan2Layout);
        EditSpan2Layout.setHorizontalGroup(
            EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpan2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditSpan2Layout.createSequentialGroup()
                        .addComponent(EditC)
                        .addGap(13, 13, 13)
                        .addComponent(EditCtf, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchCN, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditSpan2Layout.createSequentialGroup()
                        .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EditSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EdditAdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditSupplirttf, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EditSpan2Layout.createSequentialGroup()
                                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ResetSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(EditSpan2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Contactsupp)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SupplierCN, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))))
                .addGap(14, 14, 14))
            .addGroup(EditSpan2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Suppliemaail, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditSpan2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(EditSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SuppEditM, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EditSpan2Layout.setVerticalGroup(
            EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditSpan2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchCN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditCtf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditSupplirttf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EdditAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Contactsupp, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SupplierCN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Suppliemaail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SuppEditM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditSpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditSupp)
                    .addComponent(ResetSupp))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ServiceOperation2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceOperation2.setText("Supplier Operation");

        ServiceTableLAble2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ServiceTableLAble2.setText("Suppplier Tabel");

        jScrollPane9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane9MouseClicked(evt);
            }
        });

        SupplierTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SupplierTable.setModel(new javax.swing.table.DefaultTableModel(
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
        SupplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SupplierTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(SupplierTable);
        if (SupplierTable.getColumnModel().getColumnCount() > 0) {
            SupplierTable.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            SupplierTable.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            SupplierTable.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            SupplierTable.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        nameDel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameDel.setText("Company Name :");

        Deltf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        DelSupp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        DelSupp.setText("Delete");
        DelSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelSuppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddSpan5Layout = new javax.swing.GroupLayout(AddSpan5);
        AddSpan5.setLayout(AddSpan5Layout);
        AddSpan5Layout.setHorizontalGroup(
            AddSpan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Deltf, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(DelSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        AddSpan5Layout.setVerticalGroup(
            AddSpan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSpan5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AddSpan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameDel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Deltf, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DelSupp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout mainproductpan1Layout = new javax.swing.GroupLayout(mainproductpan1);
        mainproductpan1.setLayout(mainproductpan1Layout);
        mainproductpan1Layout.setHorizontalGroup(
            mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainproductpan1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(EditSpan2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSpan4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddSpan5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ServiceOperation2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainproductpan1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainproductpan1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(ServiceTableLAble2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        mainproductpan1Layout.setVerticalGroup(
            mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainproductpan1Layout.createSequentialGroup()
                .addGroup(mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainproductpan1Layout.createSequentialGroup()
                        .addComponent(ServiceOperation2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSpan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(EditSpan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddSpan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainproductpan1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(mainproductpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainproductpan1Layout.createSequentialGroup()
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(mainproductpan1Layout.createSequentialGroup()
                                .addComponent(ServiceTableLAble2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(mainproductpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(mainproductpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        SupplierScroll1.setViewportView(jPanel15);

        javax.swing.GroupLayout SupplierDtabLayout = new javax.swing.GroupLayout(SupplierDtab);
        SupplierDtab.setLayout(SupplierDtabLayout);
        SupplierDtabLayout.setHorizontalGroup(
            SupplierDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SupplierDtabLayout.createSequentialGroup()
                .addComponent(SupplierScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        SupplierDtabLayout.setVerticalGroup(
            SupplierDtabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SupplierDtabLayout.createSequentialGroup()
                .addComponent(SupplierScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        OtherDTab.setPreferredSize(new java.awt.Dimension(1046, 624));

        otherpan.setBackground(new java.awt.Color(38, 3, 3));

        Developer.setBackground(new java.awt.Color(204, 204, 204));

        Developers.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Developers.setText("Developers");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("vaibhavupadhyay0077@gmail.com");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Vaibhav Upadhyay");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Komal Hedau");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("komalshedau786@gmail.com");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("ujwalasonkusare22@gmail.com");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Ujwala Sonkusare");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Mukesh Barapatre");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("mukeshbarapatre@gmail.com");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Vishal Sarve");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("sarvevishal7@gmail.com");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(181, 181, 181))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(Developers)
                        .addGap(397, 397, 397))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel7))
                        .addGap(199, 199, 199))))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator8)
                    .addComponent(jSeparator10))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(222, 222, 222))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Developers, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout DeveloperLayout = new javax.swing.GroupLayout(Developer);
        Developer.setLayout(DeveloperLayout);
        DeveloperLayout.setHorizontalGroup(
            DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeveloperLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        DeveloperLayout.setVerticalGroup(
            DeveloperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DeveloperLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout otherpanLayout = new javax.swing.GroupLayout(otherpan);
        otherpan.setLayout(otherpanLayout);
        otherpanLayout.setHorizontalGroup(
            otherpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherpanLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(Developer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        otherpanLayout.setVerticalGroup(
            otherpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherpanLayout.createSequentialGroup()
                .addGap(0, 71, Short.MAX_VALUE)
                .addComponent(Developer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 71, Short.MAX_VALUE))
        );

        otherscroll.setViewportView(otherpan);

        javax.swing.GroupLayout OtherDTabLayout = new javax.swing.GroupLayout(OtherDTab);
        OtherDTab.setLayout(OtherDTabLayout);
        OtherDTabLayout.setHorizontalGroup(
            OtherDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(otherscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );
        OtherDTabLayout.setVerticalGroup(
            OtherDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(otherscroll, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
        );

        MessageDTab.setPreferredSize(new java.awt.Dimension(944, 517));

        MessageScroll.setPreferredSize(new java.awt.Dimension(946, 515));

        MessagePanel.setPreferredSize(new java.awt.Dimension(944, 515));

        mpl.setBackground(new java.awt.Color(38, 3, 3));

        mainmsgpan.setBackground(new java.awt.Color(204, 204, 204));

        to.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        to.setText("To");

        TO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        message.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        message.setText("Message");

        Msg.setColumns(20);
        Msg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Msg.setRows(5);
        jScrollPane14.setViewportView(Msg);

        reset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        Send1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Send1.setText("SEND");
        Send1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Send1ActionPerformed(evt);
            }
        });

        Search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        message1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        message1.setText("Name");

        SearchKey.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SearchKey.setText("Search");
        SearchKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchKeyActionPerformed(evt);
            }
        });

        MsgTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        MsgTable.setModel(new javax.swing.table.DefaultTableModel(
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
        MsgTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MsgTableMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(MsgTable);

        javax.swing.GroupLayout mainmsgpanLayout = new javax.swing.GroupLayout(mainmsgpan);
        mainmsgpan.setLayout(mainmsgpanLayout);
        mainmsgpanLayout.setHorizontalGroup(
            mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainmsgpanLayout.createSequentialGroup()
                .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainmsgpanLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainmsgpanLayout.createSequentialGroup()
                                .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(TO, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(26, 26, 26)
                                .addComponent(SearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainmsgpanLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(Send1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        mainmsgpanLayout.setVerticalGroup(
            mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainmsgpanLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainmsgpanLayout.createSequentialGroup()
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchKey))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainmsgpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Send1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout mplLayout = new javax.swing.GroupLayout(mpl);
        mpl.setLayout(mplLayout);
        mplLayout.setHorizontalGroup(
            mplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mplLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainmsgpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mplLayout.setVerticalGroup(
            mplLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mplLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(mainmsgpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MessagePanelLayout = new javax.swing.GroupLayout(MessagePanel);
        MessagePanel.setLayout(MessagePanelLayout);
        MessagePanelLayout.setHorizontalGroup(
            MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessagePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mpl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MessagePanelLayout.setVerticalGroup(
            MessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mpl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MessageScroll.setViewportView(MessagePanel);

        javax.swing.GroupLayout MessageDTabLayout = new javax.swing.GroupLayout(MessageDTab);
        MessageDTab.setLayout(MessageDTabLayout);
        MessageDTabLayout.setHorizontalGroup(
            MessageDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MessageDTabLayout.createSequentialGroup()
                .addComponent(MessageScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        MessageDTabLayout.setVerticalGroup(
            MessageDTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MessageScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(30, 3, 3));
        setForeground(new java.awt.Color(30, 3, 3));

        homepanel.setBackground(new java.awt.Color(38, 3, 3));

        Look.setFont(new java.awt.Font("Georgia", 1, 58)); // NOI18N
        Look.setForeground(new java.awt.Color(255, 255, 255));
        Look.setText("LOOK  HUB");

        description.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        description.setForeground(new java.awt.Color(102, 204, 255));
        description.setText("The Family Salon");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        adminpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/AdminLogin.png"))); // NOI18N
        adminpic.setText("jLabel1");
        adminpic.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        logout.setText("Log Out");
        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usernameLabel.setText("username");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(adminpic, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminpic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        modelicon.setForeground(new java.awt.Color(38, 3, 3));
        modelicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/Unisex.png"))); // NOI18N
        modelicon.setText("jLabel1");

        NandMpan.setBackground(new java.awt.Color(204, 204, 204));
        NandMpan.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        jScrollPane13.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        NotificationText.setEditable(false);
        NotificationText.setColumns(20);
        NotificationText.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        NotificationText.setRows(5);
        NotificationText.setText("No Notification Yet !");
        jScrollPane13.setViewportView(NotificationText);

        javax.swing.GroupLayout NandMpanLayout = new javax.swing.GroupLayout(NandMpan);
        NandMpan.setLayout(NandMpanLayout);
        NandMpanLayout.setHorizontalGroup(
            NandMpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NandMpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        NandMpanLayout.setVerticalGroup(
            NandMpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NandMpanLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        Notification.setBackground(new java.awt.Color(255, 255, 255));
        Notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/notification.png"))); // NOI18N
        Notification.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Notification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotificationActionPerformed(evt);
            }
        });

        Message.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/Messaging.png"))); // NOI18N
        Message.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageActionPerformed(evt);
            }
        });

        clock.setForeground(new java.awt.Color(255, 255, 255));
        clock.setText(" ");

        javax.swing.GroupLayout homepanelLayout = new javax.swing.GroupLayout(homepanel);
        homepanel.setLayout(homepanelLayout);
        homepanelLayout.setHorizontalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modelicon, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NandMpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Notification, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        homepanelLayout.setVerticalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(Notification, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, homepanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, homepanelLayout.createSequentialGroup()
                        .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modelicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Look, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(clock))
                    .addComponent(NandMpan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1067, 1067, 1067))
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

        Appoint_bt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        Appoint_bt.setText("Appointments");
        Appoint_bt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Appoint_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Appoint_btActionPerformed(evt);
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
        supplier_bt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplier_btActionPerformed(evt);
            }
        });

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

        credit_btn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        credit_btn.setText("Credits");
        credit_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        credit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                credit_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonpanelLayout = new javax.swing.GroupLayout(buttonpanel);
        buttonpanel.setLayout(buttonpanelLayout);
        buttonpanelLayout.setHorizontalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(credit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deltail_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(emp_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Appoint_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(product_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(service_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(supplier_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(report_bt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billing_bt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonpanelLayout.setVerticalGroup(
            buttonpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deltail_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(emp_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Appoint_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(product_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(service_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(supplier_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(report_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(billing_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(credit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 5, true));

        HomeTab.setBackground(new java.awt.Color(38, 3, 3));

        Welcome.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        Welcome.setForeground(new java.awt.Color(255, 102, 102));
        Welcome.setText("WELCOME TO LOOKHUB SOFTWARE");

        developed.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        developed.setForeground(new java.awt.Color(255, 102, 102));
        developed.setText("Developed By VIP And His Team");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lookhub/Images/welcome.png"))); // NOI18N

        javax.swing.GroupLayout HomeTabLayout = new javax.swing.GroupLayout(HomeTab);
        HomeTab.setLayout(HomeTabLayout);
        HomeTabLayout.setHorizontalGroup(
            HomeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeTabLayout.createSequentialGroup()
                .addGap(0, 269, Short.MAX_VALUE)
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(269, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(developed, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(HomeTabLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HomeTabLayout.setVerticalGroup(
            HomeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HomeTabLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(developed, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
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
                        .addComponent(buttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(homepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(homepanel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deltail_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deltail_btActionPerformed
      jTabbedPane1.add(SalonDTab1);
      jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(SalonDTab1), getTitlePanel(jTabbedPane1, SalonDTab1, "Salon Details    "));
      jTabbedPane1.setSelectedComponent(SalonDTab1);
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
        jTabbedPane1.add(ReportDTab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(ReportDTab), getTitlePanel(jTabbedPane1, ReportDTab, "Report    "));
        jTabbedPane1.setSelectedComponent(ReportDTab);
    }//GEN-LAST:event_report_btActionPerformed

    private void emp_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_btActionPerformed
        // TODO add your handling code here:
        if (ADMINorUSER.equals("ADMIN")) {
            jTabbedPane1.add(EmployeeDTab);
            jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(EmployeeDTab), getTitlePanel(jTabbedPane1, EmployeeDTab, "Employee    "));
            jTabbedPane1.setSelectedComponent(EmployeeDTab);
        } else {
            JOptionPane.showMessageDialog(null, "Only Admin Can Access Employees Tab");
        }
        
    }//GEN-LAST:event_emp_btActionPerformed

    private void Appoint_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Appoint_btActionPerformed
        // TODO add your handling code here:
            jTabbedPane1.add(AppointmentDTab);
            jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(AppointmentDTab), getTitlePanel(jTabbedPane1, AppointmentDTab, "Appointment    "));
            jTabbedPane1.setSelectedComponent(AppointmentDTab); 
    }//GEN-LAST:event_Appoint_btActionPerformed

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
      String contact =ContactTextfield.getText();
        String contactRegex = "^[789][0-9]{9}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            jTextArea.requestFocus();
        }
        else
        {
        JOptionPane.showMessageDialog(this, "Enter  Valid Contact","Contact",JOptionPane.OK_OPTION);
         ContactTextfield.requestFocus();
          ContactTextfield.setText("");
        }
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
         if (NameTextfield.getText().equals("")||SurnameTextfield.getText().equals("")||ContactNumebe.getText().equals("")||jTextArea.getText().equals("")||UserIdTextfield.getText().equals("")||PasswordTextfield.getText().equals("")||MailTextfield.getText().equals("")||ConfirmPasswordTextfield.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the above details","Details",JOptionPane.OK_OPTION);
            System.out.println("hell0");
         }else{
             String gender=null;
             String type=null;
             
            if(female.isSelected())
            {
                
                gender="female";
                 type = UserTypeCom.getItemAt(UserTypeCom.getSelectedIndex());
                 
                try {
                con=DbUtil.loadDriver();
                DbUtil.runQuery("insert into  userdetails values('"+NameTextfield.getText()+"','"+SurnameTextfield.getText()+"','"+ContactTextfield.getText()+"','"+jTextArea.getText()+"','"+gender+"','"+UserIdTextfield.getText()+"','"+PasswordTextfield.getText()+"','"+MailTextfield.getText()+"','"+type+"');");
                JOptionPane.showMessageDialog(this, "User details added Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
                getEmployeeData();
                con.close();
                
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            if(male.isSelected())
            {
                
                gender="male";
                type = UserTypeCom.getItemAt(UserTypeCom.getSelectedIndex());
                
                try {
                con=DbUtil.loadDriver();
                DbUtil.runQuery("insert into userdetails values('"+NameTextfield.getText()+"','"+SurnameTextfield.getText()+"','"+ContactTextfield.getText()+"','"+jTextArea.getText()+"','"+gender+"','"+UserIdTextfield.getText()+"','"+PasswordTextfield.getText()+"','"+MailTextfield.getText()+"','"+type+"');");
                JOptionPane.showMessageDialog(this, "User details added Succesfully","information",JOptionPane.OK_OPTION);
                getEmployeeData();
                con.close();
                    
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
             
        }
 
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

    private void AppointPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppointPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AppointPanelMouseClicked

    private void billing_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billing_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(BillingPanel);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(BillingPanel), getTitlePanel(jTabbedPane1, BillingPanel, "Billing    "));
        jTabbedPane1.setSelectedComponent(BillingPanel);
    }//GEN-LAST:event_billing_btActionPerformed

    private void addservice_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addservice_btnActionPerformed
      if(AddPriceField.getText().equals("")||AddServiceField.getText().equals("")){
      JOptionPane.showMessageDialog(this, "Enter ServiceName And Price","Details",JOptionPane.OK_OPTION);
      }else{
      try {
            con=DbUtil.loadDriver();
            DbUtil.runQuery("insert into services values('"+AddServiceField.getText()+"','"+AddPriceField.getText()+"');");
            JOptionPane.showMessageDialog(this, "Service added Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getServiceTabelData();
            con.close();
        } catch (Exception e) {
        }
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
        
        if(DeleteService.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter ServiceName ","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforDelete("delete from services where ServiceName = ?", DeleteService);
            JOptionPane.showMessageDialog(this, "Service Deleted Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getServiceTabelData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        
    }//GEN-LAST:event_Deleteservice_btnActionPerformed

    private void editservice_srh_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editservice_srh_btnActionPerformed
        // TODO add your handling code here:
        if(EditService.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter ServiceName","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from services where Servicename = ?", EditService);
            if(rs.next()){
                popUPservice.setText(rs.getString(1));
                EditServiceNF.setText(rs.getString(1));
                EditPriceF.setText(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            
                    }
        } 
    }//GEN-LAST:event_editservice_srh_btnActionPerformed

    private void ProductAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductAddActionPerformed
        try {
            con=DbUtil.loadDriver();
            DbUtil.runQuery("insert into product values('"+NameTF.getText()+"','"+PriceTF.getText()+"','"+codeTF.getText()+"','"+QuantityTF.getText()+"','"+SupplierTF.getText()+"','"+CostTf.getText()+"');");
            JOptionPane.showMessageDialog(this, "Product added Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getProductTabelData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }//GEN-LAST:event_ProductAddActionPerformed

    private void SearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchbtnActionPerformed
        // TODO add your handling code here:
        if(productnameTF.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter ProductName","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from product where ProductName = ?", productnameTF);
            if(rs.next()){
                EditnameTF.setText(rs.getString(1));
                EpriceTF.setText(rs.getString(2));
                EditcodeTF.setText(rs.getString(3));
                EquantityTF.setText(rs.getString(4));
                SnameTF.setText(rs.getString(5));
                EditCost.setText(rs.getString(6));
            }
            con.close();
        } catch (Exception e) {
            
                    }
    }                                         
        
    }//GEN-LAST:event_SearchbtnActionPerformed

    private void EpriceTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EpriceTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EpriceTFActionPerformed

    private void codeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeTFActionPerformed

    private void SupplierTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierTFActionPerformed

    private void EditServiceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditServiceKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(EditService.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Service Name","ServiceName",JOptionPane.OK_OPTION);
            }else{
                try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from services where Servicename = ?", EditService);
            if(rs.next()){
                popUPservice.setText(rs.getString(1));
                EditServiceNF.setText(rs.getString(1));
                EditPriceF.setText(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            
                    } 
            }
        }
    }//GEN-LAST:event_EditServiceKeyPressed

    private void ResetEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetEditActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            EditPriceF.setText("");
            EditService.setText("");
            EditServiceNF.setText("");
            popUPservice.setText("Example = hair cut");
        }
    }//GEN-LAST:event_ResetEditActionPerformed

    private void EditSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSerActionPerformed
        // TODO add your handling code here:
        if(EditPriceF.getText().equals("")||EditService.getText().equals("")||EditServiceNF.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Service Details","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforEdit("update services set ServiceName = ?,Price = ? where ServiceName = ?", EditServiceNF, EditPriceF,popUPservice);
            JOptionPane.showMessageDialog(this, "Service Edited Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getServiceTabelData();
            con.close();
        } catch (Exception e) {
        }
        }
    }//GEN-LAST:event_EditSerActionPerformed

    private void ServiceSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ServiceSearchKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(ServiceSearch.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Service Name","ServiceName",JOptionPane.OK_OPTION);
            }else{
                try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from services where Servicename = ?", ServiceSearch);
            if(rs.next()){
                UnitPrice.setText(rs.getString(2));
                Quantity.setText("1");
                ServiceDiscount.setText("0");
                ServiceTotal.setText(rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            
                    }
                }
        }
    }//GEN-LAST:event_ServiceSearchKeyPressed

    private void QuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuantityKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(Quantity.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Quantity","Information",JOptionPane.OK_OPTION);
            }else{
                try {
                    double totals = Integer.parseInt(Quantity.getText())*Double.parseDouble(UnitPrice.getText())-Double.parseDouble(ServiceDiscount.getText());
                    ServiceTotal.setText(Double.toString(totals));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Enter Valid Number Ex = 1,2,3,etc","Information",JOptionPane.OK_OPTION);
                }
            }
        }
    }//GEN-LAST:event_QuantityKeyPressed

    private void ServiceDiscountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ServiceDiscountKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(ServiceDiscount.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Discount","Information",JOptionPane.OK_OPTION);
            }else{
                try {
                    double totals = Integer.parseInt(Quantity.getText())*Double.parseDouble(UnitPrice.getText())-Double.parseDouble(ServiceDiscount.getText());
                    ServiceTotal.setText(Double.toString(totals));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Enter Valid Number Ex = 1,2,3,etc","Information",JOptionPane.OK_OPTION);
                }
            }
        }
    }//GEN-LAST:event_ServiceDiscountKeyPressed


    private void BServiceAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BServiceAddActionPerformed
        // TODO add your handling code here:
        if(ServiceSearch.getText().equals("")||Quantity.getText().equals("")||UnitPrice.getText().equals("")||ServiceTotal.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Service Details","Details",JOptionPane.OK_OPTION);
        }else{
            DefaultTableModel model = (DefaultTableModel)BillingTable.getModel();
            model.addRow(new Object[]{ServiceSearch.getText(),Quantity.getText(),UnitPrice.getText(),ServiceDiscount.getText(),ServiceTotal.getText()});
            totalprice = Double.parseDouble(ServiceTotal.getText())+totalprice;
            AllTotal.setText(Double.toString(totalprice));
            totaldiscout = Double.parseDouble(ServiceDiscount.getText())+totaldiscout;
            TotalDiscount2.setText(Double.toString(totaldiscout));
        } 
    }//GEN-LAST:event_BServiceAddActionPerformed

    private void BServiceDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BServiceDelActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)BillingTable.getModel();
        try {
            int selectRow = BillingTable.getSelectedRow();
            model.removeRow(selectRow);
            totalprice = totalprice-Double.parseDouble(ServiceTotal.getText());
            AllTotal.setText(Double.toString(totalprice));
            totaldiscout = totaldiscout-Double.parseDouble(ServiceDiscount.getText());
            TotalDiscount2.setText(Double.toString(totaldiscout));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_BServiceDelActionPerformed

    private void ServiceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ServiceTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)ServiceTable.getModel();
        try {
            int selectRow = ServiceTable.getSelectedRow();
            EditService.setText(model.getValueAt(selectRow, 0).toString());
            DeleteService.setText(model.getValueAt(selectRow, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ServiceTableMouseClicked

    private void BillingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BillingTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)BillingTable.getModel();
        try {
            int selectRow = BillingTable.getSelectedRow();
            ServiceSearch.setText(model.getValueAt(selectRow, 0).toString());
            Quantity.setText(model.getValueAt(selectRow, 1).toString());
            UnitPrice.setText(model.getValueAt(selectRow, 2).toString());
            ServiceDiscount.setText(model.getValueAt(selectRow, 3).toString());
            ServiceTotal.setText(model.getValueAt(selectRow, 4).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_BillingTableMouseClicked

    private void EditServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditServiceActionPerformed

    private void SnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SnameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SnameTFActionPerformed

    private void DeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProductActionPerformed
              // TODO add your handling code here:
               if(DeleteProductTF.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter ProductName ","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforDelete("delete from product where ProductName = ?", DeleteProductTF);
            JOptionPane.showMessageDialog(this, "Product Deleted Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getProductTabelData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
             
    }//GEN-LAST:event_DeleteProductActionPerformed

    private void EditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditProductActionPerformed
        // TODO add your handling code here:
        if(productnameTF.getText().equals("")||EditnameTF.getText().equals("")||EpriceTF.getText().equals("")||EditcodeTF.getText().equals("")||EquantityTF.getText().equals("")||SnameTF.getText().equals("")||EditCost.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Product Details","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforEditProduct("update product set ProductName = ?,price = ?,ProductCode=?,Quantity=?,SupplierName=?,cost=? where ProductName = ?", EditnameTF, EpriceTF,EditcodeTF,EquantityTF,SnameTF,EditCost,productnameTF);
            JOptionPane.showMessageDialog(this, "Product Edited Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getProductTabelData();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }//GEN-LAST:event_EditProductActionPerformed
   }
    private void ResetProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetProductActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            EditnameTF.setText("");
            EpriceTF.setText("");
            EditcodeTF.setText("");
            EquantityTF.setText("");
            SnameTF.setText("");
            EditCost.setText("");
        }
    }//GEN-LAST:event_ResetProductActionPerformed

    private void AddResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddResetActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            NameTF.setText("");
            PriceTF.setText("");
            codeTF.setText("");
            QuantityTF.setText("");
            SupplierTF.setText("");
            CostTf.setText("");
        }
    }//GEN-LAST:event_AddResetActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
        if(CustumerjTextField.getText().equals("")||MobilejTextField.getText().equals("")||MobilejTextField.getText().equals("")||AllTotal.getText().equals("")||billNOjTextField1.getText().equals("")||BarberNameField.getText().equals("")){
      JOptionPane.showMessageDialog(this, "Please Enter Billing Details","Details",JOptionPane.OK_OPTION);
      }else{
      try { 
            Date date= new Date(dateChooserCombo3.getText());
            sqlDate = new java.sql.Date(date.getTime());
            con=DbUtil.loadDriver();
            rs = DbUtil.runQueryforCheck("SELECT * FROM `custumerdetails` WHERE `CustumerName` =? AND `MobileNo`=? AND `Email`=?", CustumerjTextField, MobilejTextField, EmailCustjTextField1.getText());
            if (rs.next()) {
              DbUtil.runQueryforEditCustomer("update `custumerdetails` set `Total Service Rs` = ?,`Last Visit` = ? where `CustumerName` = ?", AllTotal, sqlDate,CustumerjTextField);
          } else {
                DbUtil.runQuery("insert into custumerdetails values('"+CustumerjTextField.getText()+"','"+MobilejTextField.getText()+"','"+EmailCustjTextField1.getText()+"','"+AllTotal.getText()+"','"+sqlDate+"');");
          }
            DbUtil.runQuery("insert into billing values('"+CustumerjTextField.getText()+"','"+billNOjTextField1.getText()+"','"+sqlDate+"','"+AllTotal.getText()+"','"+BarberNameField.getText()+"');");
            createNewPdf(CustumerjTextField.getText(),billNOjTextField1.getText());
            openpdf(CustumerjTextField.getText(),billNOjTextField1.getText());
            JOptionPane.showMessageDialog(this, "Bill Saved Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Bill Are Already Saved or Change Bill No or Create New Bill or "+e);
        }
      }
    }//GEN-LAST:event_SaveActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Create New Bill ?","New",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            getBillNo();
            CustumerjTextField.setText("");
            MobilejTextField.setText("");
            EmailCustjTextField1.setText("");
            ServiceSearch.setText("");
            UnitPrice.setText("");
            ServiceDiscount.setText("");
            Quantity.setText("");
            AllTotal.setText("");
            TotalDiscount2.setText("");
            BarberNameField.setText("");
            emailLabel.setText("");
            DefaultTableModel model = (DefaultTableModel)BillingTable.getModel();
                try {
                    int row = model.getRowCount();
                    for(int i=row-1;i>=0;i--){
                        model.removeRow(i);
                    }
                } catch (Exception e) {
                    }
            totaldiscout = 0.0;
            totalprice = 0.0;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ResetBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetBillActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            getBillNo();
            CustumerjTextField.setText("");
            MobilejTextField.setText("");
            EmailCustjTextField1.setText("");
            ServiceSearch.setText("");
            UnitPrice.setText("");
            ServiceDiscount.setText("");
            Quantity.setText("");
            AllTotal.setText("");
            TotalDiscount2.setText("");
            BarberNameField.setText("");
            emailLabel.setText("");
            DefaultTableModel model = (DefaultTableModel)BillingTable.getModel();
                try {
                    int row = model.getRowCount();
                    for(int i=row-1;i>=0;i--){
                    model.removeRow(i);
                    }
                } catch (Exception e) {
                    }
            totaldiscout = 0.0;
            totalprice = 0.0;
        }
    }//GEN-LAST:event_ResetBillActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_CancelActionPerformed

    private void ProductTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)ProductTable.getModel();
        try {
            int selectRow = ProductTable.getSelectedRow();
            productnameTF.setText(model.getValueAt(selectRow, 0).toString());
            DeleteProductTF.setText(model.getValueAt(selectRow, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_ProductTableMouseClicked

    private void Emp_searchbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Emp_searchbtActionPerformed
        if(search_Tf.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter  Employee Name","Details",JOptionPane.OK_OPTION);
        }
        else{
            try {
                con=DbUtil.loadDriver();
                rs=DbUtil.getResultSetForSearch("select * from userdetails where FirstName = ?", search_Tf);
                if(rs.next())
                {
                    emp_textF1.setText(rs.getString(1));
                    emp_textF2.setText(rs.getString(2));
                    emp_textF3.setText(rs.getString(3));
                    emp_textF4.setText(rs.getString(4));
                    if (rs.getString(5).equals("male")) {
                        male_rbt.setSelected(true);
                    }
                    if (rs.getString(5).equals("female")) {
                        female_rbt.setSelected(true);
                    }
                    user_Tf.setText(rs.getString(6));
                    Pass_Tf.setText(rs.getString(7));
                    email_Tf.setText(rs.getString(8));
                    UserTypeCom1.setSelectedItem(rs.getString(9));
                    con.close();
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Enter  Employee Name","Details",JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_Emp_searchbtActionPerformed

    private void DeleteEmployee1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteEmployee1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteEmployee1ActionPerformed

    private void DeleteEmployee_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteEmployee_btn1ActionPerformed
        // TODO add your handling code here:
        if(DeleteEmployee1.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter User Id ","Details",JOptionPane.OK_OPTION);
        }else{
            try {
                con=DbUtil.loadDriver();
                DbUtil.runQueryforDelete("delete from userdetails where  FirstName= ?", DeleteEmployee1);
                JOptionPane.showMessageDialog(this, "Userdetails Deleted Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
                getEmployeeData();
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_DeleteEmployee_btn1ActionPerformed

    private void email_TfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_email_TfKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(email_Tf.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                UserTypeCom1.requestFocus();
            }
        }
    }//GEN-LAST:event_email_TfKeyPressed

    private void email_TfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_TfActionPerformed
        // TODO add your handling code here:
        String contact =email_Tf.getText();
        String contactRegex ="^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            UserTypeCom1.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Email IDs","Contact",JOptionPane.OK_OPTION);
            email_Tf.requestFocus();
            email_Tf.setText("");
        }
    }//GEN-LAST:event_email_TfActionPerformed

    private void reset_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_btActionPerformed
        // TODO add your handling code here:
        
            int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            emp_textF1.setText("");
            emp_textF2.setText("");
            emp_textF3.setText("");
            emp_textF4.setText("");
            male_rbt.setSelected(false);
            female_rbt.setSelected(false);
            user_Tf.setText("");
            Pass_Tf.setText("");
            email_Tf.setText("");
        }
    }//GEN-LAST:event_reset_btActionPerformed

    private void male_rbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_male_rbtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_male_rbtActionPerformed

    private void female_rbtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_female_rbtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(female_rbt.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                user_Tf.requestFocus();
            }
        }
    }//GEN-LAST:event_female_rbtKeyPressed

    private void Pass_TfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pass_TfKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(Pass_Tf.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                email_Tf.requestFocus();
            }
        }
    }//GEN-LAST:event_Pass_TfKeyPressed

    private void Pass_TfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pass_TfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pass_TfActionPerformed

    private void user_TfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_TfKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(user_Tf.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                Pass_Tf.requestFocus();
            }
        }
    }//GEN-LAST:event_user_TfKeyPressed

    private void user_TfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_TfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_TfActionPerformed

    private void emp_textF4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_textF4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(emp_textF4.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                female_rbt.requestFocus();
            }
        }
    }//GEN-LAST:event_emp_textF4KeyPressed

    private void emp_textF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_textF4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_textF4ActionPerformed

    private void emp_textF3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_textF3KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(emp_textF3.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                emp_textF4.requestFocus();
            }
        }
    }//GEN-LAST:event_emp_textF3KeyPressed

    private void emp_textF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_textF3ActionPerformed
        String contact =emp_textF3.getText();
        String contactRegex = "^[789][0-9]{9}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            jTextArea.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Contact","Contact",JOptionPane.OK_OPTION);
            emp_textF3.setText("");
            emp_textF3.requestFocus();
            
        }
    }//GEN-LAST:event_emp_textF3ActionPerformed

    private void emp_textF2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_textF2KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(emp_textF2.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter last Name","LastName",JOptionPane.OK_OPTION);
            }else{
                emp_textF3.requestFocus();
            }
        }
    }//GEN-LAST:event_emp_textF2KeyPressed

    private void emp_textF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emp_textF2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emp_textF2ActionPerformed

    private void emp_textF1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emp_textF1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(emp_textF1.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter First Name","FirstName",JOptionPane.OK_OPTION);
            }else{
                emp_textF2.requestFocus();
            }
        }
    }//GEN-LAST:event_emp_textF1KeyPressed

    private void editEmployee_btn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editEmployee_btn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_editEmployee_btn1KeyPressed

    private void editEmployee_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEmployee_btn1ActionPerformed
        // TODO add your handling code here:
        String gender=null;
        if(female_rbt.isSelected())
        {
            gender="female";
        }

        if(male_rbt.isSelected())
        {
            gender="male";
        }

        if(emp_textF1.getText().equals("")||emp_textF2.getText().equals("")||emp_textF3.getText().equals("")||emp_textF4.getText().equals("")||user_Tf.getText().equals("")||Pass_Tf.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter employee Details","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforEditEmployee("UPDATE `userdetails` SET `FirstName`=?,`LastName`=?,`Contact No`=?,`Address`=?,`Gender`=?,`User Id`=?,`Password`=?,`Email`=?,`Type`=? WHERE FirstName=?;", emp_textF1, emp_textF2,emp_textF3,emp_textF4,gender,user_Tf,Pass_Tf,email_Tf,UserTypeCom1.getItemAt(UserTypeCom1.getSelectedIndex()),search_Tf);
            JOptionPane.showMessageDialog(this, "Employee Details Edited Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getEmployeeData();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    } 
        
    }//GEN-LAST:event_editEmployee_btn1ActionPerformed

    private void MobilejTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobilejTextFieldActionPerformed
        // TODO add your handling code here:
        String contact =MobilejTextField.getText();
        String contactRegex = "^[789][0-9]{9}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            EmailCustjTextField1.requestFocus();
        }
        else
        {
        JOptionPane.showMessageDialog(this, "Enter  Valid Contact","Contact",JOptionPane.OK_OPTION);
         MobilejTextField.requestFocus();
          MobilejTextField.setText("");
        }
    }//GEN-LAST:event_MobilejTextFieldActionPerformed

    private void EmailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTFActionPerformed
        // TODO add your handling code here:
        String contact =EmailTF.getText();
        String contactRegex ="^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            PhoneTF.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Email IDs","Contact",JOptionPane.OK_OPTION);
            EmailTF.requestFocus();
            EmailTF.setText("");
        }
    }//GEN-LAST:event_EmailTFActionPerformed

    private void PhoneTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneTFActionPerformed
        // TODO add your handling code here:
        String contact =PhoneTF.getText();
        String contactRegex = "^[789][0-9]{9}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            DescriptionTF.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Contact","Contact",JOptionPane.OK_OPTION);
            PhoneTF.requestFocus();
            PhoneTF.setText("");
        }
    }//GEN-LAST:event_PhoneTFActionPerformed

    private void PhoneTFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PhoneTFKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneTFKeyPressed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
        try {
            String file = "C:\\Users\\Admin\\Documents\\pdfcreater\\"+CustumerjTextField.getText()+billNOjTextField1.getText()+".pdf";
            org.icepdf.core.pobjects.Document doc = new org.icepdf.core.pobjects.Document();
            doc.setFile(file);
            SwingController sc = new SwingController();
            DocumentViewController vc = new DocumentViewControllerImpl(sc); 
            vc.setDocument(doc);
            PrintHelper printHelper = new PrintHelper(vc, doc.getPageTree(), 
            MediaSizeName.NA_LEGAL, PrintQuality.DRAFT); 
            printHelper.setupPrintService( 0, 9, 1,true, true);
            printHelper.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unkown error occurs");
        }
        
        
    }//GEN-LAST:event_PrintActionPerformed

    private void SearchCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCustActionPerformed
        // TODO add your handling code here:
        if(CustumerjTextField.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter Customer Namer","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from custumerdetails where CustumerName = ?", CustumerjTextField);
            if(rs.next()){
                MobilejTextField.setText(rs.getString(2));
                EmailCustjTextField1.setText(rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e);
                    }
        }
        
    }//GEN-LAST:event_SearchCustActionPerformed

    private void Emp_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Emp_tableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Emp_table.getModel();
        try {
            int selectRow = Emp_table.getSelectedRow();
            search_Tf.setText(model.getValueAt(selectRow, 0).toString());
            DeleteEmployee1.setText(model.getValueAt(selectRow, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_Emp_tableMouseClicked

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        AdminLogin al = new AdminLogin();
        al.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void SearchReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchReportActionPerformed
        // TODO add your handling code here:
        
        
                    Date dfrom= new Date(datefrom.getText());
                    java.sql.Date from = new java.sql.Date(dfrom.getTime());
                    
                    Date dto= new Date(todate.getText());
                    java.sql.Date to = new java.sql.Date(dto.getTime());
                    
                    
                    
        if (ReportCombo.getSelectedItem().equals("Billing")) {
            SearchNameReport.setText("Bill No");
            try {
                    con = DbUtil.loadDriver();
                    rs = DbUtil.getResultSetForbillinReport("SELECT `CustumerName`, `Bill No`, `Bill Date`, `Total Bill Rs` FROM `billing` WHERE `Bill Date` BETWEEN ? AND ?",from.toString(),to.toString());
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    con.close();
                    SearchTable.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                        }
        }
        if (ReportCombo.getSelectedItem().equals("Barber")) {
            SearchNameReport.setText("Barber's Name");
            getSuggestionPane(ReportNameTextFielad, "userdetails");
            try {
                    con = DbUtil.loadDriver();
                    rs = DbUtil.getResultSetForbillinReport("SELECT `Barber Name`, `CustumerName`, `Bill No`, `Bill Date`, `Total Bill Rs` FROM `billing` WHERE `Bill Date` BETWEEN ? AND ?",from.toString(),to.toString());
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    con.close();
                    SearchTable.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                        }
        }
        if (ReportCombo.getSelectedItem().equals("Product")) {
            SearchNameReport.setText("Product Name");
            getSuggestionPane(ReportNameTextFielad, "product");
            try {
                    con = DbUtil.loadDriver();
                    rs = DbUtil.getResultSet("SELECT * FROM `product` ");
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    con.close();
                    SearchTable.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                        }
        }
        if (ReportCombo.getSelectedItem().equals("Suppliers")) {
            SearchNameReport.setText("Supplier's Name");
            getSuggestionPane(ReportNameTextFielad, "supplier");
            try {
                    con = DbUtil.loadDriver();
                    rs = DbUtil.getResultSet("SELECT * FROM `supplier` ");
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    con.close();
                    SearchTable.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                        }
        }
        if (ReportCombo.getSelectedItem().equals("Customers")) {
            SearchNameReport.setText("Customer's Name");
            getSuggestionPane(ReportNameTextFielad, "custumerdetails");
            try {
                    con = DbUtil.loadDriver();
                    rs = DbUtil.getResultSetForbillinReport("SELECT * FROM `custumerdetails` WHERE `Last Visit` BETWEEN ? AND ?",from.toString(),to.toString());
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    con.close();
                    SearchTable.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
                        }
        }
    }//GEN-LAST:event_SearchReportActionPerformed

    private void SearchIteamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchIteamActionPerformed
        // TODO add your handling code here:
        if (ReportCombo.getSelectedItem().equals("Billing")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Bill No","Details",JOptionPane.OK_OPTION);
            }else{ 
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT * FROM `billing` WHERE `Bill No` = ?", ReportNameTextFielad);
                    if(rs.next()){
                        Column1.setText("Bill No");
                        row1.setText(rs.getString(2));
                        Column2.setText("Bill Date");
                        row2.setText(rs.getString(3));
                        Column3.setText("Customer Name");
                        row3.setText(rs.getString(1));
                        Column4.setText("Total Rs");
                        row4.setText(rs.getString(4));
                        Column5.setText("View Bill");
                        row5.setText("Close Bill");
                        Column5.setText("");
                        row5.setText("");
                        bill.setVisible(true);
                        bill.setText("BILL");
                        
                        
                    }else{
                    JOptionPane.showMessageDialog(this, "bill not found please check bill no");
                    }
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
        }
        if (ReportCombo.getSelectedItem().equals("Customers")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Customer Name","Details",JOptionPane.OK_OPTION);
            }else{
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT * FROM `custumerdetails` WHERE `CustumerName` = ?", ReportNameTextFielad);
                    if(rs.next()){
                        Column5.setVisible(true);
                        row5.setVisible(true);
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Customer Name");
                        row1.setText(rs.getString(1));
                        Column2.setText("Mobile No");
                        row2.setText(rs.getString(2));
                        Column3.setText("Email");
                        row3.setText(rs.getString(3));
                        Column4.setText("Total Service Charge");
                        row4.setText(rs.getString(4));
                        Column5.setText("Last Visited");
                        bill.setVisible(false);
                        row5.setText(rs.getString(5));
                        
                    }else{
                    JOptionPane.showMessageDialog(this, "Customer not found please check Customer's Name");
                    }
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
         if (ReportCombo.getSelectedItem().equals("Barber")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Barber Name","Details",JOptionPane.OK_OPTION);
            }else{
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT * FROM `billing` WHERE `Barber Name` = ?", ReportNameTextFielad);
                    if(rs.next()){
                        Column5.setVisible(true);
                        row5.setVisible(true);
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Barber Name");
                        row1.setText(rs.getString(5));
                        Column2.setText("Custumer Name");
                        row2.setText(rs.getString(1));
                        Column3.setText("Bill No");
                        row3.setText(rs.getString(2));
                        Column4.setText("Bill Date");
                        row4.setText(rs.getString(3));
                        Column5.setText("Total Service Rs");
                        bill.setVisible(false);
                        row5.setText(rs.getString(4));
                        
                    }else{
                    JOptionPane.showMessageDialog(this, "Barber not found please check Barber's Name");
                    }
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
         if (ReportCombo.getSelectedItem().equals("Product")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Product Name","Details",JOptionPane.OK_OPTION);
            }else{
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT * FROM `product` WHERE `ProductName` = ?", ReportNameTextFielad);
                    if(rs.next()){
                        Column5.setVisible(true);
                        row5.setVisible(true);
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Product Name");
                        row1.setText(rs.getString(1));
                        Column2.setText("Price");
                        row2.setText(rs.getString(2));
                        Column3.setText("Quantity");
                        row3.setText(rs.getString(4));
                        Column4.setText("Supplier Name");
                        row4.setText(rs.getString(5));
                        Column5.setText("Cost");
                        bill.setVisible(false);
                        row5.setText(rs.getString(6));
                        
                    }else{
                    JOptionPane.showMessageDialog(this, "Product not found please check Product's Name");
                    }
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
         if (ReportCombo.getSelectedItem().equals("Suppliers")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Supplier Name","Details",JOptionPane.OK_OPTION);
            }else{
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT * FROM `supplier` WHERE `CompanyName` = ?", ReportNameTextFielad);
                    if(rs.next()){
                        Column5.setVisible(true);
                        row5.setVisible(true);
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Company Name");
                        row1.setText(rs.getString(1));
                        Column2.setText("Address");
                        row2.setText(rs.getString(2));
                        Column3.setText("Contact");
                        row3.setText(rs.getString(3));
                        Column4.setText("Email");
                        row4.setText(rs.getString(4));
                        Column5.setText("");
                        bill.setVisible(false);
                        row5.setText("");
                        
                    }else{
                    JOptionPane.showMessageDialog(this, "Supplier not found please check Supplier's Name");
                    }
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_SearchIteamActionPerformed

    private void ReportTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportTableMouseClicked
        // TODO add your handling code here:
        if (ReportCombo.getSelectedItem().equals("Billing")) {
            DefaultTableModel model = (DefaultTableModel)ReportTable.getModel();
                try {
                    int selectRow = ReportTable.getSelectedRow();
                    Column1.setText("Bill No");
                    row1.setText(model.getValueAt(selectRow, 1).toString());
                    ReportNameTextFielad.setText(model.getValueAt(selectRow, 1).toString());
                    Column2.setText("Bill Date");
                    row2.setText(model.getValueAt(selectRow, 2).toString());
                    Column3.setText("Customer Name");
                    row3.setText(model.getValueAt(selectRow, 0).toString());
                    Column4.setText("Total Rs");
                    row4.setText(model.getValueAt(selectRow, 3).toString());
                    Column5.setText("");
                    row5.setText("");
                    bill.setVisible(true);
                    bill.setText("BILL");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select Bill From Table","information",JOptionPane.OK_OPTION);
                }
        }
        if (ReportCombo.getSelectedItem().equals("Customers")) {
            DefaultTableModel model = (DefaultTableModel)ReportTable.getModel();
                try {
                    int selectRow = ReportTable.getSelectedRow();
                        Column5.setForeground(new Color(255,255,255));
                        row5.setForeground(new Color(255,255,255));
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Customer Name");
                        row1.setText(model.getValueAt(selectRow, 0).toString());
                        ReportNameTextFielad.setText(model.getValueAt(selectRow, 0).toString());
                        Column2.setText("Mobile No");
                        row2.setText(model.getValueAt(selectRow, 1).toString());
                        Column3.setText("Email");
                        row3.setText(model.getValueAt(selectRow, 2).toString());
                        Column4.setText("Total Service Charge");
                        row4.setText(model.getValueAt(selectRow, 3).toString());
                        Column5.setText("Last Visited");
                        row5.setText(model.getValueAt(selectRow, 4).toString());
                        bill.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select Customer Name From Table","information",JOptionPane.OK_OPTION);
                }
        }
        if (ReportCombo.getSelectedItem().equals("Barber")) {
            DefaultTableModel model = (DefaultTableModel)ReportTable.getModel();
                try {
                    int selectRow = ReportTable.getSelectedRow();
                        Column5.setForeground(new Color(255,255,255));
                        row5.setForeground(new Color(255,255,255));
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Barber Name");
                        row1.setText(model.getValueAt(selectRow, 0).toString());
                        ReportNameTextFielad.setText(model.getValueAt(selectRow, 0).toString());
                        Column2.setText("Customer Name");
                        row2.setText(model.getValueAt(selectRow, 1).toString());
                        Column3.setText("Bill No");
                        row3.setText(model.getValueAt(selectRow, 2).toString());
                        Column4.setText("Bill Date");
                        row4.setText(model.getValueAt(selectRow, 3).toString());
                        Column5.setText("Total Service Rs");
                        row5.setText(model.getValueAt(selectRow, 4).toString());
                        bill.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select Barber Name From Table","information",JOptionPane.OK_OPTION);
                }
        }
        if (ReportCombo.getSelectedItem().equals("Product")) {
            DefaultTableModel model = (DefaultTableModel)ReportTable.getModel();
                try {
                    int selectRow = ReportTable.getSelectedRow();
                        Column5.setForeground(new Color(255,255,255));
                        row5.setForeground(new Color(255,255,255));
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Product Name");
                        row1.setText(model.getValueAt(selectRow, 0).toString());
                        ReportNameTextFielad.setText(model.getValueAt(selectRow, 0).toString());
                        Column2.setText("Pricr");
                        row2.setText(model.getValueAt(selectRow, 1).toString());
                        Column3.setText("Quantity");
                        row3.setText(model.getValueAt(selectRow, 2).toString());
                        Column4.setText("Supplier Name");
                        row4.setText(model.getValueAt(selectRow, 3).toString());
                        Column5.setText("Cost");
                        row5.setText(model.getValueAt(selectRow, 4).toString());
                        bill.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select Product Name From Table","information",JOptionPane.OK_OPTION);
                }
        }
        if (ReportCombo.getSelectedItem().equals("Suppliers")) {
            DefaultTableModel model = (DefaultTableModel)ReportTable.getModel();
                try {
                    int selectRow = ReportTable.getSelectedRow();
                        Column5.setForeground(new Color(255,255,255));
                        row5.setForeground(new Color(255,255,255));
                        Column5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        row5.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        Column1.setText("Company Name");
                        row1.setText(model.getValueAt(selectRow, 0).toString());
                        ReportNameTextFielad.setText(model.getValueAt(selectRow, 0).toString());
                        Column2.setText("Address");
                        row2.setText(model.getValueAt(selectRow, 1).toString());
                        Column3.setText("Contact");
                        row3.setText(model.getValueAt(selectRow, 2).toString());
                        Column4.setText("Email");
                        row4.setText(model.getValueAt(selectRow, 3).toString());
                        Column5.setText("");
                        row5.setText("");
                        bill.setVisible(false);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Please Select Supplier Name From Table","information",JOptionPane.OK_OPTION);
                }
        }
    }//GEN-LAST:event_ReportTableMouseClicked

    private void searchCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCNActionPerformed
        // TODO add your handling code here:
        if(EditCtf.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter CompanyName","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from supplier where CompanyName = ?",EditCtf);
            if(rs.next()){
                EditSupplirttf.setText(rs.getString(1));
                AddTextArea.setText(rs.getString(2));
                SupplierCN.setText(rs.getString(3));
                SuppEditM.setText(rs.getString(4));
                
            }
            con.close();
        } catch (Exception e) {
            
                    }
    }                                         
        
    }//GEN-LAST:event_searchCNActionPerformed

    private void SupplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)SupplierTable.getModel();
        try {
            int selectRow = SupplierTable.getSelectedRow();
            EditCtf.setText(model.getValueAt(selectRow, 0).toString());
            EditSupplirttf.setText(model.getValueAt(selectRow, 0).toString());
            AddTextArea.setText(model.getValueAt(selectRow, 1).toString());
            SupplierCN.setText(model.getValueAt(selectRow, 2).toString());
            SuppEditM.setText(model.getValueAt(selectRow, 3).toString());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Supplier From Table","information",JOptionPane.OK_OPTION);
        }
        
    }//GEN-LAST:event_SupplierTableMouseClicked

    private void DelSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelSuppActionPerformed
        // TODO add your handling code here:
        if(Deltf.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter CompanyName ","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforDelete("delete from supplier where CompanyName = ?", Deltf);
            JOptionPane.showMessageDialog(this, "Supplier Deleted Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getSupplierTabelData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }//GEN-LAST:event_DelSuppActionPerformed

    private void ContactTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContactTFActionPerformed

    private void SEmailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEmailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEmailTFActionPerformed

    private void SaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaddActionPerformed
        // TODO add your handling code here:
        try {
            con=DbUtil.loadDriver();
            DbUtil.runQuery("insert into supplier values('"+CNameTF.getText()+"','"+AddressTA.getText()+"','"+ContactTF.getText()+"','"+SEmailTF.getText()+"');");
            JOptionPane.showMessageDialog(this, "Supplier Details added Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getSupplierTabelData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
    }//GEN-LAST:event_SaddActionPerformed

    private void SresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SresetActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            CNameTF.setText("");
            AddressTA.setText("");
            ContactTF.setText("");
            SEmailTF.setText("");
        }
    }//GEN-LAST:event_SresetActionPerformed

    private void SupplierCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupplierCNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierCNActionPerformed

    private void SuppEditMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppEditMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuppEditMActionPerformed

    private void EditSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSuppActionPerformed
        // TODO add your handling code here:
        if(EditCtf.getText().equals("")||EditSupplirttf.getText().equals("")||AddTextArea.getText().equals("")||SupplierCN.getText().equals("")||SuppEditM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Supplier Details","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforEditSupplie("update supplier set CompanyName = ?,Address = ?,Contact=?,Email=? where CompanyName = ?", EditSupplirttf, AddTextArea,SupplierCN,SuppEditM,EditCtf);
            JOptionPane.showMessageDialog(this, "Product Edited Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getSupplierTabelData();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }                          
    }//GEN-LAST:event_EditSuppActionPerformed

    private void ResetSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetSuppActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            EditCtf.setText("");
            EditSupplirttf.setText("");
            AddTextArea.setText("");
            SupplierCN.setText("");
            SuppEditM.setText("");
        }
        
    }//GEN-LAST:event_ResetSuppActionPerformed

    private void supplier_btActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplier_btActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(SupplierDtab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(SupplierDtab), getTitlePanel(jTabbedPane1, SupplierDtab, "Supplier    "));
        jTabbedPane1.setSelectedComponent(SupplierDtab);     
    }//GEN-LAST:event_supplier_btActionPerformed

    private void jScrollPane9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane9MouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel)SupplierTable.getModel();
        try {
            int selectRow = SupplierTable.getSelectedRow();
            //EditCtf.setText(model.getValueAt(selectRow, 0).toString());
            EditSupplirttf.setText(model.getValueAt(selectRow, 1).toString());
            AddTextArea.setText(model.getValueAt(selectRow, 2).toString());
            SupplierCN.setText(model.getValueAt(selectRow, 3).toString());
            SuppEditM.setText(model.getValueAt(selectRow, 4).toString());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Supplier From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_jScrollPane9MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "png","jpg","gif");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectFile = fileChooser.getSelectedFile();
            String path = selectFile.getAbsolutePath();
            ImageIcon myIcon = new ImageIcon(path);
            java.awt.Image img = myIcon.getImage();
            java.awt.Image newImage = img.getScaledInstance(IMAGE.getWidth(), IMAGE.getHeight(),Image.ALIGN_MIDDLE);
            ImageIcon imageIcon = new ImageIcon(newImage);
            IMAGE.setIcon(imageIcon);
            Imagepath =path;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        IMAGE.setIcon(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMouseClicked
        // TODO add your handling code here:
        Pdfviewer pf = new Pdfviewer(row3.getText(),row1.getText());
        pf.setVisible(true);
    }//GEN-LAST:event_billMouseClicked

    private void printTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTableActionPerformed
        // TODO add your handling code here:
        try {
            MessageFormat header = new MessageFormat("LOOK HUB "+ReportCombo.getSelectedItem()+" Report");
            MessageFormat footer = new MessageFormat("");
            ReportTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_printTableActionPerformed

    private void SaveasPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveasPDFActionPerformed
        // TODO add your handling code here:
        Pdfviewer pf = new Pdfviewer(CustumerjTextField.getText(),billNOjTextField1.getText());
        pf.setVisible(true);
        
    }//GEN-LAST:event_SaveasPDFActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (SalonNametf.getText().equals("")||Addresstf.getText().equals("")||EmailTF.getText().equals("")||PhoneTF.getText().equals("")||DescriptionTF.getText().equals("")||IMAGE.getIcon()==null) {
            JOptionPane.showMessageDialog(this, "Enter Salon Details","Details",JOptionPane.OK_OPTION);
        }else{
            int opt = JOptionPane.showConfirmDialog(this, "Do you really want to Save Changes ?","SAVE",JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION){
                try {
                    con=DbUtil.loadDriver();
                    DbUtil.runQuery("DELETE FROM `salondetail` WHERE `Sr No` = 1");
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `salondetail`(`Sr No`, `SalonName`, `Address`, `Email`, `PhoneNo`, `Description`, `SalonLogo`) VALUES (?,?,?,?,?,?,?)");
                    ps.setInt(1, 1);
                    ps.setString(2, SalonNametf.getText());
                    ps.setString(3, Addresstf.getText());
                    ps.setString(4,EmailTF.getText());
                    ps.setLong(5, Long.parseLong(PhoneTF.getText()));
                    ps.setString(6, DescriptionTF.getText());
                    FileInputStream file = new FileInputStream(Imagepath);
                    ps.setBinaryStream(7, file,file.available());
                    ps.executeUpdate();
                    salondetail();
                    
                    JOptionPane.showMessageDialog(this, "Details Save SuccessFully","SAVED",JOptionPane.INFORMATION_MESSAGE,iconimg);
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void ResetDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetDefaultActionPerformed
        // TODO add your handling code here:
        int opt = JOptionPane.showConfirmDialog(this, "Do you really want to RESET Default ?","RESET Default",JOptionPane.YES_NO_OPTION);
            if(opt==JOptionPane.YES_OPTION){
                try {
                    con=DbUtil.loadDriver();
                    DbUtil.runQuery("DELETE FROM `salondetail` WHERE `Sr No` = 1");
                    con.close();
                    JOptionPane.showMessageDialog(this, "Please RESTART The Software To See Changes","RESET",JOptionPane.INFORMATION_MESSAGE,iconimg);
                }catch (Exception e) {
                }
            }
    }//GEN-LAST:event_ResetDefaultActionPerformed

    private void SearchTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTableActionPerformed
        // TODO add your handling code here:
if (ReportCombo.getSelectedItem().equals("Barber")) {
            if(ReportNameTextFielad.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Enter Barber Name","Details",JOptionPane.OK_OPTION);
            }else{
                try {
                    con=DbUtil.loadDriver();
                    rs=DbUtil.getResultSetForSearch("SELECT `Barber Name`, `CustumerName`, `Bill No`, `Bill Date`, `Total Bill Rs` FROM `billing` WHERE `Barber Name` = ?", ReportNameTextFielad);
                    ReportTable.setModel(DbUtils.resultSetToTableModel(rs));
                    
                    con.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }//GEN-LAST:event_SearchTableActionPerformed

    private void credit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_credit_btnActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(OtherDTab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(OtherDTab), getTitlePanel(jTabbedPane1, OtherDTab, "Credits    "));
        jTabbedPane1.setSelectedComponent(OtherDTab);
    }//GEN-LAST:event_credit_btnActionPerformed

    private void CustomerNameopKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CustomerNameopKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CustomerNameopKeyPressed

    private void MobilejTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobilejTextField1ActionPerformed
        // TODO add your handling code here:
        String contact =MobilejTextField1.getText();
        String contactRegex = "^[789][0-9]{9}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            EmailCustjTextField2.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Contact","Contact",JOptionPane.OK_OPTION);
            MobilejTextField1.setText("");
            MobilejTextField1.requestFocus();
            
        }
    }//GEN-LAST:event_MobilejTextField1ActionPerformed

    private void MobilejTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobilejTextField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MobilejTextField1KeyPressed

    private void EmailCustjTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailCustjTextField2ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String contact =EmailCustjTextField2.getText();
        String contactRegex ="^[a-zA-Z0-9_+&*-]+(?:\\."+
        "[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
        "A-Z]{2,7}$";
        Pattern p = Pattern.compile(contactRegex);
        Matcher m = p.matcher(contact);
        boolean matchFound = m.matches();
        if (matchFound)
        {
            jSpinner1.requestFocus();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Enter  Valid Email IDs","Contact",JOptionPane.OK_OPTION);
            EmailCustjTextField2.requestFocus();
            EmailCustjTextField2.setText("");
        }
    }//GEN-LAST:event_EmailCustjTextField2ActionPerformed

    private void EmailCustjTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailCustjTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailCustjTextField2KeyPressed

    private void SearchCust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCust1ActionPerformed
        // TODO add your handling code here:
        if(CustomerNameop.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter Customer Namer","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select * from custumerdetails where CustumerName = ?", CustomerNameop);
            if(rs.next()){
                MobilejTextField1.setText(rs.getString(2));
                EmailCustjTextField2.setText(rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, e);
                    }
        }
    }//GEN-LAST:event_SearchCust1ActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
        // TODO add your handling code here:
        if(CustomerNameop.getText().equals("")||MobilejTextField1.getText().equals("")||MobilejTextField1.getText().equals("")){
      JOptionPane.showMessageDialog(this, "Please Enter  Details","Details",JOptionPane.OK_OPTION);
      }else{
      try { 
            Date date= new Date(dateChooserCombo4.getText());
            sqlDate = new java.sql.Date(date.getTime());
            con=DbUtil.loadDriver();
            
            String time = jSpinner1.getValue().toString();
            DbUtil.runQuery("insert into appointment values('"+CustomerNameop.getText()+"','"+MobilejTextField1.getText()+"','"+EmailCustjTextField2.getText()+"','"+sqlDate+"','"+time.substring(11, 19)+"');");
            JOptionPane.showMessageDialog(this, "Appointment set Sucessfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getAppointmentData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
      }
    }//GEN-LAST:event_ADDActionPerformed

    private void AppointmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AppointmentTableMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)AppointmentTable.getModel();
        try {
            int selectRow = AppointmentTable.getSelectedRow();
            c11.setText(model.getValueAt(selectRow, 0).toString());
            c12.setText(model.getValueAt(selectRow, 1).toString());
            c14.setText(model.getValueAt(selectRow, 2).toString());
            c15.setText(model.getValueAt(selectRow, 3).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Service From Table or "+e,"information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_AppointmentTableMouseClicked

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
        if(CustomerNameop.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Enter Customer Name ","Details",JOptionPane.OK_OPTION);
        }else{
         try {
            con=DbUtil.loadDriver();
            DbUtil.runQueryforDelete("delete from appointment where `Customer Name` = ?", CustomerNameop);
            JOptionPane.showMessageDialog(this, "Appointment Deleted Succesfully","information",JOptionPane.INFORMATION_MESSAGE,iconimg);
            getAppointmentData();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        
    }//GEN-LAST:event_DELETEActionPerformed

    private void ReseTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReseTActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            CustomerNameop.setText("");
            MobilejTextField1.setText("");
            EmailCustjTextField2.setText("");
        }
    }//GEN-LAST:event_ReseTActionPerformed

    private void RegistrationbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrationbtnActionPerformed
        // TODO add your handling code here:
        mainEmployeepan1.setVisible(false);
        RegistryPanel.setVisible(true);
        
    }//GEN-LAST:event_RegistrationbtnActionPerformed

    private void BACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKActionPerformed
        // TODO add your handling code here:
        RegistryPanel.setVisible(false);
        mainEmployeepan1.setVisible(true);
        
    }//GEN-LAST:event_BACKActionPerformed

    private void NotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotificationActionPerformed
        // TODO add your handling code here:
        if (NandMpan.isVisible()) {
            NandMpan.setVisible(false);
        } else {
            NandMpan.setVisible(true);
        }
        
    }//GEN-LAST:event_NotificationActionPerformed

    private void MessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.add(MessageDTab);
        jTabbedPane1.setTabComponentAt(jTabbedPane1.indexOfComponent(MessageDTab), getTitlePanel(jTabbedPane1, MessageDTab, "Message    "));
        jTabbedPane1.setSelectedComponent(MessageDTab);
    }//GEN-LAST:event_MessageActionPerformed

    private void Send1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Send1ActionPerformed
        // TODO add your handling code here:
        try {
			// Construct data
			String apiKey = "apikey=" + "2Vwexo6G5xA-e8wmamg2tRTD2O7qath6t6csS3b6Hm";
			String message = "&message=" + Msg.getText();
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + TO.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message ;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showMessageDialog(null, line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
                         JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
    }//GEN-LAST:event_Send1ActionPerformed

    private void SearchKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchKeyActionPerformed
        // TODO add your handling code here:
        if(Search.getText().equals("")){
        JOptionPane.showMessageDialog(this, "Enter Name","Details",JOptionPane.OK_OPTION);
        }else{
        try {
            con=DbUtil.loadDriver();
            rs=DbUtil.getResultSetForSearch("select MobileNo from custumerdetails where CustumerName = ?", Search);
            if(rs.next()){
                TO.setText(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, e);
                    }
        }
    }//GEN-LAST:event_SearchKeyActionPerformed

    private void MsgTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MsgTableMouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel)MsgTable.getModel();
        try {
            int selectRow = MsgTable.getSelectedRow();
            Search.setText(model.getValueAt(selectRow, 0).toString());
            TO.setText(model.getValueAt(selectRow, 1).toString());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Select Name From Table","information",JOptionPane.OK_OPTION);
        }
    }//GEN-LAST:event_MsgTableMouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        int Resetopt = JOptionPane.showConfirmDialog(this, "Do you really want to Reset ?","RESET",JOptionPane.YES_NO_OPTION);

        if(Resetopt==JOptionPane.YES_OPTION){
            Msg.setText("");
            TO.setText("");
            Search.setText("");
        }
    }//GEN-LAST:event_resetActionPerformed
    //here is code for generate pdf
    
    public void createNewPdf(String name,String billnos){
        File file = new File("C:\\Users\\Admin\\Documents\\pdfcreater\\"+name+billnos+".pdf");
        
        try{
            if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "folder not found");
        }
        Document doc = new Document();
        try {
            PdfWriter p = PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();
            Paragraph title = new Paragraph();
            Paragraph discription = new Paragraph();
            
            //for image 
            Image img = Image.getInstance("src\\lookhub\\Images\\Unisex.png");
            img.setAbsolutePosition(150f, 725f);// image position
            img.scaleAbsolute(75f, 75f);
            
            
            //for title
            Font fnt = new Font(Font.FontFamily.TIMES_ROMAN, 50, WIDTH, BaseColor.PINK);
            title.setFont(fnt);
            title.setIndentationLeft(190); // title left side space
            title.setLeading(50);// title top side space
            title.add("Look Hub");
            
            //for discription
            Font fnt1 = new Font(Font.FontFamily.TIMES_ROMAN, 20, WIDTH, BaseColor.RED);
            discription.setFont(fnt1);
            discription.setIndentationLeft(195); // di.. title left side space
            discription.setLeading(25);// discr... top side space
            discription.add("the family salon");
            
            //for line
            LineSeparator line = new LineSeparator();
            PdfContentByte pd = new PdfContentByte(p);
            line.drawLine(pd, LEFT_ALIGNMENT, RIGHT_ALIGNMENT, TOP_ALIGNMENT);
            line.setOffset(-10);
            line.setPercentage(70);
            line.setAlignment(-50);
            
            //for contact and address
            Paragraph voidp = new Paragraph();
            voidp.setIndentationLeft(100); // title left side space
            voidp.setLeading(5);
            voidp.add("  ");
            Paragraph contactdetails = new Paragraph();
            contactdetails.setIndentationLeft(85); // title left side space
            contactdetails.setIndentationRight(80);
            String address = "Akruti Apartment, Vaishali Nagar Cement Road, Nagpur - 440017, Near Indian Oil Petrol Pump";
            contactdetails.add(address);
            Paragraph phoneEmail = new Paragraph();
            phoneEmail.setIndentationLeft(85); // title left side space
            phoneEmail.setIndentationRight(80);
            String contact = "+91 9152423149";
            String mail = "lookhub@gmail.com";
            phoneEmail.add("Phone  "+contact+"   E-mail  "+mail);
            
            //for line
            LineSeparator line1 = new LineSeparator();
            line1.drawLine(pd, LEFT_ALIGNMENT, RIGHT_ALIGNMENT, TOP_ALIGNMENT);
            line1.setOffset(-5);
            line1.setPercentage(70);
            line1.setAlignment(-50);
            
            //Tabel for bill
            PdfPTable BillTable = new PdfPTable(5);
            BillTable.setWidthPercentage(70);
            BillTable.setSpacingBefore(2f);
            BillTable.setSpacingAfter(2f);
            //for column
            float[] columnwidth = {1f,2.5f,1f,1f,1f};
            BillTable.setWidths(columnwidth);
            
            PdfPCell srno = new PdfPCell(new Paragraph("Sr No"));
            srno.setBackgroundColor(BaseColor.ORANGE);
            srno.setBorderColor(BaseColor.BLACK);
            srno.setPaddingTop(5);
            srno.setPaddingBottom(5);
            srno.setHorizontalAlignment(Element.ALIGN_CENTER);
            srno.setVerticalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell services = new PdfPCell(new Paragraph("Services"));
            services.setBackgroundColor(BaseColor.ORANGE);
            services.setBorderColor(BaseColor.BLACK);
            services.setPaddingTop(5);
            services.setPaddingBottom(5);
            services.setHorizontalAlignment(Element.ALIGN_CENTER);
            services.setVerticalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell qty = new PdfPCell(new Paragraph("Qty"));
            qty.setBackgroundColor(BaseColor.ORANGE);
            qty.setBorderColor(BaseColor.BLACK);
            qty.setPaddingTop(5);
            qty.setPaddingBottom(5);
            qty.setHorizontalAlignment(Element.ALIGN_CENTER);
            qty.setVerticalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell unit = new PdfPCell(new Paragraph("Unit Amt."));
            unit.setBackgroundColor(BaseColor.ORANGE);
            unit.setBorderColor(BaseColor.BLACK);
            unit.setPaddingTop(5);
            unit.setPaddingBottom(5);
            unit.setHorizontalAlignment(Element.ALIGN_CENTER);
            unit.setVerticalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell amount = new PdfPCell(new Paragraph("Amount"));
            amount.setBackgroundColor(BaseColor.ORANGE);
            amount.setBorderColor(BaseColor.BLACK);
            amount.setPaddingTop(5);
            amount.setPaddingBottom(5);
            amount.setHorizontalAlignment(Element.ALIGN_CENTER);
            amount.setVerticalAlignment(Element.ALIGN_CENTER);
            
            BillTable.addCell(srno);
            BillTable.addCell(services);
            BillTable.addCell(qty);
            BillTable.addCell(unit);
            BillTable.addCell(amount);
        
            for (int i = 0; i < BillingTable.getRowCount(); i++) {
                String srn = Integer.toString(i+1);
                String SERVICE = BillingTable.getValueAt(i, 0).toString();
                String QTY = BillingTable.getValueAt(i, 1).toString();
                String UNIT = BillingTable.getValueAt(i, 2).toString();
                String Amt = BillingTable.getValueAt(i, 4).toString();
                BillTable.addCell(srn);
                BillTable.addCell(SERVICE);
                BillTable.addCell(QTY);
                BillTable.addCell(UNIT);
                BillTable.addCell(Amt);
            }
            
            /*PdfPCell discount = new PdfPCell(new Paragraph("Discount"));
           // discount.setBackgroundColor(BaseColor.GRAY);
            discount.setBorderColor(BaseColor.BLACK);
            discount.setPaddingTop(5);
            discount.setPaddingBottom(5);
            discount.setHorizontalAlignment(Element.ALIGN_CENTER);
            discount.setVerticalAlignment(Element.ALIGN_CENTER);
            discount.setColspan(4);*/
            
           // PdfPCell discountAmt = new PdfPCell(new Paragraph(TotalDiscount2.getText()));
           /* discountAmt.setBackgroundColor(BaseColor.GREEN);
            discountAmt.setBorderColor(BaseColor.BLACK);
            discountAmt.setPaddingTop(5);
            discountAmt.setPaddingBottom(5);
            discountAmt.setHorizontalAlignment(Element.ALIGN_CENTER);
            discountAmt.setVerticalAlignment(Element.ALIGN_CENTER);*/
            
            PdfPCell total = new PdfPCell(new Paragraph("Total"));
           // total.setBackgroundColor(BaseColor.GRAY);
            total.setBorderColor(BaseColor.BLACK);
            //total.setPaddingTop(5);
            //total.setPaddingBottom(5);
            total.setHorizontalAlignment(Element.ALIGN_CENTER);
            total.setVerticalAlignment(Element.ALIGN_CENTER);
            total.setColspan(4);
            
            PdfPCell totalAmt = new PdfPCell(new Paragraph(AllTotal.getText()));
           /* totalAmt.setBackgroundColor(BaseColor.GREEN);
            totalAmt.setBorderColor(BaseColor.BLACK);
            totalAmt.setPaddingTop(5);
            totalAmt.setPaddingBottom(5);
            totalAmt.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalAmt.setVerticalAlignment(Element.ALIGN_CENTER);*/
            
            BillTable.addCell(total);
            BillTable.addCell(totalAmt);
           // BillTable.addCell(discount);
           // BillTable.addCell(discountAmt);
            
            
            
            
            
            Paragraph custmer = new Paragraph();
            Font fnt2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, WIDTH, BaseColor.BLACK);
            custmer.setFont(fnt2);
            custmer.setIndentationLeft(85); // title left side space
            custmer.setIndentationRight(100);
            custmer.add("Customer Name :- "+name);
            
            Paragraph billdate = new Paragraph();
            billdate.setFont(fnt2);
            billdate.setIndentationLeft(85); // title left side space
            //billdate.setIndentationRight(100);
           // billdate.setLeading(15);
            billdate.add("Bill Date :- "+dateChooserCombo3.getText());
            
           /* Paragraph billdate = new Paragraph();
            billdate.setFont(fnt2);
            billdate.setIndentationLeft(300); // title left side space
            billdate.setIndentationRight(80);
            billdate.setLeading(-15);
            billdate.add("Bill Date :- "+dateChooserCombo3.getText());*/
            
            Paragraph billno = new Paragraph();
            billno.setFont(fnt2);
            billno.setIndentationLeft(300); // title left side space
            billno.setIndentationRight(80);
            billno.setLeading(-0);
            
            billno.add("Bill No :- "+billNOjTextField1.getText());
            
            Paragraph disctAt = new Paragraph();
            disctAt.setFont(fnt2);
            disctAt.setIndentationLeft(85); // title left side space
            disctAt.setIndentationRight(80);
            //disct.setLeading(0);
            disctAt.add("Total Discount = "+TotalDiscount2.getText()+"                     Grand Total = "+AllTotal.getText());
            
            Paragraph Signature = new Paragraph();
            Signature.setFont(fnt2);
            Signature.setIndentationLeft(85); // title left side space
            Signature.setIndentationRight(100);
            Signature.setLeading(20);
            Signature.add("\n\nSign or Stamp =  _________________________________");
            
            doc.add(img);
            doc.add(title);
            doc.add(discription);
            doc.add(line);
            doc.add(voidp);
            doc.add(contactdetails);
            doc.add(phoneEmail);
            doc.add(line1);
            doc.add(voidp);
            doc.add(custmer);
            //doc.add(custmername);
            doc.add(billdate);
            doc.add(billno);
            doc.add(voidp);
            doc.add(BillTable);
            doc.add(voidp);
            doc.add(disctAt);
            doc.add(Signature);
            
            doc.close();
            //JOptionPane.showMessageDialog(this, "pdf created and saved");
           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        
        
        
        
    }
//here is code for view pdf in panel
    public void openpdf(String name,String billnos){
    try {
        String file = "C:\\Users\\Admin\\Documents\\pdfcreater\\"+name+billnos+".pdf";
           SwingController control=new SwingController();
            SwingViewBuilder factry=new SwingViewBuilder(control);
            BillSheet=factry.buildViewerPanel();
            ComponentKeyBinding.install(control, BillSheet);
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                    control.getDocumentViewController()));
                   control.openDocument(file);
        billscroll.setViewportView(BillSheet);  
        } catch (Exception ex) {
        }
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ADD;
    private javax.swing.JLabel Account;
    private javax.swing.JTextField AddPriceField;
    private javax.swing.JButton AddReset;
    private javax.swing.JTextField AddServiceField;
    private javax.swing.JPanel AddSpan;
    private javax.swing.JPanel AddSpan1;
    private javax.swing.JPanel AddSpan2;
    private javax.swing.JPanel AddSpan3;
    private javax.swing.JPanel AddSpan4;
    private javax.swing.JPanel AddSpan5;
    private javax.swing.JTextArea AddTextArea;
    private javax.swing.JLabel AddressL;
    private javax.swing.JTextArea AddressTA;
    private javax.swing.JTextArea Addresstf;
    private javax.swing.JTextField AllTotal;
    private javax.swing.JLabel ApDate;
    private javax.swing.JLabel ApTime;
    private javax.swing.JPanel AppointPanel;
    private javax.swing.JScrollPane AppointScroll;
    private javax.swing.JButton Appoint_bt;
    private javax.swing.JPanel AppointmentDTab;
    private javax.swing.JTable AppointmentTable;
    private javax.swing.JButton BACK;
    private javax.swing.JButton BServiceAdd;
    private javax.swing.JButton BServiceDel;
    private javax.swing.JLabel BarberName;
    private javax.swing.JTextField BarberNameField;
    private javax.swing.JLabel BillDate;
    private javax.swing.JLabel BillDate1;
    private javax.swing.JLabel BillDate2;
    private javax.swing.JLabel BillNo;
    private javax.swing.JPanel BillSheet;
    private javax.swing.JPanel BillingDTab;
    private javax.swing.JPanel BillingPanel;
    private javax.swing.JTable BillingTable;
    private javax.swing.JLabel CName;
    private javax.swing.JTextField CNameTF;
    private javax.swing.JButton Cancel;
    private javax.swing.JLabel ChangeName;
    private javax.swing.JLabel ChangePrice;
    private javax.swing.JLabel Column1;
    private javax.swing.JLabel Column2;
    private javax.swing.JLabel Column3;
    private javax.swing.JLabel Column4;
    private javax.swing.JLabel Column5;
    private javax.swing.JLabel ConfirmPassword;
    private javax.swing.JPasswordField ConfirmPasswordTextfield;
    private javax.swing.JLabel Contact;
    private javax.swing.JLabel ContactNumebe;
    private javax.swing.JTextField ContactTF;
    private javax.swing.JTextField ContactTextfield;
    private javax.swing.JLabel Contactsupp;
    private javax.swing.JLabel CostL;
    private javax.swing.JTextField CostTf;
    private javax.swing.JTextField CustomerNameop;
    private javax.swing.JLabel CustumerN;
    private javax.swing.JLabel CustumerN1;
    private javax.swing.JTextField CustumerjTextField;
    private javax.swing.JButton DELETE;
    private javax.swing.JButton DelSupp;
    private javax.swing.JTextField DeleteEmployee1;
    private javax.swing.JButton DeleteEmployee_btn1;
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JTextField DeleteProductTF;
    private javax.swing.JTextField DeleteService;
    private javax.swing.JPanel DeleteSpan;
    private javax.swing.JPanel DeleteSpan1;
    private javax.swing.JButton Deleteservice_btn;
    private javax.swing.JTextField Deltf;
    private javax.swing.JTextField DescriptionTF;
    private javax.swing.JPanel DetailPanel;
    private javax.swing.JPanel DetailPanel1;
    private javax.swing.JPanel Developer;
    private javax.swing.JLabel Developers;
    private javax.swing.JLabel Discount;
    private javax.swing.JLabel Discount1;
    private javax.swing.JLabel EQuantity;
    private javax.swing.JLabel EdditAdd;
    private javax.swing.JLabel EditC;
    private javax.swing.JTextField EditCost;
    private javax.swing.JTextField EditCtf;
    private javax.swing.JTextField EditPriceF;
    private javax.swing.JButton EditProduct;
    private javax.swing.JButton EditSer;
    private javax.swing.JTextField EditService;
    private javax.swing.JTextField EditServiceNF;
    private javax.swing.JPanel EditSpan;
    private javax.swing.JPanel EditSpan1;
    private javax.swing.JPanel EditSpan2;
    private javax.swing.JButton EditSupp;
    private javax.swing.JLabel EditSupplier;
    private javax.swing.JTextField EditSupplirttf;
    private javax.swing.JLabel Edit_User;
    private javax.swing.JLabel EditcodeL;
    private javax.swing.JTextField EditcodeTF;
    private javax.swing.JLabel EditnameL;
    private javax.swing.JTextField EditnameTF;
    private javax.swing.JLabel Email;
    private javax.swing.JLabel EmailCust;
    private javax.swing.JLabel EmailCust1;
    private javax.swing.JTextField EmailCustjTextField1;
    private javax.swing.JTextField EmailCustjTextField2;
    private javax.swing.JTextField EmailTF;
    private javax.swing.JLabel Emp_Tablel2;
    private javax.swing.JButton Emp_searchbt;
    private javax.swing.JLabel Emp_searchlable;
    private javax.swing.JTable Emp_table;
    private javax.swing.JPanel EmployeeDTab;
    private javax.swing.JLabel Employee_l1;
    private javax.swing.JScrollPane EmpolyeeScroll;
    private javax.swing.JLabel EpriceL;
    private javax.swing.JTextField EpriceTF;
    private javax.swing.JTextField EquantityTF;
    private javax.swing.JPanel FinalBillPanal;
    private javax.swing.JLabel Gender;
    private javax.swing.JPanel HomeTab;
    private javax.swing.JLabel IMAGE;
    private javax.swing.JLabel Look;
    private javax.swing.JTextField MailTextfield;
    private javax.swing.JButton Message;
    private javax.swing.JPanel MessageDTab;
    private javax.swing.JPanel MessagePanel;
    private javax.swing.JScrollPane MessageScroll;
    private javax.swing.JLabel Mobile;
    private javax.swing.JLabel Mobile1;
    private javax.swing.JLabel MobileNo;
    private javax.swing.JTextField MobilejTextField;
    private javax.swing.JTextField MobilejTextField1;
    private javax.swing.JTextArea Msg;
    private javax.swing.JTable MsgTable;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel NameL;
    private javax.swing.JTextField NameTF;
    private javax.swing.JTextField NameTextfield;
    private javax.swing.JPanel NandMpan;
    private javax.swing.JButton Notification;
    private javax.swing.JTextArea NotificationText;
    private javax.swing.JPanel Opoinment;
    private javax.swing.JPanel OtherDTab;
    private javax.swing.JTextField Pass_Tf;
    private javax.swing.JLabel Password;
    private javax.swing.JPasswordField PasswordTextfield;
    private javax.swing.JLabel PeriodLable;
    private javax.swing.JLabel PeriodLable1;
    private javax.swing.JLabel PhoneL;
    private javax.swing.JTextField PhoneTF;
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
    private javax.swing.JButton Registrationbtn;
    private javax.swing.JPanel RegistryPanel;
    private javax.swing.JPanel Report;
    private javax.swing.JComboBox<String> ReportCombo;
    private javax.swing.JPanel ReportDTab;
    private javax.swing.JTextField ReportNameTextFielad;
    private javax.swing.JPanel ReportScreen;
    private javax.swing.JScrollPane ReportScroll;
    private javax.swing.JPanel ReportSearchPanel;
    private javax.swing.JTable ReportTable;
    private javax.swing.JPanel Reportpan;
    private javax.swing.JPanel Reportpanofpan;
    private javax.swing.JButton ReseT;
    private javax.swing.JButton Reset;
    private javax.swing.JButton ResetBill;
    private javax.swing.JButton ResetDefault;
    private javax.swing.JButton ResetEdit;
    private javax.swing.JButton ResetProduct;
    private javax.swing.JButton ResetSupp;
    private javax.swing.JLabel SEmail;
    private javax.swing.JTextField SEmailTF;
    private javax.swing.JLabel S_Pricce;
    private javax.swing.JLabel S_Pricce6;
    private javax.swing.JButton Sadd;
    private javax.swing.JPanel SalonDTab;
    private javax.swing.JPanel SalonDTab1;
    private javax.swing.JTextField SalonNametf;
    private javax.swing.JButton Save;
    private javax.swing.JButton SaveasPDF;
    private javax.swing.JTextField Search;
    private javax.swing.JButton SearchCust;
    private javax.swing.JButton SearchCust1;
    private javax.swing.JButton SearchIteam;
    private javax.swing.JButton SearchKey;
    private javax.swing.JLabel SearchNameReport;
    private javax.swing.JLabel SearchReortLab;
    private javax.swing.JButton SearchReport;
    private javax.swing.JButton SearchTable;
    private javax.swing.JButton Searchbtn;
    private javax.swing.JButton Send1;
    private javax.swing.JLabel ServicT;
    private javax.swing.JLabel ServicT1;
    private javax.swing.JLabel ServicT10;
    private javax.swing.JLabel Service;
    private javax.swing.JPanel ServiceCPanel;
    private javax.swing.JPanel ServiceDTab2;
    private javax.swing.JTextField ServiceDiscount;
    private javax.swing.JLabel ServiceOperation;
    private javax.swing.JLabel ServiceOperation1;
    private javax.swing.JLabel ServiceOperation2;
    private javax.swing.JPanel ServicePanel;
    private javax.swing.JScrollPane ServiceScroll1;
    private javax.swing.JTextField ServiceSearch;
    private javax.swing.JLabel ServiceT2;
    private javax.swing.JTable ServiceTable;
    private javax.swing.JLabel ServiceTableLAble;
    private javax.swing.JLabel ServiceTableLAble1;
    private javax.swing.JLabel ServiceTableLAble2;
    private javax.swing.JTextField ServiceTotal;
    private javax.swing.JLabel SnameL;
    private javax.swing.JTextField SnameTF;
    private javax.swing.JScrollPane SolonDScroll;
    private javax.swing.JButton Sreset;
    private javax.swing.JButton Submit;
    private javax.swing.JTextField SuppEditM;
    private javax.swing.JLabel Suppliemaail;
    private javax.swing.JTextField SupplierCN;
    private javax.swing.JPanel SupplierDtab;
    private javax.swing.JLabel SupplierL;
    private javax.swing.JScrollPane SupplierScroll1;
    private javax.swing.JTextField SupplierTF;
    private javax.swing.JTable SupplierTable;
    private javax.swing.JLabel Surname;
    private javax.swing.JTextField SurnameTextfield;
    private javax.swing.JTextField TO;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel Total1;
    private javax.swing.JTextField TotalDiscount2;
    private javax.swing.JTextField UnitPrice;
    private javax.swing.JLabel UserId;
    private javax.swing.JTextField UserIdTextfield;
    private javax.swing.JLabel UserType;
    private javax.swing.JComboBox<String> UserTypeCom;
    private javax.swing.JComboBox<String> UserTypeCom1;
    private javax.swing.JLabel Welcome;
    private javax.swing.JLabel addrees;
    private javax.swing.JButton addservice_btn;
    private javax.swing.JLabel adminpic;
    private javax.swing.JLabel bill;
    private javax.swing.JTextField billNOjTextField1;
    private javax.swing.JButton billing_bt;
    private javax.swing.JScrollPane billscroll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupMaleFemale;
    private javax.swing.JPanel buttonpanel;
    private javax.swing.JLabel c11;
    private javax.swing.JLabel c12;
    private javax.swing.JLabel c14;
    private javax.swing.JLabel c15;
    private javax.swing.JLabel clock;
    private javax.swing.JLabel codeL;
    private javax.swing.JTextField codeTF;
    private javax.swing.JLabel confirm_pswd;
    private javax.swing.JButton credit_btn;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private datechooser.beans.DateChooserCombo dateChooserCombo4;
    private datechooser.beans.DateChooserCombo datefrom;
    private javax.swing.JButton deltail_bt;
    private javax.swing.JLabel description;
    private javax.swing.JLabel developed;
    private javax.swing.JButton editEmployee_btn1;
    private javax.swing.JButton editservice_srh_btn;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField email_Tf;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JLabel emp_Address;
    private javax.swing.JButton emp_bt;
    private javax.swing.JLabel emp_conctact;
    private javax.swing.JLabel emp_dellebal;
    private javax.swing.JLabel emp_email;
    private javax.swing.JLabel emp_first;
    private javax.swing.JLabel emp_gender;
    private javax.swing.JLabel emp_last;
    private javax.swing.JLabel emp_pass;
    private javax.swing.JTextField emp_textF1;
    private javax.swing.JTextField emp_textF2;
    private javax.swing.JTextField emp_textF3;
    private javax.swing.JTextField emp_textF4;
    private javax.swing.JPanel employepan;
    private javax.swing.JRadioButton female;
    private javax.swing.JRadioButton female_rbt;
    private javax.swing.JPanel homepanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JButton logout;
    private javax.swing.JPanel mainEmployeepan1;
    private javax.swing.JPanel mainmsgpan;
    private javax.swing.JPanel mainproductpan;
    private javax.swing.JPanel mainproductpan1;
    private javax.swing.JPanel mainservicepan;
    private javax.swing.JRadioButton male;
    private javax.swing.JRadioButton male_rbt;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message1;
    private javax.swing.JLabel modelicon;
    private javax.swing.JPanel mpl;
    private javax.swing.JLabel name;
    private javax.swing.JLabel nameDel;
    private javax.swing.JPanel otherpan;
    private javax.swing.JScrollPane otherscroll;
    private javax.swing.JLabel passLabel;
    private javax.swing.JLabel pname;
    private javax.swing.JLabel popUPservice;
    private javax.swing.JButton printTable;
    private javax.swing.JButton product_bt;
    private javax.swing.JTextField productnameTF;
    private javax.swing.JButton report_bt;
    private javax.swing.JButton reset;
    private javax.swing.JButton reset_bt;
    private javax.swing.JLabel row1;
    private javax.swing.JLabel row2;
    private javax.swing.JLabel row3;
    private javax.swing.JLabel row4;
    private javax.swing.JLabel row5;
    private javax.swing.JButton searchCN;
    private javax.swing.JTextField search_Tf;
    private javax.swing.JButton service_bt;
    private javax.swing.JButton supplier_bt;
    private javax.swing.JLabel to;
    private datechooser.beans.DateChooserCombo todate;
    private javax.swing.JLabel unit;
    private javax.swing.JLabel user;
    private javax.swing.JTextField user_Tf;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
