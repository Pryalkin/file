package com.example.test.service;

import com.example.test.entity.Application;
import com.example.test.entity.Brigade;
import com.example.test.entity.Plan;
import com.example.test.entity.Report;
import com.example.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    public final PlanRepository planRepository;
    public final ApplicationRepository applicationRepository;
    public final BrigadeRepository brigadeRepository;
    public final TypeOfWorkRepository typeOfWorkRepository;

    public void add(Long application, Long brigade, Long typeOfWork,
                    Date startDate, Date endDate){
        Plan plan = new Plan();

        Application app = applicationRepository.findById(application).get();
        app.setAccepted(true);
        applicationRepository.save(app);
        plan.setApplication(app);

        Brigade brig = brigadeRepository.findById(brigade).get();
        brig.setAvailability(true);
        brigadeRepository.save(brig);
        plan.setBrigade(brig);

        plan.setTypeOfWork(typeOfWorkRepository.findById(typeOfWork).get());
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        planRepository.save(plan);
    }

    public List<Plan> getAll(){
        return planRepository.findAll();
    }

}
