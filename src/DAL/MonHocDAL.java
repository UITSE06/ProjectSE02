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
 * @author Nguyen Thanh Thai
 */
public class MonHocDAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadMonHoc() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadMonHoc}");
    }

    public int InsertMH() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call InsertMH}");
    }

    public ResultSet LoadMHbyNganh(clsMayjors_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LoadMHbyNganh(?)}");
        cabCmd.setString(1, p.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }
}
