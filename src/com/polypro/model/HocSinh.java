/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.model;

/**
 *
 * @author Admin
 */
public class HocSinh {
    private String MaHS;
    private String Hoten;
    private String Diem;
    private boolean GioiTinh;
    
    
    @Override
    public String toString(){
        return this.Hoten;
    }

    public String getMaHS() {
        return MaHS;
    }

    public void setMaHS(String MaHS) {
        this.MaHS = MaHS;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getDiem() {
        return Diem;
    }

    public void setDiem(String Diem) {
        this.Diem = Diem;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }
    
    
}
