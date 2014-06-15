/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author John
 */
public class ClsRegisterForm_Public {
    private String idRegisterForm;
    private String idStudent;
    private Date dateOfRegister;
    private String idStaff;
    private String idSemesterYears;
    private BigDecimal sumOfMoneyRegister;
    private BigDecimal sumOfMoneyMustPay;
    private BigDecimal sumOfMoneyPaid;

    /**
     * @return the maPhieuDK
     */
    public String getIdRegisterForm() {
        return idRegisterForm;
    }

    /**
     * @param maPhieuDK the maPhieuDK to set
     */
    public void setIdRegisterForm(String maPhieuDK) {
        this.idRegisterForm = maPhieuDK;
    }

    /**
     * @return the mssv
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     * @param mssv the mssv to set
     */
    public void setIdStudent(String mssv) {
        this.idStudent = mssv;
    }

    /**
     * @return the ngayDK
     */
    public Date getDateOfRegister() {
        return dateOfRegister;
    }

    /**
     * @param ngayDK the ngayDK to set
     */
    public void setDateOfRegister(Date ngayDK) {
        this.dateOfRegister = ngayDK;
    }

    /**
     * @return the maNV
     */
    public String getIdStaff() {
        return idStaff;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setIdStaff(String maNV) {
        this.idStaff = maNV;
    }

    /**
     * @return the maHKNH
     */
    public String getIdSemesterYears() {
        return idSemesterYears;
    }

    /**
     * @param maHKNH the maHKNH to set
     */
    public void setIdSemesterYears(String maHKNH) {
        this.idSemesterYears = maHKNH;
    }

    /**
     * @return the TongTienDK
     */
    public BigDecimal getSumOfMoneyRegister() {
        return sumOfMoneyRegister;
    }

    /**
     * @param TongTienDK the TongTienDK to set
     */
    public void setSumOfMoneyRegister(BigDecimal TongTienDK) {
        this.sumOfMoneyRegister = TongTienDK;
    }

    /**
     * @return the TongTienPhaiDong
     */
    public BigDecimal getSumOfMoneyMustPay() {
        return sumOfMoneyMustPay;
    }

    /**
     * @param TongTienPhaiDong the TongTienPhaiDong to set
     */
    public void setSumOfMoneyMustPay(BigDecimal TongTienPhaiDong) {
        this.sumOfMoneyMustPay = TongTienPhaiDong;
    }

    /**
     * @return the TongTienDaDong
     */
    public BigDecimal getSumOfMoneyPaid() {
        return sumOfMoneyPaid;
    }

    /**
     * @param TongTienDaDong the TongTienDaDong to set
     */
    public void setSumOfMoneyPaid(BigDecimal TongTienDaDong) {
        this.sumOfMoneyPaid = TongTienDaDong;
    }
}
