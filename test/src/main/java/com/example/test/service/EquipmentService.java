package com.example.test.service;

import com.example.test.entity.Equipment;
import com.example.test.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public void add(String nameEq, String modelEq){
        Equipment equipment = new Equipment();
        equipment.setNameEq(nameEq);
        equipment.setModelEq(modelEq);
        equipmentRepository.save(equipment);
    }

    public List<Equipment> getAll(){
        return equipmentRepository.findAll();
    }
}
