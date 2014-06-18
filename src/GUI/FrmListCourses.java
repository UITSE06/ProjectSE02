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
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
    private String maKh = "";

    private DefaultComboBoxModel modelKhoa;
    private DefaultComboBoxModel modelNganh;
    private DefaultComboBoxModel modelHocKi;

    private Item_Cbx itemKh;
    private Item_Cbx itemNg;

    /**
     * Creates new form DanhSachMonHoc
     *
     * @throws java.lang.Exception
     */
    public FrmListCourses() throws Exception {
        initComponents();
        Load();
    }

    // Hàm này dùng chung cho cả form Nhập môn học
    public void Load() throws Exception {
        loadDataTable();
        this.modelKhoa = new DefaultComboBoxModel(LoadKhoa().toArray());
        cbxKhoa.setModel(modelKhoa);
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
    public void loadDataTable() throws Exception {
        try {
            clsMayjors_Public mayjorP = new clsMayjors_Public();
            initTable();

            // nếu comboKhoa được chọn thì load môn học theo khoa
            if (cbxNganh.getSelectedItem() != null && cbxHocKi.getSelectedItem() != null) {
                itemNg = (Item_Cbx) cbxNganh.getSelectedItem();
                mayjorP.setIdMayjors(itemNg.getId());
                if (cbxHocKi.getSelectedItem().equals("Tất cả")) {
                    mayjorP.setNoSemester(0);
                } else {
                    mayjorP.setNoSemester(Integer.parseInt(cbxHocKi.getSelectedItem().toString()));
                }
            }
            ResultSet data = mhBLL.LoadMH_Nganh(mayjorP);
            while (data.next()) {
                Vector data_rows = new Vector();
                data_rows.add(data.getObject(1));
                data_rows.add(data.getObject(2));
                data_rows.add(data.getObject(3));
                data_rows.add(data.getObject(4));
                data_rows.add(data.getObject(5));
                dtmMH.addRow(data_rows);
            }
            // settable cho table môn học
            setTableModel();
        } catch (SQLException ex) {
            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // lấy toàn bộ thông tin khoa, lưu vào mảng
    public ArrayList<Item_Cbx> LoadKhoa() {
        ArrayList<Item_Cbx> arrKh = new ArrayList<>();
        try {
            ResultSet rsKH = kBLL.LoadKhoa();
            while (rsKH.next()) {
                arrKh.add(new Item_Cbx(rsKH.getString(1), rsKH.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrKh;
    }

    // Xử lí sự kiện cho combobox khoa
    public void FillComboKhoa() {
        if (cbxKhoa.getSelectedItem() != null) {
            itemKh = (Item_Cbx) cbxKhoa.getSelectedItem();
            maKh = itemKh.getId();
            // Khi comboKhoa thay doi, keo theo thay doi gia tri comboNganh
            FillComboNganh();
        }

        // xử lý sự kiện cho combo khoa
        cbxKhoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbxKhoa.getSelectedItem() != null) {
                    itemKh = (Item_Cbx) cbxKhoa.getSelectedItem();
                    // Lấy giá trị mã khoa
                    maKh = itemKh.getId();
                    // Khi comboKhoa thay doi, keo theo thay doi gia tri comboNganh
                    FillComboNganh();
                }
            }
        });
    }

    // lấy toàn bộ dữ liệu của ngành và add vào combobox ngành
    public ArrayList<Item_Cbx> LoadNganh() {
        ArrayList<Item_Cbx> arrNg = new ArrayList<>();
        try {
            nP.setIdFaculty(maKh);
            // lấy thông tin ngành theo mã khoa
            ResultSet rsNg = nBLL.fLoadInfoMayjors_idFaculty(nP);
            arrNg.add(new Item_Cbx("01", "Tất cả"));
            while (rsNg.next()) {
                arrNg.add(new Item_Cbx(rsNg.getString(1), rsNg.getString(2), rsNg.getString(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrNg;
    }

    // dữ liệu cho combobox học kì
    public ArrayList<String> LoadHocKi() {
        ArrayList<String> arrHk = new ArrayList<>();
        arrHk.add("Tất cả");
        return arrHk;
    }

    public void FillComboNganh() {
        try {

            //reload- xóa dữ liệu trong bảng
            if (dtmMH.getRowCount() > 0) {
                int a = dtmMH.getRowCount();
                for (int i = a - 1; i >= 0; i--) {
                    dtmMH.removeRow(i);
                }
            }
            // set model cho các combobox
            this.modelNganh = new DefaultComboBoxModel(LoadNganh().toArray());
            cbxNganh.setModel(modelNganh);

            this.modelHocKi = new DefaultComboBoxModel(LoadHocKi().toArray());
            cbxHocKi.setModel(modelHocKi);

            itemNg = (Item_Cbx) cbxNganh.getSelectedItem();
            if (itemNg != null && !itemNg.getDescription().equals("Tất cả")) {
                // Xóa item của combobox Hocki
                cbxHocKi.removeAllItems();
                cbxHocKi.addItem("Tất cả");
                for (int i = 1; i <= Integer.parseInt(itemNg.getOption()); i++) {
                    cbxHocKi.addItem(i);
                }
                try {
                    loadDataTable();
                } catch (Exception ex) {
                    Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // xử lý sự kiện
            cbxNganh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    itemNg = (Item_Cbx) cbxNganh.getSelectedItem();
                    if (itemNg != null && !itemNg.getDescription().equals("Tất cả")) {
                        // Xóa item của combobox Hocki
                        cbxHocKi.removeAllItems();
                        cbxHocKi.addItem("Tất cả");
                        // thêm các học kì vào combo Học kì
                        for (int i = 1; i <= Integer.parseInt(itemNg.getOption()); i++) {
                            cbxHocKi.addItem(i);
                        }
                        try {
                            loadDataTable();
                        } catch (Exception ex) {
                            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            // Sự kiện trên combo Học kì
            cbxHocKi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cbxHocKi.getSelectedItem() != null) {
                        try {
                            loadDataTable();
                        } catch (Exception ex) {
                            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
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
        btnXoa = new org.jdesktop.swingx.JXButton();
        lbNganh = new org.jdesktop.swingx.JXLabel();
        cbxNganh = new javax.swing.JComboBox();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        cbxKhoa = new javax.swing.JComboBox();
        btnCapNhat = new org.jdesktop.swingx.JXButton();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        cbxHocKi = new javax.swing.JComboBox();
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
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThemMoi.setText("Thêm mới");
        btnThemMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemMoi.setName("btnThemMoi"); // NOI18N
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLamMoi.setName("btnLamMoi"); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbNganh.setText("Ngành học:");
        lbNganh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jXLabel1.setText("Khoa:");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapNhat.setName("btnThemMoi"); // NOI18N
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jXLabel2.setText("Học kì:");
        jXLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addGap(117, 117, 117)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        FrmInsertCourse nhapMonHoc = new FrmInsertCourse(this);
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
        try {
            // TODO add your handling code here:
            loadDataTable();
        } catch (Exception ex) {
            Logger.getLogger(FrmListCourses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    // Xóa môn học
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (tbMonHoc.getSelectedRow() >= 0) {
            try {
                int ySn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa môn học này?", "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
                if (ySn == JOptionPane.YES_OPTION) {
                    clsCourses_Public MHP = new clsCourses_Public();
                    String maMH = tbMonHoc.getValueAt(tbMonHoc.getSelectedRow(), 0).toString();
                    MHP.setMaMon(maMH);
                    ResultSet delCourses = mhBLL.Courses_Delete(MHP);
                    while (delCourses.next()) {
                        if (delCourses.getInt(1) == 1) {
                            JOptionPane.showMessageDialog(this, "Xóa thành công môn học.");
                            loadDataTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "Không thể xóa môn học, đã phát sinh lỗi");
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Không thể xóa môn học, đã phát sinh lỗi");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    // Cập nhật thông tin môn học
    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        if (tbMonHoc.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học cần cập nhật", "THẤT BẠI", JOptionPane.ERROR_MESSAGE);
        } else {
            FrmInsertCourse frmInsertCourses = new FrmInsertCourse(this, tbMonHoc.getValueAt(tbMonHoc.getSelectedRow(), 0).toString());
            frmInsertCourses.setTitelText("CẬP NHẬT MÔN HỌC");
            frmInsertCourses.setVisible(true);
            enableComponents(this, false);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnCapNhat;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnThemMoi;
    private org.jdesktop.swingx.JXButton btnXoa;
    private javax.swing.JComboBox cbxHocKi;
    private javax.swing.JComboBox cbxKhoa;
    private javax.swing.JComboBox cbxNganh;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel lbNganh;
    private org.jdesktop.swingx.JXTable tbMonHoc;
    private org.jdesktop.swingx.JXPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
