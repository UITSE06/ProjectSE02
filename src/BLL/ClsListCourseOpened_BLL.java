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
public class ClsListCourseOpened_BLL {

    private ClsListCourseOpened_DAL lcDAL = new ClsListCourseOpened_DAL();

    public boolean IsExistIdSemesterYear(ClsListCoursesOpened_Public p) throws Exception {
        ResultSet rs = lcDAL.LoadIdSemesterYears(p);
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public int CreateTempDSMHMO() throws Exception {
        return lcDAL.CreateTempDSMHMO();
    }

    public int InsertTempDSMHMO(ClsListCoursesOpened_Public p) throws Exception {
        return lcDAL.InsertTempDSMHMO(p);
    }

    public int UpdateListCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        return lcDAL.UpdateListCourseOpened(p);
    }

    public int InsertListCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        return lcDAL.InsertListCourseOpened(p);
    }
    
    public ResultSet GetListCourseOpened(String idSemesterYear) throws Exception {
        return lcDAL.GetListCourseOpened(idSemesterYear);
    }
    
    public int DeleterACourse(ClsListCoursesOpened_Public p) throws Exception {
        return lcDAL.DeleterACourse(p);
    }
    
    public int DeleterAllCourseOpened(ClsListCoursesOpened_Public p) throws Exception {
        return lcDAL.DeleterAllCourseOpened(p);
    }
    
    public ResultSet GetAllDeadline(String idSemesterYear) throws Exception {
        return lcDAL.GetAllDeadline(idSemesterYear);
    }
    
    public int UpdateDeadlineAndPercentReduce(ClsSemesterYearPublic p) throws Exception {
        return lcDAL.UpdateDeadlineAndPercentReduce(p);
    }
}
