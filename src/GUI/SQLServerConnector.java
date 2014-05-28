/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class SQLServerConnector {

    String server = "";                 // Ten Server.
    int portNumber = Integer.MIN_VALUE;
    String userName = "";               // UserName SQL
    String password = "";               // Password 
    String databaseName = "";           // Ten CSDL

    Connection connect = null;          // Khoi tao connector
    PreparedStatement preState = null;
    Statement statement = null;         // Khoi tao statement thuc thi cau lenh sql
    CallableStatement callableSta = null; // Khoi tao callableStatement
    ResultSet result = null;            // Khoi tao ResultSet de chua du lieu sau khi thuc thi cau lenh sql

    // Ham khoi tao.
    public SQLServerConnector(String Server, int PortNumber, String UserName, String Password, String DatabaseName) {
        this.server = Server;
        this.portNumber = PortNumber;
        this.userName = UserName;
        this.password = Password;
        this.databaseName = DatabaseName;
    }

    // Ham kiem tra driver co ton tai hay k
    protected void driverTest() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("SQLServer JDBC Driver not found.");
        }
    }

    protected Connection getConnect() throws Exception {
        // Kiem tra connect da ton tai hay chua
        if (this.connect == null) {
            driverTest();
            try {
                // Tao Connection thong qua DataSource
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName(this.server);
                ds.setPortNumber(this.portNumber);
                ds.setDatabaseName(this.databaseName);
                ds.setUser(this.userName);
                ds.setPassword(this.password);
                this.connect = ds.getConnection();
            } catch (SQLServerException e) {
                throw new Exception("Khong the ket noi den Database Server. " + e.getSQLState());
            }
        }
        return this.connect;
    }

    // Tao Statement de thuc thi cau Query
    protected Statement getStatement() throws SQLException, Exception {
        // Kiem tra statement co null hoac da bi dong hay chua
        if (this.statement == null ? true : this.statement.isClosed()) {
            this.statement = this.getConnect().createStatement();
        }
        // Tra Statement ra ngoai.
        return this.statement;
    }

    // Tao CallableStatement de thu thi StoreProcedure
    protected CallableStatement getCallableStatement(String sql) {
        try {
            if (this.callableSta == null ? true : this.callableSta.isClosed()) {
                this.callableSta = this.getConnect().prepareCall(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.callableSta;
    }
    // Tao Prepare Statement de thuc thi cau lenh SQL voi tham so
    protected PreparedStatement getPrepareStatement(String sql) throws SQLException, Exception
    {
        // Kiem tra statement co null hoac da bi dong hay chua
        if (this.preState == null ? true : this.preState.isClosed()) {
            this.preState = this.getConnect().prepareStatement(sql);
        }
        // Tra Statement ra ngoai.
        return this.preState;
    }
    // Ham thuc thi cau lenh Select de lay du lieu
    public ResultSet excuteQuery(String Query) throws Exception {
        try {
            this.result = getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        return this.result;
    }

    ////////////////// Ham thuc thi cau lenh bang store procedure
    public ResultSet excuteStore(String Store) throws Exception {
        try {
            this.result = this.getConnect().prepareCall(Store).executeQuery();
        } catch (Exception e) {
            throw new Exception("Error: " + JOptionPane.showConfirmDialog(null, e.getMessage(), "Lỗi", 1));
        }
        return this.result;
    }
    //// Ham Insert, Update, Delete voi Store Procedure
    public int excuteUpdateStore(String Store) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            //Thuc thi cau lenh
            res = getCallableStatement(Store).executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error: " + e.getMessage());
        } // Sau khi thuc hien cau lenh se tien hanh dong ket noi.
        finally {
            this.Close();
        }
        return res;
    }
    
    //////// Ham thuc thi cau lenh bang Store Procedure voi tham so
    public ResultSet excuteStore_Para(CallableStatement state)
    {
        try {
            this.result = state.executeQuery();
        } catch (SQLException e) {
        }
        return this.result;
    }
    
    ///// Ham thuc thi Insert, Update, Delete voi Store Procedure co tham so
    public int excuteUpdateStorePara(CallableStatement state) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            //Thuc thi cau lenh
            res = state.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error: " + e.getMessage());
        } // Sau khi thuc hien cau lenh se tien hanh dong ket noi.
        finally {
            this.Close();
        }
        return res;
    }
    
    // Ham thuc thi cac cau lenh Insert, Update, Delete binh thuong
    public int excuteUpdate(String Query) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            //Thuc thi cau lenh
            res = getStatement().executeUpdate(Query);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        } // Sau khi thuc hien cau lenh se tien hanh dong ket noi.
        finally {
            this.Close();
        }
        return res;
    }

    public void Close() throws SQLException {
        // Kiem tra ResultSet
        if (this.result != null && this.result.isClosed()) {
            this.result.close();
            this.result = null;
        }
        // Kiem tra Statement
        if (this.statement != null && this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        // Kiem tra connection
        if (this.connect != null && this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }
    }
}
