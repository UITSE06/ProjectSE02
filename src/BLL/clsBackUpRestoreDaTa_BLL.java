/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.clsBackUpRestoreDaTa_DAL;


/**
 *
 * @author John
 */
public class clsBackUpRestoreDaTa_BLL {
    clsBackUpRestoreDaTa_DAL clsBK = new clsBackUpRestoreDaTa_DAL();
    
    public int BackUpDaTa(String nameData, String path) throws Exception {
        return clsBK.BackUpDaTa(nameData, path);
    }
    
    public int RestoreData(String name, String path) throws Exception {
        return clsBK.RestoreData(name, path);
    }
}
