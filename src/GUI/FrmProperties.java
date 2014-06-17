package GUI;

import DAL.Encrytion_DAL;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class FrmProperties extends javax.swing.JFrame {

    /**
     * Creates new form FrmProperties
     */
    FrmConfig config;
    String server = "";                 // Ten Server.
    int portNumber = Integer.MIN_VALUE;
    String userName = "";               // UserName SQL
    String password = "";               // Password 
    Connection connect = null;          // Khoi tao connector

    public FrmProperties(FrmConfig temp) {
        initComponents();
        config = temp;
        CheckCondition();
    }

    public final void CheckCondition() {
        if (cbAuthen.getSelectedItem().toString().equals("Windows Authentication")) {
            txtUser.setText("");
            txtPass.setText("");
            txtUser.setEditable(false);
            txtPass.setEditable(false);
        } else {
            txtUser.setEditable(true);
            txtPass.setEditable(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanel3 = new org.jdesktop.swingx.JXPanel();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        jXPanel4 = new org.jdesktop.swingx.JXPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        txtServer = new org.jdesktop.swingx.JXTextField();
        txtUser = new org.jdesktop.swingx.JXTextField();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        cbAuthen = new javax.swing.JComboBox();
        btnConnect = new org.jdesktop.swingx.JXButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        txtPass = new javax.swing.JPasswordField();
        jXPanel5 = new org.jdesktop.swingx.JXPanel();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();

        javax.swing.GroupLayout jXPanel3Layout = new javax.swing.GroupLayout(jXPanel3);
        jXPanel3.setLayout(jXPanel3Layout);
        jXPanel3Layout.setHorizontalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jXPanel3Layout.setVerticalGroup(
            jXPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(428, 366));
        setResizable(false);

        jXPanel4.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        jXLabel2.setText("Server:");
        jXLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtServer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtServerFocusLost(evt);
            }
        });

        txtUser.setEditable(false);

        jXLabel4.setText("Password:");
        jXLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jXLabel3.setText("UserName:");
        jXLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jXLabel5.setText("Authentication:");
        jXLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbAuthen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAuthen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Windows Authentication", "SQL Server Authentication" }));
        cbAuthen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbAuthenItemStateChanged(evt);
            }
        });

        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iConvert-icon.png"))); // NOI18N
        btnConnect.setText("Connect");
        btnConnect.setEnabled(false);
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel1.setText("KẾT NỐI CƠ SỞ DỮ LIỆU");
        jXLabel1.setFont(new java.awt.Font("Times New Roman", 1, 26)); // NOI18N

        javax.swing.GroupLayout jXPanel4Layout = new javax.swing.GroupLayout(jXPanel4);
        jXPanel4.setLayout(jXPanel4Layout);
        jXPanel4Layout.setHorizontalGroup(
            jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel4Layout.createSequentialGroup()
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jXPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jXPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbAuthen, 0, 237, Short.MAX_VALUE)
                            .addComponent(txtServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPass))))
                .addGap(16, 16, 16))
            .addComponent(jXLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXPanel4Layout.setVerticalGroup(
            jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel4Layout.createSequentialGroup()
                .addComponent(jXLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAuthen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jXPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jXLabel6.setText("Lưu ý: Nếu bạn chưa biết Server name, hoặc chưa có quyền truy cập  cơ sở dữ liệu. Xin vui lòng liên hệ quản trị viên để được cấp quyền.");
        jXLabel6.setAutoscrolls(true);
        jXLabel6.setLineWrap(true);

        javax.swing.GroupLayout jXPanel5Layout = new javax.swing.GroupLayout(jXPanel5);
        jXPanel5.setLayout(jXPanel5Layout);
        jXPanel5Layout.setHorizontalGroup(
            jXPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXPanel5Layout.setVerticalGroup(
            jXPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        // Kiểm tra, txtUser và txtPass
        if (cbAuthen.getSelectedIndex() == 1) {
            if (txtUser.getText().equals("") && new String(txtPass.getPassword()).equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên đăng nhập và mật khẩu.", "LỖI", JOptionPane.YES_OPTION);
                return;
            }
        }
        // Kiểm tra, kết nối bằng server, user, pass có được không
        if (cbAuthen.getSelectedIndex() == 0) {
            try {
                // Tạo chuỗi kết nối
                String url = "jdbc:sqlserver://" + txtServer.getText() + ";integratedSecurity=true";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // Khởi tạo kết nối
                this.connect = DriverManager.getConnection(url);

                // nếu kết nối thành công, tiến hành lưu vào file.
                saveConnectString(txtServer.getText(), "", "");
                config.enableComponents(config, true);
                config.diableBtnMo();
                this.dispose();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(FrmProperties.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Vui lòng liên hệ quản trị viên.", "KHÔNG THỂ KẾT NỐI ĐẾN CƠ SỞ DỮ LIỆU.", JOptionPane.YES_OPTION);
            }
        } else { // ngược lại, kết nối phải dùng đến user và pass
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName(txtServer.getText());
            ds.setPortNumber(1433);
            ds.setUser(txtUser.getText());
            ds.setPassword(new String(txtPass.getPassword()));
            try {
                this.connect = ds.getConnection();
                // Nếu kết nối thành công, tiến hành lưu vào file
                saveConnectString(txtServer.getText(), txtUser.getText(), new String(txtPass.getPassword()));
                config.enableComponents(config, true);
                config.diableBtnMo();
                this.dispose();
            } catch (SQLServerException ex) {
                Logger.getLogger(FrmProperties.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Vui lòng liên hệ quản trị viên.", "KHÔNG THỂ KẾT NỐI ĐẾN CƠ SỞ DỮ LIỆU.", JOptionPane.YES_OPTION);
            }
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    // Nếu kết nối thành công, tiến hành lưu thông tin kết nối vào file
    public void saveConnectString(String serverName, String userName, String passWord) {
        try {
            Encrytion_DAL encryDAL = new Encrytion_DAL();
            FileOutputStream fos = new FileOutputStream("connectionString", false);
            PrintWriter pw = new PrintWriter(fos);
            // Đọc file
            // Lưu tên server
            pw.println(serverName);
            // Lưu user và pass sau khi đã được mã hóa
            pw.println(encryDAL.EncryptText(userName));
            pw.println(encryDAL.EncryptText(passWord));
            pw.close();
            fos.flush();
            fos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cbAuthenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbAuthenItemStateChanged
        // TODO add your handling code here:
        CheckCondition();
    }//GEN-LAST:event_cbAuthenItemStateChanged

    private void txtServerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtServerFocusLost
        // TODO add your handling code here:
        if (!txtServer.getText().isEmpty()) {
            btnConnect.setEnabled(true);
        } else {
            btnConnect.setEnabled(false);
        }
    }//GEN-LAST:event_txtServerFocusLost

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnConnect;
    private javax.swing.JComboBox cbAuthen;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXPanel jXPanel3;
    private org.jdesktop.swingx.JXPanel jXPanel4;
    private org.jdesktop.swingx.JXPanel jXPanel5;
    private javax.swing.JPasswordField txtPass;
    private org.jdesktop.swingx.JXTextField txtServer;
    private org.jdesktop.swingx.JXTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
