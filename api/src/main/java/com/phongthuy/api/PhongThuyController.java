package com.phongthuy.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*") // Cho phép giao diện HTML ở bất kỳ đâu gọi vào API này
public class PhongThuyController {

    // Tạo một đường dẫn mạng (endpoint) là: /api/tinh-menh
    @GetMapping("/api/tinh-menh")
    public String tinhMenh(@RequestParam int namSinh) {
        
    	
    	@Autowired
        private VatTheRepository vatTheRepository;

        // API 1: Lấy danh sách toàn bộ vật thể đang có trong database
        @GetMapping("/api/vat-the")
        public List<VatTheNoiThat> layDanhSachVatThe() {
            return vatTheRepository.findAll();
        }

        // API 2: Thêm một vật thể mới vào database
        @PostMapping("/api/vat-the")
        public String themVatThe(@RequestBody VatTheNoiThat vatThe) {
            vatTheRepository.save(vatThe);
            return "Đã lưu thành công vật thể: " + vatThe.getTenVatThe();
        }
        // Logic tính Can Chi được mang vào đây
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

        // Trả về kết quả dưới dạng chuỗi văn bản cho Frontend
        return "Tuổi " + can + " " + chi + " - Mệnh " + menh;
    }
}