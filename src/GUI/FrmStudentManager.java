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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author ThanhThaiNguyen
 */
public final class FrmStudentManager extends javax.swing.JPanel {

    /**
     * Creates new form DangKyMon
     */
    private ResultSet rs;
    private DefaultTableModel dtm;
    private DefaultTableModel model;
    private int rowSelected = 0;
    // ThanhThai
    private boolean btnThemSelect = true;
    File f = null;

    private final clsStudent_BLL svBLL = new clsStudent_BLL();
    private final DoiTuongBLL dtBLL = new DoiTuongBLL();
    private final clsFacultyBLL kBLL = new clsFacultyBLL();
    private final clsMayjors_BLL nBLL = new clsMayjors_BLL();

    private final clsFaculty_Public kP = new clsFaculty_Public();
    private final clsMayjors_Public ngP = new clsMayjors_Public();
    private final clsStudent_Public svP = new clsStudent_Public();
    private final ClsRegulationStatic_Public reguP = new ClsRegulationStatic_Public();

    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

    private String maDT = "";
    private String maKh = "";
    private String maNg = "";
    private String namNhapHoc = "";

    // Hàm khởi tạo mặc định
    public FrmStudentManager() throws Exception {
        initComponents();
        LoadTable();
        txtHoTen.setDocument(new ClsTextOnlyDocument_BLL(100));
        txtHuyen.setDocument(new ClsLimitDocument_BLL(50));
        txtTinh.setDocument(new ClsTextOnlyDocument_BLL(50));
        String[] titiles = {"STT", "Họ và Tên", "Tuổi", "Địa chỉ", "Mã số sinh viên"};
        model = new DefaultTableModel(titiles, 0);
//        tb_Excel.setModel(model);
        dateNgaySinh.setFormats("dd-MM-yyyy");
    }

    // Set các thành phần của form
    private void setComponents(boolean b) {
        txtHoTen.setEnabled(b);
        txtHoTen.setEditable(b);

        jradioNam.setEnabled(b);
        jradioNu.setEnabled(b);

        dateNgaySinh.setEnabled(b);
        dateNgaySinh.setEditable(b);

        txtTinh.setEnabled(b);
        txtTinh.setEditable(b);

        txtHuyen.setEnabled(b);
        txtHuyen.setEditable(b);

        cbxDoiTuong.setEnabled(b);
        cboNganh.setEnabled(b);
        cboKhoa.setEnabled(b);
        cbxTrangThai.setEnabled(b);

        btnTim.setEnabled(b);
    }

    // Hàm khởi tạo, và load dữ liệu cho bảng
    public void LoadTable() throws Exception {
        initTable();
        LoadData();
        setTableModel();
    }

    // Hàm khởi tạo bảng
    public void initTable() {
        String[] titile = {"MSSV", "Họ & tên", "Ngày sinh", "Giới tính", "Huyện", "Tỉnh", "Ngành học", "Đối tượng", "Năm nhập học", "Hình đại diện", "Khoa", "Trạng thái"};
        //dtm = (DefaultTableModel) tbSinhVien.getModel();
        dtm = new DefaultTableModel(titile, 0);
    }

    // Load dữ liệu cho bảng
    private void LoadData() throws Exception {

        rs = svBLL.LoadSV();
        while (rs.next()) {
            Vector data_rows;
            data_rows = new Vector();
            data_rows.add(rs.getObject(1));
            data_rows.add(rs.getObject(2));
            if (rs.getDate(3) != null) {
                data_rows.add(formatDate.format(rs.getDate(3)));
            } else {
                data_rows.add(null);
            }
            data_rows.add(rs.getObject(4));
            data_rows.add(rs.getObject(5));
            data_rows.add(rs.getObject(6));
            data_rows.add(rs.getObject(7));
            data_rows.add(rs.getObject(8));
            data_rows.add(rs.getObject(9));
            data_rows.add(rs.getObject(10));
            data_rows.add(rs.getObject(11));
            data_rows.add(rs.getObject(12));
            dtm.addRow(data_rows);
        }
        tbSinhVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if (tbSinhVien.getRowCount() != 0) {
                        rowSelected = tbSinhVien.getSelectedRow();
                        bindingData(rowSelected);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        FillcomboObject();
    }

    // Hàm setModel cho table sinh viên
    public void setTableModel() {
        tbSinhVien.setModel(dtm);
        tbSinhVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ẩn cột Huyện đi
        tbSinhVien.getColumnModel().getColumn(4).setMinWidth(0);
        tbSinhVien.getColumnModel().getColumn(4).setMaxWidth(0);
        tbSinhVien.getColumnModel().getColumn(4).setWidth(0);
        // ẩn cột hình đại diện đi
        tbSinhVien.getColumnModel().getColumn(9).setMinWidth(0);
        tbSinhVien.getColumnModel().getColumn(9).setMaxWidth(0);
        tbSinhVien.getColumnModel().getColumn(9).setWidth(0);
        // Ẩn cột khoa đi
        tbSinhVien.getColumnModel().getColumn(10).setMinWidth(0);
        tbSinhVien.getColumnModel().getColumn(10).setMaxWidth(0);
        tbSinhVien.getColumnModel().getColumn(10).setWidth(0);
        // Ẩn cột trạng thái đi
        tbSinhVien.getColumnModel().getColumn(11).setMinWidth(0);
        tbSinhVien.getColumnModel().getColumn(11).setMaxWidth(0);
        tbSinhVien.getColumnModel().getColumn(11).setWidth(0);
        /// set Row Selection Mode.
        //tbSinhVien.setColumnSelectionAllowed(false);
        //tbSinhVien.setRowSelectionAllowed(true);
        if (tbSinhVien.getRowCount() > 0) {
            tbSinhVien.setRowSelectionInterval(0, 0);
        }
    }

    // Hàm chuyển dữ liệu từ bảng sang các components khác  
    public void bindingData(int row) throws ParseException {
        try {
            if (row < 0) {
                row = 0;
            }
            txtMSSV.setText(tbSinhVien.getStringAt(row, 0));
            txtHoTen.setText(tbSinhVien.getStringAt(row, 1));
            if (tbSinhVien.getStringAt(row, 3).equals("Nam")) {
                jradioNam.setSelected(true);
            } else {
                jradioNu.setSelected(true);
            }
            txtHuyen.setText(tbSinhVien.getStringAt(row, 4));
            txtTinh.setText(tbSinhVien.getStringAt(row, 5));

            String kh = tbSinhVien.getStringAt(row, 11);
            for (int i = 0; i < cboKhoa.getItemCount(); i++) {
                if (((Item_Cbx) cboKhoa.getItemAt(i)).getDescription().equals(kh)) {
                    cboKhoa.setSelectedIndex(i);
                }
            }

            String ng = tbSinhVien.getStringAt(row, 6);
            for (int i = 0; i < cboNganh.getItemCount(); i++) {
                if (((Item_Cbx) cboNganh.getItemAt(i)).getDescription().equals(ng)) {
                    cboNganh.setSelectedIndex(i);
                }
            }
            //cboNganh.setSelectedItem(ng);

            String dt = tbSinhVien.getStringAt(row, 7);
            for (int i = 0; i < cbxDoiTuong.getItemCount(); i++) {
                if (((Item_Cbx) cbxDoiTuong.getItemAt(i)).getDescription() != null) {
                    if (((Item_Cbx) cbxDoiTuong.getItemAt(i)).getDescription().equals(dt)) {
                        cbxDoiTuong.setSelectedIndex(i);
                    }
                } else {
                    cbxDoiTuong.setSelectedIndex(0);
                }
            }

            String tthai = tbSinhVien.getStringAt(row, 10);
            switch (tthai) {
                case "Đang học":
                    cbxTrangThai.setSelectedIndex(0);
                    break;
                case "Bị đình chỉ":
                    cbxTrangThai.setSelectedIndex(1);
                    break;
                case "Đã tốt nghiệp":
                    cbxTrangThai.setSelectedIndex(2);
                    break;
            }

            dateNgaySinh.setDate(formatDate.parse(tbSinhVien.getStringAt(row, 2)));
            byte[] databyte;
            if ((tbSinhVien.getModel().getValueAt(row, 9)) != null) {
                Object x = (Object) tbSinhVien.getModel().getValueAt(row, 9);
                databyte = (byte[]) x;
                Image img = getToolkit().createImage(databyte);
                ImageIcon icon = new ImageIcon(img);
                //lbImage.setSize(icon.getIconHeight(), icon.getIconWidth());
                lbImage.setIcon(icon);
            } else {
                lbImage.setIcon(null);
            }
        } catch (ParseException e) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Fill combo khoa
    public void FillComboFacultys() {
        try {
            // Xóa toàn bộ những item của combobox cũ
            cboKhoa.removeAllItems();
            // Load khoa
            ResultSet rsKh = kBLL.LoadKhoa();
            while (rsKh.next()) {
                cboKhoa.addItem(new Item_Cbx(rsKh.getString(1), rsKh.getString(2), rsKh.getString(3)));
            }
            // Kiểm tra xem combobox Khoa đã bị xóa hết chưa
            if (cboKhoa.getSelectedItem() != null) {
                Item_Cbx item = (Item_Cbx) cboKhoa.getSelectedItem();
                maKh = item.getId();
                FillComboMayjors();
            }
            cboKhoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cboKhoa.getSelectedItem() != null) {
                        Item_Cbx item = (Item_Cbx) cboKhoa.getSelectedItem();
                        maKh = item.getId();
                        FillComboMayjors();
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(FrmInsertCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Load dữ liệu cho combo ngành
    public void FillComboMayjors() {
        try {
            // Xóa toàn bộ những item của combobox cũ
            cboNganh.removeAllItems();
            // Add giá trị khoảng trắng mặc định cho combobox Môn học lý thuyết
            //cboNganh.addItem(new Item_Cbx("1", "Tất cả", null));
            // Load ngành dựa vào mã khoa đang được chọn
            ngP.setIdFaculty(maKh);
            ResultSet x = nBLL.fLoadInfoMayjors_idFaculty(ngP);
            // add nó vào combobox Ngành
            while (x.next()) {
                cboNganh.addItem(new Item_Cbx(x.getString(1), x.getString(2), x.getString(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmInsertCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Xử lý sự kiện cho combobox Ngành
        Item_Cbx itemNg = (Item_Cbx) cboNganh.getSelectedItem();
        if (itemNg != null && (!itemNg.getId().equals("1"))) {
            maNg = itemNg.getId();
        }
        cboNganh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item_Cbx itemNg = (Item_Cbx) cboNganh.getSelectedItem();
                if (itemNg != null && (!itemNg.getId().equals("1"))) {
                    maNg = ((Item_Cbx) cboNganh.getSelectedItem()).getId();
                }
            }
        });
    }

    // Load dữ liệu cho combo đối tượng
    public void FillcomboObject() {
        try {
            FillComboFacultys();
            cbxDoiTuong.removeAllItems();
            cbxDoiTuong.addItem(new Item_Cbx("01", ""));
            ResultSet dt = dtBLL.LoadDT();
            while (dt.next()) {
                cbxDoiTuong.addItem(new Item_Cbx(dt.getString(1), dt.getString(2)));
            }
            cbxDoiTuong.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Item_Cbx itemObject = (Item_Cbx) cbxDoiTuong.getSelectedItem();
                    if (itemObject != null && !(itemObject.getId().equals("01"))) {
                        maDT = ((Item_Cbx) cbxDoiTuong.getSelectedItem()).getId();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        fullPanel = new org.jdesktop.swingx.JXPanel();
        leftPanel = new org.jdesktop.swingx.JXPanel();
        lefttopPanel = new org.jdesktop.swingx.JXPanel();
        lbHoTen = new org.jdesktop.swingx.JXLabel();
        lbMSSV = new org.jdesktop.swingx.JXLabel();
        txtMSSV = new org.jdesktop.swingx.JXTextField();
        lbGioiTinh = new org.jdesktop.swingx.JXLabel();
        lbNgaySinh = new org.jdesktop.swingx.JXLabel();
        dateNgaySinh = new org.jdesktop.swingx.JXDatePicker();
        lbNganh = new org.jdesktop.swingx.JXLabel();
        lbKhoa = new org.jdesktop.swingx.JXLabel();
        lbTinh = new org.jdesktop.swingx.JXLabel();
        lbHuyen = new org.jdesktop.swingx.JXLabel();
        txtHoTen = new org.jdesktop.swingx.JXTextField();
        txtHuyen = new org.jdesktop.swingx.JXTextField();
        cbxDoiTuong = new javax.swing.JComboBox();
        lbDoiTuong = new org.jdesktop.swingx.JXLabel();
        jradioNam = new javax.swing.JRadioButton();
        jradioNu = new javax.swing.JRadioButton();
        txtTinh = new org.jdesktop.swingx.JXTextField();
        lbImage = new org.jdesktop.swingx.JXLabel();
        cboKhoa = new javax.swing.JComboBox();
        btnTim = new org.jdesktop.swingx.JXButton();
        lbTrangThai = new org.jdesktop.swingx.JXLabel();
        cbxTrangThai = new javax.swing.JComboBox();
        cboNganh = new javax.swing.JComboBox();
        rightPanel = new org.jdesktop.swingx.JXPanel();
        tableThongTin = new javax.swing.JScrollPane();
        tbSinhVien = new org.jdesktop.swingx.JXTable();
        topPanel18 = new org.jdesktop.swingx.JXPanel();
        btnThem = new org.jdesktop.swingx.JXButton();
        btnCapNhat = new org.jdesktop.swingx.JXButton();
        btnXuaDS18 = new org.jdesktop.swingx.JXButton();
        sfTimKiem = new org.jdesktop.swingx.JXSearchField();
        btnLuu = new org.jdesktop.swingx.JXButton();
        btnHuyTT = new org.jdesktop.swingx.JXButton();
        btnXoaSV = new org.jdesktop.swingx.JXButton();
        btnLamMoi = new org.jdesktop.swingx.JXButton();
        chkPhanBiet = new javax.swing.JCheckBox();
        btnImportExcel = new org.jdesktop.swingx.JXButton();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();

        lefttopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sinh viên"));

        lbHoTen.setText("Họ tên:");
        lbHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbMSSV.setText("MSSV: ");
        lbMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMSSV.setEditable(false);

        lbGioiTinh.setText("Giới tính: ");
        lbGioiTinh.setFocusable(false);
        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbNgaySinh.setText("Ngày sinh: ");
        lbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        dateNgaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateNgaySinhActionPerformed(evt);
            }
        });

        lbNganh.setText("Ngành:");
        lbNganh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbKhoa.setText("Khoa:");
        lbKhoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbTinh.setText("Tỉnh:");
        lbTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHuyen.setText("Huyện:");
        lbHuyen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoTen.setEditable(false);

        txtHuyen.setEditable(false);

        cbxDoiTuong.setEnabled(false);

        lbDoiTuong.setText("Đối tượng:");
        lbDoiTuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jradioNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNam.setText("Nam");
        jradioNam.setEnabled(false);
        jradioNam.setSelected(true);

        jradioNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNu.setText("Nữ");
        jradioNu.setEnabled(false);
        buttonGroup1.add(jradioNam);
        buttonGroup1.add(jradioNu);

        txtTinh.setEditable(false);

        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImage.setMaximumSize(new java.awt.Dimension(128, 128));
        lbImage.setPreferredSize(new java.awt.Dimension(128, 128));

        cboKhoa.setEnabled(false);

        btnTim.setText("Tìm ảnh");
        btnTim.setEnabled(false);
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        lbTrangThai.setText("Trạng thái:");
        lbTrangThai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang học", "Bị đình chỉ", "Đã tốt nghiệp" }));
        cbxTrangThai.setEnabled(false);

        cboNganh.setEnabled(false);

        javax.swing.GroupLayout lefttopPanelLayout = new javax.swing.GroupLayout(lefttopPanel);
        lefttopPanel.setLayout(lefttopPanelLayout);
        lefttopPanelLayout.setHorizontalGroup(
            lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lefttopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addComponent(lbDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(lbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(jradioNam)
                        .addGap(18, 18, 18)
                        .addComponent(jradioNu))
                    .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(txtMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        lefttopPanelLayout.setVerticalGroup(
            lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lefttopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(txtMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(lbMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(lbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jradioNam)
                            .addComponent(lbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jradioNu))
                        .addGap(11, 11, 11)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(txtHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbxDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDoiTuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNganh, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lefttopPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(lefttopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tbSinhVien.setName(""); // NOI18N
        tbSinhVien.getTableHeader().setReorderingAllowed(false);
        tableThongTin.setViewportView(tbSinhVien);
        tbSinhVien.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tbSinhVien.getColumnModel().getColumnCount() > 0) {
            tbSinhVien.getColumnModel().getColumn(5).setHeaderValue("Tỉnh");
            tbSinhVien.getColumnModel().getColumn(6).setHeaderValue("Ngành");
            tbSinhVien.getColumnModel().getColumn(7).setHeaderValue("Đối tượng");
            tbSinhVien.getColumnModel().getColumn(8).setHeaderValue("Năm nhập học");
            tbSinhVien.getColumnModel().getColumn(10).setHeaderValue("Khoa");
        }

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableThongTin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fullPanelLayout.createSequentialGroup()
                        .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(fullPanelLayout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))))
        );

        topPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnXuaDS18.setText("Xuất danh sách");
        btnXuaDS18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuaDS18ActionPerformed(evt);
            }
        });

        sfTimKiem.setToolTipText("Tìm kiếm");
        sfTimKiem.setName("sfTimKiem"); // NOI18N
        sfTimKiem.setPrompt("Tìm kiếm");
        sfTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfTimKiemActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.setEnabled(false);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnHuyTT.setText("Hủy thao tác");
        btnHuyTT.setEnabled(false);
        btnHuyTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTTActionPerformed(evt);
            }
        });

        btnXoaSV.setText("Xóa");
        btnXoaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSVActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        chkPhanBiet.setText("Phân biệt in hoa/in thường");
        chkPhanBiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPhanBietActionPerformed(evt);
            }
        });

        btnImportExcel.setText("jXButton1");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanel18Layout = new javax.swing.GroupLayout(topPanel18);
        topPanel18.setLayout(topPanel18Layout);
        topPanel18Layout.setHorizontalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuaDS18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHuyTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkPhanBiet)
                .addGap(18, 18, 18)
                .addComponent(sfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanel18Layout.setVerticalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuaDS18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuyTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkPhanBiet)
                    .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel1.setText("THÔNG TIN SINH VIÊN");
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
                .addComponent(fullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Sự kiện button tìm ảnh đại diện
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        JFileChooser Load = new JFileChooser();
        int returnVal = Load.showOpenDialog(Load);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                // lấy đường dẫn
                String abc = Load.getSelectedFile().getAbsolutePath();
                // Get file đang được chọn
                f = Load.getSelectedFile();
                // đọc ảnh
                BufferedImage originalImage
                        = ImageIO.read(new File(abc));

                // Chuyển ảnh sang dạng dữ liệu byte
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", baos);
                byte[] imageInByte = baos.toByteArray();

                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(imageInByte));

                ImageIcon icon = new ImageIcon(imag);
                lbImage.setSize(icon.getIconHeight(), icon.getIconWidth());
                lbImage.setIcon(icon);
            } catch (IOException ex) {
                Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try {
            if (txtHoTen.getText().equals("") || txtHuyen.getText().equals("") || txtTinh.getText().equals("") || dateNgaySinh.getEditor().getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin của nhân viên");
            } else {
                int len = 0;
                if (f != null) {
                    FileInputStream fin = new FileInputStream(f);
                    len = (int) f.length();
                    svP.setHinhdaidien(fin);
                } else {
                    svP.setHinhdaidien(null);
                }

                svP.setIdStudent(txtMSSV.getText());
                svP.setName(txtHoTen.getText());

                Calendar cal = Calendar.getInstance();
                cal.getTime();
                
                java.sql.Date ins = new java.sql.Date(formatDate.parse(dateNgaySinh.getEditor().getText()).getTime());
                if ((Calendar.getInstance().get(Calendar.YEAR) - ins.getYear() - 1900) < reguP.getMinStudentYearOld()) {
                    JOptionPane.showMessageDialog(this, "Tuổi sinh viên phải lớn hơn " + reguP.getMinStudentYearOld());
                    return;
                }
                svP.setBirthday(ins);
                if (jradioNam.isSelected()) {
                    svP.setGender(1);
                } else {
                    svP.setGender(0);
                }
                svP.setDistrict(txtHuyen.getText());
                svP.setProvinces(txtTinh.getText());
                svP.setYearOfApply(namNhapHoc);
                svP.setIdMayjors(maNg);

                if (((Item_Cbx) cbxDoiTuong.getSelectedItem()).getDescription().equals("")) {
                    svP.setIdObjects("");
                } else {
                    svP.setIdObjects(maDT);
                }
                switch (cbxTrangThai.getSelectedItem().toString()) {
                    case "Đang học":
                        svP.setTinhTrang("Đang học");
                        break;
                    case "Bị đình chỉ":
                        svP.setTinhTrang("Bị đình chỉ");
                        break;
                    case "Đã tốt nghiệp":
                        svP.setTinhTrang("Đã tốt nghiệp");
                        break;
                }

                int InsertUpdateStudent = svBLL.InsertUpdateStudent(svP, len);
                if (InsertUpdateStudent < 0) {
                    JOptionPane.showMessageDialog(this, "Thêm thông tin sinh viên thành công");
                    btnLuu.setEnabled(false);
                    btnThem.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    tbSinhVien.setEnabled(true);
                    btnHuyTT.setEnabled(false);
                    if (!txtMSSV.getText().equals("")) {
                        txtMSSV.setText("");
                        txtHoTen.setText("");
                        txtTinh.setText("");
                        txtHuyen.setText("");
                        dateNgaySinh.setDate(null);
                    }
                    setComponents(false);
                    LoadTable();
                }
            }
        } catch (SQLException | IOException ex) {
            //Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Đã phát sinh lỗi.");
        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Đã phát sinh lỗi.");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXuaDS18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuaDS18ActionPerformed
        // TODO add your handling code here:
        clsExportExcel clsE = new clsExportExcel();
        try {
            clsE.exportTable(tbSinhVien, new File(clsE.jChooserPath(FrmStudentManager.this)));
        } catch (IOException ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuaDS18ActionPerformed

    public void setNamNhapHoc(String nam, String maNgInsert, String maKhInsert) {
        try {
            /// new thanh thai - 10:18 - 14/06
            namNhapHoc = nam;
            svP.setIdStudent(nam.substring(2, 4) + maNgInsert.substring(5, 7));
            ResultSet maxMSSV = svBLL.getMaxMSSV(svP);
            // gán giá trị mssv mới cho txtMSSV
            while (maxMSSV.next()) {
                txtMSSV.setText(maxMSSV.getString(1));
            }
            // Gán giá trị cho comboKhoa
            for (int i = 0; i < cboKhoa.getItemCount(); i++) {
                if (((Item_Cbx) cboKhoa.getItemAt(i)).getId().equals(maKhInsert)) {
                    cboKhoa.setSelectedIndex(i);
                }
            }
            cboKhoa.setEnabled(false);
            // Gán giá trị cho comboNganh
            for (int i = 0; i < cboNganh.getItemCount(); i++) {
                if (((Item_Cbx) cboNganh.getItemAt(i)).getId().equals(maNgInsert)) {
                    cboNganh.setSelectedIndex(i);
                }
            }
            cboNganh.setEnabled(false);

        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetComponentsListStudents() {
        tbSinhVien.setEnabled(false);
        btnThem.setEnabled(false);
        txtMSSV.setEditable(false);
        txtHoTen.setText("");
        txtTinh.setText("");
        txtHuyen.setText("");
        dateNgaySinh.setDate(null);
        lbImage.setIcon(null);

        btnTim.setEnabled(true);
        btnCapNhat.setEnabled(false);
        btnHuyTT.setEnabled(true);
        btnLuu.setEnabled(true);

        setComponents(true);
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        // Mở form nhập năm nhập học
        frmInsertYearStudy frmInsertYear = new frmInsertYearStudy(this);
        frmInsertYear.setVisible(true);
        // Disable các componet của form danh sách sinh viên
        enableComponents(this, false);
    }//GEN-LAST:event_btnThemActionPerformed

    // Hàm disable các components
    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    public void setMSSV(String mssv) {
        txtMSSV.setText(mssv);
    }

    // Hủy thao tác vừa thực hiện
    public void HuyThaoTac() {
        btnThem.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnLuu.setEnabled(false);
        tbSinhVien.setEnabled(true);
        btnHuyTT.setEnabled(false);

        setComponents(false);

        lbImage.setIcon(null);

        if (isBtnThemSelect()) {
            try {
                bindingData(rowSelected);
            } catch (ParseException ex) {
                Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            setBtnThemSelect(false);
        }
    }
    private void btnHuyTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTTActionPerformed
        // TODO add your handling code here:
        HuyThaoTac();
    }//GEN-LAST:event_btnHuyTTActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        int row = tbSinhVien.getSelectedRow();
        if (row >= 0) {

            btnCapNhat.setEnabled(false);
            btnThem.setEnabled(false);
            btnHuyTT.setEnabled(true);
            btnLuu.setEnabled(true);
            setComponents(true);
            txtMSSV.setEditable(false);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        try {
            LoadTable();
        } catch (Exception ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSVActionPerformed
        // TODO add your handling code here:
        if (tbSinhVien.getSelectedRow() >= 0) {
            try {
                int ySn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên này?", "THÔNG BÁO", JOptionPane.YES_NO_OPTION);
                if (ySn == JOptionPane.YES_OPTION) {
                    String mssv = tbSinhVien.getValueAt(tbSinhVien.getSelectedRow(), 0).toString();
                    svP.setIdStudent(mssv);
                    int DeleteStudent = svBLL.DeleteStudent(svP);
                    if (DeleteStudent > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công sinh viên.");
                        LoadTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể xóa sinh viên, đã phát sinh lỗi");
                    }
                } else {
                    return;
                }

            } catch (Exception ex) {
                //Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Không thể xóa sinh viên, đã phát sinh lỗi");
            }
        }
    }//GEN-LAST:event_btnXoaSVActionPerformed

    private void sfTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfTimKiemActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < dtm.getRowCount(); i++) {
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                if (chkPhanBiet.getModel().isSelected()) {//có phân biệt hoa thường
                    if (String.valueOf(dtm.getValueAt(i, j)).indexOf(sfTimKiem.getText()) >= 0) {
                        tbSinhVien.setRowSelectionInterval(i, i);
                        tbSinhVien.scrollRowToVisible(i);
                        return;
                    }
                } else {//khong phân biet
                    if (String.valueOf(dtm.getValueAt(i, j)).toUpperCase().indexOf(sfTimKiem.getText().toUpperCase()) >= 0) {
                        tbSinhVien.setRowSelectionInterval(i, i);
                        tbSinhVien.scrollRowToVisible(i);
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_sfTimKiemActionPerformed

    private void chkPhanBietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPhanBietActionPerformed
        sfTimKiem.setText("");
    }//GEN-LAST:event_chkPhanBietActionPerformed

    private void dateNgaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateNgaySinhActionPerformed
        try {
            // TODO add your handling code here:
            java.sql.Date ins = new java.sql.Date(formatDate.parse(dateNgaySinh.getEditor().getText()).getTime());
            if ((Calendar.getInstance().get(Calendar.YEAR) - ins.getYear() - 1900) < reguP.getMinStudentYearOld()) {
                JOptionPane.showMessageDialog(this, "Tuổi sinh viên phải lớn hơn " + reguP.getMinStudentYearOld());
            }
        } catch (ParseException ex) {
            Logger.getLogger(FrmStudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_dateNgaySinhActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        // TODO add your handling code here:
        frmImportExcel iEList = new frmImportExcel();
        iEList.setVisible(true);
    }//GEN-LAST:event_btnImportExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnCapNhat;
    private org.jdesktop.swingx.JXButton btnHuyTT;
    private org.jdesktop.swingx.JXButton btnImportExcel;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnLuu;
    private org.jdesktop.swingx.JXButton btnThem;
    private org.jdesktop.swingx.JXButton btnTim;
    private org.jdesktop.swingx.JXButton btnXoaSV;
    private org.jdesktop.swingx.JXButton btnXuaDS18;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboKhoa;
    private javax.swing.JComboBox cboNganh;
    private javax.swing.JComboBox cbxDoiTuong;
    private javax.swing.JComboBox cbxTrangThai;
    private javax.swing.JCheckBox chkPhanBiet;
    private org.jdesktop.swingx.JXDatePicker dateNgaySinh;
    private org.jdesktop.swingx.JXPanel fullPanel;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JRadioButton jradioNam;
    private javax.swing.JRadioButton jradioNu;
    private org.jdesktop.swingx.JXLabel lbDoiTuong;
    private org.jdesktop.swingx.JXLabel lbGioiTinh;
    private org.jdesktop.swingx.JXLabel lbHoTen;
    private org.jdesktop.swingx.JXLabel lbHuyen;
    private org.jdesktop.swingx.JXLabel lbImage;
    private org.jdesktop.swingx.JXLabel lbKhoa;
    private org.jdesktop.swingx.JXLabel lbMSSV;
    private org.jdesktop.swingx.JXLabel lbNganh;
    private org.jdesktop.swingx.JXLabel lbNgaySinh;
    private org.jdesktop.swingx.JXLabel lbTinh;
    private org.jdesktop.swingx.JXLabel lbTrangThai;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private org.jdesktop.swingx.JXPanel lefttopPanel;
    private org.jdesktop.swingx.JXPanel rightPanel;
    private org.jdesktop.swingx.JXSearchField sfTimKiem;
    private javax.swing.JScrollPane tableThongTin;
    private org.jdesktop.swingx.JXTable tbSinhVien;
    private org.jdesktop.swingx.JXPanel topPanel18;
    private org.jdesktop.swingx.JXTextField txtHoTen;
    private org.jdesktop.swingx.JXTextField txtHuyen;
    private org.jdesktop.swingx.JXTextField txtMSSV;
    private org.jdesktop.swingx.JXTextField txtTinh;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the btnThemSelect
     */
    public boolean isBtnThemSelect() {
        return btnThemSelect;
    }

    /**
     * @param btnThemSelect the btnThemSelect to set
     */
    public void setBtnThemSelect(boolean btnThemSelect) {
        this.btnThemSelect = btnThemSelect;
    }
}
