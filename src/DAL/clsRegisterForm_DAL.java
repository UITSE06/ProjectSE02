/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import PUBLIC.PhieuDangKyPublic;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hung
 */
public class clsRegisterForm_DAL {
    
    SQLServerConnector conn;
   CallableStatement cabCmd;
    public ResultSet LOAD_LIST_STUDENT_ALLPARAMETER(PhieuDangKyPublic regFormPublic) {
        try {
            conn = new SQLServerConnector();
            cabCmd = conn.getCallableStatement("{call LOAD_LIST_STUDENT_ALLPARAMETER(?,?,?,?,?,?)}");
            cabCmd.setString(1, regFormPublic.getIdFaculty());
            cabCmd.setString(2, regFormPublic.getIdMayjors());
            cabCmd.setString(3, regFormPublic.getYearOfApply());
            cabCmd.setObject(4, regFormPublic.getNoSemeter());
            cabCmd.setObject(5, regFormPublic.getY1());
            cabCmd.setObject(6, regFormPublic.getY2());
            return conn.excuteStore_Para(cabCmd);
        } catch (Exception ex) {
            Logger.getLogger(clsRegisterForm_DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
