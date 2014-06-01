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
public class KhoaBLL {
    
    private final KhoaDAL kDAL = new KhoaDAL();
    
    public int ThemKhoa(KhoaPublic p) throws Exception
    {
        return kDAL.ThemKhoa(p);
    }
    
    public int CapNhatKhoa(KhoaPublic p) throws Exception
    {
        return kDAL.CapNhatKhoa(p);
    }
    
    public ResultSet LayTTKhoa() throws Exception
    {
        return kDAL.LayTTKhoa();
    }
    
    public int XoaKhoa(KhoaPublic p) throws Exception
    {
        return kDAL.XoaKhoa(p);
    }
}
