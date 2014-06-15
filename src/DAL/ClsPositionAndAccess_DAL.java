/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import PUBLIC.*;

/**
 *
 * @author John
 */
public class ClsPositionAndAccess_DAL {
    SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet LoadPosition() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteQuery("Select * from CHUCVU");
    }
    
    public int InsertPosition(ClsPositionAndAccess_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call ThemChucVu(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);        
        cabCmd.setString(1, p.getIdPosition());
        cabCmd.setString(2, p.getNamePosition());
        cabCmd.setString(3, p.getIdSetAccess());       
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int UpdatePosition(ClsPositionAndAccess_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatChucVu(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);        
        cabCmd.setString(1, p.getIdPosition());
        cabCmd.setString(2, p.getNamePosition());
        cabCmd.setString(3, p.getIdSetAccess());       
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int DeletePosition(ClsPositionAndAccess_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call XoaChucVu(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);        
        cabCmd.setString(1, p.getIdPosition());      
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
