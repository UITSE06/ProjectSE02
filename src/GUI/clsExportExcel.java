/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.io.*;
import java.nio.charset.Charset;
import javax.swing.*;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Hung
 */
public class clsExportExcel {
    clsExportExcel(){};
        
    public void exportTable(JXTable table, File file) throws IOException{
        TableModel model = table.getModel();
       // final ImageIcon Error = new ImageIcon(getClass().getResource("Images/close.png")); 
        //try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")))) {
            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i)+"\t");
            }
            out.write("\n");
            
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (model.getValueAt(i, j)!=null) {
                        out.write(model.getValueAt(i, j).toString()+"\t");
                    }else{
                        out.write(""+"\t");
                    }
                }
                out.write("\n");
            }
             
             if (JOptionPane.showConfirmDialog(null, "Tạo file thành công, tại địa chỉ: " + file + "\n Bạn có muốn mở file hay không?","Thành công",JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                try{
                 Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " +file);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Lỗi");
                }
            }
        }catch(Exception ioe){
            JOptionPane.showMessageDialog(null, "Tạo file không thành công","Thông báo lỗi",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public String jChooserPath(JPanel frm){
        String file = "" ;
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(frm);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();
            int len = file.length();
            String ext = "";
            if (len > 4) {
                ext = filename.substring(len-4, len);
            }
            
            if (ext.equals(".xls")) {
                file = path + "\\" + filename;
            }else{
                file = path + "\\" + filename + ".xls";
            }
        }
        return file;
    }
}
