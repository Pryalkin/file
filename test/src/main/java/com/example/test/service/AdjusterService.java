package com.example.test.service;

import com.example.test.entity.Adjuster;
import com.example.test.entity.Customer;
import com.example.test.repository.AdjusterRepository;
import com.example.test.repository.BrigadeRepository;
import com.example.test.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdjusterService {

    private final AdjusterRepository adjusterRepository;
    private final BrigadeRepository brigadeRepository;

    public void add(String fname, String lname, Long grade, Long numberBrigades, String phone) {
        Adjuster adjuster = new Adjuster();
        adjuster.setFname(fname);
        adjuster.setLname(lname);
        adjuster.setGrade(grade);
        adjuster.setNumberBrigades(brigadeRepository.findById(numberBrigades).get());
        adjuster.setPhone(phone);
        adjusterRepository.save(adjuster);
    }

    public List<Adjuster> getAll(){
        return adjusterRepository.findAll();
    }

}
