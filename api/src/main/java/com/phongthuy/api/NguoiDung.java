package com.phongthuy.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "nguoi_dung")
public class NguoiDung {
    
    @Id
    private String id;
    private String ho;
    private String ten;
    private int namSinh;
    private int thangSinh;
    private int ngaySinh;
    private String gioiTinh; // Nam/Nữ
    private String email;
    private String soDienThoai;
    private String dia_chi;
    
    // Constructors
    public NguoiDung() {}
    
    public NguoiDung(String ho, String ten, int namSinh, int thangSinh, int ngaySinh, String gioiTinh) {
        this.ho = ho;
        this.ten = ten;
        this.namSinh = namSinh;
        this.thangSinh = thangSinh;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getHo() { return ho; }
    public void setHo(String ho) { this.ho = ho; }
    
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    
    public int getNamSinh() { return namSinh; }
    public void setNamSinh(int namSinh) { this.namSinh = namSinh; }
    
    public int getThangSinh() { return thangSinh; }
    public void setThangSinh(int thangSinh) { this.thangSinh = thangSinh; }
    
    public int getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(int ngaySinh) { this.ngaySinh = ngaySinh; }
    
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    
    public String getDia_chi() { return dia_chi; }
    public void setDia_chi(String dia_chi) { this.dia_chi = dia_chi; }
}
