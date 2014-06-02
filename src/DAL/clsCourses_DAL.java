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
public class clsCourses_DAL {

    SQLServerConnector connect;
    CallableStatement cabCmd;

    public ResultSet LoadCourses() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadMonHoc}");
    }

    public int InsertMH() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call InsertMH}");
    }

    public ResultSet LoadMH_Nganh(clsMayjors_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LoadMHbyNganh(?)}");
        cabCmd.setString(1, p.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet LoadMHTH_Nganh(clsMayjors_Public p) throws Exception {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LoadMHTH_Nganh(?)}");
        cabCmd.setString(1, p.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }
}
