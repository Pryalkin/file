package com.example.test.service;

import com.example.test.entity.Application;
import com.example.test.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CustomerRepository customerRepository;
    private final EquipmentRepository equipmentRepository;
    private final PlaceRepository placeRepository;
    private final ManagerRepository managerRepository;

    public void add(Long customer, Long equipment, Long place, Date date) {
        Application application = new Application();
        application.setCustomer(customerRepository.findById(customer).get());
        application.setEquipment(equipmentRepository.findById(equipment).get());
        application.setPlace(placeRepository.findById(place).get());
        application.setDate(date);
        application.setAccepted(false);
        applicationRepository.save(application);
    }

    public void update(Application application) {
        applicationRepository.save(application);
    }

        public List<Application>getAllExceptManager() {
            return applicationRepository.findAll().stream().filter(application -> application.getManager() == null).collect(Collectors.toList());
        }

    public List<Application>getAllExceptNullManager() {
        return applicationRepository.findAll().stream().filter(application -> application.getManager() != null && application.getAccepted() == false).collect(Collectors.toList());
    }

    public void addManager(Long id, Long manager) {
        Application application = applicationRepository.findById(id).get();
        application.setManager(managerRepository.findById(manager).get());
        applicationRepository.save(application);
    }
}
