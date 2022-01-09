package com.example.test.service;

import com.example.test.entity.AppForMat;
import com.example.test.entity.TypeOfWork;
import com.example.test.entity.Warehouse;
import com.example.test.repository.AppForMatRepository;
import com.example.test.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final AppForMatRepository appForMatRepository;

    public void add(Long material, Long price, Date date){
        Warehouse warehouse = new Warehouse();
        AppForMat appForMat = appForMatRepository.findById(material).get();
        appForMat.setAccepted(true);
        appForMatRepository.save(appForMat);
        warehouse.setMaterial(appForMat);
        warehouse.setPrice(price);
        warehouse.setDate(date);
        warehouse.setAvailability(true);
        warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAll(){
        return warehouseRepository.findAll().stream().filter(warehouse -> warehouse.getAvailability() == true).collect(Collectors.toList());
    }
}
