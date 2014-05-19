/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Hung
 * Description: Import du lieu tu file excel toi jTable
 * 
 */
public class ImportExceltojTable {

    private static final String DRIVER_NAME = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DATABASE_URL = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=%s;DriverID=22;READONLY=false";

    private static final String FILEPATH = "C:/Documents and Settings/web/Desktop/Employee.xlsx";

    private static Connection con = null;
    private static Statement stmt = null;

    private Connection getConnection(File file) throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        Connection con = DriverManager.getConnection(String.format(DATABASE_URL, file.getAbsolutePath()));
        return con;
    }

    public List<String> getData(File file, String[] columns) throws SQLException {

        List<String> list = null;
        try {
            ResultSet rs = getRecord(file, "select emailid from [Sheet1$]");
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println(rsmd.getColumnCount());

            while (rs.next()) {
                if (list == null) {
                    list = new ArrayList<String>();
                }
                for (int i = 0; i < columns.length; i++) {
                    list.add(rs.getString(columns[i]));
                }
            }
            rs.close();
        } catch (Exception e) {
        } finally {
            closeConnection();
            return list;
        }
    }

    private void closeConnection() throws SQLException {
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }

        if (con != null) {
            con.close();
            con = null;
        }

    }

    ResultSet getRecord(File file, String query) throws ClassNotFoundException, SQLException {
        con = getConnection(file);
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    
    void fillData(File file){
        
    }
}
