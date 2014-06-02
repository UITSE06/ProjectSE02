/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class createtempMHDAL {
    SQLServerConnector connect;
    
    public int createtempMH() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteUpdateStore("{call createTempMH}");
    }
}
