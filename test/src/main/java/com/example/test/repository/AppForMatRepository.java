package com.example.test.repository;

import com.example.test.entity.AppForMat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppForMatRepository extends JpaRepository<AppForMat, Long> {
}
