/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.NganhPublic;
import java.sql.CallableStatement;
import java.sql.ResultSet;
/**
 *
 * @author John
 */
public class NganhDAL {
    private SQLServerConnector connect;
    
    public ResultSet LayTTNganh() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayTTNganh}");
    }
    
     public int ThemNganh(NganhPublic p) throws Exception
    {
        connect = new SQLServerConnector();        
        // Insert voi Store Procedure co tham so
        String strCall = "{call ThemNganh(?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaNganh());
        cabCmd.setString(2, p.getTenNganh());
        cabCmd.setString(3, p.getMaKhoa());
        cabCmd.setString(4, Integer.toString(p.getSoHK()));
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int CapNhatNganh(NganhPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatNganh(?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaNganh());
        cabCmd.setString(2, p.getTenNganh());
        cabCmd.setString(3, p.getMaKhoa());
        cabCmd.setString(4, Integer.toString(p.getSoHK()));
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int XoaNganh(NganhPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call XoaNganh(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaNganh());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
