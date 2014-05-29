/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import PUBLIC.*;
import java.sql.ResultSet;
/**
 *
 * @author John
 */
public class DangNhapBLL {
    DangNhapDAL dnDAL= new DangNhapDAL();
    
    public ResultSet GetPassByUserName(NhanVienPublic p) throws Exception
    {
        return dnDAL.GetPassByUserName(p);
    }
}
