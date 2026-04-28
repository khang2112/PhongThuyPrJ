package com.phongthuy.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends MongoRepository<NguoiDung, String> {
    // Spring Data MongoDB tự động cung cấp các method: save(), findAll(), findById(), delete()
}
