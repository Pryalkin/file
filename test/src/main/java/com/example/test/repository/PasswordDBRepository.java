package com.example.test.repository;

import com.example.test.entity.PasswordDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordDBRepository extends JpaRepository<PasswordDB, Long> {
    public Optional<PasswordDB> findByLoginAndPass (String login, String pass);
}
