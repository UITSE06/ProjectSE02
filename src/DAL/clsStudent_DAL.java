/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import PUBLIC.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class clsStudent_DAL {

    private SQLServerConnector connect;
    CallableStatement cabCmd;
    
    public ResultSet LoadSV() throws Exception {
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LoadSV}");
    }
    
    public ResultSet fLoad_Year_Of_StudentApply() throws Exception{
        connect = new SQLServerConnector();
        return  connect.excuteStore("{call LOAD_YEARSTAPPLY()}");
    }
    
    /*@HungNgoc
    * Date: 06/02/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy
    */
    public ResultSet fLOAD_LISTSTUDENT_RP() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_LISTSTUDENT_RP()}");
    }
    
     /*@HungNgoc
    * Date: 06/02/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao la manganh
    */
    public ResultSet LOAD_LISTST_IDMAYJORS(clsStudent_Public sPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDMAYJORS(?)}");
        cabCmd.setString(1, sPublic.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }
            
}
