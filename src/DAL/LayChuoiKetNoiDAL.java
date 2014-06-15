/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class LayChuoiKetNoiDAL {

    String server = "";                 // Ten Server.
    int portNumber = Integer.MIN_VALUE;
    String userName = "";               // UserName SQL
    String password = "";               // Password 
    String databaseName = "";           // Ten CSDL

    public LayChuoiKetNoiDAL() {
        this.server = "JOHNTRAN";
        this.portNumber = 1433;
        this.userName = "";
        this.password = "";
        this.databaseName = "QUANLYDANGKYMONHOC";
    }

}
