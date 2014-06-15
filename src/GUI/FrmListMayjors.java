/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import PUBLIC.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.combobox.ListModelComboBoxWrapper;

/**
 *
 * @author John
 */
public class FrmListMayjors extends javax.swing.JPanel {

    /**
     * Creates new form DangKiMonHoc2
     */
    private DefaultTableModel dtm;
    private int rowSelected = 0;
    private final clsMayjors_BLL ngBLL = new clsMayjors_BLL();
    private final clsFacultyBLL kBLL = new clsFacultyBLL();
    private boolean themMoi = false;
    private ResultSet rsKhoa;

    public FrmListMayjors() {
        initComponents();
        txtTenNganh.setDocument(new ClsLimitDocument_BLL(50));
        LayDuLieu();
        //
        tblDSNganh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDSNganh.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    rowSelected = tblDSNganh.getSelectedRow();
                    bindingData(rowSelected);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmListFaculty.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FullPanel = new org.jdesktop.swingx.JXPanel();
        TopInFull = new org.jdesktop.swingx.JXPanel();
        TopLeft = new org.jdesktop.swingx.JXPanel();
        txtMaNganh = new org.jdesktop.swingx.JXTextField();
        lbMSSV = new org.jdesktop.swingx.JXLabel();
        txtTenNganh = new org.jdesktop.swingx.JXTextField();
        lbHoTen1 = new org.jdesktop.swingx.JXLabel();
        lbHoTen2 = new org.jdesktop.swingx.JXLabel();
        HoTen = new org.jdesktop.swingx.JXLabel();
        cboKhoa = new javax.swing.JComboBox();
        spNumSemester = new javax.swing.JSpinner();
        ControlPanel = new org.jdesktop.swingx.JXPanel();
        btnThem = new org.jdesktop.swingx.JXButton();
        btnCapNhat = new org.jdesktop.swingx.JXButton();
        btnHuyThaoTac = new org.jdesktop.swingx.JXButton();
        btnLuu = new org.jdesktop.swingx.JXButton();
        btnLamMoi = new org.jdesktop.swingx.JXButton();
        btnXoa = new org.jdesktop.swingx.JXButton();
        btnXuatDS = new org.jdesktop.swingx.JXButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSNganh = new org.jdesktop.swingx.JXTable();
        TopPanel = new org.jdesktop.swingx.JXPanel();
        Title = new org.jdesktop.swingx.JXLabel();

        TopLeft.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Ngành"));

        txtMaNganh.setEnabled(false);

        lbMSSV.setText("Mã Ngành:");
        lbMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTenNganh.setEnabled(false);

        lbHoTen1.setText("Số học kì:");
        lbHoTen1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHoTen2.setText("Thuộc Khoa:");
        lbHoTen2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        HoTen.setText("Tên Ngành:");
        HoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboKhoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhoa.setEnabled(false);

        spNumSemester.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spNumSemester.setEnabled(false);

        javax.swing.GroupLayout TopLeftLayout = new javax.swing.GroupLayout(TopLeft);
        TopLeft.setLayout(TopLeftLayout);
        TopLeftLayout.setHorizontalGroup(
            TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopLeftLayout.createSequentialGroup()
                        .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(TopLeftLayout.createSequentialGroup()
                        .addComponent(lbHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spNumSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TopLeftLayout.createSequentialGroup()
                        .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TopLeftLayout.createSequentialGroup()
                        .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtTenNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        TopLeftLayout.setVerticalGroup(
            TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeftLayout.createSequentialGroup()
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spNumSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TopInFullLayout = new javax.swing.GroupLayout(TopInFull);
        TopInFull.setLayout(TopInFullLayout);
        TopInFullLayout.setHorizontalGroup(
            TopInFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TopInFullLayout.setVerticalGroup(
            TopInFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopInFullLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(TopLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        TopLeft.getAccessibleContext().setAccessibleName("ThongTinNganh");

        ControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển"));
        ControlPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        ControlPanel.add(btnThem);

        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        ControlPanel.add(btnCapNhat);

        btnHuyThaoTac.setText("Hủy Thao Tác");
        btnHuyThaoTac.setEnabled(false);
        btnHuyThaoTac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyThaoTacActionPerformed(evt);
            }
        });
        ControlPanel.add(btnHuyThaoTac);

        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        ControlPanel.add(btnLuu);

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.setEnabled(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        ControlPanel.add(btnLamMoi);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        ControlPanel.add(btnXoa);

        btnXuatDS.setText("Xuất Danh Sách");
        ControlPanel.add(btnXuatDS);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách ngành học"));

        tblDSNganh.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDSNganh.setEditable(false);
        tblDSNganh.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblDSNganh);

        javax.swing.GroupLayout FullPanelLayout = new javax.swing.GroupLayout(FullPanel);
        FullPanel.setLayout(FullPanelLayout);
        FullPanelLayout.setHorizontalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FullPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TopInFull, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        FullPanelLayout.setVerticalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(TopInFull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("DANH SÁCH NGÀNH HỌC");
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
                .addGap(2, 2, 2)
                .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /*@AnhQuan
     *them rsKhoa.BeforeFist và break;
     */
    private void LayDuLieu() {
        try {            
            //lay thong tin ve khoa
            if (cboKhoa.getItemCount() > 0) {
                cboKhoa.removeAllItems();
            }
            rsKhoa = kBLL.LayTTKhoa();
            while (rsKhoa.next()) {
                String khoa = rsKhoa.getString(2);//2 tuc la lay cai ten khoa
                cboKhoa.addItem(khoa);
            }
            //lay thong tin ve nganh
            ResultSet rs = ngBLL.LayTTNganh();
            String[] titile = {"Mã ngành", "Tên ngành", "Thuộc khoa", "Số học kì"};
            dtm = new DefaultTableModel(titile, 0);

            while (rs.next()) {
                Vector data_rows = new Vector();
                data_rows.add(rs.getObject(1));
                data_rows.add(rs.getObject(2));
                //lay cai ten khoa
                rsKhoa.beforeFirst();
                while (rsKhoa.next()) {
                    if (rsKhoa.getString(1).equals(rs.getString(3)))//neu ma khoa giong nhau thi lay cai ten khoa
                    {
                        data_rows.add(rsKhoa.getObject(2));
                        break;
                    }
                }
                //
                data_rows.add(rs.getObject(4));
                dtm.addRow(data_rows);
            }
            tblDSNganh.setModel(dtm);

        } catch (Exception ex) {
            Logger.getLogger(FrmListFaculty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* AnhQuan
    *Them dieu kien if btnLuu.isEnable
    */
    private void bindingData(int row) throws ParseException {
        if (!btnLuu.isEnabled()) {
            try {
                if (row < 0) {
                    row = 0;
                }
                txtMaNganh.setText(tblDSNganh.getStringAt(row, 0));
                txtTenNganh.setText(tblDSNganh.getStringAt(row, 1));
                cboKhoa.setSelectedItem(tblDSNganh.getStringAt(row, 2));
                spNumSemester.setValue(Integer.parseInt(tblDSNganh.getStringAt(row, 3)));
            } catch (NumberFormatException e) {
                Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        txtMaNganh.setText("MN00000");
        txtTenNganh.setEnabled(true);
        spNumSemester.setEnabled(true);
        cboKhoa.setEnabled(true);
        txtTenNganh.setText("");
        //thay doi trang thai btn
        btnThem.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnLuu.setEnabled(true);
        btnLamMoi.setEnabled(true);
        btnXoa.setEnabled(false);
        btnXuatDS.setEnabled(false);
        btnHuyThaoTac.setEnabled(true);
        //gan themMoi la true de biet la dang them moi
        themMoi = true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        txtTenNganh.setEnabled(true);
        spNumSemester.setEnabled(true);
        cboKhoa.setEnabled(true);
        //thay doi trang thai btn
        btnThem.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnLuu.setEnabled(true);
        btnLamMoi.setEnabled(true);
        btnXoa.setEnabled(false);
        btnXuatDS.setEnabled(false);
        btnHuyThaoTac.setEnabled(true);
        //gan themMoi la false để biết là đang cập nhật
        themMoi = false;
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        //kiem tra input
        //kiem tra so hoc ki co hop le khong?
        if (txtMaNganh.getText().isEmpty() || txtTenNganh.getText().isEmpty() || spNumSemester.getValue() == null) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đủ thông tin");
            return;
        }
        //tao doi tuong public
        clsMayjors_Public p = new clsMayjors_Public();
        p.setIdMayjors(txtMaNganh.getText());
        p.setNameMayjors(txtTenNganh.getText());
        p.setNoSemester((int)spNumSemester.getValue());
        //lay ma khoa de luu xuong du lieu
        try {
            rsKhoa.beforeFirst();
            while (rsKhoa.next()) {
                String tenKhoa = rsKhoa.getString(2);//2 tuc la lay cai ten khoa
                if (tenKhoa.equals((String) cboKhoa.getSelectedItem())) {
                    p.setIdFaculty(rsKhoa.getString(1));//lay cai ma khoa roi them zo
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(FrmListMayjors.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (themMoi)//nếu thêm mới thì gọi hàm thêm
            {
                //goi den ham luu cua BLL
                if (ngBLL.ThemNganh(p) != 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            } else //không thêm mới tức là cập nhật
            {
                if (ngBLL.CapNhatNganh(p) != 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmListFaculty.class.getName()).log(Level.SEVERE, null, ex);
        }

        //disable cac component
        txtTenNganh.setEnabled(false);
        spNumSemester.setEnabled(false);
        cboKhoa.setEnabled(false);
        //thay doi trang thai btn
        btnThem.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnLuu.setEnabled(false);
        btnLamMoi.setEnabled(false);
        btnXoa.setEnabled(true);
        btnXuatDS.setEnabled(true);
        btnHuyThaoTac.setEnabled(false);
        //làm mới lại dữ liệu
        LayDuLieu();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        if (txtTenNganh.isEnabled()) {
            txtTenNganh.setText("");
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (txtMaNganh.getText().isEmpty()) {
            return;
        }
        clsMayjors_Public p = new clsMayjors_Public();
        p.setIdMayjors(txtMaNganh.getText());
        try {
            if (ngBLL.XoaNganh(p) != 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                LayDuLieu();//làm mới dữ liệu
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmListFaculty.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyThaoTacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyThaoTacActionPerformed
        btnHuyThaoTac.setEnabled(false);
//disable cac component
        txtTenNganh.setEnabled(false);
        spNumSemester.setEnabled(false);
        cboKhoa.setEnabled(false);
        //thay doi trang thai btn
        btnThem.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnLuu.setEnabled(false);
        btnLamMoi.setEnabled(false);
        btnXoa.setEnabled(true);
        btnXuatDS.setEnabled(true);
        //làm mới lại dữ liệu
        LayDuLieu();
    }//GEN-LAST:event_btnHuyThaoTacActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel ControlPanel;
    private org.jdesktop.swingx.JXPanel FullPanel;
    private org.jdesktop.swingx.JXLabel HoTen;
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopInFull;
    private org.jdesktop.swingx.JXPanel TopLeft;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnCapNhat;
    private org.jdesktop.swingx.JXButton btnHuyThaoTac;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnLuu;
    private org.jdesktop.swingx.JXButton btnThem;
    private org.jdesktop.swingx.JXButton btnXoa;
    private org.jdesktop.swingx.JXButton btnXuatDS;
    private javax.swing.JComboBox cboKhoa;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel lbHoTen1;
    private org.jdesktop.swingx.JXLabel lbHoTen2;
    private org.jdesktop.swingx.JXLabel lbMSSV;
    private javax.swing.JSpinner spNumSemester;
    private org.jdesktop.swingx.JXTable tblDSNganh;
    private org.jdesktop.swingx.JXTextField txtMaNganh;
    private org.jdesktop.swingx.JXTextField txtTenNganh;
    // End of variables declaration//GEN-END:variables
}
