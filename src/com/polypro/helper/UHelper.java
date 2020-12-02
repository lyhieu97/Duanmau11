/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polypro.helper;

import com.polypro.dao.ChuyenDeDAO;
import com.polypro.dao.NguoiHocDAO;
import com.polypro.dao.NhanVienDAO;
import com.polypro.model.ChuyenDe;
import com.polypro.model.NguoiHoc;
import com.polypro.model.NhanVien;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class UHelper {
    
    public static String getStatus(double mark){
        if(mark < 0){
            return "Chưa nhập";
        } else if(mark < 3){
            return "Kém";
        } else if(mark < 5){
            return "Yếu";
        } else if(mark < 6.5){
            return "Trung Bình";
        } else if(mark < 7.5){
            return "Khá";
        } else if(mark < 9){
            return "Giỏi";
        } else {
            return "Xuất Sắc";  
        }
    }
    public static boolean checkNull_JTextField(JTextField c, String mss){
        if(c.getText().length() == 0){
            DialogHelper.alert(c, mss + " Không được trống");
            c.requestFocus();
            return false;
        } else{
            return true;
        }
    }
    public static boolean checkNull_JTextArea(JTextArea c, String mss){
        if(c.getText().length() == 0){
            DialogHelper.alert(c, mss + " Không được trống");
            c.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkNull_JCombobox(JComboBox c){
        if(c.getSelectedItem().equals(c)){
            DialogHelper.alert(c, "Không được trống");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkNull_JPasswordField(JPasswordField c, String mss){
        if(new String(c.getPassword()).length() == 0){
            DialogHelper.alert(c, mss + "Không được trống");
            c.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkSize_JPasswordField(JPasswordField c, String mss){
        if (new String(c.getPassword()).length() < 6){
            DialogHelper.alert(c, mss + " Có ít nhất 6 ký tự");
            c.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkSizeMKNV_JPasswordField(JPasswordField c, String mss){
        if(new String(c.getPassword()).length() < 3){
            DialogHelper.alert(c, mss + " có ít nhất 3 ký tự");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkSize_MaCD(JTextField c){
        if(c.getText().length() < 5){
            DialogHelper.alert(c, "Mã CĐ phải <= 5 ký tự");
            return false;
        } else {
            return true;
        }
    }
    public static boolean checkSize_MaNH(JTextField c){
        if(c.getText().length() < 7){
            DialogHelper.alert(c, "Mã NH đúng 7 ký tự");
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkDienThoai_JTextField(JTextField c){
        if(!c.getText().matches("0\\d{9,10}")){
            DialogHelper.alert(c, "Số điện thoại không đúng định dạng !");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkEmail_JTextField(JTextField c){
        if(!c.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")){
            DialogHelper.alert(c, "Email không đúng định dạng !");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkHoTen_JTextField(JTextField c){
        if(!c.getText().matches("[a-zA-Z ]+")){
            DialogHelper.alert(c, "Nhập họ tên không đúng !");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkTenCD_JTextField(JTextField c){
        if(!c.getText().matches("[a-zA-Z ]+")){
            DialogHelper.alert(c, "Chỉ chứa alphabet và ký tự trắng !");
            c.requestFocus();
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkDate_JTextField(JTextField c, String mss){
        Date ngay;
        try{
            ngay = DateHelper.toDate(c.getText(), "dd/MM/yyyy");
            return true;
        }catch(Exception e){
            DialogHelper.alert(c, "Vui lòng nhập đúng định dạng dd/MM/yyyy" + mss);
            return false;
        }
    }
    public static boolean checkDiem_JTextField(JTextField c, String mss){
        double so;
        try{
            so = Double.parseDouble(c.getText());
            return true;
        }catch(Exception e){
            DialogHelper.alert(c, "Vui lòng nhập số thực cho " + mss);
            return false;
        }
    }
    public static boolean checkTrungMaNV_JTextField(JTextField c){
        NhanVienDAO dao = new NhanVienDAO();
        NhanVien model;
        model = dao.findById(c.getText());
        if(model != null){
            DialogHelper.alert(c, c.getText() + " Đã tồn tại !");
            return false;
        } else{
            return true;
        }
    }
    public static boolean checkTrungMaNH_JTextField(JTextField c){
        NguoiHocDAO dao = new NguoiHocDAO();
        NguoiHoc model;
        model = dao.findById(c.getText());
        if(model != null){
            DialogHelper.alert(c, c.getText() + "Đã tồn tại !");
            return false;
        }else{
            return true;
        }
    }
    public static boolean checkTrungMaCD_JTextField(JTextField c){
        ChuyenDeDAO dao = new ChuyenDeDAO();
        ChuyenDe model;
        model = dao.findById(c.getText());
        if(model != null){
            DialogHelper.alert(c, c.getText() + " Đã tồn tại !");
            return false;
        }else{
            return true;
        }
    }
}
