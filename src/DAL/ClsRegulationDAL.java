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
 * @author John
 */
public class ClsRegulationDAL {
    SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet LoadRegulation() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayThamSo}");
    }
    
    public int InsertRegulation() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call ThemThamSo}");
    }
    
    public int UpdateRegulation(ClsRegulation_Public p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatThamSo(?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);        
        cabCmd.setInt(1, p.getMaxCredit());
        cabCmd.setFloat(2, p.getMinScore());
        cabCmd.setFloat(3, p.getMaxScore());
        cabCmd.setFloat(4, p.getPassScore());
        cabCmd.setInt(5, p.getMinStaffYearOld());
        cabCmd.setInt(6, p.getMaxStaffYearOld());
        cabCmd.setInt(7, p.getMinStudentYearOld());        
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
