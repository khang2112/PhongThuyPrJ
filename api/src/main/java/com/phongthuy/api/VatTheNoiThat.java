package com.phongthuy.api;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Đánh dấu đây là một Collection lưu trong MongoDB
@Document(collection = "vat_the") 
public class VatTheNoiThat {
    
    @Id
    private String id; // ID tự tạo của MongoDB
    private String tenVatThe; // Ví dụ: Bếp ga đôi
    private String loai; // Ví dụ: Bếp
    private String nguHanh; // Ví dụ: Hỏa
    private String mauSac; // Ví dụ: Đỏ, Cam

    // Code bắt buộc: Tạo Constructors (Hàm khởi tạo)
    public VatTheNoiThat() {}

    public VatTheNoiThat(String tenVatThe, String loai, String nguHanh, String mauSac) {
        this.tenVatThe = tenVatThe;
        this.loai = loai;
        this.nguHanh = nguHanh;
        this.mauSac = mauSac;
    }

    // Code bắt buộc: Tạo các hàm Getters và Setters (Bạn có thể dùng tính năng Generate của IDE để tự sinh ra phần này)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTenVatThe() { return tenVatThe; }
    public void setTenVatThe(String tenVatThe) { this.tenVatThe = tenVatThe; }

    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }

    public String getNguHanh() { return nguHanh; }
    public void setNguHanh(String nguHanh) { this.nguHanh = nguHanh; }

    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }
}