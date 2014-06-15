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
    
}
