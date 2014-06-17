/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import GUI.FrmConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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
    
    private String Server = "";
    private String UserName = "";
    private String PassWord = "";
    private String DatabaseName = "";

    // Đọc file lưu giữ cấu hình
    public final void readFileConnectionString() throws IOException {
        FileReader fr;
        try {
            // Mở file lên, đọc Server name, user và pass
            fr = new FileReader("connectionString");
            BufferedReader br = new BufferedReader(fr);
            String line;
            // đọc server
            if ((line = br.readLine()) != null) {
                Server = line;
            }
            // đọc user
            if ((line = br.readLine()) != null) {
                UserName = line;
            }
            // đọc pass
            if ((line = br.readLine()) != null) {
                PassWord = line;
            }
            // đọc databasename
            if ((line = br.readLine()) != null) {
                DatabaseName = line;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LayChuoiKetNoiDAL() {
        try {
            Encrytion_DAL encryDAL = new Encrytion_DAL();
            readFileConnectionString();
            this.server = Server;
            this.portNumber = 1433;
            this.userName = encryDAL.DecryptText(UserName);
            this.password = encryDAL.DecryptText(PassWord);
            this.databaseName = DatabaseName;
        } catch (IOException ex) {
            Logger.getLogger(LayChuoiKetNoiDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
