/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsMayjors_DAL {
    SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet LoadNganh() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteQuery("Select * from NGANH");
    }
    
    public ResultSet fLoadInfoMayjors_idFaculty(clsMayjors_Public p) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LAYTTNGANH_TENNGANH(?)}");
        cabCmd.setString(1, p.getIdFaculty());
        return connect.excuteStore_Para(cabCmd);
        
    }
    
    public ResultSet LayTTNganh() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayTTNganh}");
    }
    
     public int ThemNganh(clsMayjors_Public p) throws Exception
    {
        connect = new SQLServerConnector();        
        // Insert voi Store Procedure co tham so
        String strCall = "{call ThemNganh(?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdMayjors());
        cabCmd.setString(2, p.getTenNganh());
        cabCmd.setString(3, p.getIdFaculty());
        cabCmd.setString(4, Integer.toString(p.getNoSemester()));
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int CapNhatNganh(clsMayjors_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatNganh(?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdMayjors());
        cabCmd.setString(2, p.getTenNganh());
        cabCmd.setString(3, p.getIdFaculty());
        cabCmd.setString(4, Integer.toString(p.getNoSemester()));
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int XoaNganh(clsMayjors_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call XoaNganh(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdMayjors());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
