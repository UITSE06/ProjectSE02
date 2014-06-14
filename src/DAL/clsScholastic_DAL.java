/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Hung
 * Date: 10/06/2014
 * 
 */
public class clsScholastic_DAL {

   SQLServerConnector connect;
    CallableStatement cabCmd;
   
    
    public ResultSet LOAD_SCHOLASTIC() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_SCHOLASTIC()}");
    }
    
    public ResultSet LOAD_SEMETER() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_SEMETER()}");
    }
}
