/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

import java.io.FileInputStream;
import java.sql.Date;

/**
 *
 * @author John
 */
public class clsStaff_Public {
    private String maNV;
    private String tenNV;
    private Date ngaySinh;
    private int gioiTinh;
    private String diaChi;
    private String TenDN;
    private String MatKhau;
    private String maChucVu;
    private FileInputStream hinhdaidien;

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
        this.maNV = maNV;
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
        this.tenNV = tenNV;
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
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the gioiTinh
     */
    public int getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
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
        this.diaChi = diaChi;
    }

    /**
     * @return the TenDN
     */
    public String getTenDN() {
        return TenDN;
    }

    /**
     * @param TenDN the TenDN to set
     */
    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    /**
     * @return the MatKhau
     */
    public String getMatKhau() {
        return MatKhau;
    }

    /**
     * @param MatKhau the MatKhau to set
     */
    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    /**
     * @return the hinhdaidien
     */
    public FileInputStream getHinhdaidien() {
        return hinhdaidien;
    }

    /**
     * @param hinhdaidien the hinhdaidien to set
     */
    public void setHinhdaidien(FileInputStream hinhdaidien) {
        this.hinhdaidien = hinhdaidien;
    }

    /**
     * @return the maChucVu
     */
    public String getMaChucVu() {
        return maChucVu;
    }

    /**
     * @param maChucVu the maChucVu to set
     */
    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }
}
