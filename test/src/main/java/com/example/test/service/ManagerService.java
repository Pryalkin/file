package com.example.test.service;

import com.example.test.entity.Application;
import com.example.test.entity.Manager;
import com.example.test.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;

    public void add(String fname, String lname, String email, String phone) {
        Manager manager = new Manager();
        manager.setFname(fname);
        manager.setLname(lname);
        manager.setEmail(email);
        manager.setPhone(phone);
        managerRepository.save(manager);
    }

    public List<Manager> getAll() {
        return managerRepository.findAll();
    }
}
