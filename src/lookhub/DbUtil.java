package lookhub;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
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

    static void runQueryforEdit(String update_product_set_ProductName__price__Pr, JTextField EditnameTF, JTextField EpriceTF, JTextField EditcodeTF, JTextField EquantityTF, JTextField SnameTF, JTextField EditCost) {
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
    public static void runQueryforDelete(String query,JTextField textField) throws SQLException {
        Connection con = loadDriver();
        ResultSet rs;
        PreparedStatement st = con.prepareStatement(query);
        st.setString(1, textField.getText());
        st.executeUpdate();
    }
}