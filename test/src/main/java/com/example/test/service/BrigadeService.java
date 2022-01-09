package com.example.test.service;

import com.example.test.entity.Brigade;
import com.example.test.entity.Customer;
import com.example.test.repository.AdjusterRepository;
import com.example.test.repository.BrigadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrigadeService {

    public final BrigadeRepository brigadeRepository;

    public void add(Long number){
        Brigade brigade = new Brigade();
        brigade.setNumber(number);
        brigade.setAvailability(false);
        brigadeRepository.save(brigade);
    }

    public List<Brigade> getAllExceptAvailable(){
        return brigadeRepository.findAll().stream().filter(brigade -> brigade.getAvailability() == false).collect(Collectors.toList());
    }

    public List<Brigade> getAll(){
        return brigadeRepository.findAll();
    }
}
