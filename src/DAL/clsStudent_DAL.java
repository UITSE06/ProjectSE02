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
    
    /**@HungNgoc
    * Date: 06/02/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy
    */
    public ResultSet fLOAD_LISTSTUDENT_RP() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{call LOAD_LISTSTUDENT_RP()}");
    }
    
     /**@HungNgoc
    * Date: 06/02/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao la makhoa
    */
    public ResultSet LOAD_LISTST_IDMAYJORS(clsStudent_Public sPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDMAYJORS(?)}");
        cabCmd.setString(1, sPublic.getIdMayjors());
        return connect.excuteStore_Para(cabCmd);
    }
    
    
    /**@HungNgoc
    * Date: 06/06/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao la manganh, year
    */
     public ResultSet LOAD_LISTST_IDMAYJORS_YEAR(clsStudent_Public sPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDMAYJOR_YEAR(?,?)}");
        cabCmd.setString(1, sPublic.getIdMayjors());
        cabCmd.setString(2, sPublic.getYearOfApply());
        return connect.excuteStore_Para(cabCmd);
    }
     
     /**@HungNgoc
    * Date: 06/06/2014
    * Description: Load thong tin sinh toan truong gom co:
      MSSV, HoTen, TenKhoa, TenNganh, SoTCDangKy voi tham so truyen vao la makhoa, year
    */
    public ResultSet LOAD_LISTST_IDFACULTY_YEAR(clsStudent_Public stPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOAD_LISTST_IDFACULTY_YEAR(?,?)}");
        cabCmd.setString(1, stPublic.getIdFaculty());
        cabCmd.setString(2, stPublic.getYearOfApply());
        return connect.excuteStore_Para(cabCmd);
    }
    
    public ResultSet LOAD_LISTST_REGISTERED() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{LOAD_LISTST_REGISTERED()}");
    }
    
    public ResultSet LOAD_LISTST_NOTREGISTERED() throws Exception{
        connect = new SQLServerConnector();
        return connect.excuteStore("{LOAD_LISTST_NOTREGISTERED()}");
    }
    
    /**@HungNgoc
    * Date: 08/06/2014
    * Description: Load thong tin chi tiet ve viec dang ky hoc phan sinh vien
    */
    public ResultSet LOA_LIST_STUDENT_DETAIL_ID(clsStudent_Public stPublic) throws Exception{
        connect = new SQLServerConnector();
        cabCmd = connect.getCallableStatement("{call LOA_LIST_STUDENT_DETAIL_ID(?)}");
        cabCmd.setString(1, stPublic.getIdStudent());
        return connect.excuteStore_Para(cabCmd);
    }
}
