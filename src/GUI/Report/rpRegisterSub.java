/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Report;

import BLL.Item_Cbx;
import BLL.clsFacultyBLL;
import BLL.clsMayjors_BLL;
import BLL.clsStudent_BLL;
import GUI.LineNumberTableRowHeader;
import PUBLIC.clsFaculty_Public;
import PUBLIC.clsMayjors_Public;
import PUBLIC.clsStudent_Public;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Hung Date: 01/06/2014 Description: Tao form de hien thi thong tin ve
 * tinh trang dang ky hoc phan cua mot sinh vien bat ky. Chỉnh sửa ngày
 * 06/05/2014
 */
public class rpRegisterSub extends javax.swing.JPanel {

    private ResultSet rs;
    private clsFacultyBLL FaBLL = new clsFacultyBLL();
    private clsMayjors_BLL MaBLL = new clsMayjors_BLL();
    private clsMayjors_Public mayjorsPublic = new clsMayjors_Public();
    private clsFaculty_Public faPublic = new clsFaculty_Public();
    private String idFaculty = "";
    private String idMayjors = "";
    private String year = "";
    private clsStudent_BLL stBLL = new clsStudent_BLL();
    private clsStudent_Public stPublic = new clsStudent_Public();
    private ArrayList<clsFaculty_Public> listFaculty;
    private DefaultTableModel dtmListStudent;
    private DefaultTableModel dtmListStudentDetail;
    private Item_Cbx itemFaculty;
    private Item_Cbx itemMayjors;
    private Item_Cbx itemYearApply;
    final DefaultComboBoxModel dfModel = new DefaultComboBoxModel();
    DefaultComboBoxModel modelMayjors;
    DefaultComboBoxModel modelYearApply;
    DefaultComboBoxModel modelCondition;
    String selectedData = "";

    /**
     * Creates new form rpDangKyMonHoc
     */
    public rpRegisterSub() {
        initComponents();
        try {
            Load();
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Load() throws Exception {
        try {
            this.modelMayjors = new DefaultComboBoxModel(fAddtoArrr_fill_cbMayjors().toArray());
            this.modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
            this.modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
            fFillCombobox_cbFaculty();
            fFillDataToTBListStudent();
            fFillCombobox_cbMayjors();
            FillCombobox_cbYearApply();
           eventOnTable();
        } catch (Exception ex) {
            throw ex;
        }

    }

    //Code cua Tinh Vo
    private void fBindingDataToCombobox() {
        listFaculty = FaBLL.fGetInfoToArr();
        DefaultComboBoxModel dfModel = new DefaultComboBoxModel(listFaculty.toArray());
        cbFaculty.setModel(dfModel);
    }

    /**
     * @Hung Date: 06/05/2014 Tạo ra mảng để lưu các giá trị trong combobox để
     * setModel mặc định giá trị ban đầu cho combobox
     */
    private ArrayList<Item_Cbx> fAddtoArrr_fill_cbMayjors() {
        ArrayList<Item_Cbx> arr = new ArrayList<>();
        try {
            mayjorsPublic.setIdFaculty(idFaculty);
            rs = MaBLL.fLoadInfoMayjors_idFaculty(mayjorsPublic);
            arr.add(new Item_Cbx("01", "Tất cả"));
            while (rs.next()) {
                arr.add(new Item_Cbx(rs.getNString(1), rs.getNString(2)));
                cbMayjors.setModel(modelMayjors);
            }

        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    /**
     * @HungNgoc Date: 06/05/2014 tạo mảng cho combobox Năm nhập học
     */
    private ArrayList<Item_Cbx> fAddtoArr_fill_cbYearApply() {
        ArrayList<Item_Cbx> cbYearArr = new ArrayList<>();
        try {
            stBLL = new clsStudent_BLL();
            rs = stBLL.fLoad_Year_Of_StudentApply();
            cbYearArr.add(new Item_Cbx("Tất cả"));
            while (rs.next()) {
                cbYearArr.add(new Item_Cbx(rs.getString(1)));
            }
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbYearArr;
    }

    //
    private ArrayList<Item_Cbx> fAddtoArrr_fill_cbCondition() {
        ArrayList<Item_Cbx> cbCondition = new ArrayList<>();
        cbCondition.add(new Item_Cbx("01", "Tất cả"));
        cbCondition.add(new Item_Cbx("02", "Đã đăng ký tín chỉ"));
        cbCondition.add(new Item_Cbx("03", "Chưa đăng ký tín chỉ"));
        return cbCondition;
    }

    /**
     * @HungNgoc Fill vào combobox của mục Ngành
     */
    private void fFillCombobox_cbMayjors() {
        try {
            modelMayjors = new DefaultComboBoxModel(fAddtoArrr_fill_cbMayjors().toArray());
            cbMayjors.setModel(modelMayjors);

            itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
            itemMayjors.getId();
            cbMayjors.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
                    idMayjors = itemMayjors.getId();
                    stPublic.setIdMayjors(idMayjors);
                    modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
                    cbYearApply.setModel(modelYearApply);
                    /// cbo filter
                    modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                    cbCoFilter.setModel(modelCondition);
                    System.out.println(idMayjors);
                    if (idMayjors != "01") {
                        try {
                            rs = stBLL.LOAD_LISTST_IDMAYJORS(stPublic);
                            fSetTableListStudent();
                            fFillDataToTBListStudent();
                        } catch (Exception ex) {
                            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (idMayjors == "01") {
                        try {
                            idFaculty = itemFaculty.getId();
                            faPublic.setIdFaculty(idFaculty);
                            System.out.println(idFaculty);
                            rs = FaBLL.fLOAD_LISTST_IDFACULTY(faPublic);
                            fSetTableListStudent();
                            fFillDataToTBListStudent();
                        } catch (Exception ex) {
                            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @Hung Load du lieu ten khoa tu store procudure vao cbFaculty, Ham viet
     * lai
     */
    private void fFillCombobox_cbFaculty() {
        try {
            rs = FaBLL.fLoad_NameOfFaculty();
            while (rs.next()) {
                dfModel.addElement(new Item_Cbx(rs.getNString(1), rs.getNString(2)));
                cbFaculty.setModel(dfModel);
            }
            cbFaculty.addItem(new Item_Cbx("01", "Tất cả"));
            itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
            idFaculty = itemFaculty.getId();
            faPublic.setIdFaculty(idFaculty);
            rs = FaBLL.fLOAD_LISTST_IDFACULTY(faPublic);
            fSetTableListStudent();
            fFillDataToTBListStudent();
            System.out.println(idFaculty);
            cbFaculty.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                        idFaculty = itemFaculty.getId();
                        faPublic.setIdFaculty(idFaculty);
                        rs = FaBLL.fLOAD_LISTST_IDFACULTY(faPublic);
                        fSetTableListStudent();
                        fFillDataToTBListStudent();
                        modelMayjors = new DefaultComboBoxModel(fAddtoArrr_fill_cbMayjors().toArray());
                        //cbMayjors.setModel(modelMayjors);
                        modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
                        cbYearApply.setModel(modelYearApply);
                        /// cbo filter
                        modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                        cbCoFilter.setModel(modelCondition);
                        fFillCombobox_cbMayjors();
                        if (idFaculty == "01") {

                            rs = stBLL.fLOAD_LISTSTUDENT_RP();
                            fFillDataToTBListStudent();
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Load du lieu vao combobox cua Nam Nhap Hoc
     */
    private void FillCombobox_cbYearApply() {
        try {
            modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
            cbYearApply.setModel(modelYearApply);
            // stPublic.setIdMayjors(idMayjors);
            itemYearApply = (Item_Cbx) cbYearApply.getSelectedItem();
            year = itemYearApply.getDescription();
            cbYearApply.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    itemYearApply = (Item_Cbx) cbYearApply.getSelectedItem();
                    year = itemYearApply.getDescription();
                    /// cbo filter
                    modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                    cbCoFilter.setModel(modelCondition);

                    if (year == "Tất cả") {
                        itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                        idFaculty = itemFaculty.getId();
                        faPublic.setIdFaculty(idFaculty);
                        try {
                            rs = FaBLL.fLOAD_LISTST_IDFACULTY(faPublic);
                        } catch (Exception ex) {
                            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        fSetTableListStudent();
                        fFillDataToTBListStudent();
                    } else {
                        try {
                            stPublic.setYearOfApply(year.trim());
                            if (idMayjors == "01") {
                                itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                                idFaculty = itemFaculty.getId();
                                stPublic.setIdFaculty(idFaculty);
                                rs = stBLL.LOAD_LISTST_IDFACULTY_YEAR(stPublic);
                                fSetTableListStudent();
                                fFillDataToTBListStudent();
                            } else {
                                itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
                                idMayjors = itemMayjors.getId();
                                rs = stBLL.LOAD_LISTST_IDMAJORS_YEAR(stPublic);
                                fSetTableListStudent();
                                fFillDataToTBListStudent();

                            }
                        } catch (Exception ex) {
                            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fSetTableListStudent() {

        String[] titleTableListStudent = {"MSSV", "Họ và Tên", "Khóa", "Khoa", "Ngành Học", "Số Tín Chỉ"};
        LineNumberTableRowHeader lineRow = new LineNumberTableRowHeader(scrollPaneListStudent, tb_ListStudent);
        lineRow.setBackground(new Color(240, 240, 240));
        scrollPaneListStudent.setRowHeaderView(lineRow);
        dtmListStudent = new DefaultTableModel(titleTableListStudent, 0);
        tb_ListStudent.setModel(dtmListStudent);
        tb_ListStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }
    
     private void fSetTableListStudentDetail() {

        String[] titleTableListStudent = {"Mã môn học", "Tên môn học","Loại môn học", "Số tín chỉ đăng ký"};
        LineNumberTableRowHeader lineRow = new LineNumberTableRowHeader(scrollPaneListStudent, tbDetailStudent);
        lineRow.setBackground(new Color(240, 240, 240));
        scrStudentDetail.setRowHeaderView(lineRow);
        dtmListStudentDetail = new DefaultTableModel(titleTableListStudent, 0);
        tbDetailStudent.setModel(dtmListStudentDetail);
        tbDetailStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /**
     * @Hung Hàm dùng chung để fill dữ liệu từ Resultset lên JXTable Khi nào cần
     * fill chỉ cần gọi đến không cần tạo mới
     */
    private void fFillDataToTBListStudent() {
        try {

            while (rs.next()) {
                Vector data_Rows = new Vector();
                data_Rows.add(rs.getObject(1));
                data_Rows.add(rs.getObject(2));
                data_Rows.add(rs.getObject(3));
                data_Rows.add(rs.getObject(4));
                data_Rows.add(rs.getObject(5));
                if (rs.getObject(6) == null) {
                    data_Rows.add(0);
                } else {
                    data_Rows.add(rs.getObject(6));
                }
                dtmListStudent.addRow(data_Rows);
            }

        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     private void fFillDataToTBListStudentDetail() {
        try {
            while (rs.next()) {
                Vector data_Rows = new Vector();
                data_Rows.add(rs.getObject(1));
                data_Rows.add(rs.getObject(2));
                data_Rows.add(rs.getObject(3));
                if((rs.getObject(4))==null && (rs.getObject(5))!=null ){
                    data_Rows.add(rs.getObject(5));
                }else if((rs.getObject(4))!=null && (rs.getObject(5))==null ){
                     data_Rows.add(rs.getObject(4));
                }
                dtmListStudentDetail.addRow(data_Rows);
            }

        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void eventOnTable() {
        ListSelectionModel cellSelectionModel = tb_ListStudent.getSelectionModel();
        // cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                try {
                    int selectedRow1 = tb_ListStudent.getSelectedRow();
                    if(selectedRow1 < 0) return;
                    selectedData = String.valueOf(tb_ListStudent.getValueAt(selectedRow1, 0));
                    //JOptionPane.showMessageDialog(tb_ListStudent, selectedData);
                    stPublic.setIdStudent(selectedData);
                    rs = stBLL.LOA_LIST_STUDENT_DETAIL_ID(stPublic);
                    if (rs.next() == false) {
                        JOptionPane.showMessageDialog(tb_ListStudent, "Sinh viên chưa đăng ký môn học");
                    }else{
                    fSetTableListStudentDetail();
                    fFillDataToTBListStudentDetail();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        cbMayjors = new org.jdesktop.swingx.JXComboBox();
        cbFaculty = new org.jdesktop.swingx.JXComboBox();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        cbYearApply = new org.jdesktop.swingx.JXComboBox();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();
        cbCoFilter = new org.jdesktop.swingx.JXComboBox();
        jXLabel7 = new org.jdesktop.swingx.JXLabel();
        jXLabel9 = new org.jdesktop.swingx.JXLabel();
        jXLabel10 = new org.jdesktop.swingx.JXLabel();
        jXLabel11 = new org.jdesktop.swingx.JXLabel();
        jPanel5 = new javax.swing.JPanel();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        sField = new org.jdesktop.swingx.JXSearchField();
        jXLabel8 = new org.jdesktop.swingx.JXLabel();
        cbColumnSearch = new org.jdesktop.swingx.JXComboBox();
        jPanel7 = new javax.swing.JPanel();
        scrollPaneListStudent = new javax.swing.JScrollPane();
        tb_ListStudent = new org.jdesktop.swingx.JXTable(){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                if (this.getValueAt(row, 5).toString().equals("0")){
                    c.setBackground(Color.red);
                }
                else{
                    c.setBackground(Color.green);
                }
                return c;
            }
        };
        jPanel8 = new javax.swing.JPanel();
        scrStudentDetail = new javax.swing.JScrollPane();
        tbDetailStudent = new org.jdesktop.swingx.JXTable();
        jPanel3 = new javax.swing.JPanel();
        btnXuatBaoCao = new org.jdesktop.swingx.JXButton();
        btnHuy = new org.jdesktop.swingx.JXButton();

        setLayout(new java.awt.BorderLayout());

        jXLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jXLabel1.setText("TÌNH TRẠNG ĐĂNG KÝ MÔN HỌC");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(jXLabel1);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Điều kiện chọn"));

        jXLabel3.setText("Khoa:");

        jXLabel4.setText("Ngành:");

        jXLabel5.setText("Năm nhập học:");

        jXLabel6.setText("Điều kiện lọc:");

        cbCoFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "Đã đăng ký tín chỉ", "Chưa đăng ký tín chỉ" }));
        cbCoFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCoFilterItemStateChanged(evt);
            }
        });

        jXLabel7.setText("Sinh viên đã đăng ký môn học");

        jXLabel9.setBackground(new java.awt.Color(255, 0, 51));
        jXLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jXLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/statusRed.png"))); // NOI18N

        jXLabel10.setBackground(new java.awt.Color(255, 0, 51));
        jXLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jXLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/statusGreen.png"))); // NOI18N

        jXLabel11.setText("Sinh viên chưa đăng ký môn học");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jXLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCoFilter, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(cbYearApply, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(cbMayjors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbFaculty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMayjors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbYearApply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCoFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel5.setPreferredSize(new java.awt.Dimension(150, 331));

        jXPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jXLabel2.setText("Giá trị tìm kiếm:");

        sField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sFieldActionPerformed(evt);
            }
        });

        jXLabel8.setText("Cột tìm kiếm:");

        cbColumnSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả", "MSSV", "Họ Tên" }));

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbColumnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbColumnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sinh viên"));

        tb_ListStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "MSSV", "Họ và tên", "Khóa", "Khoa", "Ngành", "Số tín chỉ đăng ký"
            }
        ));
        tb_ListStudent.setShowGrid(true);
        scrollPaneListStudent.setViewportView(tb_ListStudent);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneListStudent)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneListStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết sinh viên"+idFaculty));

        tbDetailStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã môn học", "Tên môn học", "Số tín chỉ đăng ký"
            }
        ));
        scrStudentDetail.setViewportView(tbDetailStudent);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrStudentDetail)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrStudentDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));

        btnXuatBaoCao.setText("Xuất báo cáo");

        btnHuy.setText("Hủy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(651, Short.MAX_VALUE)
                .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(jPanel3, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void sFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sFieldActionPerformed

    private void cbCoFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCoFilterItemStateChanged
        // TODO add your handling code here:
        if (cbCoFilter.getSelectedItem().toString().trim() == "Tất cả") {
            tb_ListStudent.setModel(dtmListStudent);
        } else {
            if (cbCoFilter.getSelectedItem().toString().trim() == "Đã đăng ký tín chỉ") {
                ReloadDataTable(dtmListStudent, true);
            } else {
                ReloadDataTable(dtmListStudent, false);
            }
        }
    }//GEN-LAST:event_cbCoFilterItemStateChanged

    public void ReloadDataTable(DefaultTableModel old, boolean cd) {
        String[] titleTableListStudent = {"MSSV", "Họ và Tên", "Khóa", "Khoa", "Ngành Học", "Số Tín Chỉ"};
        DefaultTableModel cd1 = new DefaultTableModel(titleTableListStudent, 0);
        DefaultTableModel cd2 = new DefaultTableModel(titleTableListStudent, 0);

        for (int i = 0; i < old.getRowCount(); i++) {
            Vector rows = new Vector();
            rows = (Vector) old.getDataVector().elementAt(i);
            if (Integer.parseInt(rows.get(5).toString()) > 0) {
                cd1.addRow(rows);
            } else {
                cd2.addRow(rows);
            }
        }
        if (cd) {
            tb_ListStudent.setModel(cd1);
        } else {
            tb_ListStudent.setModel(cd2);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton btnHuy;
    private org.jdesktop.swingx.JXButton btnXuatBaoCao;
    private org.jdesktop.swingx.JXComboBox cbCoFilter;
    private org.jdesktop.swingx.JXComboBox cbColumnSearch;
    private org.jdesktop.swingx.JXComboBox cbFaculty;
    private org.jdesktop.swingx.JXComboBox cbMayjors;
    private org.jdesktop.swingx.JXComboBox cbYearApply;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel10;
    private org.jdesktop.swingx.JXLabel jXLabel11;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel jXLabel8;
    private org.jdesktop.swingx.JXLabel jXLabel9;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXSearchField sField;
    private javax.swing.JScrollPane scrStudentDetail;
    private javax.swing.JScrollPane scrollPaneListStudent;
    private org.jdesktop.swingx.JXTable tbDetailStudent;
    private org.jdesktop.swingx.JXTable tb_ListStudent;
    // End of variables declaration//GEN-END:variables
}
