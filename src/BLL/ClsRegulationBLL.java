/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import PUBLIC.ClsRegulation_Public;
import java.sql.ResultSet;

/**
 *
 * @author John
 */
public class ClsRegulationBLL {
    
    private final ClsRegulationDAL reDAL = new ClsRegulationDAL();
    
    public ResultSet LoadRegulation() throws Exception
    {
        return reDAL.LoadRegulation();
    }    
    
    public int InsertRegulation() throws Exception
    {
        return reDAL.InsertRegulation();
    }
    
    public int UpdateRegulation(ClsRegulation_Public p) throws Exception
    {
        return reDAL.UpdateRegulation(p);
    }
}
