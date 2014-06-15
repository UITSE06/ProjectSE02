/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.clsRegisterForm_DAL;
import PUBLIC.PhieuDangKyPublic;
import PUBLIC.clsStudent_Public;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hung
 */
public class clsRegisterForm_BLL {

    clsRegisterForm_DAL reFormDAL;

    public ResultSet LOAD_LIST_STUDENT_ALLPARAMETER(PhieuDangKyPublic regFormPublic) {
        reFormDAL = new clsRegisterForm_DAL();
        try {
            return reFormDAL.LOAD_LIST_STUDENT_ALLPARAMETER(regFormPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet LOAD_LISTST_FEE(PhieuDangKyPublic regFormPublic) {
        try {
            reFormDAL = new clsRegisterForm_DAL();
            return reFormDAL.LOAD_LISTST_FEE(regFormPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public ResultSet LOAD_LISTST_FEE_DETAIL(PhieuDangKyPublic regFormPublic, clsStudent_Public stPublic) {
        try {
            reFormDAL = new clsRegisterForm_DAL();
            return reFormDAL.LOAD_LISTST_FEE_DETAIL(regFormPublic,stPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public ResultSet SEARCH_STUDENT(PhieuDangKyPublic regFormPublic, clsStudent_Public stPublic) {
        try {
            reFormDAL = new clsRegisterForm_DAL();
            return reFormDAL.SEARCH_STUDENT(regFormPublic,stPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     public ResultSet SEARCH_STUDENT_FEE(PhieuDangKyPublic regFormPublic, clsStudent_Public stPublic) {
        try {
            reFormDAL = new clsRegisterForm_DAL();
            return reFormDAL.SEARCH_STUDENT_FEE(regFormPublic,stPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
}
