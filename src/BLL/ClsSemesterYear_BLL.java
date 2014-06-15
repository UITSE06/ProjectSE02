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
public class ClsSemesterYear_BLL {

    private ClsSemesterYear_DAL syDAL = new ClsSemesterYear_DAL();

    public boolean IsExistSemesterAndYear(ClsSemesterYearPublic p) throws Exception {
        ResultSet rs = syDAL.LoadSemesterAndFirstYear(p);
        if (rs.next()) {
            return true;
        }
        return false;
    }

    public String GetIdSemesterYear(ClsSemesterYearPublic p) throws Exception {
        ResultSet rs = syDAL.GetIdSemesterYear(p);
        while (rs.next()) {
            String rss = rs.getString(1);
            if (!rss.isEmpty()) {
                return rss;
            }
        }
        return "";
    }

    public int InsertSemesterYear(ClsSemesterYearPublic p) throws Exception {
        return syDAL.InsertSemesterYear(p);
    }
    
    public ResultSet LoadSemesterYears() throws Exception{
        return syDAL.LoadSemesterYears();
    }
            
    public int InsertUpdateSemesterYear(ClsSemesterYearPublic p) throws Exception {
        return syDAL.InsertUpdateSemesterYear(p);
    }
    
    // Thanh Thai
    public ResultSet LoadFirstYear() throws Exception{
        return syDAL.LoadFirstYear();
    }
    
    public ResultSet GetDeadlineToRegister(ClsSemesterYearPublic p) throws Exception{
        return syDAL.GetDeadlineToRegister(p);
    }
}
