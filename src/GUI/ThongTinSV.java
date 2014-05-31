/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAL.*;
import BLL.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
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
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Hung New aaaa
 */
public class ThongTinSV extends javax.swing.JPanel {

    /**
     * Creates new form DangKyMon
     */
    private SQLServerConnector connect;
    private ResultSet rs;
    private DefaultTableModel dtm;
    private int rowSelected = 0;
    File f = null;
    
    private SinhVienBLL svBLL = new SinhVienBLL();
    private DoiTuongBLL dtBLL = new DoiTuongBLL();

    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

    public ThongTinSV() throws Exception {
        initComponents();
        setComponents();
        LoadData();
        //st Tim Kiem cai dat thuoc tinh
        sfTimKiem.setSearchMode(JXSearchField.SearchMode.INSTANT);
        sfTimKiem.setInstantSearchDelay(180);//delay mot khoang 180 milisecond giua 2 lan tim tiem
        JPopupMenu po = new JPopupMenu();
        sfTimKiem.setFindPopupMenu(po);
        sfTimKiem.setRecentSearchesSaveKey("saveKey");
        sfTimKiem.setPrompt("Tìm kiếm");
        sfTimKiem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //tbSinhVien.setRowSelectionInterval(2, 0);
                //lam gi do de tim kiem
                boolean flag = false;
                if(tbSinhVien.getRowCount()>0)
                {
                    for(int i = 0; i< tbSinhVien.getRowCount();i++)
                    {
                        for(int j = 0; j< tbSinhVien.getColumnCount();j++)
                        {
                            if(tbSinhVien.getStringAt(i, j).indexOf(sfTimKiem.getText()) != -1)
                            {
                                tbSinhVien.setRowSelectionInterval(i, i);
                                flag = true;
                                break;
                                //tbSinhVien.set
                            }
                            else
                            {
                                tbSinhVien.setRowSelectionInterval(0, 0);
                            }
                        }
                        if(flag)
                        {
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setComponents() {
        txtHoTen.setEditable(false);
        txtMSSV.setEditable(false);
        jradioNam.setEnabled(false);
        jradioNu.setEnabled(false);
        dateNgaySinh.setEditable(true);
        txtTinh.setEditable(false);
        txtHuyen.setEditable(false);
        //cbxDoiTuong.setEnabled(false);
        //cbxDoiTuong.setEditable(false);
        cbxLop.setEnabled(false);
        cbxNganh.setEnabled(false);
        cbxKhoa.setEnabled(false);
        dateNgaySinh.setFormats(formatDate);
    }

    private void LoadData() throws Exception {
        
        rs = svBLL.LoadSV();
        
        // Lay du lieu voi Store Procedure co tham so
//        String strCall = "{call LoadSV_MSSV(?)}";
//        CallableStatement cabCmd = connect.getCallableStatement(strCall);
//        cabCmd.setString(1, "11520573");
//        ResultSet rs = connect.excuteStore_Para(cabCmd);
        String[] titile = {"MSSV", "Họ & tên", "Ngày sinh", "Giới tính", "Huyện", "Tỉnh", "Ngành học", "Đối tượng", "Năm nhập học", "Hình đại diện"};
        dtm = new DefaultTableModel(titile, 0);
        while (rs.next()) {
            Vector data_rows;
            data_rows = new Vector();
            data_rows.add(rs.getObject(1));
            data_rows.add(rs.getObject(2));
            data_rows.add(formatDate.format(rs.getObject(3)));
            data_rows.add(rs.getObject(4));
            data_rows.add(rs.getObject(5));
            data_rows.add(rs.getObject(6));
            data_rows.add(rs.getObject(9));
            data_rows.add(rs.getObject(8));
            data_rows.add(rs.getObject(7));
            data_rows.add(rs.getObject(10));
            dtm.addRow(data_rows);
        }

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

        /// set Row Selection Mode.
        //tbSinhVien.setColumnSelectionAllowed(false);
        //tbSinhVien.setRowSelectionAllowed(true);
        //tbSinhVien.setRowSelectionInterval(1, 1);

        tbSinhVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    rowSelected = tbSinhVien.getSelectedRow();
                    bindingData(rowSelected);
                } catch (ParseException ex) {
                    Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Fillcombo();
    }

    public void bindingData(int row) throws ParseException {
        try {
            if(row < 0)
            {
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
            dateNgaySinh.setDate(formatDate.parse(tbSinhVien.getStringAt(row, 2)));
            byte[] databyte = null;
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

        } catch (Exception e) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void Fillcombo() {
        try {
            ResultSet x = dtBLL.LoadDT();
            while (x.next()) {
                String dTuong = x.getString(2);
                cbxDoiTuong.addItem(dTuong);
            }
        } catch (Exception ex) {
            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
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
        lbLop = new org.jdesktop.swingx.JXLabel();
        lbNganh = new org.jdesktop.swingx.JXLabel();
        cbxLop = new javax.swing.JComboBox();
        cbxNganh = new javax.swing.JComboBox();
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
        cbxKhoa = new javax.swing.JComboBox();
        btnTim = new org.jdesktop.swingx.JXButton();
        btnLuu = new org.jdesktop.swingx.JXButton();
        leftbottomPanel = new org.jdesktop.swingx.JXPanel();
        table = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();
        excelcontrolPanel = new org.jdesktop.swingx.JXPanel();
        btnNhapExcel = new org.jdesktop.swingx.JXButton();
        btnLuuDS = new org.jdesktop.swingx.JXButton();
        btnXoa = new org.jdesktop.swingx.JXButton();
        btnHuy = new org.jdesktop.swingx.JXButton();
        rightPanel = new org.jdesktop.swingx.JXPanel();
        tableThongTin = new javax.swing.JScrollPane();
        tbSinhVien = new org.jdesktop.swingx.JXTable();
        topPanel18 = new org.jdesktop.swingx.JXPanel();
        btnTaoMoi18 = new org.jdesktop.swingx.JXButton();
        btnChinhSua18 = new org.jdesktop.swingx.JXButton();
        btnXuaDS18 = new org.jdesktop.swingx.JXButton();
        sfTimKiem = new org.jdesktop.swingx.JXSearchField();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();

        lefttopPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sinh viên"));

        lbHoTen.setText("Họ tên:");
        lbHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbMSSV.setText("MSSV: ");
        lbMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbGioiTinh.setText("Giới tính: ");
        lbGioiTinh.setFocusable(false);
        lbGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbNgaySinh.setText("Ngày sinh: ");
        lbNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbLop.setText("Lớp: ");
        lbLop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbNganh.setText("Ngành:");
        lbNganh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbxLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLopActionPerformed(evt);
            }
        });

        lbKhoa.setText("Khoa:");
        lbKhoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbTinh.setText("Tỉnh:");
        lbTinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHuyen.setText("Huyện:");
        lbHuyen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cbxDoiTuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDoiTuongActionPerformed(evt);
            }
        });

        lbDoiTuong.setText("Đối tượng:");
        lbDoiTuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jradioNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNam.setText("Nam");
        jradioNam.setSelected(true);

        jradioNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jradioNu.setText("Nữ");
        buttonGroup1.add(jradioNam);
        buttonGroup1.add(jradioNu);
        jradioNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jradioNuActionPerformed(evt);
            }
        });

        lbImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lefttopPanelLayout = new javax.swing.GroupLayout(lefttopPanel);
        lefttopPanel.setLayout(lefttopPanelLayout);
        lefttopPanelLayout.setHorizontalGroup(
            lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lefttopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lefttopPanelLayout.createSequentialGroup()
                        .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxDoiTuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMSSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addComponent(txtTinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(jradioNam)
                        .addGap(18, 18, 18)
                        .addComponent(jradioNu))
                    .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addGap(16, 16, 16)
                                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jradioNam)
                                    .addComponent(lbGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jradioNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(lbNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addGroup(lefttopPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(lefttopPanelLayout.createSequentialGroup()
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(txtHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNganh, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(cbxDoiTuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDoiTuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lefttopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(cbxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        leftbottomPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách thêm mới"));

        jXTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        table.setViewportView(jXTable2);

        btnNhapExcel.setText("Nhập Excel");

        btnLuuDS.setText("Lưu danh sách");

        btnXoa.setText("Xóa");

        btnHuy.setText("Hủy");

        javax.swing.GroupLayout excelcontrolPanelLayout = new javax.swing.GroupLayout(excelcontrolPanel);
        excelcontrolPanel.setLayout(excelcontrolPanelLayout);
        excelcontrolPanelLayout.setHorizontalGroup(
            excelcontrolPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(excelcontrolPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(leftbottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(excelcontrolPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(table, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        leftbottomPanelLayout.setVerticalGroup(
            leftbottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftbottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(excelcontrolPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftbottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lefttopPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addComponent(lefttopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftbottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sinh viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        tbSinhVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "MSSV", "Họ tên", "Ngày sinh", "Giới tính", "Tỉnh", "Ngành", "Đối tượng", "Năm nhập học"
            }
        ));
        tbSinhVien.setEditable(false);
        tbSinhVien.setName(""); // NOI18N
        tbSinhVien.getTableHeader().setReorderingAllowed(false);
        tableThongTin.setViewportView(tbSinhVien);
        tbSinhVien.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(fullPanelLayout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        topPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnTaoMoi18.setText("Tạo mới");

        btnChinhSua18.setText("Chỉnh sửa");

        btnXuaDS18.setText("Xuất danh sách");

        sfTimKiem.setToolTipText("Tìm kiếm");
        sfTimKiem.setName("sfTimKiem"); // NOI18N
        sfTimKiem.setPrompt("Tìm kiếm");

        javax.swing.GroupLayout topPanel18Layout = new javax.swing.GroupLayout(topPanel18);
        topPanel18.setLayout(topPanel18Layout);
        topPanel18Layout.setHorizontalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTaoMoi18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnChinhSua18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnXuaDS18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(sfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        topPanel18Layout.setVerticalGroup(
            topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoi18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChinhSua18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuaDS18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jXLabel1.setText("THÔNG TIN SINH VIÊN");
        jXLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N

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

    private void cbxLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLopActionPerformed

    private void cbxDoiTuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDoiTuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDoiTuongActionPerformed

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
                Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
//        try {
//            FileInputStream fin = new FileInputStream(f);
//            int len = (int) f.length();
//            String query;
//            PreparedStatement pstmt;
//
//            query = ("Update SINHVIEN set HinhDaiDien = ? where HoTen = N'Nguyễn Thành Thái'");
//            pstmt = connect.getPrepareStatement(query);
//
//            // Method used to insert a stream of bytes
//            pstmt.setBinaryStream(1, fin, len);
//
//            pstmt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(ThongTinSV.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnChinhSua18;
    private org.jdesktop.swingx.JXButton btnHuy;
    private org.jdesktop.swingx.JXButton btnLuu;
    private org.jdesktop.swingx.JXButton btnLuuDS;
    private org.jdesktop.swingx.JXButton btnNhapExcel;
    private org.jdesktop.swingx.JXButton btnTaoMoi18;
    private org.jdesktop.swingx.JXButton btnTim;
    private org.jdesktop.swingx.JXButton btnXoa;
    private org.jdesktop.swingx.JXButton btnXuaDS18;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxDoiTuong;
    private javax.swing.JComboBox cbxKhoa;
    private javax.swing.JComboBox cbxLop;
    private javax.swing.JComboBox cbxNganh;
    private org.jdesktop.swingx.JXDatePicker dateNgaySinh;
    private org.jdesktop.swingx.JXPanel excelcontrolPanel;
    private org.jdesktop.swingx.JXPanel fullPanel;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXTable jXTable2;
    private javax.swing.JRadioButton jradioNam;
    private javax.swing.JRadioButton jradioNu;
    private org.jdesktop.swingx.JXLabel lbDoiTuong;
    private org.jdesktop.swingx.JXLabel lbGioiTinh;
    private org.jdesktop.swingx.JXLabel lbHoTen;
    private org.jdesktop.swingx.JXLabel lbHuyen;
    private org.jdesktop.swingx.JXLabel lbImage;
    private org.jdesktop.swingx.JXLabel lbKhoa;
    private org.jdesktop.swingx.JXLabel lbLop;
    private org.jdesktop.swingx.JXLabel lbMSSV;
    private org.jdesktop.swingx.JXLabel lbNganh;
    private org.jdesktop.swingx.JXLabel lbNgaySinh;
    private org.jdesktop.swingx.JXLabel lbTinh;
    private org.jdesktop.swingx.JXPanel leftPanel;
    private org.jdesktop.swingx.JXPanel leftbottomPanel;
    private org.jdesktop.swingx.JXPanel lefttopPanel;
    private org.jdesktop.swingx.JXPanel rightPanel;
    private org.jdesktop.swingx.JXSearchField sfTimKiem;
    private javax.swing.JScrollPane table;
    private javax.swing.JScrollPane tableThongTin;
    private org.jdesktop.swingx.JXTable tbSinhVien;
    private org.jdesktop.swingx.JXPanel topPanel18;
    private org.jdesktop.swingx.JXTextField txtHoTen;
    private org.jdesktop.swingx.JXTextField txtHuyen;
    private org.jdesktop.swingx.JXTextField txtMSSV;
    private org.jdesktop.swingx.JXTextField txtTinh;
    // End of variables declaration//GEN-END:variables
}
