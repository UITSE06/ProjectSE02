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
public class clsStudent_Public {
    private String mssv;
    private String hoTen;
    private Date ngaySinh;
    private int gioiTinh;
    private String huyen;
    private String tinh;
    private String namNhapHoc;
    private String maDoiTuong;
    private String maNganh;
    private FileInputStream hinhdaidien;
    private String tinhTrang;

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
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
     * @return the huyen
     */
    public String getHuyen() {
        return huyen;
    }

    /**
     * @param huyen the huyen to set
     */
    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    /**
     * @return the tinh
     */
    public String getTinh() {
        return tinh;
    }

    /**
     * @param tinh the tinh to set
     */
    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    /**
     * @return the namNhapHoc
     */
    public String getNamNhapHoc() {
        return namNhapHoc;
    }

    /**
     * @param namNhapHoc the namNhapHoc to set
     */
    public void setNamNhapHoc(String namNhapHoc) {
        this.namNhapHoc = namNhapHoc;
    }

    /**
     * @return the maDoiTuong
     */
    public String getMaDoiTuong() {
        return maDoiTuong;
    }

    /**
     * @param maDoiTuong the maDoiTuong to set
     */
    public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    /**
     * @return the maNganh
     */
    public String getMaNganh() {
        return maNganh;
    }

    /**
     * @param maNganh the maNganh to set
     */
    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
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
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param trangThai the tinhTrang to set
     */
    public void setTinhTrang(String trangThai) {
        this.tinhTrang = trangThai;
    }
}
