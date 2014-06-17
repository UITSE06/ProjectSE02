/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import java.sql.ResultSet;
import DAL.ClsPaymentFee_DAL;
import PUBLIC.ClsPaymentFee_Public;
import PUBLIC.ClsRegisterForm_Public;
import PUBLIC.ClsSemesterYearPublic;
import java.math.BigDecimal;

/**
 *
 * @author John
 */
public class ClsPaymentFee_BLL {
    private final ClsPaymentFee_DAL pfDAL = new ClsPaymentFee_DAL();
    
    public ResultSet LoadFirstYear() throws Exception{
        return pfDAL.LoadFirstYear();
    }
    
    public ResultSet LoadAllDeadline(ClsSemesterYearPublic p) throws Exception{
        return pfDAL.LoadAllDeadline(p);
    }
    
    public ResultSet LoadFeeInfo(ClsRegisterForm_Public p) throws Exception{
        return pfDAL.LoadFeeInfo(p);
    }
    
    public int ExcutePaymentFee(int sumMoneyPaid, ClsPaymentFee_Public p) throws Exception{
        return pfDAL.ExcutePaymentFee(sumMoneyPaid, p);
    }
}
