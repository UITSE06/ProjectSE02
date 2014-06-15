/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.ClsHumanSubjects_Public;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class ClsHumanSubjects_DAL {
    private SQLServerConnector connect;
    
    public ResultSet LoadHumanSubjects() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadDT}");
    }
    public int InsertHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
    connect = new SQLServerConnector();
        String strCall = "{call ThemDoiTuong(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaDoiTuong());
        cabCmd.setString(2, p.getTenDoiTuong());
        cabCmd.setInt(3, p.getTiLeGiamHP());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int UpdateHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
    connect = new SQLServerConnector();
        String strCall = "{call CapNhatDoiTuong(?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaDoiTuong());
        cabCmd.setString(2, p.getTenDoiTuong());
        cabCmd.setInt(3, p.getTiLeGiamHP());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int DeleteHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
    connect = new SQLServerConnector();
        String strCall = "{call XoaDoiTuong(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaDoiTuong());       
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
