/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

import java.math.BigDecimal;

/**
 *
 * @author John
 */
public class PhieuThuPublic {
    private String maPhieuThu;
    private String maNhanVien;
    private String maPhieuDK;
    private BigDecimal tongTienHocPhi;
    private BigDecimal tienConNo;

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
    public BigDecimal getTongTienHocPhi() {
        return tongTienHocPhi;
    }

    /**
     * @param tongTienHocPhi the tongTienHocPhi to set
     */
    public void setTongTienHocPhi(BigDecimal tongTienHocPhi) {
        this.tongTienHocPhi = tongTienHocPhi;
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
}
