/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.Item_Cbx;
import BLL.clsStaff_BLL;
import PUBLIC.clsStaff_Public;
import DAL.Crypter;
import DAL.SQLServerConnector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 *
 * @author Hung
 */
public final class FrmStaffManage extends javax.swing.JPanel {

    /**
     * Creates new form DangKyMon
     */
    private SQLServerConnector connect;
    private ResultSet rs;
    private DefaultTableModel dtm;
    private DefaultTableModel model;
    private int rowSelected = 0;
    private boolean btnThemSelect = true;
    File f = null;

    private final clsStaff_BLL nvBLL = new clsStaff_BLL();
    private final clsStaff_Public nvP = new clsStaff_Public();
    private final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public FrmStaffManage() throws Exception {
        initComponents();
        LoadTable();
        btnHuy.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuuDS.setEnabled(false);
        String[] titiles = {"STT", "Họ và Tên", "Tuổi", "Địa chỉ", "Mã nhân viên"};
        model = new DefaultTableModel(titiles, 0);
        tb_Excel.setModel(model);
    }

    public void LoadTable() throws Exception {
        initTable();
        LoadData();
        setTableModel();
    }

    public void setComponents(boolean b) {
        txtHoTen.setEditable(b);
        txtMaNV.setEditable(b);
        jradioNam.setEnabled(b);
        jradioNu.setEnabled(b);
        dateNgaySinh.setEditable(b);
        txtDiaChi.setEditable(b);
        cbxChucVu.setEnabled(b);
        dateNgaySinh.setFormats(formatDate);
        btnTim.setEnabled(b);
    }

    public void initTable() {
        String[] titile = {"Mã nhân viên", "Họ & tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Tên đăng nhập", "Mật khẩu", "Hình đại diện"};
        dtm = new DefaultTableModel(titile, 0);
    }

    public void LoadData() throws Exception {
        rs = nvBLL.LoadNV();
        while (rs.next()) {
            Vector data_rows;
            data_rows = new Vector();
            data_rows.add(rs.getObject(1));
            data_rows.add(rs.getObject(2));
            data_rows.add(formatDate.format(rs.getObject(3)));
            data_rows.add(rs.getObject(4));
            data_rows.add(rs.getObject(5));
            data_rows.add(rs.getObject(6));
            data_rows.add(rs.getObject(7));
            data_rows.add(rs.getObject(8));
            dtm.addRow(data_rows);
        }
        tbNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    rowSelected = tbNhanVien.getSelectedRow();
                    bindingData(rowSelected);
                } catch (ParseException ex) {
                    Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //Fillcombo();
    }

    public void setTableModel() {
        tbNhanVien.setModel(dtm);
        tbNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ẩn cột Huyện đi
        tbNhanVien.getColumnModel().getColumn(5).setMinWidth(0);
        tbNhanVien.getColumnModel().getColumn(5).setMaxWidth(0);
        tbNhanVien.getColumnModel().getColumn(5).setWidth(0);
        // ẩn cột mật khẩu đi
        tbNhanVien.getColumnModel().getColumn(6).setMinWidth(0);
        tbNhanVien.getColumnModel().getColumn(6).setMaxWidth(0);
        tbNhanVien.getColumnModel().getColumn(6).setWidth(0);
        // ẩn cột hình đại diện đi
        tbNhanVien.getColumnModel().getColumn(7).setMinWidth(0);
        tbNhanVien.getColumnModel().getColumn(7).setMaxWidth(0);
        tbNhanVien.getColumnModel().getColumn(7).setWidth(0);
        /// set Row Selection Mode.
        //tbSinhVien.setColumnSelectionAllowed(false);
        //tbSinhVien.setRowSelectionAllowed(true);
        if (tbNhanVien.getRowCount() > 0) {
            tbNhanVien.setRowSelectionInterval(0, 0);
        }
    }

    public void bindingData(int row) throws ParseException {
        try {
            if (row < 0) {
                row = 0;
            }
            txtMaNV.setText(tbNhanVien.getStringAt(row, 0));
            txtHoTen.setText(tbNhanVien.getStringAt(row, 1));
            dateNgaySinh.setDate(formatDate.parse(tbNhanVien.getStringAt(row, 2)));
            if (tbNhanVien.getStringAt(row, 3).equals("Nam")) {
                jradioNam.setSelected(true);
            } else {
                jradioNu.setSelected(true);
            }
            txtDiaChi.setText(tbNhanVien.getStringAt(row, 4));
            byte[] databyte;
            if ((tbNhanVien.getModel().getValueAt(row, 7)) != null) {
                Object x = (Object) tbNhanVien.getModel().getValueAt(row, 7);
                databyte = (byte[]) x;
                Image img = getToolkit().createImage(databyte);
                ImageIcon icon = new ImageIcon(img);
                //lbImage.setSize(icon.getIconHeight(), icon.getIconWidth());
                lbImage.setIcon(icon);
            } else {
                lbImage.setIcon(null);
            }

        } catch (ParseException e) {
            Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void FillcomboChucVu() {
        try {
            ResultSet x = connect.excuteStore("{call LoadDT}");
            while (x.next()) {
                String dTuong = x.getString(2);
                cbxChucVu.addItem(dTuong);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        fullPanel = new org.jdesktop.swingx.JXPanel();
        leftPanel = new org.jdesktop.swingx.JXPanel();
        lefttopPanel = new org.jdesktop.swingx.JXPanel();
        lbHoTen = new org.jdesktop.swingx.JXLabel();
        lbMaNV = new org.jdesktop.swingx.JXLabel();
        txtMaNV = new org.jdesktop.swingx.JXTextField();
        lbGioiTinh = new org.jdesktop.swingx.JXLabel();
        lbNgaySinh = new org.jdesktop.swingx.JXLabel();
        dateNgaySinh = new org.jdesktop.swingx.JXDatePicker();
        lbKhoa = new org.jdesktop.swingx.JXLabel();
        lbHuyen = new org.jdesktop.swingx.JXLabel();
        txtHoTen = new org.jdesktop.swingx.JXTextField();
        txtDiaChi = new org.jdesktop.swingx.JXTextField();
        jradioNam = new javax.swing.JRadioButton();
        jradioNu = new javax.swing.JRadioButton();
        lbImage = new org.jdesktop.swingx.JXLabel();
        cbxChucVu = new javax.swing.JComboBox();
        btnTim = new org.jdesktop.swingx.JXButton();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        btnTaoMoi = new org.jdesktop.swingx.JXButton();
        btnCapNhat = new org.jdesktop.swingx.JXButton();
        btnLuuLai = new org.jdesktop.swingx.JXButton();
        btnXoaNV = new org.jdesktop.swingx.JXButton();
        btnLamMoi = new org.jdesktop.swingx.JXButton();
        btnHuyTT = new org.jdesktop.swingx.JXButton();
        btnXuatDS = new org.jdesktop.swingx.JXButton();
        leftbottomPanel = new org.jdesktop.swingx.JXPanel();
        table = new javax.swing.JScrollPane();
        tb_Excel = new org.jdesktop.swingx.JXTable();
        excelcontrolPanel = new org.jdesktop.swingx.JXPanel();
        btnNhapExcel = new org.jdesktop.swingx.JXButton();
        btnLuuDS = new org.jdesktop.swingx.JXButton();
        btnXoa = new org.jdesktop.swingx.JXButton();
        btnHuy = new org.jdesktop.swingx.JXButton();
        rightPanel = new org.jdesktop.swingx.JXPanel();
        tableThongTin = new javax.swing.JScrollPane();
        tbNhanVien = new org.jdesktop.swingx.JXTable();
        topPanel18 = new org.jdesktop.swingx.JXPanel();
        jXSearchField1 = new org.jdesktop.swingx.JXSearchField();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();

        lefttopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        lbHoTen.setText("Họ tên:");
        lbHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbMaNV.setText("Mã nhân viên:");
        lbMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMaNV.setEnabled(false);

        lbGioiTinh.setText("Giới tính: ");
        lbGioiTinh.setFocusable(false);
        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbNgaySinh.setText("Ngày sinh: ");
        lbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dateNgaySinh.setEditable(false);
        dateNgaySinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateNgaySinhFocusLost(evt);
            }
        });

        lbKhoa.setText("Chức vụ:");
        lbKhoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHuyen.setText("Địa chỉ:");
        lbHuyen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoTen.setEditable(false);
        txtHoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHoTenFocusLost(evt);
            }
        });

        txtDiaChi.setEditable(false);
        txtDiaChi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDiaChiFocusLost(evt);
            }
        });

        jradioNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNam.setText("Nam");
        jradioNam.setEnabled(false);
        jradioNam.setSelected(true);

        jradioNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNu.setText("Nữ");
        jradioNu.setEnabled(false);
        buttonGroup1.add(jradioNam);
        buttonGroup1.add(jradioNu);
        jradioNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioNuActionPerformed(evt);
            }
        });

        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Culcheth_High_School_Logo3.png"))); // NOI18N

        cbxChucVu.setEnabled(false);

        btnTim.setText("Tìm");
        btnTim.setEnabled(false);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnTaoMoi.setText("Thêm");
        btnTaoMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLuuLai.setText("Lưu lại");
        btnLuuLai.setEnabled(false);
        btnLuuLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLaiActionPerformed(evt);
            }
        });

        btnXoaNV.setText("Xóa");

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnHuyTT.setText("Hủy thao tác");
        btnHuyTT.setEnabled(false);
        btnHuyTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTaoMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuuLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTaoMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuuLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuyTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        btnXuatDS.setText("Xuất DS");

        javax.swing.GroupLayout lefttopPanelLayout = new javax.swing.GroupLayout(lefttopPanel);
        lefttopPanel.setLayout(lefttopPanelLayout);
        lefttopPanelLayout.setHorizontalGroup(
            lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lefttopPanelLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lefttopPanelLayout.createSequentialGroup()
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(jradioNam)
                        .addGap(18, 18, 18)
                        .addComponent(jradioNu))
                    .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxChucVu, javax.swing.GroupLayout.Alignment.CENTER, 0, 177, Short.MAX_VALUE)
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnXuatDS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        lefttopPanelLayout.setVerticalGroup(
            lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lefttopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jradioNam)
                            .addComponent(lbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jradioNu))
                        .addGap(5, 5, 5)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXuatDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        leftbottomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên thêm từ excel"));

        tb_Excel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ và Tên", "Tuổi", "Địa chỉ", "Mã số sinh viên"
            }
        ));
        table.setViewportView(tb_Excel);

        btnNhapExcel.setText("Nhập Excel");
        btnNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelActionPerformed(evt);
            }
        });

        btnLuuDS.setText("Lưu danh sách");
        btnLuuDS.setEnabled(false);

        btnXoa.setText("Xóa");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout excelcontrolPanelLayout = new javax.swing.GroupLayout(excelcontrolPanel);
        excelcontrolPanel.setLayout(excelcontrolPanelLayout);
        excelcontrolPanelLayout.setHorizontalGroup(
            excelcontrolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(excelcontrolPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(btnLuuDS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        excelcontrolPanelLayout.setVerticalGroup(
            excelcontrolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(excelcontrolPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(excelcontrolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuDS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftbottomPanelLayout = new javax.swing.GroupLayout(leftbottomPanel);
        leftbottomPanel.setLayout(leftbottomPanelLayout);
        leftbottomPanelLayout.setHorizontalGroup(
            leftbottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftbottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(excelcontrolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(table)
        );
        leftbottomPanelLayout.setVerticalGroup(
            leftbottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftbottomPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(excelcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftbottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lefttopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addComponent(lefttopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftbottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhân viên hiện tại"));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ"
            }
        ));
        tbNhanVien.setEditable(false);
        tbNhanVien.setName(""); // NOI18N
        tbNhanVien.getTableHeader().setReorderingAllowed(false);
        tableThongTin.setViewportView(tbNhanVien);
        tbNhanVien.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableThongTin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableThongTin)
        );

        javax.swing.GroupLayout fullPanelLayout = new javax.swing.GroupLayout(fullPanel);
        fullPanel.setLayout(fullPanelLayout);
        fullPanelLayout.setHorizontalGroup(
            fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullPanelLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fullPanelLayout.setVerticalGroup(
            fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fullPanelLayout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jXSearchField1.setToolTipText("Tìm kiếm");
        jXSearchField1.setName("sfTimKiem"); // NOI18N
        jXSearchField1.setPrompt("Tìm kiếm");

        javax.swing.GroupLayout topPanel18Layout = new javax.swing.GroupLayout(topPanel18);
        topPanel18.setLayout(topPanel18Layout);
        topPanel18Layout.setHorizontalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanel18Layout.setVerticalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jXSearchField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jXLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        jXLabel1.setToolTipText("");
        jXLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jradioNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jradioNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jradioNuActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        JFileChooser Load = new JFileChooser();
        int returnVal = Load.showOpenDialog(Load);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                String abc = Load.getSelectedFile().getAbsolutePath();
                f = Load.getSelectedFile();
                BufferedImage originalImage
                        = ImageIO.read(new File(abc));

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", baos);
                byte[] imageInByte = baos.toByteArray();

                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(imageInByte));

                ImageIcon icon = new ImageIcon(imag);
                lbImage.setSize(icon.getIconHeight(), icon.getIconWidth());
                lbImage.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelActionPerformed
        // TODO add your handling code here:
        JFileChooser jFChooser = new JFileChooser();
        // jFChooser.setFileFilter(new FileNameExtensionFilter(null,"xls"));
        int add = jFChooser.showOpenDialog(jFChooser);
        if (add == JFileChooser.APPROVE_OPTION) {
            File addFile = jFChooser.getSelectedFile();
            try {
                Workbook excel = Workbook.getWorkbook(addFile);
                for (int sheets = 0; sheets < excel.getNumberOfSheets(); sheets++) {
                    Sheet sheet = excel.getSheet(sheets);
                    int columns = sheet.getColumns();
                    int rows = sheet.getRows();
                    Object[] values = new Object[columns];
                    for (int row = 0; row < rows; row++) {
                        for (int column = 0; column < columns; column++) {
//                            if (row==0) {
//                               model.addColumn(sheet.getCell(column, 0).getContents());
//                            }
                            values[column] = sheet.getCell(column, row).getContents();
                        }
                        model.addRow(values);
                    }
                }
                model.removeRow(0);
                btnNhapExcel.setEnabled(false);
                btnXoa.setEnabled(true);
                btnHuy.setEnabled(true);
                btnLuuDS.setEnabled(true);
            } catch (IOException | BiffException ex) {
                Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại định dạng file");
            }
        }

    }//GEN-LAST:event_btnNhapExcelActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        model = (DefaultTableModel) tb_Excel.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        btnNhapExcel.setEnabled(true);
        btnXoa.setEnabled(false);
        btnLuuDS.setEnabled(false);
        btnHuy.setEnabled(false);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:

        if (model.getRowCount() < 1) {
            btnNhapExcel.setEnabled(true);
            btnHuy.setEnabled(false);
            btnLuuDS.setEnabled(false);
            btnXoa.setEnabled(false);
        } else {
            int selectRow = tb_Excel.getSelectedRow();
            if (selectRow != -1) {
                model.removeRow(selectRow);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnTaoMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiActionPerformed
        // TODO add your handling code here:
        tbNhanVien.setEnabled(false);
        btnTaoMoi.setEnabled(false);
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        dateNgaySinh.setDate(null);
        lbImage.setIcon(null);
        cbxChucVu.setEnabled(false);

        btnCapNhat.setEnabled(false);
        btnXoaNV.setEnabled(true);
        btnLuuLai.setEnabled(true);
        btnHuyTT.setEnabled(true);

        setComponents(true);
        btnThemSelect = true;
    }//GEN-LAST:event_btnTaoMoiActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        int row = tbNhanVien.getSelectedRow();
        if (row >= 0) {
            btnCapNhat.setEnabled(false);
            btnTaoMoi.setEnabled(false);
            btnXoaNV.setEnabled(true);
            btnLuuLai.setEnabled(true);
            btnHuyTT.setEnabled(true);
            setComponents(true);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        try {
            // TODO add your handling code here:
            LoadTable();
        } catch (Exception ex) {
            Logger.getLogger(FrmStaffManage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnLuuLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLaiActionPerformed
        // TODO add your handling code here:
        try {
            if (txtHoTen.getText().equals("") || txtDiaChi.getText().equals("") || dateNgaySinh.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin của nhân viên");
            } else {
                int len = 0;
                if (f != null) {
                    FileInputStream fin = new FileInputStream(f);
                    len = (int) f.length();
                    nvP.setHinhdaidien(fin);
                } else {
                    nvP.setHinhdaidien(null);
                }

                if (txtMaNV.getText().equals(null)) {
                    nvP.setMaNV("");
                } else {
                    nvP.setMaNV(txtMaNV.getText());
                }
                nvP.setTenNV(txtHoTen.getText());

                java.sql.Date ins = new java.sql.Date(formatDate.parse(dateNgaySinh.getEditor().getText()).getTime());
                nvP.setNgaySinh(ins);
                if (jradioNam.isSelected()) {
                    nvP.setGioiTinh(1);
                } else {
                    nvP.setGioiTinh(0);
                }
                nvP.setDiaChi(txtDiaChi.getText());
                Item_Cbx itemCV = (Item_Cbx) cbxChucVu.getSelectedItem();
                if (itemCV == null) {
                    nvP.setMaChucVu("MCV00001");
                } else {
                    nvP.setMaChucVu("MCV00001");
                }

                String mk = dateNgaySinh.getEditor().getText().replace("-", "");
                nvP.setMatKhau(Crypter.encryptMD5(mk));
                int InsertUpdateStudent = nvBLL.InsertUpdateStaff(nvP, len);
                if (InsertUpdateStudent < 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thông tin nhân viên thành công");
                    btnLuuLai.setEnabled(false);
                    btnTaoMoi.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    tbNhanVien.setEnabled(true);
                    btnHuyTT.setEnabled(false);
                    if (!txtMaNV.getText().equals("")) {
                        txtMaNV.setText("");
                        txtHoTen.setText("");
                        txtDiaChi.setText("");
                        dateNgaySinh.setDate(null);
                    }
                    setComponents(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLuuLaiActionPerformed

    private void btnHuyTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTTActionPerformed
        // TODO add your handling code here:
        btnTaoMoi.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnLuuLai.setEnabled(false);
        tbNhanVien.setEnabled(true);
        btnHuyTT.setEnabled(false);

        setComponents(false);

        lbImage.setIcon(null);

        if (btnThemSelect) {
            try {
                bindingData(rowSelected);
            } catch (ParseException ex) {
                Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            btnThemSelect = false;
        }
    }//GEN-LAST:event_btnHuyTTActionPerformed

    private void txtHoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHoTenFocusLost
        // TODO add your handling code here:
        if (txtHoTen.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên.");
        }
    }//GEN-LAST:event_txtHoTenFocusLost

    private void dateNgaySinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateNgaySinhFocusLost
        // TODO add your handling code here:
        if (dateNgaySinh.getEditor().getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh nhân viên.");
        }
    }//GEN-LAST:event_dateNgaySinhFocusLost

    private void txtDiaChiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDiaChiFocusLost
        // TODO add your handling code here:
        if (txtDiaChi.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ nhân viên.");
        }
    }//GEN-LAST:event_txtDiaChiFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnCapNhat;
    private org.jdesktop.swingx.JXButton btnHuy;
    private org.jdesktop.swingx.JXButton btnHuyTT;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnLuuDS;
    private org.jdesktop.swingx.JXButton btnLuuLai;
    private org.jdesktop.swingx.JXButton btnNhapExcel;
    private org.jdesktop.swingx.JXButton btnTaoMoi;
    private org.jdesktop.swingx.JXButton btnTim;
    private org.jdesktop.swingx.JXButton btnXoa;
    private org.jdesktop.swingx.JXButton btnXoaNV;
    private org.jdesktop.swingx.JXButton btnXuatDS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxChucVu;
    private org.jdesktop.swingx.JXDatePicker dateNgaySinh;
    private org.jdesktop.swingx.JXPanel excelcontrolPanel;
    private org.jdesktop.swingx.JXPanel fullPanel;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXSearchField jXSearchField1;
    private javax.swing.JRadioButton jradioNam;
    private javax.swing.JRadioButton jradioNu;
    private org.jdesktop.swingx.JXLabel lbGioiTinh;
    private org.jdesktop.swingx.JXLabel lbHoTen;
    private org.jdesktop.swingx.JXLabel lbHuyen;
    private org.jdesktop.swingx.JXLabel lbImage;
    private org.jdesktop.swingx.JXLabel lbKhoa;
    private org.jdesktop.swingx.JXLabel lbMaNV;
    private org.jdesktop.swingx.JXLabel lbNgaySinh;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private org.jdesktop.swingx.JXPanel leftbottomPanel;
    private org.jdesktop.swingx.JXPanel lefttopPanel;
    private org.jdesktop.swingx.JXPanel rightPanel;
    private javax.swing.JScrollPane table;
    private javax.swing.JScrollPane tableThongTin;
    private org.jdesktop.swingx.JXTable tbNhanVien;
    private org.jdesktop.swingx.JXTable tb_Excel;
    private org.jdesktop.swingx.JXPanel topPanel18;
    private org.jdesktop.swingx.JXTextField txtDiaChi;
    private org.jdesktop.swingx.JXTextField txtHoTen;
    private org.jdesktop.swingx.JXTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
