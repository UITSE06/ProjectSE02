/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import java.sql.ResultSet;
import PUBLIC.*;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsCourses_BLL {

    clsCourses_DAL mhDAL = new clsCourses_DAL();

    public ResultSet LoadMonHoc() throws Exception {
        return mhDAL.LoadCourses();
    }

    public int InsertMH() throws Exception {
        return mhDAL.InsertMH();
    }

    public ResultSet LoadMH_Nganh(clsMayjors_Public p) throws Exception {
        return mhDAL.LoadMH_Nganh(p);
    }
    
    // Load mon học theo mã khoa
    public ResultSet LoadMH_Khoa(clsMayjors_Public p) throws Exception {
        return mhDAL.LoadMH_Khoa(p);
    }
    
    // Thanh Thai
    public ResultSet Courses_Delete(clsCourses_Public p) throws Exception{
        return mhDAL.Courses_Delete(p);
    }
    // Thanh Thai
    public ResultSet LoadMH_MonHocTienQuyet(clsCourses_Public p) throws Exception {
        return mhDAL.LoadMH_MonHocTienQuyet(p);
    }
    // Thanh Thai
    public ResultSet LoadMH_CT_Nganh(clsCourses_Public p) throws Exception {
        return mhDAL.LoadMH_CT_Nganh(p);
    }
    //Thanh Thai
    public ResultSet LoadMH_byMaMH(clsCourses_Public p) throws Exception {
        return mhDAL.LoadMH_byMaMH(p);
    }
    // Thanh Thai
    public int Courses_Update(clsCourses_Public p) throws Exception{
        return mhDAL.Courses_Update(p);
    }
}
