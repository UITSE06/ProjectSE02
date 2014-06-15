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
 * @author Hung
 */
public class LoginBLL {
    LoginDAL lgDAL = new LoginDAL();
    
    public ResultSet GetPassByUserName(clsStaff_Public p) throws Exception
    {
        return lgDAL.GetPassByUserName(p);
    }
    
    public ResultSet GetStaffInfoByUserName(clsStaff_Public p) throws Exception
    {
        return lgDAL.GetStaffInfoByUserName(p);
    }
}
