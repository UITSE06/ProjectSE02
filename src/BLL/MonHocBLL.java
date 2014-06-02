/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import java.sql.ResultSet;
import PUBLIC.*;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class MonHocBLL {

    MonHocDAL mhDAL = new MonHocDAL();

    public ResultSet LoadMonHoc() throws Exception {
        return mhDAL.LoadMonHoc();
    }

    public int InsertMH() throws Exception {
        return mhDAL.InsertMH();
    }

    public ResultSet LoadMHbyNganh(clsMayjors_Public p) throws Exception {
        return mhDAL.LoadMHbyNganh(p);
    }
}
