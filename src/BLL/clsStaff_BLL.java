/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.SQLServerConnector;
import PUBLIC.clsStaff_Public;
import DAL.clsStaff_DAL;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsStaff_BLL {
    clsStaff_DAL staDAL = new clsStaff_DAL();
    
    public ResultSet LoadNV() throws Exception {
        return staDAL.LoadNV();
    }
    
    public int InsertUpdateStaff(clsStaff_Public p, int length) throws Exception {
        return staDAL.InsertUpdateStaff(p, length);
    }
    
    public int UpdatePassWord(clsStaff_Public p) throws Exception {
        return staDAL.UpdatePassWord(p);
    }
}
