package com.example.test.service;

import com.example.test.entity.Report;
import com.example.test.entity.Warehouse;
import com.example.test.repository.PlanRepository;
import com.example.test.repository.ReportRepository;
import com.example.test.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    public final ReportRepository reportRepository;
    public final PlanRepository planRepository;
    public final WarehouseRepository warehouseRepository;

    public void add(Long plan, Long material){
        Report report = new Report();
        warehouseRepository.findById(material).ifPresent(warehouse -> {
            warehouse.setAvailability(false);
            warehouseRepository.save(warehouse);
            report.setMaterial(warehouse);
        });
        report.setPlan(planRepository.findById(plan).get());
        reportRepository.save(report);
    }

    public List<Report> getAll(){
        return reportRepository.findAll();
    }
}
