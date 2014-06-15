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
public class insertTempCTMNDAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public int insertTempCTMN(CT_NganhPublic p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call insertTempCTMN(?,?)}");
        cabCmd.setString(1, p.getMaNganh());
        cabCmd.setInt(2, p.getHocKi());
        return connect.excuteUpdateStorePara(cabCmd);
    }
}
