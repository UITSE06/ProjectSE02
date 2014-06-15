/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
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

    public ResultSet LoadSV() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call Load_SV}");
    }
    
    public ResultSet fLoad_Year_Of_StudentApply() throws Exception{
        connect = new SQLServerConnector();
        return  connect.excuteStore("{call LOAD_YEARSTAPPLY()}");
    }
    
    public int InsertUpdateStudent(clsStudent_Public p, int length) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_InsertUpdate(?,?,?,?,?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMssv());
        cabCmd.setString(2, p.getHoTen());
        cabCmd.setDate(3, p.getNgaySinh());
        cabCmd.setInt(4, p.getGioiTinh());
        cabCmd.setString(5, p.getHuyen());
        cabCmd.setString(6, p.getTinh());
        cabCmd.setString(7, p.getNamNhapHoc());
        cabCmd.setString(8, p.getMaNganh());
        cabCmd.setString(9, p.getMaDoiTuong());
        cabCmd.setBinaryStream(10, p.getHinhdaidien(), length);
        cabCmd.setString(11, p.getTinhTrang());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public int DeleteStudent(clsStudent_Public p) throws Exception{
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_Delete(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMssv());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    
    public ResultSet getMaxMSSV(clsStudent_Public p) throws Exception{
        connect = new SQLServerConnector();
        String strCall = "{call SinhVien_LayGTCuoiMSSV(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMssv());
        return connect.excuteStore_Para(cabCmd);
    }
}
