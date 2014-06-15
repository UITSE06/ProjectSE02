/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import PUBLIC.*;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author John
 */
public class FrmChooseCourseToOpen extends javax.swing.JPanel {

    /**
     * Creates new form DangKiMonHoc2
     */
    private final clsFacultyBLL kBLL = new clsFacultyBLL();
    private final ClsChooseCourse_BLL ccBLL = new ClsChooseCourse_BLL();
    private ClsListCourseOpened_BLL lcBLL = new ClsListCourseOpened_BLL();
    private final ClsPaymentFee_BLL pfBLL = new ClsPaymentFee_BLL();
    private DefaultTableModel dtm;
    private DefaultTableModel dtmOpened;
    private String idSemesterYear = "";//bien luu ma hoc ki nam hoc
    private boolean isUpdate = false;//bien kiem tra xem can cap nhat hay them moi danh sach mon hoc mo
    private boolean isViewOnly = false;
    private ClsListCoursesOpened_Public lcPulic = new ClsListCoursesOpened_Public();

    public FrmChooseCourseToOpen() {
        initComponents();
        //chuyen thong tin nhung lop se mo qua bang danh sach mon hoc se mo
        dtmOpened = (DefaultTableModel) tblListCourseOpened.getModel();

        tblListCourse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblListCourseOpened.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        dtpDeadlineRegister.setFormats("dd/MM/yyyy");
        dtpDeadlinePayFee.setFormats("dd/MM/yyyy");
        dtpDeadlineReduceFee.setFormats("dd/MM/yyyy");
        dtpBeginRegister.setFormats("dd/MM/yyyy");

        //thiet ke giao dien bang danh sach môn học
        // ẩn cột ma mon ly thuyet di
        tblListCourse.getColumnModel().getColumn(2).setMinWidth(0);
        tblListCourse.getColumnModel().getColumn(2).setMaxWidth(0);
        tblListCourse.getColumnModel().getColumn(2).setWidth(0);
        // ẩn cột ma Loai mh di
        tblListCourse.getColumnModel().getColumn(4).setMinWidth(0);
        tblListCourse.getColumnModel().getColumn(4).setMaxWidth(0);
        tblListCourse.getColumnModel().getColumn(4).setWidth(0);
        // set size cho cot "Chọn"
        tblListCourse.getColumnModel().getColumn(0).setMinWidth(60);
        tblListCourse.getColumnModel().getColumn(0).setMaxWidth(60);
        tblListCourse.getColumnModel().getColumn(0).setWidth(60);
        // set size cho cot "Mã Môn học"
        tblListCourse.getColumnModel().getColumn(1).setMinWidth(90);
        tblListCourse.getColumnModel().getColumn(1).setMaxWidth(90);
        tblListCourse.getColumnModel().getColumn(1).setWidth(90);
        // set size cho cot "Số Tiết"
        tblListCourse.getColumnModel().getColumn(6).setMinWidth(60);
        tblListCourse.getColumnModel().getColumn(6).setMaxWidth(60);
        tblListCourse.getColumnModel().getColumn(6).setWidth(60);
        //thiet ke giao dien bang danh sach môn học se mo
        // ẩn cột ma mon ly thuyet di
        tblListCourseOpened.getColumnModel().getColumn(1).setMinWidth(0);
        tblListCourseOpened.getColumnModel().getColumn(1).setMaxWidth(0);
        tblListCourseOpened.getColumnModel().getColumn(1).setWidth(0);
        // ẩn cột ma Loai mh di
        tblListCourseOpened.getColumnModel().getColumn(3).setMinWidth(0);
        tblListCourseOpened.getColumnModel().getColumn(3).setMaxWidth(0);
        tblListCourseOpened.getColumnModel().getColumn(3).setWidth(0);
        // set size cho cot "So tiet"
        tblListCourseOpened.getColumnModel().getColumn(5).setMinWidth(60);
        tblListCourseOpened.getColumnModel().getColumn(5).setMaxWidth(60);
        tblListCourseOpened.getColumnModel().getColumn(5).setWidth(60);
        // set size cho cot "Ma mon hoc"
        tblListCourseOpened.getColumnModel().getColumn(0).setMinWidth(90);
        tblListCourseOpened.getColumnModel().getColumn(0).setMaxWidth(90);
        tblListCourseOpened.getColumnModel().getColumn(0).setWidth(90);
        //thêm sự kiện cho bang danh sach mon học
        tblListCourse.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean flag = false;
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    //neu co mon nao do duoc chon thi enable btn...
                    if ((boolean) dtm.getValueAt(i, 0)) {
                        if (!btnOpenCourses.isEnabled()) {
                            btnOpenCourses.setEnabled(true);
                            btnCancelAll.setEnabled(true);
                        }
                        flag = true;
                    }
                }
                if (!flag && btnOpenCourses.isEnabled()) {//flag van bang false tuc la chua co mon nao duoc chon
                    btnOpenCourses.setEnabled(false);
                    btnCancelAll.setEnabled(false);
                }
            }
        });
        //them su kien cho bang danh sach mon hoc da mo
        tblListCourseOpened.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblListCourseOpened.getSelectedRow() > -1 && !isViewOnly) {
                    btnCancelSelectedCourse.setEnabled(true);
                } else {
                    btnCancelSelectedCourse.setEnabled(false);
                }
                if (dtmOpened.getRowCount() > 0 && !isViewOnly) {
                    btnCancelAllInOpen.setEnabled(true);
                } else {
                    btnCancelAllInOpen.setEnabled(false);
                }
            }
        });
        LayDuLieu();
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
        ControlPanel = new org.jdesktop.swingx.JXPanel();
        btnChooseAll = new org.jdesktop.swingx.JXButton();
        btnCancelAll = new org.jdesktop.swingx.JXButton();
        btnOpenCourses = new org.jdesktop.swingx.JXButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListCourse = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListCourseOpened = new org.jdesktop.swingx.JXTable();
        ControlPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        cboSemester = new javax.swing.JComboBox();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        dtpDeadlineRegister = new org.jdesktop.swingx.JXDatePicker();
        lbHoTen1 = new org.jdesktop.swingx.JXLabel();
        dtpDeadlinePayFee = new org.jdesktop.swingx.JXDatePicker();
        lbHoTen2 = new org.jdesktop.swingx.JXLabel();
        dtpDeadlineReduceFee = new org.jdesktop.swingx.JXDatePicker();
        lbHoTen3 = new org.jdesktop.swingx.JXLabel();
        lbHoTen4 = new org.jdesktop.swingx.JXLabel();
        spReducePercent = new javax.swing.JSpinner();
        lbHoTen5 = new org.jdesktop.swingx.JXLabel();
        cboYear = new javax.swing.JComboBox();
        lbHoTen6 = new org.jdesktop.swingx.JXLabel();
        dtpBeginRegister = new org.jdesktop.swingx.JXDatePicker();
        ControlPanel2 = new org.jdesktop.swingx.JXPanel();
        btnCancelSelectedCourse = new org.jdesktop.swingx.JXButton();
        btnCancelAllInOpen = new org.jdesktop.swingx.JXButton();
        btnExportListCourse = new org.jdesktop.swingx.JXButton();
        TopPanel = new org.jdesktop.swingx.JXPanel();
        Title = new org.jdesktop.swingx.JXLabel();

        ControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển danh sách môn học"));
        ControlPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnChooseAll.setText("Chọn tất cả");
        btnChooseAll.setEnabled(false);
        btnChooseAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseAllActionPerformed(evt);
            }
        });
        ControlPanel.add(btnChooseAll);

        btnCancelAll.setText("Hủy tất cả");
        btnCancelAll.setEnabled(false);
        btnCancelAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAllActionPerformed(evt);
            }
        });
        ControlPanel.add(btnCancelAll);

        btnOpenCourses.setText("Mở những môn học đang chọn");
        btnOpenCourses.setEnabled(false);
        btnOpenCourses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCoursesActionPerformed(evt);
            }
        });
        ControlPanel.add(btnOpenCourses);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách môn học"));

        tblListCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chọn", "Mã môn học", "MaMHLT", "Tên môn học", "MaLoaiMH", "Loại môn học", "Số tiết"
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
        tblListCourse.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblListCourse.setEditable(false);
        tblListCourse.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblListCourse);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách môn học đã mở"));

        tblListCourseOpened.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã môn học", "MaMHLT", "Tên môn học", "MaLoaiMH", "Loại môn học", "Số tiết"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListCourseOpened.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblListCourseOpened.setEditable(false);
        tblListCourseOpened.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblListCourseOpened);

        ControlPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Quản lý thời gian"));

        jXLabel1.setText("Học kì");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        cboSemester.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cboSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSemesterActionPerformed(evt);
            }
        });

        jXLabel2.setText("Năm học");
        jXLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbHoTen1.setText("Hạn đăng kí:");
        lbHoTen1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbHoTen2.setText("Hạn đóng học phí:");
        lbHoTen2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbHoTen3.setText("Hạn được giảm:");
        lbHoTen3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbHoTen4.setText("Phần trăm giảm:");
        lbHoTen4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        spReducePercent.setModel(new javax.swing.SpinnerNumberModel(5, 0, 100, 1));

        lbHoTen5.setText("%");
        lbHoTen5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboYearActionPerformed(evt);
            }
        });

        lbHoTen6.setText("Bắt đầu đăng kí:");
        lbHoTen6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout ControlPanel1Layout = new javax.swing.GroupLayout(ControlPanel1);
        ControlPanel1.setLayout(ControlPanel1Layout);
        ControlPanel1Layout.setHorizontalGroup(
            ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dtpBeginRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dtpDeadlineRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dtpDeadlineReduceFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(dtpDeadlinePayFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbHoTen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spReducePercent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ControlPanel1Layout.setVerticalGroup(
            ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanel1Layout.createSequentialGroup()
                .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ControlPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbHoTen6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtpBeginRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ControlPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbHoTen4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spReducePercent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtpDeadlineRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtpDeadlineReduceFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dtpDeadlinePayFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(2, 2, 2))
        );

        ControlPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển danh sách môn học đã  mở"));
        ControlPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnCancelSelectedCourse.setText("Hủy môn học đang chọn");
        btnCancelSelectedCourse.setEnabled(false);
        btnCancelSelectedCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSelectedCourseActionPerformed(evt);
            }
        });
        ControlPanel2.add(btnCancelSelectedCourse);

        btnCancelAllInOpen.setText("Hủy tất cả");
        btnCancelAllInOpen.setEnabled(false);
        btnCancelAllInOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAllInOpenActionPerformed(evt);
            }
        });
        ControlPanel2.add(btnCancelAllInOpen);

        btnExportListCourse.setText("Xuất danh sách");
        btnExportListCourse.setEnabled(false);
        btnExportListCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportListCourseActionPerformed(evt);
            }
        });
        ControlPanel2.add(btnExportListCourse);

        javax.swing.GroupLayout FullPanelLayout = new javax.swing.GroupLayout(FullPanel);
        FullPanel.setLayout(FullPanelLayout);
        FullPanelLayout.setHorizontalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FullPanelLayout.createSequentialGroup()
                        .addComponent(ControlPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(FullPanelLayout.createSequentialGroup()
                        .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ControlPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addGap(10, 10, 10))))
        );
        FullPanelLayout.setVerticalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addComponent(ControlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ControlPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("CHỌN DANH SÁCH MÔN HỌC SẼ MỞ");
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
                .addGap(0, 0, 0))
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
                .addComponent(FullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void LayDuLieu() {
        try {
            dtm = (DefaultTableModel) tblListCourse.getModel();
            //xoa dữ liệu hiện tại trong bảng nếu có
            if (dtm.getRowCount() > 0) {
                for (int i = dtm.getRowCount() - 1; i > 0; i--) {
                    dtm.removeRow(i);
                }
            }
            ResultSet rs = ccBLL.LoadCoursesToOpen();
            while (rs.next()) {
                Vector data_rows = new Vector();
                data_rows.add(false);
                data_rows.add(rs.getObject(1));
                data_rows.add(rs.getObject(2));
                data_rows.add(rs.getObject(3));
                data_rows.add(rs.getObject(4));
                data_rows.add(rs.getObject(5));
                data_rows.add(rs.getObject(6));
                dtm.addRow(data_rows);
                btnChooseAll.setEnabled(true);
            }
            //lay thong tin cac nam hoc da co            
            rs = pfBLL.LoadFirstYear();
            while (rs.next()) {
                int first = rs.getInt(1);
                cboYear.addItem(String.valueOf(first) + " - " + String.valueOf(first + 1));
            }
            //tu dong them nam hoc mới theo ngày giờ hệ thống nếu chưa có
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);
            boolean isExistThisYear = false;
            for (int i = 0; i < cboYear.getItemCount(); i++) {
                int a = Integer.parseInt(String.valueOf(cboYear.getItemAt(i)).trim().split("\\ - ")[0]);
                if (a == thisYear) {
                    isExistThisYear = true;
                }
            }
            if (!isExistThisYear) {
                cboYear.addItem(String.valueOf(thisYear) + " - " + String.valueOf(thisYear + 1));
            }
            isUpdate = CheckSemesterYearsAndListCourseOpened();//kiem tra da ton tai ma hoc ki nam hoc hien tai trong bang DANHSACHMONHOCMO hay chua?
            //neu co roi thi load danh sach do len, isUpdate = true
            if (isUpdate) {
                btnOpenCourses.setText("Cập nhật danh sách");
            } else {
                btnOpenCourses.setText("Mở những môn học đang chọn");
            }

        } catch (Exception ex) {
            Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btnChooseAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseAllActionPerformed
        //btn Chọn tất cả
        //duyệt qua tất cả các dòng của table và gán cột đầu tiên là true.
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(true, i, 0);
        }
        btnChooseAll.setEnabled(false);
        btnOpenCourses.setEnabled(true);
        btnCancelAll.setEnabled(true);
    }//GEN-LAST:event_btnChooseAllActionPerformed

    private void btnOpenCoursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCoursesActionPerformed
//kiem tra input
        if (dtpDeadlinePayFee.getEditor().getText().isEmpty()
                || dtpDeadlineReduceFee.getEditor().getText().isEmpty()
                || dtpDeadlineRegister.getEditor().getText().isEmpty()
                || spReducePercent.getValue().toString().isEmpty()
                || dtpBeginRegister.getEditor().getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đủ thông tin!");
            return;
        }
        //kiem tra rang buoc thoi gian, so sanh thoi gian, gia tri phan tram khuyen khich
        if (dtpDeadlineRegister.getDate().after(dtpDeadlineReduceFee.getDate())//han dang ki phai truoc han giam hoc phi
                || dtpDeadlineRegister.getDate().after(dtpDeadlinePayFee.getDate())//han dang ki phai truoc han dong hp
                || dtpDeadlineReduceFee.getDate().after(dtpDeadlinePayFee.getDate())//han giam học phí phải trước hạn đóng học phí
                || dtpBeginRegister.getDate().after(dtpDeadlineRegister.getDate())) {//ngay bat dau dang ki phai truoc ngay het han dang ki
            JOptionPane.showMessageDialog(this, "Nhập sai thời gian hoặc phần trăm học phí được giảm!");
            return;
        }
        if ((Calendar.getInstance().getTime()).after(dtpBeginRegister.getDate())) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu đăng kí phải sau ngày hôm nay!");
            return;
        }
//kiem tra điều kiện mở mon hoc
        //duyet qua tat cả các môn, nếu môn nào có chọn thì kiểm tra điều kiện
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
//chuyen nhung mon hoc trong bang ds mon hoc, qua bang ds mon hoc da mo            
            DeleteAllFromOpenedCourse();//xoa tat ca cac mon hoc hien tai neu co
            for (int i = 0; i < dtm.getRowCount(); i++) {
                if ((boolean) dtm.getValueAt(i, 0)) {//neu mon hoc duoc chon
                    //lay tat ca gia tri vao add vao bang mon hoc se mo
                    Vector data_rows = new Vector();
                    data_rows.add(dtm.getValueAt(i, 1));
                    data_rows.add(dtm.getValueAt(i, 2));
                    data_rows.add(dtm.getValueAt(i, 3));
                    data_rows.add(dtm.getValueAt(i, 4));
                    data_rows.add(dtm.getValueAt(i, 5));
                    data_rows.add(dtm.getValueAt(i, 6));
                    dtmOpened.addRow(data_rows);
                }
            }
            //enable button
            btnCancelAllInOpen.setEnabled(true);
            btnExportListCourse.setEnabled(true);
// kiem tra ma hoc ki nam hoc co trong bang DANHSACHMONHOCMO hay chua?
            if (isUpdate) {//có mở ít nhất 1 lần rồi thì cập nhật danh sách
                //cập nhật các deadline trong bang HOCKINAMHOC 
                ClsSemesterYearPublic syPublic = new ClsSemesterYearPublic();
                syPublic.setRegisterDeadline(new Date(dtpDeadlineRegister.getDate().getTime()));
                syPublic.setBeginRegister(new Date(dtpBeginRegister.getDate().getTime()));
                syPublic.setGetFeeDeadline(new Date(dtpDeadlinePayFee.getDate().getTime()));
                syPublic.setReduceDeadline(new Date(dtpDeadlineReduceFee.getDate().getTime()));
                syPublic.setReducePercent((int) spReducePercent.getValue());
                syPublic.setIdSemesterYear(idSemesterYear);
                if (lcBLL.UpdateDeadlineAndPercentReduce(syPublic) <= 0) {
                    JOptionPane.showMessageDialog(this, "Lưu thất bại!");
                    DeleteAllFromOpenedCourse();
                    return;
                }
                int abc = lcBLL.CreateTempDSMHMO();//tao bang tạm để cập nhật các môn sẽ được mở
                if (abc == 0) {//neu tao bang tam thanh cong
                    for (int i = 0; i < dtmOpened.getRowCount(); i++) {
                        lcPulic.setIdSemesterYears(idSemesterYear);
                        lcPulic.setIdCourse(String.valueOf(dtmOpened.getValueAt(i, 0)));
                        lcBLL.InsertTempDSMHMO(lcPulic);//thêm thông tin vào bảng tạm
                    }
                    lcBLL.UpdateListCourseOpened(lcPulic);//cập nhật danh sách môn học
                    JOptionPane.showMessageDialog(this, "Cập nhật những môn học đã mở thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật những môn học đã mở thất bại!");
                    //load lai danh sach cu
                    CheckSemesterYearsAndListCourseOpened();
                }
//chưa mở lần nào thì thêm mới                
            } else {

                ClsSemesterYearPublic syPublic = new ClsSemesterYearPublic();
                syPublic.setIdSemesterYear("NH0000");
                syPublic.setFirstYear(Integer.parseInt(String.valueOf(cboYear.getSelectedItem()).trim().split("\\ - ")[0]));
                syPublic.setSecondYear(syPublic.getFirstYear() + 1);
                syPublic.setSemester(Integer.parseInt(String.valueOf(cboSemester.getSelectedItem())));
                syPublic.setBeginRegister(new Date(dtpBeginRegister.getDate().getTime()));
                syPublic.setRegisterDeadline(new Date(dtpDeadlineRegister.getDate().getTime()));
                syPublic.setGetFeeDeadline(new Date(dtpDeadlinePayFee.getDate().getTime()));
                syPublic.setReduceDeadline(new Date(dtpDeadlineReduceFee.getDate().getTime()));
                syPublic.setReducePercent((int) spReducePercent.getValue());

                ClsSemesterYear_BLL syBLL = new ClsSemesterYear_BLL();//tao doi tuong BLL de goi ham thuc thi
//kiem tra xem da co mã học kì năm học của học kì đang chọn trong bang HOCKINAMHOC hay chưa?
                if (idSemesterYear.isEmpty()) {//chưa có thì thêm mới
                    if (syBLL.InsertSemesterYear(syPublic) > 0) {//thêm mới
                        idSemesterYear = syBLL.GetIdSemesterYear(syPublic);//lay ma hoc ki nam hoc vua mơi thêm
                    }
                } else {//đã có học kì năm học rồi thì cập nhật dealine và phần trăm học phí giảm
                    syPublic.setIdSemesterYear(idSemesterYear);
                    if (lcBLL.UpdateDeadlineAndPercentReduce(syPublic) <= 0) {
                        JOptionPane.showMessageDialog(this, "Lưu thất bại!");
                        DeleteAllFromOpenedCourse();
                        return;
                    }
                }
//them danh sach mon hoc mo
                int i;
                for (i = 0; i < dtmOpened.getRowCount(); i++) {
                    lcPulic.setIdSemesterYears(idSemesterYear);
                    lcPulic.setIdCourse(String.valueOf(dtmOpened.getValueAt(i, 0)));
                    lcBLL.InsertListCourseOpened(lcPulic);
                }
                if (i == dtmOpened.getRowCount()) {
                    JOptionPane.showMessageDialog(this, "Mở môn học thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Mở môn học thất bại!");
                    DeleteAllFromOpenedCourse();
                }
            }
            btnOpenCourses.setEnabled(false);
            LayDuLieu();//làm mới lại từ đầu
            //isUpdate = CheckSemesterYearsAndListCourseOpened();            
        } catch (Exception ex) {
            Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lưu thất bại!");
            DeleteAllFromOpenedCourse();
        }
    }//GEN-LAST:event_btnOpenCoursesActionPerformed

    private boolean CheckSemesterYearsAndListCourseOpened() throws Exception {
        idSemesterYear = "";

        ClsSemesterYearPublic syPublic = new ClsSemesterYearPublic();
        syPublic.setFirstYear(Integer.parseInt(String.valueOf(cboYear.getSelectedItem()).trim().split("\\ - ")[0]));
        syPublic.setSemester(Integer.parseInt(String.valueOf(cboSemester.getSelectedItem())));
        ClsSemesterYear_BLL syBLL = new ClsSemesterYear_BLL();//tao doi tuong BLL de goi ham thuc thi
        //kiem tra gia tri nam1 va hocki hiện tại đã co trong bang HOCKINAMHOC chua?
        if (syBLL.IsExistSemesterAndYear(syPublic)) {
            idSemesterYear = syBLL.GetIdSemesterYear(syPublic);
            if (!idSemesterYear.isEmpty()) {//neu co ma hoc ki nam hoc roi thì...
                //kiem tra cac deadline
                //lay thoi han dang ki, ...
                ResultSet rs = lcBLL.GetAllDeadline(idSemesterYear);
                if (rs.next()) {
                    dtpDeadlineRegister.setDate(rs.getDate(1));
                    dtpDeadlinePayFee.setDate(rs.getDate(2));
                    dtpDeadlineReduceFee.setDate(rs.getDate(3));
                    spReducePercent.setValue(rs.getInt(4));
                    //kiem tra xem da bat dau dang ki hay chưa?
                    Date beginRegister = rs.getDate(5);
                    dtpBeginRegister.setDate(beginRegister);
                    if (beginRegister.before(Calendar.getInstance().getTime())) {
                        //neu ngay Bd dang ki mà trước ngày hôm nay thì không cho phép chỉnh sửa
                        isViewOnly = true;
                        dtpDeadlineRegister.setEditable(false);
                        dtpDeadlinePayFee.setEditable(false);
                        dtpDeadlineReduceFee.setEditable(false);
                        spReducePercent.setEnabled(false);
                        dtpBeginRegister.setEditable(false);
                        btnOpenCourses.setEnabled(false);
                        btnChooseAll.setEnabled(false);
                        //xoa dữ liệu hiện tại trong bảng danh sach môn học nếu có
                        if (dtm.getRowCount() > 0) {
                            for (int i = (dtm.getRowCount() - 1); i >= 0; i--) {
                                dtm.removeRow(i);
                            }
                        }
                    } else {
                        isViewOnly = false;
                    }
                }
// kiem tra ma hoc ki nam hoc co trong bang DANHSACHMONHOCMO hay chua?
                ClsListCoursesOpened_Public lcPulic = new ClsListCoursesOpened_Public();
                lcPulic.setIdSemesterYears(idSemesterYear);
                if (lcBLL.IsExistIdSemesterYear(lcPulic)) {//có rồi thì 
                    //lay danh sach mon hoc da mo len
                    DeleteAllFromOpenedCourse();//xoa nhung mon hoc hien tai neu co
                    rs = lcBLL.GetListCourseOpened(idSemesterYear);
                    while (rs.next()) {
                        Vector data_rows = new Vector();
                        data_rows.add(rs.getObject(1));
                        data_rows.add(rs.getObject(2));
                        data_rows.add(rs.getObject(3));
                        data_rows.add(rs.getObject(4));
                        data_rows.add(rs.getObject(5));
                        data_rows.add(rs.getObject(6));
                        dtmOpened.addRow(data_rows);
                    }
                    return true;//tra ve true de biet lan nay chi cap nhat danh sach thoi
                }
            }
        } else { //nếu chưa có học kì năm học đang chọn thì enable cac component de them hoc ki và môn học
            isViewOnly = false;
            dtpDeadlineRegister.setEditable(true);
            dtpDeadlinePayFee.setEditable(true);
            dtpDeadlineReduceFee.setEditable(true);
            spReducePercent.setEnabled(true);
            dtpBeginRegister.setEditable(true);
            btnOpenCourses.setEnabled(false);
            btnChooseAll.setEnabled(true);
            //lay du lieu mon hoc de mo
            //xoa dữ liệu hiện tại trong bảng nếu có
            if (dtm.getRowCount() > 0) {
                for (int i = dtm.getRowCount() - 1; i > 0; i--) {
                    dtm.removeRow(i);
                }
            }
            ResultSet rs = ccBLL.LoadCoursesToOpen();
            while (rs.next()) {
                Vector data_rows = new Vector();
                data_rows.add(false);
                data_rows.add(rs.getObject(1));
                data_rows.add(rs.getObject(2));
                data_rows.add(rs.getObject(3));
                data_rows.add(rs.getObject(4));
                data_rows.add(rs.getObject(5));
                data_rows.add(rs.getObject(6));
                dtm.addRow(data_rows);
                btnChooseAll.setEnabled(true);
            }
        }
        return false; //lan nay la them moi danh sach
    }

    private void DeleteAllFromOpenedCourse() {
        if (dtmOpened.getRowCount() > 0) {
            int a = dtmOpened.getRowCount();
            for (int i = a - 1; i >= 0; i--) {
                dtmOpened.removeRow(i);
            }
        }
    }

    private void btnCancelAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAllActionPerformed
        //btn Huy tất cả
        //duyệt qua tất cả các dòng của table và gán cột đầu tiên là true.
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.setValueAt(false, i, 0);
        }
        btnChooseAll.setEnabled(true);
        btnOpenCourses.setEnabled(false);
        btnCancelAll.setEnabled(false);
    }//GEN-LAST:event_btnCancelAllActionPerformed

    private void btnCancelSelectedCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSelectedCourseActionPerformed
//duyet qua tat cả các môn, nếu môn nào có chọn thì kiểm tra điều kiện
        int currentRow = tblListCourseOpened.getSelectedRow();
        if (currentRow < 0 || idSemesterYear.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Hủy môn học thất bại!");
            return;
        }
        lcPulic.setIdSemesterYears(idSemesterYear);//gán id học kì năm học chung cho tất cả câu lệnh xóa
        if (dtmOpened.getValueAt(currentRow, 3).equals("LMH00001")) {
            //neu mon hoc ly thuyet thi kiem tra co mon hoc thuc hanh nao khong
            for (int j = 0; j < dtmOpened.getRowCount(); j++) {
                //neu ma mon hoc hien tai bang voi ma mon hoc ly thuyet cua dong nao, thi set(true) cho dong do 
                if (dtmOpened.getValueAt(currentRow, 0).equals(dtmOpened.getValueAt(j, 1))) {
                    //show message thong bao
                    if (JOptionPane.showConfirmDialog(null, "Đồng thời hủy môn học thực hành của môn học này!", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        if (currentRow > j) {
                            try {
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(currentRow);
                                }
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(j, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(j);
                                }
                                btnCancelSelectedCourse.setEnabled(false);
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(j, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(j);
                                }
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(currentRow);
                                }
                                btnCancelSelectedCourse.setEnabled(false);
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
                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                    dtmOpened.removeRow(currentRow);
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {//neu la mon hoc thuc hanh
            for (int j = 0; j < dtmOpened.getRowCount(); j++) {
                //kiem tra xem ma mon hoc ly thuyet cua mon hoc hien tai bang voi ma mon hoc nao, thi set(true)
                if (dtmOpened.getValueAt(currentRow, 1).equals(dtmOpened.getValueAt(j, 0))) {
                    //show message thong bao
                    if (JOptionPane.showConfirmDialog(null, "Đồng thời hủy môn học lý thuyết của môn học này!", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
                        if (currentRow > j) {
                            try {
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(currentRow);
                                }
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(j, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(j);
                                }
                                btnCancelSelectedCourse.setEnabled(false);
                                return;
                            } catch (Exception ex) {
                                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            try {
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(j, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(j);
                                }
                                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                                    dtmOpened.removeRow(currentRow);
                                }
                                btnCancelSelectedCourse.setEnabled(false);
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
                lcPulic.setIdCourse((String) dtmOpened.getValueAt(currentRow, 0));
                if (lcBLL.DeleterACourse(lcPulic) > 0) {
                    dtmOpened.removeRow(currentRow);
                }
            } catch (Exception ex) {
                Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        btnCancelSelectedCourse.setEnabled(false);
    }//GEN-LAST:event_btnCancelSelectedCourseActionPerformed

    private void btnCancelAllInOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAllInOpenActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy tất cả?", "Cảnh báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
            if (!idSemesterYear.isEmpty()) {
                lcPulic.setIdSemesterYears(idSemesterYear);
                try {
                    if (lcBLL.DeleterAllCourseOpened(lcPulic) > 0) {
                        if (dtmOpened.getRowCount() > 0) {
                            int a = dtmOpened.getRowCount();
                            for (int i = a - 1; i >= 0; i--) {
                                dtmOpened.removeRow(i);
                            }
                        }
                        btnCancelAllInOpen.setEnabled(false);
                        btnExportListCourse.setEnabled(false);
                        JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thành công!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thất bại!");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thất bại!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hủy tất cả môn học thất bại!");
            }
        }
    }//GEN-LAST:event_btnCancelAllInOpenActionPerformed

    private void btnExportListCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportListCourseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportListCourseActionPerformed

    private void cboSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSemesterActionPerformed
        try {
            DeleteAllFromOpenedCourse();//xoa nhung mon hoc hien tai neu co            
            isUpdate = CheckSemesterYearsAndListCourseOpened();
            if (isUpdate) {
                btnOpenCourses.setText("Cập nhật danh sách");
            } else {
                btnOpenCourses.setText("Mở những môn học đang chọn");
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboSemesterActionPerformed

    private void cboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboYearActionPerformed
        try {
            DeleteAllFromOpenedCourse();//xoa nhung mon hoc hien tai neu co            
            isUpdate = CheckSemesterYearsAndListCourseOpened();
            if (isUpdate) {
                btnOpenCourses.setText("Cập nhật danh sách");
            } else {
                btnOpenCourses.setText("Mở những môn học đang chọn");
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmChooseCourseToOpen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel ControlPanel;
    private org.jdesktop.swingx.JXPanel ControlPanel1;
    private org.jdesktop.swingx.JXPanel ControlPanel2;
    private org.jdesktop.swingx.JXPanel FullPanel;
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnCancelAll;
    private org.jdesktop.swingx.JXButton btnCancelAllInOpen;
    private org.jdesktop.swingx.JXButton btnCancelSelectedCourse;
    private org.jdesktop.swingx.JXButton btnChooseAll;
    private org.jdesktop.swingx.JXButton btnExportListCourse;
    private org.jdesktop.swingx.JXButton btnOpenCourses;
    private javax.swing.JComboBox cboSemester;
    private javax.swing.JComboBox cboYear;
    private org.jdesktop.swingx.JXDatePicker dtpBeginRegister;
    private org.jdesktop.swingx.JXDatePicker dtpDeadlinePayFee;
    private org.jdesktop.swingx.JXDatePicker dtpDeadlineReduceFee;
    private org.jdesktop.swingx.JXDatePicker dtpDeadlineRegister;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel lbHoTen1;
    private org.jdesktop.swingx.JXLabel lbHoTen2;
    private org.jdesktop.swingx.JXLabel lbHoTen3;
    private org.jdesktop.swingx.JXLabel lbHoTen4;
    private org.jdesktop.swingx.JXLabel lbHoTen5;
    private org.jdesktop.swingx.JXLabel lbHoTen6;
    private javax.swing.JSpinner spReducePercent;
    private org.jdesktop.swingx.JXTable tblListCourse;
    private org.jdesktop.swingx.JXTable tblListCourseOpened;
    // End of variables declaration//GEN-END:variables
}
