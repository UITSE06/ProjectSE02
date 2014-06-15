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
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTextField;

/**
 *
 * @author John
 */
public class FrmManageScore extends javax.swing.JPanel {

    //Thanh Thai
    private DefaultTableModel dtm;

    private final clsBangDiem_Public bdP = new clsBangDiem_Public();
    
    private final clsBangDiem_BLL bdBLL = new clsBangDiem_BLL();
    private final ClsRegisterCourses_BLL rcBLL = new ClsRegisterCourses_BLL();
    private final ClsSemesterYear_BLL syBLL = new ClsSemesterYear_BLL();

    private JXTextField txtDTB;

    /**
     * Creates new form FrmManageScore
     */
    public FrmManageScore() {
        initComponents();
        txtMssv.setDocument(new ClsLimitDocument_BLL(20));
        initTable();
        setTableSinhVienModel();
    }

    // Hàm khởi tạo table bảng điểm
    private void initTable() {
        String[] titleMH = {"Mã môn học", "Mã môn học lý thuyết", "Tên môn học", "Loại môn học", "Số tiết", "Điểm trung bình"};
        dtm = new DefaultTableModel(titleMH, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 5;
            }
        };
    }

    // Hàm set thuộc tính cho table bảng điểm
    private void setTableSinhVienModel() {
        tbListCourses.setModel(dtm);
        tbListCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // ẩn cột mã môn lý thuyết đi
        tbListCourses.getColumnModel().getColumn(1).setMinWidth(0);
        tbListCourses.getColumnModel().getColumn(1).setMaxWidth(0);
        tbListCourses.getColumnModel().getColumn(1).setWidth(0);

        // Load dữ liệu cho comboNam1
        setComboYearModel();
    }

    // load dữ liệu cho combobox Năm.
    private void setComboYearModel() {
        try {
            ResultSet rsSemesterYear = syBLL.LoadFirstYear();
            while (rsSemesterYear.next()) {
                cboNam1.addItem(rsSemesterYear.getString(1));
            }
            // Set dữ liệu cho txtNam2
            if (cboNam1.getSelectedItem() != null) {
                Integer a = (Integer.parseInt(cboNam1.getSelectedItem().toString()) + 1);
                String b = a.toString();
                txtNam2.setText(b);
            }
            cboNam1.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cboNam1.getSelectedItem() != null) {
                        Integer a = (Integer.parseInt(cboNam1.getSelectedItem().toString()) + 1);
                        String b = a.toString();
                        txtNam2.setText(b);
                    }
                }
            });
            // Chèn textFlied vào table
            setTextFlied_tbListCourses();
        } catch (Exception ex) {
            Logger.getLogger(FrmManageScore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Chèn textFlied vào table
    public void setTextFlied_tbListCourses() {
        txtDTB = new JXTextField();
        TableColumn tc = tbListCourses.getColumnModel().getColumn(5);
        TableCellEditor tce = new DefaultCellEditor(txtDTB);
        tc.setCellEditor(tce);
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
        txtNam2 = new org.jdesktop.swingx.JXTextField();
        cboNam1 = new javax.swing.JComboBox();
        lbHoTen10 = new org.jdesktop.swingx.JXLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListCourses = new org.jdesktop.swingx.JXTable();
        ControlPanel1 = new org.jdesktop.swingx.JXPanel();
        btnLoadMH = new org.jdesktop.swingx.JXButton();
        btnLuuLai = new org.jdesktop.swingx.JXButton();
        btnHuyTT = new org.jdesktop.swingx.JXButton();
        btnXuatBD = new org.jdesktop.swingx.JXButton();
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

        lbHoTen8.setText("Năm học:");
        lbHoTen8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNam2.setEditable(false);

        lbHoTen10.setText("-");
        lbHoTen10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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
                .addComponent(cboNam1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHoTen10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNam2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TopLeft1Layout.setVerticalGroup(
            TopLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopLeft1Layout.createSequentialGroup()
                .addGroup(TopLeft1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbHoTen5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHocKi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách môn học"));

        tbListCourses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Môn Học", "MaMHLT", "Tên Môn Học", "MaLoaiMH", "Loại Môn Học", "Số Tín Chỉ", "Điểm Trung Bình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListCourses.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbListCourses.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbListCourses);

        ControlPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều khiển bảng điểm sinh viên"));
        ControlPanel1.setLayout(new org.jdesktop.swingx.HorizontalLayout());

        btnLoadMH.setText("Hiển thị bảng điểm");
        btnLoadMH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadMHActionPerformed(evt);
            }
        });
        ControlPanel1.add(btnLoadMH);

        btnLuuLai.setText("Lưu lại");
        btnLuuLai.setEnabled(false);
        btnLuuLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLaiActionPerformed(evt);
            }
        });
        ControlPanel1.add(btnLuuLai);

        btnHuyTT.setText("Hủy thao tác");
        btnHuyTT.setEnabled(false);
        btnHuyTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTTActionPerformed(evt);
            }
        });
        ControlPanel1.add(btnHuyTT);

        btnXuatBD.setText("Xuất bảng điểm");
        btnXuatBD.setEnabled(false);
        btnXuatBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBDActionPerformed(evt);
            }
        });
        ControlPanel1.add(btnXuatBD);

        javax.swing.GroupLayout FullPanelLayout = new javax.swing.GroupLayout(FullPanel);
        FullPanel.setLayout(FullPanelLayout);
        FullPanelLayout.setHorizontalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopInFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ControlPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        FullPanelLayout.setVerticalGroup(
            FullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FullPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(TopInFull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(ControlPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("BẢNG ĐIỂM SINH VIÊN");
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

    private void txtMssvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMssvKeyPressed
        //neu bam phim enter thi kiem tra mssv
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMssv.setEnabled(false);
            btnSearchStudent.setText("Sinh viên mới");
            CheckStudentAndLoadCourse();
        }
    }//GEN-LAST:event_txtMssvKeyPressed

    // Kiểm tra thông tin sinh viên nhập vào
    private boolean CheckStudentAndLoadCourse() {
        clsStudent_Public stPublic = new clsStudent_Public();
        stPublic.setMssv(txtMssv.getText());
        try {
            ResultSet rs = rcBLL.LoadStudentInfo(stPublic);
            if (rs.next()) {//ma so sinh vien hop le thi load thong tin sinh vien
                txtHoTen.setText(rs.getString(1));
                txtNganh.setText(rs.getString(2));
                txtKhoa.setText(rs.getString(3));

            } else {
                JOptionPane.showMessageDialog(this, "Mã số sinh viên không hợp lệ");
                txtMssv.setText("");
                txtHoTen.setText("");
                txtNganh.setText("");
                txtKhoa.setText("");
                //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
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
            Logger.getLogger(FrmManageScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
            //lam moi tat ca du lieu trong cac bang
            dtm.setRowCount(0);
            // gọi hàm hủy thao tác.
            HuyThaoTac();
        }
        //chuyen text cua button
        if (!btnSearchStudent.getText().equals(label1)) {
            btnSearchStudent.setText(label1);
        } else {
            btnSearchStudent.setText(label2);
        }
    }//GEN-LAST:event_btnSearchStudentActionPerformed
    // cho phép nhập mới thông tin sinh viên
    private void NewStudent() {
        txtMssv.setEnabled(true);
        txtMssv.setText("");
        txtKhoa.setText("");
        txtNganh.setText("");
        txtHoTen.setText("");
        //lam moi tat ca du lieu trong cac bang
        dtm.setRowCount(0);
        //chuyen text cua button
        btnSearchStudent.setText("Tìm kiếm");
    }
    // Load bảng điểm của sinh viên đó(nếu có, hoặc load những môn học mà sinh viên đó đã đăng ký)
    private void btnLoadMHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadMHActionPerformed
        try {
            //duyet qua tat cả các môn, nếu môn nào có chọn thì kiểm tra điều kiện
            // sau khi load thông tin sinh viên, hiển thị danh sách các môn học mà sinh viên đó đã đăng ký
            bdP.setMssv(txtMssv.getText());
            bdP.setNam1(Integer.parseInt(cboNam1.getSelectedItem().toString()));
            bdP.setNam2(Integer.parseInt(txtNam2.getText()));
            bdP.setHocki(Integer.parseInt(cboHocKi.getSelectedItem().toString()));
            ResultSet rsCourses = bdBLL.LoadCoursesRegisted(bdP);
            if (!rsCourses.next()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lại thông tin của sinh viên");     
                //btnLoadMH.setEnabled(false);
            }else{
                do{
                    Vector data_rows = new Vector();
                    data_rows.add(rsCourses.getString(1));
                    data_rows.add(rsCourses.getString(2));
                    data_rows.add(rsCourses.getString(3));
                    data_rows.add(rsCourses.getString(4));
                    data_rows.add(rsCourses.getString(5));
                    data_rows.add(rsCourses.getString(6));
                    dtm.addRow(data_rows);
                }while(rsCourses.next());
                btnLoadMH.setEnabled(false);
                btnLuuLai.setEnabled(true);
                btnHuyTT.setEnabled(true);
                btnXuatBD.setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
        }
    }//GEN-LAST:event_btnLoadMHActionPerformed

    // Lưu bảng điểm xuống CSDL
    private void btnLuuLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLaiActionPerformed
        for(int i = 0; i < tbListCourses.getRowCount(); i ++){
            try {
                bdP.setMaMon(tbListCourses.getValueAt(i, 0).toString());
                bdP.setMssv(txtMssv.getText());
                float a = Float.parseFloat(tbListCourses.getValueAt(i, 5).toString());
                bdP.setDiemTB(a);
                bdP.setNam1(Integer.parseInt(cboNam1.getSelectedItem().toString()));
                bdP.setNam2(Integer.parseInt(txtNam2.getText()));
                bdP.setHocki(Integer.parseInt(cboHocKi.getSelectedItem().toString()));
                
                bdBLL.ScoresInsertUpdate(bdP);
                
            } catch (Exception ex) {
                Logger.getLogger(FrmManageScore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(this, "Thanh cong");
    }//GEN-LAST:event_btnLuuLaiActionPerformed

    // thực hiện hủy các thao tác trước đó
    public void HuyThaoTac(){
        btnLoadMH.setEnabled(true);
        btnLuuLai.setEnabled(false);
        btnXuatBD.setEnabled(false);
        btnHuyTT.setEnabled(false);

        //xoa tat ca cac mon hoc hien tai trong list da dang ki neu co
        if (dtm.getRowCount() > 0) {
            for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
                dtm.removeRow(i);
            }
        }
    }
    // Hủy thao tác
    private void btnHuyTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTTActionPerformed
        // TODO add your handling code here:
        HuyThaoTac();
        NewStudent();//cho phep nhap sinh vien moi
    }//GEN-LAST:event_btnHuyTTActionPerformed

    private void btnXuatBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatBDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel ControlPanel1;
    private org.jdesktop.swingx.JXPanel FullPanel;
    private org.jdesktop.swingx.JXLabel HoTen;
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopInFull;
    private org.jdesktop.swingx.JXPanel TopLeft;
    private org.jdesktop.swingx.JXPanel TopLeft1;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnHuyTT;
    private org.jdesktop.swingx.JXButton btnLoadMH;
    private org.jdesktop.swingx.JXButton btnLuuLai;
    private org.jdesktop.swingx.JXButton btnSearchStudent;
    private org.jdesktop.swingx.JXButton btnXuatBD;
    private javax.swing.JComboBox cboHocKi;
    private javax.swing.JComboBox cboNam1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXLabel lbHoTen10;
    private org.jdesktop.swingx.JXLabel lbHoTen2;
    private org.jdesktop.swingx.JXLabel lbHoTen3;
    private org.jdesktop.swingx.JXLabel lbHoTen5;
    private org.jdesktop.swingx.JXLabel lbHoTen8;
    private org.jdesktop.swingx.JXLabel lbMSSV;
    private org.jdesktop.swingx.JXTable tbListCourses;
    private org.jdesktop.swingx.JXTextField txtHoTen;
    private org.jdesktop.swingx.JXTextField txtKhoa;
    private org.jdesktop.swingx.JXTextField txtMssv;
    private org.jdesktop.swingx.JXTextField txtNam2;
    private org.jdesktop.swingx.JXTextField txtNganh;
    // End of variables declaration//GEN-END:variables
}
