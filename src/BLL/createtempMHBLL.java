/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class createtempMHBLL {

    createtempMHDAL crTempMH = new createtempMHDAL();

    public int createTempMH() throws Exception {
        return crTempMH.createtempMH();
    }
}
