/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import PUBLIC.clsMayjors_Public;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsMayjors_BLL {

    clsMayjors_DAL nDAL = new clsMayjors_DAL();

    public ResultSet LoadNganh() throws Exception {
        return nDAL.LoadNganh();
    }
    
    public ResultSet fLoadInfoMayjors_idFaculty(clsMayjors_Public p) throws Exception{
        return nDAL.fLoadInfoMayjors_idFaculty(p);
    }
    
    public ArrayList<clsMayjors_Public> layTTNganhThemVaoMang()
    {
        ArrayList<clsMayjors_Public> mNganh = new ArrayList<>();
        try {
            ResultSet rs = LoadNganh();
            while(rs.next())
            {
                clsMayjors_Public nP = new clsMayjors_Public(rs.getNString(1),rs.getNString(2),rs.getNString(3));
                mNganh.add(nP);
            }
        } catch (Exception ex) {
            Logger.getLogger(clsMayjors_BLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return mNganh;
    }
    
    public ResultSet LayTTNganh() throws Exception
    {
        return nDAL.LayTTNganh();
    }    
    
    public int ThemNganh(clsMayjors_Public p) throws Exception
    {
        return nDAL.ThemNganh(p);
    }
    
    public int CapNhatNganh(clsMayjors_Public p) throws Exception
    {
        return nDAL.CapNhatNganh(p);
    }
    
    public int XoaNganh(clsMayjors_Public p) throws Exception
    {
        return nDAL.XoaNganh(p);
    }
}
