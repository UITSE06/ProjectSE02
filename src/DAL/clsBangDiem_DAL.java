/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.clsBangDiem_Public;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsBangDiem_DAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadCoursesRegisted(clsBangDiem_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call SinhVien_LoadMHDK(?,?,?,?)}");
        cabCmd.setString(1, p.getMssv());
        cabCmd.setInt(2, p.getNam1());
        cabCmd.setInt(3, p.getNam2());
        cabCmd.setInt(4, p.getHocki());
        return connect.excuteStore_Para(cabCmd);
    }

    public ResultSet LoadStudentInfo_toImportScores(clsBangDiem_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayThongTinSVDangKy_TT(?,?,?,?)}");
        cabCmd.setString(1, p.getMssv());
        cabCmd.setInt(2, p.getNam1());
        cabCmd.setInt(3, p.getNam2());
        cabCmd.setInt(4, p.getHocki());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public int ScoresInsertUpdate(clsBangDiem_Public p) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call BangDiem_InsertUpdate(?,?,?,?,?,?)}");
        cabCmd.setString(1, p.getMaMon());
        cabCmd.setString(2 , p.getMssv());
        cabCmd.setFloat(3, p.getDiemTB());
        cabCmd.setInt(4, p.getNam1());
        cabCmd.setInt(5, p.getNam2());
        cabCmd.setInt(6, p.getHocki());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
