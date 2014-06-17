/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.logging.Level;
import java.util.logging.Logger;
import PUBLIC.*;

/**
 *
 * @author John
 */
public class clsBackUpRestoreDaTa_DAL {

    SQLServerConnector connect;

    public int BackUpDaTa(String nameData, String path) throws Exception {
        connect = new SQLServerConnector();
        String tenCSDL = new ClsStaffLoginInfo_Public().getTenCSDL().trim();
        String sql = "BACKUP DATABASE " + tenCSDL + " TO DISK = '" + path + "\\" + nameData + ".bak'";
        int rs = connect.excuteUpdate(sql);
        return rs;
    }

    public int RestoreData(String name, String path) throws Exception{
        int excuteUpdate;
        int x;
        try {
            connect = new SQLServerConnector();
            String sql = "use master ALTER DATABASE " + name + " SET SINGLE_USER With ROLLBACK IMMEDIATE" + "\n";
            sql += "RESTORE DATABASE " + name + " FROM DISK = '" + path + "'" + " WITH REPLACE";
            excuteUpdate = connect.excuteUpdate(sql);
            sql = "use master ALTER DATABASE " + name + " set MULTI_USER";
            x = connect.excuteUpdate(sql);
            
        } catch (Exception ex) {
            //nếu không alter được thì tạo mới
            String sql = "use master create DATABASE " + name +"\n";
            sql += "RESTORE DATABASE " + name + " FROM DISK = '" + path + "'" + " WITH REPLACE";
            excuteUpdate = connect.excuteUpdate(sql);
            sql = "use master ALTER DATABASE " + name + " set MULTI_USER";
            x = connect.excuteUpdate(sql);
        }
        if(excuteUpdate > 0 && x> 0){
            return 1;
        }
        return 0;
    }
}
