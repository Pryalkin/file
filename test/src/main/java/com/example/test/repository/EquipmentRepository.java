package com.example.test.repository;


import com.example.test.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    public Equipment findByNameEq(String name);
}
