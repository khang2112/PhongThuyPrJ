package com.phongthuy.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VatTheRepository extends MongoRepository<VatTheNoiThat, String> {
    // Chỉ cần để trống như thế này. Spring Boot đã làm sẵn các hàm save(), findAll(), findById() cho bạn rồi!
}