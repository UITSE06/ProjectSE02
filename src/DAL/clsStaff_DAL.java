/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.clsStaff_Public;
import PUBLIC.clsStudent_Public;
import java.sql.CallableStatement;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsStaff_DAL {
    
    private SQLServerConnector connect;
    public ResultSet LoadNV() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call Load_NV}");
    }
    
    public int InsertUpdateStaff(clsStaff_Public p, int length) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call NhanVien_InsertUpdate(?,?,?,?,?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaNV());
        cabCmd.setString(2, p.getTenNV());
        cabCmd.setDate(3, p.getNgaySinh());
        cabCmd.setInt(4, p.getGioiTinh());
        cabCmd.setString(5, p.getDiaChi());
        cabCmd.setString(6, p.getMatKhau());
        cabCmd.setString(7, p.getMaChucVu());
        cabCmd.setBinaryStream(8, p.getHinhdaidien(), length);
        return connect.excuteUpdateStorePara(cabCmd);
    }
    //Anh Quan viết
    public int UpdatePassWord(clsStaff_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatMatKhauNhanVien(?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getTenDN());
        cabCmd.setString(2, p.getMatKhau());
        return connect.excuteUpdateStorePara(cabCmd);
    }
    // Thanh Thai
    public ResultSet Tim_NV(clsStaff_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call NhanVien_TimDayDu(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getTenNV());
        return connect.excuteStore_Para(cabCmd);
    }
    // Thanh Thai 
    public int Delete_Staff(clsStaff_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call NhanVien_Delete(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaNV());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
