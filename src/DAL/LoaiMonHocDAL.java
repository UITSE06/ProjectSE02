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
public class LoaiMonHocDAL {
    SQLServerConnector connect;
    
    public ResultSet LoadLoaiMH() throws Exception
    {
        connect = new SQLServerConnector();
        return connect.excuteQuery("Select * from LOAIMONHOC");
    }
}
