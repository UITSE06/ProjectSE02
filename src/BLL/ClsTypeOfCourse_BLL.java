/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.ClsTypeOfCourse_DAL;
import PUBLIC.ClsTypeOfCourse_Public;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsTypeOfCourse_BLL {
    ClsTypeOfCourse_DAL tcDAL = new ClsTypeOfCourse_DAL();
    
    public ResultSet LoadTypeOfCourse() throws Exception
    {
        return tcDAL.LoadTypeOfCourse();
    }
    
    public int InsertTypeOfCourse() throws Exception {
        return tcDAL.InsertTypeOfCourse();
    }
    
    public int UpdateTypeOfCourse(ClsTypeOfCourse_Public p) throws Exception {
        return tcDAL.UpdateTypeOfCourse(p);
    }
    
    public int DeleteTypeOfCourse(ClsTypeOfCourse_Public p) throws Exception {
        return tcDAL.DeleteTypeOfCourse(p);
    }
}
