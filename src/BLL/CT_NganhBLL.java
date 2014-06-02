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
 * @author Nguyen Thanh Thai
 */
public class CT_NganhBLL {
    CT_NganhDAL ctNDAL = new CT_NganhDAL();
    
    public ResultSet getMaHKNganh(CT_NganhPublic p) throws Exception
    {
        return ctNDAL.getMaHKNganh(p);
    }
}
