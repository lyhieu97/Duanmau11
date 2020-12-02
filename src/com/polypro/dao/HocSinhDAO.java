/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.dao;

import com.polypro.helper.jdbcHelper;
import com.polypro.model.HocSinh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HocSinhDAO {
     

    

     public void insert(HocSinh model) {
        String sql = "INSERT INTO HocSinh (MaHS, HoTen, Diem, GioiTinh) VALUES (?, ?, ?, ?)";
        jdbcHelper.executeUpdate(sql, model.getMaHS(), model.getHoten(), model.getDiem(), model.isGioiTinh());
    }

    public void update(HocSinh model) {
        String sql = "UPDATE HocSinh SET HoTen=?, Diem=?, GioiTinh=? WHERE MaHS=?";
        jdbcHelper.executeUpdate(sql, model.getHoten(), model.getDiem(), model.isGioiTinh(), model.getMaHS());
    }
    public void updatemk(HocSinh model){
        String sql = "Update HocSinh set MaHS=?";
        jdbcHelper.executeUpdate(sql, model.getMaHS());
    }

    public void delete(String MaHS) {
        String sql = "DELETE FROM HocSinh WHERE MaHS=?";
        jdbcHelper.executeUpdate(sql, MaHS);
    }

    public List<HocSinh> select() {
        String sql = "SELECT * FROM HocSinh";
        return select(sql);
    }

    public HocSinh findById(String mahs) {
        String sql = "SELECT * FROM HocSinh WHERE MaHS=?";
        List<HocSinh> list = select(sql, mahs);
        return list.size() > 0 ? list.get(0) : null;
    }
    private List<HocSinh> select(String sql, Object... args) {
        List<HocSinh> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    HocSinh model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    private HocSinh readFromResultSet(ResultSet rs) throws SQLException {
        HocSinh model = new HocSinh();
        model.setMaHS(rs.getString("MaHS"));
        model.setHoten(rs.getString("HoTen"));
        model.setDiem(rs.getString("Diem"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        return model;
    }


    
}
