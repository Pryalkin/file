package com.example.test.service;

import com.example.test.entity.Brigade;
import com.example.test.entity.TypeOfWork;
import com.example.test.repository.TypeOfWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfWorkService {

    private final TypeOfWorkRepository typeOfWorkRepository;

    public void add(String name, Long days, Long price){
        TypeOfWork typeOfWork = new TypeOfWork();
        typeOfWork.setTypeOfWork(name);
        typeOfWork.setDays(days);
        typeOfWork.setPrice(price);
        typeOfWorkRepository.save(typeOfWork);
    }

    public List<TypeOfWork> getAll(){
        return typeOfWorkRepository.findAll();
    }
}
