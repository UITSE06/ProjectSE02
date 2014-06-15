/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
import java.sql.CallableStatement;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsStudent_DAL {

    private SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadSV() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call Load_SV}");
    }

    public ResultSet fLoad_Year_Of_StudentApply() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_YEARSTAPPLY()}");
    }

    /**
     * @HungNgoc Date: 06/02/2014 Description: Load thong tin sinh toan truong
     * gom co: MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy
     */
    public ResultSet fLOAD_LISTSTUDENT_RP() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_LISTSTUDENT_RP()}");
    }

    /**
     * @HungNgoc Date: 06/02/2014 Description: Load thong tin sinh toan truong
     * gom co: MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao
     * la makhoa
     */
    public ResultSet LOAD_LISTST_IDMAYJORS(clsStudent_Public sPublic) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDMAYJORS(?)}");
        cabCmd.setString(1, sPublic.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }

    /**
     * @HungNgoc Date: 06/06/2014 Description: Load thong tin sinh toan truong
     * gom co: MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao
     * la manganh, year
     */
    public ResultSet LOAD_LISTST_IDMAYJORS_YEAR(clsStudent_Public sPublic) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDMAYJOR_YEAR(?,?)}");
        cabCmd.setString(1, sPublic.getIdMayjors());
        cabCmd.setString(2, sPublic.getYearOfApply());
        return connect.excuteStore_Para(cabCmd);
    }

    /**
     * @HungNgoc Date: 06/06/2014 Description: Load thong tin sinh toan truong
     * gom co: MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao
     * la makhoa, year
     */
    public ResultSet LOAD_LISTST_IDFACULTY_YEAR(clsStudent_Public stPublic) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDFACULTY_YEAR(?,?)}");
        cabCmd.setString(1, stPublic.getIdFaculty());
        cabCmd.setString(2, stPublic.getYearOfApply());
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet LOAD_LISTST_REGISTERED() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{LOAD_LISTST_REGISTERED()}");
    }

    public ResultSet LOAD_LISTST_NOTREGISTERED() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{LOAD_LISTST_NOTREGISTERED()}");
    }

    /**
     * @HungNgoc Date: 08/06/2014 Description: Load thong tin chi tiet ve viec
     * dang ky hoc phan sinh vien
     */
    public ResultSet LOA_LIST_STUDENT_DETAIL_ID(clsStudent_Public stPublic,PhieuDangKyPublic regFormPublic) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOA_LIST_STUDENT_DETAIL_ID(?,?,?,?)}");
        cabCmd.setString(1, stPublic.getIdStudent());
        cabCmd.setObject(2, regFormPublic.getNoSemeter());
        cabCmd.setObject(3, regFormPublic.getY1());
        cabCmd.setObject(4, regFormPublic.getY2());
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet LOAD_NAMEMAYJORS(clsStudent_Public stPublic) throws Exception {
       connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_NAMEMAYJORS(?)}");
        cabCmd.setString(1, stPublic.getIdStudent());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public int InsertUpdateStudent(clsStudent_Public p, int length) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_InsertUpdate(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdStudent());
        cabCmd.setString(2, p.getName());
        cabCmd.setDate(3, p.getBirthday());
        cabCmd.setInt(4, p.getGender());
        cabCmd.setString(5, p.getDistrict());
        cabCmd.setString(6, p.getProvinces());
        cabCmd.setString(7, p.getYearOfApply());
        cabCmd.setString(8, p.getIdMayjors());
        cabCmd.setString(9, p.getIdObjects());
        cabCmd.setBinaryStream(10, p.getHinhdaidien(), length);
        cabCmd.setString(11, p.getTinhTrang());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int DeleteStudent(clsStudent_Public p) throws Exception{
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_Delete(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdStudent());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet getMaxMSSV(clsStudent_Public p) throws Exception{
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_LayGTCuoiMSSV(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getIdStudent());
        return connect.excuteStore_Para(cabCmd);
    }
}
