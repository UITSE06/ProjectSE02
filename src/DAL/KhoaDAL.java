/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.KhoaPublic;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class KhoaDAL {
    private SQLServerConnector connect;
    
    public int ThemKhoa(KhoaPublic p) throws Exception
    {
        connect = new SQLServerConnector();        
        // Insert voi Store Procedure co tham so
        String strCall = "{call ThemKhoa(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaKhoa());
        cabCmd.setString(2, p.getTenKhoa());
        cabCmd.setString(3, p.getVietTat());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int CapNhatKhoa(KhoaPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatKhoa(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaKhoa());
        cabCmd.setString(2, p.getTenKhoa());
        cabCmd.setString(3, p.getVietTat());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet LayTTKhoa() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayTTKhoa_TenKhoa}");
    }
    
    public int XoaKhoa(KhoaPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call XoaKhoa(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaKhoa());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
