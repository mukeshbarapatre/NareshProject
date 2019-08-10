package lookhub;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Database object to load drivers and perform queries
 * @author Abdulsalam Umar blog.salamtura.com
 */
public class DbUtil {

    private static Connection con;
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String ConnectionString = "jdbc:mysql://localhost:3306/proper";
    private static final String user = "root";
    private static final String pwd = "";

    static void runQueryforEditSupplier(String update_supplier_set_CompanyName__Address_, JTextField EditSupplirttf, JTextArea AddTextArea, JTextField SupplierCN, JTextField SuppEditM, JTextField EditCtf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * create Database object
     */
    public DbUtil() {
    }

    /**
     * to load the database base driver
     * @return a database connection
     * @throws SQLException throws an exception if an error occurs
     */
    public static Connection loadDriver() throws SQLException {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        con = DriverManager.getConnection(ConnectionString, user, pwd);
        return con;
    }

    /**
     * to get a result set of a query
     * @param query custom query
     * @return a result set of custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static ResultSet getResultSet(String query) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        rs = st.executeQuery();

        return rs;
    }

    /**
     * to run an update query such as update, delete
     * @param query custom query
     * @throws SQLException throws an exception if an error occurs
     */
    public static void runQuery(String query) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.executeUpdate();
    }
    public static ResultSet getResultSetForSearch(String query,JTextField textField) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        rs = st.executeQuery();

        return rs;
    }
    public static void runQueryforEdit(String query,JTextField textField,JTextField textField1,JLabel textField2) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setInt(2, Integer.parseInt(textField1.getText()));
        st.setString(3, textField2.getText());
        st.executeUpdate();
    }
    public static void runQueryforEditCustomer(String query,JTextField textField,Date sqlDate,JTextField textField2) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setDouble(1, Double.parseDouble(textField.getText()));
        st.setDate(2, sqlDate);
        st.setString(3, textField2.getText());
        st.executeUpdate();
    }
    public static void runQueryforEditProduct(String query,JTextField textField,JTextField textField1,JTextField textField2,JTextField textField3,JTextField textField4,JTextField textField5,JTextField textField6) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setInt(2, Integer.parseInt(textField1.getText()));
        st.setInt(3, Integer.parseInt(textField2.getText()));
        st.setInt(4, Integer.parseInt(textField3.getText()));
        st.setString(5, textField4.getText());
        st.setInt(6, Integer.parseInt(textField5.getText()));
        st.setString(7, textField6.getText());
        
        
        st.executeUpdate();
    }
    public static void runQueryforEditSupplie(String query,JTextField textField,JTextArea textArea1,JTextField textField1,JTextField textField2,JTextField textField3) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setString(2, textArea1.getText());
        st.setLong(3,Long.parseLong(textField1.getText()));
        st.setString(4,textField2.getText());
        st.setString(5,textField3.getText());
        
        
        st.executeUpdate();
    }
    public static ResultSet getResultSetForbillinReport(String query,String from,String to) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, from);
        st.setString(2, to);
        rs=st.executeQuery();
        return rs;
        
    }
    public static void runQueryforEditEmployee(String query,JTextField textField,JTextField textField1,JTextField textField2,JTextField textField3,String string,JTextField textField4,JTextField textField5,JTextField textField6,String string1,JTextField textField7) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setString(2, textField1.getText());
        st.setLong(3,Long.parseLong(textField2.getText()));
        st.setString(4, textField3.getText());
        st.setString(5, string);
        st.setString(6, textField4.getText());
        st.setString(7, textField5.getText());
        st.setString(8, textField6.getText());
        st.setString(9, string1);
        st.setString(10, textField7.getText());
        
        
        st.executeUpdate();
    }
    public static void runQueryforDelete(String query,JTextField textField) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.executeUpdate();
    }
    public static ResultSet runQueryforLogin(String query,JTextField textField,JTextField textField1,String type) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setString(2, textField1.getText());
        st.setString(3, type);
        rs=st.executeQuery();
        return rs;
    }
        public static ResultSet runQueryforCheck(String query,JTextField textField,JTextField textField1,String type) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.setLong(2, Long.parseLong(textField1.getText()));
        st.setString(3, type);
        rs=st.executeQuery();
        return rs;
    }
}