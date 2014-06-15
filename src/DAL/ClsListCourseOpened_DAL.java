/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.CallableStatement;
import PUBLIC.*;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsListCourseOpened_DAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadIdSemesterYears(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayMaHKNH_DSMHMO(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdSemesterYears());
        return connect.excuteStore_Para(cabCmd);
    }

    public int CreateTempDSMHMO() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call createtempDSMHMO}");
    }

    public int InsertTempDSMHMO(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call insertTempDSMHMO(?,?)}");
        cabCmd.setString(1, p.getIdSemesterYears());
        cabCmd.setString(2, p.getIdCourse());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public int UpdateListCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call CapNhatDanhSachMonHocMoTheoMaHKNH(?)}");
        cabCmd.setString(1, p.getIdSemesterYears());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int UpdateDeadlineAndPercentReduce(ClsSemesterYearPublic p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call CapNhatHocKiNamHoc(?,?,?,?,?,?)}");
        cabCmd.setString(1, p.getIdSemesterYear());
        cabCmd.setDate(2, p.getRegisterDeadline());
        cabCmd.setDate(3, p.getGetFeeDeadline());
        cabCmd.setDate(4, p.getReduceDeadline());
        cabCmd.setInt(5, p.getReducePercent());
        cabCmd.setDate(6, p.getBeginRegister());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public int InsertListCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call ThemDSMHMo(?,?)}");
        cabCmd.setString(1, p.getIdSemesterYears());
        cabCmd.setString(2, p.getIdCourse());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet GetListCourseOpened(String idSemesterYear) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayDanhSachMonHocDaMo(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, idSemesterYear);
        return connect.excuteStore_Para(cabCmd);
    }
    
    public int DeleterACourse(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call HuyMotMonHoc(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdSemesterYears());
        cabCmd.setString(2, p.getIdCourse());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int DeleterAllCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call HuyTatCaMonHocTrongMotHocKi(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdSemesterYears());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet GetAllDeadline(String idSemesterYear) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayCacHanChot(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, idSemesterYear);
        return connect.excuteStore_Para(cabCmd);
    }
}
