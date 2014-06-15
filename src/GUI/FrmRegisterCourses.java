/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import PUBLIC.*;
import java.awt.event.KeyEvent;
import BLL.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Calendar;
import java.sql.Date;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author John
 */
public class FrmRegisterCourses extends javax.swing.JPanel {

    private final ClsRegisterCourses_BLL rcBLL = new ClsRegisterCourses_BLL();
    private final ClsSemesterYear_BLL syBLL = new ClsSemesterYear_BLL();
    private final ClsPaymentFee_BLL pfBLL = new ClsPaymentFee_BLL();
    //private int currentYear;
    private Date today;
    private DefaultTableModel dtm;
    private DefaultTableModel dtmRegisted;
    private String idSemesterYear = "";
    private String maPhieuDangKy = "";//de kiem tra xem sinh vien dang ki moi, hay cap nhat.
    private BigDecimal registerMoney = BigDecimal.ZERO;//tong tien dang ki
    private BigDecimal sumMoneyMustPay = BigDecimal.ZERO;//tong tien hoc phi phai dong

    /**
     * Creates new form DangKiMonHoc2
     */
    public FrmRegisterCourses() {
        initComponents();
        txtMssv.setDocument(new ClsLimitDocument_BLL(20));
        today = new Date(Calendar.getInstance().getTime().getTime());
        tblListCourseCanBeRegister.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblListCourseRegisted.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //lay tablemodel cua bang danh sach mon hoc co the dang ki
        dtm = (DefaultTableModel) tblListCourseCanBeRegister.getModel();
        dtmRegisted = (DefaultTableModel) tblListCourseRegisted.getModel();
        //ẩn cột ma mon ly thuyet di
        tblListCourseCanBeRegister.getColumnModel().getColumn(2).setMinWidth(0);
        tblListCourseCanBeRegister.getColumnModel().getColumn(2).setMaxWidth(0);
        tblListCourseCanBeRegister.getColumnModel().getColumn(2).setWidth(0);
        // ẩn cột mtblListCourseCanBeRegister
        tblListCourseCanBeRegister.getColumnModel().getColumn(4).setMinWidth(0);
        tblListCourseCanBeRegister.getColumnModel().getColumn(4).setMaxWidth(0);
        tblListCourseCanBeRegister.getColumnModel().getColumn(4).setWidth(0);
        //set chieu rong cho cot "Chọn"
        tblListCourseCanBeRegister.getColumnModel().getColumn(0).setMinWidth(60);
        tblListCourseCanBeRegister.getColumnModel().getColumn(0).setMaxWidth(60);
        tblListCourseCanBeRegister.getColumnModel().getColumn(0).setWidth(60);
        //gan su kien cho bang  tblListCourseCanBeRegister
        tblListCourseCanBeRegister.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    boolean flag = false;
                    for (int i = 0; i < dtm.getRowCount(); i++) {
                        //neu co mon nao do duoc chon thi enable btn...
                        if ((boolean) dtm.getValueAt(i, 0)) {
                            if (!btnRegister.isEnabled()) {
                                btnRegister.setEnabled(true);
                                btnCancelAll.setEnabled(true);
                            }
                            flag = true;
                        }
                    }
                    if (!flag && btnRegister.isEnabled()) {//flag van bang false tuc la chua co mon nao duoc chon
                        btnRegister.setEnabled(false);
                        btnCancelAll.setEnabled(false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FrmHumanSubjects.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //ẩn cột ma mon ly thuyet di
        tblListCourseRegisted.getColumnModel().getColumn(1).setMinWidth(0);
        tblListCourseRegisted.getColumnModel().getColumn(1).setMaxWidth(0);
        tblListCourseRegisted.getColumnModel().getColumn(1).setWidth(0);
        // ẩn cột maLoaiMH
        tblListCourseRegisted.getColumnModel().getColumn(3).setMinWidth(0);
        tblListCourseRegisted.getColumnModel().getColumn(3).setMaxWidth(0);
        tblListCourseRegisted.getColumnModel().getColumn(3).setWidth(0);
        //gan su kien cho bang mon hoc da dang ki
        tblListCourseRegisted.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    if (tblListCourseRegisted.getSelectedRow() >= 0) {
                        btnCancelCurrentCourse.setEnabled(true);
                    } else {
                        btnCancelCurrentCourse.setEnabled(false);
                    }
                    if (dtmRegisted.getRowCount() > 0) {
                        btnCancelAllCourseRegisted.setEnabled(true);
                        btnExportListRegisted.setEnabled(true);
                    } else {
                        btnCancelAllCourseRegisted.setEnabled(false);
                        btnExportListRegisted.setEnabled(false);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FrmHumanSubjects.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        LoadFirstYear();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FullPanel = new org.jdesktop.swingx.JXPanel();
        TopInFull = new org.jdesktop.swingx.JXPanel();
        TopLeft = new org.jdesktop.swingx.JXPanel();
        txtMssv = new org.jdesktop.swingx.JXTextField();
        lbMSSV = new org.jdesktop.swingx.JXLabel();
        txtHoTen = new org.jdesktop.swingx.JXTextField();
        txtNganh = new org.jdesktop.swingx.JXTextField();
        txtKhoa = new org.jdesktop.swingx.JXTextField();
        lbHoTen2 = new org.jdesktop.swingx.JXLabel();
        lbHoTen3 = new org.jdesktop.swingx.JXLabel();
        HoTen = new org.jdesktop.swingx.JXLabel();
        btnSearchStudent = new org.jdesktop.swingx.JXButton();
        TopLeft1 = new org.jdesktop.swingx.JXPanel();
        lbHoTen5 = new org.jdesktop.swingx.JXLabel();
        cboHocKi = new javax.swing.JComboBox();
        lbHoTen8 = new org.jdesktop.swingx.JXLabel();
        cboYear = new javax.swing.JComboBox();
        ControlPanel = new org.jdesktop.swingx.JXPanel();
        btnRegister = new org.jdesktop.swingx.JXButton();
        btnCancelAll = new org.jdesktop.swingx.JXButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListCourseCanBeRegister = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListCourseRegisted = new org.jdesktop.swingx.JXTable();
        HoTen1 = new org.jdesktop.swingx.JXLabel();
        txtSumOfCredit = new org.jdesktop.swingx.JXTextField();
        lbHoTen4 = new org.jdesktop.swingx.JXLabel();
        txtSumOfFee = new org.jdesktop.swingx.JXTextField();
        lbHoTen6 = new org.jdesktop.swingx.JXLabel();
        ControlPanel1 = new org.jdesktop.swingx.JXPanel();
        btnCancelCurrentCourse = new org.jdesktop.swingx.JXButton();
        btnCancelAllCourseRegisted = new org.jdesktop.swingx.JXButton();
        btnExportListRegisted = new org.jdesktop.swingx.JXButton();
        txtSumFeeMustPay = new org.jdesktop.swingx.JXTextField();
        lbHoTen7 = new org.jdesktop.swingx.JXLabel();
        lbHoTen9 = new org.jdesktop.swingx.JXLabel();
        TopPanel = new org.jdesktop.swingx.JXPanel();
        Title = new org.jdesktop.swingx.JXLabel();

        TopLeft.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sinh Viên"));

        txtMssv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMssvKeyPressed(evt);
            }
        });

        lbMSSV.setText("Mssv:");
        lbMSSV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtHoTen.setEditable(false);

        txtNganh.setEditable(false);

        txtKhoa.setEditable(false);

        lbHoTen2.setText("Khoa:");
        lbHoTen2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHoTen3.setText("Ngành:");
        lbHoTen3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        HoTen.setText("Họ tên:");
        HoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnSearchStudent.setText("Tìm kiếm");
        btnSearchStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchStudentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TopLeftLayout = new javax.swing.GroupLayout(TopLeft);
        TopLeft.setLayout(TopLeftLayout);
        TopLeftLayout.setHorizontalGroup(
            TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMssv, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TopLeftLayout.setVerticalGroup(
            TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeftLayout.createSequentialGroup()
                .addGroup(TopLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMSSV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMssv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        TopLeft1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Học Kì - Năm Học"));

        lbHoTen5.setText("Học kì:");
        lbHoTen5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboHocKi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cboHocKi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHocKiActionPerformed(evt);
            }
        });

        lbHoTen8.setText("Năm học:");
        lbHoTen8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TopLeft1Layout = new javax.swing.GroupLayout(TopLeft1);
        TopLeft1.setLayout(TopLeft1Layout);
        TopLeft1Layout.setHorizontalGroup(
            TopLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeft1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopLeft1Layout.setVerticalGroup(
            TopLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeft1Layout.createSequentialGroup()
                .addGroup(TopLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout TopInFullLayout = new javax.swing.GroupLayout(TopInFull);
        TopInFull.setLayout(TopInFullLayout);
        TopInFullLayout.setHorizontalGroup(
            TopInFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopInFullLayout.createSequentialGroup()
                .addComponent(TopLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TopLeft1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopInFullLayout.setVerticalGroup(
            TopInFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopInFullLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(TopInFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TopLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TopLeft1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        ControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển danh sách môn học được phép đăng ký"));

        btnRegister.setText("Đăng kí");
        btnRegister.setEnabled(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnCancelAll.setText("Bỏ chọn tất cả");
        btnCancelAll.setEnabled(false);
        btnCancelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách môn học được phép đăng kí"));

        tblListCourseCanBeRegister.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Mã môn học", "MaMHLT", "Tên môn học", "MaLoaiMH", "Loại môn học", "Số tín chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListCourseCanBeRegister.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblListCourseCanBeRegister.setEditable(false);
        tblListCourseCanBeRegister.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblListCourseCanBeRegister);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách môn học đã đăng kí"));

        tblListCourseRegisted.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Môn Học", "MaMHLT", "Tên Môn Học", "MaLoaiMH", "Loại Môn Học", "Số Tín Chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListCourseRegisted.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblListCourseRegisted.setEditable(false);
        tblListCourseRegisted.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblListCourseRegisted);

        HoTen1.setText("Tổng tín chỉ:");
        HoTen1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSumOfCredit.setEditable(false);

        lbHoTen4.setText("Tổng tiền đăng kí:");
        lbHoTen4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSumOfFee.setEditable(false);

        lbHoTen6.setText("VNĐ");
        lbHoTen6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        ControlPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển danh sách môn học đã đăng ký"));

        btnCancelCurrentCourse.setText("Hủy môn học đang chọn");
        btnCancelCurrentCourse.setEnabled(false);
        btnCancelCurrentCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelCurrentCourseActionPerformed(evt);
            }
        });

        btnCancelAllCourseRegisted.setText("Hủy tất cả");
        btnCancelAllCourseRegisted.setEnabled(false);
        btnCancelAllCourseRegisted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAllCourseRegistedActionPerformed(evt);
            }
        });

        btnExportListRegisted.setText("Xuất danh sách môn học đã đăng kí");
        btnExportListRegisted.setEnabled(false);
        btnExportListRegisted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportListRegistedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlPanel1Layout = new javax.swing.GroupLayout(ControlPanel1);
        ControlPanel1.setLayout(ControlPanel1Layout);
        ControlPanel1Layout.setHorizontalGroup(
            ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelCurrentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelAllCourseRegisted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportListRegisted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ControlPanel1Layout.setVerticalGroup(
            ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelAllCourseRegisted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelCurrentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportListRegisted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        txtSumFeeMustPay.setEditable(false);

        lbHoTen7.setText("VNĐ");
        lbHoTen7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHoTen9.setText("Tổng tiền phải đóng:");
        lbHoTen9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout FullPanelLayout = new javax.swing.GroupLayout(FullPanel);
        FullPanel.setLayout(FullPanelLayout);
        FullPanelLayout.setHorizontalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopInFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FullPanelLayout.createSequentialGroup()
                        .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                            .addComponent(ControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FullPanelLayout.createSequentialGroup()
                                .addComponent(HoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSumOfCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbHoTen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSumOfFee, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHoTen6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(lbHoTen9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSumFeeMustPay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbHoTen7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ControlPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        FullPanelLayout.setVerticalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(TopInFull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ControlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FullPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSumFeeMustPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSumOfFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSumOfCredit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(HoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addContainerGap())
        );

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("ĐĂNG KÍ MÔN HỌC");
        Title.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Title.setTextAlignment(org.jdesktop.swingx.JXLabel.TextAlignment.CENTER);

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 1264, Short.MAX_VALUE)
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
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LoadFirstYear() {
        //lay tat ca nam hoc da co        
        try {
            ResultSet rs = pfBLL.LoadFirstYear();
            while (rs.next()) {
                int first = rs.getInt(1);
                int second = first + 1;
                cboYear.addItem(String.valueOf(first) + " - " + String.valueOf(second));
            }
           // cboYear.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(FrmPaymentFee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void txtMssvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMssvKeyPressed
        //neu bam phim enter thi kiem tra mssv
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (CheckStudentAndLoadCourse()) {
                txtMssv.setEnabled(false);
                btnSearchStudent.setText("Sinh viên mới");
            }
            //CheckStudentAndLoadCourse();
        }
    }//GEN-LAST:event_txtMssvKeyPressed

    private boolean CheckStudentAndLoadCourse() {
        if (txtMssv.getText().isEmpty()) {
            return false;
        }
        String maNganh = "";
        clsStudent_Public stPublic = new clsStudent_Public();
        stPublic.setIdStudent(txtMssv.getText());
        try {
            ResultSet rs = rcBLL.LoadStudentInfo(stPublic);
            if (rs.next()) {//ma so sinh vien hop le thi load thong tin sinh vien
                if (!"Đang học".equals(rs.getString(5))) {
                    JOptionPane.showMessageDialog(this,
                            "Sinh viên này không được phép đăng kí môn học\nTình trạng sinh viên: " + rs.getString(5));
                    NewStudent();//cho phep nhap sinh vien moi
                    return false;
                }
                txtHoTen.setText(rs.getString(1));
                txtNganh.setText(rs.getString(2));
                txtKhoa.setText(rs.getString(3));
                maNganh = rs.getString(4);
//kiem tra xem hoc ki va nam hoc hien tai co the dang ki duoc khong?
                ClsSemesterYearPublic syPublic = new ClsSemesterYearPublic();
                if(cboYear.getItemCount()<=0)//chưa có năm học để đăng kí thì thoát khỏi hàm, return false
                    return false;
                syPublic.setFirstYear(Integer.parseInt(String.valueOf(cboYear.getSelectedItem()).trim().split("\\ - ")[0]));
                syPublic.setSemester(Integer.parseInt(String.valueOf(cboHocKi.getSelectedItem())));
                rs = syBLL.GetDeadlineToRegister(syPublic);
                if (rs.next()) {
                    Calendar cal = Calendar.getInstance();
                    if (cal.getTime().after(rs.getDate(1))) {
                        JOptionPane.showMessageDialog(this, "Đã hết hạn đăng kí môn học trong học kì này");
                        //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                        DeleteDataFromListCourseRegisted();
                        //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                        if (dtm.getRowCount() > 0) {
                            for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                                dtm.removeRow(i);
                            }
                        }
                        return true;
                    }
                    if(cal.getTime().before(rs.getDate(2))){//kiem tra xem da den ngay duoc phep dang ki mon hoc hay chua?
                        JOptionPane.showMessageDialog(this, "Chưa đến ngày đăng kí môn học trong học kì này");
                        //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                        DeleteDataFromListCourseRegisted();
                        //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                        if (dtm.getRowCount() > 0) {
                            for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                                dtm.removeRow(i);
                            }
                        }
                        return true;
                    }
                    //Date deadlineToRegister = rs.getDate(2);
                }
                idSemesterYear = syBLL.GetIdSemesterYear(syPublic);
                if (idSemesterYear.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Học kì đang chọn chưa được phép đăng ký môn học");
                    //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                    DeleteDataFromListCourseRegisted();
                    //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                    if (dtm.getRowCount() > 0) {
                        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                            dtm.removeRow(i);
                        }
                    }
                    return true;
                }
                //xoa het du lieu cu neu co
                DeleteDataFromListCourseRegisted();
                //load thong tin cac mon hoc ma sinh vien co the dang ki dc
                rs = rcBLL.LoadListCourseToRegister(maNganh, idSemesterYear);
                //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                if (dtm.getRowCount() > 0) {
                    for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }
                }
                if (rs.next()) {
                    do {
                        Vector data_rows = new Vector();
                        data_rows.add(false);
                        data_rows.add(rs.getObject(1));
                        data_rows.add(rs.getObject(2));
                        data_rows.add(rs.getObject(3));
                        data_rows.add(rs.getObject(4));
                        data_rows.add(rs.getObject(5));
                        data_rows.add(rs.getObject(6));
                        dtm.addRow(data_rows);
                    } while (rs.next());
                } else {
                    JOptionPane.showMessageDialog(this, "Chưa có môn học nào được mở trong học kì này");
                    //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                    DeleteDataFromListCourseRegisted();
                    //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                    if (dtm.getRowCount() > 0) {
                        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                            dtm.removeRow(i);
                        }
                    }
                }
                //thay đổi nút đăng kí thành nút cập nhật nếu sinh viên đã đăng kí ít nhất 1 lần ở học kì này
                ClsRegisterForm_Public rfPublic = new ClsRegisterForm_Public();
                rfPublic.setIdSemesterYears(idSemesterYear);
                rfPublic.setIdStudent(txtMssv.getText());
                //kiem tra xem sinhvien nay da dang ki mon hoc o hoc ki nay lan nao chua
                maPhieuDangKy = rcBLL.CheckRegistedOrNot(rfPublic);//lay ma phieu dang ki cua sinh vien o hoc ki nay
                if (maPhieuDangKy.isEmpty()) {
                    btnRegister.setText("Đăng kí");
                    DeleteDataFromListCourseRegisted(); //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                } else {
                    btnRegister.setText("Cập nhật");
                    //nếu là sinh viên muốn cập nhật thì load danh sách môn học đã đăng kí trước đây
                    DeleteDataFromListCourseRegisted(); //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                    rs = rcBLL.GetListCoureRegisted(rfPublic);
                    while (rs.next()) {
                        Vector data_rows = new Vector();
                        data_rows.add(rs.getString(1));
                        data_rows.add(rs.getString(2));
                        data_rows.add(rs.getString(3));
                        data_rows.add(rs.getString(4));
                        dtmRegisted.addRow(data_rows);
                    }
                    CaculateMoneyAndCredit();
                    //cho phep bam nut xuat danh sach mon hoc
                    btnExportListRegisted.setEnabled(true);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Mã số sinh viên không hợp lệ");
                txtMssv.setText("");
                txtHoTen.setText("");
                txtNganh.setText("");
                txtKhoa.setText("");
                //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                DeleteDataFromListCourseRegisted();
                //neu tblDsMonHocDuocDky da co du lieu thi xoa di
                if (dtm.getRowCount() > 0) {
                    for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                        dtm.removeRow(i);
                    }
                }
                NewStudent();//cho phep nhap sinh vien moi
                return false;
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(FrmRegisterCourses.class.getName()).log(Level.SEVERE, null, ex);
            NewStudent();//cho phep nhap sinh vien moi
            return false;
        }
    }

    private void btnCancelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAllActionPerformed
        //duyệt qua tất cả các dòng của table và gán cột đầu tiên là false.
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(false, i, 0);
        }
        btnRegister.setEnabled(false);
        btnCancelAll.setEnabled(false);
        btnExportListRegisted.setEnabled(false);
    }//GEN-LAST:event_btnCancelAllActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        //duyet qua tat cả các môn, nếu môn nào có chọn thì kiểm tra điều kiện môn thực hành và lý thuyết
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if ((boolean) dtm.getValueAt(i, 0)) {
                //neu mon hoc ly thuyet thi kiem tra co mon hoc thuc hanh nao khong
                if (dtm.getValueAt(i, 4).equals("LMH00001")) {
                    for (int j = 0; j < dtm.getRowCount(); j++) {
                        //neu ma mon hoc hien tai bang voi ma mon hoc ly thuyet cua dong nao, thi set(true) cho dong do
                        if (dtm.getValueAt(i, 1).equals(dtm.getValueAt(j, 2))) {
                            dtm.setValueAt(true, j, 0);
                        }
                    }
                } else {//neu la mon hoc thuc hanh
                    for (int j = 0; j < dtm.getRowCount(); j++) {
                        //kiem tra xem ma mon hoc ly thuyet cua mon hoc hien tai bang voi ma mon hoc nao, thi set(true)
                        if (dtm.getValueAt(i, 2).equals(dtm.getValueAt(j, 1))) {
                            dtm.setValueAt(true, j, 0);
                        }
                    }
                }
            }
        }
        try {
            //chuyen cac mon hoc vao bang danh sach mon hoc da dang ki
            //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
            DeleteDataFromListCourseRegisted();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                if ((boolean) dtm.getValueAt(i, 0)) {//neu mon hoc duoc chon
                    //lay tat ca gia tri vao add vao bang mon hoc se mo
                    Vector data_rows = new Vector();
                    data_rows.add(dtm.getValueAt(i, 1));
                    data_rows.add(dtm.getValueAt(i, 3));
                    data_rows.add(dtm.getValueAt(i, 5));
                    data_rows.add(dtm.getValueAt(i, 6));
                    dtmRegisted.addRow(data_rows);
                }
            }
            //cho phep bam nut xuat danh sach mon hoc
            btnExportListRegisted.setEnabled(true);
////////////////////////////////////////////////////////////////////////////////////
            //kiem tra mon hoc tien quyet
            for (int i = 0; i < dtmRegisted.getRowCount(); i++) {
                String maMonHoc = String.valueOf(dtmRegisted.getValueAt(i, 0));
                if (!rcBLL.CheckCourseRequest(txtMssv.getText(), maMonHoc)) {
                    JOptionPane.showMessageDialog(this,
                            "Bạn phải học và đậu các môn tiên quyết của môn:\n "
                            + String.valueOf(dtmRegisted.getValueAt(i, 1)));
                    //xoa mon khong du dieu kien mon hoc tien quyet
                    dtmRegisted.removeRow(i);
                }
            }
            ///////tinh tien va so tin chi
            if(!CaculateMoneyAndCredit()){//neu không thỏa số lượng tín chỉ tối đa
                return;
            }
            //tạo đối tượng public de luu du lieu PHIEUDANGKY
            ClsRegisterForm_Public rfPublic = new ClsRegisterForm_Public();
            rfPublic.setIdSemesterYears(idSemesterYear);
            rfPublic.setIdStaff((new ClsStaffLoginInfo_Public()).getMaNV());
            rfPublic.setIdRegisterForm("PDK00000");
            rfPublic.setIdStudent(txtMssv.getText());
            rfPublic.setDateOfRegister(today);
            rfPublic.setSumOfMoneyRegister(registerMoney);
            rfPublic.setSumOfMoneyPaid(BigDecimal.ZERO);
            rfPublic.setSumOfMoneyMustPay(sumMoneyMustPay);
            //kiem tra xem sinhvien nay da dang ki mon hoc o hoc ki nay lan nao chua
            //String maPhieuDangKy = rcBLL.CheckRegistedOrNot(rfPublic);//lay ma phieu dang ki cua sinh vien o hoc ki nay
            if (maPhieuDangKy.isEmpty()) {//nếu mã phiếu trống, tức là chưa đăng kí lần nào thì thêm mới                
                PrepareToRegisterCourse();//chuan bi de dang ki
                if (rcBLL.SaveToRegisterForm(rfPublic) > 0) {//tien hanh dang ki moi cho sinh vien
                    JOptionPane.showMessageDialog(this, "Đăng kí thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Đăng kí thất bại!");
                    //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                    DeleteDataFromListCourseRegisted();
                }
            } else {//neu roi thi cap nhat dang ki
                rfPublic.setIdRegisterForm(maPhieuDangKy);//cap nhat ma phieu dang ky nay
                //JOptionPane.showMessageDialog(this, "Đăng kí rồi mà đăng kí chi nữa! cập nhật nha");
                PrepareToRegisterCourse();//chuan bi de dang ki
                if (rcBLL.UpdateRegisterForm(rfPublic) > 0) {//tien hanh cap nhat dang ki cho sinh vien
                    JOptionPane.showMessageDialog(this, "Cập nhật đăng kí thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật đăng kí thất bại!");
                    //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
                    DeleteDataFromListCourseRegisted();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Đăng kí thất bại!");
            //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
            DeleteDataFromListCourseRegisted();
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private boolean CaculateMoneyAndCredit() throws Exception {
        //tính tổng số tiền đăng kí
        //lấy đối tượng của sinh viên
        ResultSet rs = rcBLL.GetPercentReduceFee(txtMssv.getText());
        int percentReduceFee = 0;
        if (rs.next()) {
            percentReduceFee = rs.getInt(1);
        }
        registerMoney = BigDecimal.ZERO;
        for (int i = 0; i < dtmRegisted.getRowCount(); i++) {
            BigDecimal temp = rcBLL.InMoneyCourse(String.valueOf(dtmRegisted.getValueAt(i, 0)));
            if (temp != BigDecimal.ZERO) {
                registerMoney = registerMoney.add(temp);
            }
        }
        String[] money = registerMoney.toString().trim().split("\\.");
        if (money.length > 0) {
            txtSumOfFee.setText(money[0]);
        }
        //tinh tong tien phai tra: da tru di so tien hoc phi duoc giam do đối tượng
        sumMoneyMustPay = registerMoney.subtract(registerMoney.multiply(new BigDecimal((float) percentReduceFee / 100)));
        txtSumFeeMustPay.setText(sumMoneyMustPay.toString().trim().split("\\.")[0]);
        //tính tổng số tín chỉ
        int sumOfCredit = 0;
        for (int i = 0; i < dtmRegisted.getRowCount(); i++) {
            int temp = Integer.parseInt(String.valueOf(dtmRegisted.getValueAt(i, 3)));
            if (temp > 0) {
                sumOfCredit += temp;
            }
        }
        //tao doi tuong de kiem tra dieu kien số tín chỉ tối đa
        ClsRegulationStatic_Public rsp = new ClsRegulationStatic_Public();
        if(sumOfCredit < rsp.getMaxCredit()){
            txtSumOfCredit.setText(String.valueOf(sumOfCredit));
        } else {
            JOptionPane.showMessageDialog(this, "Số tín chỉ đăng kí vượt quá số tín chỉ tối đa trên một học kì: " + rsp.getMaxCredit());
            DeleteDataFromListCourseRegisted();
            return false;
        }   
        return true;
    }

    private void PrepareToRegisterCourse() throws Exception {
        String idReFoDe = rcBLL.CreateTempRegisterForm();//tao bảng tạm để lưu danh sách các môn học được đăng kí, va lay ma ctphieudangky lon nhat
        ClsRegisterFormDetail_Public rfdPublic = new ClsRegisterFormDetail_Public();
        //lay ma chi tiet phieu dang ki len để tăng mã rồi add vao bảng tạm
        for (int i = 0; i < dtmRegisted.getRowCount(); i++) {
            if (idReFoDe.isEmpty()) {
                idReFoDe = "CDK0000001";//ma dau tien
            } else {//neu khong phai la ma dau tien thi tang ma len
                int maso = Integer.parseInt(idReFoDe.substring(3)) + 1;
                String temp = String.valueOf(maso);
                while (temp.length() < 7) {
                    temp = "0" + temp;
                }
                idReFoDe = "CDK" + temp;
            }
            rfdPublic.setIdRegisterFormDetail(idReFoDe);
            rfdPublic.setIdRegisterForm("PDK00000");
            rfdPublic.setIdCourse(String.valueOf(dtmRegisted.getValueAt(i, 0)));
            rcBLL.InsertTempRegisterFormDetail(rfdPublic);//insert du lieu vao bang temp vua tao
        }
    }

    private void DeleteDataFromListCourseRegisted() {
        if (dtmRegisted.getRowCount() > 0) {
            int a = dtmRegisted.getRowCount();
            for (int i = a - 1; i >= 0; i--) {
                dtmRegisted.removeRow(i);
            }
        }
        txtSumOfCredit.setText("0");
        txtSumOfFee.setText("0");
        txtSumFeeMustPay.setText("0");
        btnCancelCurrentCourse.setEnabled(false);
        btnCancelAllCourseRegisted.setEnabled(false);
        btnExportListRegisted.setEnabled(false);
    }

    private void cboHocKiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHocKiActionPerformed
        CheckStudentAndLoadCourse();
    }//GEN-LAST:event_cboHocKiActionPerformed

    private void btnSearchStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchStudentActionPerformed
        //thay doi chu hien thi tren button
        String label1 = "Sinh viên mới";
        String label2 = "Tìm kiếm";
        //neu là nút tìm kiếm
        if (btnSearchStudent.getText().equals(label2)) {
            if (!CheckStudentAndLoadCourse()) {
                return;
            }
            txtMssv.setEnabled(false);
        } else {//neu la nut Sinh vien moi
            txtMssv.setEnabled(true);
            txtMssv.setText("");
            txtKhoa.setText("");
            txtNganh.setText("");
            txtHoTen.setText("");
            txtSumFeeMustPay.setText("");
            txtSumOfCredit.setText("");
            txtSumOfFee.setText("");
            //lam moi tat ca du lieu trong cac bang
            dtm.setRowCount(0);
            dtmRegisted.setRowCount(0);
        }
        //chuyen text cua button
        if (!btnSearchStudent.getText().equals(label1)) {
            btnSearchStudent.setText(label1);
        } else {
            btnSearchStudent.setText(label2);
        }
    }//GEN-LAST:event_btnSearchStudentActionPerformed

    private void NewStudent() {
        txtMssv.setEnabled(true);
        txtMssv.setText("");
        txtKhoa.setText("");
        txtNganh.setText("");
        txtHoTen.setText("");
        txtSumFeeMustPay.setText("");
        txtSumOfCredit.setText("");
        txtSumOfFee.setText("");
        //lam moi tat ca du lieu trong cac bang
        dtm.setRowCount(0);
        dtmRegisted.setRowCount(0);
        //chuyen text cua button
        btnSearchStudent.setText("Tìm kiếm");
    }

    private void btnCancelCurrentCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelCurrentCourseActionPerformed
        //duyet qua tat cả các môn, nếu môn nào có chọn thì kiểm tra điều kiện
        int currentRow = tblListCourseRegisted.getSelectedRow();
        if (currentRow < 0 || idSemesterYear.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hủy môn học thất bại!");
            return;
        }
        ClsRegisterFormDetail_Public rfdPublic = new ClsRegisterFormDetail_Public();
        //kiem tra ma phieu dang ky da co hay chua? chua co thi lay len
        if (!maPhieuDangKy.isEmpty()) {
            rfdPublic.setIdRegisterForm(maPhieuDangKy);
        } else {
            ClsRegisterForm_Public rfPublic = new ClsRegisterForm_Public();
            rfPublic.setIdSemesterYears(idSemesterYear);
            rfPublic.setIdStudent(txtMssv.getText());
            try {
                maPhieuDangKy = rcBLL.CheckRegistedOrNot(rfPublic);//lay ma phieu dang ki cua sinh vien o hoc ki nay
                rfdPublic.setIdRegisterForm(maPhieuDangKy);
            } catch (Exception ex) {
                Logger.getLogger(FrmRegisterCourses.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dtmRegisted.getValueAt(currentRow, 3).equals("LMH00001")) {
            //neu mon hoc ly thuyet thi kiem tra co mon hoc thuc hanh nao khong
            for (int j = 0; j < dtmRegisted.getRowCount(); j++) {
                //neu ma mon hoc hien tai bang voi ma mon hoc ly thuyet cua dong nao, thi set(true) cho dong do 
                if (dtmRegisted.getValueAt(currentRow, 0).equals(dtmRegisted.getValueAt(j, 1))) {
                    //show message thong bao
                    if (JOptionPane.showConfirmDialog(null, "Đồng thời hủy môn học thực hành của môn học này!", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        if (currentRow > j) {
                            try {
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(currentRow);
                                }
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(j, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(j);
                                }
                                btnCancelCurrentCourse.setEnabled(false);
                                CaculateMoneyAndCredit();
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(j, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(j);
                                }
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(currentRow);
                                }
                                btnCancelCurrentCourse.setEnabled(false);
                                CaculateMoneyAndCredit();
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
            //neu khong lien quan den mon hoc nao thi huy mon hien tai di            
            try {
                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                    dtmRegisted.removeRow(currentRow);
                    btnCancelCurrentCourse.setEnabled(false);
                    CaculateMoneyAndCredit();
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {//neu la mon hoc thuc hanh
            for (int j = 0; j < dtmRegisted.getRowCount(); j++) {
                //kiem tra xem ma mon hoc ly thuyet cua mon hoc hien tai bang voi ma mon hoc nao, thi set(true)
                if (dtmRegisted.getValueAt(currentRow, 1).equals(dtmRegisted.getValueAt(j, 0))) {
                    //show message thong bao
                    if (JOptionPane.showConfirmDialog(null, "Đồng thời hủy môn học lý thuyết của môn học này!", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        if (currentRow > j) {
                            try {
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(currentRow);
                                }
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(j, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(j);
                                }
                                btnCancelCurrentCourse.setEnabled(false);
                                CaculateMoneyAndCredit();
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(j, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(j);
                                }
                                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                                    dtmRegisted.removeRow(currentRow);
                                }
                                btnCancelCurrentCourse.setEnabled(false);
                                CaculateMoneyAndCredit();
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
            //neu khong lien quan den mon hoc nao thi huy mon hien tai di            
            try {
                rfdPublic.setIdCourse((String) dtmRegisted.getValueAt(currentRow, 0));
                if (rcBLL.CancelACourseRegisted(rfdPublic) > 0) {
                    dtmRegisted.removeRow(currentRow);
                    btnCancelCurrentCourse.setEnabled(false);
                    CaculateMoneyAndCredit();
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        btnCancelCurrentCourse.setEnabled(false);
    }//GEN-LAST:event_btnCancelCurrentCourseActionPerformed

    private void btnCancelAllCourseRegistedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAllCourseRegistedActionPerformed
        if (tblListCourseRegisted.getRowCount() <= 0) {
            return;
        }
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy tất cả?", "Cảnh báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
            //kiem tra ma phieu dang ky
            ClsRegisterFormDetail_Public rfdPublic = new ClsRegisterFormDetail_Public();//luu ma phieu dang ki de xoa
            ClsRegisterForm_Public rfPublic = new ClsRegisterForm_Public();
            if (!maPhieuDangKy.isEmpty()) {
                rfdPublic.setIdRegisterForm(maPhieuDangKy);
            } else {
                rfPublic.setIdSemesterYears(idSemesterYear);
                rfPublic.setIdStudent(txtMssv.getText());
                try {
                    maPhieuDangKy = rcBLL.CheckRegistedOrNot(rfPublic);//lay ma phieu dang ki cua sinh vien o hoc ki nay
                    rfdPublic.setIdRegisterForm(maPhieuDangKy);
                } catch (Exception ex) {
                    Logger.getLogger(FrmRegisterCourses.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //bat dau huy tat ca mon hoc
            try {
                if (rcBLL.CancelAllCourseRegisted(rfdPublic) > 0) {
                    DeleteDataFromListCourseRegisted();
                    JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thất bại!");
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thất bại!");
            }
        }
    }//GEN-LAST:event_btnCancelAllCourseRegistedActionPerformed

    private void btnExportListRegistedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportListRegistedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportListRegistedActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
        CheckStudentAndLoadCourse();
    }//GEN-LAST:event_cboYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel ControlPanel;
    private org.jdesktop.swingx.JXPanel ControlPanel1;
    private org.jdesktop.swingx.JXPanel FullPanel;
    private org.jdesktop.swingx.JXLabel HoTen;
    private org.jdesktop.swingx.JXLabel HoTen1;
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopInFull;
    private org.jdesktop.swingx.JXPanel TopLeft;
    private org.jdesktop.swingx.JXPanel TopLeft1;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnCancelAll;
    private org.jdesktop.swingx.JXButton btnCancelAllCourseRegisted;
    private org.jdesktop.swingx.JXButton btnCancelCurrentCourse;
    private org.jdesktop.swingx.JXButton btnExportListRegisted;
    private org.jdesktop.swingx.JXButton btnRegister;
    private org.jdesktop.swingx.JXButton btnSearchStudent;
    private javax.swing.JComboBox cboHocKi;
    private javax.swing.JComboBox cboYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXLabel lbHoTen2;
    private org.jdesktop.swingx.JXLabel lbHoTen3;
    private org.jdesktop.swingx.JXLabel lbHoTen4;
    private org.jdesktop.swingx.JXLabel lbHoTen5;
    private org.jdesktop.swingx.JXLabel lbHoTen6;
    private org.jdesktop.swingx.JXLabel lbHoTen7;
    private org.jdesktop.swingx.JXLabel lbHoTen8;
    private org.jdesktop.swingx.JXLabel lbHoTen9;
    private org.jdesktop.swingx.JXLabel lbMSSV;
    private org.jdesktop.swingx.JXTable tblListCourseCanBeRegister;
    private org.jdesktop.swingx.JXTable tblListCourseRegisted;
    private org.jdesktop.swingx.JXTextField txtHoTen;
    private org.jdesktop.swingx.JXTextField txtKhoa;
    private org.jdesktop.swingx.JXTextField txtMssv;
    private org.jdesktop.swingx.JXTextField txtNganh;
    private org.jdesktop.swingx.JXTextField txtSumFeeMustPay;
    private org.jdesktop.swingx.JXTextField txtSumOfCredit;
    private org.jdesktop.swingx.JXTextField txtSumOfFee;
    // End of variables declaration//GEN-END:variables
}
