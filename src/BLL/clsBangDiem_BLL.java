/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.clsBangDiem_DAL;
import PUBLIC.clsBangDiem_Public;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsBangDiem_BLL {
    
    clsBangDiem_DAL bDiemDAL = new clsBangDiem_DAL();
    
    public ResultSet LoadCoursesRegisted(clsBangDiem_Public p) throws Exception{
        return bDiemDAL.LoadCoursesRegisted(p);
    }
    
    public int ScoresInsertUpdate(clsBangDiem_Public p) throws Exception{
        return bDiemDAL.ScoresInsertUpdate(p);
    }
}
