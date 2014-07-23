/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Report;

import BLL.Item_Cbx;
import BLL.clsFacultyBLL;
import BLL.clsMayjors_BLL;
import BLL.clsRegisterForm_BLL;
import BLL.clsScholastic_BLL;
import BLL.clsStudent_BLL;
import DAL.SQLServerConnector;
import GUI.LineNumberTableRowHeader;
import GUI.frmMain;
import PUBLIC.PhieuDangKyPublic;
import PUBLIC.clsFaculty_Public;
import PUBLIC.clsMayjors_Public;
import PUBLIC.clsStudent_Public;
import com.jaspersoft.jrx.query.PlSqlQueryExecuter;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.*;

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
    private String yearOfApply = "";
    private Integer noSemeter;
    private Integer year1;
    private Integer year2;
    private clsStudent_BLL stBLL = new clsStudent_BLL();
    private clsStudent_Public stPublic = new clsStudent_Public();
    private ArrayList<clsFaculty_Public> listFaculty;
    private DefaultTableModel dtmListStudent;
    private DefaultTableModel dtmListStudentDetail;
    private Item_Cbx itemFaculty;
    private Item_Cbx itemMayjors;
    private Item_Cbx itemYearApply;
    private Item_Cbx itemNoSemeter;
    private Item_Cbx itemYear;
    private Item_Cbx itemStateRegister;
    final DefaultComboBoxModel dfModel = new DefaultComboBoxModel();
    DefaultComboBoxModel modelMayjors;
    DefaultComboBoxModel modelYearApply;
    DefaultComboBoxModel modelCondition;
    DefaultComboBoxModel modelScholatis;
    DefaultComboBoxModel modelSemeter;

    String selectedData = "";
    String getNameStudent = "";
    String getTotal = "";
    private clsScholastic_BLL scholBLL;
    private clsRegisterForm_BLL regFormBLL;
    private PhieuDangKyPublic regFormPublic = new PhieuDangKyPublic();

    frmMain frm = new frmMain();
    SQLServerConnector conn;

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
            this.modelSemeter = new DefaultComboBoxModel(fAddtoArr_fill_cbSemeter().toArray());
            this.modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
            fFillCombobox_cbFaculty();
            fFillDataToTBListStudent();
            fFillCombobox_cbMayjors();
            FillCombobox_cbYearApply();
            fFillCombobox_cbSemeter();
            fFillCombobox_cbScholatic();
            fFillCombobox_cbState();
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

    /**
     * @HungNgoc Date: 10/06/2014
     *
     */
    private ArrayList<Item_Cbx> fAddtoArr_fill_cbScholastic() {
        scholBLL = new clsScholastic_BLL();
        ArrayList<Item_Cbx> cbScholastic = new ArrayList<>();
        try {
            rs = scholBLL.LOAD_SCHOLASTIC();
            cbScholastic.add(new Item_Cbx("1", "Tất cả"));
            while (rs.next()) {

                String yearFirst = "";
                String yearSecond = "";
                yearFirst = (String) rs.getString(1);
                yearSecond = (String) rs.getString(2);
                cbScholastic.add(new Item_Cbx(yearFirst + "-" + yearSecond));
            }
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbScholastic;
    }

    /**
     * @HungNgoc
     *
     */
    private ArrayList<Item_Cbx> fAddtoArr_fill_cbSemeter() {
        scholBLL = new clsScholastic_BLL();
        ArrayList<Item_Cbx> cbSemeterArr = new ArrayList<>();
        try {
            rs = scholBLL.LOAD_SEMETER();
            cbSemeterArr.add(new Item_Cbx("1", "Tất cả"));
            while (rs.next()) {
                cbSemeterArr.add(new Item_Cbx(rs.getString(1)));
            }
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cbSemeterArr;
    }

    private void fGetData() {
        try {
            regFormBLL = new clsRegisterForm_BLL();
            if (idFaculty == "01") {
                idFaculty = "";
                regFormPublic.setIdFaculty(idFaculty);
            } else {
                itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                idFaculty = itemFaculty.getId();
                regFormPublic.setIdFaculty(idFaculty);
            }

            if (cbMayjors.getSelectedIndex() == 0 || idMayjors == "Tất cả") {
                idMayjors = "";
                regFormPublic.setIdMayjors(idMayjors);
            } else {
                itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
                idMayjors = itemMayjors.getId();
                regFormPublic.setIdMayjors(idMayjors);
            }
            if (cbYearApply.getSelectedIndex() == 0 || yearOfApply == "Tất cả") {
                yearOfApply = "";
                regFormPublic.setYearOfApply(yearOfApply);
            } else {
                itemYearApply = (Item_Cbx) cbYearApply.getSelectedItem();
                yearOfApply = itemYearApply.getDescription();
                regFormPublic.setYearOfApply(yearOfApply);
            }
            if (cbSemeter.getSelectedIndex() == 0) {
                noSemeter = null;
                regFormPublic.setNoSemeter(noSemeter);
            } else {
                itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
                noSemeter = Integer.parseInt(itemNoSemeter.getDescription());
                regFormPublic.setNoSemeter(noSemeter);
            }
            if (cbScholastic.getSelectedIndex() == 0) {
                year1 = null;
                year2 = null;
                regFormPublic.setY1(year1);
                regFormPublic.setY2(year2);
            } else {
                itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
                String scholastic = itemYear.getDescription();
                String[] yearArr = scholastic.split("-");
                year1 = Integer.parseInt(yearArr[0]);
                year2 = Integer.parseInt(yearArr[1]);
                regFormPublic.setY1(year1);
                regFormPublic.setY2(year2);
            }
            rs = regFormBLL.LOAD_LIST_STUDENT_ALLPARAMETER(regFormPublic);

            fSetTableListStudent();
            fFillDataToTBListStudent1();
        } catch (Exception ex) {
            throw ex;
        }

    }

    private void fFillCombobox_cbState() {
        selectedData = "";
        getNameStudent = "";
        modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
        cbCoFilter.setModel(modelCondition);
    }

    private void fFillCombobox_cbScholatic() throws Exception {
        modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
        cbScholastic.setModel(modelScholatis);

        fGetData();
        cbScholastic.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedData = "";
                getNameStudent = "";
                modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                cbCoFilter.setModel(modelCondition);
                fGetData();
            }
        });
    }

    private void fFillCombobox_cbSemeter() {
        modelSemeter = new DefaultComboBoxModel(fAddtoArr_fill_cbSemeter().toArray());
        cbSemeter.setModel(modelSemeter);
        modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
        cbScholastic.setModel(modelScholatis);
        fGetData();
        cbSemeter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedData = "";
                getNameStudent = "";
                modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                cbCoFilter.setModel(modelCondition);
                fGetData();
            }
        });
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
            modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
            cbYearApply.setModel(modelYearApply);
            modelSemeter = new DefaultComboBoxModel(fAddtoArr_fill_cbSemeter().toArray());
            cbSemeter.setModel(modelSemeter);
            modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
            cbScholastic.setModel(modelScholatis);
            fGetData();
            cbMayjors.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedData = "";
                    getNameStudent = "";
                    modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                    cbCoFilter.setModel(modelCondition);
                    fGetData();
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
            modelMayjors = new DefaultComboBoxModel(fAddtoArrr_fill_cbMayjors().toArray());
            cbMayjors.setModel(modelMayjors);
            modelYearApply = new DefaultComboBoxModel(fAddtoArr_fill_cbYearApply().toArray());
            cbYearApply.setModel(modelYearApply);
            modelSemeter = new DefaultComboBoxModel(fAddtoArr_fill_cbSemeter().toArray());
            cbSemeter.setModel(modelSemeter);
            modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
            cbScholastic.setModel(modelScholatis);
            fGetData();
            cbFaculty.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        selectedData = "";
                        getNameStudent = "";
                        itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                        idFaculty = itemFaculty.getId();
                        modelMayjors = new DefaultComboBoxModel(fAddtoArrr_fill_cbMayjors().toArray());
                        cbMayjors.setModel(modelMayjors);
                        modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                        cbCoFilter.setModel(modelCondition);

                        fGetData();

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
            modelSemeter = new DefaultComboBoxModel(fAddtoArr_fill_cbSemeter().toArray());
            cbSemeter.setModel(modelSemeter);
            modelScholatis = new DefaultComboBoxModel(fAddtoArr_fill_cbScholastic().toArray());
            cbScholastic.setModel(modelScholatis);
            fGetData();
            cbYearApply.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedData = "";
                    getNameStudent = "";
                    modelCondition = new DefaultComboBoxModel(fAddtoArrr_fill_cbCondition().toArray());
                    cbCoFilter.setModel(modelCondition);
                    fGetData();
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
        String[] titleTableListStudent = {"Mã môn học", "Tên môn học", "Loại môn học", "Số tín chỉ đăng ký"};
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

    private void fFillDataToTBListStudent1() {
        try {

            if (cbSemeter.getSelectedIndex() == 0) {
                while (rs.next()) {
                    Vector data_Rows = new Vector();
                    data_Rows.add(rs.getObject(1));
                    data_Rows.add(rs.getObject(2));
                    data_Rows.add(rs.getObject(3));
                    data_Rows.add(rs.getObject(4));
                    data_Rows.add(rs.getObject(7));
                    if (rs.getObject(8) == null) {
                        data_Rows.add(0);
                    } else {
                        data_Rows.add(rs.getObject(8));
                    }
                    dtmListStudent.addRow(data_Rows);
                }
            } else {
                while (rs.next()) {
                    Vector data_Rows = new Vector();
                    data_Rows.add(rs.getObject(1));
                    data_Rows.add(rs.getObject(2));
                    data_Rows.add(rs.getObject(3));
                    data_Rows.add(rs.getObject(4));
                    data_Rows.add(rs.getObject(8));
                    if (rs.getObject(9) == null) {
                        data_Rows.add(0);
                    } else {
                        data_Rows.add(rs.getObject(9));
                    }
                    dtmListStudent.addRow(data_Rows);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fFillDataToTBListStudentDetail(ResultSet temp) {
        try {
            while (temp.next()) {
                Vector data_Rows = new Vector();
                data_Rows.add(temp.getObject(1));
                data_Rows.add(temp.getObject(2));
                data_Rows.add(temp.getObject(3));
                if ((temp.getObject(4)) == null && (temp.getObject(5)) != null) {
                    data_Rows.add(temp.getObject(5));
                } else if ((temp.getObject(4)) != null && (temp.getObject(5)) == null) {
                    data_Rows.add(temp.getObject(4));
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
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    int selectedRow1 = tb_ListStudent.getSelectedRow();
                    if (selectedRow1 < 0) {
                        return;
                    }
                    selectedData = String.valueOf(tb_ListStudent.getValueAt(selectedRow1, 0));
                    getNameStudent = String.valueOf(tb_ListStudent.getValueAt(selectedRow1, 1));
                    getTotal = String.valueOf(tb_ListStudent.getValueAt(selectedRow1, 5));
                    itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
                    String semeter = itemNoSemeter.getDescription();
                    Integer _semeter = null;
                    if (semeter == "Tất cả") {
                        regFormPublic.setNoSemeter(_semeter);
                    } else {
                        _semeter = Integer.parseInt(semeter);
                        regFormPublic.setNoSemeter(_semeter);
                    }
                    itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
                    String yearscholastic = itemYear.getDescription();
                    Integer _year1 = null;
                    Integer _year2 = null;
                    if (yearscholastic == "Tất cả") {
                        regFormPublic.setY1(_year1);
                        regFormPublic.setY2(_year2);
                    } else {
                        String[] yearArr = yearscholastic.split("-");
                        _year1 = Integer.parseInt(yearArr[0]);
                        _year2 = Integer.parseInt(yearArr[1]);
                        regFormPublic.setY1(_year1);
                        regFormPublic.setY2(_year2);
                    }
                    //JOptionPane.showMessageDialog(tb_ListStudent, selectedData);
                    stPublic.setIdStudent(selectedData);
                    ResultSet rsLoadStudentDT = stBLL.LOA_LIST_STUDENT_DETAIL_ID(stPublic, regFormPublic);

                    do {
                        fSetTableListStudentDetail();
                        fFillDataToTBListStudentDetail(rsLoadStudentDT);
                    } while (rsLoadStudentDT.next());
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
        jXLabel13 = new org.jdesktop.swingx.JXLabel();
        cbSemeter = new org.jdesktop.swingx.JXComboBox();
        jXLabel14 = new org.jdesktop.swingx.JXLabel();
        cbScholastic = new org.jdesktop.swingx.JXComboBox();
        jPanel5 = new javax.swing.JPanel();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        sField = new org.jdesktop.swingx.JXSearchField();
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
        btnPrintFormDetail = new org.jdesktop.swingx.JXButton();

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

        jXLabel13.setText("Học kỳ:");

        jXLabel14.setText("Năm học:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCoFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbYearApply, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(cbMayjors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbFaculty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSemeter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbScholastic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(jXLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSemeter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbScholastic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCoFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
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
        sField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jXPanel1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sField, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sinh viên"));

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
        btnXuatBaoCao.setToolTipText("Xuất báo cáo tình trạng đăng ký môn học với các tùy chọn ở trên.");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });

        btnPrintFormDetail.setText("In phiếu thông tin ĐKMH");
        btnPrintFormDetail.setToolTipText("Xuất phiếu thông tin đăng ký môn học của sinh viên");
        btnPrintFormDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintFormDetailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(559, Short.MAX_VALUE)
                .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPrintFormDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnPrintFormDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnXuatBaoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        try {
            conn = new SQLServerConnector();
            HashMap<String, Object> parameter = new HashMap<String, Object>();
            itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
            itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
            itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
            itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
            String facultyName = itemFaculty.getDescription();
            idFaculty = itemFaculty.getId();
            String mayjorsName = itemMayjors.getDescription();
            String scholastic = itemYear.getDescription();
            String semeter = itemNoSemeter.getDescription();
            itemStateRegister = (Item_Cbx) cbCoFilter.getSelectedItem();
            String _stateRegister = itemStateRegister.getDescription();
            //{call LOAD_LIST_STUDENT_ALLPARAMETER($P{rpIdFaculty},$P{rpIdMayjors},$P{rpYearOfApply},$P{rpNoSemeter},$P{rpYear1},$P{rpYear2})}

            if (idFaculty == "01") {
                String _idFaculty = "";
                parameter.put("rpIdFaculty", String.valueOf(_idFaculty));
            } else {
                itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                String _idFaculty = itemFaculty.getId();
                parameter.put("rpIdFaculty", String.valueOf(_idFaculty));
            }

            if (cbMayjors.getSelectedIndex() == 0 || idMayjors == "Tất cả") {
                String _idMayjors = "";
                parameter.put("rpIdMayjors", String.valueOf(_idMayjors));
            } else {
                itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
                String _idMayjors = itemMayjors.getId();
                parameter.put("rpIdMayjors", String.valueOf(_idMayjors));
            }
            if (cbYearApply.getSelectedIndex() == 0 || yearOfApply == "Tất cả") {
                String _yearOfApply = "";
                parameter.put("rpYearOfApply", String.valueOf(_yearOfApply));
            } else {
                itemYearApply = (Item_Cbx) cbYearApply.getSelectedItem();
                String _yearOfApply = itemYearApply.getDescription();
                parameter.put("rpYearOfApply", String.valueOf(_yearOfApply));
            }
            if (cbSemeter.getSelectedIndex() == 0) {
                Integer _noSemeter = null;
                parameter.put("rpNoSemeter", _noSemeter);
            } else {
                itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
                Integer _noSemeter = Integer.parseInt(itemNoSemeter.getDescription());
                parameter.put("rpNoSemeter", Integer.valueOf(_noSemeter));
            }
            if (cbScholastic.getSelectedIndex() == 0) {
                Integer _year1 = null;
                Integer _year2 = null;
                parameter.put("rpYear1", _year1);
                parameter.put("rpYear2", _year2);
            } else {
                itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
                String mscholastic = itemYear.getDescription();
                String[] yearArr = mscholastic.split("-");
                Integer _year1 = Integer.parseInt(yearArr[0]);
                Integer _year2 = Integer.parseInt(yearArr[1]);
                parameter.put("rpYear1", _year1);
                parameter.put("rpYear2", _year2);
            }
            parameter.put("rpFacultyName", String.valueOf(facultyName));
            parameter.put("rpMayjorsName", String.valueOf(mayjorsName));
            parameter.put("rpScholastic", String.valueOf(scholastic));
            parameter.put("rpStateRegister", String.valueOf(_stateRegister));
            parameter.put("rpSemeterText", String.valueOf(semeter));
            // InputStream input = new FileInputStream(new File("C:\\Users\\Hung\\SkyDrive\\Tai lieu hoc tap\\Dev Project\\Java\\QLGiaoVu_DeMon\\src\\GUI\\Report\\rp_RegisterSubject.jrxml"));
            InputStream input = this.getClass().getClassLoader().getResourceAsStream("GUI/Report/rp_RegisterSubject.jasper");
            //JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);
            //JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint jpr = JasperFillManager.fillReport(jasperReport, parameter, conn.getConnect());
            JasperViewer.viewReport(jpr, false);

        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void btnPrintFormDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintFormDetailActionPerformed
        try {
            if (selectedData == "") {
                JOptionPane.showMessageDialog(tbDetailStudent, "Bạn chưa chọn sinh viên để xuất phiếu.\nVui lòng chọn sinh viên trên bảng Danh sách sinh viên.");
            } else {
                if (JOptionPane.showConfirmDialog(tbDetailStudent, "Bạn có muốn xuất phiếu danh sách đăng ký môn học cho sinh viên?\nMSSV: " + selectedData + "\nHọ tên: " + getNameStudent) == JOptionPane.YES_OPTION) {
                    conn = new SQLServerConnector();
                    String _getNameMayjor = "";
                    stPublic.setIdStudent(selectedData);
                    rs = stBLL.LOAD_NAMEMAYJORS(stPublic);
                    while (rs.next()) {
                        _getNameMayjor = (String) rs.getString("TenNganh");
                    }
                    HashMap<String, Object> parameter = new HashMap<String, Object>();
                    itemFaculty = (Item_Cbx) cbFaculty.getSelectedItem();
                    itemMayjors = (Item_Cbx) cbMayjors.getSelectedItem();
                    itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
                    itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
                    String facultyName = itemFaculty.getDescription();
                    idFaculty = itemFaculty.getId();
                    String mayjorsName = itemMayjors.getDescription();
                    String scholastic = itemYear.getDescription();
                    String semeter = itemNoSemeter.getDescription();
                    Integer _semeter = null;
                    Integer _y1 = null;
                    Integer _y2 = null;
                    if (semeter == "Tất cả") {
                        _semeter = null;
                    } else {
                        _semeter = Integer.parseInt(semeter);
                    }
                    if (scholastic == "Tất cả") {
                        _y1 = null;
                        _y2 = null;
                    } else {
                        String[] yearArr = scholastic.split("-");
                        _y1 = Integer.parseInt(yearArr[0]);
                        _y2 = Integer.parseInt(yearArr[1]);
                    }
                    parameter.put("year_Number", _y1);
                    parameter.put("year2_Number", _y2);
                    parameter.put("semeter_Number", _semeter);
                    parameter.put("facultyName_RP", String.valueOf(facultyName));
                    parameter.put("mayjorsName_RP", String.valueOf(_getNameMayjor));
                    parameter.put("scholastic_RP", String.valueOf(scholastic));
                    parameter.put("semeter_RP", String.valueOf(semeter));
                    parameter.put("rpMSSV", String.valueOf(selectedData));
                    parameter.put("total_RP", String.valueOf(getTotal));
                    parameter.put("nameStudent_RP", String.valueOf(getNameStudent));

                    InputStream input = this.getClass().getClassLoader().getResourceAsStream("GUI/Report/rp_RegisterDetail.jrxml");
                    JasperDesign design = JRXmlLoader.load(input);
                    JasperReport report = JasperCompileManager.compileReport(design);
                    JasperPrint jpr = JasperFillManager.fillReport(report, parameter, conn.getConnect());
                    JasperViewer.viewReport(jpr, false);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintFormDetailActionPerformed

    private void sFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sFieldKeyTyped
        try {
            // TODO add your handling code here:
            String search = sField.getText();
            stPublic.setName(search);
            itemNoSemeter = (Item_Cbx) cbSemeter.getSelectedItem();
            String _noSemeter = itemNoSemeter.getDescription();
            if (_noSemeter == "Tất cả") {
                noSemeter = null;
                regFormPublic.setNoSemeter(noSemeter);
            } else {
                noSemeter = Integer.parseInt(_noSemeter);
                regFormPublic.setNoSemeter(noSemeter);
            }
            if (cbScholastic.getSelectedIndex() == 0) {
                Integer _year1 = null;
                Integer _year2 = null;
                regFormPublic.setY1(_year1);
                regFormPublic.setY2(_year2);
            } else {
                itemYear = (Item_Cbx) cbScholastic.getSelectedItem();
                String mscholastic = itemYear.getDescription();
                String[] yearArr = mscholastic.split("-");
                Integer _year1 = Integer.parseInt(yearArr[0]);
                Integer _year2 = Integer.parseInt(yearArr[1]);
                regFormPublic.setY1(_year1);
                regFormPublic.setY2(_year2);
            }
            rs = regFormBLL.SEARCH_STUDENT(regFormPublic, stPublic);
            do {

                fSetTableListStudent();
                fFillDataToTBListStudent1();
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(rpRegisterSub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sFieldKeyTyped

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
    private org.jdesktop.swingx.JXButton btnPrintFormDetail;
    private org.jdesktop.swingx.JXButton btnXuatBaoCao;
    private org.jdesktop.swingx.JXComboBox cbCoFilter;
    private org.jdesktop.swingx.JXComboBox cbFaculty;
    private org.jdesktop.swingx.JXComboBox cbMayjors;
    private org.jdesktop.swingx.JXComboBox cbScholastic;
    private org.jdesktop.swingx.JXComboBox cbSemeter;
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
    private org.jdesktop.swingx.JXLabel jXLabel13;
    private org.jdesktop.swingx.JXLabel jXLabel14;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel jXLabel9;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXSearchField sField;
    private javax.swing.JScrollPane scrStudentDetail;
    private javax.swing.JScrollPane scrollPaneListStudent;
    private org.jdesktop.swingx.JXTable tbDetailStudent;
    private org.jdesktop.swingx.JXTable tb_ListStudent;
    // End of variables declaration//GEN-END:variables
}
