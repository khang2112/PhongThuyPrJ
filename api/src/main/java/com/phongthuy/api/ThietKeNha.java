package com.phongthuy.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "thiet_ke_nha")
public class ThietKeNha {
    
    @Id
    private String id;
    private String tenDuAn;
    private String nguoiDungId; // ID của người tạo
    private String moTa;
    private LocalDateTime thoiGianTao;
    private LocalDateTime thoiGianCapNhat;
    
    // Thông tin phong thủy
    private String huongNha; // Hướng nhà (Đông, Tây, Nam, Bắc, ...)
    private String menhChuNha; // Mệnh của chủ nhà
    private String mauSacChuyenSau; // Màu sắc chủ yếu
    private String huongTaiLocDe_Xuat; // Hướng tài lộc được đề xuất
    
    // Danh sách vật thể nội thất được sử dụng
    private List<VatTheThietKe> danhSachVatThe;
    
    public ThietKeNha() {}
    
    public ThietKeNha(String tenDuAn, String nguoiDungId, String moTa) {
        this.tenDuAn = tenDuAn;
        this.nguoiDungId = nguoiDungId;
        this.moTa = moTa;
        this.thoiGianTao = LocalDateTime.now();
        this.thoiGianCapNhat = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTenDuAn() { return tenDuAn; }
    public void setTenDuAn(String tenDuAn) { this.tenDuAn = tenDuAn; }
    
    public String getNguoiDungId() { return nguoiDungId; }
    public void setNguoiDungId(String nguoiDungId) { this.nguoiDungId = nguoiDungId; }
    
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    
    public LocalDateTime getThoiGianTao() { return thoiGianTao; }
    public void setThoiGianTao(LocalDateTime thoiGianTao) { this.thoiGianTao = thoiGianTao; }
    
    public LocalDateTime getThoiGianCapNhat() { return thoiGianCapNhat; }
    public void setThoiGianCapNhat(LocalDateTime thoiGianCapNhat) { this.thoiGianCapNhat = thoiGianCapNhat; }
    
    public String getHuongNha() { return huongNha; }
    public void setHuongNha(String huongNha) { this.huongNha = huongNha; }
    
    public String getMenhChuNha() { return menhChuNha; }
    public void setMenhChuNha(String menhChuNha) { this.menhChuNha = menhChuNha; }
    
    public String getMauSacChuyenSau() { return mauSacChuyenSau; }
    public void setMauSacChuyenSau(String mauSacChuyenSau) { this.mauSacChuyenSau = mauSacChuyenSau; }
    
    public String getHuongTaiLocDe_Xuat() { return huongTaiLocDe_Xuat; }
    public void setHuongTaiLocDe_Xuat(String huongTaiLocDe_Xuat) { this.huongTaiLocDe_Xuat = huongTaiLocDe_Xuat; }
    
    public List<VatTheThietKe> getDanhSachVatThe() { return danhSachVatThe; }
    public void setDanhSachVatThe(List<VatTheThietKe> danhSachVatThe) { this.danhSachVatThe = danhSachVatThe; }
}
