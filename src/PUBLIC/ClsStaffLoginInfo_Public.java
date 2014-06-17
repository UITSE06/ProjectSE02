/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

import java.util.Date;

/**
 *
 * @author John
 */
public class ClsStaffLoginInfo_Public {
    // Thanh Thai
    private static String tenCSDL;
    private static String maNV;
    private static String tenNV;
    private static Date ngaySinh;
    private static String gioiTinh;
    private static String diaChi;
    private static String maPhanQuyen;
    private static String chucVu;

    /**
     * @return the tenCSDL
     */
    public String getTenCSDL() {
        return tenCSDL;
    }

    /**
     * @param aTenCSDL the tenCSDL to set
     */
    public void setTenCSDL(String aTenCSDL) {
        tenCSDL = aTenCSDL;
    }

    /**
     * @return the maPhanQuyen
     */
    public String getMaPhanQuyen() {
        return maPhanQuyen;
    }

    /**
     * @param aMaPhanQuyen the maPhanQuyen to set
     */
    public void setMaPhanQuyen(String aMaPhanQuyen) {
        maPhanQuyen = aMaPhanQuyen;
    }

    /**
     * @return the chucVu
     */
    public String getChucVu() {
        return chucVu;
    }

    /**
     * @param aChucVu the chucVu to set
     */
    public void setChucVu(String aChucVu) {
        chucVu = aChucVu;
    }
    /**
     * @return the maNV
     */
    public String getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(String maNV) {
        ClsStaffLoginInfo_Public.maNV = maNV;
    }

    /**
     * @return the tenNV
     */
    public String getTenNV() {
        return tenNV;
    }

    /**
     * @param tenNV the tenNV to set
     */
    public void setTenNV(String tenNV) {
        ClsStaffLoginInfo_Public.tenNV = tenNV;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        ClsStaffLoginInfo_Public.ngaySinh = ngaySinh;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        ClsStaffLoginInfo_Public.gioiTinh = gioiTinh;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        ClsStaffLoginInfo_Public.diaChi = diaChi;
    }
}
