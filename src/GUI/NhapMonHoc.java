/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.*;
import BLL.Item_Cbx;
import PUBLIC.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class NhapMonHoc extends javax.swing.JFrame {

    /**
     * Creates new form NhapMonHoc
     */
    private ResultSet rs;
    private DefaultTableModel dtmMH;
    private DefaultTableModel dtmN;
    private String maLoaiMH = "";
    private String maKh = "";
    private String maMhLT = "";
    private JComboBox comboBox;

    private final CT_NganhPublic ctNganhP = new CT_NganhPublic();
    private final clsCourses_Public mhP = new clsCourses_Public();
    private final clsMayjors_Public ngP = new clsMayjors_Public();

    private final LoaiMonHocBLL lmhBLL = new LoaiMonHocBLL();
    private final clsMayjors_BLL nBLL = new clsMayjors_BLL();
    private final clsFacultyBLL kBLL = new clsFacultyBLL();
    private final clsCourses_BLL mhBLL = new clsCourses_BLL();
    private final CT_NganhBLL ctnBLL = new CT_NganhBLL();
    private final insertTempCTMNBLL inCTMNBLL = new insertTempCTMNBLL();
    private final insertTempMHBLL inMHLL = new insertTempMHBLL();
    private final createtempMHBLL crMHBLL = new createtempMHBLL();
    private final createtempCTMHBLL crCTMHBLL = new createtempCTMHBLL();

    private FrmListCourses dsMH;

    public NhapMonHoc() {
        initComponents();
        initComponents();
        setTable();
        LoadData();
    }

    public NhapMonHoc(FrmListCourses ds) {
        initComponents();
        setTable();
        LoadData();
        dsMH = ds;
    }

    // setModel cho table môn học tiên quyết và table những ngành học môn ấy
    public void setTable() {
        try {

            String[] titleMH = {"Mã môn học", "Tên môn học"};
            dtmMH = new DefaultTableModel(titleMH, 0);
            tbMhTq.setModel(dtmMH);
            tbMhTq.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            String[] titleN = {"Mã ngành", "Tên ngành", "Học kỳ"};
            dtmN = new DefaultTableModel(titleN, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column >= 2;
                }
            };
            tbNganh.setModel(dtmN);
            tbNganh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void LoadData() {
        try {
            FillComboKhoa();

            ResultSet lMH = lmhBLL.LoadLoaiMH();
            while (lMH.next()) {
                cboLoaiMH.addItem(new Item_Cbx(lMH.getString(1), lMH.getString(2)));
            }
            // Load môn học để thêm vào môn học tiên quyết và môn học lý thuyết
            cboMHTienQuyet.addItem(new Item_Cbx("1", "Tất cả", null));
            ResultSet rsMH = mhBLL.LoadMonHoc();
            while (rsMH.next()) {
                cboMHTienQuyet.addItem(new Item_Cbx(rsMH.getString(1), rsMH.getString(3)));
                cboMHLT.addItem(new Item_Cbx(rsMH.getString(1), rsMH.getString(3)));
            }

            //Lấy ma loai mon hoc dua vao item dang duoc select
            Item_Cbx item = (Item_Cbx) cboLoaiMH.getSelectedItem();
            maLoaiMH = item.getId();
            // Kiểm tra, nếu loại môn học là lý thuyết, thì sẽ không thuộc một môn lý thuyết nào
            if (item.getDescription().equals("Lý thuyết")) {
                cboMHLT.setEnabled(false);
            } else {
                cboMHLT.setEnabled(true);
            }
            cboLoaiMH.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cboLoaiMH.getSelectedItem() != null) {
                        Item_Cbx item = (Item_Cbx) cboLoaiMH.getSelectedItem();
                        maLoaiMH = item.getId();
                        // Kiểm tra, nếu loại môn học là lý thuyết, thì sẽ không thuộc một môn lý thuyết nào
                        if (item.getDescription().equals("Lý thuyết")) {
                            cboMHLT.setEnabled(false);
                        } else {
                            cboMHLT.setEnabled(true);
                        }
                    }
                }
            });
            
            cboMHTienQuyet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean flag = true;
                    Item_Cbx itemMHTQ = (Item_Cbx) cboMHTienQuyet.getSelectedItem();
                    if (itemMHTQ != null && itemMHTQ.getId().toString() != "1") {
                        // Lấy ra đối tượng đang được chọn ở combobox
                        Item_Cbx item = (Item_Cbx) cboMHTienQuyet.getSelectedItem();
                        // Nếu table môn học tiên quyết không có dòng nào.
                        if (tbMhTq.getRowCount() == 0) {
                            Vector row = new Vector();
                            row.add(item.getId());
                            row.add(item.getDescription());
                            dtmMH.addRow(row);
                        } else {//Ngược lại, nếu đã có rồi, thì kiểm tra nó có bị trùng hay k.
                            int rows = tbMhTq.getRowCount();
                            for (int i = 0; i < rows; i++) {
                                if (item.getId().equals(tbMhTq.getValueAt(i, 0))) {
                                    flag = false;
                                }
                            }
                            if (flag) {
                                Vector row = new Vector();
                                row.add(item.getId());
                                row.add(item.getDescription());
                                dtmMH.addRow(row);
                            }
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Load du lieu len comboKhoa---ThanhThai
    public void FillComboKhoa() {
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
                FillComboNganh();
            }
            cboKhoa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cboKhoa.getSelectedItem() != null) {
                        Item_Cbx item = (Item_Cbx) cboKhoa.getSelectedItem();
                        maKh = item.getId();
                        FillComboNganh();
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FillComboNganh() {
        try {
            // Xóa toàn bộ những item của combobox cũ
            cboNganhHoc.removeAllItems();
            // Add giá trị khoảng trắng mặc định cho combobox Môn học lý thuyết
            cboNganhHoc.addItem(new Item_Cbx("1", "Tất cả", null));
            // Load ngành dựa vào mã khoa đang được chọn
            ngP.setIdFaculty(maKh);
            ResultSet x = nBLL.fLoadInfoMayjors_idFaculty(ngP);
            // add nó vào combobox Ngành
            while (x.next()) {
                cboNganhHoc.addItem(new Item_Cbx(x.getString(1), x.getString(2), x.getString(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Xử lý sự kiện cho combobox Ngành
        cboNganhHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                Item_Cbx item = (Item_Cbx) cboNganhHoc.getSelectedItem();
                if (item != null && item.getId() != "1") {
                    if (tbNganh.getRowCount() == 0) {
                        Vector row = new Vector();
                        row.add(item.getId());
                        row.add(item.getDescription());
                        dtmN.addRow(row);
                        bindingCombox(Integer.parseInt(item.getOption()));
                    } else {
                        int rows = tbNganh.getRowCount();
                        for (int i = 0; i < rows; i++) {
                            if (item.getId().equals(tbNganh.getValueAt(i, 0))) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            Vector row = new Vector();
                            row.add(item.getId());
                            row.add(item.getDescription());
                            dtmN.addRow(row);
                            bindingCombox(Integer.parseInt(item.getOption()));
                        }
                    }
                }
            }
        });
    }

    // load du lieu len cho combobox
    public void bindingCombox(int sohk) {
        comboBox = new JComboBox();
        for (int i = 1; i <= sohk; i++) {
            comboBox.addItem(i);
        }
        TableColumn tc = tbNganh.getColumnModel().getColumn(2);
        TableCellEditor tce = new DefaultCellEditor(comboBox);
        tc.setCellEditor(tce);
    }

    public void InsertToTempCTMH() throws Exception {
        // Luu thong tin xuong bang CT_Nganh
        int rowstbN = tbNganh.getRowCount();
        // D.s MaNganh_Ky
        HashMap<String, Integer> maNganh_Ky = new HashMap<>();
        for (int i = 0; i < rowstbN; i++) {
            maNganh_Ky.put(tbNganh.getValueAt(i, 0).toString(), Integer.parseInt(tbNganh.getValueAt(i, 2).toString()));
        }
        //Insert values for table tempCTMH
        //D.s MaHKNganh
        int crtempCTMH = crCTMHBLL.crtempCTMH();
        for (Map.Entry item : maNganh_Ky.entrySet()) {
            try {

                ctNganhP.setMaNganh(item.getKey().toString());
                ctNganhP.setHocKi(Integer.parseInt(item.getValue().toString()));

                int abc = inCTMNBLL.insertTempCTMN(ctNganhP);

            } catch (Exception ex) {
                Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void InsertToTempMH() {
        // Insert values for tempMH1
        try {
            // Tao bang tempMH
            int createTempMH = crMHBLL.createTempMH();
            int rowMHTQ = tbMhTq.getRowCount();
            // Kiểm tra môn học này có môn lý thuyết hay k
            Item_Cbx itemMHLT = (Item_Cbx) cboMHLT.getSelectedItem();
            if (itemMHLT != null && cboMHLT.isEnabled()) {
                mhP.setMaMonLT(itemMHLT.getId());
            } else {
                mhP.setMaMonLT("");
            }
            // Neu danh sach mon hoc rong, k co mon hoc tien quyet
            if (rowMHTQ == 0) {
                mhP.setTenMon(txtTenMonHoc.getText());
                mhP.setSoTiet(Integer.parseInt(txtSoTinChi.getText().toString()));
                mhP.setMaLoaiMH(maLoaiMH);
                mhP.setMaMHTQ("");
                // Thực hiện insert dữ liệu
                int rst = inMHLL.inserttempMH(mhP);
            } else {
                for (int i = 0; i < rowMHTQ; i++) {
                    mhP.setTenMon(txtTenMonHoc.getText());
                    mhP.setSoTiet(Integer.parseInt(txtSoTinChi.getText().toString()));
                    mhP.setMaLoaiMH(maLoaiMH);
                    mhP.setMaMHTQ(tbMhTq.getValueAt(i, 0).toString());
                    // Thực hiện insert dữ liệu
                    int rst = inMHLL.inserttempMH(mhP);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
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
        btnLuu = new org.jdesktop.swingx.JXButton();
        btnLamMoi = new org.jdesktop.swingx.JXButton();
        btnHuy = new org.jdesktop.swingx.JXButton();
        fullPanel = new org.jdesktop.swingx.JXPanel();
        ThongTinMH = new org.jdesktop.swingx.JXPanel();
        lbLoaiMonHoc = new org.jdesktop.swingx.JXLabel();
        lbMoTa = new org.jdesktop.swingx.JXLabel();
        txtTenMonHoc = new org.jdesktop.swingx.JXTextField();
        lbDSMonHoc = new org.jdesktop.swingx.JXLabel();
        cboMHTienQuyet = new javax.swing.JComboBox();
        cboNganhHoc = new javax.swing.JComboBox();
        lbHoTen2 = new org.jdesktop.swingx.JXLabel();
        txtMoTaMH = new javax.swing.JScrollPane();
        jXEditorPane1 = new org.jdesktop.swingx.JXEditorPane();
        lbTenMH = new org.jdesktop.swingx.JXLabel();
        cboLoaiMH = new javax.swing.JComboBox();
        lbLoaiMH = new org.jdesktop.swingx.JXLabel();
        txtSoTinChi = new org.jdesktop.swingx.JXTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMhTq = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNganh = new org.jdesktop.swingx.JXTable();
        cboKhoa = new javax.swing.JComboBox();
        lbHoTen3 = new org.jdesktop.swingx.JXLabel();
        cboMHLT = new javax.swing.JComboBox();
        lbLoaiMonHoc1 = new org.jdesktop.swingx.JXLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("NHẬP MÔN HỌC");
        Title.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
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

        btnLuu.setText("Lưu");
        btnLuu.setName("btnLuu"); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setName("btnLamMoi"); // NOI18N
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setName("btnHuy"); // NOI18N

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ThongTinMH.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin môn học"));

        lbLoaiMonHoc.setText("Loại môn học:");
        lbLoaiMonHoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbMoTa.setText("Mô tả:");
        lbMoTa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbDSMonHoc.setText("Danh sách môn học tiên quyết:");
        lbDSMonHoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbHoTen2.setText("Danh sách các ngành học:");
        lbHoTen2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtMoTaMH.setViewportView(jXEditorPane1);

        lbTenMH.setText("Tên môn học:");
        lbTenMH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboLoaiMH.setName("cboLoaiMH"); // NOI18N

        lbLoaiMH.setText("Số tiết:");
        lbLoaiMH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tbMhTq.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbMhTq.setEditable(false);
        tbMhTq.setSortable(false);
        tbMhTq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMhTqKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbMhTq);

        tbNganh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ma Mon", "Ten Mon", "Hoc Ky"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNganh.setSortable(false);
        tbNganh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNganhKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbNganh);

        cboKhoa.setName("cboLoaiMH"); // NOI18N

        lbHoTen3.setText("Thuộc khoa:");
        lbHoTen3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cboMHLT.setName("cboLoaiMH"); // NOI18N

        lbLoaiMonHoc1.setText("Thuộc môn lý thuyết:");
        lbLoaiMonHoc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout ThongTinMHLayout = new javax.swing.GroupLayout(ThongTinMH);
        ThongTinMH.setLayout(ThongTinMHLayout);
        ThongTinMHLayout.setHorizontalGroup(
            ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinMHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(ThongTinMHLayout.createSequentialGroup()
                            .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ThongTinMHLayout.createSequentialGroup()
                                    .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbLoaiMonHoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbLoaiMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboMHLT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cboLoaiMH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTenMonHoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(68, 68, 68))
                                .addGroup(ThongTinMHLayout.createSequentialGroup()
                                    .addComponent(lbDSMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboNganhHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                    .addComponent(cboKhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(ThongTinMHLayout.createSequentialGroup()
                                        .addComponent(lbLoaiMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSoTinChi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTinMHLayout.createSequentialGroup()
                            .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbMoTa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ThongTinMHLayout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cboMHTienQuyet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addComponent(txtMoTaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        ThongTinMHLayout.setVerticalGroup(
            ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThongTinMHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtTenMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoaiMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoTinChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThongTinMHLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cboLoaiMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLoaiMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThongTinMHLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbHoTen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cboMHLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoaiMonHoc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDSMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbHoTen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cboMHTienQuyet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNganhHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThongTinMHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(txtMoTaMH, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout fullPanelLayout = new javax.swing.GroupLayout(fullPanel);
        fullPanel.setLayout(fullPanelLayout);
        fullPanelLayout.setHorizontalGroup(
            fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullPanelLayout.createSequentialGroup()
                .addComponent(ThongTinMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fullPanelLayout.setVerticalGroup(
            fullPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fullPanelLayout.createSequentialGroup()
                .addComponent(ThongTinMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(fullPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            InsertToTempCTMH();
            InsertToTempMH();
            int ab = mhBLL.InsertMH();
            if (ab < 0) {
                //System.out.println("Thanh cong roi");
                JOptionPane.showMessageDialog(null, "Thanh cong");
                dsMH.Load();
            }
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(NhapMonHoc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tbMhTqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMhTqKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int selectRow = tbMhTq.getSelectedRow();
            if (selectRow != -1) {
                DefaultTableModel model = (DefaultTableModel) tbMhTq.getModel();
                model.removeRow(selectRow);
            }
        }
    }//GEN-LAST:event_tbMhTqKeyPressed

    private void tbNganhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNganhKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            int selectRow = tbNganh.getSelectedRow();
            if (selectRow != -1) {
                DefaultTableModel model = (DefaultTableModel) tbNganh.getModel();
                model.removeRow(selectRow);
            }
        }
    }//GEN-LAST:event_tbNganhKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        if (cboLoaiMH.getSelectedItem() != null) {
            cboLoaiMH.removeAllItems();
        }
        cboMHLT.removeAllItems();
        cboMHTienQuyet.removeAllItems();
        LoadData();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        dsMH.enableComponents(dsMH, true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXPanel ThongTinMH;
    private org.jdesktop.swingx.JXLabel Title;
    private org.jdesktop.swingx.JXPanel TopPanel;
    private org.jdesktop.swingx.JXButton btnHuy;
    private org.jdesktop.swingx.JXButton btnLamMoi;
    private org.jdesktop.swingx.JXButton btnLuu;
    private javax.swing.JComboBox cboKhoa;
    private javax.swing.JComboBox cboLoaiMH;
    private javax.swing.JComboBox cboMHLT;
    private javax.swing.JComboBox cboMHTienQuyet;
    private javax.swing.JComboBox cboNganhHoc;
    private org.jdesktop.swingx.JXPanel fullPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXEditorPane jXEditorPane1;
    private org.jdesktop.swingx.JXLabel lbDSMonHoc;
    private org.jdesktop.swingx.JXLabel lbHoTen2;
    private org.jdesktop.swingx.JXLabel lbHoTen3;
    private org.jdesktop.swingx.JXLabel lbLoaiMH;
    private org.jdesktop.swingx.JXLabel lbLoaiMonHoc;
    private org.jdesktop.swingx.JXLabel lbLoaiMonHoc1;
    private org.jdesktop.swingx.JXLabel lbMoTa;
    private org.jdesktop.swingx.JXLabel lbTenMH;
    private org.jdesktop.swingx.JXTable tbMhTq;
    private org.jdesktop.swingx.JXTable tbNganh;
    private org.jdesktop.swingx.JXPanel topPanel;
    private javax.swing.JScrollPane txtMoTaMH;
    private org.jdesktop.swingx.JXTextField txtSoTinChi;
    private org.jdesktop.swingx.JXTextField txtTenMonHoc;
    // End of variables declaration//GEN-END:variables

}
