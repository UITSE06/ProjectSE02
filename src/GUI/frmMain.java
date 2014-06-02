/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Report.rpRegisterSub;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Hung
 */
public class frmMain extends javax.swing.JFrame {

    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
//        try {
//             
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Metal".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        setClocks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXFrame1 = new org.jdesktop.swingx.JXFrame();
        st_Info = new org.jdesktop.swingx.JXStatusBar();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        lb_Clock = new org.jdesktop.swingx.JXLabel();
        lb_Date = new org.jdesktop.swingx.JXLabel();
        jXButton1 = new org.jdesktop.swingx.JXButton();
        jXButton2 = new org.jdesktop.swingx.JXButton();
        jXButton3 = new org.jdesktop.swingx.JXButton();
        jXButton4 = new org.jdesktop.swingx.JXButton();
        jXButton5 = new org.jdesktop.swingx.JXButton();
        jXButton6 = new org.jdesktop.swingx.JXButton();
        jXButton7 = new org.jdesktop.swingx.JXButton();
        jXButton8 = new org.jdesktop.swingx.JXButton();
        jXButton9 = new org.jdesktop.swingx.JXButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabbedPaneMain = new GUI.ClosableTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        mnTinhTrangDangKy = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        mnInfoStudent = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jXFrame1Layout = new javax.swing.GroupLayout(jXFrame1.getContentPane());
        jXFrame1.getContentPane().setLayout(jXFrame1Layout);
        jXFrame1Layout.setHorizontalGroup(
            jXFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jXFrame1Layout.setVerticalGroup(
            jXFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM QUẢN LÝ ĐĂNG KÝ MÔN HỌC VÀ THU HỌC PHÍ");

        st_Info.setBackground(java.awt.SystemColor.activeCaption);

        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Misc-Web-Database-icon.png"))); // NOI18N
        jXLabel1.setText("Database: ");
        st_Info.add(jXLabel1);

        jXLabel3.setText("QUANLYSINHVIEN");
        st_Info.add(jXLabel3);

        jXLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/employee.png"))); // NOI18N
        jXLabel4.setText("Nhân viên:");
        jXLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        st_Info.add(jXLabel4);

        jXLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jXLabel5.setText("Huỳnh Ngọc Hưng");
        jXLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        st_Info.add(jXLabel5);

        jXPanel1.setBackground(java.awt.SystemColor.activeCaption);

        lb_Clock.setForeground(new java.awt.Color(255, 255, 255));
        lb_Clock.setText("00:00:00");
        lb_Clock.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        lb_Date.setForeground(new java.awt.Color(255, 255, 255));
        lb_Date.setText("Date");
        lb_Date.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jXButton1.setBackground(new java.awt.Color(255, 255, 255));
        jXButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dk.png"))); // NOI18N

        jXButton2.setBackground(new java.awt.Color(255, 255, 255));
        jXButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/listicon.png"))); // NOI18N

        jXButton3.setBackground(new java.awt.Color(255, 255, 255));
        jXButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/money.png"))); // NOI18N

        jXButton4.setBackground(new java.awt.Color(255, 255, 255));
        jXButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/personal-information-icon.png"))); // NOI18N

        jXButton5.setBackground(new java.awt.Color(255, 255, 255));
        jXButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search-Results.png"))); // NOI18N

        jXButton6.setBackground(new java.awt.Color(255, 255, 255));
        jXButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Backupicon.png"))); // NOI18N

        jXButton7.setBackground(new java.awt.Color(255, 255, 255));
        jXButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backup.png"))); // NOI18N

        jXButton8.setBackground(new java.awt.Color(255, 255, 255));
        jXButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Register-icon.png"))); // NOI18N

        jXButton9.setBackground(new java.awt.Color(255, 255, 255));
        jXButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout-icon.png"))); // NOI18N

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_Date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb_Clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_Clock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(TabbedPaneMain);

        jMenuBar1.setFont(jMenuBar1.getFont().deriveFont(jMenuBar1.getFont().getStyle() | java.awt.Font.BOLD, 13));

        jMenu1.setForeground(new java.awt.Color(255, 0, 0));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/System-icon.png"))); // NOI18N
        jMenu1.setText("Hệ Thống");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem1.setText("Đăng nhập");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Sao lưu CSDL");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/backup.png"))); // NOI18N
        jMenuItem4.setText("Phục hồi CSDL");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        jMenuItem5.setText("Đóng");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Giáo Vụ");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem6.setText("Danh sách môn học");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        mnTinhTrangDangKy.setText("Tình trạng đăng ký môn học");
        mnTinhTrangDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnTinhTrangDangKyActionPerformed(evt);
            }
        });
        jMenu2.add(mnTinhTrangDangKy);

        jMenuItem18.setText("Danh sách ngành");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem19.setText("Danh sách khoa");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem19);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Kế Hoạch - Tài Chính");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem14.setText("jMenuItem14");
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Nhân Viên");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenu8.setText("jMenu8");
        jMenu3.add(jMenu8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Sinh Viên");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        mnInfoStudent.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        mnInfoStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/personal-information-icon.png"))); // NOI18N
        mnInfoStudent.setText("Thông tin sinh viên");
        mnInfoStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnInfoStudentActionPerformed(evt);
            }
        });
        jMenu4.add(mnInfoStudent);

        jMenuItem8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search-Results.png"))); // NOI18N
        jMenuItem8.setText("Kết quả học tập");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dk.png"))); // NOI18N
        jMenuItem7.setText("Đăng ký môn học");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/money.png"))); // NOI18N
        jMenuItem9.setText("Thông tin học phí");
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Quản Trị");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem10.setText("Cấu hình hệ thống");
        jMenu6.add(jMenuItem10);

        jMenuItem11.setText("Quy định");
        jMenu6.add(jMenuItem11);

        jMenu9.setText("Giao diện");

        jMenuItem15.setText("Mặc định");
        jMenu9.add(jMenuItem15);

        jMenuItem16.setText("Metal");
        jMenu9.add(jMenuItem16);

        jMenuItem17.setText("Nimbus");
        jMenu9.add(jMenuItem17);

        jMenu6.add(jMenu9);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Trợ Giúp");
        jMenu7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jMenuItem12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem12.setText("Thông tin phần mềm");
        jMenu7.add(jMenuItem12);

        jMenuItem13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jMenuItem13.setText("Hướng dẫn sử dụng");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem13);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(st_Info, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(st_Info, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jXPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnInfoStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnInfoStudentActionPerformed
        try {
            ThongTinSV ttSinhVien = new ThongTinSV();
            int index = 0;
            if (TabbedPaneMain.getTabCount() == 0) {
                TabbedPaneMain.addTab("Thông tin sinh viên   ", null, ttSinhVien, "thongtinsv");
            } else {
                index = TabbedPaneMain.indexOfTab("Thông tin sinh viên   ");
                if (index < 0) {
                    TabbedPaneMain.addTab("Thông tin sinh viên   ", null, ttSinhVien, "thongtinsv");
                    TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Thông tin sinh viên   "));
                } else {
                    TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Thông tin sinh viên   "));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mnInfoStudentActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        ThongTinLop ttLopHoc = new ThongTinLop();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Thông tin lớp học   ", null, ttLopHoc, "thongtinLH");
        } else {
            int index = TabbedPaneMain.indexOfTab("Thông tin lớp học   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Thông tin lớp học   ", null, ttLopHoc, "thongtinLH");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Thông tin lớp học   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Thông tin lớp học   "));
            }
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        DangKiMonHoc2 dkMonHoc = new DangKiMonHoc2();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Đăng kí môn học   ", null, dkMonHoc, "dkMonHoc");
        } else {
            int index = TabbedPaneMain.indexOfTab("Đăng kí môn học   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Đăng kí môn học   ", null, dkMonHoc, "dkMonHoc");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Đăng kí môn học   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Đăng kí môn học   "));
            }
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FrmListCourses dsMonHoc = new FrmListCourses();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Danh sách môn học   ", null, dsMonHoc, "dsMonHoc");
        } else {
            int index = TabbedPaneMain.indexOfTab("Danh sách môn học   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Danh sách môn học   ", null, dsMonHoc, "dsMonHoc");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách môn học   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách môn học   "));
            }
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void mnTinhTrangDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnTinhTrangDangKyActionPerformed
        // TODO add your handling code here:
        rpRegisterSub rpDKM = new rpRegisterSub();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Tình trạng đăng ký môn học   ", null, rpDKM, "rpDangKyMonHoc");
        } else {
            int index = TabbedPaneMain.indexOfTab("Tình trạng đăng ký môn học   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Tình trạng đăng ký môn học   ", null, rpDKM, "rpDangKyMonHoc");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Tình trạng đăng ký môn học   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Tình trạng đăng ký môn học   "));
            }
        }
    }//GEN-LAST:event_mnTinhTrangDangKyActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        FrmListMayjors listMayjor = new FrmListMayjors();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Danh sách ngành   ", null, listMayjor, "listMayjor");
        } else {
            int index = TabbedPaneMain.indexOfTab("Danh sách ngành   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Danh sách ngành   ", null, listMayjor, "listMayjor");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách ngành   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách ngành   "));
            }
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        FrmListFaculty listFac = new FrmListFaculty();
        if (TabbedPaneMain.getTabCount() == 0) {
            TabbedPaneMain.addTab("Danh sách khoa   ", null, listFac, "listFac");
        } else {
            int index = TabbedPaneMain.indexOfTab("Danh sách khoa   ");
            if (index < 0) {
                TabbedPaneMain.addTab("Danh sách khoa   ", null, listFac, "listFac");
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách khoa   "));
            } else {
                TabbedPaneMain.setSelectedIndex(TabbedPaneMain.indexOfTab("Danh sách khoa   "));
            }
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void setClocks() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    SimpleDateFormat sdfDate = new SimpleDateFormat("E, dd/MM/yyyy");
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    lb_Clock.setText(sdf.format(cal.getTime()));
                    lb_Date.setText(sdfDate.format(cal.getTime()));
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        };
        clock.start();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
             
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmMain frM = new frmMain();
                frM.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-40);
                frM.setVisible(true);
            }
        });

    }

    //
    // private ClosableTabbedPane TabbePaneMain1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.ClosableTabbedPane TabbedPaneMain;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXButton jXButton1;
    private org.jdesktop.swingx.JXButton jXButton2;
    private org.jdesktop.swingx.JXButton jXButton3;
    private org.jdesktop.swingx.JXButton jXButton4;
    private org.jdesktop.swingx.JXButton jXButton5;
    private org.jdesktop.swingx.JXButton jXButton6;
    private org.jdesktop.swingx.JXButton jXButton7;
    private org.jdesktop.swingx.JXButton jXButton8;
    private org.jdesktop.swingx.JXButton jXButton9;
    private org.jdesktop.swingx.JXFrame jXFrame1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXLabel lb_Clock;
    private org.jdesktop.swingx.JXLabel lb_Date;
    private javax.swing.JMenuItem mnInfoStudent;
    private javax.swing.JMenuItem mnTinhTrangDangKy;
    private org.jdesktop.swingx.JXStatusBar st_Info;
    // End of variables declaration//GEN-END:variables
}
