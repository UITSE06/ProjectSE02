/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import PUBLIC.ClsHumanSubjects_Public;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class ClsHumanSubjects_BLL {
    ClsHumanSubjects_DAL dtDAL = new ClsHumanSubjects_DAL();
    
    public ResultSet LoadHumanSubjects() throws Exception
    {
        return dtDAL.LoadHumanSubjects();
    }
    
    public int InsertHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
        return dtDAL.InsertHumanSubjects(p);
    }
    
    public int UpdateHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
        return dtDAL.UpdateHumanSubjects(p);
    }
    
    public int DeleteHumanSubjects(ClsHumanSubjects_Public p) throws Exception {
        return dtDAL.DeleteHumanSubjects(p);
    }
}
