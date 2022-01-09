package com.example.test.repository;

import com.example.test.entity.TypeOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfWorkRepository extends JpaRepository<TypeOfWork, Long> {
}
