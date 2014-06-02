/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.*;
import PUBLIC.*;
/**
 *
 * @author Nguyen Thanh Thai
 */
public class insertTempCTMNBLL {
    insertTempCTMNDAL inCTMN = new insertTempCTMNDAL();
    
    public int insertTempCTMN(MonHocPublic p) throws Exception
    {
        return inCTMN.insertTempCTMN(p);
    }
}
