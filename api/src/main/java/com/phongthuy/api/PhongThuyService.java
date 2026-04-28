package com.phongthuy.api;

import org.springframework.stereotype.Service;

@Service
public class PhongThuyService {
    
    /**
     * Tính toàn bộ thông tin Phong Thủy (Sinh Mệnh + Cung Mệnh Bát Trạch)
     */
    public PhongThuyInfo tinhPhongThuy(int namSinh, String gioiTinh) {
        // 1. TÍNH SINH MỆNH (NGŨ HÀNH)
        int lastDigit = namSinh % 10;
        String[] tenCan = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        int[] giaTriCan = {4, 4, 5, 5, 1, 1, 2, 2, 3, 3};
        String can = tenCan[lastDigit];
        int canValue = giaTriCan[lastDigit];

        int chiIndex = namSinh % 12;
        String[] tenChi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi"};
        int[] giaTriChi = {1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0};
        String chi = tenChi[chiIndex];
        int chiValue = giaTriChi[chiIndex];

        int menhValue = canValue + chiValue;
        if (menhValue > 5) {
            menhValue -= 5;
        }

        String[] nguHanh = {"", "Kim", "Thủy", "Hỏa", "Thổ", "Mộc"};
        String menh = nguHanh[menhValue];
        
        String mauSacPhuHop = getMauSacTheoMenh(menh);
        String huongTaiLoc = getHuongTaiLocTheoMenh(menh);

        // 2. TÍNH CUNG MỆNH VÀ BÁT TRẠCH (Phụ thuộc Giới Tính)
        int remainder = namSinh % 9;
        if (remainder == 0) remainder = 9;

        int cungSo;
        if (gioiTinh != null && (gioiTinh.equalsIgnoreCase("Nữ") || gioiTinh.equalsIgnoreCase("Nu"))) {
            cungSo = 4 + remainder;
            if (cungSo > 9) cungSo -= 9;
            if (cungSo == 5) cungSo = 8; // Nữ thuộc cung 5 quy về Cấn (8)
        } else {
            // Mặc định là Nam
            cungSo = 11 - remainder;
            if (cungSo > 9) cungSo -= 9;
            if (cungSo == 5) cungSo = 2; // Nam thuộc cung 5 quy về Khôn (2)
        }

        String cungMenh = "";
        String nhomTrach = "";
        switch (cungSo) {
            case 1: cungMenh = "Khảm (Thủy)"; nhomTrach = "Đông Tứ Trạch"; break;
            case 2: cungMenh = "Khôn (Thổ)"; nhomTrach = "Tây Tứ Trạch"; break;
            case 3: cungMenh = "Chấn (Mộc)"; nhomTrach = "Đông Tứ Trạch"; break;
            case 4: cungMenh = "Tốn (Mộc)"; nhomTrach = "Đông Tứ Trạch"; break;
            case 6: cungMenh = "Càn (Kim)"; nhomTrach = "Tây Tứ Trạch"; break;
            case 7: cungMenh = "Đoài (Kim)"; nhomTrach = "Tây Tứ Trạch"; break;
            case 8: cungMenh = "Cấn (Thổ)"; nhomTrach = "Tây Tứ Trạch"; break;
            case 9: cungMenh = "Ly (Hỏa)"; nhomTrach = "Đông Tứ Trạch"; break;
        }
        
        return new PhongThuyInfo(can, chi, menh, mauSacPhuHop, huongTaiLoc, cungMenh, nhomTrach);
    }
    
    // ... (Các hàm getMauSacTheoMenh, getHuongTaiLocTheoMenh, laXungKhac, laTuongSinh giữ nguyên) ...
    public String getMauSacTheoMenh(String menh) {
        return switch (menh) {
            case "Kim" -> "Vàng, Trắng, Bạc";
            case "Thủy" -> "Đen, Xanh dương";
            case "Hỏa" -> "Đỏ, Cam, Tím";
            case "Thổ" -> "Cam, Vàng, Nâu";
            case "Mộc" -> "Xanh lá, Xanh ngọc";
            default -> "Không xác định";
        };
    }
    
    public String getHuongTaiLocTheoMenh(String menh) {
        return switch (menh) {
            case "Kim" -> "Hướng Tây, Tây Nam";
            case "Thủy" -> "Hướng Bắc, Tây Bắc";
            case "Hỏa" -> "Hướng Nam, Đông Nam";
            case "Thổ" -> "Hướng Trung Tâm, Tây Nam";
            case "Mộc" -> "Hướng Đông, Đông Bắc";
            default -> "Không xác định";
        };
    }

    public boolean laXungKhac(String menh1, String menh2) {
        if ((menh1.equals("Kim") && menh2.equals("Mộc")) || (menh1.equals("Mộc") && menh2.equals("Kim"))) return true;
        if ((menh1.equals("Hỏa") && menh2.equals("Thủy")) || (menh1.equals("Thủy") && menh2.equals("Hỏa"))) return true;
        return false;
    }
    
    public boolean laTuongSinh(String menh1, String menh2) {
        if ((menh1.equals("Kim") && menh2.equals("Thủy")) || (menh1.equals("Thủy") && menh2.equals("Kim"))) return true;
        if ((menh1.equals("Thủy") && menh2.equals("Mộc")) || (menh1.equals("Mộc") && menh2.equals("Thủy"))) return true;
        if ((menh1.equals("Mộc") && menh2.equals("Hỏa")) || (menh1.equals("Hỏa") && menh2.equals("Mộc"))) return true;
        if ((menh1.equals("Hỏa") && menh2.equals("Thổ")) || (menh1.equals("Thổ") && menh2.equals("Hỏa"))) return true;
        if ((menh1.equals("Thổ") && menh2.equals("Kim")) || (menh1.equals("Kim") && menh2.equals("Thổ"))) return true;
        return false;
    }
}