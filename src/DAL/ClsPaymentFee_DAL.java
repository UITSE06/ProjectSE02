/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.*;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsPaymentFee_DAL {
    SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet LoadFirstYear() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LayTatCaNam1}");
    }
    
    public ResultSet LoadAllDeadline(ClsSemesterYearPublic p) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayTatCaCacHanChotTheoHocKiVaNam1(?,?)}");
        cabCmd.setInt(1, p.getSemester());
        cabCmd.setInt(2, p.getFirstYear());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet LoadFeeInfo(ClsRegisterForm_Public p) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LayThongTinHocPhi(?,?)}");
        cabCmd.setString(1, p.getIdStudent());
        cabCmd.setString(2, p.getIdSemesterYears());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public int ExcutePaymentFee(BigDecimal sumMoneyPaid, ClsPaymentFee_Public p) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call ThuHocPhi(?,?,?,?,?,?,?,?)}");
        cabCmd.setBigDecimal(1, sumMoneyPaid);
        cabCmd.setString(2, p.getMaPhieuThu());
        cabCmd.setString(3, p.getMaNhanVien());
        cabCmd.setString(4, p.getMaPhieuDK());
        cabCmd.setBigDecimal(5, p.getTienConNo());
        cabCmd.setDate(6, p.getNgayThu());
        cabCmd.setBigDecimal(7, p.getSoTienThu());
        cabCmd.setBigDecimal(8, p.getSoTienDuocGiam());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
