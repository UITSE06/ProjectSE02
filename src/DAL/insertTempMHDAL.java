/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
import java.sql.CallableStatement;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class insertTempMHDAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public int inserttempMH(clsCourses_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call insertTempMH1(?, ?, ?, ?, ?)}");
        cabCmd.setString(1, "");
        cabCmd.setString(2, p.getTenMon());
        cabCmd.setInt(3, p.getSoTinChi());
        cabCmd.setString(4, p.getMaLoaiMH());
        cabCmd.setString(5, p.getMaMHTQ());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
