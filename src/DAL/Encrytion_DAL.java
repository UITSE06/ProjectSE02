/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class Encrytion_DAL {
private static final byte[] sharedvector = {
    0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11
    };
    public String EncryptText(String RawText)
    {
        String EncText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = "demoencryption";
        byte[] toEncryptArray = null;
  
        try
        {
            // chuyển đối chuỗi cần mã hóa sang dạng bytes
            toEncryptArray =  RawText.getBytes("UTF-8");     
            // lấy đối tượng mã hóa kiểu MD5
            MessageDigest m = MessageDigest.getInstance("MD5");
            // tạo ra một mảng ngẫu nhiên từ key
            temporaryKey = m.digest(key.getBytes("UTF-8"));
 
            // DESede yêu cầu 64 bits
            if(temporaryKey.length < 24) // DESede require 24 byte length key <=> the SunJCE provider uses a default of 64 bits for DES
            {
                int index = 0;
                // duyệt qua các phần từ của mảng ngẫu nhiên, từ độ dài của mảng tạo đến 24
                for(int i=temporaryKey.length;i< 24;i++)
                {                   
                    // chèn giá trị của phần tử đầu tiên vào mảng key mới
                    keyArray[i] =  temporaryKey[index];
                }
            }        
            // tạo ra đối tượng Cipher để mã hóa và giải mã
            // DES = Data Encryption Standard.
            // ECB = Electronic Codebook mode.
            // PKCS5Padding = PKCS #5-style padding.
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");     
            // Khởi tạo gồm
            // kiểu mã hóa, 
            c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));            
            byte[] encrypted = c.doFinal(toEncryptArray);            
            EncText = Base64.encode(encrypted);
 
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
        {
            JOptionPane.showMessageDialog(null, NoEx);
        }
        return EncText;        
    }
    
    public String DecryptText(String EncText)
    {
        String RawText = "";
        byte[] keyArray = new byte[24];
        byte[] temporaryKey;
        String key = "demoencryption";
        byte[] toEncryptArray = null;
 
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            temporaryKey = m.digest(key.getBytes("UTF-8"));           
 
            if(temporaryKey.length < 24) // DESede require 24 byte length key
            {
                int index = 0;
                for(int i=temporaryKey.length;i< 24;i++)
                {                  
                    keyArray[i] =  temporaryKey[index];
                }
            }
            
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.decode(EncText));   
 
            RawText = new String(decrypted, "UTF-8");                    
        }
        catch(NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException NoEx)
        {
            JOptionPane.showMessageDialog(null, NoEx);
        }      
 
        return RawText; 
 
    }
}
