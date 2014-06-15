/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.clsFaculty_Public;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hung
 */
public class clsFaculty_DAL {
    SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet fLoad_NameOfFaculty() throws Exception{
    connect = new SQLServerConnector();
    //cabCmd = connect.getCallableStatement("{call LayTTKhoa_TenKhoa}");
    return connect.excuteStore("{call LayTTKhoa_TenKhoa()}");
    }
    
    public ResultSet fLOAD_LISTST_IDFACULTY(clsFaculty_Public faPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDFACULTY(?)}");
        cabCmd.setString(1, faPublic.getIdFaculty());
        return connect.excuteStore_Para(cabCmd);
    }
    
     public int ThemKhoa(clsFaculty_Public p) throws Exception
    {
        connect = new SQLServerConnector();        
        // Insert voi Store Procedure co tham so
        String strCall = "{call ThemKhoa(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdFaculty());
        cabCmd.setString(2, p.getNameOfFaculty());
        cabCmd.setString(3, p.getShortcutName());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int CapNhatKhoa(clsFaculty_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatKhoa(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdFaculty());
        cabCmd.setString(2, p.getNameOfFaculty());
        cabCmd.setString(3, p.getShortcutName());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet LayTTKhoa() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayTTKhoa_TenKhoa}");
    }
    
    public int XoaKhoa(clsFaculty_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call XoaKhoa(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdFaculty());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    //Load thong tin khoa -- ThanhThai
    public ResultSet LoadKhoa() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadKhoa}");
    }
}
