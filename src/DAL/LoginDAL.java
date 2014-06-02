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
 * @author Hung
 */
public class LoginDAL {
    SQLServerConnector connect;
    // Khi nao thuc hien cau lenh store co' tham so can phai tao doi tuong nay.
    CallableStatement cabCmd;
    
    public ResultSet GetPassByUserName(NhanVienPublic p) throws Exception
    {
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call GetPassByUserName(?)}");
        cabCmd.setString(1, p.getTenDN());
        return connect.excuteStore_Para(cabCmd);
    }
}
