package com.phongthuy.api;

import org.springframework.stereotype.Service;

@Service
public class PhongThuyService {
    
    /**
     * Tính mệnh dựa trên năm sinh
     */
    public PhongThuyInfo tinhPhongThuy(int namSinh) {
        // Tính Can
        int lastDigit = namSinh % 10;
        String[] tenCan = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        int[] giaTriCan = {4, 4, 5, 5, 1, 1, 2, 2, 3, 3};
        
        String can = tenCan[lastDigit];
        int canValue = giaTriCan[lastDigit];

        // Tính Chi
        int chiIndex = namSinh % 12;
        String[] tenChi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi"};
        int[] giaTriChi = {1, 1, 2, 2, 0, 0, 1, 1, 2, 2, 0, 0};
        
        String chi = tenChi[chiIndex];
        int chiValue = giaTriChi[chiIndex];

        // Tính Mệnh (Ngũ Hành)
        int menhValue = canValue + chiValue;
        if (menhValue > 5) {
            menhValue -= 5;
        }

        String[] nguHanh = {"", "Kim", "Thủy", "Hỏa", "Thổ", "Mộc"};
        String menh = nguHanh[menhValue];
        
        // Xác định màu sắc phù hợp dựa trên mệnh
        String mauSacPhuHop = getMauSacTheoMenh(menh);
        
        // Xác định hướng tài lộc
        String huongTaiLoc = getHuongTaiLocTheoMenh(menh);
        
        return new PhongThuyInfo(can, chi, menh, mauSacPhuHop, huongTaiLoc);
    }
    
    /**
     * Lấy màu sắc phù hợp dựa trên mệnh
     */
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
    
    /**
     * Lấy hướng tài lộc dựa trên mệnh
     */
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
    
    /**
     * Kiểm tra xung khắc giữa hai mệnh
     */
    public boolean laXungKhac(String menh1, String menh2) {
        // Kim xung Mộc
        if ((menh1.equals("Kim") && menh2.equals("Mộc")) || (menh1.equals("Mộc") && menh2.equals("Kim"))) {
            return true;
        }
        // Hỏa xung Thủy
        if ((menh1.equals("Hỏa") && menh2.equals("Thủy")) || (menh1.equals("Thủy") && menh2.equals("Hỏa"))) {
            return true;
        }
        return false;
    }
    
    /**
     * Kiểm tra tương sinh giữa hai mệnh
     */
    public boolean laTuongSinh(String menh1, String menh2) {
        // Kim sinh Thủy
        if ((menh1.equals("Kim") && menh2.equals("Thủy")) || (menh1.equals("Thủy") && menh2.equals("Kim"))) {
            return true;
        }
        // Thủy sinh Mộc
        if ((menh1.equals("Thủy") && menh2.equals("Mộc")) || (menh1.equals("Mộc") && menh2.equals("Thủy"))) {
            return true;
        }
        // Mộc sinh Hỏa
        if ((menh1.equals("Mộc") && menh2.equals("Hỏa")) || (menh1.equals("Hỏa") && menh2.equals("Mộc"))) {
            return true;
        }
        // Hỏa sinh Thổ
        if ((menh1.equals("Hỏa") && menh2.equals("Thổ")) || (menh1.equals("Thổ") && menh2.equals("Hỏa"))) {
            return true;
        }
        // Thổ sinh Kim
        if ((menh1.equals("Thổ") && menh2.equals("Kim")) || (menh1.equals("Kim") && menh2.equals("Thổ"))) {
            return true;
        }
        return false;
    }
}
