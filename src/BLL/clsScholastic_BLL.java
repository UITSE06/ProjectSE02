/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.clsScholastic_DAL;
import java.sql.ResultSet;

/**
 *
 * @author Hung
 */
public class clsScholastic_BLL {
    clsScholastic_DAL scholDAL;
    public ResultSet LOAD_SCHOLASTIC() throws Exception{
        scholDAL = new clsScholastic_DAL();
        return scholDAL.LOAD_SCHOLASTIC();
    }
    
    public ResultSet LOAD_SEMETER() throws Exception{
        scholDAL = new clsScholastic_DAL();
        return scholDAL.LOAD_SEMETER();
    }
}
