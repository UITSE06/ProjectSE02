/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import java.sql.ResultSet;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class LoaiMonHocBLL {
    LoaiMonHocDAL lmhDAL = new LoaiMonHocDAL();
    
    public ResultSet LoadLoaiMH() throws Exception
    {
        return lmhDAL.LoadLoaiMH();
    }
}
