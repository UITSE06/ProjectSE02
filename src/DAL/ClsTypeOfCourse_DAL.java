/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.ClsHumanSubjects_Public;
import PUBLIC.ClsTypeOfCourse_Public;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsTypeOfCourse_DAL {

    private SQLServerConnector connect;

    public ResultSet LoadTypeOfCourse() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayLoaiMonHoc}");
    }

    public int InsertTypeOfCourse() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call ThemLoaiMonHoc}");
    }

    public int UpdateTypeOfCourse(ClsTypeOfCourse_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call CapNhatLoaiMonHoc(?,?,?,?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaLoaiMH());
        cabCmd.setString(2, p.getTenLoai());
        cabCmd.setBigDecimal(3, p.getSoTienTrenTC());
        cabCmd.setInt(4, p.getSoTietTrenTC());
        return connect.excuteUpdateStorePara(cabCmd);
    }

    public int DeleteTypeOfCourse(ClsTypeOfCourse_Public p) throws Exception {
        connect = new SQLServerConnector();
        String strCall = "{call XoaLoaiMonHoc(?)}";
        CallableStatement cabCmd = connect.getCallableStatement(strCall);
        cabCmd.setString(1, p.getMaLoaiMH());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
