/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author John
 */
public class CT_PhieuThuPublic {
    private String maCTPhieuThu;
    private String maPhieuThu;
    private BigDecimal soTienThu;
    private Date ngayThu;

    /**
     * @return the maCTPhieuThu
     */
    public String getMaCTPhieuThu() {
        return maCTPhieuThu;
    }

    /**
     * @param maCTPhieuThu the maCTPhieuThu to set
     */
    public void setMaCTPhieuThu(String maCTPhieuThu) {
        this.maCTPhieuThu = maCTPhieuThu;
    }

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
     * @return the soTienThu
     */
    public BigDecimal getSoTienThu() {
        return soTienThu;
    }

    /**
     * @param soTienThu the soTienThu to set
     */
    public void setSoTienThu(BigDecimal soTienThu) {
        this.soTienThu = soTienThu;
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
