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

    public ResultSet LoadSV() throws Exception {
        return stDAL.LoadSV();
    }

    public ResultSet fLoad_Year_Of_StudentApply() throws Exception {
        return stDAL.fLoad_Year_Of_StudentApply();
    }

    /**
     * @HungNgoc Date: 06/02/2014 Description: Ham tra ve cua lop DAL:
     * fLOAD_LISTSTUDENT_RP()
     */
    public ResultSet fLOAD_LISTSTUDENT_RP() throws Exception {
        return stDAL.fLOAD_LISTSTUDENT_RP();
    }

    public ResultSet LOAD_LISTST_IDMAYJORS(clsStudent_Public sPublic) throws Exception {
        return stDAL.LOAD_LISTST_IDMAYJORS(sPublic);
    }

    public ResultSet LOAD_LISTST_IDMAJORS_YEAR(clsStudent_Public sPublic) throws Exception {
        return stDAL.LOAD_LISTST_IDMAYJORS_YEAR(sPublic);
    }

    /**
     * @HungNgoc Date: 06/06/2014 Description: Ham tra ve cua lop DAL
     */
    public ResultSet LOAD_LISTST_IDFACULTY_YEAR(clsStudent_Public stPublic) throws Exception {
        return stDAL.LOAD_LISTST_IDFACULTY_YEAR(stPublic);
    }

    public ResultSet LOAD_LISTST_NOTREGISTERED() throws Exception {
        return stDAL.LOAD_LISTST_NOTREGISTERED();
    }

    public ResultSet LOAD_LISTST_REGISTERED() throws Exception {
        return stDAL.LOAD_LISTST_REGISTERED();
    }

    public ResultSet LOA_LIST_STUDENT_DETAIL_ID(clsStudent_Public stPublic, PhieuDangKyPublic regFormPublic) throws Exception {
        return stDAL.LOA_LIST_STUDENT_DETAIL_ID(stPublic,regFormPublic);
    }

    /**
     * @HungNgoc
     * @return
     * @throws Exception 
     */
    public ResultSet LOAD_NAMEMAYJORS(clsStudent_Public stPublic) throws Exception {
        return stDAL.LOAD_NAMEMAYJORS(stPublic);}
    
    public int InsertUpdateStudent(clsStudent_Public p, int length) throws Exception {
        return stDAL.InsertUpdateStudent(p, length);
    }
    
    public int DeleteStudent(clsStudent_Public p) throws Exception{
        return stDAL.DeleteStudent(p);
    }
    
    public ResultSet getMaxMSSV(clsStudent_Public p) throws Exception{
        return stDAL.getMaxMSSV(p);
    }
}
