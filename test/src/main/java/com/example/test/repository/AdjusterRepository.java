package com.example.test.repository;

import com.example.test.entity.Adjuster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjusterRepository extends JpaRepository<Adjuster, Long> {
}
