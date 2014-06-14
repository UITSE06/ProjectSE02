/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.clsRegisterForm_DAL;
import PUBLIC.PhieuDangKyPublic;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hung
 */
public class clsRegisterForm_BLL {

    clsRegisterForm_DAL reFormDAL;

    public ResultSet LOAD_LIST_STUDENT_ALLPARAMETER(PhieuDangKyPublic regFormPublic){
        reFormDAL = new clsRegisterForm_DAL();
        try {
            return reFormDAL.LOAD_LIST_STUDENT_ALLPARAMETER(regFormPublic);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(clsRegisterForm_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
