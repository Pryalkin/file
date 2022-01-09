package com.example.test.service;

import com.example.test.entity.AppForMat;
import com.example.test.entity.Warehouse;
import com.example.test.repository.AppForMatRepository;
import com.example.test.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppForMatService {

    private final AppForMatRepository appForMatRepository;
    private final ManagerRepository managerRepository;

    public void add(String nameMat, String modelName, Date date, Long manager){
        AppForMat appForMat = new AppForMat();
        appForMat.setNameMat(nameMat);
        appForMat.setModelName(modelName);
        appForMat.setDate(date);
        appForMat.setManager(managerRepository.findById(manager).get());
        appForMat.setAccepted(false);
        appForMatRepository.save(appForMat);
    }

    public List<AppForMat> getAll(){
        return appForMatRepository.findAll().stream().filter(appForMat -> appForMat.getAccepted() == false).collect(Collectors.toList());
    }
}
