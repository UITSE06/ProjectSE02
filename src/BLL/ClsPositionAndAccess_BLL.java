/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import java.sql.ResultSet;
import DAL.ClsPositionAndAccess_DAL;
import PUBLIC.ClsPositionAndAccess_Public;

/**
 *
 * @author John
 */
public class ClsPositionAndAccess_BLL {
    private ClsPositionAndAccess_DAL paDAL = new ClsPositionAndAccess_DAL();
    
    public ResultSet LoadPosition() throws Exception
    {
        return paDAL.LoadPosition();
    }
    
    public int InsertPosition(ClsPositionAndAccess_Public p) throws Exception
    {
        return paDAL.InsertPosition(p);
    }
    
    public int UpdatePosition(ClsPositionAndAccess_Public p) throws Exception
    {
        return paDAL.UpdatePosition(p);
    }
    
    public int DeletePosition(ClsPositionAndAccess_Public p) throws Exception
    {
        return paDAL.DeletePosition(p);
    }
}
