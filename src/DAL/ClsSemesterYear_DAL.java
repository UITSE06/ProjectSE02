/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import PUBLIC.*;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsSemesterYear_DAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadSemesterAndFirstYear(ClsSemesterYearPublic p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayHocKiVaNam1(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setInt(1, p.getSemester());
        cabCmd.setInt(2, p.getFirstYear());
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet GetIdSemesterYear(ClsSemesterYearPublic p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayMaHKNHTheoHocKiVaNam1(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setInt(1, p.getSemester());
        cabCmd.setInt(2, p.getFirstYear());
        return connect.excuteStore_Para(cabCmd);
    }

    public int InsertSemesterYear(ClsSemesterYearPublic p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call ThemHocKiNamHoc(?,?,?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdSemesterYear());
        cabCmd.setInt(2, p.getSemester());
        cabCmd.setInt(3, p.getFirstYear());
        cabCmd.setInt(4, p.getSecondYear());
        cabCmd.setDate(5, p.getBeginRegister());
        cabCmd.setDate(6, p.getRegisterDeadline());
        cabCmd.setDate(7, p.getGetFeeDeadline());
        cabCmd.setDate(8, p.getReduceDeadline());
        cabCmd.setInt(9, p.getReducePercent());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public ResultSet LoadSemesterYears() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("LayHocKiNamHoc");
    }

    public int InsertUpdateSemesterYear(ClsSemesterYearPublic p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call HocKiNamHoc_InsertUpdate(?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdSemesterYear());
        cabCmd.setInt(2, p.getFirstYear());
        cabCmd.setInt(3, p.getSecondYear());
        cabCmd.setInt(4, p.getSemester());
        cabCmd.setDate(5, (Date) p.getRegisterDeadline());
        cabCmd.setDate(6, (Date) p.getReduceDeadline());
        cabCmd.setDate(7, (Date) p.getGetFeeDeadline());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    /// Thanh Thai
    public ResultSet LoadFirstYear() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{call HocKiNamHoc_LayNam1}");
    }
    
    public ResultSet GetDeadlineToRegister(ClsSemesterYearPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        String strCall = "{call LayHanDangKyTheoHocKiVaNam1(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setInt(1, p.getSemester());
        cabCmd.setInt(2, p.getFirstYear());
        return connect.excuteStore_Para(cabCmd);
    }
}
