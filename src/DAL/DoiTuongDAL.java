/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class DoiTuongDAL {
    private SQLServerConnector connect;
    
    public ResultSet LoadDT() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadDT}");
    }
}
