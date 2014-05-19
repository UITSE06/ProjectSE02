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
public class CT_BaoCaoHP_Public {
    private String mssv;
    private String maBaoCaoHP;
    private BigDecimal tienHPConNo;
    private int tinhTrangCamThi;

    /**
     * @return the mssv
     */
    public String getMssv() {
        return mssv;
    }

    /**
     * @param mssv the mssv to set
     */
    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    /**
     * @return the maBaoCaoHP
     */
    public String getMaBaoCaoHP() {
        return maBaoCaoHP;
    }

    /**
     * @param maBaoCaoHP the maBaoCaoHP to set
     */
    public void setMaBaoCaoHP(String maBaoCaoHP) {
        this.maBaoCaoHP = maBaoCaoHP;
    }

    /**
     * @return the tienHPConNo
     */
    public BigDecimal getTienHPConNo() {
        return tienHPConNo;
    }

    /**
     * @param tienHPConNo the tienHPConNo to set
     */
    public void setTienHPConNo(BigDecimal tienHPConNo) {
        this.tienHPConNo = tienHPConNo;
    }

    /**
     * @return the tinhTrangCamThi
     */
    public int getTinhTrangCamThi() {
        return tinhTrangCamThi;
    }

    /**
     * @param tinhTrangCamThi the tinhTrangCamThi to set
     */
    public void setTinhTrangCamThi(int tinhTrangCamThi) {
        this.tinhTrangCamThi = tinhTrangCamThi;
    }
}
