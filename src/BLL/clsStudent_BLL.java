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
    
    /*@HungNgoc
    * Date: 06/02/2014
    * Description: Ham tra ve cua lop DAL: fLOAD_LISTSTUDENT_RP()
    */
    public ResultSet fLOAD_LISTSTUDENT_RP() throws Exception{
        return  stDAL.fLOAD_LISTSTUDENT_RP();
    }
    
    public ResultSet LOAD_LISTST_IDMAYJORS(clsStudent_Public sPublic) throws Exception{
        return stDAL.LOAD_LISTST_IDMAYJORS(sPublic);
    }
}
