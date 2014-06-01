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
 * @author John
 */
public class NganhBLL {
    
    private final NganhDAL ngDAL = new NganhDAL();
    
    public ResultSet LayTTNganh() throws Exception
    {
        return ngDAL.LayTTNganh();
    }    
    
    public int ThemNganh(NganhPublic p) throws Exception
    {
        return ngDAL.ThemNganh(p);
    }
    
    public int CapNhatNganh(NganhPublic p) throws Exception
    {
        return ngDAL.CapNhatNganh(p);
    }
    
    public int XoaNganh(NganhPublic p) throws Exception
    {
        return ngDAL.XoaNganh(p);
    }
}
