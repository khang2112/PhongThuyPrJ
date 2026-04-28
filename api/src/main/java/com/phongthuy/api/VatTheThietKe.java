package com.phongthuy.api;

public class VatTheThietKe {
    private String vatTheId; // ID từ VatTheNoiThat
    private String ten;
    private String loai;
    private String nguHanh;
    private String mauSac;
    private double viTri_X; // Tọa độ 3D
    private double viTri_Y;
    private double viTri_Z;
    private String huongDat; // Hướng đặt (Đông, Tây, Nam, Bắc)
    
    public VatTheThietKe() {}
    
    public VatTheThietKe(String vatTheId, String ten, String loai, String nguHanh, String mauSac) {
        this.vatTheId = vatTheId;
        this.ten = ten;
        this.loai = loai;
        this.nguHanh = nguHanh;
        this.mauSac = mauSac;
    }
    
    // Getters and Setters
    public String getVatTheId() { return vatTheId; }
    public void setVatTheId(String vatTheId) { this.vatTheId = vatTheId; }
    
    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }
    
    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }
    
    public String getNguHanh() { return nguHanh; }
    public void setNguHanh(String nguHanh) { this.nguHanh = nguHanh; }
    
    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }
    
    public double getViTri_X() { return viTri_X; }
    public void setViTri_X(double viTri_X) { this.viTri_X = viTri_X; }
    
    public double getViTri_Y() { return viTri_Y; }
    public void setViTri_Y(double viTri_Y) { this.viTri_Y = viTri_Y; }
    
    public double getViTri_Z() { return viTri_Z; }
    public void setViTri_Z(double viTri_Z) { this.viTri_Z = viTri_Z; }
    
    public String getHuongDat() { return huongDat; }
    public void setHuongDat(String huongDat) { this.huongDat = huongDat; }
}
