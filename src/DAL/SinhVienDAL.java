/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class SinhVienDAL {

    private SQLServerConnector connect;

    public ResultSet LoadSV() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadSV}");
    }
    
}
