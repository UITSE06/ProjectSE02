/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.clsCourses_DAL;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsChooseCourse_BLL {
    
    clsCourses_DAL mhDAL = new clsCourses_DAL();
    
    public ResultSet LoadCoursesToOpen() throws Exception 
    {
        return mhDAL.LoadCoursesToOpen();
    }
}