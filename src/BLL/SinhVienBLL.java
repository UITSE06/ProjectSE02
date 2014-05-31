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
public class SinhVienBLL {
    SinhVienDAL svDAL = new SinhVienDAL();
    
    public ResultSet LoadSV() throws Exception
    {
        return svDAL.LoadSV();
    }
}
