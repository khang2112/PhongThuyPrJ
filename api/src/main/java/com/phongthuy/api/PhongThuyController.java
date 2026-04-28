package com.phongthuy.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class PhongThuyController {

    @Autowired
    private VatTheRepository vatTheRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThietKeNhaRepository thietKeNhaRepository;

    @Autowired
    private PhongThuyService phongThuyService;

    // API 1: Tính chi tiết Phong Thủy dựa trên năm sinh và giới tính
    @GetMapping("/api/tinh-menh")
    public PhongThuyInfo tinhMenh(
            @RequestParam int namSinh, 
            @RequestParam(defaultValue = "Nam") String gioiTinh) {
        
        return phongThuyService.tinhPhongThuy(namSinh, gioiTinh);
    }

    // ... (Các API 2, 3, 4, 5 giữ nguyên không đổi) ...

    // API 6: Lấy thông tin phong thủy của một người dùng cụ thể từ Database
    @GetMapping("/api/nguoi-dung/{id}/phong-thuy")
    public Map<String, Object> layPhongThuyCuaNguoiDung(@PathVariable String id) {
        NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
        
        if (nguoiDung == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Không tìm thấy người dùng");
            return error;
        }

        // Gọi Service tính toán dựa trên dữ liệu lấy từ Database
        PhongThuyInfo info = phongThuyService.tinhPhongThuy(nguoiDung.getNamSinh(), nguoiDung.getGioiTinh());

        // Đóng gói dữ liệu trả về cho client
        Map<String, Object> response = new HashMap<>();
        response.put("ho", nguoiDung.getHo());
        response.put("ten", nguoiDung.getTen());
        response.put("namSinh", nguoiDung.getNamSinh());
        response.put("gioiTinh", nguoiDung.getGioiTinh());
        
        response.put("can", info.getCan());
        response.put("chi", info.getChi());
        response.put("menhNguHanh", info.getMenh());
        response.put("cungMenhBatTrach", info.getCungMenh());
        response.put("nhomTrach", info.getNhomTrach());
        response.put("huongTaiLoc", info.getHuongTaiLoc());
        response.put("mauSacPhuHop", info.getMauSacPhuHop());
        
        return response;
    }

    // API 7: Tạo thiết kế nhà mới
    @PostMapping("/api/thiet-ke-nha")
    public Map<String, String> taoThietKeNha(@RequestBody ThietKeNha thietKeNha) {
        thietKeNhaRepository.save(thietKeNha);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Đã tạo thiết kế nhà: " + thietKeNha.getTenDuAn());
        response.put("id", thietKeNha.getId());
        return response;
    }

    // API 8: Lấy danh sách thiết kế của một người dùng
    @GetMapping("/api/nguoi-dung/{id}/thiet-ke")
    public List<ThietKeNha> layDanhSachThietKe(@PathVariable String id) {
        return thietKeNhaRepository.findByNguoiDungId(id);
    }

    // API 9: Lấy chi tiết thiết kế nhà
    @GetMapping("/api/thiet-ke-nha/{id}")
    public Map<String, Object> layChiTietThietKe(@PathVariable String id) {
        ThietKeNha thietKeNha = thietKeNhaRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        
        if (thietKeNha == null) {
            response.put("error", "Không tìm thấy thiết kế nhà");
            return response;
        }
        
        response.put("id", thietKeNha.getId());
        response.put("tenDuAn", thietKeNha.getTenDuAn());
        response.put("moTa", thietKeNha.getMoTa());
        response.put("huongNha", thietKeNha.getHuongNha());
        response.put("menhChuNha", thietKeNha.getMenhChuNha());
        response.put("mauSacChuyenSau", thietKeNha.getMauSacChuyenSau());
        response.put("huongTaiLocDeXuat", thietKeNha.getHuongTaiLocDe_Xuat());
        response.put("danhSachVatThe", thietKeNha.getDanhSachVatThe());
        response.put("thoiGianTao", thietKeNha.getThoiGianTao());
        response.put("thoiGianCapNhat", thietKeNha.getThoiGianCapNhat());
        
        return response;
    }

    // API 10: Cập nhật thiết kế nhà
    @PutMapping("/api/thiet-ke-nha/{id}")
    public Map<String, String> capNhatThietKe(@PathVariable String id, @RequestBody ThietKeNha thietKeNha) {
        ThietKeNha existing = thietKeNhaRepository.findById(id).orElse(null);
        Map<String, String> response = new HashMap<>();
        
        if (existing == null) {
            response.put("error", "Không tìm thấy thiết kế nhà");
            return response;
        }
        
        thietKeNha.setId(id);
        thietKeNha.setThoiGianCapNhat(java.time.LocalDateTime.now());
        thietKeNhaRepository.save(thietKeNha);
        
        response.put("message", "Đã cập nhật thiết kế nhà thành công");
        return response;
    }

    // API 11: Xóa thiết kế nhà
    @DeleteMapping("/api/thiet-ke-nha/{id}")
    public Map<String, String> xoaThietKe(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        thietKeNhaRepository.deleteById(id);
        response.put("message", "Đã xóa thiết kế nhà thành công");
        return response;
    }

    // API 12: Kiểm tra xung khắc giữa hai vật thể
    @GetMapping("/api/phong-thuy/kiem-tra-xung-khac")
    public Map<String, Object> kiemTraXungKhac(@RequestParam String menh1, @RequestParam String menh2) {
        Map<String, Object> response = new HashMap<>();
        boolean xungKhac = phongThuyService.laXungKhac(menh1, menh2);
        
        response.put("menh1", menh1);
        response.put("menh2", menh2);
        response.put("xungKhac", xungKhac);
        
        if (xungKhac) {
            response.put("message", menh1 + " xung khắc với " + menh2);
        } else {
            response.put("message", menh1 + " không xung khắc với " + menh2);
        }
        
        return response;
    }

    // API 13: Kiểm tra tương sinh giữa hai vật thể
    @GetMapping("/api/phong-thuy/kiem-tra-tuong-sinh")
    public Map<String, Object> kiemTraTuongSinh(@RequestParam String menh1, @RequestParam String menh2) {
        Map<String, Object> response = new HashMap<>();
        boolean tuongSinh = phongThuyService.laTuongSinh(menh1, menh2);
        
        response.put("menh1", menh1);
        response.put("menh2", menh2);
        response.put("tuongSinh", tuongSinh);
        
        if (tuongSinh) {
            response.put("message", menh1 + " tương sinh với " + menh2);
        } else {
            response.put("message", menh1 + " không tương sinh với " + menh2);
        }
        
        return response;
    }
}