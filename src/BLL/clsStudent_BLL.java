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
public class clsStudent_BLL {
    clsStudent_DAL stDAL = new clsStudent_DAL();
    
    public ResultSet LoadSV() throws Exception
    {
        return stDAL.LoadSV();
    }
    
    public ResultSet fLoad_Year_Of_StudentApply() throws Exception{
        return stDAL.fLoad_Year_Of_StudentApply();
    }
}
