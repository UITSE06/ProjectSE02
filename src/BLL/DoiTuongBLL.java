/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class DoiTuongBLL {
    DoiTuongDAL dtDAL = new DoiTuongDAL();
    
    public ResultSet LoadDT() throws Exception
    {
        return dtDAL.LoadDT();
    }
}
