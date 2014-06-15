/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import PUBLIC.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John
 */
public final class FrmListCourses extends javax.swing.JPanel {

    private DefaultTableModel dtmMH;

    private final clsCourses_BLL mhBLL = new clsCourses_BLL();
    private final clsMayjors_Public nP = new clsMayjors_Public();
    private final clsMayjors_BLL nBLL = new clsMayjors_BLL();
    private final clsFacultyBLL kBLL = new clsFacultyBLL();
    private String maNg = "";
    private String maKh = "";

    /**
     * Creates new form DanhSachMonHoc
     */
    public FrmListCourses() {
        initComponents();
        Load();
    }

    // Hàm này dùng chung cho cả form Nhập môn học
    public void Load() {
        initTable();
        loadMonHoc();
        setTableModel();
        FillComboKhoa();
    }

    // Hàm khởi tạo table Môn học
    public void initTable() {
        String[] titleMH = {"Mã môn học", "Mã môn học lý thuyết", "Tên môn học", "Loại môn học", "Số tiết"};
        dtmMH = new DefaultTableModel(titleMH, 0);
    }

    // Hàm set Model cho môn học
    public void setTableModel() {
        tbMonHoc.setModel(dtmMH);
        tbMonHoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ẩn cột mã môn lý thuyết đi
        tbMonHoc.getColumnModel().getColumn(1).setMinWidth(0);
        tbMonHoc.getColumnModel().getColumn(1).setMaxWidth(0);
        tbMonHoc.getColumnModel().getColumn(1).setWidth(0);
    }

    // Hàm load dữ liệu cho table môn học
    public void loadDataTable(ResultSet data) {
        try {
            while (data.next()) {
                Vector data_rows = new Vector();
                data_rows.add(data.getObject(1));
                data_rows.add(data.getObject(2));
                data_rows.add(data.getObject(3));
                data_rows.add(data.getObject(4));
                data_rows.add(data.getObject(5));
                dtmMH.addRow(data_rows);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Load tất cả các môn học của các khoa
    public void loadMonHoc() {
        try {
            ResultSet tbMH = mhBLL.LoadMonHoc();
            loadDataTable(tbMH);
        } catch (Exception ex) {
            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Load dữ liệu khoa lên combobox
    public void FillComboKhoa() {
        try {
            // Xóa tất cả item của combobox
            cbxKhoa.removeAllItems();
            // Load khoa lên
            ResultSet x = kBLL.LoadKhoa();
            while (x.next()) {
                cbxKhoa.addItem(new Item_Cbx(x.getString(1), x.getString(2)));
            }
            // Kiểm tra xem combobox Khoa đã bị xóa hết chưa
            if (cbxKhoa.getSelectedItem() != null) {
                Item_Cbx item = (Item_Cbx) cbxKhoa.getSelectedItem();
                // lấy giá trị mã ngành
                maKh = item.getId();
                FillComboNganh();
            }

            cbxKhoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cbxKhoa.getSelectedItem() != null) {
                        Item_Cbx item = (Item_Cbx) cbxKhoa.getSelectedItem();
                        maKh = item.getId();
                        FillComboNganh();
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FillComboNganh() {
        try {
            // Xóa toàn bộ những item của combobox cũ
            cbxNganh.removeAllItems();
            // Load ngành dựa vào mã khoa đang được chọn
            nP.setIdFaculty(maKh);
            ResultSet x = nBLL.fLoadInfoMayjors_idFaculty(nP);
            // add nó vào combobox Ngành
            while (x.next()) {
                cbxNganh.addItem(new Item_Cbx(x.getString(1), x.getString(2)));
            }
            // Kiểm tra khác null mới thực thi
            if (cbxNganh.getSelectedItem() != null) {
                Item_Cbx item = (Item_Cbx) cbxNganh.getSelectedItem();
                maNg = item.getId();
                LoadMHby_MaNganh();
            }
            //
            cbxNganh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cbxNganh.getSelectedItem() != null) {
                        Item_Cbx item = (Item_Cbx) cbxNganh.getSelectedItem();
                        maNg = item.getId();
                        LoadMHby_MaNganh();
                    }
                }
            });

        } catch (Exception ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Load danh sách môn học theo ngành
    public void LoadMHby_MaNganh() {
        try {
            nP.setIdMayjors(maNg);
            ResultSet mhNganh = mhBLL.LoadMH_Nganh(nP);
            // Khởi tạo lại table để thêm dữ liệu
            initTable();
            // Đổ dữ liệu vào bảng
            loadDataTable(mhNganh);
            // setModel cho table
            setTableModel();
        } catch (Exception ex) {
            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
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

        TopPanel = new org.jdesktop.swingx.JXPanel();
        Title = new org.jdesktop.swingx.JXLabel();
        topPanel = new org.jdesktop.swingx.JXPanel();
        btnThemMoi = new org.jdesktop.swingx.JXButton();
        btnLamMoi = new org.jdesktop.swingx.JXButton();
        btnHuy = new org.jdesktop.swingx.JXButton();
        btnLuu = new org.jdesktop.swingx.JXButton();
        lbNganh = new org.jdesktop.swingx.JXLabel();
        cbxNganh = new javax.swing.JComboBox();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        cbxKhoa = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMonHoc = new org.jdesktop.swingx.JXTable();

        setEnabled(false);

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("DANH SÁCH MÔN HỌC");
        Title.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Title.setTextAlignment(org.jdesktop.swingx.JXLabel.TextAlignment.CENTER);

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThemMoi.setName("btnThemMoi"); // NOI18N
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLamMoi.setName("btnLamMoi"); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHuy.setName("btnHuy"); // NOI18N

        btnLuu.setText("Lưu");
        btnLuu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuu.setName("btnLuu"); // NOI18N

        lbNganh.setText("Ngành học:");
        lbNganh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jXLabel1.setText("Khoa:");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(260, 260, 260)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMonHoc.setEditable(false);
        jScrollPane1.setViewportView(tbMonHoc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Mở form nhập thêm môn học
    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        NhapMonHoc nhapMonHoc = new NhapMonHoc(this);
        nhapMonHoc.setVisible(true);
        enableComponents(this, false);
    }//GEN-LAST:event_btnThemMoiActionPerformed

    // Hàm enable/disable các component
    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    // Load lại danh sách môn học
    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        Load();
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnHuy;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnLuu;
    private org.jdesktop.swingx.JXButton btnThemMoi;
    private javax.swing.JComboBox cbxKhoa;
    private javax.swing.JComboBox cbxNganh;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel lbNganh;
    private org.jdesktop.swingx.JXTable tbMonHoc;
    private org.jdesktop.swingx.JXPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
