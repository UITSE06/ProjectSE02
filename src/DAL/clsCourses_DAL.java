/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsCourses_DAL {

    SQLServerConnector connect;

    public ResultSet LoadCourses() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadMonHoc}");
    }
    
    public ResultSet LoadCoursesToOpen() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadMonHocDeMo}");
    }

    public int InsertMH() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call InsertMH}");
    }

    public ResultSet LoadMH_Nganh(clsMayjors_Public p) throws Exception {
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call LoadMHbyNganh(?,?)}");
        cabCmd.setString(1, p.getIdMayjors());
        cabCmd.setInt(2, p.getNoSemester());
        return connect.excuteStore_Para(cabCmd);
    }
    // Load mon học theo mã khoa
    public ResultSet LoadMH_Khoa(clsMayjors_Public p) throws Exception {
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call LoadMHbyKhoa(?)}");
        cabCmd.setString(1, p.getIdFaculty());
        return connect.excuteStore_Para(cabCmd);
    }
    
    // Thanh Thai
    public ResultSet Courses_Delete(clsCourses_Public p) throws Exception{
        connect = new SQLServerConnector();
        String strCall = "{call MonHoc_Delete(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaMon());
        return connect.excuteStore_Para(cabCmd);
    }
    // Thanh Thai
    public ResultSet LoadMH_MonHocTienQuyet(clsCourses_Public p) throws Exception { 
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call MonHoc_LoadMONHOCTIENQUYET(?)}");
        cabCmd.setString(1, p.getMaMon());
        return connect.excuteStore_Para(cabCmd);
    }
    // Thanh Thai
    public ResultSet LoadMH_CT_Nganh(clsCourses_Public p) throws Exception {
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call MonHoc_LoadCT_NGANH(?)}");
        cabCmd.setString(1, p.getMaMon());
        return connect.excuteStore_Para(cabCmd);
    }
    //Thanh Thai
    public ResultSet LoadMH_byMaMH(clsCourses_Public p) throws Exception {
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call MonHoc_LoadbyMaMH(?)}");
        cabCmd.setString(1, p.getMaMon());
        return connect.excuteStore_Para(cabCmd);
    }
    // Thanh Thai
    public int Courses_Update(clsCourses_Public p) throws Exception{ 
        connect = new SQLServerConnector();
        CallableStatement cabCmd = connect.getCallableStatement("{call MonHoc_Update(?,?,?,?,?)}");
        cabCmd.setString(1, p.getMaMon());
        cabCmd.setString(2, p.getMaMonLT());
        cabCmd.setString(3, p.getTenMon());
        cabCmd.setInt(4, p.getSoTiet());
        cabCmd.setString(5, p.getMaLoaiMH());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
