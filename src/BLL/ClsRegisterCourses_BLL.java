/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import PUBLIC.*;
import java.sql.ResultSet;
import java.math.BigDecimal;

/**
 *
 * @author John
 */
public class ClsRegisterCourses_BLL {

    ClsRegisterCourses_DAL rcDAL = new ClsRegisterCourses_DAL();
    ResultSet rs;

    public ResultSet LoadStudentInfo(clsStudent_Public p) throws Exception {
        return rcDAL.LoadStudentInfo(p);
    }

    public ResultSet LoadListCourseToRegister(String idMayjor, String idSemesterYear) throws Exception {
        return rcDAL.LoadListCourseToRegister(idMayjor, idSemesterYear);
    }

    public int InMoneyCourse(String IdCourse) throws Exception {
        rs = rcDAL.InMoneyCourse(IdCourse);
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }

    public int SaveToRegisterForm(ClsRegisterForm_Public p) throws Exception {
        return rcDAL.SaveToRegisterForm(p);
    }
    
    public int UpdateRegisterForm(ClsRegisterForm_Public p) throws Exception {
        return rcDAL.UpdateRegisterForm(p);
    }

    public String CreateTempRegisterForm() throws Exception {
        String result = "";
        ResultSet rs = rcDAL.CreateTempRegisterForm();
        if(rs.next())
        {
            result = rs.getString(1);
            if(result != null)
                return result;
        }
        return "";
    }
    
    public int InsertTempRegisterFormDetail(ClsRegisterFormDetail_Public p) throws Exception {
        return rcDAL.InsertTempRegisterFormDetail(p);
    }
    /*
    *Tra ve chuoi MaPhieuDK neu sinh vien da dang ki mon hoc o hoc ki hien tai
    */
    public String CheckRegistedOrNot(ClsRegisterForm_Public p) throws Exception {
        ResultSet rs = rcDAL.CheckRegistedOrNot(p);
        if(rs.next())
            return rs.getString(1);
        else
            return "";
    }
    /*
    * tra ve false neu chua hoc mon tien quyet
    * true neu da hoc mon tien quyet, hoac mon hoc k co mon tien quyet
    */
    public boolean CheckCourseRequest(String mssv, String maMonHoc) throws Exception {
        ResultSet rs = rcDAL.CheckCourseRequest(mssv, maMonHoc);
        return !rs.next();
    }
    
    public ResultSet GetListCoureRegisted(ClsRegisterForm_Public p) throws Exception {
        return rcDAL.GetListCoureRegisted(p);
    }
    
    public String GetSumOfFee(ClsRegisterForm_Public p) throws Exception {
        ResultSet rs = rcDAL.GetSumOfFee(p);
        String[] arr;
        if(rs.next()&&(arr = rs.getString(1).trim().split("\\.")).length > 0){
            return arr[0];
        } else {
            return "";
        }                
    }
    
    public ResultSet GetPercentReduceFee(String mssv) throws Exception {
        return rcDAL.GetPercentReduceFee(mssv);
    }
    
    public int CancelACourseRegisted(ClsRegisterFormDetail_Public p) throws Exception {
        return rcDAL.CancelACourseRegisted(p);
    }
    
    public int CancelAllCourseRegisted(ClsRegisterFormDetail_Public p) throws Exception {
        return rcDAL.CancelAllCourseRegisted(p);
    }
    
    
}
