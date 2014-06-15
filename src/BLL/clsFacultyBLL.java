/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;
import DAL.*;
import PUBLIC.clsFaculty_Public;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Hung
 * Date: 01/06/2014
 */
public class clsFacultyBLL {
    clsFaculty_DAL faDAL = new clsFaculty_DAL();
    public ResultSet fLoad_NameOfFaculty() throws Exception{
        return faDAL.fLoad_NameOfFaculty();
    }
    
    public ArrayList<clsFaculty_Public> fGetInfoToArr(){
        ArrayList<clsFaculty_Public> kPArray = new ArrayList<>();
        ResultSet rs;
        try {
            rs = fLoad_NameOfFaculty();
            while(rs.next())
            {
                clsFaculty_Public kP = new clsFaculty_Public(rs.getNString(1),rs.getNString(2));
                kPArray.add(kP);
            }
        } catch (Exception ex) {
            Logger.getLogger(clsFacultyBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kPArray;
    }
    
    public ResultSet fLOAD_LISTST_IDFACULTY(clsFaculty_Public faPublic) throws Exception{
        return faDAL.fLOAD_LISTST_IDFACULTY(faPublic);
    }
    
    public int ThemKhoa(clsFaculty_Public p) throws Exception
    {
        return faDAL.ThemKhoa(p);
    }
    
    public int CapNhatKhoa(clsFaculty_Public p) throws Exception
    {
        return faDAL.CapNhatKhoa(p);
    }
    
    public ResultSet LayTTKhoa() throws Exception
    {
        return faDAL.LayTTKhoa();
    }
    
    public int XoaKhoa(clsFaculty_Public p) throws Exception
    {
        return faDAL.XoaKhoa(p);
    }
    
    public ResultSet LoadKhoa() throws Exception
    {
        return faDAL.LoadKhoa();
    }
}
