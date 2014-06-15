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
public class ClsRegisterCourses_DAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadStudentInfo(clsStudent_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayThongTinSVDangKy(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMssv());
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet LoadListCourseToRegister(String idMayjor, String idSemesterYear) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call LayMonHocDeDangKy(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, idMayjor);
        cabCmd.setString(2, idSemesterYear);
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet InMoneyCourse(String IdCourse) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call TinhTienDangKy(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, IdCourse);
        return connect.excuteStore_Para(cabCmd);
    }

    public int SaveToRegisterForm(ClsRegisterForm_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call ThemPhieuDangKy(?,?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdRegisterForm());
        cabCmd.setString(2, p.getIdStudent());
        cabCmd.setDate(3, p.getDateOfRegister());
        cabCmd.setString(4, p.getIdStaff());
        cabCmd.setString(5, p.getIdSemesterYears());
        cabCmd.setBigDecimal(6, p.getSumOfMoneyRegister());
        cabCmd.setBigDecimal(7, p.getSumOfMoneyMustPay());
        cabCmd.setBigDecimal(8, p.getSumOfMoneyPaid());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int UpdateRegisterForm(ClsRegisterForm_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatPhieuDangKy(?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdRegisterForm());
        cabCmd.setDate(2, p.getDateOfRegister());
        cabCmd.setString(3, p.getIdStaff());
        cabCmd.setBigDecimal(4, p.getSumOfMoneyRegister());
        cabCmd.setBigDecimal(5, p.getSumOfMoneyMustPay());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public ResultSet CreateTempRegisterForm() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call createtempRegisterFormDetail}");
    }

    public int InsertTempRegisterFormDetail(ClsRegisterFormDetail_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call inserttempRegisterFormDetail(?,?,?)}");
        cabCmd.setString(1, p.getIdRegisterFormDetail());
        cabCmd.setString(2, p.getIdRegisterForm());
        cabCmd.setString(3, p.getIdCourse());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public ResultSet CheckRegistedOrNot(ClsRegisterForm_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call KiemTraDaDangKyHayChua(?,?)}");
        cabCmd.setString(1, p.getIdStudent());
        cabCmd.setString(2, p.getIdSemesterYears());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet CheckCourseRequest(String mssv, String maMonHoc) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call KiemTraMonHocTienQuyet(?,?)}");
        cabCmd.setString(1, mssv);
        cabCmd.setString(2, maMonHoc);
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet GetListCoureRegisted(ClsRegisterForm_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayDanhSachMonHocSinhVienDaDangKy(?,?)}");
        cabCmd.setString(1, p.getIdStudent());
        cabCmd.setString(2, p.getIdSemesterYears());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet GetSumOfFee(ClsRegisterForm_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayTongTienDangKy(?,?)}");
        cabCmd.setString(1, p.getIdStudent());
        cabCmd.setString(2, p.getIdSemesterYears());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet GetPercentReduceFee(String mssv) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayTiLeGiamHPCuaSinhVien(?)}");
        cabCmd.setString(1, mssv);
        return connect.excuteStore_Para(cabCmd);
    }
    
    public int CancelACourseRegisted(ClsRegisterFormDetail_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call HuyMotMonHocDaDangKy(?,?)}");
        cabCmd.setString(1, p.getIdRegisterForm());
        cabCmd.setString(2, p.getIdCourse());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int CancelAllCourseRegisted(ClsRegisterFormDetail_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call HuyTatCaMonHocDaDangKy(?)}");
        cabCmd.setString(1, p.getIdRegisterForm());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
