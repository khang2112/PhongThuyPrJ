package com.phongthuy.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ThietKeNhaRepository extends MongoRepository<ThietKeNha, String> {
    // Tìm tất cả thiết kế của một người dùng
    List<ThietKeNha> findByNguoiDungId(String nguoiDungId);
}
