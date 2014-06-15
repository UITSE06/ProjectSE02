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
public class insertTempMHBLL {
    insertTempMHDAL intempMH = new insertTempMHDAL();
    
    public int inserttempMH(clsCourses_Public p) throws Exception
    {
        return intempMH.inserttempMH(p);
    }
}
