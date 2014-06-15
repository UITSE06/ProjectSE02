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
public class ClsPaymentFee_Public {
    private String maPhieuThu;
    private String maNhanVien;
    private String maPhieuDK;
    private Date ngayThu;
    private BigDecimal soTienThu;
    private BigDecimal tienConNo;
    private BigDecimal soTienDuocGiam;

    /**
     * @return the maPhieuThu
     */
    public String getMaPhieuThu() {
        return maPhieuThu;
    }

    /**
     * @param maPhieuThu the maPhieuThu to set
     */
    public void setMaPhieuThu(String maPhieuThu) {
        this.maPhieuThu = maPhieuThu;
    }

    /**
     * @return the maNhanVien
     */
    public String getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the maPhieuDK
     */
    public String getMaPhieuDK() {
        return maPhieuDK;
    }

    /**
     * @param maPhieuDK the maPhieuDK to set
     */
    public void setMaPhieuDK(String maPhieuDK) {
        this.maPhieuDK = maPhieuDK;
    }

    /**
     * @return the tongTienHocPhi
     */
    public BigDecimal getSoTienThu() {
        return soTienThu;
    }

    /**
     * @param tongTienHocPhi the tongTienHocPhi to set
     */
    public void setSoTienThu(BigDecimal tongTienHocPhi) {
        this.soTienThu = tongTienHocPhi;
    }

    /**
     * @return the tienConNo
     */
    public BigDecimal getTienConNo() {
        return tienConNo;
    }

    /**
     * @param tienConNo the tienConNo to set
     */
    public void setTienConNo(BigDecimal tienConNo) {
        this.tienConNo = tienConNo;
    }

    /**
     * @return the soTienDuocGiam
     */
    public BigDecimal getSoTienDuocGiam() {
        return soTienDuocGiam;
    }

    /**
     * @param soTienDuocGiam the soTienDuocGiam to set
     */
    public void setSoTienDuocGiam(BigDecimal soTienDuocGiam) {
        this.soTienDuocGiam = soTienDuocGiam;
    }

    /**
     * @return the ngayThu
     */
    public Date getNgayThu() {
        return ngayThu;
    }

    /**
     * @param ngayThu the ngayThu to set
     */
    public void setNgayThu(Date ngayThu) {
        this.ngayThu = ngayThu;
    }
}
