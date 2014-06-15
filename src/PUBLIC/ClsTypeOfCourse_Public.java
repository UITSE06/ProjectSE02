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
public class ClsTypeOfCourse_Public {
    private String maLoaiMH;
    private String tenLoai;
    private BigDecimal soTienTrenTC;
    private int soTietTrenTC;

    /**
     * @return the maLoaiMH
     */
    public String getMaLoaiMH() {
        return maLoaiMH;
    }

    /**
     * @param maLoaiMH the maLoaiMH to set
     */
    public void setMaLoaiMH(String maLoaiMH) {
        this.maLoaiMH = maLoaiMH;
    }

    /**
     * @return the tenLoai
     */
    public String getTenLoai() {
        return tenLoai;
    }

    /**
     * @param tenLoai the tenLoai to set
     */
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    /**
     * @return the soTienTrenTC
     */
    public BigDecimal getSoTienTrenTC() {
        return soTienTrenTC;
    }

    /**
     * @param soTienTrenTC the soTienTrenTC to set
     */
    public void setSoTienTrenTC(BigDecimal soTienTrenTC) {
        this.soTienTrenTC = soTienTrenTC;
    }

    /**
     * @return the soTietTrenTC
     */
    public int getSoTietTrenTC() {
        return soTietTrenTC;
    }

    /**
     * @param soTietTrenTC the soTietTrenTC to set
     */
    public void setSoTietTrenTC(int soTietTrenTC) {
        this.soTietTrenTC = soTietTrenTC;
    }
}
